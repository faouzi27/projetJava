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
import application.Entity.Matiere;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import application.Entity.Matiere;
/**
 *
 * @author Hamza
 */
public class ServiceMatiere {
     private Connection con;
    private Statement ste;
    private PreparedStatement pre;
    private ResultSet rs;
    
     public ServiceMatiere() {
      //  con = DBConnect.getInstance().getConnection();

    }
       public Matiere findById(int id) {
        String req="Select* from matiere where matiere="+id+";";
        Matiere m=null;
        try {
            ste=DBConnect.getConnection().createStatement();
            rs=ste.executeQuery(req);
            if(rs.next()){
                m= new Matiere(rs.getInt(1), rs.getString("nom_matiere"), rs.getInt(3));
            }
            return m; 
            
        } catch (SQLException ex) {
            return null;
        }
    }
       
           public List<Matiere> displayAll() {
       String sql="Select * from matiere";
        List<Matiere> list=new ArrayList<>();
        try {
            ste=DBConnect.getConnection().createStatement();
            rs=ste.executeQuery(sql);
            while(rs.next()){
                list.add(new Matiere(rs.getInt(1), 
                        rs.getString("nom_matiere"),
                        rs.getInt(3)));
            }
            return list;
        } catch (SQLException ex) {
        }
        return list; 
          }
           
           
     public List<Matiere> findByName() throws SQLException {
      List<Matiere> list =new ArrayList<>();
      ste=DBConnect.getConnection().createStatement();

     String sql="select nom_matiere from matiere";
         
            rs=ste.executeQuery(sql);
            while(rs.next()){
                Matiere m=new Matiere();
                m.setNom_matiere(rs.getString("nom_matiere"));
                list.add(m);
            }
            return list;
            
     
        }    

 public String FindNomMatiere(String nommat) {
        String sql="Select* from matiere where nom_matiere="+nommat+";";
        Matiere m=null;
        try {
            ste=DBConnect.getConnection().createStatement();
            rs=ste.executeQuery(sql);
            if(rs.next()){
                m= new Matiere(rs.getString("nom_matiere"));
            }
            return nommat; 
            
        } catch (SQLException ex) {
            return null;
        }
    }
 
  public static Matiere getById(int id) {
        String sql="Select* from matiere where id="+id+";";
        Matiere matiere=null;
        try {
            DBConnect.getConnection().createStatement();
        ResultSet    rse=DBConnect.getConnection().createStatement().executeQuery(sql);
            if(rse.next()){
                matiere= new Matiere();
                matiere.setNom_matiere(rse.getString("nom_matiere"));
                matiere.setId(id);
                matiere.setCoefficient(rse.getInt("coefficient"));
            }
            return matiere; 
            
        } catch (SQLException ex) {
            return null;
        }
    }
}


