/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventes_des_voitures.Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Marsihay
 */
public class Client {
    private SimpleStringProperty numClient;
    private SimpleStringProperty nomClient;

    
    public Client() {
   }
   
    public String getNumClient() {
        return numClient.get();
    }

    public void setNumClient(String numV) {
        this.numClient = new SimpleStringProperty(numV);
    }

    public String getNomClient() {
        return nomClient.get();
    }

    public void setMarque(String marque) {
        this.nomClient = new SimpleStringProperty(marque);
    }

}
