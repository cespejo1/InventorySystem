/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carlosespejoinventorysystem.View_Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author carlosespejo
 */
public class CarlosEspejoInventorySystem extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //FXMLLoader loader = new FXMLLoader();
        //loader.setLocation(CarlosEspejoInventorySystem.class.getResource("MainScreen.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Inventory System");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
