/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.biz.mongo;

/**
 *
 * @author Akhilesh yadav
 */
import java.text.ParseException;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Locale;  
public class SimpleDateFormatExample {  
public static void main(String[] args) {  
    Date date = new Date();  
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
    String strDate = formatter.format(date);  
    System.out.println("Date Format with MM/dd/yyyy : "+strDate);  
  
    formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");  
    strDate = formatter.format(date);  
    System.out.println("Date Format with dd-M-yyyy hh:mm:ss : "+strDate);  
  
    formatter = new SimpleDateFormat("dd MMMM yyyy");  
    strDate = formatter.format(date);  
    System.out.println("Date Format with dd MMMM yyyy : "+strDate);  
  
    formatter = new SimpleDateFormat("dd MMMM yyyy zzzz");  
    strDate = formatter.format(date);  
    System.out.println("Date Format with dd MMMM yyyy zzzz : "+strDate);  
  
    formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");  
    strDate = formatter.format(date);  
    System.out.println("Date Format with E, dd MMM yyyy HH:mm:ss z : "+strDate);  
}  
    public static SimpleDateFormat getDateFormat(){
    Date date = new Date();  
    
    SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");  
    String strDate  = formatter.format(date);  
    System.out.println("Date Format with dd-M-yyyy hh:mm:ss : "+strDate);
    return formatter;
}
} 