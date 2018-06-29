package com.example.aghasi.todolist.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String dateToStringParser(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyy  kk:mm");
        return simpleDateFormat.format(date);
    }
}
