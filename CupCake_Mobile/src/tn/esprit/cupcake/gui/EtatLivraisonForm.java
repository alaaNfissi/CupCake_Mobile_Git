/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.*;

import com.codename1.ui.layouts.BoxLayout;

import tn.esprit.cupcake.services.CommandeService;
import tn.esprit.cupcake.services.LivraisonService;

/**
 *
 * @author Alaa
 */
public class EtatLivraisonForm extends com.codename1.ui.Form {

	public EtatLivraisonForm() {
		this(com.codename1.ui.util.Resources.getGlobalResources());
	}

	public EtatLivraisonForm(com.codename1.ui.util.Resources resourceObjectInstance) {
		initGuiBuilderComponents(resourceObjectInstance);
		getTitleArea().setUIID("Container");
		getToolbar().setUIID("Container");
		getToolbar().getTitleComponent().setUIID("SigninTitle");
		FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
		getToolbar().addCommandToLeftBar("", mat, e -> new MySpaceForm().show());
		getContentPane().setUIID("SignInForm");
	}

//-- DON'T EDIT BELOW THIS LINE!!!
	private Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
	private ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
	private RadioButton etat1 = new RadioButton("En cours du traitement");
	private RadioButton etat2 = new RadioButton("En route");
	private RadioButton etat3 = new RadioButton("Livrée");
	private ButtonGroup grpBtn = new ButtonGroup();
	private Button registerBtn = new Button();
	private Label LAnnulee = new Label();
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
				onButton_2ActionEvent(ev);
			}

		}

		public void dataChanged(int type, int index) {
		}
	}

	private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
		guiBuilderBindComponentListeners();
		setLayout(new com.codename1.ui.layouts.BorderLayout());
		setTitle("Etat Livraison");
		setName("SignUpForm");
		LivraisonService ls = new LivraisonService();
		ls.getLivraisonByIdCmd(CommandeService.commande);
		addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
		gui_Container_1.setScrollableY(true);
		gui_Container_1.setName("Container_1");
		//gui_Container_1.addComponent(gui_Label_1);
		gui_Container_1.addComponent(gui_Component_Group_1);

		if (ls.l.getEtat_livraison() == 3) {
			gui_Container_1.removeAll();
			LAnnulee.setText(ls.l.getEtatLivraison());
			LAnnulee.setUIID("CalendarHourSelected");
			gui_Container_1.add(LAnnulee);
		} else {
			gui_Component_Group_1.add(etat1);
			gui_Component_Group_1.add(etat2);
			gui_Component_Group_1.add(etat3);
			grpBtn = new ButtonGroup(etat1, etat2, etat3);
			grpBtn.setSelected(ls.l.getEtat_livraison());
			gui_Component_Group_1.setName("Component_Group_1");
			gui_Container_1.addComponent(registerBtn);
			gui_Component_Group_1.setName("Component_Group_1");
			registerBtn.setText("Valider");
			registerBtn.setName("Button_2");
		}

		gui_Container_1.setScrollableY(true);
		gui_Container_1.setName("Container_1");
	}// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
	public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {
		LivraisonService ls = new LivraisonService();
		System.out.println("etat Livraison **************************** => " + grpBtn.getSelectedIndex());
		ls.changerEtatLivraison(CommandeService.commande, grpBtn.getSelectedIndex());
		new MySpaceForm().show();
	}

}
