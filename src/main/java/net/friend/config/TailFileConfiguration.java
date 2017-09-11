package net.friend.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.file.tail.ApacheCommonsFileTailingMessageProducer;

import net.friend.service.transfer.FileContentTransferService;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class TailFileConfiguration {
    @Value("${app.input.filename}")
    private String inputFilename;
    @Value("${app.input.folder}")
    private String inputFolder;


    @Autowired
    private FileContentTransferService fileContentTransferService;

    public MessageProducerSupport fileContentProducer() {
        String inputFile = inputFolder + "/" + inputFilename;
        log.debug("Setup MessageProducerSupport " + inputFile);
        ApacheCommonsFileTailingMessageProducer tailFileProducer =
                new ApacheCommonsFileTailingMessageProducer();
        tailFileProducer.setFile(new File(inputFile));
        tailFileProducer.setPollingDelay(1000);
        return tailFileProducer;
    }

    @Bean
    public IntegrationFlow tailFilesFlow() {
        log.debug("Setup Flows");
        return IntegrationFlows.from(fileContentProducer())
                .handle(fileContentTransferService, "sendLineToQueue")
                .get();
    }
}
