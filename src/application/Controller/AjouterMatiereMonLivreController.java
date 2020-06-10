/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Controller;

import Service.ServiceCategorie;
import Service.ServiceMatiereMonLivre;
import application.Entity.Categorie;
import application.Entity.MatiereMonLivre;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Rzouga
 */
public class AjouterMatiereMonLivreController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField nbh;
    @FXML
    private ChoiceBox<String> cat;
    @FXML
    private ImageView imv;
    
    Connection cnx = DataSource.getInstance().getCnx();

    int c;
    int file;
    File pDir;
    File pfile;
    String lien;
    
    @FXML
    ServiceCategorie cs = new ServiceCategorie();
    
  public ObservableList catlist (){
    ObservableList<String> l = FXCollections.observableArrayList();
    ObservableList<Categorie> k = cs.afficher();
    Iterator<Categorie> i = k.iterator();
    while(i.hasNext())
    {
        l.add(i.next().getNom());
    }
              return l;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cat.setItems(catlist());
        c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
        pDir = new File("C:/wamp/www/EcoleO/web/uploads/Profile" + c + ".jpg");
        lien = "Profile" + c + ".jpg";

    }    

    @FXML
    private void uploadFile(ActionEvent event) throws MalformedURLException {
        
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Choisir une image: ");
                fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );
        Window stage = null;
        pfile = fileChooser.showOpenDialog(stage);

        /* - draw image */
        if (pfile != null) {
            file=1;
            Image image = new Image(pfile.toURI().toURL().toExternalForm());
            imv.setImage(image);
    }
    }

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
        
       copier(pfile,pDir);
                String req = "select * from categorie where nomCat=?";
    
                  PreparedStatement pst = cnx.prepareStatement(req);
                  pst.setString(1, (String)cat.getSelectionModel().getSelectedItem());
                  ResultSet rs =pst.executeQuery();
                  while(rs.next()){
                  Categorie   c= new Categorie(rs.getInt(1),rs.getString(2));
                     
                  String nomMat = nom.getText();
                  int hr = Integer.parseInt(nbh.getText());
                  
                      MatiereMonLivre math = new MatiereMonLivre(c.getId(), hr, nomMat, lien);
                      ServiceMatiereMonLivre sm = new ServiceMatiereMonLivre();
                      sm.ajouter(math);
    }
    }
    
         public static boolean copier(File source, File dest) {
        try (InputStream sourceFile = new java.io.FileInputStream(source);
                OutputStream destinationFile = new FileOutputStream(dest)) {
            // Lecture par segment de 0.5Mo  
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false; // Erreur 
        }
        return true; // Résultat OK   
    }

    
}
