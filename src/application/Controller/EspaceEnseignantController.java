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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
 
public class EspaceEnseignantController implements Initializable {
	ConnectionEcole pc=new ConnectionEcole();

	Alert alert = new Alert(null);
	public ObservableList<cour> ObservableList = FXCollections.observableArrayList();
	public ObservableList optionsCombo1=  pc.selectclass();
	public ObservableList optionsCombo2=pc.selectmatier();
	
	Map<String, Object> fxmlNamespace;
	private fichier F;
	@FXML
	private BorderPane bp;
	@FXML
	private Button upload;
	@FXML
	private Button supprimer;
	@FXML
	private Button Consulteremploit;
	@FXML
	private AnchorPane ap;
	
	@FXML
	private TextField fabspath;
	@FXML
	private TableView <ObservableList> Tableupl;
	@FXML
	private ComboBox classeCom;
	@FXML
	private ComboBox MatiCom;
	@FXML
	private TableView Tablsup;
	
	@FXML
	private Button suppCour;
	
	
	
	
	@FXML
	private Button Deconnection;
	
	@FXML
	private Label labelEnsi;

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
	private Button telechemplo;
	@FXML
	private TableView tableemp;

	
	

	
	
	// Event Listener on Button[#telechemplo].onAction
	@FXML
	public void telechemplo(ActionEvent event) {

		fichier f=new fichier();
		
		ObservableList rowList = (ObservableList) Tablsup.getItems().get(Tablsup.getSelectionModel().getSelectedIndex());
		String nomf = rowList.get(1).toString();

		String extf = rowList.get(2).toString();

		String dataf = rowList.get(3).toString();
		
		f.setData(dataf);
		f.setNom(nomf);
		f.setExtension(extf);
		try {
			f.Operationfichier(f);
		} catch (FileAlreadyExistsException e) {
			e.printStackTrace();
		}
		buildData(tableemp,"emploit");
	}
	
	
	// Event Listener on Button[#suppCour].onAction
	@FXML
	public void suppCour(ActionEvent event) {
		

		ObservableList rowList = (ObservableList) Tablsup.getItems().get(Tablsup.getSelectionModel().getSelectedIndex());
		String id = rowList.get(0).toString();

		pc.suppcour(Integer.parseInt(id));
		buildData(Tablsup,"cour");
	
	}
	
	


	private String f;
	
	private FileReader 	filereader;
	public 		fichier ff=new fichier();


	private Connection conn;

	
	@FXML
	private void Selection(ActionEvent event) {

		classeCom.setItems(optionsCombo1);
		MatiCom.setItems(optionsCombo2);
			
		FileChooser fc=new FileChooser();
		File selected=fc.showOpenDialog(null);
		
		if(selected !=null) {
			fabspath.setText(selected.getAbsolutePath());
		}
		
			String		pathf=fabspath.getText();
			Path fileLocation =  Paths.get(pathf);
		try {
			byte[] data = Files.readAllBytes(fileLocation);
			
			ff.setData(ff.byte_to_base64(data));
			ff.setExtension(pathf.substring(pathf.length()-3, pathf.length()));
			ff.setNom(pathf.substring(pathf.lastIndexOf("\\")+1, pathf.length()-4));
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	

	@FXML
	public void upload(MouseEvent event) {
		bp.setCenter(ap);
		classeCom.setItems(optionsCombo1);
		MatiCom.setItems(optionsCombo2);
	}
	User u=new User();
		// Event Listener on BorderPane[#bp].onMouseEntered
		@FXML
		public void getidfromtitle(MouseEvent event) {

			 Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			
			
			 u=pc.user(Integer.parseInt(stage.getTitle().toString()));
			 labelEnsi.setText(u.getUsername()+"  "+u.getPrenom());
			 
			 
		}
	
	@FXML
	public void suppr(MouseEvent event) {

		looad("Sup");
		
	}
	
	
	
	@FXML
	public void conslt(MouseEvent event) {
		looad("ConsultEmpl");

	}
	

	@FXML
	public void ValidBu(ActionEvent event) {
		

	ConnectionEcole cn=new ConnectionEcole();
	String sql="INSERT INTO cour ( nom, extension, matiere, classe, cour_pdf) VALUES (?,?,?,?,?)";
	
	String selectedclasse=classeCom.getValue().toString();
	String selectedMatiere=MatiCom.getValue().toString();
	
	

	ConnectionEcole cnx=new ConnectionEcole();
	int idclasse=cnx.getclasse(selectedclasse);
	
	int idmatiere=cnx.getmatiere(selectedMatiere);
	
	

	

	int test=cn.uploadcour(sql, ff,idclasse,idmatiere);
	
if(test==1) {
	fabspath.setText("");
	classeCom=new ComboBox();
		MatiCom=new ComboBox();	
		buildData(Tableupl,"cour");
}else {
	alert.setAlertType(AlertType.WARNING);
	alert.setHeaderText(null);
	alert.setTitle("Warning");
	alert.setContentText("Erreur D'insertion");
	alert.showAndWait();
}
		
	}
	
	
	
	
	
	
	



 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
            
            
            String location1=location.toString();
				
		String	ffx=location1.substring(location1.lastIndexOf("/")+1, location1.length());
			
			

			if(ffx.equals("EspaceEnseignant.fxml")) {
				buildData(Tableupl,"cour");
			}else if(ffx.equals("EnseignantSup.fxml")) {
				buildData(Tablsup,"cour");
			}else if(ffx.equals("EnseignantConsultEmpl.fxml")){
				buildData(tableemp,"emploi");
			}
		
			
			
			

			
	     
		
		
	}
		
	
	
  private ObservableList<ObservableList> data;
 

 
	
	
  public void buildData(TableView<ObservableList> Tab,String ent) {
      Connection c;
      Statement ste;
      data = FXCollections.observableArrayList();
      try {
         // c = DBConnect();
         ste = DBConnect.getConnection().createStatement();
          
          String SQL = "SELECT * from "+ent;
          
          ResultSet rs = ste.executeQuery(SQL);

         
          for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
              final int j = i;
              TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
              col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                  public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                      return new SimpleStringProperty(param.getValue().get(j).toString());
                  }
              });

              Tab.getColumns().addAll(col);
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
				root=FXMLLoader.load(getClass().getResource("../UI/Enseignant"+page+".fxml"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		bp.setCenter(root);
	
		
		
		
	}
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
	
	



