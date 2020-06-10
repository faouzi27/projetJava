/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import application.Entity.CourMonLivre;
import application.Entity.MatiereMonLivre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author Rzouga
 */
public class ServiceCourMonLivre {
        
    private Statement ste;



    
         Connection cnx = DataSource.getInstance().getCnx();

    
    public void ajouter(CourMonLivre t) {
        try {
                                    System.out.println("hahahs !");

            String requete = "INSERT INTO monlivre (nomCour,description,video,Matieremonlivre) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getNomCour());
            pst.setString(2, t.getDescription());
            pst.setString(3, t.getVideo());
            pst.setInt(4, t.getIdMatier());
            pst.executeUpdate();
            System.out.println("Cour ajoutée !");

        } catch (SQLException ex) {
       Logger.getLogger(ServiceCourMonLivre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void supprimer(CourMonLivre t) {
        try {
            String requete = "DELETE FROM monlivre WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Matiere supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


    public void modifier(CourMonLivre t) {
        try {
            String requete = "UPDATE monlivre SET description=?,nomCour=?,Matieremonlivre=? WHERE id="+t.getId();
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getDescription());           
            pst.setString(2, t.getNomCour());           
            pst.setInt(3, t.getIdMatier());           
            pst.executeUpdate();
            System.out.println("Cours modifiée !"+t);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


    public ObservableList<CourMonLivre> afficher() {
        ObservableList<CourMonLivre> c = FXCollections.observableArrayList();


        try {
            String requete = "SELECT * FROM monlivre";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                c.add(new CourMonLivre(rs.getInt(1), rs.getInt(5), rs.getString(3), rs.getString(2), rs.getString(4)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return c;
    }
    
        public int numberevent () throws SQLException{
         int y=0;
          ste=cnx.createStatement() ;
           ResultSet rs=ste.executeQuery("SELECT COUNT(*) as total FROM monlivre ");
           while(rs.next())
           {
                y = rs.getInt("total");
               
               
           }
           System.out.println("total number : "+y);
           return y;
         
     }
    
}
