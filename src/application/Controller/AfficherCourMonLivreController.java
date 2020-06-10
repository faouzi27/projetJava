/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Controller;

import Service.ServiceCategorie;
import Service.ServiceCourMonLivre;
import application.Entity.Categorie;
import application.Entity.CourMonLivre;
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
public class AfficherCourMonLivreController implements Initializable {

    @FXML
    private TableColumn<CourMonLivre, Integer> id;
    @FXML
    private TableColumn<CourMonLivre, String> nomCour;
    @FXML
    private TableColumn<CourMonLivre, String> Description;
    @FXML
    private TableColumn<CourMonLivre, Integer> matieres;
    @FXML
    private TableView<CourMonLivre> table;

        private ObservableList<CourMonLivre> CourData = FXCollections.observableArrayList();
        
            ServiceCourMonLivre sc = new ServiceCourMonLivre();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
                
        List<CourMonLivre> listCours = new ArrayList<CourMonLivre>(); // TODO
        listCours = sc.afficher();
        CourData.clear();
        CourData.addAll(listCours);
        table.setItems(CourData);
        
        id.setCellValueFactory
                (
                        new PropertyValueFactory<>("id")
                );
        nomCour.setCellValueFactory
                (
                        new PropertyValueFactory<>("nomCour")
                );
               matieres.setCellValueFactory
                (
                        new PropertyValueFactory<>("IdMatier")
                );       Description.setCellValueFactory
                (
                        new PropertyValueFactory<>("description")
                );
        
        // TODO
    }    

    @FXML
    private void Modifier(ActionEvent event) throws IOException {
        
                ModifierCourMonLivreController.coursMonLivre=table.getSelectionModel().getSelectedItem();
                System.out.println("com.gentrepot.views.AffichageProduitController.ModifierProd()"+ ModifierCourMonLivreController.coursMonLivre);
                Parent root = FXMLLoader.load(getClass().getResource("../UI/ModifierCourMonLivre.fxml"));
                Scene scene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLDataException {
     CourMonLivre sponsorSelec = (CourMonLivre) table.getSelectionModel().getSelectedItem();
                sc.supprimer(sponsorSelec);
                resetTableData();
    }
    public void resetTableData() throws SQLDataException
    {
        List<CourMonLivre> list = new ArrayList<>();
        list = sc.afficher();
        ObservableList<CourMonLivre> data = FXCollections.observableArrayList(list);
        table.setItems(data);
    }

    
}
