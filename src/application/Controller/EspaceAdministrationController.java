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


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.ResourceBundle;

import application.Entity.User;
import application.Entity.cour;
import application.Entity.fichier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
public class EspaceAdministrationController implements Initializable {
	@FXML
	private Button parentbu;
	@FXML
	private Button emploiyerbu;
	
	ConnectionEcole pc=new ConnectionEcole();
	
	public ObservableList optionsCombo1=  pc.selectbox();

	public ObservableList optionsCombo4= pc.selectbox1();
	public ObservableList optionsCombo2=pc.selectclass();
	public ObservableList optionsCombo3=pc.selectmatbox();
	
	@FXML
	private BorderPane bp;
	
	@FXML
	private VBox vb;
	
	@FXML
	private ComboBox app_parentCom;
	@FXML
	private ComboBox classeCom;
	@FXML
	private Label welcAdmin;
	
	
	@FXML
	private TextField usernameap;
	@FXML
	private TextField passap;
	@FXML
	private TextField emailap;
	@FXML
	private TextField telap;
	@FXML
	private TableView<ObservableList> tablev1;
	@FXML
	private Button modifierad;
	@FXML
	private Button supprimerad;
	Map<String, Object> fxmlNamespace;
	private String id;
	private String prenom;
	
	
	@FXML
	
	private String matie;
	@FXML
	private ComboBox ens_emp;
	@FXML
	private ComboBox MatiereCom;
	@FXML
	private TextField usernametx;
	@FXML
	private TextField prenomtx;
	
	@FXML
	private TextField passwordx;
        
        @FXML
	private DatePicker dt;
        
	@FXML
	private TextField emailtx;
	@FXML
	private TextField cinx;
	@FXML
	private TextField telx;
	@FXML
	private TextField matierx;
	@FXML
	private Button ajouterui;
	@FXML
	private Button modifierui;
	@FXML
	private Button supprimerbui;
	@FXML
	private TableView tablex1;
	@FXML
	private RadioButton sexehx;
	@FXML
	private RadioButton sexefx;

	// Event Listener on RadioButton[#sexehx].onAction
	@FXML
	public void h_h(ActionEvent event) {
		sexehx.setSelected(true);
		sexefx.setSelected(false);
	}
	// Event Listener on RadioButton[#sexefx].onAction
	@FXML
	public void f_f(ActionEvent event) {
		sexehx.setSelected(false);
		sexefx.setSelected(true);
	}
	
	// Event Listener on VBox.onMouseEntered
	@FXML
	public void filcom(MouseEvent event) {
		ens_emp.setItems(optionsCombo4);
		MatiereCom.setItems(optionsCombo3);
	}
	
	// Event Listener on ComboBox[#ens_emp].onAction
	@FXML
	public void ens_emp(ActionEvent event) {

		if(!ens_emp.getValue().toString().equals("a:1:{i:0;s:13:\"ROLE_EMPLOYER\";}") || ens_emp.getValue().equals(null)) {
			
			if(ens_emp.getValue().equals("a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}")){
				String sql="SELECT id,password,email,tel,username,prenom,cin,sexe,matiere FROM user WHERE roles='a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}'";
				
				try {
					tablex1.getColumns().clear();
				} catch (Exception e) {
					// TODO: handle exception
				}
				

				buildData(tablex1, sql);
			}else {
				System.out.println(ens_emp.getSelectionModel().toString());
				MatiereCom.setDisable(false);
				MatiereCom.setItems(optionsCombo3);
			}
		}else {
			matierx.setDisable(true);
			MatiereCom.setDisable(true);
		}	
		
		if(!ens_emp.getValue().equals(null) ) {
			if(ens_emp.getValue().equals("a:1:{i:0;s:13:\"ROLE_EMPLOYER\";}") && MatiereCom.isDisabled()) {
				String sql="SELECT id,password,email,tel,username,prenom,cin,sexe FROM user WHERE roles='a:1:{i:0;s:13:\"ROLE_EMPLOYER\";}'";
				matierx.setDisable(true);
				try {
					tablex1.getColumns().clear();
				} catch (Exception e) {
					// TODO: handle exception
				}
				

				buildData(tablex1, sql);
			
			}
		}
		
	}
	
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
		

