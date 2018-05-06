/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.control.Alert;
import tn.esprit.cupcake.entities.Commande;
import tn.esprit.cupcake.entities.Panier;
import tn.esprit.cupcake.entities.Produit;
import tn.esprit.cupcake.services.CommandeService;
import tn.esprit.cupcake.services.PanierService;
import tn.esprit.cupcake.services.UtilisateurService;

/**
 *
 * @author Boubaker Ibrahim
 */
public class AffichagePanier extends BaseForm {
	public static ArrayList<Commande> listCommandesPanier = new ArrayList<>();
    Form f;
    SpanLabel lb;
    Container cnt;
    Label libelle;
    TextField nom;
    TextField adresse;
    TextField secunum;
    TextField cardnum;
    TextField email;
    TextField prenom;
    Label qua;
    SpanLabel lb2;
    float quantite = 0;
    float prixtotal = 0;
    float prixunitaire = 0;
    String NomProduit = "";
	int idCurrPanier;
    ArrayList<Produit> prix = new ArrayList<>();
    
    public AffichagePanier() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public AffichagePanier(com.codename1.ui.util.Resources resourceObjectInstance) {
        f = new Form("Votre Panier");
        cnt = new Container();
         

        PanierService panierser = new PanierService();
        Panier p = new Panier(UtilisateurService.user.getId());
        ArrayList<Panier> l = panierser.getList2(p);
        //for (int i = 0; i < l.size(); i++) {
        for (Panier panier : l) {
            prix = panierser.getprix(UtilisateurService.user.getId());
            quantite = panier.getId_produit();
			idCurrPanier=panier.getId_panier();
			System.out.println("idddddddddddd       "+idCurrPanier);
        }

        for (Produit pr : prix) {

            prixtotal += pr.getPrix();
        }

//        for (Panier panier : l) {
//            
//            cnt.add(addItem(panier));
//        }
        for (Produit pr2 : prix) {

            Container cnt1 = new Container(BoxLayout.y());
            
            Container cnt2 = new Container(BoxLayout.y());
            
            EncodedImage encImg = EncodedImage.createFromImage(UIManager.initFirstTheme("/theme").getImage("high.png"), false);
            System.err.println(pr2.getImage());
            URLImage imgUrl = URLImage.createToStorage(encImg, pr2.getImage(), "http://localhost/images/" + pr2.getImage(), URLImage.RESIZE_SCALE_TO_FILL);
            // imgUrl.fetch();
            ImageViewer image = new ImageViewer(imgUrl);

            Button bt1 = new Button("Supprimer");
            bt1.getUnselectedStyle().setFgColor(0xff0000);
            bt1.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, bt1.getUnselectedStyle()));

            Label lab3 = new Label("La Quantité : 1");
            NomProduit = pr2.getLibelle_produit();
            Label lab1 = new Label("Nom Du Produit" + " : " + NomProduit);
            prixunitaire = pr2.getPrix();
            Label prix = new Label(Float.toString(prixunitaire) + "DT");
            Label lab2 = new Label("Le Prix  Du produit" + " : " + Float.toString(prixunitaire) + "DT");

            Label lnomproduit = new Label(NomProduit);
            cnt1.add(image);
            cnt1.add(lab1);
//            cnt1.add(lnomproduit);
            cnt1.add(lab3);
            cnt2.add(lab2);
//            cnt2.add(prix);
            cnt2.add(bt1);
            bt1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //            ServiceTask ser = new ServiceTask();
//            Task t = new Task(0, tnom.getText(), tetat.getText());
//            ser.ajoutTask(t);
                    PanierService ps = new PanierService();
                    Panier p = new Panier(1, pr2.getId_produit());
                    ps.supprimerPanier(p);
                    AffichagePanier a = new AffichagePanier();
                    a.getF().show();

                }
            });
            cnt.add(cnt1);
            cnt.add(cnt2);
