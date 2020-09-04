package com.johar.countingservice.infrastructure.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName: CountTimeGenerator
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-09-04 08:22
 * @Since: 1.0.0
 */
public class CountTimeGenerator {

    public static Date currentTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
