/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import tn.esprit.cupcake.entities.Client;
import tn.esprit.cupcake.entities.Patisserie;
import tn.esprit.cupcake.entities.Utilisateur;
import static tn.esprit.cupcake.services.UtilisateurService.user;

/**
 *
 * @author Alaa
 */
public class PatisserieService {
	public static Patisserie patisserie;
	
		public Patisserie getLoggedPatisserie(String json) {
		Patisserie p = new Patisserie();
		try {
			System.out.println(json);
			JSONParser j = new JSONParser();

			Map<String, Object> patisseries = j.parseJSON(new CharArrayReader(json.toCharArray()));
			System.out.println(patisseries);
			//System.out.println(patisseries.get("idPatisserie").toString());
			p.setId_patisserie((int)Double.parseDouble(patisseries.get("idPatisserie").toString().trim()));
			p.setLibelle_patisserie(patisseries.get("libellePatisserie").toString());
			p.setAdresse_patisserie(patisseries.get("adressePatisserie").toString());
			p.setDate_creation(null);
			p.setSpecialite(patisseries.get("specialite").toString());
			p.setCompte_facebook(patisseries.get("compteFacebook").toString());
			p.setCompte_facebook(patisseries.get("compteInstagram").toString());
			p.setDescription(patisseries.get("description").toString());
			p.setImage(patisseries.get("image").toString());
			UtilisateurService us= new UtilisateurService();
			Utilisateur u = new Utilisateur();
			u=UtilisateurService.user;
			//u=us.getListUtilisateur(patisseries.get("utilisateur").toString());
			p.setPatissier(u.getId());
	
			System.out.println(p);

		} catch (IOException ex) {
		}
		return p;
	}
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
		Url+="&idp="+p.getPatissier();
		con.setUrl(Url);
        System.out.println("tt");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
			PatisserieService ps=new PatisserieService();
			PatisserieService.patisserie=ps.getLoggedPatisserie(str);
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
			System.out.println(patisserie);
	}
}
