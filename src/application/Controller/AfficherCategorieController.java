/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Controller;


import Service.ServiceCategorie;
import application.Entity.Categorie;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Rzouga
 */
public class AfficherCategorieController implements Initializable {

    @FXML
    private TableView<Categorie> table;
    @FXML
    private TableColumn<Categorie, Integer> Id;
    @FXML
    private TableColumn<Categorie, Integer> nom;

    private ObservableList<Categorie> CategorieData = FXCollections.observableArrayList();

    ServiceCategorie sc = new ServiceCategorie();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<Categorie> listCategorie = new ArrayList<Categorie>(); // TODO
        listCategorie = sc.afficher();
        CategorieData.clear();
        CategorieData.addAll(listCategorie);
        table.setItems(CategorieData);
        Id.setCellValueFactory
                (
                        new PropertyValueFactory<>("id")
                );
        nom.setCellValueFactory
                (
                        new PropertyValueFactory<>("nom")
                );
        // TODO
    }    

    @FXML
    private void Supprimer(ActionEvent event) throws SQLDataException {
    
            Categorie sponsorSelec = (Categorie) table.getSelectionModel().getSelectedItem();
                sc.supprimer(sponsorSelec);
                resetTableData();
    }
    public void resetTableData() throws SQLDataException
    {
        List<Categorie> list = new ArrayList<>();
        list = sc.afficher();
        ObservableList<Categorie> data = FXCollections.observableArrayList(list);
        table.setItems(data);
    }
    
}
