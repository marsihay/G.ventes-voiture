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
import com.toedter.calendar.DateUtil;
import java.awt.HeadlessException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static ventes_des_voitures.controller.EditVenteController.ConvertStringtoLocalDate;

/**
 * FXML Controller class
 *
 * @author Marsihay
 */
public class AddVenteController implements Initializable {
    double x, y;
    Statement stmt;
    Connexion maConnexion=new Connexion();
    static boolean foundC = false;
    static boolean foundV = false;
    @FXML
    private JFXDialogLayout ContentDialog;
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
       void AlertDialog(String Alert){
        /*JFXDialogLayout content= new JFXDialogLayout();*/
        ContentDialog.setHeading(new Text("Un message d'erreur"));
        ContentDialog.setBody(new Text(Alert));
        JFXDialog dialog=new JFXDialog(loginStackPane, ContentDialog, JFXDialog.DialogTransition.CENTER);
        JFXButton button=new JFXButton("OK");
        button.setStyle("-fx-background-color :  #007AFF; -fx-text-fill:  white;-fx-cursor: hand;-fx-pref-width: 50px");
        button.setOnAction(new EventHandler<ActionEvent>(){
          @Override
        public void handle(ActionEvent event) {  
            dialog.close();
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
        dateVente.setValue(LocalDate.now());
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
           numCliVente.setText(""); 
           numVoiVente.setText("");
           quantitéVente.setText("");
           dateVente.setValue(LocalDate.now());
           notification.setText("");
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
          }else if (isInputValid()){
              String qte="SELECT stock from voiture where num_voiture='"+num_Voiture+"';";
        try{
            int QTE=Integer.parseInt(quantite);
            
            stmt=maConnexion.ObtenirConnexion().createStatement();
            java.sql.ResultSet rs=stmt.executeQuery(qte);
            if(rs.next()){
                if(QTE > rs.getInt("stock")){notification.setText("Ooops!!! Stock Insuffisant.");
                } else {
                    int qteActuel=rs.getInt("stock");
                    int qteAfter=qteActuel-QTE;
                         String update="UPDATE voiture set stock="+qteAfter+" where num_voiture='"+num_Voiture+"';";
                         String requete ="INSERT into vente(num_client, num_voiture, qte, date_vente) values ('"+num_Client+"','"+num_Voiture+"', '"+quantite+"', '"+date+"'); ";
                       try{

                           stmt=maConnexion.ObtenirConnexion().createStatement();
                           stmt.executeUpdate(requete);
                           stmt.executeUpdate(update);
                            messageDialog();



                       }catch(HeadlessException | SQLException e){
                           System.out.println("--> Exception : " + e) ;
                           notification.setText("Veuillez vérifiez toute les champs.");
                           }
                }
            };
            
            }catch(HeadlessException | SQLException e){
            System.out.println("--> Exception : " + e) ;
            }
         
          }
        }
        
    }
    
     private boolean isInputValid() {
        String errorMessage = "";

        if (numCliVente.getText() == null || numCliVente.getText().length() == 0) {
            errorMessage += "Num Client Invalide!\n";
        }
        if (numVoiVente.getText() == null || numVoiVente.getText().length() == 0) {
            errorMessage += "Num Voiture Invalide!\n";
        }

        if (quantitéVente.getText() == null || quantitéVente.getText().length() == 0) {
            errorMessage += "Quantité Non Valide!\n";
        } else {
            // try to parse the Quantity into an int.
            try {
                Integer.parseInt(quantitéVente.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Quantité Invalide (Doit etre en Entier)!\n";
            }
        }

        if (dateVente.getValue() == null ) {
            errorMessage += "Date de la Vente Invalide!\n";
        
        }
        
         try{
                java.sql.Statement stmt1=maConnexion.ObtenirConnexion().createStatement();
                java.sql.ResultSet numClient= stmt1.executeQuery("select num_client from client;");
               String Client=numCliVente.getText();
            while(numClient.next()){
                if (Client.equals(numClient.getString("num_client"))) {
                this.foundC= true;
                System.out.println("Num Client Trouvée: "+numCliVente.getText()+" et "+numClient.getString("num_client")+" ") ;
                break;
                    }  else this.foundC= false;               
            }
            java.sql.ResultSet numVoiture= stmt1.executeQuery("select num_voiture from voiture;");
            while(numVoiture.next()){
                if ((numVoiVente.getText()).equals(numVoiture.getString("num_voiture"))) {
                this.foundV= true;
                System.out.println("Num Voiture Trouvée ") ;
                break;
                }  else this.foundV= false;              
            }           
             
         if (!this.foundC){errorMessage += "Le numéro du Client que vous avez entré n'existe pas!\n";};
         if (!this.foundV){errorMessage += "Le numéro de la Voiture que vous avez entré n'existe pas!\n";};   
        }catch(Exception e){

        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            AlertDialog(errorMessage);
            return false;
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
