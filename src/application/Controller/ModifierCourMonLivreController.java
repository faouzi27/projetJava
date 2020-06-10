/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Controller;

import Service.ServiceCourMonLivre;
import Service.ServiceMatiereMonLivre;
import application.Entity.CourMonLivre;
import application.Entity.MatiereMonLivre;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Rzouga
 */

public class ModifierCourMonLivreController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField description;
    @FXML
    private ChoiceBox<String> matier;
    
    public static CourMonLivre coursMonLivre ;
        Connection cnx = DataSource.getInstance().getCnx();
        ServiceMatiereMonLivre smv = new ServiceMatiereMonLivre();


 public ObservableList catlist (){
    ObservableList<String> l = FXCollections.observableArrayList();
    ObservableList<MatiereMonLivre> k = smv.afficher();
    Iterator<MatiereMonLivre> i = k.iterator();
    while(i.hasNext())
    {
        l.add(i.next().getMatiere());
    }
              return l;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
               matier.setItems(catlist());
               nom.setText(coursMonLivre.getNomCour());
               description.setText(coursMonLivre.getDescription());

        // TODO
    }    

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
     String req = "select * from matieremonlivre where matiere=?";
    
                  PreparedStatement pst = cnx.prepareStatement(req);
                  pst.setString(1, (String)matier.getSelectionModel().getSelectedItem());
                  ResultSet rs =pst.executeQuery();
                                          System.out.println("req !");

                  while(rs.next()){
                  MatiereMonLivre   c= new MatiereMonLivre(rs.getInt(1));
                     
                  String nomc = nom.getText();
                  String des = description.getText();
                      System.out.println("application.Controller.AjouterCourMonlivreController.Ajouter()"+nom);
                      CourMonLivre cours = new CourMonLivre(c.getId(), des, nomc,coursMonLivre.getId());
                      ServiceCourMonLivre sm = new ServiceCourMonLivre();
                      sm.modifier(cours);
    }
    }
    
}
