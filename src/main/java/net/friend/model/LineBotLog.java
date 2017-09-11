package net.friend.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class LineBotLog extends BaseLog {
    protected String timeLocal;

    protected String timeZone;

    public String getFormattedEvent() {
        if(event != null) {
            return event.replace("\\x22", "\"");
        }else {
            return null;
        }
    }

    public Date getTimeLocal() {
        //11/Sep/2017:11:27:23
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss");
        try {
            if(timeLocal != null) {
                return dateFormat.parse(timeLocal);
            }else {
                return null;
            }
        }catch (ParseException ex) {
            return null;
        }
    }
}
