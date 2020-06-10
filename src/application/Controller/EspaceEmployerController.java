/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Controller;

/**
 *
 * @author acer
 */

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import application.Entity.User;
import application.Entity.cour;
import application.Entity.fichier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import java.awt.image.DataBufferUShort;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
public class EspaceEmployerController implements Initializable{
	ConnectionEcole pc=new ConnectionEcole();

	private Connection conn;
	  private ObservableList<ObservableList> data;
	@FXML
	private BorderPane bp1;
	@FXML
	private Button Classebu;
	@FXML
	private Button Absencebu;
	@FXML
	private Button Apprenantbu1;
	
	@FXML
	private TextField UsEmail;
	@FXML
	private PasswordField PassEmail;
	@FXML
	private TextField SujetTX;
	@FXML
	private TextArea EmailCont;
	@FXML
	private Button Env;
	@FXML
	private Button Matierebu;
	@FXML
	private Button Garderiebu;
	@FXML
	private Button Eventbu;
	@FXML
	private Button Notebu;
	private String idm;
	private String matier;
	private String coef;
	
	


	// Event Listener on TableView[#TablMatiere].onMouseClicked
	@FXML
	public void TablMatiere(MouseEvent event) {
		ObservableList rowList = (ObservableList) TablMatiere.getItems().get(TablMatiere.getSelectionModel().getSelectedIndex());
		 idm = rowList.get(0).toString();
		 matier = rowList.get(1).toString();
		 coef = rowList.get(2).toString();
		 nom_matiere.setText(matier);
		 coefficient.setText(coef);
	}
	

	// Event Listener on TableView[#tableappPS].onMouseClicked
	@FXML
	public void tableappPS(MouseEvent event) {
		ObservableList rowList = (ObservableList) TablMatiere.getItems().get(TablMatiere.getSelectionModel().getSelectedIndex());
		 idm = rowList.get(0).toString();
	
	}
	 public User u=new User();
	
	
	 
	// Event Listener on BorderPane[#bp1].onMouseEntered
	@FXML
	public void getidfromtitle(MouseEvent event) {

		 Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		
		
		 u=pc.user(Integer.parseInt(stage.getTitle().toString()));
		 labelEmp.setText(u.getUsername()+"  "+u.getPrenom());
		 
		 
	}
	
	
	@FXML
	private AnchorPane ap1;
	@FXML
	private TableView <ObservableList> Tablclasse;
	@FXML
	private TableView <ObservableList> TablMatiere;
	@FXML
	private TableView <ObservableList> tableappPS;
	@FXML
	private Button ModifierClasse;
	@FXML
	private TextField niveaut;
	@FXML
	private TextField Numt;
	@FXML
	private TextField Typet;
	@FXML
	private Button Ajouterclass;
	@FXML
	private Button suppclass;

	@FXML
	private Button Deconnection;
	
	@FXML
	private Label labelEmp;

