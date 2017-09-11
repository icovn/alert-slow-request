package net.friend.alert;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Profile("simpleAlert")
@Slf4j
public class SimpleAlertService implements AlertService {
    @Override
    public void sendAlert(String message) {
        log.error(message);
    }
}
