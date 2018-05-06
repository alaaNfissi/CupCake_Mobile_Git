/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import tn.esprit.cupcake.entities.Produit;
import tn.esprit.cupcake.entities.ProduitPromotion;
import tn.esprit.cupcake.entities.Promotion;

/**
 *
 * @author Basly
 */
public class PromotionService {

    public ArrayList<Promotion> getPromotionByPatisserie(String json) {
        ArrayList<Promotion> listPromotionsByPatisserie = new ArrayList<>();
        try {
            System.out.println("11111111111111" + json);
            JSONParser j = new JSONParser();
            Map<String, Object> promotions = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println("2222222222222" + promotions);
            List<Map<String, Object>> list = (List<Map<String, Object>>) promotions.get("root");
            System.out.println("5555555555555555" + list);
            for (Map<String, Object> obj : list) {
                Promotion e = new Promotion();
                float id = Float.parseFloat(obj.get("idPromotion").toString());
                System.out.println(id);
                e.setId_promotion((int) id);

//                float idPatisserie = Float.parseFloat(obj.get("idPatisserie").toString());
//                System.out.println(idPatisserie);
//                e.setId_evenement((int) idPatisserie);
                e.setLibelle_promotion(obj.get("libellePromotion").toString());

                System.out.println("33333333333333333333 ****  ** *  " + e.toString());
                listPromotionsByPatisserie.add(e);
            }
        } catch (IOException ex) {
        }
        System.out.println("4444444444444444" + listPromotionsByPatisserie);
        return listPromotionsByPatisserie;
    }
    ArrayList<Promotion> PromotionsByPatisserie = new ArrayList<>();

    public ArrayList<Promotion> getPromotionsByPatisserie(int idPatisserie) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CupCake_Web_VF-master/web/app_dev.php/Promotion/api/promoByPatisserie?idPatisserie=" + idPatisserie);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                PromotionService ser = new PromotionService();
                PromotionsByPatisserie = ser.getPromotionByPatisserie(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return PromotionsByPatisserie;

    }

    public ArrayList<Produit> getProduitPromotionByPatisserie(String json) {
        ArrayList<Produit> listProduits = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> produits = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) produits.get("root");
            for (Map<String, Object> obj : list) {
                Produit p = new Produit();
                float id = Float.parseFloat(obj.get("idProduit").toString());
                System.out.println(id);
                p.setId_produit((int) id);
                p.setCategorie(obj.get("categorie").toString());
                p.setLibelle_produit(obj.get("libelleProduit").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());
                p.setPrix(prix);
                p.setImage(obj.get("image").toString());
                listProduits.add(p);
            }
        } catch (IOException ex) {
        }
        return listProduits;
    }
    ArrayList<Produit> ProduitsPromos = new ArrayList<>();

    public ArrayList<Produit> getProduitsPromosByPatisserie(int idPatisserie) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CupCake_Web_VF-master/web/app_dev.php/Promotion/api/produitPromo?idPatisserie=" + idPatisserie);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                PromotionService ser = new PromotionService();
                ProduitsPromos = ser.getProduitPromotionByPatisserie(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return ProduitsPromos;

    }

    public ArrayList<ProduitPromotion> getAllProduitPromotionByPatisserie(String json) {
        ArrayList<ProduitPromotion> listProduitsPromotion = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> produitPromotion = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) produitPromotion.get("root");
            for (Map<String, Object> obj : list) {
                ProduitPromotion p = new ProduitPromotion();
                Map<String, Object> produit = (Map<String, Object>) obj.get("idProduit");
                
                float idProduit = Float.parseFloat(produit.get("idProduit").toString());
                System.out.println(idProduit);
                System.out.println("IDDDDDDDDDDDDDDDDDDDDD::" + idProduit);
                p.setId_produit((int) idProduit);

                Map<String, Object> promotion = (Map<String, Object>) obj.get("idPromotion");

                float idPromotion = Float.parseFloat(promotion.get("idPromotion").toString());
                System.out.println(idProduit);
                p.setId_promotion((int) idPromotion);

                System.out.println("IDDDDDDDDDDDDDDDDDDDDD::" + idPromotion);

//                
//                float idProduit = Float.parseFloat(obj.get("idProduit").toString());
//                System.out.println(idProduit);
//                p.setId_produit((int) idProduit);
//
//                
//                float idPromotion = Float.parseFloat(obj.get("idPromotion").toString());
//                System.out.println(idPromotion);
//                p.setId_promotion((int) idPromotion);
                float prixPromotion = Float.parseFloat(obj.get("prixPromotion").toString());
                p.setPrix_promotion(prixPromotion);

                listProduitsPromotion.add(p);
            }
        } catch (IOException ex) {
        }
        return listProduitsPromotion;
    }
    ArrayList<ProduitPromotion> ProduitPromotion = new ArrayList<>();

    public ArrayList<ProduitPromotion> getAllProduitsPromosByPatisserie(int idPatisserie) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CupCake_Web_VF-master/web/app_dev.php/Promotion/api/AllproduitPromo?idPatisserie=" + idPatisserie);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                PromotionService ser = new PromotionService();
                ProduitPromotion = ser.getAllProduitPromotionByPatisserie(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return ProduitPromotion;

    }
//    public ArrayList<Object> getAllProduitsPromosByPatisserie(int idProduit,String json) {
//        ArrayList<Object> lo = new ArrayList<Object>();
////        try {
////            JSONParser j = new JSONParser();
////            Map<String, Object> produitPromotion = j.parseJSON(new CharArrayReader(json.toCharArray()));
////            List<Map<String, Object>> list = (List<Map<String, Object>>) produitPromotion.get("root");
////            for (Map<String, Object> obj : list) {
////                int prixPromotion =(int) obj.get(idProduit);
////                lo.add(obj);
////            }
////        } catch (IOException err) {
////        }
//
//        JSONParser j = new JSONParser();
//        try {
//            Map<String, Object> data = j.parseJSON(new CharArrayReader(json.toCharArray()));
//            java.util.List<Map<String, Object>> content = (java.util.List<Map<String, Object>>) data.get("root");
//            for (Map<String, Object> obj : content) {
//                String url = (String) obj.get("url");
//
//            }
//        } catch (IOException err) {
//        }
//        return lo;
//    }
//    ArrayList<Object> lo = new ArrayList<Object>();
//
//    public ArrayList<Object> getAllProduitsPromosByPatisserie(int idPatisserie, int idProduit) {
//        ConnectionRequest con = new ConnectionRequest();
//        con.setUrl("http://localhost:8085/CupCake_Web_VF/web/app_dev.php/Promotion/api/AllproduitPromo?idPatisserie=" + idPatisserie);
//        con.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                PromotionService ser = new PromotionService();
//                lo = ser.getAllProduitsPromosByPatisserie(idProduit,new String(con.getResponseData()));
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);
//        return lo;
//
//    }

}
