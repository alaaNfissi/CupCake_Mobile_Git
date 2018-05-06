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
import java.util.Date;
import java.util.Map;
import tn.esprit.cupcake.entities.Commande;
import tn.esprit.cupcake.entities.Livraison;
import tn.esprit.cupcake.entities.Patisserie;
import tn.esprit.cupcake.entities.Utilisateur;
import static tn.esprit.cupcake.services.PatisserieService.patisserie;

/**
 *
 * @author Alaa
 */
public class LivraisonService {
	public static Livraison l;
	public Livraison getLivraison(String json, Commande c) {
		Livraison l = new Livraison();
		Livraison lf = new Livraison();
		try {
			System.out.println(json);
			JSONParser j = new JSONParser();

			Map<String, Object> livraisons = j.parseJSON(new CharArrayReader(json.toCharArray()));
			System.out.println(livraisons);
			//System.out.println(patisseries.get("idPatisserie").toString());
			l.setId_livraison((int) Double.parseDouble(livraisons.get("idLivraison").toString().trim()));
			l.setEtat_livraison((int) Double.parseDouble(livraisons.get("etatLivraison").toString().trim()));
			//l.setEtatLivraison(json);
			l.setDate_livraison(null);
			l.setPrix_livraison(Float.parseFloat(livraisons.get("prixLivraison").toString().trim()));
			l.setId_commande(c.getId_commande());
			lf = new Livraison(l.getId_livraison(),l.getDate_livraison(),l.getPrix_livraison(),l.getEtat_livraison(),l.getEtatLivraison(),l.getId_commande());
			/*p.setId_patisserie((int)Double.parseDouble(patisseries.get("idPatisserie").toString().trim()));
			p.setLibelle_patisserie(patisseries.get("libellePatisserie").toString());
			p.setAdresse_patisserie(patisseries.get("adressePatisserie").toString());
			p.setDate_creation(null);
			p.setSpecialite(patisseries.get("specialite").toString());
			p.setCompte_facebook(patisseries.get("compteFacebook").toString());
			p.setCompte_facebook(patisseries.get("compteInstagram").toString());
			p.setDescription(patisseries.get("description").toString());
			p.setImage(patisseries.get("image").toString());*/
 /*UtilisateurService us= new UtilisateurService();
			Utilisateur u = new Utilisateur();
			u=UtilisateurService.user;
			//u=us.getListUtilisateur(patisseries.get("utilisateur").toString());
			p.setPatissier(u.getId());*/

			System.out.println(lf);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return lf;
	}

	public Livraison getLivraisonByIdCmd(Commande c) {
		ConnectionRequest con = new ConnectionRequest();
		con.setPost(true);
		String Url = "http://localhost/CupCake_Web_VF-master/web/app_dev.php/api/getLivraisonByIdCmd?idCmd=" + c.getId_commande();
		con.setUrl(Url);
		System.out.println("tt");
		con.addResponseListener((e) -> {
			String str = new String(con.getResponseData());
			LivraisonService.l = getLivraison(str,c);
			System.out.println(str);
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		System.out.println(l.toString());
		return l;
	}
	
	public void changerEtatLivraison(Commande c,int etat)
	{
		ConnectionRequest con = new ConnectionRequest();
		con.setPost(true);
		String Url = "http://localhost/CupCake_Web_VF-master/web/app_dev.php/api/changerEtatLivraison?id=" + c.getId_commande()+"&etat="+etat;
		con.setUrl(Url);
		System.out.println("tt");
		con.addResponseListener((e) -> {
			String str = new String(con.getResponseData());
			//LivraisonService.l = getLivraison(str,c);
			System.out.println(str);
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		//System.out.println(l.toString());
	}
	
	public void ajouterLivraison(int idCommande,float prix,Date dateLivraison,int etatLivraison)
	{
		ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/CupCake_Web_VF-master/web/app_dev.php/api/ajouterLivraison?id_commande="+idCommande+"&prix_livraison="+prix+"&date_livraison="+dateLivraison+"&etat_livraison="+etatLivraison;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
	}
}
