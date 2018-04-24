/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import tn.esprit.cupcake.CupCake;
import tn.esprit.cupcake.services.UtilisateurService;

/**
 *
 * @author Alaa
 */
public class LoginGui {
	Form f ;
	TextField username ;
	TextField password;
	Button loginBtn;
	Button fbBtn;
	Button registerBtn;
	
	public LoginGui()
	{
		f = new Form("Login",BoxLayout.y());
		username = new TextField();
		username.setHint("username");
		password = new TextField();
		password.setHint("Password");
		loginBtn = new Button("Login");
		fbBtn = new Button("Sign in/up with facebook");
		registerBtn = new Button("Create an account");
		
		f.add(username);
		f.add(password);
		f.add(loginBtn);
		f.add(fbBtn);
		f.add(registerBtn);
		loginBtn.addActionListener((e)->{
			UtilisateurService us=new UtilisateurService();
			us.LoggedUser(username.getText(), password.getText());
			if (UtilisateurService.user != null) {
				Form FL=new Form(UtilisateurService.user.getUsername());
				FL.show();
			}
		});
	}

	public Form getF() {
		return f;
	}

	public void setF(Form f) {
		this.f = f;
	}

	public TextField getUsername() {
		return username;
	}

	public void setUsername(TextField username) {
		this.username = username;
	}

	public TextField getPassword() {
		return password;
	}

	public void setPassword(TextField password) {
		this.password = password;
	}

	public Button getLoginBtn() {
		return loginBtn;
	}

	public void setLoginBtn(Button loginBtn) {
		this.loginBtn = loginBtn;
	}

	public Button getFbBtn() {
		return fbBtn;
	}

	public void setFbBtn(Button fbBtn) {
		this.fbBtn = fbBtn;
	}

	public Button getRegisterBtn() {
		return registerBtn;
	}

	public void setRegisterBtn(Button registerBtn) {
		this.registerBtn = registerBtn;
	}
	
}