//            for (Panier panier : l) {
//
//                cnt.add(addItem(panier));
//            }

        }
        Container cnt3 = new Container(BoxLayout.x());
        cnt3.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
            cnt3.getUnselectedStyle().setBackgroundGradientEndColor(0xFFBCCA);
            cnt3.getUnselectedStyle().setBackgroundGradientStartColor(0xFFBCCA);
        Label prixTotal = new Label(Float.toString(prixtotal) + "DT");
        Label pr = new Label("Le Prix Total :");
        Button achat = new Button("Terminer l'achat");
        achat.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MONETIZATION_ON, achat.getUnselectedStyle()));
        achat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Form f = new Form("Payement");
                Container cnachat = new Container(BoxLayout.y());
                 
                 nom = new TextField();
                nom.setHint(" Nom");
                nom.setText(UtilisateurService.user.getUsername());
                 prenom = new TextField();
                prenom.setHint(" Prenom");
                prenom.setText(UtilisateurService.user.getPrenom());
                //Label ldate = new Label("Choisir Date de livraison");
                //Picker dateNaissance = new Picker();

                 adresse = new TextField();
                adresse.setHint("Adresse");
                adresse.setText(UtilisateurService.user.getAdresse());
                 cardnum = new TextField();
                cardnum.setHint("Numero carte");
                 secunum = new TextField();
                secunum.setHint("Numero de securité");
                 email = new TextField("", "", 20, TextArea.EMAILADDR);
                email.setHint("Email");
                email.setText(UtilisateurService.user.getEmail());
                TextField pays = new TextField();
                pays.setHint("Country");
                 
                pays.setText("Tunisie");
                RadioButton visa = new RadioButton("Visa");
                EncodedImage encImg  = EncodedImage.createFromImage(UIManager.initFirstTheme("/theme").getImage("visa.png"),false);
      ImageViewer image = new ImageViewer(encImg);
                RadioButton master = new RadioButton("Master-Card");
                EncodedImage encImg2  = EncodedImage.createFromImage(UIManager.initFirstTheme("/theme").getImage("master.png"),false);
      ImageViewer image2 = new ImageViewer(encImg2);
                RadioButton paypal = new RadioButton("Paypal");
                EncodedImage encImg3  = EncodedImage.createFromImage(UIManager.initFirstTheme("/theme").getImage("paypal.png"),false);
      ImageViewer image3 = new ImageViewer(encImg3);
                Button btterminer = new Button("Payement Total " + " " + Float.toString(prixtotal) + "DT");
                btterminer.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MONETIZATION_ON, btterminer.getUnselectedStyle()));
                btterminer.addActionListener((evt) -> {
                    if (controlesaisie())
                    {
                      if (Dialog.show("Attention ", "Vous allez payé une somme de "+" "+prixtotal+"DT"+" "+" étes vous sur de vouloir payer ?", "Oui", "Non")) {
                ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setMessage("Verification en cours veuillez patientez s'il vous plait");
                    status.setProgress(0);
//            status.setIcon(createIcon(FontImage.MATERIAL_BACKUP));
                    status.show();

                    for (int progress = 0; progress <= 100; progress += 20) {
                        Display.getInstance().invokeAndBlock(() -> {
                            Util.sleep(1000);
                        });
                        status.setProgress(progress);
                        status.show();
                        if (progress == 100) {
							CommandeService cs=new CommandeService();
							listCommandesPanier=cs.PasserCommande(idCurrPanier, prixtotal);
                            status.setProgress(-1);
                            status.setExpires(3000);
                            status.setMessage("Achat Terminer Avec Succés , Nous Vous Remercions Pour Votre Fidélité");
                            status.show();
							new PanierForm().show();
                        }
                    }
               
            }
                      
                    }     
                });

                cnachat.add(visa);
                cnachat.add(image);
                cnachat.add(master);
                cnachat.add(image2);
                cnachat.add(paypal);
                cnachat.add(image3);
                cnachat.add(nom);
                cnachat.add(prenom);
                cnachat.add(pays);
                cnachat.add(cardnum);
                cnachat.add(secunum);
                cnachat.add(email);
                //cnachat.add(ldate);
                //cnachat.add(dateNaissance);
                cnachat.add(adresse);
                cnachat.add(btterminer);

                f.add(cnachat);
                Form hi = new Form("Mon Calendrier", new BorderLayout());
                Calendar cld = new Calendar();
                cld.addActionListener((c) -> Log.p("You picked: " + new Date(cld.getSelectedDay())));
                hi.add(BorderLayout.CENTER, cld);
                f.add(hi);

                f.show();

                f.getToolbar().addCommandToRightBar("Retour", null, (ev) -> {
                    AffichagePanier a=new AffichagePanier( );
        
				a.getF().show();
                    
                });

            }
        });

