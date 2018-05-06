/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import tn.esprit.cupcake.entities.Patisserie;

/**
 *
 * @author Basly
 */
public class MenuPatisserieGui extends BaseForm {

    Form hi = new Form("", new GridLayout(2, 2));
    Container cnty = new Container(BoxLayout.y());
    Container cntx0 = new Container(BoxLayout.x());
    Container cntx1 = new Container(BoxLayout.x());
    Container cntx2 = new Container(BoxLayout.x());
    Button Produits = new Button("Produits");
    Button Evenements = new Button("Evenements");
    Button Catalogues = new Button("Catalogues");
    Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
    FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_BACKSPACE, s);

    MenuPatisserieGui(Patisserie patisserie) {
        hi.getToolbar().addCommandToLeftBar("", icon, (e) -> new ListPatisseriesGui().show());

        FontImage.setMaterialIcon(Evenements, FontImage.MATERIAL_DATE_RANGE);
        FontImage.setMaterialIcon(Catalogues, FontImage.MATERIAL_CARD_GIFTCARD);
        FontImage.setMaterialIcon(Produits, FontImage.MATERIAL_ROOM_SERVICE);

        Evenements.setPreferredSize(new Dimension(200, 100));
        Catalogues.setPreferredSize(new Dimension(200, 100));
        Produits.setPreferredSize(new Dimension(200, 100));
        hi.setTitle("Menu");
        cntx0.add(Produits);
        cntx1.add(Catalogues);
        cntx2.add(Evenements);
        cnty.add(cntx0);
        cnty.add(cntx1);
        cnty.add(cntx2);
        hi.add(cnty);

        Produits.addPointerPressedListener((e) -> {
            new ListProduitPromotionGui(patisserie).show();
        });

        Catalogues.addPointerPressedListener((e) -> {
            System.out.println("Catalogues" + patisserie.getLibelle_patisserie());
        });
        Evenements.addPointerPressedListener((e) -> {
            new ListEvenementClientGui(patisserie).show();
        });

    }

//-- DON'T EDIT ABOVE THIS LINE!!!
    @Override
    protected boolean isCurrentTrending() {
        return true;
    }

    public Form getF() {
        return hi;
    }

    public void setF(Form f) {
        this.hi = f;
    }

}
