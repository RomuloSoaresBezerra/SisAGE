
package com.ifpb.agendaeletronica.interfaces;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;


public interface DataValidator {
    
    public default boolean validaData(LocalDate inDate){
        String data = inDate.toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        
        if (data == null) return false;
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (data.trim().length() != dateFormat.toPattern().length()) 
            return false;
        
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(data.trim());
        } catch (ParseException e) { 
            return false; 
        }catch (IllegalArgumentException e) {
            System.err.println("Data Invalida");
            return false;
        }
        return true;
    } 
}
