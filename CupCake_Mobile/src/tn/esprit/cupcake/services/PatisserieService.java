/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
			p.setId_patisserie((int) Double.parseDouble(patisseries.get("idPatisserie").toString().trim()));
			p.setLibelle_patisserie(patisseries.get("libellePatisserie").toString());
			p.setAdresse_patisserie(patisseries.get("adressePatisserie").toString());
			
			Map<String, Object> results = (Map<String, Object>) patisseries.get("dateCreation");
			Double dateCreation = (Double) results.get("timestamp");
			Date dateCreation2 = new Date((long) (dateCreation * 1000));
			p.setDate_creation(dateCreation2);
			
			p.setSpecialite(patisseries.get("specialite").toString());
			p.setCompte_facebook(patisseries.get("compteFacebook").toString());
			p.setCompte_instagram(patisseries.get("compteInstagram").toString());
			p.setDescription(patisseries.get("description").toString());
			p.setImage(patisseries.get("image").toString());
			UtilisateurService us = new UtilisateurService();
			Utilisateur u = new Utilisateur();
			u = UtilisateurService.user;
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
		String Url = "http://localhost/CupCake_Web_VF-master/web/app_dev.php/api/createPatisserie?";
		Url += "libelle_patisserie=" + p.getLibelle_patisserie();
		Url += "&adresse_patisserie=" + p.getAdresse_patisserie();
		Url += "&date_creation=" + p.getDate_creation();
		Url += "&specialite=" + p.getSpecialite();
		Url += "&compte_facebook=" + p.getCompte_facebook();
		Url += "&compte_instagram=" + p.getCompte_instagram();
		Url += "&description=" + p.getDescription();
		Url += "&image=" + p.getImage();
		Url += "&idp=" + p.getPatissier();
		con.setUrl(Url);
		System.out.println("tt");
		con.addResponseListener((e) -> {
			String str = new String(con.getResponseData());
			PatisserieService ps = new PatisserieService();
			PatisserieService.patisserie = ps.getLoggedPatisserie(str);
			System.out.println(str);
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		System.out.println(patisserie);
	}

	public void getPatisserieUser(Utilisateur u) {
		ConnectionRequest con = new ConnectionRequest();
		con.setPost(true);
		String Url = "http://localhost/CupCake_Web_VF-master/web/app_dev.php/api/getPatisserieUser?id=" + u.getId();
		con.setUrl(Url);
		System.out.println("tt");
		con.addResponseListener((e) -> {
			String str = new String(con.getResponseData());
			PatisserieService ps = new PatisserieService();
			PatisserieService.patisserie = ps.getLoggedPatisserie(str);
			System.out.println(str);
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		System.out.println(patisserie);
	}
	//************************CODDDIII RAHMA ***************************************////
	//////////***************Raaaaaaaaaaahammaaa******************///

	public void modifierPatisserie(Patisserie p) {
		ConnectionRequest con = new ConnectionRequest();
		con.setPost(true);
		String Url = "http://localhost/CupCake_Web_VF-master/web/app_dev.php/api/modifierPatisserie?";
		Url += "idPatisserie=" + p.getId_patisserie();
		Url += "&libelle=" + p.getLibelle_patisserie();
		Url += "&adresse=" + p.getAdresse_patisserie();
		Url += "&dateCreation=" + p.getDate_creation();
		Url += "&specialite=" + p.getSpecialite();
		Url += "&facebook=" + p.getCompte_facebook();
		Url += "&instagram=" + p.getCompte_instagram();
		Url += "&description=" + p.getDescription();
		Url += "&image=" + p.getImage();
		con.setUrl(Url);
		System.out.println("tt");
		con.addResponseListener((e) -> {
			String str = new String(con.getResponseData());
			System.out.println(str);
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		System.out.println(patisserie);

	}

	public Patisserie getPatisserieUser(long idUser) {
		ConnectionRequest con = new ConnectionRequest();
		con.setPost(true);
		String Url = "http://localhost/CupCake_Web_VF-master/web/app_dev.php/api/findPatByUser?id=" + idUser;
		con.setUrl(Url);
		System.out.println("tt");
		con.addResponseListener((e) -> {
			String str = new String(con.getResponseData());
			PatisserieService ps = new PatisserieService();
			PatisserieService.patisserie = ps.getLoggedPatisserie(str);
			System.out.println(str);
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		System.out.println(patisserie);
		return PatisserieService.patisserie;
	}

	public ArrayList<Patisserie> getAllPatisserie(String json) {
		ArrayList<Patisserie> listpat = new ArrayList<Patisserie>();
		try {
			System.out.println(json);
			JSONParser j = new JSONParser();

			Map<String, Object> patisseries = j.parseJSON(new CharArrayReader(json.toCharArray()));
			System.out.println(patisseries);
			List<Map<String, Object>> list = (List<Map<String, Object>>) patisseries.get("root");
			for (Map<String, Object> obj : list) {
				Patisserie p = new Patisserie();

				float idPatisserie = Float.parseFloat(obj.get("idPatisserie").toString());
				System.out.println(idPatisserie);
				p.setId_patisserie((int) idPatisserie);
				p.setLibelle_patisserie(obj.get("libellePatisserie").toString());
				p.setAdresse_patisserie(obj.get("adressePatisserie").toString());

				Map<String, Object> results = (Map<String, Object>) obj.get("dateCreation");
				Double dateCreation = (Double) results.get("timestamp");
				Date dateCreation2 = new Date((long) (dateCreation * 1000));
				p.setDate_creation(dateCreation2);

				p.setSpecialite(obj.get("specialite").toString());
				p.setCompte_facebook(obj.get("compteFacebook").toString());
				p.setCompte_instagram(obj.get("compteInstagram").toString());
				p.setDescription(obj.get("description").toString());
				p.setImage(obj.get("image").toString());
				listpat.add(p);
			}

		} catch (IOException ex) {
		}
		return listpat;
	}
	ArrayList<Patisserie> Patisseries = new ArrayList<>();

	public ArrayList<Patisserie> getAllPatisserie() {
		ConnectionRequest con = new ConnectionRequest();
		con.setUrl("http://localhost/CupCake_Web_VF-master/web/app_dev.php/api/allPats");
		con.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
				PatisserieService ser = new PatisserieService();
				Patisseries = ser.getAllPatisserie(new String(con.getResponseData()));
			}
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		return Patisseries;
	}

	//************************ CODDDIII RAHMA ***************************************////
}
