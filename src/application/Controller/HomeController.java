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


import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import application.Entity.User;
import java.sql.Date;
import java.time.LocalDateTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class HomeController implements Initializable {
	public User user = new User();
	Alert alert = new Alert(null);
	
	@FXML
	private TextField un;
	@FXML
	private PasswordField mp;


     
	@FXML
	private void login(ActionEvent event) {
	
		check();
		if(user.getUsername_canonical().equals(un.getText())&& user.getUsername_canonical()!="" && mp.getText().equals(user.getPassword())&&user.getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {
			
			try {
				
				FXMLLoader uiloader1 = new FXMLLoader(getClass().getResource("../UI/EspaceAdministration.fxml"));
				Parent frame= (Parent) uiloader1.load();
				Stage scene= new Stage();
				scene.setScene(new Scene(frame));
				
			
				scene.setTitle(String.valueOf(user.getId()));
                                System.out.println(user.getId());
			
				scene.show();
				((Node) event.getSource()).getScene().getWindow().hide();

			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(user.getUsername_canonical().equals(un.getText())&& user.getUsername_canonical()!="" && mp.getText().equals(user.getPassword())&&user.getRoles().equals("a:1:{i:0;s:13:\"ROLE_EMPLOYER\";}")) {
			try {
				FXMLLoader uiloader1 = new FXMLLoader(getClass().getResource("../UI/EspaceEmployer.fxml"));
				Parent frame= (Parent) uiloader1.load();
				Stage scene= new Stage();
				scene.setScene(new Scene(frame));
				scene.setTitle(String.valueOf(user.getId()));
				scene.show();
				((Node) event.getSource()).getScene().getWindow().hide();

			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}else if(user.getUsername_canonical().equals(un.getText())&& user.getUsername_canonical()!="" && mp.getText().equals(user.getPassword())&&user.getRoles().equals("a:1:{i:0;s:11:\"ROLE_PARENT\";}")) {
			try {
				FXMLLoader uiloader1 = new FXMLLoader(getClass().getResource("../UI/EspaceParent.fxml"));
				
				Parent frame= (Parent) uiloader1.load();
				
				
				 EspaceParentController secController=uiloader1.getController();
				 secController.u.setId(user.getId());
			

				 Stage scene= new Stage();
				scene.setScene(new Scene(frame));
				scene.setTitle(String.valueOf(secController.u.getId()));
			
				scene.show();
				((Node) event.getSource()).getScene().getWindow().hide();

			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(user.getUsername_canonical().equals(un.getText())&& user.getUsername_canonical()!="" && mp.getText().equals(user.getPassword())&&user.getRoles().equals("a:1:{i:0;s:14:\"ROLE_APPRENANT\";}")) {
			try {
				FXMLLoader uiloader1 = new FXMLLoader(getClass().getResource("../UI/EspaceApprennent.fxml"));
				Parent frame= (Parent) uiloader1.load();
				Stage scene= new Stage();
				scene.setScene(new Scene(frame));
				scene.setTitle(user.getUsername()+" "+user.getCin());
				scene.setTitle(String.valueOf(user.getId()));
				scene.show();
				((Node) event.getSource()).getScene().getWindow().hide();

			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(user.getUsername_canonical().equals(un.getText())&& user.getUsername_canonical()!="" && mp.getText().equals(user.getPassword())&&user.getRoles().equals("a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}")) {
			try {
				FXMLLoader uiloader1 = new FXMLLoader(getClass().getResource("../UI/EspaceEnseignant.fxml"));
				Parent frame= (Parent) uiloader1.load();
				Stage scene= new Stage();
				scene.setScene(new Scene(frame));
				scene.setTitle(String.valueOf(user.getId()));
				scene.show();
				((Node) event.getSource()).getScene().getWindow().hide();

			} catch(Exception e) {
				e.printStackTrace();
			}

				
		}else {
			alert.setAlertType(AlertType.WARNING);
			alert.setHeaderText(null);
			alert.setTitle("Warning");
			alert.setContentText("Nom d'utulisateur ou mot de passe incorrect");
			alert.showAndWait();
		}
	}
	
	
	public void check() {

		ConnectionEcole pc=new ConnectionEcole();
		LinkedList<User> al= pc.affichier("select * from user");
           
  
		for(User us: al) {
			if(us.getUsername_canonical().equals(un.getText()) && mp.getText().equals(us.getPassword())) {
                            user=us;
				break;
			} else {
				user.setUsername_canonical("");
				user.setPassword("");
				user.setRoles("");
                                user.setLast_login(LocalDateTime.MIN);
			}
	}
		
		
	}
	
	// Event Listener on Button.onAction
	@FXML
	private void inscrit(ActionEvent event) {
		 try {
				FXMLLoader uiloader = new FXMLLoader(getClass().getResource("../UI/Inscrire.FXML"));
				Parent frame= (Parent) uiloader.load();	
				Stage scene= new Stage();
				scene.setScene(new Scene(frame));
				scene.show();
			    }		
				 catch(Exception e) {
					e.printStackTrace();
				}
		
	}
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}

