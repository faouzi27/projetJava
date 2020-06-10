/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import application.Entity.Categorie;
import application.Entity.MatiereMonLivre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author Rzouga
 */
public class ServiceMatiereMonLivre {
    
    
     Connection cnx = DataSource.getInstance().getCnx();

    
    public void ajouter(MatiereMonLivre t) {
        try {
            String requete = "INSERT INTO matieremonlivre (categorie,matiere,nbHeure,nomfile,rate,vote) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getCategorie());
            pst.setString(2, t.getMatiere());
            pst.setInt(3, t.getNbrhour());
            pst.setString(4, t.getImage());
            pst.setDouble(5, 0);
            pst.setInt(6, 0);
            pst.executeUpdate();
            System.out.println("Matiere ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


    public void supprimer(MatiereMonLivre t) {
        try {
            String requete = "DELETE FROM matieremonlivre WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Matiere supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


    public void modifier(MatiereMonLivre t) {
        try {
            String requete = "UPDATE matieremonlivre SET categorie=?,matiere=?,nbHeure=? WHERE id="+t.getId();
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getCategorie());           
            pst.setString(2, t.getMatiere());           
            pst.setInt(3, t.getNbrhour());           
            pst.executeUpdate();
            System.out.println("Matiere modifiée !"+t);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


    public ObservableList<MatiereMonLivre> afficher() {
        ObservableList<MatiereMonLivre> c = FXCollections.observableArrayList();


        try {
            String requete = "SELECT * FROM matieremonlivre";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                c.add(new MatiereMonLivre(rs.getInt(1),rs.getInt(2) , rs.getInt(7), rs.getInt(4), rs.getString(3), rs.getString(5), rs.getDouble(6)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return c;
    }
    
}
