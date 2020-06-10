/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Controller;

import application.Controller.DBConnect;
import application.Entity.Clase;
import application.Entity.Matiere;
import application.Entity.User;
import application.Controller.ServiceClasse;
import application.Controller.ServiceUser;
import application.Entity.Affecter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.SortEvent;
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
public class InterfaceClasseController implements Initializable {

    private Connection con;
    private Statement ste;
    private PreparedStatement pre;
    private ResultSet rs;
    
    @FXML
    private ComboBox<Integer> BoxNiveau;
    @FXML
    private ComboBox<String> BoxType;
    private TableView<User> ListNonAffecter;

    
    
    /**
     * Initializes the controller class.
     */
     ServiceClasse sc=new ServiceClasse();
     ServiceUser su=new ServiceUser();
   
     ObservableList<String> listtype;
    ObservableList<Integer> listniveau;
    
    ObservableList<User> listeUsers;
    ObservableList<Clase> ListeClass;
    
     
   
     
     
    @FXML
    private Button btnSupprimerClasse;
    @FXML
    private Button btnModifierClasse;
    @FXML
    private Button btnAffecterApprenant;
    @FXML
    private Button btnAjouterClasse;
    @FXML
    private TableView<Clase>ListeClasse;
    
    @FXML
    private TableView<User>tableViewUsers;
    //ListeClasse1
    
    @FXML
    private TableColumn<Clase, Integer> CNiveau;
    @FXML
    private TableColumn<Clase, String> CType;
    @FXML
    private TableColumn<Clase, Integer> CId;
     @FXML
    private TableColumn<User, String> cNom;
    @FXML
    private TableColumn<User, String> cPrenom;
    @FXML
    private TableColumn<User, Date> cDateNaissance;
    ObservableList<Clase>ob=FXCollections.observableArrayList();
    ObservableList<User>apprenants=FXCollections.observableArrayList();
   
    @FXML
    private TextField TextType;
    @FXML
    private TextField TextNiveau;
    @FXML
    private TextField TextId;
    @FXML
    private Button btnselection;
    @FXML
    private Button rm;

    
    public InterfaceClasseController() throws SQLException {
        this.listtype = FXCollections.observableArrayList("A","B","C","D");
        this.listniveau = FXCollections.observableArrayList(1,2,3,4,5,6);
       // this.ListClassNonAff=FXCollections.observableArrayList(su.ApprenantNonAffecter());
       // this.ListeClass=FXCollections.observableArrayList(sc.ClassList());


       // con = DataBase.getInstance().getConnection();

    }
  
    
    
   
    
    
        

    @FXML
    private void SupprimerClasse(ActionEvent event) throws SQLException {
    ServiceClasse sc=new ServiceClasse();
        Clase c = (Clase)ListeClasse.getSelectionModel().getSelectedItem();
        System.out.println(c);
        if(c== null){
        
        JOptionPane.showMessageDialog(null, "choisir classe");
        }else{
            try {
                sc.DeleteClasse(c.getId());
               // JOptionPane.showMessageDialog(null, "");
                this.initInterface("Supprission effectuer");              
        //   this.initInterface("");
                
                
            } catch (Exception e) {
            }
            
            try {
            } catch (Exception e) {
            }
        }
    
    }

    @FXML
    private void ModifierClasse(ActionEvent event) throws SQLException {
        ServiceClasse sc=new ServiceClasse();
        String a=TextId.getText();
        int id=Integer.parseInt(a);
        String b=TextNiveau.getText();
        int niveau=Integer.parseInt(b);

        String c=TextType.getText();
        Clase c1=new application.Entity.Clase(id, niveau, c);
        sc.UpdateClasse(c1);
        this.initInterface("Classe Modifier Avec Succee");
     
       
    }

