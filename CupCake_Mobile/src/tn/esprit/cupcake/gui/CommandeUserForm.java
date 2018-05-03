/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
//import com.twilio.rest.monitor.v1.Alert;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.cupcake.entities.Commande;
import tn.esprit.cupcake.services.CommandeService;
import tn.esprit.cupcake.services.LivraisonService;
import tn.esprit.cupcake.services.PatisserieService;
import tn.esprit.cupcake.services.UtilisateurService;

/**
 *
 * @author Alaa
 */
public class CommandeUserForm extends BaseForm {

	public CommandeUserForm() {
		this(com.codename1.ui.util.Resources.getGlobalResources());
	}

	@Override
	protected boolean isCurrentInbox() {
		return true;
	}

	public CommandeUserForm(com.codename1.ui.util.Resources resourceObjectInstance) {
		initGuiBuilderComponents(resourceObjectInstance);
		CommandeService cs = new CommandeService();
		ArrayList<Commande> listCommandes = cs.getListCommandeUser(UtilisateurService.user);
		getToolbar().setTitleComponent(
				FlowLayout.encloseCenterMiddle(
						new Label("Commandes", "Title"),
						new Label("" + listCommandes.size(), "InboxNumber")
				)
		);

		installSidemenu(resourceObjectInstance);

		getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("toolbar-profile-pic.png"), e -> {
		});

		gui_Label_5.setShowEvenIfBlank(true);
		gui_Label_6.setShowEvenIfBlank(true);
		gui_Label_7.setShowEvenIfBlank(true);
		gui_Label_8.setShowEvenIfBlank(true);
		gui_Label_9.setShowEvenIfBlank(true);

		gui_Text_Area_1.setRows(2);
		gui_Text_Area_1.setColumns(100);
		gui_Text_Area_1.setEditable(false);
		gui_Text_Area_1_1.setRows(2);
		gui_Text_Area_1_1.setColumns(100);
		gui_Text_Area_1_1.setEditable(false);
		gui_Text_Area_1_2.setRows(2);
		gui_Text_Area_1_2.setColumns(100);
		gui_Text_Area_1_2.setEditable(false);
		gui_Text_Area_1_3.setRows(2);
		gui_Text_Area_1_3.setColumns(100);
		gui_Text_Area_1_3.setEditable(false);
		gui_Text_Area_1_4.setRows(2);
		gui_Text_Area_1_4.setColumns(100);
		gui_Text_Area_1_4.setEditable(false);

		FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
		RoundBorder rb = (RoundBorder) fab.getUnselectedStyle().getBorder();
		rb.uiid(true);
		fab.bindFabToContainer(getContentPane());
		fab.addActionListener(e -> {
			fab.setUIID("FloatingActionButtonClose");
			Image oldImage = fab.getIcon();
			FontImage image = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "FloatingActionButton", 3.8f);
			fab.setIcon(image);
			Dialog popup = new Dialog();
			popup.setDialogUIID("Container");
			popup.setLayout(new LayeredLayout());

			Button c1 = new Button(resourceObjectInstance.getImage("contact-a.png"));
			Button c2 = new Button(resourceObjectInstance.getImage("contact-b.png"));
			Button c3 = new Button(resourceObjectInstance.getImage("contact-c.png"));
			Button trans = new Button(" ");
			trans.setUIID("Container");
			c1.setUIID("Container");
			c2.setUIID("Container");
			c3.setUIID("Container");
			Style c1s = c1.getAllStyles();
			Style c2s = c2.getAllStyles();
			Style c3s = c3.getAllStyles();

			c1s.setMarginUnit(Style.UNIT_TYPE_DIPS);
			c2s.setMarginUnit(Style.UNIT_TYPE_DIPS);
			c3s.setMarginUnit(Style.UNIT_TYPE_DIPS);

			c1s.setMarginBottom(16);
			c1s.setMarginLeft(12);
			c1s.setMarginRight(3);

			c2s.setMarginLeft(4);
			c2s.setMarginTop(5);
			c2s.setMarginBottom(10);
			c3s.setMarginRight(14);

			c3s.setMarginTop(12);
			c3s.setMarginRight(16);

			popup.add(trans).
					add(FlowLayout.encloseIn(c1)).
					add(FlowLayout.encloseIn(c2)).
					add(FlowLayout.encloseIn(c3));

			ActionListener a = ee -> popup.dispose();

			trans.addActionListener(a);
			c1.addActionListener(a);
			c2.addActionListener(a);
			c3.addActionListener(a);

			popup.setTransitionInAnimator(CommonTransitions.createEmpty());
			popup.setTransitionOutAnimator(CommonTransitions.createEmpty());
			popup.setDisposeWhenPointerOutOfBounds(true);
			int t = CommandeUserForm.this.getTintColor();
			CommandeUserForm.this.setTintColor(0);
			popup.showPopupDialog(new Rectangle(CommandeUserForm.this.getWidth() - 10, CommandeUserForm.this.getHeight() - 10, 10, 10));
			CommandeUserForm.this.setTintColor(t);
			fab.setUIID("FloatingActionButton");
			fab.setIcon(oldImage);
		});
	}

