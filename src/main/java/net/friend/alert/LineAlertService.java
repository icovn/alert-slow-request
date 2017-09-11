package net.friend.alert;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@ConfigurationProperties(prefix = "app.alert.line")
@Profile("lineAlert")
@Slf4j
public class LineAlertService implements AlertService {
    @Getter
    @Setter
    private List<String> mids;

    @Autowired
    private LineMessagingClient lineMessagingClient;

    @Override
    public void sendAlert(String message) {
        for (String mid: mids) {
            PushMessage pushMessage = new PushMessage(mid, new TextMessage(message));
            lineMessagingClient.pushMessage(pushMessage);
        }
    }
}
