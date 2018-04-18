/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidad;

import java.util.regex.Pattern;

/**
 *
 * @author Yornel
 */
public class Numeros {
    
    public static Double round(double value) {
        int places = 2;
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    
     public static String roundToString(double value) {
        int places = 2;
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        Double rounded = (double) tmp / factor;
        String roundedString = rounded.toString();
        String[] parts = roundedString.split(Pattern.quote("."));
        String partDecimal = parts[1];
        if (partDecimal.length() == 1) {
            roundedString += "0";
        }
        return roundedString;
    }
    
    public static Double round(String valueString) {
        
        double value = 0d;
        
        if (!valueString.equals("") && !valueString.equals("."))
            value = Double.valueOf(valueString);
        
        int places = 2;
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    
    public static Integer roundInt(double d){
        double dAbs = Math.abs(d);
        int i = (int) dAbs;
        double result = dAbs - (double) i;
        if(result<0.5){
            return d<0 ? -i : i;            
        }else{
            return d<0 ? -(i+1) : i+1;          
        }
    }
}
