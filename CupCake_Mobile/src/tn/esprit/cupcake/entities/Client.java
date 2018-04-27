/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.entities;

import java.util.Date;



/**
 *
 * @author Basly
 */
public class Client extends Utilisateur {
    private String adresse ;
    private String sexe ;
    private String image;

    public Client() {
    }

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return super.toString()+" Client{" + "adresse=" + adresse + ", sexe=" + sexe + ", image=" + image + '}';
	}
    
}
