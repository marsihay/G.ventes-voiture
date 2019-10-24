/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventes_des_voitures.controller;

import animatefx.animation.*;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Element;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.ibm.icu.text.RuleBasedNumberFormat;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import ventes_des_voitures.Model.Voiture;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import eu.hansolo.medusa.Clock;
import eu.hansolo.medusa.Clock.ClockSkinType;
import eu.hansolo.medusa.ClockBuilder;
import java.awt.HeadlessException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.textfield.CustomTextField;
import ventes_des_voitures.Model.Chiffres_Affaires;
import ventes_des_voitures.Model.Client;
import ventes_des_voitures.Model.Facture;
import ventes_des_voitures.Model.Stock;
import ventes_des_voitures.Model.Vente;
import static ventes_des_voitures.controller.EditVenteController.ConvertStringtoLocalDate;
import static ventes_des_voitures.controller.StyleWindow.*;

/**
 * FXML Controller class
 *
 * @author Marsihay
 */
public class HomeController implements Initializable {
    double x, y;    
    Statement stmt;
    Connexion maConnexion= new Connexion();
    LoginController login=new LoginController();
    public static String TableSelectedIndex;
    public static String TableClientSelectedIndex;
    public static Integer TableVenteSelectedIndex;
    @FXML
    private JFXButton minimize;
    @FXML
    private JFXButton close;
    @FXML
    private JFXButton accueil;
    @FXML
    private JFXButton produit;
    @FXML
    private JFXButton vente;
    @FXML
    private JFXButton client;
    @FXML
    private JFXButton bilan;
    @FXML
    private Pane bilanPNL;
    @FXML
    private Pane clientPNL;
    @FXML
    private Pane ventePNL;
    @FXML
    private Pane produitPNL;
    @FXML
    private Pane accueilPNL;
    @FXML
    private JFXButton logout;
    @FXML
    private TableView<Voiture> tableVoiture;
    @FXML
    private TableColumn<Voiture, String> num_voiture;
    @FXML
    private TableColumn<Voiture, String> marque;
    @FXML
    private TableColumn<Voiture, Double> PU;
    @FXML
    private TableColumn<Voiture, Integer> stock;
    @FXML
    private TextField searchBar;
    @FXML
    private FontAwesomeIconView searchIcon;
    @FXML
    private JFXButton addVoiture;
    @FXML
    private JFXButton editVoiture;
    @FXML
    private JFXButton delVoiture;    
    @FXML
    private Label nbVoiturelbl;
    @FXML
    private Label montantlbl;
    @FXML
    private Label stocklbl;
    @FXML
    private FontAwesomeIconView searchIcon1;
    @FXML
    private JFXButton addClient;
    @FXML
    private JFXButton editClient;
    @FXML
    private JFXButton delClient;
    @FXML
    private TableView<Client> tableClient;
    @FXML
    private TableColumn<Client, String> numCli;
    @FXML
    private TableColumn<Client, String> nomCli;
    @FXML
    private TextField searchBarClient;
    @FXML
    private JFXTextField numCliTextField;
    @FXML
    private JFXTextField nomCliTextField;
    @FXML
    private Label ClientNotif;
    private JFXButton ResetClient;
    @FXML
    private StackPane StackPaneHome;
    @FXML
    private JFXDialogLayout DialogLayout;
    @FXML
    private AnchorPane HomeAnchorPane;
    @FXML
    private Label nombreClientNotif;
    @FXML
    private FontAwesomeIconView searchIcon2;
    @FXML
    private Label montantlbl1;
    @FXML
    private ContextMenu ContextMenuVoitureTable;
    @FXML
    private JFXButton addVente;
    @FXML
    private TextField searchBarVente;
    @FXML
    private TableColumn<Vente, String> NumCliColumn;
    @FXML
    private TableColumn<Vente, String> NumVoitureColumn;
    @FXML
    private TableColumn<Vente, Integer> QteColumn;
    @FXML
    private TableColumn<Vente, Date> dateVenteColumn;
    @FXML
    private TableView<Vente> TableVente;
    @FXML
    private JFXDatePicker DateDebut;
    @FXML
    private ContextMenu ContextMenuVenteTable;
    @FXML
    private JFXDatePicker DateFin;
    @FXML
    private JFXDatePicker dateFacture;
    @FXML
    private Label NomClient;
    @FXML
    private JFXTextField NumClientF;
    @FXML
    private TableColumn<Facture, String> NumVoitureF;
    @FXML
    private TableColumn<Facture, String> MarqueVoitureF;
    @FXML
    private TableColumn<Facture, Double> puF;
    @FXML
    private TableColumn<Facture, Integer> qteVoitureF;
    @FXML
    private TableColumn<Facture, Double> MontantF;
    @FXML
    private CustomTextField MontantTotal;
    @FXML
    private TableView<Facture> TableFacture;
    @FXML
    private Label MontantEnLettre;
    @FXML
    private JFXButton PrintBtn;
    @FXML
    private TableColumn<Chiffres_Affaires, String> numCliChiffre;
    @FXML
    private TableColumn<Chiffres_Affaires, String> nomCliChiffre;
    @FXML
    private TableColumn<Chiffres_Affaires, Double> ChiffreAffaire;
    @FXML
    private JFXTextField ANNEE;
    @FXML
    private TableView<Chiffres_Affaires> Chiffres_AffairesTbl;
    @FXML
    private PieChart pieChartCH;
    @FXML
    private TableView<Stock> StockTable;
    @FXML
    private TableColumn<Stock, String> ClientNom;
    @FXML
    private TableColumn<Stock, Integer> QTEColumn;
    @FXML
    private TableColumn<Stock, Date> date_VenteC;
    @FXML
    private Label EtatLBL;
    @FXML
    private JFXTextField ProdNum;
    @FXML
    private Label MarqueLBL;
    @FXML
    private Label ChiffreLBL;
    @FXML
    private Clock lera;
    @FXML
    private JFXDatePicker DateDebut1;
    @FXML
    private JFXDatePicker DateFin1;
    @FXML
    private CustomTextField num_Facture;
    @FXML
    private FontAwesomeIconView numFactINFO;
    @FXML
    private StackPane StackPaneVente;
    
    
    private Voiture createVoiture(ResultSet rs) {
      Voiture v = new Voiture();
      try {
         v.setNumVoiture(rs.getString("num_voiture"));
         v.setMarque(rs.getString("marque"));
         v.setPU(rs.getDouble("pu"));
         v.setStock(rs.getInt("stock"));
      } catch (SQLException ex) {
      }
      return v;
   }
    
    
/* INSERT into voiture(num_voiture, marque, pu, stock) values ('v5','RENAULT', 16000, 4);*/
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO          
             logout.setText(login.Username);
     // ajouter les donnees dans la table        
             loadVoitureTable();           
             loadClientTable();
             loadVenteTable();
             dateFacture.setValue(LocalDate.now());
             loadFactureTable();
            // Listen for selection changes for Table Voiture.
            tableVoiture.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if(newValue==null){System.out.println("Cell selected NULL");}
                
