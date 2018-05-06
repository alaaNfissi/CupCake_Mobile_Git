/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.gui;

import com.codename1.components.ImageViewer;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.*;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.util.ArrayList;
import tn.esprit.cupcake.entities.Evenement;
import tn.esprit.cupcake.entities.EvenementUser;
import tn.esprit.cupcake.entities.Patisserie;
import tn.esprit.cupcake.services.EvenementService;
import tn.esprit.cupcake.services.EvenementUserService;
import tn.esprit.cupcake.services.UtilisateurService;

/**
 *
 * @author Basly
 */
public class DetailEventClientGui {

    Form f = new Form();
    Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
    FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_BACKSPACE, s);
    private com.codename1.ui.Label gui_Label_libelle = new com.codename1.ui.Label("Libelle :");
    private com.codename1.ui.Label gui_Label_adresse = new com.codename1.ui.Label("Adresse :");
    private com.codename1.ui.Label gui_Label_dateDebut = new com.codename1.ui.Label("Date debut :");
    private com.codename1.ui.Label gui_Label_dateFin = new com.codename1.ui.Label("Date fin :");
    private com.codename1.ui.Label gui_Label_description = new com.codename1.ui.Label("Description :");
    private com.codename1.ui.Label gui_Label_valuelibelle = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Label_valueadresse = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Label_valuedateDebut = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Label_valuedateFin = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Label_valuedescription = new com.codename1.ui.Label();
    private com.codename1.ui.Button gui_Button_Participer = new com.codename1.ui.Button("Participer");
    private com.codename1.ui.Button gui_Button_Annuler = new com.codename1.ui.Button("Annuler");
    Slider gg = createStarRankSlider();
    double n = (double) gg.getProgress();
    ImageViewer image = new ImageViewer();

    public DetailEventClientGui(Evenement event,Patisserie patisserie) {
        f.setTitle(event.getLibelle_evenement());
        f.getToolbar().addCommandToLeftBar("", icon, (e) -> new ListEvenementClientGui(patisserie).show());

        FontImage.setMaterialIcon(gui_Label_libelle, FontImage.MATERIAL_INFO);
        FontImage.setMaterialIcon(gui_Label_adresse, FontImage.MATERIAL_LOCATION_ON);
        FontImage.setMaterialIcon(gui_Label_dateDebut, FontImage.MATERIAL_DATE_RANGE);
        FontImage.setMaterialIcon(gui_Label_dateFin, FontImage.MATERIAL_DATE_RANGE);
        FontImage.setMaterialIcon(gui_Label_description, FontImage.MATERIAL_DESCRIPTION);

        FontImage.setMaterialIcon(gui_Button_Participer, FontImage.MATERIAL_THUMB_UP);
        FontImage.setMaterialIcon(gui_Button_Annuler, FontImage.MATERIAL_CLEAR);

        EvenementService serviceEvenement = new EvenementService();
        EvenementUserService serviceEvenementUser = new EvenementUserService();

        ArrayList<Evenement> ListEvents = serviceEvenement.getEvenement(event.getId_evenement());

        ArrayList<EvenementUser> ListEventUser2 = serviceEvenementUser.getEvenementUserByEvent(event.getId_evenement());

        int count = ListEventUser2.size();
        int sum = 0;
        int noteEvent = 0;
        for (EvenementUser leu : ListEventUser2) {
            sum += leu.getNote();
        }
        try {
            noteEvent = sum / count;
        } catch (Exception ex) {
        }

        //for (Evenement event : ListEvents) {
        EncodedImage encImg = EncodedImage.createFromImage(UIManager.initFirstTheme("/theme").getImage("bridge.jpg"), false);
        System.err.println(event.getImage());
        URLImage imgUrl = URLImage.createToStorage(encImg, event.getImage(), "http://localhost/images/" + event.getImage(), URLImage.RESIZE_SCALE_TO_FILL);
        image = new ImageViewer(imgUrl);

        gg.setProgress(noteEvent);
        String dateDebut = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(event.getDate_debut());
        String dateFin = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(event.getDate_fin());

        gui_Label_valuelibelle.setText(event.getLibelle_evenement());
        gui_Label_valueadresse.setText(event.getAdresse_evenement());
        gui_Label_valuedateDebut.setText(dateDebut);
        gui_Label_valuedateFin.setText(dateFin);
        gui_Label_valuedescription.setText(event.getDescription());

        gui_Button_Participer.setPreferredSize(new Dimension(160, 40));
        gui_Button_Annuler.setPreferredSize(new Dimension(160, 40));

        Container cnty = new Container(BoxLayout.y());
        Container cntx0 = new Container(BoxLayout.x());
        Container cntx1 = new Container(BoxLayout.x());
        Container cntx2 = new Container(BoxLayout.x());
        Container cntx3 = new Container(BoxLayout.x());
        Container cntx4 = new Container(BoxLayout.x());
        Container cntx5 = new Container(BoxLayout.x());
        Container cntx6 = new Container(BoxLayout.x());

        ArrayList<EvenementUser> ListEventUser = serviceEvenementUser.getEvenementUser(event.getId_evenement(), UtilisateurService.user.getId());

        if (ListEventUser.isEmpty()) {

            gui_Button_Participer.setVisible(true);
            gui_Button_Annuler.setVisible(false);
            cntx6.add(gui_Button_Participer);
        } else {
            gui_Button_Participer.setVisible(false);
            gui_Button_Annuler.setVisible(true);
            cntx6.add(gui_Button_Annuler);
        }

        gui_Button_Participer.addActionListener((e) -> {
            cntx6.removeComponent(gui_Button_Participer);
            cntx6.add(gui_Button_Annuler);
            gui_Button_Participer.setVisible(false);
            gui_Button_Annuler.setVisible(true);
            ArrayList<EvenementUser> ListEventUser3 = serviceEvenementUser.getEvenementUser(event.getId_evenement(), UtilisateurService.user.getId());
            if (ListEventUser3.isEmpty()) {
                EvenementUser eventUser = new EvenementUser(event.getId_evenement(), UtilisateurService.user.getId(), 1, 0);
                serviceEvenementUser.ajoutEventParticipeNote(eventUser);
            } else {
                EvenementUser t = new EvenementUser(event.getId_evenement(), UtilisateurService.user.getId(), 1, ListEventUser3.get(0).getNote());
                serviceEvenementUser.modifierEventParticipeNote(t);
            }
        });

        gui_Button_Annuler.addActionListener((e) -> {
            cntx6.removeComponent(gui_Button_Annuler);
            cntx6.add(gui_Button_Participer);
            gui_Button_Participer.setVisible(true);
            gui_Button_Annuler.setVisible(false);
            ArrayList<EvenementUser> ListEventUser3 = serviceEvenementUser.getEvenementUser(event.getId_evenement(), UtilisateurService.user.getId());
            EvenementUser t = new EvenementUser(event.getId_evenement(), UtilisateurService.user.getId(), 0, ListEventUser3.get(0).getNote());
            serviceEvenementUser.modifierEventParticipeNote(t);
        });
        gg.addActionListener((evt) -> {
            ArrayList<EvenementUser> ListEventUser3 = serviceEvenementUser.getEvenementUser(event.getId_evenement(), UtilisateurService.user.getId());

            if (ListEventUser3.isEmpty()) {
                EvenementUser t = new EvenementUser(event.getId_evenement(), UtilisateurService.user.getId(), 0, (int) gg.getProgress());
                serviceEvenementUser.ajoutEventParticipeNote(t);
                ArrayList<EvenementUser> ListEventUser4 = serviceEvenementUser.getEvenementUser(event.getId_evenement(), UtilisateurService.user.getId());
                int count3 = ListEventUser3.size();
                int sum3 = 0;
                int noteEvent3 = 0;
                for (EvenementUser leu : ListEventUser3) {
                    sum3 += leu.getNote();
                }
                try {
                    noteEvent3 = sum3 / count3;
                } catch (ArithmeticException ex) {
                }

                event.setNote(noteEvent3);
                serviceEvenement.modifierNoteEvent(event, noteEvent3);;
                gg.setProgress(noteEvent3);
            } else {
                EvenementUser t = new EvenementUser(event.getId_evenement(), UtilisateurService.user.getId(), ListEventUser3.get(0).getParticipe(), (int) gg.getProgress());
                serviceEvenementUser.modifierEventParticipeNote(t);
                ArrayList<EvenementUser> ListEventUser4 = serviceEvenementUser.getEvenementUser(event.getId_evenement(), UtilisateurService.user.getId());
                int count3 = ListEventUser3.size();
                int sum3 = 0;

                for (EvenementUser leu : ListEventUser3) {
                    sum3 += leu.getNote();
                }
                int noteEvent3 = sum3 / count3;
                event.setNote(noteEvent3);
                serviceEvenement.modifierNoteEvent(event, noteEvent3);;
                gg.setProgress(noteEvent3);
            }

        });

        cntx0.add(image);
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

        cnty.add(cntx0);
        cnty.add(cntx1);
        cnty.add(cntx2);
        cnty.add(cntx3);
        cnty.add(cntx4);
        cnty.add(cntx5);
        cnty.add(cntx6);

        f.add(cnty);

        f.add(FlowLayout.encloseCenter(gg));
        //}

    }

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(10);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
