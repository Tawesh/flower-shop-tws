package com.tws.flowershop.comment.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * @Author:Tws
 * @Date:2023/8/14-21:36
 */
@Component
public class DateTimeUtil {
    /**
     * @return datetime-yyyy-MM-dd HH:mm:ss
    */
    public  String getDateTime() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(currentDate);
    }

    /**
     * @return timestamp
    */
    public String getTimestamp(){
        long timestamp = Instant.now().getEpochSecond();
        return Long.toString(timestamp);

    }

}
