/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rli
 */
public class Calendario {
    
    public String getData(int print){
        String data = null;
        
        Calendar cal = Calendar.getInstance();
        //cal.add(Calendar.DATE, 1);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        //System.out.println(cal.getTime());
        // Ouput "Wed Sep 26 14:23:28 EST 2012"

        data = format1.format(cal.getTime());
        if(print==1){
            System.out.println("data: "+data);
        }
        return data;
    }
    public String getHora(int print){
        String hora = null;
        
        Calendar cal = Calendar.getInstance();
        //cal.add(Calendar.DATE, 1);
        SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss");
        //System.out.println(cal.getTime());
        // Ouput "Wed Sep 26 14:23:28 EST 2012"

        hora = format1.format(cal.getTime());
        if(print == 1){
            System.out.println("hora: "+hora);
        }
        return hora;
    }
    
    public String getDia(int print){
        String dia = null;
        String diaEng = null;
        //Locale.setDefault (new Locale ("pt", "BR")); 
        Locale.setDefault (new Locale ("en", "US"));  
        DateFormat df = new SimpleDateFormat ("yyyy-MM-dd");  
        Date dt = null;  
        try {
            dt = df.parse (getData(1));
        } catch (ParseException ex) {
            Logger.getLogger(Calendario.class.getName()).log(Level.SEVERE, null, ex);
        }
        DateFormat df2 = new SimpleDateFormat ("EEEE");  
        //System.out.println (df2.format (dt));
        diaEng = df2.format (dt);
        if(diaEng.equals("Monday")){
            dia = "segunda";
        }else if (diaEng.equals("Tuesday")){
            dia = "terca";
        }else if (diaEng.equals("Wednesday")){
            dia = "quarta";
        }else if (diaEng.equals("Thursday")){
            dia = "quinta";
        }else if (diaEng.equals("Friday")){
            dia = "sexta";
        }else if (diaEng.equals("Saturday")){
            dia = "sabado";
        }else if (diaEng.equals("Sunday")){
            dia = "domingo";
        }
        
        if(print==1){
            System.out.println ("dia: "+dia);
        }
        
        return dia;
    }
    
    public String getDiaFormatado(int print){
        String dia = null;
        String diaEng = null;
        //Locale.setDefault (new Locale ("pt", "BR")); 
        Locale.setDefault (new Locale ("en", "US"));  
        DateFormat df = new SimpleDateFormat ("yyyy-MM-dd");  
        Date dt = null;  
        try {
            dt = df.parse (getData(0));
        } catch (ParseException ex) {
            Logger.getLogger(Calendario.class.getName()).log(Level.SEVERE, null, ex);
        }
        DateFormat df2 = new SimpleDateFormat ("EEEE");  
        //System.out.println (df2.format (dt));
        diaEng = df2.format (dt);
        if(diaEng.equals("Monday")){
            dia = "Segunda-Feira";
        }else if (diaEng.equals("Tuesday")){
            dia = "Terça-Feira";
        }else if (diaEng.equals("Wednesday")){
            dia = "Quarta-Feira";
        }else if (diaEng.equals("Thursday")){
            dia = "Quinta-Feira";
        }else if (diaEng.equals("Friday")){
            dia = "Sexta-Feira";
        }else if (diaEng.equals("Saturday")){
            dia = "Sábado";
        }else if (diaEng.equals("Sunday")){
            dia = "Domingo";
        }
        if(print == 1){
            System.out.println ("dia: "+dia);
        }
        return dia;
    }
}
