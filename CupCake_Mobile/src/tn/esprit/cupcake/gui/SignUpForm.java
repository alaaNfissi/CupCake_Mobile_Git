/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.*;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import java.util.Date;
/*import java.util.regex.Matcher;
import java.util.regex.Pattern;*/
import tn.esprit.cupcake.entities.Client;
//import tn.esprit.cupcake.entities.Patissier;
//import sun.security.util.Password;
import tn.esprit.cupcake.services.UtilisateurService;

/**
 *
 * @author Alaa
 */
public class SignUpForm extends com.codename1.ui.Form {

	public SignUpForm() {
		this(com.codename1.ui.util.Resources.getGlobalResources());
	}

	public SignUpForm(com.codename1.ui.util.Resources resourceObjectInstance) {
		initGuiBuilderComponents(resourceObjectInstance);
		getTitleArea().setUIID("Container");
		getToolbar().setUIID("Container");
		getToolbar().getTitleComponent().setUIID("SigninTitle");
		FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
		getToolbar().addCommandToLeftBar("", mat, e -> new SignInForm().show());
		getContentPane().setUIID("SignInForm");
	}

//-- DON'T EDIT BELOW THIS LINE!!!
	private Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
	private Container sexe_container = new Container(new BoxLayout(BoxLayout.X_AXIS));
	private Container roles_container = new Container(new BoxLayout(BoxLayout.X_AXIS));
	private Label gui_Label_1 = new com.codename1.ui.Label();
	private ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
	private TextField username = new TextField();
	//private TextField email = new TextField();
	private TextField email = new TextField("", "", 20, TextArea.EMAILADDR);
	private TextField password = new TextField();
	//private TextField nTel = new TextField();
	private TextField nTel = new TextField("", "", 20, TextArea.PHONENUMBER);
	private TextField nom = new TextField();
	private TextField prenom = new TextField();
	private Picker dateNaissance = new Picker();
	private TextField adresse = new TextField();
	/*--------------- Sexe -----------------*/
	private RadioButton sexe_h = new RadioButton("Homme");
	private RadioButton sexe_f = new RadioButton("Femme");
	private ButtonGroup sexeGrp = new ButtonGroup();
	private TextField sexe = new TextField();
	/*-------------------------------------------*/
	private TextField image = new TextField();
	//private 
	/*--------------- Role -----------------*/
	private RadioButton client = new RadioButton("Client");
	private RadioButton patissier = new RadioButton("Patissier");
	private ButtonGroup rolesGrp = new ButtonGroup();
	private TextField roles = new TextField();
	/*--------------------------------------------*/
	private Button registerBtn = new Button();
	private Button backBtn = new Button();
// <editor-fold defaultstate="collapsed" desc="Generated Code">                          

	private void guiBuilderBindComponentListeners() {
		EventCallbackClass callback = new EventCallbackClass();
		registerBtn.addActionListener(callback);
	}

	class EventCallbackClass implements com.codename1.ui.events.ActionListener, com.codename1.ui.events.DataChangedListener {

		private com.codename1.ui.Component cmp;

		public EventCallbackClass(com.codename1.ui.Component cmp) {
			this.cmp = cmp;
		}

		public EventCallbackClass() {
		}

		public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
			com.codename1.ui.Component sourceComponent = ev.getComponent();
			if (sourceComponent.getParent().getLeadParent() != null) {
				sourceComponent = sourceComponent.getParent().getLeadParent();
			}

			if (sourceComponent == registerBtn) {
				if (verif()) {
				onButton_2ActionEvent(ev);
				}
			}

		}

