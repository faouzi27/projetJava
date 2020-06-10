/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import application.Controller.DBConnect;
import java.security.Key;
import java.util.Date;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author acer
 */
public class Main {
     public static void main(String[] args)  {
     
     DBConnect c = new DBConnect();
                
        Date d = new Date();
  
     System.out.println(d.getDate()+"/"+d.getMonth()+"/"+d.getYear()+"\t"+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds());
     System.exit(0);
       
    
     


 
     
     }
     

         
     
}
