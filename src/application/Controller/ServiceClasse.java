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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import application.Entity.Clase;
import application.Entity.Matiere;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author Hamza
 */
public class ServiceClasse {
    
     private Connection con;
    private Statement ste;
    private PreparedStatement pre;
    private ResultSet rs;
    //SupprimerclassController s;
    
    
    public ServiceClasse() throws SQLException {
        con = DBConnect.getConnection();

    }
    
    //Ajouter Class 
    public void AjouterClasse(Clase c) throws SQLException{
    PreparedStatement pre=con.prepareStatement("INSERT INTO `ecole-1`.`classe` ( `niveau`, `type`) VALUES ( ?, ?);");
    pre.setInt(1, c.getNiveau());
    pre.setString(2, c.getType());
    pre.executeUpdate();
    System.out.println("HALA");
        
        }
    

    //delete class
    public boolean DeleteClasse( int id ) throws SQLException {
        String req2="SELECT * FROM classe WHERE id="+id;
        pre=DBConnect.getConnection().prepareStatement(req2);
        pre.execute(req2);
        rs=pre.getResultSet();
        if(rs.next()){
        String req1="DELETE FROM classe WHERE id="+id;
        System.out.println(req1);
        PreparedStatement pre=DBConnect.getConnection().prepareStatement(req1);
        System.out.println("Classe supprimer");      
        pre.executeUpdate();
        }else{
        System.out.println("pas de classe");      

        }
       return true;
    }
    
    //modifier les champ du class a partir de l'class
    public boolean UpdateClasse(Clase c) throws SQLException {

   ste = DBConnect.getConnection().createStatement();
 String query = ("UPDATE classe SET niveau = '"+c.getNiveau()+"', type = '"+c.getType()+"'WHERE id = '"+c.getId()+"'");
   if(ste.executeUpdate(query)!=0){
        System.out.println("Classe est modifier");
    return true;
    }else{
    return false;
    }
    }
    
     //lister les class
    public List<Clase> Afficher() throws SQLException {    
    List<Clase> arr=new ArrayList<Clase>();
    ste=DBConnect.getConnection().createStatement();
    ResultSet rs=ste.executeQuery("select * from ecole-1.`classe`");
     while (rs.next()) {                
               
     arr.add(new Clase(rs.getInt(1), rs.getInt(2), rs.getString("type")));
             


     }

   return arr;
    }
    
     public Clase FindById(int id) {
        String req="Select* from classe where id="+id+";";
        Clase c=null;
        try {
            ste=DBConnect.getConnection().createStatement();
            rs=ste.executeQuery(req);
            if(rs.next()){
                c= new Clase(rs.getInt(1), rs.getInt(2), rs.getString("type"));
            }
            return c; 
            
        } catch (SQLException ex) {
            return null;
        }
    }
     
     public List<String> ClassList() throws SQLException{
    String req2="select * from classe ";
        ste=DBConnect.getConnection().createStatement();
        rs=ste.executeQuery(req2);
        List<String> list =new ArrayList<>();
        while(rs.next()){
           list.add("Classe:"+" "+
                   rs.getInt("niveau")+" "
                   +
                   rs.getString("type")+" "
                   );
        
        }
        list.forEach(System.out::println);
        return list;

    }
     public List<Clase> getALL() throws SQLException{
    String req2="select * from classe ";
        ste=DBConnect.getConnection().createStatement();
        rs=ste.executeQuery(req2);
        List<Clase> classes =new ArrayList<>();
        while(rs.next()){
            Clase classe= new Clase();
            classe.setId(rs.getInt("id"));
            classe.setNiveau(rs.getInt("niveau"));
            classe.setType(rs.getString("type"));
          classes.add(classe);
        
        }
       
        return classes;

    }
     
     
       public Clase TriClasseParNiveau(int id) {
        String sql="Select* from classe where id="+id+" ORDER by niveau";
        Clase c=null;
        try {
            ste=DBConnect.getConnection().createStatement();
            rs=ste.executeQuery(sql);
            if(rs.next()){
                c= new Clase(rs.getInt("id"));
            }
            return c; 
            
        } catch (SQLException ex) {
            return null;
        }
    }
       
         public List<Clase> TrieNiveau() throws SQLException{
        String req2="select * from classe ORDER BY  niveau";
        ste=DBConnect.getConnection().createStatement();
        rs=ste.executeQuery(req2);
        List<Clase> list =new ArrayList<>();
        while(rs.next()){
         list.add(new Clase(rs.getInt("id"), rs.getInt("niveau"), rs.getString("type")));
        
        }
        return list;

    }
       
}
