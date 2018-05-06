/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.gui;

import com.codename1.components.ImageViewer;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import java.util.ArrayList;
import tn.esprit.cupcake.entities.Patisserie;
import tn.esprit.cupcake.services.PatisserieService;

/**
 *
 * @author Basly
 */
public class ListPatisseriesGui extends BaseForm {

    public ListPatisseriesGui() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public ListPatisseriesGui(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        gui_separator1.setShowEvenIfBlank(true);

        installSidemenu(resourceObjectInstance);



        gui_Text_Area_1.setRows(2);
        gui_Text_Area_1.setColumns(100);
        gui_Text_Area_1.setGrowByContent(false);
        gui_Text_Area_1.setEditable(false);
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1;
    private com.codename1.components.MultiButton gui_Multi_Button_1;
    private com.codename1.components.MultiButton gui_LA;
    private com.codename1.ui.Container gui_imageContainer1;
    private com.codename1.ui.Container gui_Container_2;
    private com.codename1.ui.TextArea gui_Text_Area_1;
    private com.codename1.ui.Button gui_Button_1;
    private com.codename1.ui.Label gui_separator1;
    ImageViewer image = new ImageViewer();

// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {

        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("Patisseries");
        setName("TrendingForm");
        PatisserieService servicePatisserie = new PatisserieService();
        ArrayList<Patisserie> ListPatisseries = servicePatisserie.getAllPatisserie();

        for (Patisserie patisserie : ListPatisseries) {

            gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            gui_Multi_Button_1 = new com.codename1.components.MultiButton();
            gui_LA = new com.codename1.components.MultiButton();
            gui_imageContainer1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            gui_Text_Area_1 = new com.codename1.ui.TextArea();
            gui_Button_1 = new com.codename1.ui.Button();
            gui_separator1 = new com.codename1.ui.Label();

            EncodedImage encImg = EncodedImage.createFromImage(UIManager.initFirstTheme("/theme").getImage("bridge.jpg"), false);

            URLImage imgUrl = URLImage.createToFileSystem(encImg, patisserie.getImage(),  patisserie.getImage(), URLImage.RESIZE_SCALE_TO_FILL);
            image = new ImageViewer(imgUrl);

            addComponent(gui_Container_1);
            gui_Container_1.setName("Container_1");
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Multi_Button_1);
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_LA);
            String dateCreation = new SimpleDateFormat("yyyy-MM-dd").format(patisserie.getDate_creation());
            gui_Multi_Button_1.setUIID("Label");
            gui_Multi_Button_1.setName("Multi_Button_1");
            //gui_Multi_Button_1.setIcon(resourceObjectInstance.getImage("contact-b.png"));
            gui_Multi_Button_1.setPropertyValue("line1", patisserie.getLibelle_patisserie());
            gui_Multi_Button_1.setPropertyValue("line2", dateCreation);
            gui_Multi_Button_1.setPropertyValue("uiid1", "Label");
            gui_Multi_Button_1.setPropertyValue("uiid2", "RedLabel");

            gui_LA.setUIID("Label");
            gui_LA.setName("LA");
            gui_LA.setPropertyValue("line1",patisserie.getAdresse_patisserie());
            gui_LA.setPropertyValue("uiid1", "SlightlySmallerFontLabel");
            gui_LA.setPropertyValue("uiid2", "RedLabelRight");
            //image go map "location"
            FontImage.setMaterialIcon(gui_LA, FontImage.MATERIAL_LOCATION_ON);
            gui_LA.setIconPosition(BorderLayout.EAST);
            gui_LA.addActionListener(e -> {
                new MapPatisserieGui(patisserie);
            });

            addComponent(image);
            image.setName("imageContainer1");

            //image btn go details
            addComponent(gui_imageContainer1);
            gui_imageContainer1.setName("imageContainer1");
            gui_imageContainer1.addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Container_2);

            gui_Container_2.setName("Container_2");
            gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Text_Area_1);
            gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Button_1);
            gui_Text_Area_1.setText(patisserie.getDescription());
            gui_Text_Area_1.setUIID("SlightlySmallerFontLabelLeft");
            gui_Text_Area_1.setName("Text_Area_1");
            gui_Button_1.setText("");
            gui_Button_1.setUIID("Label");
            gui_Button_1.setName("Button_1");
            com.codename1.ui.FontImage.setMaterialIcon(gui_Button_1, "".charAt(0));
            gui_Container_2.setName("Container_2");
            addComponent(gui_separator1);

            //btn go details
            gui_Button_1.addActionListener((e) -> {
                MenuPatisserieGui menu = new MenuPatisserieGui(patisserie);
                menu.getF().show();
            });

        }
    }

// </editor-fold>
//-- DON'T EDIT ABOVE THIS LINE!!!
    @Override
    protected boolean isCurrentTrending() {
        return true;
    }

}
