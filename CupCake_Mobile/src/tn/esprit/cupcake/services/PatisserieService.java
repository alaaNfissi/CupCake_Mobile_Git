/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import tn.esprit.cupcake.entities.Client;
import tn.esprit.cupcake.entities.Patisserie;
import static tn.esprit.cupcake.services.UtilisateurService.user;

/**
 *
 * @author Alaa
 */
public class PatisserieService {
	public static Patisserie patisserie;
		public void createPatisserie(Patisserie p) {
		ConnectionRequest con = new ConnectionRequest();
		con.setPost(true);
		String Url ="http://localhost/CupCake_Web_VF-master/web/app_dev.php/api/createPatisserie?"; 
		Url+="libelle_patisserie="+p.getLibelle_patisserie();
		Url+="&adresse_patisserie="+p.getAdresse_patisserie();
		Url+="&date_creation="+p.getDate_creation();
		Url+="&specialite="+p.getSpecialite();
		Url+="&compte_facebook="+p.getCompte_facebook();
		Url+="&compte_instagram="+p.getCompte_instagram();
		Url+="&description="+p.getDescription();
		Url+="&image="+p.getImage();
		Url+="&idp="+UtilisateurService.user.getId();
		con.setUrl(Url);
        System.out.println("tt");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
		patisserie=p;
	}
}
