/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Controller;

import application.Controller.DBConnect;
import application.Entity.Absence;
import application.Entity.Clase;
import application.Entity.User;
import application.Controller.ServiceAbsence;
import application.Controller.ServiceClasse;
import application.Controller.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class InterfaceAbsenceController implements Initializable {
          
    ObservableList<User>ListApprenant;

    @FXML
    private ComboBox<Clase> BoxClasse;
    @FXML
    private TableView<User> ListeViewAbsence;
    @FXML
    private TextField TextNBAbsence;
    @FXML
    private Button btnAbsence;
    @FXML
    private Button btnselection;
    @FXML
    private TableColumn<User, Integer> cId;
    @FXML
    private TableColumn<User, String> cNom;
    @FXML
    private TableColumn<User, String> cPrenom;
    @FXML
    private TableColumn<Clase, String> cClasse;
    @FXML
    private TableColumn<User, Absence> cNbAbsence;

    /**
     * Initializes the controller class.
     */
         
        ObservableList<Absence>ab=FXCollections.observableArrayList();
    @FXML
    private Button rm;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
           ServiceClasse sc=new ServiceClasse();
            
            ObservableList<Clase>classe=FXCollections.observableArrayList(sc.getALL());
          //  ListeViewAbsence.setItems(ab);
            
            BoxClasse.setItems(classe);
            
            // TODO
        } catch (SQLException ex) {
        }
    } 
    
        public void initInterface2(String message)
    
  {
  }
    

    @FXML
    private void AbsenceAffecter(ActionEvent event) throws SQLException {
      String nombreAbsence=TextNBAbsence.getText().toString();
       User user=ListeViewAbsence.getSelectionModel().getSelectedItem();
       Absence absence=user.getAbsence();
       absence.setNb_absence(Integer.parseInt(nombreAbsence));
       if(absence.getId()==-1){
           
ServiceAbsence.createAbsence(absence);
       }
       else{
ServiceAbsence.updateAbsence(absence);
       
       }
        showAbsences();
    }
     @FXML
    private void boxClasseChanged(ActionEvent event) throws SQLException {
         showAbsences();
    }

   public void showAbsences(){
   
         try {
             System.out.println("ici");
         ServiceUser su=new ServiceUser();
        Clase classe=BoxClasse.getSelectionModel().getSelectedItem();
            //System.out.println(classe.getId());
          this.ListApprenant = FXCollections.observableArrayList(su.findByClasse(classe));

              //  ab=serviceUser.findByClasse(classe);
            cId.setCellValueFactory(new PropertyValueFactory<>("id"));
             cNom.setCellValueFactory(new PropertyValueFactory<>("username"));
            cPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            cClasse.setCellValueFactory(new PropertyValueFactory<>("classe"));
            cNbAbsence.setCellValueFactory(new PropertyValueFactory<>("absence"));
            System.out.println(" count users  "+ this.ListApprenant.size());
           ListeViewAbsence.setItems(this.ListApprenant);
           
  } catch (Exception e) {
        }}
    
    
    @FXML
    private void Selection(ActionEvent event) {
        
       User user=ListeViewAbsence.getSelectionModel().getSelectedItem();
       TextNBAbsence.setText(user.getAbsence().getNb_absence()+"");

    }

    @FXML
    private void retourmenu(ActionEvent event) throws IOException {
        
         FXMLLoader fxml=new FXMLLoader(getClass().getResource("/GUI/menugestion.fxml"));
      Parent root=fxml.load();
       TextNBAbsence.getScene().setRoot(root);
    //    MenugestionController InterNote=fxml.getController();
        
    }
    
}
