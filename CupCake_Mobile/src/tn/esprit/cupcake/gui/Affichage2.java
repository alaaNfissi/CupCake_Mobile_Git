/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.gui;


import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import java.util.ArrayList;
import tn.esprit.cupcake.entities.Panier;
import tn.esprit.cupcake.entities.Produit;
import tn.esprit.cupcake.services.PanierService;
import tn.esprit.cupcake.services.ProduitService;
import tn.esprit.cupcake.services.UtilisateurService;

/**
 *
 * @author esprit
 */
public class Affichage2 {
    
    Form f;
    SpanLabel lb;
    Container cnt;
    Label libelle;
    Label prix;
    Label description;
    int idproduit;
  
    public Affichage2() {
        
        f = new Form();
        cnt=new Container();
        lb = new SpanLabel("");
        f.add(lb);
        ProduitService ProduitService=new ProduitService();
        ArrayList<Produit> l = ProduitService.getList3();
        for (int i = 0; i < l.size(); i++) {

            cnt.add(addItem(l.get(i)));

        }
        f.add(cnt);
     //   lb.setText(ProduitService.getList2().toString());
        /*/
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://41.226.11.243:10004/tasks/");
        NetworkManager.getInstance().addToQueue(con);
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceTask ser = new ServiceTask();
                List<Task> list = ser.getListTask(new String(con.getResponseData()));
                System.out.println("sana");
                System.out.println(list);
                lb.setText(list.toString());
               
                System.out.println(lb.getText());
                f.refreshTheme();
            }
        });
        //*/
          f.getToolbar().addCommandToRightBar("back", null, (ev)->{HomeForm h=new HomeForm();
          h.getF().show();
         // h.getHi().show();
      //  h.show();
          });
    }
    
    public Container addItem(Produit p) {
        libelle = new Label(p.getLibelle_produit());
        idproduit = p.getId_produit();
       prix = new Label(String.valueOf(p.getPrix()));
          //     prix.setText(p.getPrix().toString());
        description = new Label(p.getDescription());

        Container cnt1 = new Container(BoxLayout.y());
        Container cnt2 = new Container(BoxLayout.x());
        EncodedImage encImg  = EncodedImage.createFromImage(UIManager.initFirstTheme("/theme").getImage("high.png"),false);
      System.err.println(p.getImage());
      URLImage imgUrl = URLImage.createToStorage(encImg, p.getImage(), "http://localhost/images/"+p.getImage(),URLImage.RESIZE_SCALE_TO_FILL);
     // imgUrl.fetch();
      ImageViewer image = new ImageViewer(imgUrl);
        cnt1.add(image);

     //   Button bt1 = new Button("Ajouter Au Panier");
       // Button bt2 = new Button("Ajouter Aux Favoris");
        
        cnt1.add(libelle);
        cnt1.add(description);
        cnt1.add(prix);
        Button ajouter = new Button();
        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
PanierService ps = new PanierService();
Panier p = new Panier(UtilisateurService.user.getId(),idproduit);
        ps.ajoutPanier(p);
            }
        });
        cnt1.add(ajouter);
      //  cnt1.add(bt1);
      //  cnt1.add(bt2);

        cnt2.add(cnt1);
        
      
        return cnt2;
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