    @FXML
    private void AffecterClasse(ActionEvent event) throws SQLException {
        
//         Matiere value=boxmatiere.getSelectionModel().getSelectedItem();  
          // String ValueApprenant=BoxType.getItems().get(BoxType.getSelectionModel().getSelectedIndex());
           User user = (User)tableViewUsers.getSelectionModel().getSelectedItem();
           Clase classe = (Clase)ListeClasse.getSelectionModel().getSelectedItem();
           Affecter aff=new Affecter();
           ste=DBConnect.getConnection().createStatement();
           int a=classe.getId();
           int b=user.getId();
           System.out.println("dsssssssssssssssssssssssssssss"+a);
           System.out.println("dsssssssssssssssssssssssssssss"+b);
           String query = ("INSERT INTO `affecter`(`classe`, `user`) VALUES ( "
              +a+
              ","+b+")");
         System.out.println("3asbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
         ste.executeUpdate(query);
         

//      String req = "UPDATE user SET classe ='"+classe.getId()+"'WHERE id='"+user.getId()+"'";
//      ste.executeUpdate(req);
         int s=classe.getNiveau();
         String niveau=String.valueOf(s);
         String req = "UPDATE user SET classe ='"+classe.getId()+"' WHERE id="+user.getId()+"";
      ste.executeUpdate(req);
       
       String req1="UPDATE user SET affecter ="+1+" WHERE id="+user.getId()+"";
       ste.executeUpdate(req1);
        
//      String query = ("INSERT INTO `affecter`(`classe`, `user`) VALUES ( "
//              +aff.getCl().getId()+
//              ","+aff.getUs().getId()+")");
//    
//         ste.executeUpdate(query);
       
       // System.out.println("dddddddddddddddddd"+aff.getCl().getId());
     
       this.initInterface2("Affectation bien");

       
    }

    

    @FXML
    private void AjouterClasse(ActionEvent event) throws SQLException, IOException {
        
        ServiceClasse sc=new ServiceClasse();
        
//       String valueType=BoxType.getItems().get(BoxType.getSelectionModel().getSelectedIndex());
//       Integer valueNiveau=BoxNiveau.getItems().get(BoxNiveau.getSelectionModel().getSelectedIndex()); 7
         String value=BoxType.getSelectionModel().getSelectedItem();
         Integer value1=BoxNiveau.getSelectionModel().getSelectedItem();

       
        sc.AjouterClasse(new Clase(value1, value));
      
       // JOptionPane.showMessageDialog(null, "");
        this.initInterface("Ajout effectué");
    
    }
    

    
    
    private void ListeNonAffe(SortEvent<User> event) {
//    CNom.setCellValueFactory(new PropertyValueFactory<User,String>("nom"));
//    CPrenom.setCellValueFactory(new PropertyValueFactory<User,String>("prenom"));
//    CDateNaiss.setCellValueFactory(new PropertyValueFactory<User,Date>("date_naissance"));

    }


    @FXML
    private void SelectioncClasse(ActionEvent event) {
   
        Clase c=(Clase)ListeClasse.getSelectionModel().getSelectedItem();
         System.out.println(c);

        int id =c.getId();
        String idd=String.valueOf(id);
        int niveau=c.getNiveau();
        String niv=String.valueOf(niveau);
        String type=c.getType();
        TextId.setText(idd);
        TextNiveau.setText(niv);
        TextType.setText(type);
    
    }


 @Override
    public void initialize(URL url, ResourceBundle rb) {
     
     this.initInterface("");
     this.initInterface2("");

        }

    
    private void initInterface(String message){
       try {
            BoxType.setItems(listtype);
            BoxNiveau.setItems(listniveau);
            ListeClasse.setItems(ListeClass);
            ob.clear();
            ResultSet rs=DBConnect.getConnection().createStatement().executeQuery("select * from classe");
            while (rs.next()){
                ob.add(new Clase(rs.getInt("id"),rs.getInt("niveau"), rs.getString("type")));
            }
            CId.setCellValueFactory(new PropertyValueFactory<>("id"));
            CNiveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));
            CType.setCellValueFactory(new PropertyValueFactory<>("type"));
            ListeClasse.setItems(ob);
             if(!message.equals("")){
                  JOptionPane.showMessageDialog(null, message);
            }
        } catch (SQLException ex) {
        }}

  
    public void initInterface2(String message)
    
  {
         try {
            
                  apprenants.clear();

            ResultSet rsApprenant=DBConnect.getConnection().createStatement().executeQuery("select * from user where roles= 'a:1:{i:0;s:14:\"ROLE_APPRENANT\";}' and classe is null");
            while (rsApprenant.next()){
                User apprenant = new User();
                apprenant.setId(rsApprenant.getInt("id"));
                apprenant.setUsername(rsApprenant.getString("username"));
                apprenant.setPrenom(rsApprenant.getString("prenom"));
               // apprenant.setDtn(rsApprenant.getDate("date_naissance"));
                System.out.println(apprenant.getDtn());
                apprenants.add(apprenant);
               // ob.add(new Classe(rs.getInt("id"),rs.getInt("niveau"), rs.getString("type")));
            }
          
            
            cNom.setCellValueFactory(new PropertyValueFactory<>("username"));
            cPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            cDateNaissance.setCellValueFactory(new PropertyValueFactory<>("dtn"));
            tableViewUsers.setItems(apprenants);
            if(!message.equals("")){
                  JOptionPane.showMessageDialog(null, message);
            }
  } catch (Exception e) {
        }
  }

    @FXML
    private void retourmenu(ActionEvent event) throws IOException {
          FXMLLoader fxml=new FXMLLoader(getClass().getResource("/GUI/menugestion.fxml"));
      Parent root=fxml.load();
       TextNiveau.getScene().setRoot(root);
       // MenugestionController InterNote=fxml.getController();
        
    }

}