//        Button btcalendrier = new Button("Voir les jours");
//        btcalendrier.setIcon(FontImage.createMaterial(FontImage.MATERIAL_VIEW_DAY, btcalendrier.getUnselectedStyle()));
//        btcalendrier.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//   Form hi = new Form("Calendar", new BorderLayout());
//Calendar cld = new Calendar();
//cld.addActionListener((c) -> Log.p("You picked: " + new Date(cld.getSelectedDay())));
//hi.add(BorderLayout.CENTER, cld);
//f.add(hi);
//            }
//        });  
//        f.add(btcalendrier);
        cnt3.add(pr);
        cnt3.add(prixTotal);
        Container conachat = new Container(BoxLayout.x());
        conachat.add(achat);
        cnt.add(cnt3);
        cnt.add(conachat);
        f.add(cnt);
        
        
        f.getToolbar().addCommandToRightBar("Retour", null, (ev) -> {
           TrendingForm tf = new TrendingForm();
                   tf.show();
                    
                });
        
        

    
    }
   
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }

        return true;
    }
     private boolean controlesaisie() {

         if (!isInteger(cardnum.getText())) {
            Dialog.show("Attention ", "veuillez saisir un numéro  dans le champ Numéro de la carte", "Oui",null);
            return false;
        }  else if (!isInteger(secunum.getText())) {
                        Dialog.show("Attention ", "veuillez saisir un numéro dans le numéro de securité composé de 4 chiffres  ", "Oui", null);

            return false;
            
        }   else if (isInteger(adresse.getText())) {
                        Dialog.show("Attention ", "veuillez mettre votre Adresse correctement", "Oui", null);

            return false;
        }
         else if (isInteger(nom.getText())) {
                        Dialog.show("Attention ", "veuillez saisir votre Nom correctement ", "Oui", null);

            return false;
        } 
         else if (isInteger(prenom.getText())) {
                        Dialog.show("Attention ", "veuillez saisir votre Prenom correctement ", "Oui", null);

            return false;
        } 
         else if ((secunum.getText().length()>5)) {
                        Dialog.show("Attention ", "veuillez saisir un numéro dans le numéro de securité composé de 4 chiffres  ", "Oui", null);

            return false;
        } 
         else if (isInteger(email.getText())) {
                        Dialog.show("Attention ", "veuillez saisir votre Email correctement ", "Oui", null);

            return false;
        } 

        return true;
    }
    
    
    public Container addItem(Panier p) {

        qua = new Label(Integer.toString(p.getId_produit()));

        Container cnt1 = new Container(BoxLayout.x());
//        cnt1.setUIID("Container_uiid_name");
//        cnt1.getStyle().setBgColor(0x99CCCC);
cnt1.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
            cnt1.getUnselectedStyle().setBackgroundGradientEndColor(0xFFBCCA);
            cnt1.getUnselectedStyle().setBackgroundGradientStartColor(0xFFBCCA);
        Container cnt2 = new Container(BoxLayout.x());
//         Container cnt3 = new Container(BoxLayout.y());

//        Label lab2 = new Label("Quantité Produit Panier");
//        cnt1.add(lab2);
        cnt1.add(qua);

//         cnt3.add(cnt1);
        cnt2.add(cnt1);
        return cnt2;

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }


