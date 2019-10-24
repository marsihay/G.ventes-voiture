/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventes_des_voitures.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.scene.control.Label;



/**
 *
 * @author Marsihay
 */
public class Connexion {
    public String notification;
    String urlPilote="com.mysql.jdbc.Driver";
    String urlBaseDonnees="jdbc:Mysql://localhost:3306/g2v";//Chemin de connexion a la base
    Connection con;
        public Connexion(){
    try{
    Class.forName(urlPilote);
    System.out.println("Chargement du pilote de réussi");
    notification="Chargement du pilote de réussi";
}catch(ClassNotFoundException ex){
    System.out.println(ex);
}
    try{
        con=DriverManager.getConnection(urlBaseDonnees,"root","");
        System.out.println("Connexion à la base de données réussi");
        notification="Connexion à la base de données réussi";
    
}catch(SQLException ex){
    System.out.println(ex);
}
}
        Connection ObtenirConnexion(){
        return con;
    }

    Statement prepareStatement(String requete) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
