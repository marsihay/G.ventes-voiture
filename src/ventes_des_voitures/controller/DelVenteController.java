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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
public class DelVenteController implements Initializable {
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
    private Label DeleteNumClient;
    @FXML
    private Label DeleteQuantité;
    @FXML
    private Label DeleteNumVoiture;
    @FXML
    private Label DeleteDate;
    @FXML
    private JFXButton minimize;
    @FXML
    private JFXButton close;
    void messageDialog(){
        /*JFXDialogLayout content= new JFXDialogLayout();*/
        content.setHeading(new Text("Notification"));
        content.setBody(new Text("Suppression a réussi."));
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
                DeleteNumClient.setText(resultat.getString("num_client"));
                DeleteNumVoiture.setText(resultat.getString("num_voiture"));
                DeleteQuantité.setText(resultat.getString("qte"));
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");                
                sdf.format(resultat.getDate("date_vente"));
                DeleteDate.setText(sdf.format(resultat.getDate("date_vente")));
            }

        }catch(Exception e){

        }
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
            
                Integer Index=HomeController.TableVenteSelectedIndex;
                String num_Voiture=DeleteNumVoiture.getText();
                 String qte="SELECT stock from voiture where num_voiture='"+num_Voiture+"';";
        try{
            
            int QTE=Integer.parseInt(DeleteQuantité.getText());
            
            stmt=maConnexion.ObtenirConnexion().createStatement();
            java.sql.ResultSet rs=stmt.executeQuery(qte);
            if(rs.next()){
                    int qteActuel=rs.getInt("stock");
                    int qteAfter=qteActuel+QTE;
                         String update="UPDATE voiture set stock="+qteAfter+" where num_voiture='"+num_Voiture+"';";
                        try{
                         java.sql.Statement stmt1=maConnexion.ObtenirConnexion().createStatement();                         
                           stmt.executeUpdate(update);
                         stmt1.executeUpdate(" DELETE from vente WHERE  num_vente='"+Index+"';");
                         System.out.println("Suppression Réussie ");
                         messageDialog();
                         }catch(HeadlessException | SQLException e){
                         System.out.println("--> Exception : " + e) ;
                         }
                         
            };
            
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
