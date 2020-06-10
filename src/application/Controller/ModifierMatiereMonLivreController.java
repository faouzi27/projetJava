/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Controller;

import Service.ServiceCategorie;
import Service.ServiceMatiereMonLivre;
import application.Entity.Categorie;
import application.Entity.MatiereMonLivre;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Rzouga
 */
public class ModifierMatiereMonLivreController implements Initializable {

    @FXML
    private TextField nomM;
    @FXML
    private TextField nbrh;
    @FXML
    private ChoiceBox<String> cat;
        Connection cnx = DataSource.getInstance().getCnx();


        public static MatiereMonLivre matLivre ;

        ServiceCategorie cs = new ServiceCategorie();
    
  public ObservableList catlist (){
    ObservableList<String> l = FXCollections.observableArrayList();
    ObservableList<Categorie> k = cs.afficher();
    Iterator<Categorie> i = k.iterator();
    while(i.hasNext())
    {
        l.add(i.next().getNom());
    }
              return l;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
                cat.setItems(catlist());
               nomM.setText(matLivre.getMatiere());
               nbrh.setText(String.valueOf(matLivre.getNbrhour()));

        // TODO
    }    

    @FXML
    private void Modifier(ActionEvent event) throws SQLException{
        
        ServiceMatiereMonLivre sm = new ServiceMatiereMonLivre();
        
                String req = "select * from categorie where nomCat=?";
    
                  PreparedStatement pst = cnx.prepareStatement(req);
                  pst.setString(1, (String)cat.getSelectionModel().getSelectedItem());
                  ResultSet rs =pst.executeQuery();
                  while(rs.next()){
                  Categorie   c= new Categorie(rs.getInt(1),rs.getString(2));
                     
                  String nomMat = nomM.getText();
                  int hr = Integer.parseInt(nbrh.getText());
                  
                      MatiereMonLivre math = new MatiereMonLivre(matLivre.getId(),c.getId(), hr, nomMat);
                      sm.modifier(math);
    }        
          
    }
    
}
