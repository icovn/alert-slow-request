package net.friend.processor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import net.friend.model.LineBotLog;
import net.friend.util.GrokUtil;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Component
@Profile("linebotProcessor")
@Slf4j
public class LineBotRequestProcessor extends JmsProcessorBase {
    @Value("${app.processor.line.grok}")
    private String lineGrok;

    @Value("${app.processor.line.pattern}")
    private String linepPattern;

    @Override
    void processMessage(String content) {
        log.debug(content);
        String json = GrokUtil.toJson(linepPattern, lineGrok, content);

        Gson gson = new Gson();
        LineBotLog event = gson.fromJson(json, LineBotLog.class);
        log.debug(event.toString());
        log.debug("requestTime:" + event.getRequestTime());
        log.debug("formattedEvent:" + event.getFormattedEvent());

        if(event.getRequestTime() > maxRequestTime) {
            alertService.sendAlert(
                    event.getTimeLocal() + " - " + event.getRequestTime() + " - " + event.getFormattedEvent());
        }
    }
}
