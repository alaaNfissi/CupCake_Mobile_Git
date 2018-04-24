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
import tn.esprit.cupcake.entities.Utilisateur;

/**
 *
 * @author Alaa
 */
public class UtilisateurService {

	public static Utilisateur user;

	public void login(String username, String pw) {
		ConnectionRequest cnx;
		cnx = new ConnectionRequest("http://localhost/CupCake_Web_VF-master/web/app_dev.php/api/LoginUser?un=" + username + "&pw=" + pw) {
			int ch;
			StringBuffer str;

			@Override
			protected void readResponse(InputStream input) throws IOException {
				str = new StringBuffer();
				while ((ch = input.read()) != -1) {
					str.append((char) ch);
				}
			}

			@Override
			protected void postResponse() {
				if (str.toString() != null) {
					System.out.println(str.toString());
					int id = Integer.parseInt(str.toString().substring(2));
					//getUser(id);
					System.out.println(id);

				} else {
					Dialog.show(str.toString(), "", "retry", null);
					System.out.println(str.toString());

				}
			}

		};
		NetworkManager.getInstance().addToQueue(cnx);
	}

	public Utilisateur getListUtilisateur(String json) {

		//ArrayList<Utilisateur> ListUtilisateurs = new ArrayList<>();
		Utilisateur u = new Utilisateur();
		try {
			System.out.println(json);
			JSONParser j = new JSONParser();

			Map<String, Object> utilisateurs = j.parseJSON(new CharArrayReader(json.toCharArray()));
			System.out.println(utilisateurs);

			//List<Map<String, Object>> list = (List<Map<String, Object>>) utilisateurs;

			//for (Map<String, Object> obj : utilisateurs) {
				

				// System.out.println(obj.get("id"));
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
				ArrayList<String> roles= new ArrayList<>();
				roles=(ArrayList < String >)utilisateurs.get("roles");
				System.out.println(roles.get(0).toString()+"/"+roles.get(1).toString());
				u.setRoles(roles.get(0).toString()+"/"+roles.get(1).toString());
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
				/*ListUtilisateurs.add(u);
			}*/

		} catch (IOException ex) {
		}
		//System.out.println(ListUtilisateurs);
		return u;
	}
	//ArrayList<Utilisateur> listUtilisateurs = new ArrayList<>();

	public void LoggedUser(String username, String pw) {
		//Utilisateur u = new Utilisateur();
		ConnectionRequest con = new ConnectionRequest();
		con.setUrl("http://localhost/CupCake_Web_VF-master/web/app_dev.php/api/LoginUser?un=" + username + "&pw=" + pw);
		con.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
				UtilisateurService us = new UtilisateurService();
				user = us.getListUtilisateur(new String(con.getResponseData()));
			}
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		//return listUtilisateurs;
	}
}
