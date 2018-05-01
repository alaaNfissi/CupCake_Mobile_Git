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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
/*import java.text.ParseException;
import java.text.SimpleDateFormat;*/
import java.util.ArrayList;
import java.util.List;
//import java.util.Locale;
import java.util.Map;
//import tn.esprit.cupcake.entities.Client;
/*import java.util.logging.Level;
import java.util.logging.Logger;*/
import tn.esprit.cupcake.entities.Commande;
import tn.esprit.cupcake.entities.Patisserie;
import tn.esprit.cupcake.entities.Utilisateur;

/**
 *
 * @author Alaa
 */
public class CommandeService {

	public ArrayList<Commande> ListCommandesPatisserie(String json){
		ArrayList<Commande> listCommandes = new ArrayList<>();

		try {
			System.out.println(json);
			JSONParser j = new JSONParser();

			Map<String, Object> commandes = j.parseJSON(new CharArrayReader(json.toCharArray()));
			System.out.println(commandes);

			List<Map<String, Object>> list = (List<Map<String, Object>>) commandes.get("root");

			for (Map<String, Object> obj : list) {
				Commande c = new Commande();
				float id = Float.parseFloat(obj.get("idCommande").toString());
				System.out.println(id);

				c.setId_commande((int) id);
				c.setNum_commande(Integer.parseInt(obj.get("numCommande").toString().trim()));
				c.setPrix_totale(Float.parseFloat(obj.get("prixTotale").toString()));
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String date = obj.get("dateCommande").toString();
				c.setDate_commande(formatter.parse(date));
				//c.setDate_commande(null);
				c.setId_panier(0);
				c.setLibelle_patisserie(null);
				//System.out.println();
				listCommandes.add(c);

			}

		} catch (IOException ex) {
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		System.out.println(listCommandes);
		return listCommandes;
	}

	ArrayList<Commande> listCommandes = new ArrayList<>();

	public ArrayList<Commande> getListCommande(Patisserie p) {
		ConnectionRequest con = new ConnectionRequest();
		con.setUrl("http://localhost/CupCake_Web_VF-master/web/app_dev.php/api/getCommandesPatisserie?idPatisserie=" + p.getId_patisserie());
		con.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
				CommandeService cs = new CommandeService();
				listCommandes = cs.ListCommandesPatisserie(new String(con.getResponseData()));
			}
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		return listCommandes;
	}
	
	public ArrayList<Commande> getListCommandeUser(Utilisateur  u) {
		ConnectionRequest con = new ConnectionRequest();
		con.setUrl("http://localhost/CupCake_Web_VF-master/web/app_dev.php/api/getCommandeUser?idU=" +u.getId());
		con.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
				CommandeService cs = new CommandeService();
				listCommandes = cs.ListCommandesPatisserie(new String(con.getResponseData()));
			}
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		return listCommandes;
	}
}
