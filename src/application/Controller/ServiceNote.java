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
import application.Entity.Matiere;
import application.Entity.Note;
import application.Entity.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Hamza
 */
public class ServiceNote {
   
    private Connection con;
    private Statement ste;
    private PreparedStatement pre;
    private ResultSet rs;
    
    public ServiceNote() {
       // con = DBConnect.getInstance().getConnection();

    }
     public void AffecterNote(Note n) throws SQLException{
    PreparedStatement pre=DBConnect.getConnection().prepareStatement("INSERT INTO note ( `matiere`,`note_orale`, `note_ecrit`, `moyenne`, `apprenant`) VALUES ( ?,?, ?, ? ,?);");
    pre.setInt(1,n.getM().getId());
    pre.setDouble(2, n.getNote_orale());
    pre.setDouble(3, n.getNote_ecrit());
    pre.setDouble(4, n.getMoyenne());
    pre.setInt(5, n.getU().getId());
    pre.executeUpdate();
         System.out.println("affectation ");
    
    
     }
     public boolean DeleteNote( int id ) throws SQLException {
        String req1="DELETE FROM note WHERE id="+id;
        PreparedStatement pre=DBConnect.getConnection().prepareStatement(req1);
        if(pre.executeUpdate()!=0){
           System.out.println("Note supprimer");     
    
            return true;
        }else{
           System.out.println("Pas de Note");     

        return false;
        }
        }
         public boolean RechercherParId(int id) throws SQLException{
     String req1="SELECT * FROM note WHERE id="+id+"and role='a:1:{i:0;s:14:\"ROLE_APPRENANT\";}'";
        ste=DBConnect.getConnection().createStatement();
        rs=ste.executeQuery(req1);
        rs.last();
        int nbrRow=rs.getRow();
         if(nbrRow ==1){
            System.out.println("Apprenant avec Note");
        }else{
            System.out.println("Apprenant sans Note");
        }
         return true;
    }
//       String req="select nom, note_orale, moyenne, note_ecrit, nom_matiere \"\n" +
//"//            + \"FROM note INNER JOIN apprenant ON note.apprenant = apprenant.apprenant\"\n" +
//"//            + \" INNER JOIN matiere ON note.matiere = matiere.matiere\"\n" +
//"";
         //Apprenant a=new Apprenant();
          //Matiere m=new Matiere();
         
    public List<Note> AfficherTous() {   
 
          ServiceUser su=new ServiceUser();
          ServiceMatiere sm=new ServiceMatiere();
    

        String req="select * from note";

        List<Note> list=new ArrayList<>();
        try {
            ste=DBConnect.getConnection().createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()){
           list.add(new Note(
                   sm.findById(rs.getInt("matiere")), 
                   rs.getDouble("note_orale"), 
                   rs.getDouble("moyenne"), 
                   rs.getDouble("note_ecrit"), 
                   su.FindById(rs.getInt("apprenant"))
           ));
           
            }
                   return list;             
        
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        System.out.println(list);
        return list;             
        

    }
    
    public boolean UpdateNote(Note n) throws SQLException {

   ste = DBConnect.getConnection().createStatement();
    String query = ("UPDATE note SET note_orale = '"+n.getNote_orale()+"', note_ecrit = '"+n.getNote_ecrit()+"', moyenne = '"+n.getMoyenne()+"' WHERE id = '"+n.getId()+"'");
    if(ste.executeUpdate(query)==0){
        System.out.println("Note est modifier");
       return false;

    }else{
        return true;

    }

    }
   
//    public List<String> affiche() {   
// 
//          ServiceUser sa=new ServiceUser();
//          ServiceMatiere sm=new ServiceMatiere();
//    
//
//        String req="select * from note";
//
//        List<String> list=new ArrayList<>();
//        try {
//            ste=con.createStatement();
//            rs=ste.executeQuery(req);
//            while(rs.next()){
//                   int n1=rs.getInt("matiere");
//                   Note n=new Note(
//                   sm.findById(rs.getInt("matiere")), 
//                   rs.getInt(2), 
//                   rs.getInt(4), 
//                   rs.getDouble(3), 
//                   sa.FindById(rs.getInt("apprenant"))
//           );
//                   
//                   
//                   Apprenant a=sa.findById(rs.getInt("apprenant"));
//                
//                System.out.println(sm.findById(n1)+" "+n.toString());
//         String s=a.getNom();
//             
//                //System.out.println(s);
//                   
//                   int b=rs.getInt(2);
//                   String ch=String.valueOf(b);
//                 
//                   int  c=rs.getInt(4) ;
//                   String ch1=String.valueOf(c);
//
//                   double d=rs.getDouble(3); 
//                   String ch2=String.valueOf(d);
//                Apprenant app=sa.findById(rs.getInt("apprenant"));
//                String ch4=app.getNom();
//                
//              
//              String ch6=s.concat(ch).concat(ch1).concat(ch2).concat(ch4);
//              list.add(ch6);
//           
//            }
//                                
//
//        } catch (SQLException ex) {
//            System.out.println(ex.toString());
//        }
//        return list;  
//
//
//
//
//
//
//}
    public List<String> ListeDesNoteApprenant() throws SQLException{
    String req2="select * from note n ,user u,matiere m where n.apprenant=u.id and n.matiere=m.id and role='a:1:{i:0;s:14:\"ROLE_APPRENANT\";}' ";
        ste=DBConnect.getConnection().createStatement();
        rs=ste.executeQuery(req2);
        List<String> list =new ArrayList<>();
        while(rs.next()){
           list.add("IDNote"+" "+rs.getInt("id")
                   +" "+"Nom Apprenant:"+" "+
                   rs.getString("nom")+" "
                   +"Note Orale:"+" "
                   +rs.getDouble("note_orale")+" "
                   +"Note Ecrit:"+" "+rs.getDouble("note_ecrit")+" "
                   +rs.getDouble("moyenne")+" "+"Moyenne"+" "
                   +"Nom du Matiere:"+" "+rs.getString("nom_matiere"));
        
        }
        list.forEach(System.out::println);
        return list;

    }
       public static List<Note> findByUser(User user) throws SQLException {
      List<Note> list =new ArrayList<>();
      

     String req="select * from note where apprenant="+user.getId()+"";
         
        ResultSet rse = DBConnect.getConnection().createStatement()
.executeQuery(req);
            
            while(rse.next()){
               // User use=new User();
                Note note=new Note();
                note.setId(rse.getInt("id"));
                note.setM(ServiceMatiere.getById(rse.getInt("matiere")));
                note.setU(user);
                
                
                note.setNote_ecrit(rse.getDouble("note_ecrit"));
                                note.setNote_orale(rse.getDouble("note_orale"));
                note.setMoyenne(rse.getDouble("moyenne"));
list.add(note);
        System.out.println(" count useggggggrs  "+ list.size());
     //   System.out.println(" id ab=  "+ absence.getId());

         
         
         
          //u.setAbsence(absence);
         
                        
            }
            return list;
            
     
        }
    

}
    

        

