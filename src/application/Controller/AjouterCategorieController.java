/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Controller;


import Service.ServiceCategorie;
import application.Entity.Categorie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Rzouga
 */
public class AjouterCategorieController implements Initializable {

    @FXML
    private TextField nom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    



    @FXML
    private void Ajouter(ActionEvent event) {
        
        ServiceCategorie sc = new ServiceCategorie();
        Categorie c = new Categorie(nom.getText());
        sc.ajouter(c);
        JOptionPane.showMessageDialog(null, "Categorie ajoutée !");

    }
    
}
