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
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import tn.esprit.cupcake.entities.Client;
import tn.esprit.cupcake.entities.Patissier;
import tn.esprit.cupcake.entities.Utilisateur;

/**
 *
 * @author Alaa
 */
public class UtilisateurService {

	public static Utilisateur user;

	public Utilisateur getListUtilisateur(String json) {
		Utilisateur u = new Utilisateur();
		try {
			System.out.println(json);
			JSONParser j = new JSONParser();

			Map<String, Object> utilisateurs = j.parseJSON(new CharArrayReader(json.toCharArray()));
			System.out.println(utilisateurs);
			//float id = Float.parseFloat(obj.get("id").toString());
			//System.out.println(id);
			u.setId(Long.parseLong(utilisateurs.get("id").toString().trim()));
			//e.setId(Integer.parseInt(obj.get("id").toString().trim()));
			u.setNom(utilisateurs.get("nom").toString());
			u.setPrenom(utilisateurs.get("prenom").toString());
			u.setNum_tel(0);
			//Integer.parseInt(utilisateurs.get("numTel").toString().trim())
			u.setDate_naissance(null);
			//(Date) utilisateurs.get("dateNaissance")
			ArrayList<String> roles = new ArrayList<>();
			roles = (ArrayList< String>) utilisateurs.get("roles");
			System.out.println(roles.get(0).toString() + "/" + roles.get(1).toString());
			u.setRoles(roles.get(0).toString() /*+ "/" + roles.get(1).toString()*/);
			//u.setSalt(utilisateurs.get("salt"));
			u.setEmail(utilisateurs.get("email").toString());
			u.setEmail_canonical(utilisateurs.get("emailCanonical").toString());
			u.setUsername(utilisateurs.get("username").toString());
			u.setUsername_canonical(utilisateurs.get("usernameCanonical").toString());
//				u.setConfirmation_token(utilisateurs.get("confirmationToken").toString());
			u.setPassword_requested_at(null);
			//(Date) utilisateurs.get("passwordRequestedAt")
			u.setLast_login(null);
			//(Date) utilisateurs.get("lastLogin")
			u.setEnabled(utilisateurs.get("enabled").toString());
			u.setPassword(utilisateurs.get("password").toString());
			System.out.println(u);

		} catch (IOException ex) {
		}
		return u;
	}

	public void LoggedUser(String username, String pw) {
		ConnectionRequest con = new ConnectionRequest();
		con.setUrl("http://localhost/CupCake_Web_VF-master/web/app_dev.php/api/LoginUser?un=" + username + "&pw=" + pw);
		con.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
				UtilisateurService us = new UtilisateurService();
				user = us.getListUtilisateur(new String(con.getResponseData()));
				if (user.getRoles().equals("ROLE_PATISSIER")) {
					PatisserieService ps= new PatisserieService();
					ps.getPatisserieUser(user);
				}
			}
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
	}

	public void addUser(Client u) {
		ConnectionRequest con = new ConnectionRequest();
		con.setPost(true);
		String Url ="http://localhost/CupCake_Web_VF-master/web/app_dev.php/api/registerUser?"; 
		Url+="un="+u.getUsername();
		Url+="&email="+u.getEmail();
		Url+="&pw="+u.getPassword();
		Url+="&Ntel="+u.getNum_tel();
		Url+="&nom="+u.getNom();
		Url+="&prenom="+u.getPrenom();
		Url+="&dateN="+u.getDate_naissance();
		Url+="&adresse="+u.getAdresse();
		Url+="&sexe="+u.getSexe();
		Url+="&image="+u.getImage();
		Url+="&roles="+u.getRoles();
		con.setUrl(Url);
        System.out.println("tt");
        /*con.addResponseListener((e) -> {
			UtilisateurService us = new UtilisateurService();
            String str = new String(con.getResponseData());
			user = us.getListUtilisateur(new String(con.getResponseData()));
            System.out.println(str);
        });*/
		con.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
				String str = new String(con.getResponseData());
				UtilisateurService us = new UtilisateurService();
				UtilisateurService.user = us.getListUtilisateur(str);
				System.out.println(str);
			}
		});
        NetworkManager.getInstance().addToQueueAndWait(con);
		System.out.println(user);
	}
}
