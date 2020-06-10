/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Controller;

import Service.ServiceMatiereMonLivre;
import application.Entity.MatiereMonLivre;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Rzouga
 */
public class AfficheFrontMatiereController implements Initializable {

    @FXML
    private TableView<MatiereMonLivre> table;
    @FXML
    private TableColumn<MatiereMonLivre, Integer> id;
    @FXML
    private TableColumn<MatiereMonLivre, String> nomMatiere;
    @FXML
    private TableColumn<MatiereMonLivre, String> nombreh;
    @FXML
    private TableColumn<MatiereMonLivre, Integer> vote;
    @FXML
    private TableColumn<MatiereMonLivre, Double> rate;
    @FXML
    private TableColumn<MatiereMonLivre, Integer> categorie;
    @FXML
    private ImageView imv;
    
        private ObservableList<MatiereMonLivre> data;

      
     private ObservableList<MatiereMonLivre> MatiereLivreData = FXCollections.observableArrayList();
     
     ServiceMatiereMonLivre sm = new ServiceMatiereMonLivre();
     
     Connection conn = DataSource.getInstance().getCnx();
         private ResultSet rs;




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
    private void ViewsCour(ActionEvent event) throws IOException {
        
                //ModifierMatiereMonLivreController.matLivre=table.getSelectionModel().getSelectedItem();
                  //System.out.println("com.gentrepot.views.AffichageProduitController.ModifierProd()"+ModifierMatiereMonLivreController.matLivre);
                Parent root = FXMLLoader.load(getClass().getResource("../UI/FrontVideo.fxml"));
                Scene scene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }

    @FXML
    private void selectionne(MouseEvent event) {
        
        int indexSelected = table.getSelectionModel().getSelectedIndex();
        MatiereMonLivre s = (MatiereMonLivre) table.getSelectionModel().getSelectedItem();
        int id = s.getId();
            
        try {
            data = FXCollections.observableArrayList();
            String sql = "SELECT * FROM matieremonlivre where id='"+id+"'";
            rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {
                String lien = rs.getString("nomfile");
                System.out.print(lien);
                         Image image = new Image("http://localhost/EcoleO/web/uploads/" + lien);

                imv.setImage(image);

            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        
    }
    
}
