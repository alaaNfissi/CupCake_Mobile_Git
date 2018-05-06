/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;

import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import tn.esprit.cupcake.entities.Panier;
import tn.esprit.cupcake.entities.Produit;

/**
 *
 * @author Boubaker Ibrahim
 */
public class PanierService {

    public void ajoutPanier(Panier p) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/CupCake_Web_VF-master/web/app_dev.php/Panier/api/ajouterMobilePanier/" + p.getId_utilisateur() + "/" + p.getId_produit();
        con.setUrl(Url);
//        String Url3 = "http://localhost/CupCake_Web_VF/web/app_dev.php/Panier/ajouterMobilePanier?id_utilisateur=" + p.getId_utilisateur() + "&id_produit=" + p.getId_produit();
//     String Url2 = "http://localhost/web-master/web/app_dev.php/api/promotions/new?descritpion=" + ta.getDescription()+ "&etat_promo=" + ta.getEtat_promo();
        System.out.println("tt");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            Dialog.show("Operation Réussie", "Produit Ajouté Avec Succés","Ok",null);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void supprimerPanier(Panier p) {
        ConnectionRequest con = new ConnectionRequest();
//                String Url3 = "http://localhost/CupCake_Web_VF/web/app_dev.php/Panier/supprimerMobilePanier/utilisateur=" + p.getId_utilisateur() + "&produit=" + p.getId_produit();
        String Url = "http://localhost/CupCake_Web_VF-master/web/app_dev.php/Panier/api/supprimerMobilePanier2/"+ p.getId_utilisateur()+ "/" +p.getId_produit();
        con.setUrl(Url);
        System.out.println("tt");
         con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            Dialog.show("Operation Réussie", "Produit Supprimé Avec Succés","Ok",null);
            
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
//    public void remove(Panier p){   
//        ConnectionRequest con = new ConnectionRequest() {
//            @Override
//            protected void postResponse() {
//           Dialog.show("Succes", "supprimer avec succes", "ok", null);
//            }           
//        };
//        con.setUrl("http://localhost/CupCake_Web_VF/web/app_dev.php/Panier/api/supprimerMobilePanier/"+ p.getId_utilisateur()+ "/" +p.getId_produit());
//       con.addResponseListener((e) -> {
//            String str = new String(con.getResponseData());
//            System.out.println(str);
//        NetworkManager.getInstance().addToQueue(con);
//    });
//               }

    public ArrayList<Panier> getListPanier(String json) {

        ArrayList<Panier> listEtudiants = new ArrayList<>();

        try {
            System.out.println("0000000000" + json);
            JSONParser j = new JSONParser();

            Map<String, Object> panierproduit = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println("1111");
            System.out.println(panierproduit);

            List<Map<String, Object>> list = (List<Map<String, Object>>) panierproduit.get("root");
            System.out.println("3333333");
            for (Map<String, Object> obj : list) {
                Panier e = new Panier();
                float idp = Float.parseFloat(obj.get("idPanier").toString());
                e.setId_panier((int) idp);
                float qproduit = Float.parseFloat(obj.get("quantiteProduitPanier").toString());
                e.setId_produit((int) qproduit);
//                String produit = obj.get("produit.getLibelleProduit()").toString();
//                e.setId_produit(produit);
//            e.setId_produit.LibelleProduit(Integer.parseInt(obj.get("produit.getLibelleProduit()").toString()));
                listEtudiants.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listEtudiants);
        return listEtudiants;

    }

    ArrayList<Panier> listTasks = new ArrayList<>();

    public ArrayList<Panier> getList2(Panier p) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1/CupCake_Web_VF-master/web/app_dev.php/Panier/api/getall/" +p.getId_utilisateur());
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                PanierService ser = new PanierService();
                listTasks = ser.getListPanier(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
    
    
    
    public ArrayList<Produit> getListProduit(String json) {

        ArrayList<Produit> listProduits = new ArrayList<>();

        try {
            System.out.println("0000000000" + json);
            JSONParser j = new JSONParser();

            Map<String, Object> panierproduit = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println("1111");
            System.out.println(panierproduit);

            List<Map<String, Object>> list = (List<Map<String, Object>>) panierproduit.get("root");
            System.out.println("3333333");
            for (Map<String, Object> obj : list) {
                Produit p = new Produit();
                float idp = Float.parseFloat(obj.get("idProduit").toString());
                p.setId_produit((int) idp);
                float prix = Float.parseFloat(obj.get("prix").toString());
                p.setPrix((int) prix);
                String nomproduit = obj.get("libelleProduit").toString();
                 String image = obj.get("image").toString();
                 p.setImage(image);
          p.setLibelle_produit(nomproduit);
//            e.setId_produit.LibelleProduit(Integer.parseInt(obj.get("produit.getLibelleProduit()").toString()));
                listProduits.add(p);

            }

        } catch (IOException ex) {
        }
        System.out.println(listProduits);
        return listProduits;

    }
    
    
    
   ArrayList<Produit> listProduit = new ArrayList<Produit>();
   public ArrayList<Produit> getprix(long idUser) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1/CupCake_Web_VF-master/web/app_dev.php/Panier/api/prixpanier/" +idUser);
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                PanierService ser = new PanierService();
                listProduit = ser.getListProduit(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProduit;
    }

}
