/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.*;

import com.codename1.ui.spinner.Picker;
import java.util.Date;

/**
 *
 * @author Alaa
 */
public class PanierForm extends com.codename1.ui.Form {

	public PanierForm() {
		this(com.codename1.ui.util.Resources.getGlobalResources());
	}

	public PanierForm(com.codename1.ui.util.Resources resourceObjectInstance) {
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
	private Picker dateLivraison = new Picker();
	private Button commnaderBtn=new Button("Commander");
// <editor-fold defaultstate="collapsed" desc="Generated Code">                          

	private void guiBuilderBindComponentListeners() {
		EventCallbackClass callback = new EventCallbackClass();
		commnaderBtn.addActionListener(callback);
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

			if (sourceComponent == commnaderBtn) {
				onButton_2ActionEvent(ev);
			}

		}

		public void dataChanged(int type, int index) {
		}
	}

	private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
		guiBuilderBindComponentListeners();
		setLayout(new com.codename1.ui.layouts.BorderLayout());
		setTitle("Mon Panier");
		setName("SignUpForm");
		addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
		gui_Container_1.setScrollableY(true);
		gui_Container_1.setName("Container_1");
		//gui_Container_1.addComponent(gui_Label_1);
		gui_Container_1.addComponent(gui_Component_Group_1);
		gui_Component_Group_1.add(dateLivraison);
		gui_Container_1.add(commnaderBtn);
		gui_Container_1.setScrollableY(true);
		gui_Container_1.setName("Container_1");
	}// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
	public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {
		
		new MySpaceForm().show();
	}

}
