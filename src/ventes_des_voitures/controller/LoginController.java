/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventes_des_voitures.controller;

import animatefx.animation.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.runtime.Debug.id;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Marsihay
 */
public class LoginController implements Initializable {
    double x, y;
    public static String Username;
      Statement stmt;
    Connexion maConnexion=new Connexion();
    
    @FXML
    private AnchorPane loginAnchorP;
    @FXML
    private JFXButton minimize;

    @FXML
    private JFXButton close;

    @FXML
    private JFXButton okbtn;

    @FXML
    private JFXButton resetbtn;

    @FXML
    private JFXButton cancelbtn;
    @FXML
    private JFXTextField user;
    @FXML
    private JFXPasswordField pwd;
    @FXML
    private Pane signUpPane;
    @FXML
    private JFXTextField user1;
    @FXML
    private JFXPasswordField pwd1;
    @FXML
    private JFXPasswordField pwd11;
    @FXML
    private Pane loginPane;
    @FXML
    private Label notification;
    @FXML
    private Label SignIn;
    @FXML
    private FontAwesomeIconView retour;
    @FXML
    private Label SignUp;
    @FXML
    private JFXButton OkBtnSign;
    @FXML
    private StackPane loginStackPane;
    @FXML
    private JFXDialogLayout DialogLayout;
    
     void messageDialog(){
        /*JFXDialogLayout content= new JFXDialogLayout();*/
        DialogLayout.setHeading(new Text("Message"));
        DialogLayout.setBody(new Text("Enregistrement réussi."));
        JFXDialog dialog=new JFXDialog(loginStackPane, DialogLayout, JFXDialog.DialogTransition.CENTER);
        JFXButton button=new JFXButton("OK");
        button.setStyle("-fx-background-color :   #007AFF; -fx-text-fill:  white;-fx-cursor: hand;");
        button.setOnAction(new EventHandler<ActionEvent>(){
          @Override
        public void handle(ActionEvent event) {  
            dialog.close();
            
            
            Node node=(Node) event.getSource();
            Stage stage=(Stage)node.getScene().getWindow();
            stage.close();
            
            Parent home;
              try {
                  home = FXMLLoader.load(getClass().getResource("/ventes_des_voitures/fxml/Home.fxml"));
                  Scene scene = new Scene(home);
                    stage.setScene(scene);
                    new FlipInX(home).play();
                    stage.show();
              } catch (IOException ex) {
                  Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
              }
            
        }        
        });
        DialogLayout.setActions(button);
        dialog.show();
    }
     
      void WampServerNotification(){
        /*JFXDialogLayout content= new JFXDialogLayout();*/
        DialogLayout.setHeading(new Text("Notification"));
        DialogLayout.setBody(new Text("Veuillez démarrer le Wamp Server, s'il vous plait!!!. \n Et Veuillez redémarrer cette application"));
        JFXDialog dialog=new JFXDialog(loginStackPane, DialogLayout, JFXDialog.DialogTransition.CENTER);
        JFXButton button=new JFXButton("OK");
        button.setStyle("-fx-background-color :  #007AFF; -fx-text-fill:  white;-fx-cursor: hand;");
        button.setOnAction(new EventHandler<ActionEvent>(){
          @Override
        public void handle(ActionEvent event) {  
            dialog.close();            
        }        
        });
        DialogLayout.setActions(button);
        dialog.show();
    }
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if(event.getSource()== close){
            Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
        } else if(event.getSource()== minimize){
             Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
        } else if(event.getSource()== resetbtn){
           user.setText(""); 
           user1.setText("");
           pwd.setText("");
           pwd1.setText("");
           pwd11.setText("");
           notification.setText(""); 
        } else if(event.getSource()== cancelbtn){
             Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
        } else if(event.getSource()== okbtn){
           String userlog=user.getText();
           String passW=pwd.getText();
            if (userlog.equals("")){
              notification.setText("Complete the Username field");
          } else if (passW.equals("")){
              notification.setText("Complete the Password field");
          } else {
             String requete ="select * from login where username ='"+userlog+"' and password ='"+DigestUtils.md5Hex(passW)+"' ";
        try{
            
            stmt=maConnexion.ObtenirConnexion().createStatement();
            ResultSet resultat= stmt.executeQuery(requete);
            notification.setText(maConnexion.notification);
            
        if(!resultat.next()){
            notification.setText("Enter correct Username/Password");
            user.setText(userlog);
            pwd.setText(null);
        }else{
             LoginController.Username = resultat.getString("username");
            
            Node node=(Node) event.getSource();
            Stage stage=(Stage)node.getScene().getWindow();
            stage.close();
            
            Parent home = FXMLLoader.load(getClass().getResource("/ventes_des_voitures/fxml/Home.fxml"));
            Scene scene = new Scene(home);
            stage.setScene(scene);
            new FlipInX(home).play();
            stage.show();             
        }   
            
        }catch(HeadlessException | SQLException e){
            System.out.println("--> Exception : " + e) ;
        }
        finally{
            WampServerNotification();
        }
        }
      }else if(event.getSource()== OkBtnSign){
          String user= user1.getText();
          String pass1= pwd1.getText();
          String pass2= pwd11.getText();
          if (user.equals("")){
              notification.setText("Complete the Username field");
          } else if (pass1.equals("")){
              notification.setText("Complete the Password field");
          } else if (pass2.equals("")){
              notification.setText("Complete the Password confirmation field");
          }else
              if(!pass1.equals(""+pass2+"")){
              notification.setText("you've entered diffrent password");
          }else {
          String passWord= DigestUtils.md5Hex(pass1);
          String requete ="Insert into login (username, password) values ('"+user+"','"+passWord+"') ";
        try{
            
            stmt=maConnexion.ObtenirConnexion().createStatement();
            stmt.executeUpdate(requete);
            
             messageDialog();
            /*JOptionPane.showMessageDialog(null,"Enregistrement réussi!");*/
            LoginController.Username = user;
             
          
            
        }catch(HeadlessException | SQLException e){
            System.out.println("--> Exception : " + e) ;
            }
        finally{
            WampServerNotification();
        }
          }
        } 
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    @FXML
    private void SignUp(MouseEvent event) {
        signUpPane.toFront();
        new FadeIn(signUpPane).play();
    }

    @FXML
    private void retour(MouseEvent event) {
        loginPane.toFront();
        new FadeIn(loginPane).play();
    }
    
}
