/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Controller;

/**
 *
 * @author acer
 */
import application.Entity.Absence;
import application.Entity.Note;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ServiceAbsence {
    private Connection con;
    private Statement ste;
    private PreparedStatement pre;
    private ResultSet rs;
    
    public ServiceAbsence() {
       //con = DataBase.getInstance().getConnection();

    }
    public void affecter_absence(Absence a) throws SQLException{
    PreparedStatement pre=DBConnect.getConnection().prepareStatement("INSERT INTO `ecole-1`.`absence` ( `nb_absence`,`classe`, `apprenant`) VALUES ( ?, ?, ?);");
    pre.setInt(1, a.getNb_absence());
    pre.setInt(2, a.getC().getId());
    pre.setInt(3, a.getU().getId());
    pre.executeUpdate();
    
    }
    
    //Absence Modifié
     public boolean update(Absence a) throws SQLException {

   ste = DBConnect.getConnection().createStatement();
 String query = ("UPDATE absence SET nb_absence = '"+a.getNb_absence()+"'WHERE id = '"+a.getId()+"'");
    
   if(ste.executeUpdate(query)!=0){
        System.out.println("Absence est modifier");
    return true;
    }else{
    return false;
    }
     }
     
     public static boolean updateAbsence(Absence a) throws SQLException {

   
 String query = ("UPDATE absence SET nb_absence = '"+a.getNb_absence()+"'WHERE id = '"+a.getId()+"'");
    
   if(DBConnect.getConnection().createStatement().executeUpdate(query)!=0){
        System.out.println("Absence est modifier");
    return true;
    }else{
    return false;
    }
     }
     
    public static boolean createAbsence(Absence a) throws SQLException {

   
 String query = ("INSERT INTO `absence`(`id`, `classe`, `apprenant`, `nb_absence`) VALUES ( null,"+a.getC().getId()+","+a.getU().getId()+","+a.getNb_absence()+")");
    
   if(DBConnect.getConnection().createStatement().executeUpdate(query)!=0){
        System.out.println("Absence est modifier");
    return true;
    }else{
    return false;
    }
     }

         public List<Absence> AfficherAbsence() throws SQLException {   
          
          ServiceClasse sc=new ServiceClasse();
          ServiceUser su=new ServiceUser();
    

        String req="select * from absence";

        List<Absence> list=new ArrayList<>();
        try {
            ste=DBConnect.getConnection().createStatement();
            rs=ste.executeQuery(req);
                   

            while(rs.next()){
           list.add(new Absence(rs.getInt("nb_absence"), 
            sc.FindById(rs.getInt("classe")), 
            su.FindById(rs.getInt("apprenant"))));
           
            }
                   return list;             

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return list;    
               }
         
         
         
         
       public List<String> NbAbsenceListe() throws SQLException{
    String req2="select * from absence ab ,user u,classe c where ab.apprenant=u.id and ab.class=c.id and role='a:1:{i:0;s:14:\"ROLE_APPRENANT\";}'";
        ste=DBConnect.getConnection().createStatement();
        rs=ste.executeQuery(req2);
        List<String> list =new ArrayList<>();
        while(rs.next()){
           list.add("IDAbsence "+rs.getInt("id")+" "+"IDApprenant"+" "
                   +rs.getInt("apprenant")+" "+"Nom Apprenant:"+" "+
                   rs.getString("nom")+" "
                   +"Class:"+" "
                   +rs.getInt("niveau")+""
                   +""+rs.getString("type")+" "
                   +"Nombre d'absence:"+" "+rs.getInt("nb_absence")
                   );
        
        }
        list.forEach(System.out::println);
        return list;

    }
}

