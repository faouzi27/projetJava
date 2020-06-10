/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


public class MainFx extends Application {
@Override
public void start(Stage primaryStage)throws Exception {
		
          Parent root = FXMLLoader.load(getClass().getResource("./UI/Acceuil.fxml")); //back

             ////Parent root = FXMLLoader.load(getClass().getResource("./UI/AfficheFrontMatiere.fxml")); //front
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
