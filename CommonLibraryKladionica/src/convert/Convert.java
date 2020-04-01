/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convert;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author not-sure
 */
public class Convert {
    public static String date2String(Date d){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        String text = sdf.format(d);
        return text;
    }
    
    public static String date2StringNice(Date d){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm yyyy-MM-dd");
        String text = sdf.format(d);
        return text;
    }
    
    public static Date addHours(Date d, int hours){
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return cal.getTime();
    }
}
