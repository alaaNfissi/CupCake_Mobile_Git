/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import java.io.IOException;

import java.util.Hashtable;

import tn.esprit.cupcake.entities.Patisserie;
import tn.esprit.cupcake.services.PatisserieService;
import tn.esprit.cupcake.services.UtilisateurService;

/**
 *
 * @author Basly
 */
public class ModifierPatisserieGui extends BaseForm {

    public ModifierPatisserieGui() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public ModifierPatisserieGui(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);

        ScaleImageLabel sl = new ScaleImageLabel(resourceObjectInstance.getImage("skate-park.jpg"));
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        sl = new ScaleImageLabel(resourceObjectInstance.getImage("bridge.jpg"));
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        installSidemenu(resourceObjectInstance);
//        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {
//        });

    }
    private Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private Container sexe_container = new Container(new BoxLayout(BoxLayout.X_AXIS));
    private Container roles_container = new Container(new BoxLayout(BoxLayout.X_AXIS));
    private Label gui_Label_1 = new com.codename1.ui.Label();
    private ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();

    private TextField libelle_patisserie = new TextField();
    private TextField adresse_patisserie = new TextField();
    private Picker date_creation = new Picker();
    private TextField specialite = new TextField();
    private TextField compte_facebook = new TextField("", "", 200, TextArea.URL);
    private TextField compte_instagram = new TextField("", "", 200, TextArea.URL);
    private TextArea description = new TextArea();
    private TextField image = new TextField();
    private Button ModifierPatisserie = new Button();
    private Button selectImage = new Button("Select Image");
    PatisserieService patService = new PatisserieService();
    Patisserie patisserie = patService.getPatisserieUser(UtilisateurService.user.getId());
    String filePath = "";
    Image img = null;
    Container cnt = new Container();
    Image capturedImage;

    private boolean validateInputs() {
        if ((libelle_patisserie.getText() == "") || (adresse_patisserie.getText() == "")
                || (specialite.getText() == "") || (compte_facebook.getText() == "")
                || (compte_instagram.getText() == "") || (description.getText() == "")) {
            Dialog.show("Confirm", "", "Ok", "");
            return false;
        }
        return true;
    }

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {

        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Modifier Patisserie");
        setName("SignUpForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);

        //Label picture = new Label("", "Container");
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Component_Group_1.addComponent(libelle_patisserie);
        gui_Component_Group_1.addComponent(adresse_patisserie);
        gui_Component_Group_1.addComponent(date_creation);
        gui_Component_Group_1.addComponent(specialite);
        gui_Component_Group_1.addComponent(compte_facebook);
        gui_Component_Group_1.addComponent(compte_instagram);
        gui_Component_Group_1.addComponent(description);
        gui_Component_Group_1.addComponent(image);
        cnt.add(img);
        gui_Component_Group_1.addComponent(cnt);
        //gui_Component_Group_1.addComponent(picture);

        libelle_patisserie.setPreferredSize(new Dimension(160, 50));
        adresse_patisserie.setPreferredSize(new Dimension(160, 50));
        date_creation.setPreferredSize(new Dimension(160, 50));
        specialite.setPreferredSize(new Dimension(160, 50));
        compte_facebook.setPreferredSize(new Dimension(160, 50));
        compte_instagram.setPreferredSize(new Dimension(160, 50));
        description.setPreferredSize(new Dimension(160, 50));

        gui_Container_1.addComponent(ModifierPatisserie);
        gui_Label_1.setUIID("CenterLabel");
        gui_Label_1.setName("Label_1");

        gui_Component_Group_1.setName("Component_Group_1");
        ModifierPatisserie.setText("Modifier");
        ModifierPatisserie.setName("Button_2");
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");

        libelle_patisserie.setHint("Libelle Patisserie");
        adresse_patisserie.setHint("Adresse");
        specialite.setHint("Spécialité");
        compte_facebook.setHint("Url page facebook");
        compte_instagram.setHint("Url page instagram");
        description.setHint("Dit quelque chose à propos de votre patisserie ...");
        image.setHint("Image");
        String dateCreation = new SimpleDateFormat("yyyy-MM-dd").format(patisserie.getDate_creation());
        libelle_patisserie.setText(patisserie.getLibelle_patisserie());
        adresse_patisserie.setText(patisserie.getAdresse_patisserie());
        date_creation.setText(dateCreation);
        specialite.setText(patisserie.getSpecialite());
        compte_facebook.setText(patisserie.getCompte_facebook());
        compte_instagram.setText(patisserie.getCompte_instagram());
        description.setText(patisserie.getDescription());
        image.setText(patisserie.getImage());

        Image img2 = null;
        try {
            img2 = Image.createImage(patisserie.getImage());
        } catch (IOException ex) {
        }
        EncodedImage scaledImage2 = EncodedImage.createFromImage(img2, false).scaledEncoded(100, 100);

        //gui_Label_1.setIcon(resourceObjectInstance.getImage("contact-b.png"));
        gui_Label_1.setIcon(scaledImage2);

        gui_Label_1.addPointerPressedListener((ev) -> {
            Display.getInstance().openGallery(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    if (ev != null && ev.getSource() != null) {
                        filePath = (String) ev.getSource();
                        int fileNameIndex = filePath.lastIndexOf("/") + 1;
                        String fileName = filePath.substring(fileNameIndex);

                        try {
                            img = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        File f = new File(filePath);
                        String imageName = f.getAbsolutePath();
                        Image img3 = null;
                        try {
                            img3 = Image.createImage(filePath);
                        } catch (IOException ex) {
                        }
                        EncodedImage scaledImage = EncodedImage.createFromImage(img3, false).scaledEncoded(100, 100);

                        gui_Label_1.setIcon(scaledImage);

                        Hashtable tableItem = new Hashtable();
                        tableItem.put("icon", img.scaled(Display.getInstance().getDisplayHeight() / 10, -1));
                        tableItem.put("emblem", fileName);
						System.out.println("AABBSOLUUTE PATH"+filePath);

                    }
                }
            }, Display.GALLERY_IMAGE);
        });
        ModifierPatisserie.addActionListener((e) -> {
            if ((libelle_patisserie.getText().length() < 1) || (adresse_patisserie.getText().length() < 1)
                    || (specialite.getText().length() < 1) || (compte_facebook.getText().length() < 1)
                    || (compte_instagram.getText().length() < 1) || (description.getText().length() < 1)) {
                Dialog.show("Champ Invalid", "Veillez remplir tout les champs", "Ok", "");
            } else {

                patisserie.setLibelle_patisserie(libelle_patisserie.getText());
                patisserie.setAdresse_patisserie(adresse_patisserie.getText());

                SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

                date_creation.setFormatter(dateFormatter);

                patisserie.setDate_creation(date_creation.getDate());
                patisserie.setSpecialite(specialite.getText());
                patisserie.setCompte_facebook(compte_facebook.getText());
                patisserie.setCompte_instagram(compte_instagram.getText());
                patisserie.setDescription(description.getText());
                if (filePath == "") {
                    patisserie.setImage(patisserie.getImage());
                } else {
                    patisserie.setImage(filePath);
                }

                patService.modifierPatisserie(patisserie);

            }
        });

    }

    @Override
    protected boolean isCurrentTrending() {
        return true;
    }

}
