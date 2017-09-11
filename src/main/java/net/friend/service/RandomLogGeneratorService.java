package net.friend.service;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@ConfigurationProperties(prefix = "app.random")
@Profile({"development", "development-icovn"})
@Service
@Slf4j
public class RandomLogGeneratorService {
    private static int index = 0;

    @Getter
    @Setter
    private List<String> sample;

    public String getRandomLog() {
        int len = sample.size();
        return sample.get((int)(Math.random() * len)) + "\n";
    }

    public String getLog() {
        if(index >= sample.size()) {
            index = 0;
        }
        return sample.get(index++) + "\n";
    }
}
