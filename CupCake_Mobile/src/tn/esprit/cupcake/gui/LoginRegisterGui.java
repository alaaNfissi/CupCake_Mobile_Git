/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import static com.codename1.ui.CN.*;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import com.codename1.ui.layouts.BoxLayout;
/**
 *
 * @author Alaa
 */
public class LoginRegisterGui {
	private Form current;
	private Resources theme;
	private TextField pseudo;
	private TextField password;
	private Button loginBtn;
	private ConnectionRequest con;
	private String url;
	private EncodedImage encoded;
	private URLImage uRLImage;
	private ImageViewer imgV;
	private Button backBtn;
	//private String url1="http://localhost/codeNameOne/nader.jpg";

	public void init(Object context) {
		theme = UIManager.initFirstTheme("/theme");

		// Enable Toolbar on all Forms by default
		Toolbar.setGlobalToolbar(true);

		// Pro only feature
		Log.bindCrashProtection(true);
	}

	public void start() {
		if (current != null) {
			current.show();
			return;
		}
		Form hi = new Form("Hi World", BoxLayout.y());
		hi.add(new Label("Hi World"));
		pseudo = new TextField("", "Pseudo");
		password = new TextField("", "Password");
		password.setConstraint(TextField.PASSWORD);
		loginBtn = new Button("Login");
		hi.add(pseudo);
		hi.add(password);
		hi.add(loginBtn);
		con = new ConnectionRequest();
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				//url = "http://localhost/codeNameOne/login.php";//name=" + pseudo.getText() + "&password=" + password.getText();
				url = "http://localhost/CupCake_Web_VF-master/web/app_dev.php/api/allUsers";
				/**
				 * *****
				 */
				con.setPost(false);
				//con.addArgument("name", pseudo.getText());
				con.addArgument("username", pseudo.getText());
				con.addArgument("password", password.getText());
				/**
				 * *****
				 */

				con.setUrl(url);
				NetworkManager.getInstance().addToQueueAndWait(con);
			}
		});
		con.addResponseListener((e) -> {
			String str = new String(con.getResponseData());
			str.trim();
			if (str.length() != 0) {
				if (str.equals("error")) {
					Dialog.show("Error", "Invalid inputs", "Ok", "Cancel");

				} else {
					Form f = new Form();
					backBtn=new Button("Back");
					encoded = EncodedImage.createFromImage(theme.getImage("round.png"), false);
					//uRLImage = URLImage.createToStorage(encoded, url1, url1);
					//imgV = new ImageViewer(uRLImage);
					f.add(new SpanLabel(str));
					f.add(backBtn);
					f.add(imgV);
					f.show();
					backBtn.addActionListener((a)->{
						hi.show();
					});
				}
				con.setUrl(url);
			}

		});
		hi.show();
	}

	public void stop() {
		current = getCurrentForm();
		if (current instanceof Dialog) {
			((Dialog) current).dispose();
			current = getCurrentForm();
		}
	}

	public void destroy() {
	}
}
