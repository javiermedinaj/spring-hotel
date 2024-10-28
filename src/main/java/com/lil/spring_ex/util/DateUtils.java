package com.lil.spring_ex.util;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Component
public class DateUtils {
    public Date createDateFormatString(String dateString){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        if (StringUtils.hasText(dateString)){
            try {
                date = new Date(format.parse(dateString).getTime());
            } catch (Exception e){
                date = new Date(new java.util.Date().getTime());
            }
        } else {
            date = new Date(new java.util.Date().getTime());
        }
        return date;
    }

    public java.util.Date createDateFromDateString(String dateString) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        if (StringUtils.hasText(dateString)){
            try {
                date = format.parse(dateString);
            } catch (Exception e){
                date = new java.util.Date();
            }
        } else {
            date = new java.util.Date();
        }
        return date;
    }
}