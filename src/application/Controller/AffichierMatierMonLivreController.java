/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Controller;

import Service.ServiceMatiereMonLivre;
import application.Entity.MatiereMonLivre;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rzouga
 */
public class AffichierMatierMonLivreController implements Initializable {

    @FXML
    private TableView<MatiereMonLivre> table;
    @FXML
    private TableColumn<MatiereMonLivre, Integer> id;
    @FXML
    private TableColumn<MatiereMonLivre, String> nomMatiere;
    @FXML
    private TableColumn<MatiereMonLivre, Integer> nombreh;
    @FXML
    private TableColumn<MatiereMonLivre, Integer> vote;
    @FXML
    private TableColumn<MatiereMonLivre, Double> rate;
    @FXML
    private TableColumn<MatiereMonLivre, Integer> categorie;
    
    
     private ObservableList<MatiereMonLivre> MatiereLivreData = FXCollections.observableArrayList();
     
     ServiceMatiereMonLivre sm = new ServiceMatiereMonLivre();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<MatiereMonLivre> listMatiereMonLivres= new ArrayList<MatiereMonLivre>();
        listMatiereMonLivres = sm.afficher();
        MatiereLivreData.clear();
        MatiereLivreData.addAll(listMatiereMonLivres);
        table.setItems(MatiereLivreData);
        
        
         id.setCellValueFactory
        (
            new PropertyValueFactory<>("id")
        );
        
        nomMatiere.setCellValueFactory
        (
            new PropertyValueFactory<>("matiere")
        );
        
         nombreh.setCellValueFactory
        (
            new PropertyValueFactory<>("nbrhour")
        );
              vote.setCellValueFactory
        (
            new PropertyValueFactory<>("vote")
        );     rate.setCellValueFactory
        (
            new PropertyValueFactory<>("rate")
        );     categorie.setCellValueFactory
        (
            new PropertyValueFactory<>("categorie")
        );
    }    

    @FXML
    private void Modifier(ActionEvent event) throws IOException {
        
                ModifierMatiereMonLivreController.matLivre=table.getSelectionModel().getSelectedItem();
                  System.out.println("com.gentrepot.views.AffichageProduitController.ModifierProd()"+ModifierMatiereMonLivreController.matLivre);
                Parent root = FXMLLoader.load(getClass().getResource("../UI/ModifierMatiereMonLivre.fxml"));
                Scene scene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLDataException {
         MatiereMonLivre sponsorSelec = (MatiereMonLivre) table.getSelectionModel().getSelectedItem();
                sm.supprimer(sponsorSelec);
                resetTableData();
    }
    public void resetTableData() throws SQLDataException
    {
        List<MatiereMonLivre> list = new ArrayList<>();
        list = sm.afficher();
        ObservableList<MatiereMonLivre> data = FXCollections.observableArrayList(list);
        table.setItems(data);
    }

    
    
}
