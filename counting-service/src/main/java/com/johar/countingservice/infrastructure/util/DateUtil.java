package com.johar.countingservice.infrastructure.util;

import com.johar.commonlib.api.ResultCode;
import com.johar.commonlib.error.ServiceException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: DateUtil
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-09-10 00:13
 * @Since: 1.0.0
 */
public class DateUtil {

    public static Date toDate(String time){
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = dateFormat.parse(time);

            return date;
        } catch (Exception e){
            throw new ServiceException(ResultCode.PARAM_VALID_ERROR, "time format is valid, must like 'yyyy-MM-dd hh:mm:ss'");
        }
    }
}
