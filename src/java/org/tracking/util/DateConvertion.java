/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author root
 */
public class DateConvertion {
    private Long epochTime;
    private String readableDate;
    private String thisDate;
    
     DateFormat formatter;
     Date date = null;
     Calendar cal;
     public String getThisDate(){
        formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
	date = new Date();
	
         return thisDate = (formatter.format(date));
     }
     
     public Long getEpochtime(){
         
         return epochTime;
     }
     public void setEpochTime(String newString){
         formatter =   new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
         date = null;
         try{
             date = formatter.parse(newString);
         }catch(ParseException ex){
             ex.printStackTrace();
         }
         epochTime = date.getTime()/1000L;
         
     }
     /*
      * epoch time to human readable date
      */
     
     public  String getReadableDate(){
         
         return readableDate;
     }
     public void setReadableDate(Long epoch){
         date = new Date(epoch*1000L);
         formatter = new SimpleDateFormat("dd-M-yyyy hh:mm");//"yyyy-MM-dd HH:mm:ss z"   //dd-MM-yyyy HH:mm:ss z
         formatter.setTimeZone(TimeZone.getTimeZone("GMT+3"));
         readableDate = formatter.format(date);
     }
     public static void main(String args[]) throws ParseException {
         LocalDateTime mydate = now();
         long input2 =1417208400; //1372339860/// 1405601341
         String datestring = "29-11-2014 05:31:45";///"yyyy/MMM/dd hh:mm:ss z"
         DateConvertion con1 = new DateConvertion();
         con1.setEpochTime(datestring);
         con1.setReadableDate(input2);
         
         SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
         SimpleDateFormat printFormat = new SimpleDateFormat("HH:mm");
        Date date = parseFormat.parse("2011-04-23 09:30:51:01");
        System.out.println( "from date to time only"+printFormat.format(date)); // prints 09:30:51
       System.out.println("?????????????????????????????????????????????????????????????????????????????????-");
        System.out.println("----------------------------------------------------------------------------------------");  
         // current date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
	//Date date = new Date();
	System.out.println(dateFormat.format(date)); //2013/10/15 16:16:39
         
          System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("input4 convertion from epoch to human date: " + con1.getEpochtime());
        System.out.println("input4 convertion from human date to epoch: " + con1.getReadableDate());
         System.out.println("input4 convertion from human Current date and time: " + dateFormat.format(date));
         System.out.println("input5 convertion Date to current date and time: " + con1.getThisDate());
        System.out.println("----------------------------------------------------------------------------------------");
   
         
     }
    
}