                else {TableSelectedIndex=newValue.getNumVoiture();
                    System.out.println("Cell selected "+TableSelectedIndex+"");
                }
            });
            tableVoiture.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
 
            @Override
            public void handle(ContextMenuEvent event) {
 
                ContextMenuVoitureTable.show(tableVoiture, event.getScreenX(), event.getScreenY());
            }
        });
            
            // Listen for selection changes for Table Client.
            tableClient.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if(newValue==null){System.out.println("Cell selected NULL");}
                
                else {TableClientSelectedIndex=newValue.getNumClient();
                    System.out.println("Cell selected "+TableClientSelectedIndex+"");
                }
            });
            
      
            // Listen for selection changes for Table Vente.
            TableVente.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if(newValue==null){System.out.println("Cell selected NULL");}
                
                else {TableVenteSelectedIndex=newValue.getId();
                    TableClientSelectedIndex=newValue.getDateVente();
                    System.out.println("Cell selected "+TableVenteSelectedIndex+"");
                }
            });   
            TableVente.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
 
            @Override
            public void handle(ContextMenuEvent event) {
 
                ContextMenuVenteTable.show(TableVente, event.getScreenX(), event.getScreenY());
            }
        });         
           
    }  
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource()== close){
            Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
        } else if(event.getSource()== minimize){
             Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
        }  else if(event.getSource()== accueil){
             accueilPNL.toFront();
        new FadeIn(accueilPNL).play();
        }else if(event.getSource()== produit){
             produitPNL.toFront();
             loadVoitureTable();
             editVoiture.setDisable(true);
            delVoiture.setDisable(true);
        new FadeIn(produitPNL).play();
        }else if(event.getSource()== vente){
             ventePNL.toFront();
        new FadeIn(ventePNL).play();
        }else if(event.getSource()== client){
             clientPNL.toFront();
        new FadeIn(clientPNL).play();
        }else if(event.getSource()== bilan){
             bilanPNL.toFront();
        new FadeIn(bilanPNL).play();
        } else if(event.getSource()== ResetClient) {
            numCliTextField.setText("");
            nomCliTextField.setText("");
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

    @FXML
    private void Loggout(MouseEvent event) throws IOException {
        Node node=(Node) event.getSource();
            Stage stage=(Stage)node.getScene().getWindow();
            stage.close();            
            Parent log = FXMLLoader.load(getClass().getResource("/ventes_des_voitures/fxml/Login.fxml"));
            Scene scene = new Scene(log);
            stage.setScene(scene);
            new FlipInY(log).play();
            stage.show();
            
    }
    
    @FXML
    void loadVoitureTable()
    {  
        int nb;
        num_voiture.setCellValueFactory(new PropertyValueFactory<>("numVoiture"));
        marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        PU.setCellValueFactory(new PropertyValueFactory<>("PU"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
      ObservableList<Voiture> list = FXCollections.observableArrayList();        
        list=getVoitureData();
        nb=list.size();
        nbVoiturelbl.setText(""+nb+"");
      tableVoiture.setItems(list);
            String requete ="select  SUM(stock*pu) as Montant,SUM(stock) as Stock from voiture;";
            try {
                stmt=maConnexion.ObtenirConnexion().createStatement();
                ResultSet result= stmt.executeQuery(requete); 
                while (result.next()) {    
                    montantlbl.setText(""+result.getString("Montant")+" Ar"); 
                    stocklbl.setText(""+result.getString("Stock")+""); 
                }
            }catch (SQLException ex) {
      } 
         System.out.println("Chargement du tableau"); 
         editVoiture.setDisable(true);
        delVoiture.setDisable(true);
    }
    
        
   public ObservableList<Voiture> getVoitureData() {
      ObservableList<Voiture> list = FXCollections.observableArrayList();
      try {
         java.sql.Statement stmt1=maConnexion.ObtenirConnexion().createStatement();
         java.sql.ResultSet resultat= stmt1.executeQuery("SELECT  `num_voiture` ,  `marque` , `pu`,  `stock` from voiture;");
         
         while (resultat.next()) {
            Voiture p = createVoiture(resultat);
            list.add(p);
         }
         resultat.close();
         stmt1.close();
      } catch (SQLException ex) {
      }      
      return list;
   }
   
   public ObservableList<Voiture> getSearchData() {
      ObservableList<Voiture> list = FXCollections.observableArrayList();
      String search= searchBar.getText();
      try {
         java.sql.Statement stmt1=maConnexion.ObtenirConnexion().createStatement();
         java.sql.ResultSet resultat= stmt1.executeQuery(
                 "SELECT  `num_voiture` ,  `marque` , `pu`,  `stock` from voiture WHERE  num_voiture LIKE '%"+search+"%' OR marque LIKE '"+search+"%' OR pu LIKE '%"+search+"%' OR stock='"+search+"';");
         
         while (resultat.next()) {
            Voiture p = createVoiture(resultat);
            list.add(p);
         }
         resultat.close();
         stmt1.close();
      } catch (SQLException ex) {
      }      
      return list;
   }

    @FXML
    private void laodVoitureFxml(MouseEvent event) {
        loadStage("/ventes_des_voitures/fxml/Voiture.fxml");
        
    }
    private void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image("/ventes_des_voitures/images/G2V_Icon.png"));
            stage.setTitle("Ventes des Voitures");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
            makeBlur(HomeAnchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void ejectBlur(MouseEvent event) {
        ejectBlur(HomeAnchorPane); 
    }

    @FXML
    private void ChercherVoiture(KeyEvent event) {
        int nb;
        num_voiture.setCellValueFactory(new PropertyValueFactory<>("numVoiture"));
        marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        PU.setCellValueFactory(new PropertyValueFactory<>("PU"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
      ObservableList<Voiture> list = FXCollections.observableArrayList();        
        list=getSearchData();
      tableVoiture.setItems(list);
                            editVoiture.setDisable(true);
                            delVoiture.setDisable(true);
                            addVoiture.setDisable(false);
         System.out.println("Chargement de la Recherche KeyEvent");
    }

    @FXML
    private void TableSelectedRow(MouseEvent event) throws SQLException {
        editVoiture.setDisable(false);
        delVoiture.setDisable(false);
        addVoiture.setDisable(false);          
    }

    @FXML
    private void loadEditVoitureFxml(MouseEvent event) {
        editVoiture.setDisable(true);
        delVoiture.setDisable(true);
        addVoiture.setDisable(false);
        loadStage("/ventes_des_voitures/fxml/VoitureEdit.fxml");
    }

    @FXML
    private void loadDeleteVoitureFxml(MouseEvent event) {
        editVoiture.setDisable(true);
        delVoiture.setDisable(true);
        addVoiture.setDisable(false);
        loadStage("/ventes_des_voitures/fxml/VoitureDel.fxml");
        
    }
    
    @FXML
    private void loadEditVoitureFxmlByContextMenu(ActionEvent event) {
        editVoiture.setDisable(true);
        delVoiture.setDisable(true);
        addVoiture.setDisable(false);
        loadStage("/ventes_des_voitures/fxml/VoitureEdit.fxml");
    }

    @FXML
    private void loadDeleteVoitureFxmlByContextMenu(ActionEvent event) {
         editVoiture.setDisable(true);
        delVoiture.setDisable(true);
        addVoiture.setDisable(false);
        loadStage("/ventes_des_voitures/fxml/VoitureDel.fxml");
    }
    
    @FXML
    private void laodVoitureFxmlByContextMenu(ActionEvent event) {
        loadStage("/ventes_des_voitures/fxml/Voiture.fxml");
    }
    /*/ pour le Client Tab *********************************************
    *****************************************************************************************************
    /*/
    void messageDialog(){
        /*JFXDialogLayout content= new JFXDialogLayout();*/
        DialogLayout.setHeading(new Text("Notification"));
        DialogLayout.setBody(new Text("Votre client a été supprimer correctement."));
        JFXDialog dialog=new JFXDialog(StackPaneHome, DialogLayout, JFXDialog.DialogTransition.CENTER);
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
    private Client createClient(ResultSet rs) {
      Client c = new Client();
      try {
         c.setNumClient(rs.getString("num_client"));
         c.setMarque(rs.getString("nom"));
      } catch (SQLException ex) {
      }
      return c;
   }
    
     public ObservableList<Client> getClientData() {
      ObservableList<Client> list = FXCollections.observableArrayList();
      try {
         java.sql.Statement stmt1=maConnexion.ObtenirConnexion().createStatement();
         java.sql.ResultSet resultat= stmt1.executeQuery("SELECT  * from client;");
         
         while (resultat.next()) {
            Client p = createClient(resultat);
            list.add(p);
         }
         resultat.close();
         stmt1.close();
      } catch (SQLException ex) {
      }      
      return list;
   }
     
     void loadClientTable()
    {  
        int nb;
        numCli.setCellValueFactory(new PropertyValueFactory<>("numClient"));
        nomCli.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
      ObservableList<Client> list = FXCollections.observableArrayList();        
        list=getClientData();
        nb=list.size();
      tableClient.setItems(list);
         System.out.println("Chargement du tableau Client"); 
         editClient.setDisable(true);
        delClient.setDisable(true);
        nombreClientNotif.setText(""+nb+"");
    }
    
    @FXML
    private void AddClient(MouseEvent event) {
        String numCli=numCliTextField.getText();
        String nomCli=nomCliTextField.getText();
        if (numCli.equals("")){
              ClientNotif.setText("Completez le champ Numéro du Client");
          } else if (nomCli.equals("")){
              ClientNotif.setText("Completez le Champ Nom du Client");
          } else {
          String requete ="INSERT into client(num_client, nom) values ('"+numCli+"','"+nomCli+"'); ";
        try{
            
            stmt=maConnexion.ObtenirConnexion().createStatement();
            stmt.executeUpdate(requete);
             loadClientTable();
             numCliTextField.setText("");
            nomCliTextField.setText("");
          ClientNotif.setText("");
            
        }catch(HeadlessException | SQLException e){
            System.out.println("--> Exception : " + e) ;
            ClientNotif.setText("Veuillez vérifiez si le numéro du client dejà existe.");
            }
          }
    }

    @FXML
    private void EditClient(MouseEvent event) {
          String numClient= numCliTextField.getText();
          String nomClient= nomCliTextField.getText();
          if (numClient.equals("")){
              ClientNotif.setText("Completez le champ Numéro du Client");
          } else if (nomClient.equals("")){
              ClientNotif.setText("Completez le Champ Nom du Client");
          } else {
          String requete ="UPDATE client SET nom='"+nomClient+"' Where num_client='"+numClient+"'; ";
        try{
            
            stmt=maConnexion.ObtenirConnexion().createStatement();
            stmt.executeUpdate(requete);
            editClient.setDisable(true);
            delClient.setDisable(true);
            addClient.setDisable(false);
            numCliTextField.setText("");
            numCliTextField.setEditable(true);
            nomCliTextField.setText("");
             loadClientTable();
             
          
            
        }catch(HeadlessException | SQLException e){
            System.out.println("--> Exception : " + e) ;
            }
          }
        
    }

    @FXML
    private void DeleteClient(MouseEvent event) {
            String Index=TableClientSelectedIndex;
               try{
                java.sql.Statement stmt1=maConnexion.ObtenirConnexion().createStatement();
                stmt1.executeUpdate(" DELETE from client WHERE  num_client='"+Index+"';");
                System.out.println("Suppression Client Réussie ");
                messageDialog();
                }catch(HeadlessException | SQLException e){
                System.out.println("--> Exception : " + e) ;
                }
            editClient.setDisable(true);
            delClient.setDisable(true);
            addClient.setDisable(false);
            numCliTextField.setText("");
            numCliTextField.setEditable(true);
            nomCliTextField.setText("");
             loadClientTable();
    }

    @FXML
    private void ActiverModifierEtSupprimerButton(MouseEvent event) {
        editClient.setDisable(false);
        delClient.setDisable(false);  
        addClient.setDisable(true); 
        try{
                String Index=TableClientSelectedIndex;
                java.sql.Statement stmt1=maConnexion.ObtenirConnexion().createStatement();
                java.sql.ResultSet resultat= stmt1.executeQuery(" SELECT  * from client WHERE  num_client='"+Index+"';");
            if(resultat.next()){
                numCliTextField.setText(resultat.getString("num_client"));
                numCliTextField.setEditable(false);
                nomCliTextField.setText(resultat.getString("nom"));
            }

        }catch(Exception e){

        }
    }
    
    public ObservableList<Client> getClientSearchData() {
      ObservableList<Client> list = FXCollections.observableArrayList();
      String search= searchBarClient.getText();
      try {
         java.sql.Statement stmt1=maConnexion.ObtenirConnexion().createStatement();
         java.sql.ResultSet resultat= stmt1.executeQuery(
                 "SELECT  `num_client` ,  `nom` from client WHERE  num_client LIKE '%"+search+"%' OR nom LIKE '%"+search+"%';");
         
         while (resultat.next()) {
            Client p = createClient(resultat);
            list.add(p);
         }
         resultat.close();
         stmt1.close();
      } catch (SQLException ex) {
      }      
      return list;
   }

    @FXML
    private void ChercherClient(KeyEvent event) {
        int nb;
        numCli.setCellValueFactory(new PropertyValueFactory<>("numClient"));
        nomCli.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
      ObservableList<Client> list = FXCollections.observableArrayList();        
        list=getClientSearchData();
      tableClient.setItems(list);
                            editClient.setDisable(true);
                            delClient.setDisable(true);
                            addClient.setDisable(false);
         System.out.println("Chargement de la Recherche KeyEvent");
         nb=list.size();
        nombreClientNotif.setText(""+nb+"");
    }

    private void ejectBlur(AnchorPane HomeAnchorPane) {
        GaussianBlur gaussianBlur = new GaussianBlur();     
       gaussianBlur.setRadius(0);
       HomeAnchorPane.setEffect(gaussianBlur); 
         System.out.println("ejecter Blur Effect");
    }
/*/ pour le Vente Tab *********************************************
    *****************************************************************************************************
    /*/
    
    private Vente createVente(ResultSet rs) {
      Vente c = new Vente();
      try {
         c.setId(rs.getInt("num_vente"));
         c.setNumClient(rs.getString("num_client"));
         c.setNumVoiture(rs.getString("num_voiture"));
         c.setQuantité(rs.getInt("qte"));
         c.setDateVente(rs.getDate("date_vente")); 
      } catch (SQLException ex) {
      }
      return c;
   }
    
     public ObservableList<Vente> getVenteData() {
      ObservableList<Vente> list = FXCollections.observableArrayList();
      try {
         java.sql.Statement stmt1=maConnexion.ObtenirConnexion().createStatement();
         java.sql.ResultSet resultat= stmt1.executeQuery("SELECT  * from vente;");
         
         while (resultat.next()) {
            Vente p = createVente(resultat);
            list.add(p);
         }
         resultat.close();
         stmt1.close();
      } catch (SQLException ex) {
      }      
      return list;
   }
    
    @FXML
    private void loadVenteTable() {
        int nb;
        NumCliColumn.setCellValueFactory(new PropertyValueFactory<>("numClient"));
        NumVoitureColumn.setCellValueFactory(new PropertyValueFactory<>("numVoiture"));
        QteColumn.setCellValueFactory(new PropertyValueFactory<>("quantité"));
        dateVenteColumn.setCellValueFactory(new PropertyValueFactory<>("Date_Vente"));
      ObservableList<Vente> list = FXCollections.observableArrayList();        
        list=getVenteData();
        nb=list.size();
      TableVente.setItems(list);
         System.out.println("Chargement du tableau Vente");       
         
         //Initialize JfxDtaePicker
            try{
                java.sql.Statement stmt1=maConnexion.ObtenirConnexion().createStatement();
                java.sql.ResultSet resultat= stmt1.executeQuery(" SELECT  MIN(date_vente) as DateDebut, MAX(date_vente) AS DateFin from vente ;");
            if(resultat.next()){
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");                
                LocalDate datedebut=ConvertStringtoLocalDate(sdf.format(resultat.getDate("DateDebut")));
                LocalDate datefin=ConvertStringtoLocalDate(sdf.format(resultat.getDate("DateFin")));
                DateDebut.setValue(datedebut);
                DateFin.setValue(datefin);
                DateDebut1.setValue(datedebut);
                DateFin1.setValue(datefin);
            }

        }catch(Exception e){

        }
    }
    @FXML
    private void laodAddVenteFxml(MouseEvent event) {
        loadStage("/ventes_des_voitures/fxml/AddVente.fxml");
    }

    @FXML
    private void ActiverModifierEtSupprimerButtonVente(MouseEvent event) {
        /*editVente.setDisable(false);
        delVente.setDisable(false);  
        addVente.setDisable(true); */
    }

    private void loadEditVenteFxml(MouseEvent event) {
       
        addVente.setDisable(false);
        loadStage("/ventes_des_voitures/fxml/EditVente.fxml");
    }

    private void loadDeleteVenteFxml(MouseEvent event) {
        
        addVente.setDisable(false);
        loadStage("/ventes_des_voitures/fxml/DelVente.fxml");
    }
    
    public ObservableList<Vente> getVenteSearchData() {
      ObservableList<Vente> list = FXCollections.observableArrayList();
      String search= searchBarVente.getText();
      try {
         java.sql.Statement stmt1=maConnexion.ObtenirConnexion().createStatement();
         java.sql.ResultSet resultat= stmt1.executeQuery(
                 "SELECT  * from vente WHERE  num_client LIKE '%"+search+"%' OR num_voiture LIKE '%"+search+"%' OR qte LIKE '%"+search+"%' OR date_vente LIKE '%"+search+"%';");
         
         while (resultat.next()) {
            Vente p = createVente(resultat);
            list.add(p);
         }
         resultat.close();
         stmt1.close();
      } catch (SQLException ex) {
      }      
      return list;
   }

    @FXML
    private void ChercherVente(KeyEvent event) {
        int nb;
        NumCliColumn.setCellValueFactory(new PropertyValueFactory<>("numClient"));
        NumVoitureColumn.setCellValueFactory(new PropertyValueFactory<>("numVoiture"));
        QteColumn.setCellValueFactory(new PropertyValueFactory<>("quantité"));
        dateVenteColumn.setCellValueFactory(new PropertyValueFactory<>("Date_Vente"));
      ObservableList<Vente> list = FXCollections.observableArrayList();        
        list=getVenteSearchData();
      TableVente.setItems(list);
                            
                            addVente.setDisable(false);
         System.out.println("Chargement de la Recherche KeyEvent");
         nb=list.size();
    }

    @FXML
    private void laodVenteFxmlByContextMenu(ActionEvent event) {
         loadStage("/ventes_des_voitures/fxml/AddVente.fxml");
    }

    @FXML
    private void loadEditVenteFxmlByContextMenu(ActionEvent event) {
       
        addVente.setDisable(false);
        loadStage("/ventes_des_voitures/fxml/EditVente.fxml");
    }

    @FXML
    private void loadDeleteVenteFxmlByContextMenu(ActionEvent event) {
        
        addVente.setDisable(false);
        loadStage("/ventes_des_voitures/fxml/DelVente.fxml");
    }
    
    public ObservableList<Vente> getListeEntreDeuxDateData() {
      ObservableList<Vente> list = FXCollections.observableArrayList();
      LocalDate date1= DateDebut.getValue();
      LocalDate date2= DateFin.getValue();
      try {
         java.sql.Statement stmt1=maConnexion.ObtenirConnexion().createStatement();
         java.sql.ResultSet resultat= stmt1.executeQuery(
                 "SELECT  * from vente WHERE  date_vente BETWEEN '"+date1+"' AND '"+date2+"';");
         
         while (resultat.next()) {
            Vente p = createVente(resultat);
            list.add(p);
         }
         resultat.close();
         stmt1.close();
      } catch (SQLException ex) {
      }      
      return list;
   }

    @FXML
    private void ListeVenteEntreDeuxDate() {
        int nb;
        NumCliColumn.setCellValueFactory(new PropertyValueFactory<>("numClient"));
        NumVoitureColumn.setCellValueFactory(new PropertyValueFactory<>("numVoiture"));
        QteColumn.setCellValueFactory(new PropertyValueFactory<>("quantité"));
        dateVenteColumn.setCellValueFactory(new PropertyValueFactory<>("Date_Vente"));
      ObservableList<Vente> list = FXCollections.observableArrayList();        
        list=getListeEntreDeuxDateData();
      TableVente.setItems(list);
                            
                            addVente.setDisable(false);
         System.out.println("Chargement de la Recherche Entre deux date");
         nb=list.size();
    }
    /*********************************************************************************
     * *************************** Edition de la Facture *****************************
     */
    
    private Facture createFacture(ResultSet rs) {
      Facture c = new Facture();
      try {
         c.setNumVoiture(rs.getString("num_voiture"));
         c.setMarque(rs.getString("marque"));
         c.setPU(rs.getDouble("pu"));
         c.setQuantité(rs.getInt("qte"));
         c.setMontant(rs.getDouble("Montant"));
      } catch (SQLException ex) {
      }
      return c;
   }
    
     public ObservableList<Facture> getFactureData() {
      ObservableList<Facture> list = FXCollections.observableArrayList();
      try { 
         
         String num=NumClientF.getText();
         String requete="SELECT vente.num_voiture, voiture.marque, voiture.pu, vente.qte, (voiture.pu*vente.qte) AS Montant FROM client, voiture, vente WHERE (vente.num_client=client.num_client) AND (vente.num_voiture=voiture.num_voiture) AND (vente.num_client='"+num+"');";
          java.sql.Statement stmt1=maConnexion.ObtenirConnexion().createStatement();
         java.sql.ResultSet resultat= stmt1.executeQuery(requete);         
         Double Total = 0.00;
         while (resultat.next()) {
            Facture p = createFacture(resultat);            
         Total=Total+resultat.getDouble("Montant");
            list.add(p);
         }         
      MontantTotal.setText(""+Total+" Ariary");
      RuleBasedNumberFormat rbnf = new RuleBasedNumberFormat(Locale.FRANCE, RuleBasedNumberFormat.SPELLOUT);
 	MontantEnLettre.setText(""+rbnf.format(Total)+" Ariary");
         resultat.close();
         stmt1.close();
      } catch (SQLException ex) {
      }      
      return list;
   }
    
    @FXML
    private void loadFactureTable() {
        int nb;
        NumVoitureF.setCellValueFactory(new PropertyValueFactory<>("numVoiture"));
        MarqueVoitureF.setCellValueFactory(new PropertyValueFactory<>("marque"));
        puF.setCellValueFactory(new PropertyValueFactory<>("PU"));
        qteVoitureF.setCellValueFactory(new PropertyValueFactory<>("quantité"));
        MontantF.setCellValueFactory(new PropertyValueFactory<>("Montant"));
      ObservableList<Facture> list = FXCollections.observableArrayList();        
        list=getFactureData();
        nb=list.size();
      TableFacture.setItems(list);
      
       //Pour activer PDF boutton
       PrintBtn.setDisable(true);
         System.out.println("Chargement du tableau Facture");
         //Afficher nom Client
            try{
                String num=NumClientF.getText();
                java.sql.Statement stmt1=maConnexion.ObtenirConnexion().createStatement();
                java.sql.ResultSet resultat= stmt1.executeQuery(" SELECT nom from client where num_client='"+num+"';");
            if(resultat.next()){
                NomClient.setText(resultat.getString("nom"));
                //Pour activer PDF boutton
                PrintBtn.setDisable(false);
            }

        }catch(Exception e){

        }        
 }
    /**/
    /*********************************************************************************
     * *************************** Edition du Chiffre d'affaire par Client dans une Année *****************************
     */
    
    private Chiffres_Affaires createChiffreAffaire(ResultSet rs) {
      Chiffres_Affaires c = new Chiffres_Affaires();
      try {
         c.setNumClientCH(rs.getString("num_client"));
         c.setNomClientCH(rs.getString("nom"));
         c.setC_H(rs.getDouble("chiffre_affaire"));
      } catch (SQLException ex) {
      }
      return c;
   }
    private PieChart.Data createPieChart(ResultSet data) throws SQLException{
        PieChart.Data slice = new PieChart.Data(data.getString("nom"), data.getDouble("chiffre_affaire"));
        pieChartCH.getData().add(slice);            
        return slice;
    }
     public ObservableList<Chiffres_Affaires> getChiffreAffaireData() {
      ObservableList<Chiffres_Affaires> list = FXCollections.observableArrayList();
      try { 
         
         String an=ANNEE.getText();
         if(an.length() == 4){
         String requete="SELECT client.num_client, client.nom, (vente.qte*voiture.pu) As chiffre_affaire from client, voiture, vente WHERE (client.num_client=vente.num_client) AND (voiture.num_voiture=vente.num_voiture) AND vente.date_vente BETWEEN '"+an+"-01-01' And '"+an+"-12-31';"; 
         java.sql.Statement stmt1=maConnexion.ObtenirConnexion().createStatement();
         java.sql.ResultSet resultat= stmt1.executeQuery(requete);         
         Double Total = 0.00;
         while (resultat.next()) {
            Chiffres_Affaires p = createChiffreAffaire(resultat);  
            PieChart.Data slice = createPieChart(resultat);
         Total=Total+resultat.getDouble("Chiffre_Affaire");
            list.add(p); 
         }         
      montantlbl1.setText(""+Total+" AR");
         resultat.close();
         stmt1.close();         
         } else pieChartCH.getData().clear();
      } catch (SQLException ex) {
      }      
      return list;
   }
    
    @FXML
    private void loadChiffreAffaireTable() {
        numCliChiffre.setCellValueFactory(new PropertyValueFactory<>("numClientCH"));
        nomCliChiffre.setCellValueFactory(new PropertyValueFactory<>("nomClientCH"));
        ChiffreAffaire.setCellValueFactory(new PropertyValueFactory<>("C_H"));
      ObservableList<Chiffres_Affaires> list = FXCollections.observableArrayList();        
        list=getChiffreAffaireData();
      Chiffres_AffairesTbl.setItems(list);
         System.out.println("Chargement du tableau Chiffre D' Affaires");
         
                
 }
    /**/
    /*********************************************************************************
     * *************************** Edition du Chiffre d'affaire par Client dans une Année *****************************
     */
    
    private Stock createStock(ResultSet rs) {
      Stock c = new Stock();
      try {
         c.setNomClient(rs.getString("nom"));
         c.setQuantité(rs.getInt("qte"));
         c.setDateVente(rs.getDate("date_vente"));
      } catch (SQLException ex) {
      }
      return c;
   }
     public ObservableList<Stock> getStockData() {
      ObservableList<Stock> list = FXCollections.observableArrayList();
      try { 
         
         String num=ProdNum.getText();
         String requete="SELECT client.nom, vente.qte,vente.date_vente from client, voiture, vente WHERE (client.num_client=vente.num_client) AND (voiture.num_voiture=vente.num_voiture) AND vente.num_voiture='"+num+"';"; 
         java.sql.Statement stmt1=maConnexion.ObtenirConnexion().createStatement();
         java.sql.ResultSet resultat= stmt1.executeQuery(requete);
         while (resultat.next()) {
            Stock p = createStock(resultat);  
            list.add(p);
         }         
         resultat.close();
         stmt1.close();
      } catch (SQLException ex) {
      }      
      return list;
   }
    
    @FXML
    private void loadStockTable() {
        loadVenteTable();
        int nb;
        ClientNom.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
        QTEColumn.setCellValueFactory(new PropertyValueFactory<>("quantité"));
        date_VenteC.setCellValueFactory(new PropertyValueFactory<>("Date_Vente"));
      ObservableList<Stock> list = FXCollections.observableArrayList();        
        list=getStockData(); 
            nb=list.size();
      StockTable.setItems(list);
         System.out.println("Chargement du tableau Stock");
        ChiffreLBL.setText(""+nb+"");
         //Afficher Marque et Etat Actuel
            try{
                String num=ProdNum.getText();
                java.sql.Statement stmt1=maConnexion.ObtenirConnexion().createStatement();
                java.sql.ResultSet resultat= stmt1.executeQuery(" SELECT marque, stock from voiture where num_voiture='"+num+"';");
            if(resultat.next()){MarqueLBL.setText(resultat.getString("marque"));EtatLBL.setText(resultat.getString("stock"));}

        }catch(Exception e){

        }  
                
 }
    
public ObservableList<Stock> getStockDataEntreDeuxDate() {
      ObservableList<Stock> list = FXCollections.observableArrayList();
      try { 
         LocalDate dateD=DateDebut1.getValue();
         LocalDate dateF=DateFin1.getValue();
         String num=ProdNum.getText();
         System.out.println("Chargement du tableau Stock: "+num+" "+dateD+"  "+dateF+"");
         String requete="SELECT client.nom, vente.qte,vente.date_vente from client, voiture, vente WHERE (client.num_client=vente.num_client) AND (voiture.num_voiture=vente.num_voiture) AND vente.num_voiture='"+num+"' AND vente.date_vente BETWEEN '"+dateD+"' and '"+dateF+"';"; 
         java.sql.Statement stmt1=maConnexion.ObtenirConnexion().createStatement();
         java.sql.ResultSet resultat= stmt1.executeQuery(requete);
         while (resultat.next()) {
            Stock p = createStock(resultat);  
            list.add(p);
         }         
         resultat.close();
         stmt1.close();
      } catch (SQLException ex) {
      }      
      return list;
   }
    @FXML
    private void ListeEtatStockEntreDeuxDate(ActionEvent event) {
         int nb;
        ClientNom.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
        QTEColumn.setCellValueFactory(new PropertyValueFactory<>("quantité"));
        date_VenteC.setCellValueFactory(new PropertyValueFactory<>("Date_Vente"));
      ObservableList<Stock> list = FXCollections.observableArrayList();        
        list=getStockDataEntreDeuxDate(); 
            nb=list.size();
      StockTable.setItems(list);
         System.out.println("Chargement du tableau Stock");
        ChiffreLBL.setText(""+nb+"");
         //Afficher Marque et Etat Actuel
            try{
                String num=ProdNum.getText();
                java.sql.Statement stmt1=maConnexion.ObtenirConnexion().createStatement();
                java.sql.ResultSet resultat= stmt1.executeQuery(" SELECT marque, stock from voiture where num_voiture='"+num+"';");
            if(resultat.next()){MarqueLBL.setText(resultat.getString("marque"));EtatLBL.setText(resultat.getString("stock"));}

        }catch(Exception e){

        }  
    }
    
    /*************************************************************************
     * **************************PDF creater with ****************************
     * ************************** Itextpdf.jar********************************
     */
    
   
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD, BaseColor.BLUE);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    
     
    @FXML
    private void PrintFactureToPDF(MouseEvent event) {
        try {
            String i=num_Facture.getText();    
            if (i.equals("")){ 
                numFactINFO.setVisible(true); 
            }
            else {                
            //int num=Integer.parseInt(i);
             String FILE = "C:/Users/"+System.getProperty("user.name")+"/Documents/Facture_"+i+".pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addContent(document);
            document.close();
            numFactINFO.setVisible(false);
            FactureNotification();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void FactureNotification(){
        String i=num_Facture.getText();
        String FILE = "C:/Users/"+System.getProperty("user.name")+"/Documents/Facture_"+i+".pdf";
        /*JFXDialogLayout content= new JFXDialogLayout();*/
        DialogLayout.setHeading(new Text("Notification"));
        DialogLayout.setBody(new Text("Votre facture est prête!!! \n Et son emplacement est dans "+FILE+" de votre ordinateur."));
        JFXDialog dialog=new JFXDialog(StackPaneVente, DialogLayout, JFXDialog.DialogTransition.CENTER);
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
    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private static void addMetaData(Document document) {
        document.addTitle("Facture");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Marsihay");
        document.addCreator("Marsihay");
    }

    private void addContent(Document document) throws DocumentException {        
        Anchor anchor = new Anchor("Facture", catFont);
        anchor.setName("Facture");
        
        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);
        String i=num_Facture.getText();
        LocalDate date= dateFacture.getValue();
        Paragraph subPara = new Paragraph("Facture numéro "+i+" du "+ new Date(), subFont);
        Section subCatPart = catPart.addSection(subPara);
        
        //Afficher nom Client su le PDF
            try{
                String num=NumClientF.getText();
                if (num.equals("")){ 
                //Pour activer PDF boutton
                PrintBtn.setDisable(true); 
                }
                else { 
                    //Pour activer PDF boutton
                    PrintBtn.setDisable(false);
                    
                java.sql.Statement stmt1=maConnexion.ObtenirConnexion().createStatement();
                java.sql.ResultSet resultat= stmt1.executeQuery(" SELECT nom from client where num_client='"+num+"';");
            if(resultat.next()){
                subCatPart.add(new Paragraph("-Client : "+resultat.getString("nom")));
                subCatPart.add(new Paragraph("-Son numéro : "+num));
                subCatPart.add(new Paragraph("-Date : "+ date));
                }
             }
        }catch(Exception e){

        }  
       
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 2);
        subCatPart.add(paragraph);
        // add a table
        createTable(subCatPart);
        
        subCatPart.add(new Paragraph("ARRETEE LA PRESENTE FACTURE A LA SOMME DE :"));
        subCatPart.add(new Paragraph(MontantEnLettre.getText(), redFont));
        // now add all this to the document
        document.add(catPart);

    }

    private void createTable(Section subCatPart)
            throws BadElementException {
        PdfPTable table = new PdfPTable(5);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        //t.setSpacing(4);
        // t.setBorderWidth(1);

        PdfPCell c1 = new PdfPCell(new Phrase("Num_Voiture"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Marque"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Prix Unitaire"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Quantité"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Montant"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);
        
         try{
                String num=NumClientF.getText();
                java.sql.Statement stmt1=maConnexion.ObtenirConnexion().createStatement();
                String requete="SELECT vente.num_voiture, voiture.marque, voiture.pu, vente.qte, (voiture.pu*vente.qte) AS Montant FROM client, voiture, vente WHERE (vente.num_client=client.num_client) AND (vente.num_voiture=voiture.num_voiture) AND (vente.num_client='"+num+"');";
                 java.sql.ResultSet rs= stmt1.executeQuery(requete);         
                Double Total = 0.00;
                while (rs.next()) {
                    table.addCell(rs.getString("num_voiture"));
                    table.addCell(rs.getString("marque"));
                    table.addCell(""+rs.getDouble("pu")+" Ar");
                    table.addCell(""+rs.getInt("qte")+"");
                    table.addCell(""+rs.getDouble("Montant")+" Ar");
                    Total=Total+rs.getDouble("Montant");
                } 
                    table.addCell("");
                    table.addCell("");
                    table.addCell("");
                    table.addCell("");
                    table.addCell(""+Total+" Ar");
         }
         catch (Exception e){}

        subCatPart.add(table);

    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    
}
