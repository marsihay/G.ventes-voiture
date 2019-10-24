/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventes_des_voitures.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
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
public class VoitureController implements Initializable {
    double x, y;
    Statement stmt;
    Connexion maConnexion=new Connexion();
    @FXML
    private StackPane loginStackPane;
    @FXML
    private Pane signUpPane;
    @FXML
    private JFXButton OKadd;
    @FXML
    private JFXButton resetadd;
    @FXML
    private JFXButton canceladd;
    @FXML
    private JFXTextField numVlbl;
    @FXML
    private JFXTextField stockVlbl;
    @FXML
    private JFXTextField marqueVlbl;
    @FXML
    private JFXTextField puVlbl;
    @FXML
    private Label notification;
    @FXML
    private JFXButton minimize;
    @FXML
    private JFXButton close;
    @FXML
    private JFXDialogLayout ContentDialog;
    
    void messageDialog(){
        /*JFXDialogLayout content= new JFXDialogLayout();*/
        ContentDialog.setHeading(new Text("Notification"));
        ContentDialog.setBody(new Text("Votre Enregistrement a réussi."));
        JFXDialog dialog=new JFXDialog(loginStackPane, ContentDialog, JFXDialog.DialogTransition.CENTER);
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
        ContentDialog.setActions(button);
        dialog.show();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
         if(event.getSource()== close){
            Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
        } else if(event.getSource()== minimize){
             Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
        } else if(event.getSource()== resetadd){
           numVlbl.setText(""); 
           marqueVlbl.setText("");
           puVlbl.setText("");
           stockVlbl.setText("");
           notification.setText("");
        } else if(event.getSource()== canceladd){
             Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
        }else if(event.getSource()== OKadd){
          String num_voiture= numVlbl.getText();
          String marque= marqueVlbl.getText();
          String pu= puVlbl.getText();          
          String stock= stockVlbl.getText();
          if (num_voiture.equals("")){
              notification.setText("Complete the Num Voiture field");
          } else if (marque.equals("")){
              notification.setText("Complete the Marque field");
          } else if (pu.equals("")){
              notification.setText("Complete the Pu field");
          }else
              if(stock.equals("")){
              notification.setText("Complete the Pu field");
          }else {
          String requete ="INSERT into voiture(num_voiture, marque, pu, stock) values ('"+num_voiture+"','"+marque+"', '"+pu+"', '"+stock+"'); ";
        try{
            
            stmt=maConnexion.ObtenirConnexion().createStatement();
            stmt.executeUpdate(requete);
            
             messageDialog();
             
          
            
        }catch(HeadlessException | SQLException e){
            System.out.println("--> Exception : " + e) ;
            notification.setText("Veuillez vérifiez si le numéro dejà existe/le Prix et le Stock ne sont pas un nombre.");
            }
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