//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.components.MultiButton gui_Multi_Button_1 = new com.codename1.components.MultiButton();
    private com.codename1.components.MultiButton gui_LA = new com.codename1.components.MultiButton();
    private com.codename1.ui.Container gui_imageContainer1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.TextArea gui_Text_Area_1 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
    private com.codename1.ui.Label gui_separator1 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_null_1_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.components.MultiButton gui_null_1_1_1 = new com.codename1.components.MultiButton();
    private com.codename1.components.MultiButton gui_newYork = new com.codename1.components.MultiButton();
    private com.codename1.ui.Container gui_imageContainer2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.TextArea gui_Text_Area_2 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    private com.codename1.ui.Label gui_Label_1_1_1 = new com.codename1.ui.Label();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("Trending");
        setName("TrendingForm");
        addComponent(gui_Container_1);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Multi_Button_1);
        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_LA);
        gui_Multi_Button_1.setUIID("Label");
        gui_Multi_Button_1.setName("Multi_Button_1");
        gui_Multi_Button_1.setIcon(resourceObjectInstance.getImage("contact-c.png"));
        gui_Multi_Button_1.setPropertyValue("line1", "Ami Koehler");
        gui_Multi_Button_1.setPropertyValue("line2", "@dropperidiot");
        gui_Multi_Button_1.setPropertyValue("uiid1", "Label");
        gui_Multi_Button_1.setPropertyValue("uiid2", "RedLabel");
        gui_LA.setUIID("Label");
        gui_LA.setName("LA");
        gui_LA.setPropertyValue("line1", "3 minutes ago");
        gui_LA.setPropertyValue("line2", "in Los Angeles");
        gui_LA.setPropertyValue("uiid1", "SlightlySmallerFontLabel");
        gui_LA.setPropertyValue("uiid2", "RedLabelRight");
        addComponent(gui_imageContainer1);
        gui_imageContainer1.setName("imageContainer1");
        gui_imageContainer1.addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Container_2);
        gui_Container_2.setName("Container_2");
        gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Text_Area_1);
        gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Button_1);
        gui_Text_Area_1.setText("The park is a favorite among skaters in California and it definitely deserves it. The park is complete with plenty of smooth banks to gain a ton of speed in the flow bowl.");
        gui_Text_Area_1.setUIID("SlightlySmallerFontLabelLeft");
        gui_Text_Area_1.setName("Text_Area_1");
        gui_Button_1.setText("");
        gui_Button_1.setUIID("Label");
        gui_Button_1.setName("Button_1");
        com.codename1.ui.FontImage.setMaterialIcon(gui_Button_1,"".charAt(0));
                gui_Container_2.setName("Container_2");
        addComponent(gui_separator1);
        addComponent(gui_null_1_1);
        gui_null_1_1.setName("null_1_1");
        gui_null_1_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_null_1_1_1);
        gui_null_1_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_newYork);
        gui_null_1_1_1.setUIID("Label");
        gui_null_1_1_1.setName("null_1_1_1");
        gui_null_1_1_1.setIcon(resourceObjectInstance.getImage("contact-b.png"));
        gui_null_1_1_1.setPropertyValue("line1", "Detra Mcmunn");
        gui_null_1_1_1.setPropertyValue("line2", "@dropperidiot");
        gui_null_1_1_1.setPropertyValue("uiid1", "Label");
        gui_null_1_1_1.setPropertyValue("uiid2", "RedLabel");
        gui_newYork.setUIID("Label");
        gui_newYork.setName("newYork");
        gui_newYork.setPropertyValue("line1", "15 minutes ago");
        gui_newYork.setPropertyValue("line2", "in New York");
        gui_newYork.setPropertyValue("uiid1", "SlightlySmallerFontLabel");
        gui_newYork.setPropertyValue("uiid2", "RedLabelRight");
        addComponent(gui_imageContainer2);
        gui_imageContainer2.setName("imageContainer2");
        gui_imageContainer2.addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Container_3);
        gui_Container_3.setName("Container_3");
        gui_Container_3.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Text_Area_2);
        gui_Container_3.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Button_2);
        gui_Text_Area_2.setText("Griffith Observatory is a facility in Los Angeles, California sitting on the south-facing slope of Mount Hollywood in Los Angeles' Griffith Park.");
        gui_Text_Area_2.setUIID("SlightlySmallerFontLabelLeft");
        gui_Text_Area_2.setName("Text_Area_2");
        gui_Button_2.setText("");
        gui_Button_2.setUIID("Label");
        gui_Button_2.setName("Button_2");
        com.codename1.ui.FontImage.setMaterialIcon(gui_Button_2,"".charAt(0));
        gui_Container_3.setName("Container_3");
        addComponent(gui_Label_1_1_1);
        gui_Container_1.setName("Container_1");
        gui_imageContainer1.setName("imageContainer1");
        gui_separator1.setUIID("Separator");
        gui_separator1.setName("separator1");
        gui_null_1_1.setName("null_1_1");
        gui_imageContainer2.setName("imageContainer2");
        gui_Label_1_1_1.setUIID("Separator");
        gui_Label_1_1_1.setName("Label_1_1_1");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!

    @Override
    protected boolean isCurrentTrending() {
        return true;
    }
    
}
