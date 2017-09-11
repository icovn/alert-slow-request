package net.friend.util;

import io.thekraken.grok.api.Grok;
import io.thekraken.grok.api.Match;
import io.thekraken.grok.api.exception.GrokException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GrokUtil {
    /**
     * http://grokconstructor.appspot.com/do/construction
     * @param patternFile
     * @param pattern
     * @param input
     * @return
     */
    public static String toJson(String patternFile, String pattern, String input) {
        try{
            ClassLoader classLoader = GrokUtil.class.getClassLoader();
            Grok grok = Grok.create(classLoader.getResource(patternFile).getFile());
            grok.compile(pattern);
            Match gm = grok.match(input);
            gm.captures();
            return gm.toJson();
        }catch (GrokException ex) {
            log.error(ex.getMessage());
            return null;
        }
    }
}
