package net.friend.service.transfer;

import static net.friend.constant.JmsQueue.QUEUE_FILE_TAIL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Profile("jmsTransfer")
@Service
@Slf4j
public class JmsFileContentTransferService implements FileContentTransferService {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendLineToQueue(String line) {
        log.debug(line);
        jmsTemplate.convertAndSend(QUEUE_FILE_TAIL, line);
    }
}
