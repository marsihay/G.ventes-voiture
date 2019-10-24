/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventes_des_voitures.Model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Marsihay
 */
public class Vente {
    private SimpleIntegerProperty id; 
    private SimpleStringProperty numClient;
    private SimpleStringProperty numVoiture;
    private SimpleIntegerProperty quantité;
    private SimpleObjectProperty<Date> Date_Vente;

    
    public Vente() {
   }
   public Integer getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }
    public String getNumClient() {
        return numClient.get();
    }

    public void setNumClient(String numC) {
        this.numClient = new SimpleStringProperty(numC);
    }

    public String getNumVoiture() {
        return numVoiture.get();
    }

    public void setNumVoiture(String NumV) {
        this.numVoiture = new SimpleStringProperty(NumV);
    }

    public Integer getQuantité() {
        return quantité.get();
    }

    public void setQuantité(int Qte) {
        this.quantité = new SimpleIntegerProperty(Qte);
    }
    
    public String getDateVente() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(Date_Vente.get());
	}

	public void setDateVente(Date day) {
		this.Date_Vente=new SimpleObjectProperty<Date>(day);
	}
	
	public SimpleObjectProperty<Date> Date_VenteProperty() {
		return Date_Vente;
	}
}