		User u=new User();
	
		
				// Event Listener on Button[#idfromtitle].onMouseEntered
				@FXML
				public void idfromtitle(MouseEvent event) {
					Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
					
					 
					 u=pc.user(Integer.parseInt(stage.getTitle().toString()));
					
					
					String id=String.valueOf(u.getId());
					System.out.println(id);
					welcAdmin.setText(u.getUsername()+" "+u.getPrenom());
				}
		
	
	// Event Listener on ComboBox[#MatiereCom].onAction
	@FXML
	public void MatiereCom(ActionEvent event) {
		
		matierx.setDisable(false);
		
		matierx.setText(String.valueOf(pc.getmatiere(MatiereCom.getValue().toString())));
		
	}
	// Event Listener on TableView[#tablex].onMouseClicked
	@FXML
	public void tablex(MouseEvent event) {
		ObservableList rowList = (ObservableList) tablex1.getItems().get(tablex1.getSelectionModel().getSelectedIndex());
		
		String password = rowList.get(2).toString();
		String email = rowList.get(3).toString();
		String tel = rowList.get(4).toString();
		String username = rowList.get(5).toString();
		String prenom = rowList.get(6).toString();
		String cin = rowList.get(7).toString();
		String sexe = rowList.get(8).toString();

		usernametx.setText(username);
		passwordx.setText(password);
		
		emailtx.setText(email);
		telx.setText(tel);
		prenomtx.setText(prenom);
		cinx.setText(cin);
                
		
		
		
		try {
			try {
				String sex1 = rowList.get(8).toString();
				if(sex1.equals("Male")) {
					sexehx.setSelected(true);
					sexefx.setSelected(false);

				}else {
					sexehx.setSelected(false);
					sexefx.setSelected(true);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			if(!MatiereCom.isDisabled()) {
				String matiere = rowList.get(9).toString();
				matierx.setDisable(false);
				matierx.setText(matiere);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	// Event Listener on Button[#ajouterui].onAction
	@FXML
	public void ajouterui(ActionEvent event) {

		
		String username=usernametx.getText();
		String pass=passwordx.getText();
		String mail=emailtx.getText();
		String tel=telx.getText();
		String prnom=prenomtx.getText();
		String cin=cinx.getText();
		
		
	
	String sexe;
		if(	sexehx.isSelected()) {
		sexe="Male";
	}else {
		sexe="Female";
	}
		if(matierx.getText().equals("") || matierx.isDisabled()) {
			String sql="INSERT INTO user( username, prenom, cin, sexe, tel, email, password, roles,username_canonical,email_canonical,enabled,date_naissance) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		User u=new User();
		u.setUsername(username);
		u.setPrenom(prnom);
		u.setCin(cin);
		u.setEmail(mail);
		u.setTel(tel);
		u.setSexe(sexe);
		u.setPassword(pass);
                u.setDtn(dt.getValue());
		u.setRoles("a:1:{i:0;s:13:\"ROLE_EMPLOYER\";}");
                u.setUsername_canonical(username);
                u.setEmail_canonical(mail);
                u.setEnabled(true);
		
			pc.ajouteen(sql, u);
			tablex1.getColumns().clear();
			String sqltab="SELECT id,password,email,tel,username,prenom,cin,sexe FROM user WHERE roles='a:1:{i:0;s:13:\"ROLE_EMPLOYER\";}'";

			buildData(tablex1, sqltab);
		}else  {
			String sql="INSERT INTO user( username, prenom, cin, sexe, tel, email, password, roles,matiere,username_canonical,email_canonical,enabled,date_naissance) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			User u=new User();
			
			
			u.setUsername(username);
			u.setPrenom(prnom);
			u.setCin(cin);
			u.setMatiere(Integer.parseInt(matierx.getText().toString()));
			u.setEmail(mail);
			u.setTel(tel);
			u.setSexe(sexe);
			u.setPassword(pass);
                        u.setDtn(dt.getValue());
			u.setRoles("a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}");
                        u.setUsername_canonical(username);
			u.setEmail_canonical(mail);
			u.setEnabled(true);
                        
			pc.ajouteen(sql, u);
			tablex1.getColumns().clear();
			String sqltab="SELECT id,password,email,tel,username,prenom,cin,sexe,matiere FROM user WHERE roles='a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}'";

			buildData(tablex1, sqltab);
			
		}
		
		
		
		
		
		
	}
	// Event Listener on Button[#supprimerad].onAction
	@FXML
	public void supprimerad(ActionEvent event) {
		// TODO Autogenerated
	}
	

	
	// Event Listener on Button[#modifierui].onAction
	@FXML
	public void modifierui(ActionEvent event) {
		ObservableList rowList = (ObservableList) tablex1.getItems().get(tablex1.getSelectionModel().getSelectedIndex());
		String id = rowList.get(0).toString();
		String username=usernametx.getText();
		String pass=passwordx.getText();
		String mail=emailtx.getText();
		String tel=telx.getText();
		String prnom=prenomtx.getText();
		String cin=cinx.getText();
		String sql="UPDATE user SET username='"+username+"', password='"+pass+"',prenom='"+prnom+"',cin='"+cin+"',email='"+mail+"',tel="+tel+",roles='"+ens_emp.getValue().toString()+",date_naissance='"+dt.getValue()+"' where id="+id+" ";
	
		pc.modifmat(sql);
		tablex1.getColumns().clear();

		if(!ens_emp.getValue().toString().equals("a:1:{i:0;s:13:\"ROLE_EMPLOYER\";}") || ens_emp.getValue().equals(null)) {
			
			if(ens_emp.getValue().equals("a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}")){
				String sql1="SELECT id,password,email,tel,username,prenom,cin,sexe,matiere FROM user WHERE roles='a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}'";
				
				try {
					tablex1.getColumns().clear();
				} catch (Exception e) {
					// TODO: handle exception
				}
				

				buildData(tablex1, sql1);
			}else {
				System.out.println(ens_emp.getSelectionModel().toString());
				MatiereCom.setDisable(false);
				MatiereCom.setItems(optionsCombo3);
			}
		}else {
			matierx.setDisable(true);
			MatiereCom.setDisable(true);
		}	
		
		if(!ens_emp.getValue().equals(null) ) {
			if(ens_emp.getValue().equals("a:1:{i:0;s:13:\"ROLE_EMPLOYER\";}") && MatiereCom.isDisabled()) {
				String sql1="SELECT id,password,email,tel,username,prenom,cin,sexe FROM user WHERE roles='a:1:{i:0;s:13:\"ROLE_EMPLOYER\";}'";
				matierx.setDisable(true);
				try {
					tablex1.getColumns().clear();
				} catch (Exception e) {
					// TODO: handle exception
				}
				

				buildData(tablex1, sql1);
			
			}
		}
		
	
	}
	// Event Listener on Button[#supprimerbui].onAction
	@FXML
	public void supprimerbui(ActionEvent event) {
		ObservableList rowList = (ObservableList) tablex1.getItems().get(tablex1.getSelectionModel().getSelectedIndex());
		String id = rowList.get(0).toString();
		pc.suppuser(id);
                tablex1.getColumns().clear();
               	if(!ens_emp.getValue().toString().equals("a:1:{i:0;s:13:\"ROLE_EMPLOYER\";}") || ens_emp.getValue().equals(null)) {
			
			if(ens_emp.getValue().equals("a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}")){
				String sql1="SELECT id,password,email,tel,username,prenom,cin,sexe,matiere FROM user WHERE roles='a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}'";
				
				try {
					tablex1.getColumns().clear();
				} catch (Exception e) {
					// TODO: handle exception
				}
				

				buildData(tablex1, sql1);
			}else {
				System.out.println(ens_emp.getSelectionModel().toString());
				MatiereCom.setDisable(false);
				MatiereCom.setItems(optionsCombo3);
			}
		}else {
			matierx.setDisable(true);
			MatiereCom.setDisable(true);
		}
				
              if(!ens_emp.getValue().equals(null) ) {
			if(ens_emp.getValue().equals("a:1:{i:0;s:13:\"ROLE_EMPLOYER\";}") && MatiereCom.isDisabled()) {
				String sql1="SELECT id,password,email,tel,username,prenom,cin,sexe FROM user WHERE roles='a:1:{i:0;s:13:\"ROLE_EMPLOYER\";}'";
				matierx.setDisable(true);
				try {
					tablex1.getColumns().clear();
				} catch (Exception e) {
					// TODO: handle exception
				}
				

				buildData(tablex1, sql1);
			
			}
		}
                
	}
	// Event Listener on Button[#parentbu].onMouseClicked
	@FXML
	public void parentbu(MouseEvent event) {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		
		 
		 u=pc.user(Integer.parseInt(stage.getTitle().toString()));
		
		
		String id=String.valueOf(u.getId());
		System.out.println(id);
		welcAdmin.setText(u.getUsername()+" "+u.getPrenom());
	bp.setCenter(vb);
	}
	// Event Listener on ComboBox[#app_parentCom].onAction
		@FXML
		public void app_parentCom(ActionEvent event) {
			if(!app_parentCom.getValue().toString().equals("a:1:{i:0;s:11:\"ROLE_PARENT\";}")) {
				System.out.println(app_parentCom.getSelectionModel().toString());
				classeCom.setDisable(false);
				classeCom.setItems(optionsCombo2);
				
			}else {
				classeCom.setDisable(true);
			}	
			
			if(!app_parentCom.getValue().equals(null)) {
				if(app_parentCom.getValue().equals("a:1:{i:0;s:11:\"ROLE_PARENT\";}") && classeCom.isDisabled()) {
					String sql="SELECT password,email,tel,username,prenom FROM user WHERE roles='a:1:{i:0;s:11:\"ROLE_PARENT\";}'";
					tablev1.getColumns().clear();

					buildData(tablev1, sql);

				}
			}
			
			}
		
		// Event Listener on TableView[#tablev1].onMouseClicked
		@FXML
		public void tableevent(MouseEvent event) {
			ObservableList rowList = (ObservableList) tablev1.getItems().get(tablev1.getSelectionModel().getSelectedIndex());
			
			String password = rowList.get(1).toString();
			String email = rowList.get(2).toString();
			String tel = rowList.get(3).toString();
			 String username = rowList.get(4).toString();
			 prenom=rowList.get(5).toString();
			 id=rowList.get(6).toString();
	                 
			passwordx.setText(password);
			emailtx.setText(email);
			telx.setText(tel);
			
		}
	
	// Event Listener on ComboBox[#classeCom].onAction
	@FXML
	public void classeCom(ActionEvent event) {
		String clas=classeCom.getValue().toString();
		System.out.println(clas);
		String ter=String.valueOf(pc.getclasse(clas));
		System.out.println(ter);
		if(!classeCom.getValue().equals(null)) {				
		String sql="SELECT password,email,tel,username,prenom,id FROM user WHERE roles='a:1:{i:0;s:14:\"ROLE_APPRENANT\";}' and affecter=1 and classe='"+clas+"'";
			tablev1.getColumns().clear();
		buildData(tablev1, sql);

		}
						
							
							
						
					
				}
	
	
	// Event Listener on Button[#emploiyerbu].onMouseClicked
	@FXML
	public void emploiyerbu(MouseEvent event) {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		
		 
		 u=pc.user(Integer.parseInt(stage.getTitle().toString()));
		
		
		String id=String.valueOf(u.getId());
		System.out.println(id);
		welcAdmin.setText(u.getUsername()+" "+u.getPrenom());
		looad("Empensei");
	}

	// Event Listener on Button[#supprimerad].onAction
	@FXML
	public void modifierad(ActionEvent event) {
		String username=	usernameap.getText();

		String pass=passap.getText();

		String mail=emailap.getText();

		String tel=telap.getText();
		System.out.println(id);
		int idu= Integer.parseInt(id);
		String clas=classeCom.getValue().toString();
		String sql="UPDATE user SET username='"+username+"', password='"+pass+"', email='"+mail+"', tel="+tel+" where id="+id+" ";
		
			pc.up_ap_parent(idu,sql);
			String sql1="SELECT password,email,tel,username,prenom,id FROM user WHERE roles='a:1:{i:0;s:14:\"ROLE_APPRENANT\";}' and affecter=1 and classe='"+clas+"'";
			tablev1.getColumns().clear();
			buildData(tablev1, sql1);
	
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		String location1=location.toString();
		
		String	ffx=location1.substring(location1.lastIndexOf("/")+1, location1.length());
			
			

			if(ffx.equals("EspaceAdministration.fxml")) {
				app_parentCom.setItems(optionsCombo1);
			}else if(ffx.equals("empenseiAdministration.fxml")){
				ens_emp.setItems(optionsCombo4);
				MatiereCom.setItems(optionsCombo3);
				
			}
		
		
	
	}
	  private ObservableList<ObservableList> data;
	  
	  
	  
	  public void buildData(TableView<ObservableList> tab,String s) {
	        Connection c;
                Statement ste;
	        data = FXCollections.observableArrayList();
	        try {
	            ste = DBConnect.getConnection().createStatement();
	            String SQL = s;
	            ResultSet rs = ste.executeQuery(SQL);
	 
	            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
	                final int j = i;
	                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
	                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
	                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
	                        return new SimpleStringProperty(param.getValue().get(j).toString());
	                    }
	                });
	 
	                tab.getColumns().addAll(col);
	                System.out.println("Column [" + i + "] ");
	            }
	 
	            
	            while (rs.next()) {
	                //Iterate Row
	                ObservableList<String> row = FXCollections.observableArrayList();
	                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	                    //Iterate Column
	                    row.add(rs.getString(i));
	                }
	                System.out.println("Row [1] added " + row);
	                data.add(row);
	 
	            }
	 
	            //FINALLY ADDED TO TableView
	            tab.setItems(data);
	           
	            data=null;
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("Error on Building Data");
	        }
	    }




		private void looad(String page) {
			Parent root=null;
			try {
				System.out.println(page);
					root=FXMLLoader.load(getClass().getResource("../UI/"+page+"Administration.fxml"));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(page.equals("Espace")) {
				bp.setCenter(root);
			}else {
				bp.setCenter(root);
			}
			
		
			
			
			
		}

	
	
	 

}

