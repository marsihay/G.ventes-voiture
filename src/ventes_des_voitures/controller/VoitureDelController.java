/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventes_des_voitures.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.awt.HeadlessException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Marsihay
 */
public class VoitureDelController implements Initializable {
    double x, y;
    Statement stmt;
    Connexion maConnexion=new Connexion();
    @FXML
    private JFXDialogLayout content;
    @FXML
    private StackPane loginStackPane;
    @FXML
    private Pane signUpPane;
    @FXML
    private JFXButton OKadd;
    @FXML
    private JFXButton canceladd;
    @FXML
    private JFXButton minimize;
    @FXML
    private JFXButton close;
    @FXML
    private Label DeleteNum;
    @FXML
    private Label DeleteMarque;
    @FXML
    private Label DeletePu;
    @FXML
    private Label DeleteStock;
    
    void messageDialog(){
        /*JFXDialogLayout content= new JFXDialogLayout();*/
        content.setHeading(new Text("Notification"));
        content.setBody(new Text("Votre produit a été supprimer correctement."));
        JFXDialog dialog=new JFXDialog(loginStackPane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button=new JFXButton("OK");
        button.setStyle("-fx-background-color :  #007AFF; -fx-text-fill:  white;-fx-cursor: hand;");
        button.setOnAction(new EventHandler<ActionEvent>(){
          @Override
        public void handle(ActionEvent event) {  
            dialog.close();
            Node node=(Node) event.getSource();
            Stage stage=(Stage)node.getScene().getWindow();
            stage.close();
           
            
        }        
        });
        content.setActions(button);
        dialog.show();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          try{
                String Index=HomeController.TableSelectedIndex;
                java.sql.Statement stmt1=maConnexion.ObtenirConnexion().createStatement();
                java.sql.ResultSet resultat= stmt1.executeQuery(" SELECT  * from voiture WHERE  num_voiture='"+Index+"';");
            if(resultat.next()){
                DeleteNum.setText(resultat.getString("num_voiture"));
                DeleteMarque.setText(resultat.getString("marque"));
                DeletePu.setText(resultat.getString("pu"));
                DeleteStock.setText(resultat.getString("stock"));
            }

        }catch(Exception e){

        }
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
        if(event.getSource()== close){
            Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
        } else if(event.getSource()== minimize){
             Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
        } else if(event.getSource()== canceladd){
             Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
        }else if(event.getSource()== OKadd){
            
                String Index=HomeController.TableSelectedIndex;
                System.out.println("Suppression Réussie 1");
               try{
                java.sql.Statement stmt1=maConnexion.ObtenirConnexion().createStatement();
                stmt1.executeUpdate(" DELETE from voiture WHERE  num_voiture='"+Index+"';");
                System.out.println("Suppression Réussie ");
                messageDialog();
                }catch(HeadlessException | SQLException e){
                System.out.println("--> Exception : " + e) ;
                }
        }
         
    }

    @FXML
    private void Dragged(MouseEvent event) {
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    private void Pressed(MouseEvent event) {
        x= event.getSceneX();
        y= event.getSceneY();
    }
    
}
