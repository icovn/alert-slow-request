package net.friend.alert;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Profile("slackAlert")
@Slf4j
public class SlackAlertService implements AlertService {
    @Override
    public void sendAlert(String message) {

    }
}
