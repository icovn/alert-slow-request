package net.friend.demo;

import io.thekraken.grok.api.Grok;
import io.thekraken.grok.api.Match;
import io.thekraken.grok.api.exception.GrokException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoJavaGrok {
    public static void main(String[] args) {
        try{
            ClassLoader classLoader = DemoJavaGrok.class.getClassLoader();

            /** Create a new grok instance */
            Grok grok = Grok.create(classLoader.getResource("patterns/patterns").getFile());

            /** Grok pattern to compile, here httpd logs */
            grok.compile("\\A%{NUMBER:requestTime}%{SPACE}\\[%{NOTSPACE:time} %{ISO8601_TIMEZONE}]%{SPACE}%{QS:event}");

            /** Line of log to match */
            String logLine =
                    "0.009 [11/Sep/2017:11:27:23 +0900] "
                    + "\"{\\x22events\\x22:[{\\x22type\\x22:\\x22message\\x22,"
                    + "\\x22replyToken\\x22:\\x224d55f772c76c43d7889da52c813a09ad\\x22,"
                    + "\\x22source\\x22:{\\x22userId\\x22:\\x22uba6dcf96ef8ade154b72ffc25822320c\\x22,"
                    + "\\x22type\\x22:\\x22user\\x22},\\x22timestamp\\x22:1505096843588,"
                    + "\\x22message\\x22:{\\x22type\\x22:\\x22text\\x22,\\x22id\\x22:\\x226678537394188\\x22,"
                    + "\\x22text\\x22:\\x220\\x22}}]}\"";

            Match gm = grok.match(logLine);
            gm.captures();

            log.info(gm.toJson());
        }catch (GrokException ex) {
            log.error(ex.getMessage());
        }
    }
}