		public void dataChanged(int type, int index) {
		}
	}

	private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
		guiBuilderBindComponentListeners();
		setLayout(new com.codename1.ui.layouts.BorderLayout());
		setTitle("Sign Up");
		setName("SignUpForm");
		addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
		gui_Container_1.setScrollableY(true);
		gui_Container_1.setName("Container_1");
		gui_Container_1.addComponent(gui_Label_1);
		gui_Container_1.addComponent(gui_Component_Group_1);
		sexeGrp = new ButtonGroup(sexe_h, sexe_f);
		rolesGrp = new ButtonGroup(client, patissier);
		sexe_container.add(sexe_h).add(sexe_f);
		roles_container.add(client).add(patissier);
		//sexe_f.setSelected(true);

		//sexe_container.addComponent(sexe_h);
		gui_Component_Group_1.setName("Component_Group_1");
		gui_Component_Group_1.addComponent(username);
		gui_Component_Group_1.addComponent(email);
		gui_Component_Group_1.addComponent(password);
		gui_Component_Group_1.addComponent(nTel);
		gui_Component_Group_1.addComponent(nom);
		gui_Component_Group_1.addComponent(prenom);
		gui_Component_Group_1.addComponent(dateNaissance);
		gui_Component_Group_1.addComponent(adresse);
		gui_Component_Group_1.addComponent(sexe_container);
		image.setText("https://ucarecdn.com/4d708d52-215a-4b23-b55f-215d384ec518/");
		gui_Component_Group_1.addComponent(image);
		gui_Component_Group_1.addComponent(roles_container);
		username.setHint("Username");
		email.setHint("E-mail");
		password.setHint("Password");
		nTel.setHint("Numéro Tél");
		nom.setHint("Nom");
		prenom.setHint("Prenom");
		//dateNaissance.setHint("Date de naissance");
		adresse.setHint("Adresse");
		//sexe.setHint("Sexe");
		image.setHint("Image");
		//roles.setHint("Roles");
		gui_Container_1.addComponent(registerBtn);
		gui_Container_1.addComponent(backBtn);
		gui_Label_1.setUIID("CenterLabel");
		gui_Label_1.setName("Label_1");
		gui_Label_1.setIcon(resourceObjectInstance.getImage("profile_image.png"));
		gui_Component_Group_1.setName("Component_Group_1");
		registerBtn.setText("Sign Up");
		registerBtn.setName("Button_2");
		//addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, backBtn);
		gui_Container_1.setScrollableY(true);
		gui_Container_1.setName("Container_1");
		backBtn.setText("I already have an account");
		backBtn.setUIID("CenterLabel");
		backBtn.setName("Button_1");
	}// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
	public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {
			UtilisateurService us = new UtilisateurService();
			Client c = new Client();
			c.setUsername(username.getText());
			c.setEmail(email.getText());
			c.setPassword(password.getText());
			c.setNum_tel(nTel.getAsInt(BASELINE));
			c.setNom(nom.getText());
			c.setPrenom(prenom.getText());
			c.setDate_naissance(dateNaissance.getDate());
			c.setAdresse(adresse.getText());
			if (sexeGrp.getSelectedIndex() == 0) {
				c.setSexe("homme");
			} else if (sexeGrp.getSelectedIndex() == 1) {
				c.setSexe("femme");
			}
			
			c.setImage(image.getText());
			//c.setRoles(roles);
			if (rolesGrp.getSelectedIndex() == 0) {
				c.setRoles("ROLE_CLIENT");
			} else if (rolesGrp.getSelectedIndex() == 1) {
				c.setRoles("ROLE_PATISSIER");
			}
			System.out.println(c.toString());
			us.addUser(c);
			if (UtilisateurService.user != null && UtilisateurService.user.getRoles().equals("ROLE_PATISSIER")) {
				new CreatePatisserieForm().show();
			} else if (UtilisateurService.user != null && UtilisateurService.user.getRoles().equals("ROLE_CLIENT")) {
				new ListPatisseriesGui().show();
			}
	}

	public boolean verif() {
		if (email.getText() == "" || username.getText() == "" || password.getText() == "" || nTel.getText() == "" || nom.getText() == "" || prenom.getText() == "" || dateNaissance.getDate() == null || adresse.getText() == "" || (!sexeGrp.isSelected()) || image.getText() == "" || (!rolesGrp.isSelected())) {
			Dialog.show("Attention", "SVP ! Vérifiez vos Données .", "Ok", "");
			return false;
		}
		return true;
	}

}
