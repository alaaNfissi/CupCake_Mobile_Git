/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.*;
import static com.codename1.ui.Component.BASELINE;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import tn.esprit.cupcake.entities.Client;
import tn.esprit.cupcake.entities.Patisserie;
import tn.esprit.cupcake.services.PatisserieService;
import tn.esprit.cupcake.services.UtilisateurService;

/**
 * 
 * @author Alaa
 */
public class CreatePatisserieForm extends com.codename1.ui.Form {

	public CreatePatisserieForm() {
		this(com.codename1.ui.util.Resources.getGlobalResources());
	}

	public CreatePatisserieForm(com.codename1.ui.util.Resources resourceObjectInstance) {
		initGuiBuilderComponents(resourceObjectInstance);
		getTitleArea().setUIID("Container");
		getToolbar().setUIID("Container");
		getToolbar().getTitleComponent().setUIID("SigninTitle");
		FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
		getToolbar().addCommandToLeftBar("", mat, e -> new SplashForm().show());
		getContentPane().setUIID("SignInForm");
	}

//-- DON'T EDIT BELOW THIS LINE!!!
	private Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
	private Container sexe_container = new Container(new BoxLayout(BoxLayout.X_AXIS));
	private Container roles_container = new Container(new BoxLayout(BoxLayout.X_AXIS));
	private Label gui_Label_1 = new com.codename1.ui.Label();
	private ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
	
	private TextField libelle_patisserie = new TextField();
	//private TextField email = new TextField();
	private TextField adresse_patisserie = new TextField();
	private Picker date_creation = new Picker();
	private TextField specialite = new TextField();
	//private TextField nTel = new TextField();
	private TextField compte_facebook = new TextField("", "", 200, TextArea.URL);
	private TextField compte_instagram = new TextField("", "", 200, TextArea.URL);
	private TextArea description = new TextArea();
	private TextField image = new TextField();
	private Button createBtn = new Button();
// <editor-fold defaultstate="collapsed" desc="Generated Code">                          

	private void guiBuilderBindComponentListeners() {
		EventCallbackClass callback = new EventCallbackClass();
		createBtn.addActionListener(callback);
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

			if (sourceComponent == createBtn) {
				onButton_2ActionEvent(ev);
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
		//sexe_f.setSelected(true);

		//sexe_container.addComponent(sexe_h);
		gui_Component_Group_1.setName("Component_Group_1");
		gui_Component_Group_1.addComponent(libelle_patisserie);
		gui_Component_Group_1.addComponent(adresse_patisserie);
		gui_Component_Group_1.addComponent(date_creation);
		gui_Component_Group_1.addComponent(specialite);
		gui_Component_Group_1.addComponent(compte_facebook);
		gui_Component_Group_1.addComponent(compte_instagram);
		gui_Component_Group_1.addComponent(description);
		gui_Component_Group_1.addComponent(image);
		libelle_patisserie.setHint("Libelle Patisserie");
		adresse_patisserie.setHint("Adresse");
		specialite.setHint("Spécialité");
		compte_facebook.setHint("Url page facebook");
		compte_instagram.setHint("Url page instagram");
		description.setHint("Dit quelque chose à propos de votre patisserie ...");
		image.setHint("Image");

		gui_Container_1.addComponent(createBtn);
		gui_Label_1.setUIID("CenterLabel");
		gui_Label_1.setName("Label_1");
		gui_Label_1.setIcon(resourceObjectInstance.getImage("profile_image.png"));
		gui_Component_Group_1.setName("Component_Group_1");
		createBtn.setText("Create");
		createBtn.setName("Button_2");
		gui_Container_1.setScrollableY(true);
		gui_Container_1.setName("Container_1");
	}// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
	public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {
		PatisserieService ps= new PatisserieService();
		Patisserie p=new Patisserie();
		p.setLibelle_patisserie(libelle_patisserie.getText());
		p.setAdresse_patisserie(adresse_patisserie.getText());
		p.setDate_creation(date_creation.getDate());
		p.setSpecialite(specialite.getText());
		p.setCompte_facebook(compte_facebook.getText());
		p.setCompte_instagram(compte_instagram.getText());
		p.setDescription(description.getText());
		p.setImage(image.getText());
		p.setPatissier(UtilisateurService.user.getId());
		//System.out.println(p.getPatissier());
		System.out.println(p.toString());
		ps.createPatisserie(p);
		if (ps.patisserie != null) {
			new TrendingForm().show();
		}
	}

}

