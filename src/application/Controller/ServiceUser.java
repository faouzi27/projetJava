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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import application.Entity.Absence;
import application.Entity.Clase;
import application.Entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;


/**
 *
 * @author Hamza
 */
public class ServiceUser {
    
     private Connection con;
    private Statement ste;
    private PreparedStatement pre;
    private ResultSet rs;
    
   public ServiceUser() {
 

    }
  public User FindById(int id) {
        String sql="Select * from user where apprennent="+id+"and role = 'a:1:{i:0;s:14:\"ROLE_APPRENANT\";}'"+";";
        User u=null;
        try {
            ste=DBConnect.getConnection().createStatement();
            rs=ste.executeQuery(sql);
            if(rs.next()){
                u= new User(rs.getInt("id"))
                ;
            }
            return u; 
            
        } catch (SQLException ex) {
            return null;
        }
    }
  public List<User> FindByNameApprenant() throws SQLException {
      List<User> list =new ArrayList<>();
      ste=DBConnect.getConnection().createStatement();

     String req="select * from user where roles = 'a:1:{i:0;s:14:\"ROLE_APPRENANT\";}'";
         
            rs=ste.executeQuery(req);
            while(rs.next()){
                
                User u=new User();
                u.setUsername(rs.getString("username"));
                list.add(u);
                         

//                list.add(nom);
            }
            return list;
            
     
        } 
   public List<User> findByClasse(Clase classe) throws SQLException {
      List<User> list =new ArrayList<>();
      ste=DBConnect.getConnection().createStatement();

     String req="select * from user where roles ='a:1:{i:0;s:14:\"ROLE_APPRENANT\";}' and classe="+classe.getId()+"";
      System.out.print(req);
                
            rs=ste.executeQuery(req);
            
            while(rs.next()){
  
                User u=new User();
                u.setUsername(rs.getString("username"));
                u.setId(rs.getInt("id"));
                u.setPrenom(rs.getString("prenom"));
                u.setClase(classe);
        System.out.println(" count useggggggrs  "+ list.size());
         list.add(u);
                      }
            for( User user : list){
                 String req2="select * from absence where classe="+classe.getId()+" and apprenant="+user.getId()+"";
                    System.out.print(req2);
                   
                   ResultSet rs1=ste.executeQuery(req2);
                   System.out.println("hedha size"); 
                   Absence absence=new Absence();
                   absence.setU(user);
                   absence.setC(classe);
          
         int compteur=0;
         while(rs1.next()){
         absence.setId(rs1.getInt("id"));
         absence.setNb_absence(rs1.getInt("nb_absence"));
         compteur++;
         
         }
         if(compteur==0){
         absence.setId(-1);
         }
         user.setAbsence(absence);
                System.out.println(user.getAbsence().getId()+" ------  "+user.getAbsence().getNb_absence());
            }
           
            return list;
           
     
        } 
        public User getApprenant(User u) throws SQLException { 
        User user = new User();
        PreparedStatement pre = DBConnect.getConnection().prepareStatement("SELECT * FROM user WHERE id LIKE ? and roles='a:1:{i:0;s:14:\"ROLE_APPRENANT\";}' ;");

        pre.setInt(1, user.getId());
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String prenom = rs.getString("prenom");

            user.setId(id);
            user.setUsername(username);
            user.setPrenom(prenom);
           
        }
        return user;    
    }
        
        
        
            public List<String> ApprenantNonAffecter() throws SQLException{
          String req2="select * from user where roles='a:1:{i:0;s:14:\"ROLE_APPRENANT\";}' and affecter=0 ";
        ste=DBConnect.getConnection().createStatement();
        rs=ste.executeQuery(req2);
        List<String> list =new ArrayList<>();
        while(rs.next()){
           list.add("Username:"+" "+rs.getString("username")
                   +" "+"Prenom:"+" "+
                   rs.getString("prenom")+" "
                   +"DateNaissance:"+" "
                   +rs.getDate("date_naissance"));
        
        }
        list.forEach(System.out::println);
        return list;

    }
            
          public List<String> ApprenantAffecter() throws SQLException{
         String req2="select * from user where roles='a:1:{i:0;s:14:\"ROLE_APPRENANT\";}' and affecter=1 ";
        ste=DBConnect.getConnection().createStatement();
        rs=ste.executeQuery(req2);
        List<String> list =new ArrayList<>();
        while(rs.next()){
           list.add("Username:"+" "+rs.getString("username")
                   +" "+"Prenom:"+" "+
                   rs.getString("prenom")+" "
                   +"DateNaissance:"+" "
                  +rs.getDate("date_naissance")+" "+
                   rs.getBoolean("affecter")+" "+
                   rs.getString("classe"));
        
        }
        list.forEach(System.out::println);
        return list;

    }
          
          
          public List<User> getByClasse(Clase classe) throws SQLException{
           
              
              String cls=String.valueOf(classe.getId());
                 System.out.print(cls);
         String req2="select * from user where classe="+cls+ " and affecter=1";
           System.out.print(req2);
        ste=DBConnect.getConnection().createStatement();
        rs=ste.executeQuery(req2);
         List<User> listUser;
    listUser =  new ArrayList<User>() ;
   
        while(rs.next()){
            User user= new  User();
            user.setId(rs.getInt("id"));
             user.setUsername(rs.getString("username"));
                       user.setPrenom(rs.getString("prenom"));
                       user.setAffecter(rs.getInt("affecter"));
                       user.setClase(classe);
                     //   user.setDtn(rs.getDate("date_naissance"));
            
        listUser.add(user);
        }
      
        return listUser;

    }
            
}