//-- DON'T EDIT BELOW THIS LINE!!!
	private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
	private com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
	private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
	private com.codename1.ui.Container gui_Container_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
	private com.codename1.ui.Label gui_Label_4 = new com.codename1.ui.Label();
	private com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
	private com.codename1.ui.Label gui_Label_3 = new com.codename1.ui.Label();
	private com.codename1.ui.Label gui_Label_2 = new com.codename1.ui.Label();
	private com.codename1.ui.TextArea gui_Text_Area_1 = new com.codename1.ui.TextArea();
	private com.codename1.ui.Label gui_Label_6 = new com.codename1.ui.Label();
	private com.codename1.ui.Container gui_Container_1_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
	private com.codename1.ui.Container gui_Container_2_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
	private com.codename1.ui.Label gui_Label_1_1 = new com.codename1.ui.Label();
	private com.codename1.ui.Container gui_Container_4_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
	private com.codename1.ui.Label gui_Label_4_1 = new com.codename1.ui.Label();
	private com.codename1.ui.Container gui_Container_3_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
	private com.codename1.ui.Label gui_Label_3_1 = new com.codename1.ui.Label();
	private com.codename1.ui.Label gui_Label_2_1 = new com.codename1.ui.Label();
	private com.codename1.ui.TextArea gui_Text_Area_1_1 = new com.codename1.ui.TextArea();
	private com.codename1.ui.Label gui_Label_7 = new com.codename1.ui.Label();
	private com.codename1.ui.Container gui_Container_1_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
	private com.codename1.ui.Container gui_Container_2_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
	private com.codename1.ui.Label gui_Label_1_2 = new com.codename1.ui.Label();
	private com.codename1.ui.Container gui_Container_4_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
	private com.codename1.ui.Label gui_Label_4_2 = new com.codename1.ui.Label();
	private com.codename1.ui.Container gui_Container_3_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
	private com.codename1.ui.Label gui_Label_3_2 = new com.codename1.ui.Label();
	private com.codename1.ui.Label gui_Label_2_2 = new com.codename1.ui.Label();
	private com.codename1.ui.TextArea gui_Text_Area_1_2 = new com.codename1.ui.TextArea();
	private com.codename1.ui.Label gui_Label_8 = new com.codename1.ui.Label();
	private com.codename1.ui.Container gui_Container_1_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
	private com.codename1.ui.Container gui_Container_2_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
	private com.codename1.ui.Label gui_Label_1_3 = new com.codename1.ui.Label();
	private com.codename1.ui.Container gui_Container_4_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
	private com.codename1.ui.Label gui_Label_4_3 = new com.codename1.ui.Label();
	private com.codename1.ui.Container gui_Container_3_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
	private com.codename1.ui.Label gui_Label_3_3 = new com.codename1.ui.Label();
	private com.codename1.ui.Label gui_Label_2_3 = new com.codename1.ui.Label();
	private com.codename1.ui.TextArea gui_Text_Area_1_3 = new com.codename1.ui.TextArea();
	private com.codename1.ui.Label gui_Label_9 = new com.codename1.ui.Label();
	private com.codename1.ui.Container gui_Container_1_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
	private com.codename1.ui.Container gui_Container_2_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
	private com.codename1.ui.Label gui_Label_1_4 = new com.codename1.ui.Label();
	private com.codename1.ui.Container gui_Container_4_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
	private com.codename1.ui.Label gui_Label_4_4 = new com.codename1.ui.Label();
	private com.codename1.ui.Container gui_Container_3_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
	private com.codename1.ui.Label gui_Label_3_4 = new com.codename1.ui.Label();
	private com.codename1.ui.Label gui_Label_2_4 = new com.codename1.ui.Label();
	private com.codename1.ui.TextArea gui_Text_Area_1_4 = new com.codename1.ui.TextArea();
	private com.codename1.ui.Label gui_Label_5 = new com.codename1.ui.Label();

// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
	private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
		setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
		CommandeService cs = new CommandeService();
		ArrayList<Commande> listCommandes = cs.getListCommandeUser(UtilisateurService.user);
		System.out.println(listCommandes);

		setTitle("InboxForm");
		setName("InboxForm");
		for (Commande listCommande : listCommandes) {
			Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
			Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
			Label gui_Label_1 = new com.codename1.ui.Label();
			Container gui_Container_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
			Label gui_Label_4 = new com.codename1.ui.Label();
			Label gui_Label_5 = new com.codename1.ui.Label();
			Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
			Label gui_Label_3 = new com.codename1.ui.Label();
			Label gui_Label_2 = new com.codename1.ui.Label();
			TextArea gui_Text_Area_1 = new com.codename1.ui.TextArea();
			Label gui_Label_6 = new com.codename1.ui.Label();
			Container gui_Container_6 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
			Button viewBtn = new Button();

			addComponent(gui_Container_1);
			gui_Container_1.setName("Container_1");
			gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Container_2);
			gui_Container_2.setName("Container_2");
			gui_Container_2.addComponent(gui_Label_1);
			gui_Container_2.addComponent(gui_Container_6);
			gui_Label_1.setText("N° : " + listCommande.getNum_commande());
			gui_Label_1.setUIID("SmallFontLabel");
			gui_Label_1.setName("Label_1");
			//viewBtn.setUIID("GetStartedRedArrow");
			viewBtn.setText("");
			viewBtn.setUIID("GetStartedButton");
			viewBtn.setName("Button_1");
			viewBtn.setTextPosition(com.codename1.ui.Component.LEFT);
			gui_Label_5.setUIID("GetStartedRedArrow");
			gui_Label_5.setName("Label_1");
			gui_Container_6.setUIID("GetStartedButton");
			gui_Container_6.setName("Container_6");
			com.codename1.ui.FontImage.setMaterialIcon(gui_Label_5, "".charAt(0));
			gui_Container_6.addComponent(viewBtn);
			gui_Container_6.addComponent(gui_Label_5);
//			((com.codename1.ui.layouts.FlowLayout)gui_Container_6.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
			//        ((com.codename1.ui.layouts.FlowLayout)gui_Container_6.getLayout()).setValign(com.codename1.ui.Component.CENTER);
			/*-----------------------------------------------------------------------*/
			gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.WEST, gui_Container_4);
			gui_Container_4.setName("Container_4");
			((com.codename1.ui.layouts.FlowLayout) gui_Container_4.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
			gui_Container_4.addComponent(gui_Label_4);
			gui_Label_4.setUIID("Padding2");
			gui_Label_4.setName("Label_4");
			gui_Label_4.setIcon(resourceObjectInstance.getImage("label_round.png"));
			gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_3);
			gui_Container_3.setName("Container_3");
			gui_Container_3.addComponent(gui_Label_3);
			gui_Container_3.addComponent(gui_Label_2);
			gui_Container_3.addComponent(gui_Text_Area_1);
			/*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String date = obj.get("dateCommande").toString();
				c.setDate_commande(formatter.parse(date));*/
			gui_Label_3.setText("Date : " + listCommande.getDate_commande());
			gui_Label_3.setName("Label_3");
			gui_Label_2.setText("Prix T : " + listCommande.getPrix_totale());
			gui_Label_2.setUIID("RedLabel");
			gui_Label_2.setName("Label_2");
			//gui_Text_Area_1.setText(PatisserieService.patisserie.getLibelle_patisserie());
			gui_Text_Area_1.setUIID("SmallFontLabel");
			gui_Text_Area_1.setName("Text_Area_1");
			gui_Container_2.setName("Container_2");
			gui_Container_4.setName("Container_4");
			((com.codename1.ui.layouts.FlowLayout) gui_Container_4.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
			gui_Container_3.setName("Container_3");
			addComponent(gui_Label_6);

			viewBtn.addActionListener((e) -> {
				//new CalendarForm().show();
				LivraisonService ls=new LivraisonService();
				ls.getLivraisonByIdCmd(listCommande);
				System.out.println("teeeeeest "+ls.l.getEtatLivraison());
				Dialog d = new Dialog("Commande n° : "+listCommande.getNum_commande());
				TextArea popupBody = new TextArea("Prix Livraison : "+ls.l.getPrix_livraison()+" DT\n"+"Etat : "+ls.l.getEtatLivraison()+"\n"+"Date : "+ls.l.getDate_livraison(), 3, 20);
				popupBody.setUIID("PopupBody");
				popupBody.setEditable(false);
				Button b=new Button("Annuler Commande");
				b.addActionListener((l)->{
				ls.changerEtatLivraison(listCommande,3);
				new CommandeUserForm().show();
				});
				d.setLayout(new BorderLayout());
				d.add(BorderLayout.CENTER, popupBody);
				d.add(BorderLayout.SOUTH, b);
				d.showPopupDialog(viewBtn);
			});
			gui_Container_1.setLeadComponent(viewBtn);
		}
	}// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
