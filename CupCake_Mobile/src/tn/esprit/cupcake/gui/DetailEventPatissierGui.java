/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.contacts.ContactsModel;
import com.codename1.io.FileSystemStorage;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.ImageIO;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import tn.esprit.cupcake.entities.Evenement;
import tn.esprit.cupcake.entities.EvenementUser;
import tn.esprit.cupcake.services.EvenementService;
import tn.esprit.cupcake.services.EvenementUserService;

/**
 *
 * @author Basly
 */
public class DetailEventPatissierGui {

	Form f = new Form();
	ImageViewer image = new ImageViewer();
	ImageViewer image1 = new ImageViewer();
	Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
	FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_BACKSPACE, s);

	private com.codename1.ui.Label gui_Label_libelle = new com.codename1.ui.Label("Libelle :");
	private com.codename1.ui.Label gui_Label_adresse = new com.codename1.ui.Label("Adresse :");
	private com.codename1.ui.Label gui_Label_dateDebut = new com.codename1.ui.Label("Date debut :");
	private com.codename1.ui.Label gui_Label_dateFin = new com.codename1.ui.Label("Date fin :");
	private com.codename1.ui.Label gui_Label_description = new com.codename1.ui.Label("Description :");
	private com.codename1.ui.Label gui_Label_note = new com.codename1.ui.Label("Note :");
	private com.codename1.ui.Label gui_Label_nbparticipe = new com.codename1.ui.Label("Nombre Participant :");
	private com.codename1.ui.Label gui_Label_valuenbparticipe = new com.codename1.ui.Label();
	private com.codename1.ui.Label gui_Label_valuenote = new com.codename1.ui.Label();
	private com.codename1.ui.Label gui_Label_valuelibelle = new com.codename1.ui.Label();
	private com.codename1.ui.Label gui_Label_valueadresse = new com.codename1.ui.Label();
	private com.codename1.ui.Label gui_Label_valuedateDebut = new com.codename1.ui.Label();
	private com.codename1.ui.Label gui_Label_valuedateFin = new com.codename1.ui.Label();
	private com.codename1.ui.Label gui_Label_valuedescription = new com.codename1.ui.Label();

	public DetailEventPatissierGui(Evenement event) {
		f.getToolbar().addCommandToLeftBar("", icon, (e) -> new ListEvenementPatissierGui().show());
		f.setTitle(event.getLibelle_evenement());
		FontImage.setMaterialIcon(gui_Label_libelle, FontImage.MATERIAL_INFO);
		FontImage.setMaterialIcon(gui_Label_adresse, FontImage.MATERIAL_LOCATION_ON);
		FontImage.setMaterialIcon(gui_Label_dateDebut, FontImage.MATERIAL_DATE_RANGE);
		FontImage.setMaterialIcon(gui_Label_dateFin, FontImage.MATERIAL_DATE_RANGE);
		FontImage.setMaterialIcon(gui_Label_description, FontImage.MATERIAL_DESCRIPTION);
		FontImage.setMaterialIcon(gui_Label_note, FontImage.MATERIAL_STAR);
		FontImage.setMaterialIcon(gui_Label_nbparticipe, FontImage.MATERIAL_FUNCTIONS);
		EvenementService serviceEvenement = new EvenementService();
		EvenementUserService serviceEvenementUser = new EvenementUserService();
		ArrayList<EvenementUser> ListEventUser = serviceEvenementUser.getEvenementUserByEvent(event.getId_evenement());
		ArrayList<Evenement> ListEvents = serviceEvenement.getEvenement(event.getId_evenement());
		int nbParticipant = ListEventUser.size();

		EncodedImage encImg = EncodedImage.createFromImage(UIManager.initFirstTheme("/theme").getImage("bridge.jpg"), false);
		System.err.println(event.getImage());
		URLImage imgUrl = URLImage.createToStorage(encImg, event.getImage(), "http://localhost/images/" + event.getImage(), URLImage.RESIZE_SCALE_TO_FILL);
		image = new ImageViewer(imgUrl);
		String dateDebut = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(event.getDate_debut());
		String dateFin = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(event.getDate_fin());
		gui_Label_valuelibelle.setText(event.getLibelle_evenement());
		gui_Label_valueadresse.setText(event.getAdresse_evenement());
		gui_Label_valuedateDebut.setText(dateDebut);
		gui_Label_valuedateFin.setText(dateFin);
		gui_Label_valuedescription.setText(event.getDescription());
		gui_Label_valuenote.setText(String.valueOf(event.getNote()));
		gui_Label_valuenbparticipe.setText(String.valueOf(nbParticipant));
		//gui_Label_valuenbparticipe.setText();
		Container cnty = new Container(BoxLayout.y());
		Container cntx0 = new Container(BoxLayout.x());
		Container cntx1 = new Container(BoxLayout.x());
		Container cntx2 = new Container(BoxLayout.x());
		Container cntx3 = new Container(BoxLayout.x());
		Container cntx4 = new Container(BoxLayout.x());
		Container cntx5 = new Container(BoxLayout.x());
		Container cntx6 = new Container(BoxLayout.x());
		Container cntx7 = new Container(BoxLayout.x());
		cntx0.add(image);
		String sharePhrase = event.getLibelle_evenement() + "\n à " + event.getAdresse_evenement() + "\n ,Commence le : " + dateDebut + "et se termine vers : " + dateFin + "\n Description : " + event.getDescription();
		Container sb = new Container();
		sb = (Container) showShare(sharePhrase);
		//Button bs=new Button();
		//sb.setText("");
		Form hi = new Form("");
		ShareButton sb1 = new ShareButton();
		sb1.setText("");
		image1 = new ImageViewer(imgUrl);
		hi.add(sb1);
		//hi.add(image1);
		Image screenshot = Image.createImage(hi.getWidth(), hi.getHeight());
		hi.revalidate();
		hi.setVisible(true);
		hi.paintComponent(screenshot.getGraphics(), true);

		String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
		try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
			ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
		} catch (IOException err) {
			//Log.e(err);
			err.printStackTrace();
		}
		sb1.setImageToShare(imageFile, "image/png") ;
		
		/**************************************/
		cntx1.add(gui_Label_libelle);
		cntx1.add(gui_Label_valuelibelle);
		cntx2.add(gui_Label_adresse);
		cntx2.add(gui_Label_valueadresse);
		cntx3.add(gui_Label_dateDebut);
		cntx3.add(gui_Label_valuedateDebut);
		cntx4.add(gui_Label_dateFin);
		cntx4.add(gui_Label_valuedateFin);
		cntx5.add(gui_Label_description);
		cntx5.add(gui_Label_valuedescription);
		cntx6.add(gui_Label_note);
		cntx6.add(gui_Label_valuenote);
		cntx7.add(gui_Label_nbparticipe);
		cntx7.add(gui_Label_valuenbparticipe);

		cnty.add(cntx0);
		cnty.add(hi);
		cnty.add(cntx1);
		cnty.add(cntx2);
		cnty.add(cntx3);
		cnty.add(cntx4);
		cnty.add(cntx5);
		cnty.add(cntx6);
		cnty.add(cntx7);
		cnty.add(sb);
		f.add(cnty);

	}

	public Form getF() {
		return f;
	}

	public void setF(Form f) {
		this.f = f;
	}

	private Component showShare(String phrase) {
		final Container c = new Container(new BorderLayout());
		final ShareButton share = new ShareButton();
		//ContactsModelcm=new ContactsModel(ids);
		final TextArea t = new TextArea(phrase);
		//t.setHidden(true);
		t.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				share.setTextToShare("test !");
			}
		});
		c.addComponent(BorderLayout.CENTER, t);
		share.setTextToShare(t.getText());
		Container cnt = new Container(new BorderLayout());
		cnt.addComponent(BorderLayout.SOUTH, share);
		c.addComponent(BorderLayout.EAST, cnt);
		return c;
	}
}
