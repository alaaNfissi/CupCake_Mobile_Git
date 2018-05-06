/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.gui;

import com.codename1.components.ImageViewer;
import java.util.ArrayList;
import tn.esprit.cupcake.entities.Patisserie;
import tn.esprit.cupcake.entities.Produit;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.plaf.Style;
import tn.esprit.cupcake.services.PromotionService;
import com.codename1.ui.plaf.UIManager;
import java.io.IOException;
import tn.esprit.cupcake.entities.ProduitPromotion;

/**
 *
 * @author Basly
 */
public class ListProduitPromotionGui extends BaseForm {
   Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_BACKSPACE, s);
    public ListProduitPromotionGui(Patisserie patisserie) {
        this(com.codename1.ui.util.Resources.getGlobalResources(), patisserie);
    }

    public ListProduitPromotionGui(com.codename1.ui.util.Resources resourceObjectInstance, Patisserie patisserie) {
        initGuiBuilderComponents(resourceObjectInstance, patisserie);
  
       
        getToolbar().addCommandToLeftBar("", icon, (e) -> new ListPatisseriesGui().show());

    }
    private com.codename1.ui.Container gui_Container_1;
    private com.codename1.components.MultiButton gui_Multi_Button_1;
    private com.codename1.components.MultiButton gui_LA;
    private com.codename1.ui.Container gui_imageContainer1;
    private com.codename1.ui.Container gui_Container_2;
    private com.codename1.ui.TextArea gui_Text_Area_1;
    private com.codename1.ui.Button gui_Button_1;
    private com.codename1.ui.Label gui_separator1;
    Label produitPromotion = new Label("Produits en promotion");
    ImageViewer image = new ImageViewer();
    

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance, Patisserie patisserie) {
     
        PromotionService promoService = new PromotionService();
        ArrayList<Produit> listProduit = promoService.getProduitsPromosByPatisserie(patisserie.getId_patisserie());

        ArrayList<ProduitPromotion> listPrixProduit = promoService.getAllProduitsPromosByPatisserie(patisserie.getId_patisserie());

        System.out.println("PAAAAAAAAAAAAAAT" + patisserie.getLibelle_patisserie());
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("Produits");
        setName("TrendingForm");

        add(produitPromotion);
        for (Produit produit : listProduit) {

            //System.out.println("pppprprprprpprprprprpr"+promoService.getAllProduitsPromosByPatisserie(patisserie.getId_patisserie(),produit.getId_produit()));
            gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            gui_Multi_Button_1 = new com.codename1.components.MultiButton();
            gui_LA = new com.codename1.components.MultiButton();
            gui_imageContainer1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            gui_Text_Area_1 = new com.codename1.ui.TextArea();
            gui_Button_1 = new com.codename1.ui.Button();
            gui_separator1 = new com.codename1.ui.Label();
            gui_Text_Area_1.setEditable(false);
            EncodedImage encImg = EncodedImage.createFromImage(UIManager.initFirstTheme("/theme").getImage("bridge.jpg"), false);
            System.err.println(produit.getImage());
            System.out.println("11111111111 image" + produit.getImage());
            URLImage imgUrl = URLImage.createToStorage(encImg, produit.getImage(), "http://localhost:8085/images/" + produit.getImage(), URLImage.RESIZE_SCALE_TO_FILL);
            image = new ImageViewer(imgUrl);

            addComponent(gui_Container_1);
            gui_Container_1.setName("Container_1");
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Multi_Button_1);
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_LA);

            gui_Multi_Button_1.setUIID("Label");
            gui_Multi_Button_1.setName("Multi_Button_1");
            Image img2 = null;
            try {
                img2 = Image.createImage(patisserie.getImage());
            } catch (IOException ex) {
            }
            EncodedImage scaledImage2 = EncodedImage.createFromImage(img2, false).scaledEncoded(50, 50);

            //gui_Label_1.setIcon(resourceObjectInstance.getImage("contact-b.png"));
            gui_Multi_Button_1.setIcon(scaledImage2);
            gui_Multi_Button_1.setPropertyValue("line1", produit.getLibelle_produit());
            gui_Multi_Button_1.setPropertyValue("uiid1", "Label");
            gui_Multi_Button_1.setPropertyValue("uiid2", "RedLabel");

            gui_LA.setUIID("Label");
            gui_LA.setName("LA");
            gui_LA.setPropertyValue("line1", produit.getCategorie());
            gui_LA.setPropertyValue("uiid1", "SlightlySmallerFontLabel");
            gui_LA.setPropertyValue("uiid2", "RedLabelRight");

            addComponent(image);
            image.setName("imageContainer1");

            //image btn go details
            addComponent(gui_imageContainer1);
            gui_imageContainer1.setName("imageContainer1");
            gui_imageContainer1.addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Container_2);

            gui_Container_2.setName("Container_2");
            gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Text_Area_1);
            gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Button_1);

            for (ProduitPromotion pp : listPrixProduit) {
                if (produit.getId_produit() == pp.getId_produit()) {
                    String prixPromotion = String.valueOf(pp.getPrix_promotion());
                    Label prixPromotionLabel = new Label(prixPromotion);
                    prixPromotionLabel.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
                    prixPromotionLabel.getUnselectedStyle().setBackgroundGradientEndColor(0xFF0000);
                    prixPromotionLabel.getUnselectedStyle().setBackgroundGradientStartColor(0xFF0000);
                    gui_Text_Area_1.setText("Prix produit : "+String.valueOf(produit.getPrix()) + "            Prix promotion : " + prixPromotionLabel.getText());
                }
            }
            gui_Text_Area_1.setUIID("SlightlySmallerFontLabelLeft");
            gui_Text_Area_1.setName("Text_Area_1");
            gui_Button_1.setText("");
            gui_Button_1.setUIID("Label");
            gui_Button_1.setName("Button_1");
//            com.codename1.ui.FontImage.setMaterialIcon(gui_Button_1, "".charAt(0));
//            gui_Container_2.setName("Container_2");
            addComponent(gui_separator1);

            //btn go details
//            gui_Button_1.addActionListener((e) -> {
//                DetailEventClientGui details = new DetailEventClientGui(event,patisserie);
//                details.getF().show();
//            });
        }

    }

    @Override
    protected boolean isCurrentTrending() {
        return true;
    }

}
