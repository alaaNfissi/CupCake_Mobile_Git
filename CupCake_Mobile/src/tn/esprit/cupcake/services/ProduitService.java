/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;

import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import tn.esprit.cupcake.entities.Produit;

/**
 *
 * @author esprit
 */
public class ProduitService {

    /* public ArrayList<Produit> getListProduit(String json) {

        ArrayList<Produit> listProduits = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> Produit = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(Produit);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) Produit.get("root");

            for (Map<String, Object> obj : list) {
                Produit p = new Produit();

                // System.out.println(obj.get("id"));
                int id_produit = Integer.parseInt(obj.get("id_produit").toString());
                System.out.println(id_produit);
                p.setId_produit((int) id_produit);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                p.setLibelle_produit(obj.get("libelle_produit").toString());
                 p.setCategorie(obj.get("categorie").toString());
                  float prix = Float.parseFloat(obj.get("prix").toString());
               
                p.setPrix((float) prix);
                 int quantite = Integer.parseInt(obj.get("quantite").toString());
               
                p.setQuantite((int) quantite);
                p.setDescription(obj.get("description").toString());
                  int note = Integer.parseInt(obj.get("note").toString());
               
                p.setNote((int) note);
                 int id_patisserie = Integer.parseInt(obj.get("id_patisserie").toString());
               
                p.setId_patisserie((int) id_patisserie);
                System.out.println(p);
                listProduits.add(p);

            }

        } catch (IOException ex) {
        }
        System.out.println(listProduits);
        return listProduits;

    }*/
    public ArrayList<Produit> getListProduits(String json) {
        ArrayList<Produit> listProduits = new ArrayList<>();
        System.out.println("0000000000000000000000" + json);
        try {
            System.out.println("11111111111111" + json);
            JSONParser j = new JSONParser();
            Map<String, Object> Produit = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println("2222222222222" + Produit);
            List<Map<String, Object>> list = (List<Map<String, Object>>) Produit.get("root");
            System.out.println("5555555555555555" + list);
            for (Map<String, Object> obj : list) {
                Produit p = new Produit();
                System.out.println(String.valueOf(Float.parseFloat(obj.get("idProduit").toString())));
                p.setId_produit((int) (Float.parseFloat(obj.get("idProduit").toString())));
                   p.setNote((int) (Float.parseFloat(obj.get("note").toString())));
                //                int id = Integer.parseInt(obj.get("idEvenement").toString());
//                System.out.println(id);
//                e.setId_evenement(id);  
              //  float id_produit = Float.parseFloat(obj.get("idProduit").toString());
               // System.out.println(id_produit);
               // p.setId_produit((int) id_produit);
                p.setLibelle_produit(obj.get("libelleProduit").toString());
               // p.setCategorie(obj.get("categorie").toString());
//                Date  dateD = (Date) (obj.get("dateDebut")) ;
//                Date  dateF = (Date) (obj.get("dateFin")) ;
//                e.setDate_debut(dateD);
//                e.setDate_fin(dateF);
              //  float prix = Float.parseFloat(obj.get("prix").toString());
              //  System.out.println(prix);
               // p.setPrix((float) prix);
                  p.setPrix((int) (Float.parseFloat(obj.get("prix").toString())));
              //  float quantite = Float.parseFloat(obj.get("quantite").toString());
               // System.out.println(quantite);
               // p.setQuantite((int) quantite);
                p.setDescription(obj.get("description").toString());
                   p.setImage(obj.get("image").toString());
                //e.setImage(null);
                //  double longitude =  Double.parseDouble(obj.get("longitude").toString());
                // double latitude = Double.parseDouble(obj.get("latitude").toString());
                //  e.setLongitude(longitude);
                //  e.setLatitude(latitude);
              //  float note = Float.parseFloat(obj.get("note").toString());
              //  System.out.println(note);
               // p.setNote((int) note);
                System.out.println("33333333333333333333 **  ** *  " + p.toString());
                listProduits.add(p);

            }

        } catch (IOException ex) {
        }
        System.out.println("4444444444444444" + listProduits);
        return listProduits;

    }

    ArrayList<Produit> listProduits = new ArrayList<>();

    public ArrayList<Produit> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CupCake_Web_VF-master/web/app_dev.php/Produit/api/produit/ListProduitMobile");
       // con.setUrl("http://127.0.0.1:8000/Produit/produit/ListProduitMobile");
        //      con.setUrl(Url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ProduitService pr = new ProduitService();
                listProduits = pr.getListProduits(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProduits;
    }
    
    
    
        ArrayList<Produit> ToplistProduits = new ArrayList<>();

    public ArrayList<Produit> getList3() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CupCake_Web_VF-master/web/app_dev.php/Produit/api/produit/topListMobile");
       // con.setUrl("http://127.0.0.1:8000/Produit/produit/ListProduitMobile");
        //      con.setUrl(Url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ProduitService pr = new ProduitService();
                ToplistProduits = pr.getListProduits(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return ToplistProduits;
    }

}
