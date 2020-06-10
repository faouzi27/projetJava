/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import application.Entity.Categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author oussema
 */
public class ServiceCategorie  {
    Connection cnx = DataSource.getInstance().getCnx();

    
    public void ajouter(Categorie t) {
        try {
            String requete = "INSERT INTO categorie (nomCat) VALUES (?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.executeUpdate();
            System.out.println("Categorie ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


    public void supprimer(Categorie t) {
        try {
            String requete = "DELETE FROM categorie WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Categorie supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


    public void modifier(Categorie t) {
        try {
            String requete = "UPDATE categorie SET nomCat=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(2, t.getId());
            pst.setString(1, t.getNom());           
            pst.executeUpdate();
            System.out.println("Categorie modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


    public ObservableList<Categorie> afficher() {
        ObservableList<Categorie> c = FXCollections.observableArrayList();


        try {
            String requete = "SELECT * FROM categorie";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                c.add(new Categorie(rs.getInt(1), rs.getString(2)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return c;
    }
    
}
