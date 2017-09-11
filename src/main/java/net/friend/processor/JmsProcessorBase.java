package net.friend.processor;

import static net.friend.constant.JmsQueue.QUEUE_FILE_TAIL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;

import net.friend.alert.AlertService;

public abstract class JmsProcessorBase {
    @Value("${app.max.request.time}")
    protected float maxRequestTime;

    @Autowired
    protected AlertService alertService;

    @JmsListener(destination = QUEUE_FILE_TAIL)
    public void handleMessage(String content) {
        processMessage(content);
    }

    abstract void processMessage(String content);
}
