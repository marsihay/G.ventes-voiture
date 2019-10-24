/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventes_des_voitures;

import animatefx.animation.FlipInX;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



/**
 *
 * @author Marsihay
 */
public class Ventes_des_Voitures extends Application {
    
    @Override
   /* public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Login.fxml"));        
        Scene scene = new Scene(root);        
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.getIcons().add(new Image("/ventes_des_voitures/images/icons8_Car_50px.png"));        
        new FlipInX(root).play();
        stage.show();
    }*/
    
     public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Login.fxml"));
        primaryStage.setTitle("Ventes des Voitures");
        primaryStage.getIcons().add(new Image("/ventes_des_voitures/images/G2V_Icon.png"));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(new Scene(root));
        new FlipInX(root).play();
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
