package com.hkd.file;

import java.io.File;
import java.time.LocalDate;

public class PathGenerator {

    private LocalDate localDate;

    private String separator=File.separator;

    public PathGenerator(){
        localDate=LocalDate.now();
    }

    public  String ymdPath(){
        String ymPath = ymPath();
        String day=String.valueOf(localDate.getDayOfMonth());
        return ymPath+day+separator;
    }

    public  String ymPath(){
        String yPath=yPath();
        String month=String.valueOf(       localDate.getMonthValue());
        return yPath+month+separator;
    }

    public String yPath(){
        String year=String.valueOf(        localDate.getYear());
        return separator+year+separator;
    }
}
