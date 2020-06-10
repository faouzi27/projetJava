/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Controller;

import application.Controller.DBConnect;
import application.Entity.Clase;
import application.Entity.Matiere;
import application.Entity.Note;
import application.Entity.User;
import application.Controller.ServiceClasse;
import application.Controller.ServiceMatiere;
import application.Controller.ServiceNote;
import application.Controller.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Observable;
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
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class InterfaceNoteController implements Initializable {

    ServiceMatiere sm=new ServiceMatiere();
    ServiceNote sn=new ServiceNote();
    ServiceUser su=new ServiceUser();
     ServiceClasse serviceClasse=new ServiceClasse();
    
    private Connection con;
    private Statement ste;
    private PreparedStatement pre;
    private ResultSet rs;
    @FXML
    private Button selection;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btnSupp;
    @FXML
    private TableColumn<Note, Integer> cID;
    @FXML
    private TextField textID;
      public InterfaceNoteController() throws SQLException {
       
       

        //con = DataBase.getInstance().getConnection();

    }
           
    ObservableList<Matiere> ListMatiere;
        ObservableList<Clase> classes;
    ObservableList<User>ListApprenant;
        ObservableList<Note>ListNote;


    @FXML
    private Button affecterNote;
    @FXML
    private TextField textecrit;
    @FXML
    private TextField textorale;
    @FXML
    private TextField textmoyenne;
    @FXML
    private ComboBox<Matiere> BoxMatiere;
    @FXML
    private ComboBox<User> BoxApp;
     @FXML
    private ComboBox<Clase> boxClasse;
    @FXML
    private Button rm;
    @FXML
    private TableView<Note> TableDesNotes;
    @FXML
    private TableColumn<Note, Matiere> CMatiere;
    @FXML
    private TableColumn<Note, Double> CNoteOrale;
    @FXML
    private TableColumn<Note, Double> CNoteEcrit;
    @FXML
    private TableColumn<Note, Double> CMoyenne;
    private TableColumn<?, ?> CApprenant;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try{
         this.ListMatiere = FXCollections.observableArrayList(sm.displayAll());
         this.ListApprenant = FXCollections.observableArrayList(su.FindByNameApprenant());
         this.classes = FXCollections.observableArrayList(serviceClasse.getALL());
        BoxMatiere.setItems(ListMatiere);
        boxClasse.setItems(classes);
        BoxApp.setItems(ListApprenant); 
       // this.initInterface("");
         
         }
       catch (SQLException ex) {

       }
       
    }    

    @FXML
    private void Affecter_Note(ActionEvent event) throws IOException, SQLException {
    
        ServiceNote sn=new ServiceNote();
        ServiceUser su=new ServiceUser();
        ServiceMatiere mat=new ServiceMatiere();
       String ecrit=textecrit.getText();
       double tecrit=Double.parseDouble(ecrit);  
        String orale=textorale.getText();
       double torale=Double.parseDouble(orale);
       String moyenne=textmoyenne.getText();
       double tmoyenne=Double.parseDouble(moyenne);
       Matiere ValueMatiere=BoxMatiere.getItems().get(BoxMatiere.getSelectionModel().getSelectedIndex()); 
       User ValueApprenant=BoxApp.getItems().get(BoxApp.getSelectionModel().getSelectedIndex());
        System.out.println("id matiere "+ValueMatiere.getId());
        System.out.println("id user "+ValueApprenant.getId());
//         Matiere value=boxmatiere.getSelectionModel().getSelectedItem();  
       
    
    if( tecrit > 20  ){
                      JOptionPane.showMessageDialog(null, "Note Ecrit doit etre Inferieur 20");
        
        }else if( torale > 20 ){
                      JOptionPane.showMessageDialog(null, "Note Orale doit etre Inferieur 20");

    } else if( tmoyenne > 20 ){
                      JOptionPane.showMessageDialog(null, "Note Moyenne doit etre Inferieur 20");
 }else{
       try {

//            System.out.println(ValueMatiere.getNom_matiere());
//            System.out.println(ValueApprenant.getNom());
Note n=new Note(ValueMatiere, torale, tecrit, tmoyenne, ValueApprenant);
        System.out.println(ValueApprenant.getId());
        System.out.println(n);
  // sn.AffecterNote(n);
    PreparedStatement pre=DBConnect.getConnection().prepareStatement("INSERT INTO note ( `note_orale`, `moyenne`, `note_ecrit`, `apprenant`, `matiere` ) VALUES ('" +torale+"','"+tmoyenne+"','"+tecrit+"',"+ValueApprenant.getId()+","+ValueMatiere.getId()+")");
     pre.executeUpdate();
           System.out.println("aaa");
        } catch (Exception ex) {
           ex.printStackTrace();
//
      }
        JOptionPane.showMessageDialog(null, "ajout effectuer");
        this.reloadMatiere();
       
    }
    }
    //getApprenantByClasse

    @FXML
    private void retourmenu(ActionEvent event) throws IOException {
          FXMLLoader fxml=new FXMLLoader(getClass().getResource("/GUI/menugestion.fxml"));
      Parent root=fxml.load();
       textID.getScene().setRoot(root);
      //  MenugestionController InterNote=fxml.getController();
        
       
    }
    
     @FXML
    private void getApprenantByClasse(ActionEvent event) throws SQLException {
        
       Clase classe=this.boxClasse.getSelectionModel().getSelectedItem();
       if(classe!= null){
          BoxApp.getItems().clear();

          this.ListApprenant= FXCollections.observableArrayList(su.getByClasse(classe));
           System.out.println(ListApprenant);
         BoxApp.setItems(ListApprenant);
         System.out.println("hjhhhhhhhhhhh");
       }
       
    }
    
    
      public void initInterface2(String message){
         try {
            
               
  } catch (Exception e) {
        }
  }

    @FXML
    private void SelectionNote(ActionEvent event) {
         Note n=(Note)TableDesNotes.getSelectionModel().getSelectedItem();
         System.out.println(n);

        double idd =n.getNote_ecrit();
        String noteEcrit=String.valueOf(idd);
        double noteO=n.getNote_orale();
        String noteOrale=String.valueOf(noteO);
        double moy=n.getMoyenne();
        String moyenne=String.valueOf(moy);
        int i=n.getId();
        String id=String.valueOf(i);
        
        textecrit.setText(noteEcrit);
        textorale.setText(noteOrale);
        textmoyenne.setText(moyenne);
        textID.setText(id);
        
    }

    @FXML
    private void Update(ActionEvent event) throws SQLException {
         ServiceNote sn=new ServiceNote();
        String a=textecrit.getText();
        double ecrit=Double.parseDouble(a);
        String b=textorale.getText();
        double orale=Double.parseDouble(b);
        String c=textmoyenne.getText();
        double moyenne=Double.parseDouble(c);     
        String d=textID.getText();
        int id=Integer.parseInt(d);     



        
        Note n1=new Note(id, orale, ecrit, moyenne);
        sn.UpdateNote(n1);
        this.reloadMatiere();
    }

    @FXML
    private void Supprission(ActionEvent event) {
        ServiceNote sn=new ServiceNote();
        Note n = (Note)TableDesNotes.getSelectionModel().getSelectedItem();
        System.out.println(n);
        if(n== null){
        
        JOptionPane.showMessageDialog(null, "choisir Note");
        }else{
            try {
                sn.DeleteNote(n.getId());
               // JOptionPane.showMessageDialog(null, "");
                this.reloadMatiere();
                
                
            } catch (Exception e) {
            }
            
            try {
            } catch (Exception e) {
            }
        }
        
    }
    
      

    @FXML
    private void userChanged(ActionEvent event) {
        reloadMatiere();
        

    }
    
    public void reloadMatiere(){
    BoxApp.getSelectionModel().getSelectedItem();
        try {
        this.ListNote = FXCollections.observableArrayList(ServiceNote.findByUser(BoxApp.getSelectionModel().getSelectedItem()));
         CMatiere.setCellValueFactory(new PropertyValueFactory<>("m"));
            CNoteEcrit.setCellValueFactory(new PropertyValueFactory<>("note_ecrit"));
            CNoteOrale.setCellValueFactory(new PropertyValueFactory<>("note_orale"));
             CMoyenne.setCellValueFactory(new PropertyValueFactory<>("moyenne"));
             cID.setCellValueFactory(new PropertyValueFactory<>("id"));
//            CApprenant.setCellValueFactory(new PropertyValueFactory<>("u"));

        TableDesNotes.setItems(ListNote);
        } catch (SQLException ex) {
        }
    
    }

    @FXML
    private double moyenne(MouseEvent event) {
//        DecimalFormat df=new DecimalFormat("0.00");
         double tE=Double.parseDouble(textecrit.getText());
      double tO=Double.parseDouble(textorale.getText()); 
      
      String v=String.valueOf((tO+tE*2)/3);
//      String v1=String.valueOf(df.format(v));
      textmoyenne.setText(v);
      
            
      double moy=Double.parseDouble(textmoyenne.getText());

      
      return moy;
        
    }
}
