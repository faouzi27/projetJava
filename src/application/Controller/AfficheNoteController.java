package application.Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import application.Controller.ServiceMatiere;
import application.Controller.ServiceNote;
import application.Controller.ServiceUser;
import application.Entity.Matiere;
import application.Entity.Note;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class AfficheNoteController implements Initializable {
   ServiceMatiere sm=new ServiceMatiere();
    ServiceNote sn=new ServiceNote();
    ServiceUser su=new ServiceUser();
    
    private Connection con;
    private Statement ste;
    private PreparedStatement pre;
    private ResultSet rs;
    
    @FXML
    private TableView<Note> TableDesNotes;
    @FXML
    private TableColumn<Note, Integer> cID;
    @FXML
    private TableColumn<Note, Matiere> cMatiere;
    @FXML
    private TableColumn<Note, Double> cNoteEcrit;
    @FXML
    private TableColumn<Note, Double> cNoteOrale;
    @FXML
    private TableColumn<Note, Double> cMoyenne;
        
    ObservableList<Note>ListNote;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.ListNote = FXCollections.observableArrayList(sn.AfficherTous());
                
            cMatiere.setCellValueFactory(new PropertyValueFactory<>("m"));
            cNoteEcrit.setCellValueFactory(new PropertyValueFactory<>("note_ecrit"));
            cNoteOrale.setCellValueFactory(new PropertyValueFactory<>("note_orale"));
            cMoyenne.setCellValueFactory(new PropertyValueFactory<>("moyenne"));
            cID.setCellValueFactory(new PropertyValueFactory<>("u"));
            TableDesNotes.setItems(ListNote);
    }    
    
}
