package net.friend.service.transfer;

import static net.friend.constant.TopicType.TOPIC_TAIL_FILE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Profile("simpleTransfer")
@Service
@Slf4j
public class SimpleFileContentTransferService implements FileContentTransferService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void sendLineToQueue(String line) {
        log.debug(line);
        simpMessagingTemplate.convertAndSend(TOPIC_TAIL_FILE, line);
    }
}
