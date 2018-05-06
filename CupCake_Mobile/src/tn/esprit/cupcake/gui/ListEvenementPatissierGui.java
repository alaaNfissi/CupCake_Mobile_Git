/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package tn.esprit.cupcake.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import tn.esprit.cupcake.entities.Evenement;
import tn.esprit.cupcake.entities.Patisserie;
import tn.esprit.cupcake.services.EvenementService;
import tn.esprit.cupcake.services.PatisserieService;
import tn.esprit.cupcake.services.UtilisateurService;

/**
 * GUI builder created Form
 *
 * @author shai
 */
public class ListEvenementPatissierGui extends BaseForm {
//    PatisserieService ps = new PatisserieService();
//        ArrayList<Patisserie> Patisserie = ps.getPatisserieById(1);

    EvenementService serviceEvenement = new EvenementService();
    PatisserieService servicePatisserie = new PatisserieService();
    Patisserie Patisserie = servicePatisserie.getPatisserieUser(UtilisateurService.user.getId());
    ArrayList<Evenement> ListEventByPatisserie = serviceEvenement.getEvenementsByPatisserie(Patisserie.getId_patisserie());

    public ListEvenementPatissierGui() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    @Override
    protected boolean isCurrentInbox() {
        return true;
    }

    public ListEvenementPatissierGui(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Evenements", "Title"),
                        new Label(String.valueOf(ListEventByPatisserie.size()), "InboxNumber")
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
        fab.setVisible(false);

    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1;
    private com.codename1.ui.Container gui_Container_2;
    private com.codename1.ui.Label gui_Label_1;
    private com.codename1.ui.Container gui_Container_4;
    private com.codename1.ui.Label gui_Label_4;
    private com.codename1.ui.Container gui_Container_3;
    private com.codename1.ui.Label gui_Label_3;
    private com.codename1.ui.Label gui_Label_2;
    private com.codename1.ui.TextArea gui_Text_Area_1;
    private com.codename1.ui.Label gui_Label_6;
    private com.codename1.ui.Container gui_Container_1_1;
    private com.codename1.ui.Container gui_Container_2_1;
    private com.codename1.ui.Label gui_Label_1_1;
    private com.codename1.ui.Container gui_Container_4_1;
    private com.codename1.ui.Label gui_Label_4_1;
    private com.codename1.ui.Container gui_Container_3_1;
    private com.codename1.ui.Label gui_Label_3_1;
    private com.codename1.ui.Label gui_Label_2_1;
    private com.codename1.ui.TextArea gui_Text_Area_1_1;
    private com.codename1.ui.Label gui_Label_7;
    private com.codename1.ui.Container gui_Container_1_2;
    private com.codename1.ui.Container gui_Container_2_2;
    private com.codename1.ui.Label gui_Label_1_2;
    private com.codename1.ui.Container gui_Container_4_2;
    private com.codename1.ui.Label gui_Label_4_2;
    private com.codename1.ui.Container gui_Container_3_2;
    private com.codename1.ui.Label gui_Label_3_2;
    private com.codename1.ui.Label gui_Label_2_2;
    private com.codename1.ui.TextArea gui_Text_Area_1_2;
    private com.codename1.ui.Label gui_Label_8;
    private com.codename1.ui.Container gui_Container_1_3;
    private com.codename1.ui.Container gui_Container_2_3;
    private com.codename1.ui.Label gui_Label_1_3;
    private com.codename1.ui.Container gui_Container_4_3;
    private com.codename1.ui.Label gui_Label_4_3;
    private com.codename1.ui.Container gui_Container_3_3;
    private com.codename1.ui.Label gui_Label_3_3;
    private com.codename1.ui.Label gui_Label_2_3;
    private com.codename1.ui.TextArea gui_Text_Area_1_3;
    private com.codename1.ui.Label gui_Label_9;
    private com.codename1.ui.Container gui_Container_1_4;
    private com.codename1.ui.Container gui_Container_2_4;
    private com.codename1.ui.Label gui_Label_1_4;
    private com.codename1.ui.Container gui_Container_4_4;
    private com.codename1.ui.Label gui_Label_4_4;
    private com.codename1.ui.Container gui_Container_3_4;
    private com.codename1.ui.Label gui_Label_3_4;
    private com.codename1.ui.Label gui_Label_2_4;
    private com.codename1.ui.TextArea gui_Text_Area_1_4;
    private com.codename1.ui.Label gui_Label_5;
    ImageViewer image = new ImageViewer();
    private Resources theme;

// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        //System.out.println("PPPPAAAT"+Patisserie.get(0).getId_patisserie());
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("InboxForm");
        setName("InboxForm");
        for (Evenement event : ListEventByPatisserie) {

            gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
            gui_Label_1 = new com.codename1.ui.Label();
            gui_Container_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
            gui_Label_4 = new com.codename1.ui.Label();
            gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
            gui_Label_3 = new com.codename1.ui.Label();
            gui_Label_2 = new com.codename1.ui.Label();
            gui_Text_Area_1 = new com.codename1.ui.TextArea();
            gui_Label_6 = new com.codename1.ui.Label();
            gui_Container_1_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            gui_Container_2_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
            gui_Label_1_1 = new com.codename1.ui.Label();
            gui_Container_4_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
            gui_Label_4_1 = new com.codename1.ui.Label();
            gui_Container_3_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
            gui_Label_3_1 = new com.codename1.ui.Label();
            gui_Label_2_1 = new com.codename1.ui.Label();
            gui_Text_Area_1_1 = new com.codename1.ui.TextArea();
            gui_Label_7 = new com.codename1.ui.Label();
            gui_Container_1_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            gui_Container_2_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
            gui_Label_1_2 = new com.codename1.ui.Label();
            gui_Container_4_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
            gui_Label_4_2 = new com.codename1.ui.Label();
            gui_Container_3_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
            gui_Label_3_2 = new com.codename1.ui.Label();
            gui_Label_2_2 = new com.codename1.ui.Label();
            gui_Text_Area_1_2 = new com.codename1.ui.TextArea();
            gui_Label_8 = new com.codename1.ui.Label();
            gui_Container_1_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            gui_Container_2_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
            gui_Label_1_3 = new com.codename1.ui.Label();
            gui_Container_4_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
            gui_Label_4_3 = new com.codename1.ui.Label();
            gui_Container_3_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
            gui_Label_3_3 = new com.codename1.ui.Label();
            gui_Label_2_3 = new com.codename1.ui.Label();
            gui_Text_Area_1_3 = new com.codename1.ui.TextArea();
            gui_Label_9 = new com.codename1.ui.Label();
            gui_Container_1_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            gui_Container_2_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
            gui_Label_1_4 = new com.codename1.ui.Label();
            gui_Container_4_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
            gui_Label_4_4 = new com.codename1.ui.Label();
            gui_Container_3_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
            gui_Label_3_4 = new com.codename1.ui.Label();
            gui_Label_2_4 = new com.codename1.ui.Label();
            gui_Text_Area_1_4 = new com.codename1.ui.TextArea();
            gui_Label_5 = new com.codename1.ui.Label();
            //create placeholder image
            EncodedImage encImg = EncodedImage.createFromImage(UIManager.initFirstTheme("/theme").getImage("medium.png"), false);;
            //get image from the web
            URLImage urlimg = URLImage.createToStorage(encImg, event.getImage(), "http://localhost/images/" + event.getImage(), URLImage.RESIZE_SCALE_TO_FILL);
            //try to convert and scale
            EncodedImage scaledImage = EncodedImage.createFromImage(urlimg, false).scaledEncoded(50, 50);
            //display image using a label

            addComponent(gui_Container_1);
            gui_Container_1.setName("Container_1");
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Container_2);
            gui_Container_2.setName("Container_2");
            gui_Container_2.addComponent(gui_Label_1);
            gui_Label_1.setIcon(scaledImage);

            gui_Label_1.setUIID("SmallFontLabel");
            gui_Label_1.setName("Label_1");
            Button myBtn = new Button();
            myBtn.addActionListener(e -> {
                    DetailEventPatissierGui details = new DetailEventPatissierGui (event);
                details.getF().show();
            });


            gui_Container_1.setLeadComponent(myBtn);

            // EncodedImage encImg = EncodedImage.createFromImage(UIManager.initFirstTheme("/theme").getImage("medium.png"), false);
            // System.err.println(event.getImage());
            // System.out.println("11111111111 image" + event.getImage());
            //       URLImage imgUrl = URLImage.createToStorage(encImg, event.getImage(), "http://localhost:8085/images/" + event.getImage(), URLImage.RESIZE_SCALE_TO_FILL);
            //image = new ImageViewer(imgUrl);
//            addComponent(image);
//            image.setName("imageContainer1");
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.WEST, gui_Container_4);
            gui_Container_4.setName("Container_4");
            ((com.codename1.ui.layouts.FlowLayout) gui_Container_4.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
            gui_Container_4.addComponent(gui_Label_4);
            gui_Label_4.setUIID("Padding2");
            gui_Label_4.setName("Label_4");
            gui_Label_4.setIcon(resourceObjectInstance.getImage("label_round-selected.png"));
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_3);
            gui_Container_3.setName("Container_3");
            gui_Container_3.addComponent(gui_Label_3);
            gui_Container_3.addComponent(gui_Label_2);
            gui_Container_3.addComponent(gui_Text_Area_1);
            gui_Label_3.setText(event.getLibelle_evenement());
            gui_Label_3.setName("Label_3");
            gui_Label_2.setText(event.getAdresse_evenement());
            gui_Label_2.setUIID("RedLabel");
            gui_Label_2.setName("Label_2");
            gui_Text_Area_1.setText(event.getDescription());
            gui_Text_Area_1.setUIID("SmallFontLabel");
            gui_Text_Area_1.setName("Text_Area_1");
            gui_Container_2.setName("Container_2");
            gui_Container_4.setName("Container_4");
            ((com.codename1.ui.layouts.FlowLayout) gui_Container_4.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
            gui_Container_3.setName("Container_3");
            addComponent(gui_Label_6);
            gui_Label_6.setText("");
            gui_Label_6.setUIID("Separator");
            gui_Label_6.setName("Label_6");

        }

    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