	// Event Listener on Button[#Deconnection].onAction
	@FXML
	public void Deconnection(ActionEvent event) {
		((Node) event.getSource()).getScene().getWindow().hide();
		BorderPane root;
		try {
			root = (BorderPane)FXMLLoader.load(getClass().getResource("../UI/Home.fxml"));
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	@FXML
	private TextField nom_matiere;
	@FXML
	private TextField coefficient;
	
	
	@FXML
	private Button ModifierMatiere;
	@FXML
	private Button suppMatiere;
	@FXML
	private Button AjouterMatier;

	// Event Listener on Button[#ModifierMatiere].onAction
	@FXML
	public void ModifierMatiere(ActionEvent event) {
		ObservableList rowList = (ObservableList) TablMatiere.getItems().get(TablMatiere.getSelectionModel().getSelectedIndex());
		 idm = rowList.get(0).toString();
		
		 	String nommt=nom_matiere.getText();
			String coef=coefficient.getText();	
			int coef1=Integer.parseInt(coef);
	
		String sql="UPDATE matiere SET nom_matiere='"+nommt+"', coefficient="+coef1+" where id="+idm+" ";
		pc.modifmat(sql);
		TablMatiere.getColumns().clear();

		buildData(TablMatiere, "Matiere");	
		
	}
	// Event Listener on Button[#suppMatiere].onAction
	@FXML
	public void suppMatiere(ActionEvent event) {
		
		ObservableList rowList = (ObservableList) TablMatiere.getItems().get(TablMatiere.getSelectionModel().getSelectedIndex());
		 idm = rowList.get(0).toString();
		
		pc.suppmat(Integer.parseInt(idm));
		TablMatiere.getColumns().clear();

		buildData(TablMatiere, "Matiere");
	}
	// Event Listener on Button[#AjouterMatier].onAction
	@FXML
	public void AjouterMatier(ActionEvent event) {
		String nommt=nom_matiere.getText();
		String coef=coefficient.getText();	
		int coef1=Integer.parseInt(coef);
		String sql="insert into matiere (nom_matiere,coefficient) values (?,?)";
		pc.ajoutermatiere(sql, nommt, coef1);
		
		TablMatiere.getColumns().clear();

		buildData(TablMatiere, "Matiere");
	}

	// Event Listener on Button[#Classebu].onMouseClicked
	@FXML
	public void Classe(MouseEvent event) {
		looad("Classe");
	}
	// Event Listener on Button[#Absencebu].onMouseClicked
	@FXML
	public void Absence(MouseEvent event) {
		looad("Absence");
	}
	// Event Listener on Button[#Matierebu].onMouseClicked
	@FXML
	public void Matiere(MouseEvent event) {
		looad("Matiere");
	}
	
	// Event Listener on Button[#Garderiebu].onMouseClicked
	@FXML
	public void Garderie(MouseEvent event) {
		looad("Garderie");
	}
	// Event Listener on Button[#Eventbu].onMouseClicked
	@FXML
	public void Event(MouseEvent event) {
		looad("Event");
	}
	
	// Event Listener on Button[#Apprenant].onMouseClicked
		@FXML
		public void Apprenant(MouseEvent event) {
			looad("Passapp");
		}
	
		@FXML
		public void Envoyer(ActionEvent event) {
			test();
		}
	
	// Event Listener on Button[#Notebu].onMouseClicked
	@FXML
	public void Note(MouseEvent event) {
		looad("Note");
                
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		String location1=location.toString();
		
		String	ffx=location1.substring(location1.lastIndexOf("/")+1, location1.length());
			
			

			if(ffx.equals("EspaceEmployer.fxml")) {
				
			}
                        else if(ffx.equals("EmployerMatiere.fxml")){
				buildData(TablMatiere,"Matiere");  
			}
                        else if(ffx.equals("EmployerNote.fxml")){
                              
                        }
		
		
		
		
	}
	
	
	
	 
	
	
	
	
 

 
	
	
	public void buildData(TableView<ObservableList> Tab,String ent) {
        Connection c;
        Statement ste;
        data = FXCollections.observableArrayList();
        try {
            ste = DBConnect.getConnection().createStatement();
            
            String SQL = "SELECT * from "+ent;
            
            ResultSet rs = ste.executeQuery(SQL);
 
           
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
				TableColumn col1 = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col1.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {    
                	public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                
                Tab.getColumns().addAll(col1);
            }
 
            
            while (rs.next()) {
                
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                  
                    row.add(rs.getString(i));
                }
                data.add(row);
 
            }
 
            Tab.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

	
	
	
	
	
	
	
	
	 private void looad(String page) {
			Parent root=null;                                       
			try {
                        root=FXMLLoader.load(getClass().getResource("../UI/Employer"+page+".fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			bp1.setCenter(root);
		}
		
	 
	 
	 
	 
	 
	 public void test(){
		   final String username = "edsmart1997@gmail.com";
		   final String password = " ";
	
	        Properties prop = new Properties();
			prop.put("mail.smtp.host", "smtp.gmail.com");
	        prop.put("mail.smtp.port", "587");
	        prop.put("mail.smtp.auth", "true");
	        prop.put("mail.smtp.starttls.enable", "true"); //TLS
	        
	        Session session = Session.getInstance(prop,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(username, password);
	                    }
	                });

	        try {

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("edsmart1997@gmail.com"));
	            message.setRecipients(
	                    Message.RecipientType.TO,
	                    InternetAddress.parse(UsEmail.getText())
	            );
	            message.setSubject(SujetTX.getText());
	            message.setText(EmailCont.getText());

	            Transport.send(message);

	            System.out.println("Done");

	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 
	
	
		}



