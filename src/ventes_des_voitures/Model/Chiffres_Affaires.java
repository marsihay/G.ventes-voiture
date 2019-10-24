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
public class Chiffres_Affaires {
    private SimpleStringProperty numClientCH;
    private SimpleStringProperty nomClientCH;
    private SimpleDoubleProperty C_H;

    
    public Chiffres_Affaires() {
   }
   
    public String getNumClientCH() {
        return numClientCH.get();
    }

    public void setNumClientCH(String numV) {
        this.numClientCH = new SimpleStringProperty(numV);
    }

    public String getNomClientCH() {
        return nomClientCH.get();
    }

    public void setNomClientCH(String marque) {
        this.nomClientCH = new SimpleStringProperty(marque);
    }

    public double getC_H() {
        return C_H.get();
    }

    public void setC_H(double pu) {
        this.C_H = new SimpleDoubleProperty(pu);
    }
    
}
