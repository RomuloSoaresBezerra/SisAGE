/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.agendaeletronica.entidades;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.dateTime;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author romulo
 */
public class Teste {
    public static void main(String[] args) throws ParseException{
        String data = "2016/03/02";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date = LocalDate.parse(data,formatter);
        System.out.println(date.getClass());
          
          
       
        
        
        
    }
   
}
