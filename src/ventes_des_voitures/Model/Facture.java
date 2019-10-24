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
public class Facture {
    private SimpleStringProperty numVoiture;
    private SimpleStringProperty marque;
    private SimpleDoubleProperty PU;
    private SimpleIntegerProperty quantité;
    private SimpleDoubleProperty Montant;
    
     public Facture() {
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
    
    public int getQuantité() {
        return quantité.get();
    }

    public void setQuantité(int stock) {
        this.quantité = new SimpleIntegerProperty(stock);
    }
    
    public double getMontant() {
        return Montant.get();
    }

    public void setMontant(double Montant) {
        this.Montant = new SimpleDoubleProperty(Montant);
    }
}
