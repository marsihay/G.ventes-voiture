/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventes_des_voitures.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import java.awt.HeadlessException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import static ventes_des_voitures.controller.HomeController.TableVenteSelectedIndex;

/**
 * FXML Controller class
 *
 * @author Marsihay
 */
public class EditVenteController implements Initializable {
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
    private JFXTextField numCliVente;
    @FXML
    private JFXTextField numVoiVente;
    @FXML
    private JFXTextField quantitéVente;
    @FXML
    private JFXDatePicker dateVente;
    @FXML
    private Label notification;
    @FXML
    private JFXButton minimize;
    @FXML
    private JFXButton close;
    
    void messageDialog(){
        /*JFXDialogLayout content= new JFXDialogLayout();*/
        content.setHeading(new Text("Notification"));
        content.setBody(new Text("Votre Modification a été Enregistré correctement."));
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
                Integer Index=HomeController.TableVenteSelectedIndex;
                java.sql.Statement stmt1=maConnexion.ObtenirConnexion().createStatement();
                java.sql.ResultSet resultat= stmt1.executeQuery(" SELECT  * from vente WHERE  num_vente='"+Index+"';");
            if(resultat.next()){
                numCliVente.setText(resultat.getString("num_client"));
                numVoiVente.setText(resultat.getString("num_voiture"));
                quantitéVente.setText(resultat.getString("qte"));
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");                
                LocalDate date=ConvertStringtoLocalDate(sdf.format(resultat.getDate("date_vente")));
                dateVente.setValue(date);
            }

        }catch(Exception e){

        }
    }    
    
    public static LocalDate ConvertStringtoLocalDate(String date) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate= LocalDate.parse(date, myFormatObj);
        return localDate;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
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
          String num_Client= numCliVente.getText();
          String num_Voiture= numVoiVente.getText();
          String quantite= quantitéVente.getText();
          LocalDate date= dateVente.getValue();
          if (num_Client.equals("")){
              notification.setText("Complete the Num Client field");
          } else if (numVoiVente.equals("")){
              notification.setText("Complete the Nul Voiture field");
          } else if (quantite.equals("")){
              notification.setText("Complete the Quantité field");
          }else if(date.equals("")){
              notification.setText("Complete the Date de la Vente field");
          }else {
              Integer Index=HomeController.TableVenteSelectedIndex;
          String requete ="UPDATE vente SET num_client='"+num_Client+"', num_voiture='"+num_Voiture+"', qte='"+quantite+"', date_vente='"+date+"' Where num_vente='"+Index+"'; ";
        try{
            
            stmt=maConnexion.ObtenirConnexion().createStatement();
            stmt.executeUpdate(requete);
            
             messageDialog();
             
          
            
        }catch(HeadlessException | SQLException e){
            System.out.println("--> Exception : " + e) ;
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
