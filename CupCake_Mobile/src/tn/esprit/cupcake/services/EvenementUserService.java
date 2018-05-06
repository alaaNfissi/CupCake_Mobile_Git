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
import tn.esprit.cupcake.entities.EvenementUser;

/**
 *
 * @author Basly
 */
public class EvenementUserService {


    public void ajoutEventParticipeNote(EvenementUser ev) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/CupCake_Web_VF-master/web/app_dev.php/EventUser/api/addevuser2?idEvent=" + ev.getId_evenement() + "&idUser=" + ev.getId_user() + "&participe=" + ev.getParticipe() + "&note=" + ev.getNote();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void modifierEventParticipeNote(EvenementUser ev) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/CupCake_Web_VF-master/web/app_dev.php/EventUser/api/editEventUser?idEvent=" + ev.getId_evenement() + "&participe=" + ev.getParticipe() + "&idUser=" + ev.getId_user() + "&note=" + ev.getNote();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<EvenementUser> getEvenementUserById(String json) {
        ArrayList<EvenementUser> listEvenementUser = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> evenements = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) evenements.get("root");
            

             
            for (Map<String, Object> obj : list) {
                EvenementUser eu = new EvenementUser();
                float note = Float.parseFloat(obj.get("note").toString());
                eu.setNote((int) note);                
                float participe = Float.parseFloat(obj.get("participe").toString());
                eu.setParticipe((int) participe);
                listEvenementUser.add(eu);
            }
        } catch (IOException ex) {
        }
        return listEvenementUser;
    }
    ArrayList<EvenementUser> EvenementUser = new ArrayList<>();

    public ArrayList<EvenementUser> getEvenementUser(int idEvent, long idUser) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CupCake_Web_VF-master/web/app_dev.php/EventUser/api/getEuById?idEvent="+idEvent+"&idUser="+idUser);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                EvenementUserService ser = new EvenementUserService();
                EvenementUser = ser.getEvenementUserById(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return EvenementUser;

    }
        public ArrayList<EvenementUser> getEvenementUserByEvent(String json) {
        ArrayList<EvenementUser> listEvenementUser = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> evenements = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) evenements.get("root");
            for (Map<String, Object> obj : list) {
                EvenementUser eu = new EvenementUser();
                float participe = Float.parseFloat(obj.get("participe").toString());
                eu.setParticipe((int) participe);
                
                float note = Float.parseFloat(obj.get("note").toString());
                eu.setNote((int) note);
                
                listEvenementUser.add(eu);
            }
        } catch (IOException ex) {
        }
        return listEvenementUser;
    }
         public ArrayList<EvenementUser> getEvenementUserByEvent(int idEvent) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CupCake_Web_VF-master/web/app_dev.php/EventUser/api/EventUserByEvent?idEvent="+idEvent);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                EvenementUserService ser = new EvenementUserService();
                EvenementUser = ser.getEvenementUserByEvent(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return EvenementUser;

    }
    
    

}
