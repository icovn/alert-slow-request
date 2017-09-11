package net.friend.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.endpoint.MethodInvokingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;

import net.friend.service.RandomLogGeneratorService;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Profile({"development", "development-icovn"})
@Slf4j
public class WriteFileConfiguration {
    @Value("${app.input.filename}")
    private String inputFilename;
    @Value("${app.input.folder}")
    private String inputFolder;

    @Autowired
    private RandomLogGeneratorService randomLogGeneratorService;

    @Bean
    public MessageSource<?> quoteSource() {
        MethodInvokingMessageSource source = new MethodInvokingMessageSource();
        source.setObject(randomLogGeneratorService);
        source.setMethodName("getLog");
        return source;
    }

    @Bean
    public IntegrationFlow writeQuotesFlow() {
        return IntegrationFlows.from(quoteSource(), c -> c.poller(Pollers.fixedRate(2000)))
                .enrichHeaders(s -> s.header("file_name", inputFilename))
                .handle(fileWritingMessageHandler())
                .get();
    }

    @Bean
    public FileWritingMessageHandler fileWritingMessageHandler() {
        File folder = new File(inputFolder);
        log.debug(folder.getAbsolutePath());
        FileWritingMessageHandler fileWritingMessageHandler = new FileWritingMessageHandler(folder);
        fileWritingMessageHandler.setFileExistsMode(FileExistsMode.APPEND);
        fileWritingMessageHandler.setExpectReply(false);
        return fileWritingMessageHandler;
    }
}
