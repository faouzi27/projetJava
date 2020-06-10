
package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class AcceuilController implements Initializable {

    @FXML
    private BorderPane bp2;
        public void chargerpage (String page) 
    {
        Parent root = null ;
        try {
            root = FXMLLoader.load(getClass().getResource("../UI/"+page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp2.setCenter(root);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void AjouterCategorie(ActionEvent event) {
             chargerpage("AjoutCategorie");

    }

    @FXML
    private void AfficherCategorie(ActionEvent event) {
             chargerpage("AfficherCategorie");

    }

    @FXML
    private void AjouterMatiere(ActionEvent event) {
      chargerpage("AjouterMatiereMonLivre");

    }

    @FXML
    private void AjouterCour(ActionEvent event) {
                     chargerpage("AjouterCourMonlivre");

    }

    @FXML
    private void AfficherCour(ActionEvent event) {
                     chargerpage("AfficherCourMonLivre");

    }

    @FXML
    private void AfficherMatiere(ActionEvent event) {
        chargerpage("AffichierMatierMonLivre");

    }

    @FXML
    private void AjouterProduit(MouseEvent event) {
    }
    
}
