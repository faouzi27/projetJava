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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import application.Entity.User;
public class InscrireController  extends ConnectionEcole {
	Alert alert = new Alert(null);
	User u = new User();
	User a = new User();
	int n,o;
	@FXML
	private TextField username;
	@FXML
	private TextField usernamee;
	@FXML
	private TextField prenom;
	@FXML
	private TextField prenoma;
	@FXML
	private TextField cin;
	@FXML
	private TextField tel;
	@FXML
	private TextField email;
	@FXML
	private TextField emaila;
        @FXML
	private TextField cinapp;
	@FXML
	private TextField login;
	@FXML
	private TextArea loisir;
	@FXML
	private TextField logina;
        @FXML
	private TextField telapp;
	@FXML
	private TextField password;
        @FXML
	private TextField passapp;
        
        
        
	@FXML 
	private RadioButton m;
	@FXML 
	private RadioButton ma;
	@FXML 
	private RadioButton f;
	@FXML 
	private RadioButton fa;
	@FXML 
	private DatePicker dt;
	@FXML 
	private DatePicker dta;
	@FXML
	private TextField nbr;
	@FXML
	private TextField niveau;
	
	@FXML
	private Label parentlabel;
	
	@FXML
	
	private void sub(ActionEvent event) {
		n=Integer.parseInt(nbr.getText());
		u.setUsername(username.getText());
		u.setPrenom(prenom.getText());
		u.setCin(cin.getText());
		u.setTel(tel.getText());
		u.setEmail(email.getText());
		u.setPassword(password.getText());
                u.setUsername_canonical(username.getText());
                u.setEmail_canonical(email.getText());
                u.setEnabled(true);
		
                if(m.isSelected()) {
			u.setSexe("Male");
		}else if(f.isSelected()) {
			u.setSexe("Female");
		}
		u.setDtn(dt.getValue());
	u.setRoles("a:1:{i:0;s:11:\"ROLE_PARENT\";}");
		
	ConnectionEcole cn=new ConnectionEcole();
	String sql="INSERT INTO user (username, prenom, cin, sexe, tel, email, password, roles,date_naissance,username_canonical,email_canonical,enabled) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	cn.ajouterpa(sql, u);
		
		((Node) event.getSource()).getScene().getWindow().hide();
		
		
	for(int i=0;i<n;i++) {
		try {
			FXMLLoader uiloader1 = new FXMLLoader(getClass().getResource("../UI/InscrireApprennent.FXML"));
			Parent frame= (Parent) uiloader1.load();
		
			
		Stage scene= new Stage();
		scene.setScene(new Scene(frame));
		
		String idparent=String.valueOf(cn.getparent("select * from user", u.getUsername(), u.getCin())); 
		
		scene.setTitle(idparent);
				scene.show();
				
			
					
					
					
					
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	}
				
	
	
	@FXML
	private void ajouter(ActionEvent event) {
		
		 Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		 parentlabel.setText(stage.getTitle().toString());
    
		
		o=Integer.parseInt(niveau.getText());
		a.setNiveau(o);
		a.setUsername(usernamee.getText());
		a.setPrenom(prenoma.getText());
                a.setCin(cinapp.getText()); 
                a.setPassword(passapp.getText());
		a.setTel(telapp.getText());
                a.setDtn(dta.getValue());
		a.setEmail(emaila.getText());
		a.setLoisir(loisir.getText());
                a.setUsername_canonical(usernamee.getText());
                a.setEmail_canonical(emaila.getText());
                a.setEnabled(true);
                
		if(ma.isSelected()) {
			a.setSexe("Male");
		}else if(fa.isSelected()) {
			a.setSexe("Female");
		}
		a.setParent(Integer.parseInt(stage.getTitle()));
		a.setRoles("a:1:{i:0;s:14:\"ROLE_APPRENANT\";}");
		ConnectionEcole cn=new ConnectionEcole();
		String sql="INSERT INTO user (username, prenom, cin, sexe, tel, email, roles,date_naissance,niveau, loisir, parent,username_canonical,email_canonical,enabled,password) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		 
		
		cn.ajouterappre(sql, a);
		
		
		((Node) event.getSource()).getScene().getWindow().hide();
	}
	
}

























