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
public class Voiture {
    
    private SimpleStringProperty numVoiture;
    private SimpleStringProperty marque;
    private SimpleDoubleProperty PU;
    private SimpleIntegerProperty stock;

    
    public Voiture() {
   }
   
    public String getNumVoiture() {
        return numVoiture.get();
    }

    public void setNumVoiture(String numV) {
        this.numVoiture = new SimpleStringProperty(numV);
    }

    public String getMarque() {
        return marque.get();
    }

    public void setMarque(String marque) {
        this.marque = new SimpleStringProperty(marque);
    }

    public double getPU() {
        return PU.get();
    }

    public void setPU(double pu) {
        this.PU = new SimpleDoubleProperty(pu);
    }
    
    public int getStock() {
        return stock.get();
    }

    public void setStock(int stock) {
        this.stock = new SimpleIntegerProperty(stock);
    }
}
