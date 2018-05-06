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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import tn.esprit.cupcake.entities.Evenement;

/**
 *
 * @author Basly
 */
public class EvenementService {

    public ArrayList<Evenement> getEvenementById(String json) {
        ArrayList<Evenement> listEvenements = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> evenements = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) evenements.get("root");
            for (Map<String, Object> obj : list) {
                Evenement e = new Evenement();
                float id = Float.parseFloat(obj.get("idEvenement").toString());
                System.out.println(id);
                e.setId_evenement((int) id);
                e.setLibelle_evenement(obj.get("libelleEvenement").toString());
                e.setAdresse_evenement(obj.get("adresseEvenement").toString());

                Map<String, Object> resultsDateDebut = (Map<String, Object>) obj.get("dateDebut");
                Map<String, Object> resultsDateFin = (Map<String, Object>) obj.get("dateFin");

                Double dateDebut = (Double) resultsDateDebut.get("timestamp");
                Date dateDebut2 = new Date((long) (dateDebut * 1000));
                e.setDate_debut(dateDebut2);

                Double dateFin = (Double) resultsDateFin.get("timestamp");
                Date dateFin2 = new Date((long) (dateFin * 1000));
                e.setDate_fin(dateFin2);

                e.setDescription(obj.get("description").toString());

                float note = Float.parseFloat(obj.get("note").toString());
                System.out.println(note);
                e.setNote((int) note);
                e.setImage(obj.get("image").toString());

                double longitude = Double.parseDouble(obj.get("longitude").toString());
                double latitude = Double.parseDouble(obj.get("latitude").toString());
                e.setLongitude(longitude);
                e.setLatitude(latitude);

                listEvenements.add(e);
            }
        } catch (IOException ex) {
        }
        return listEvenements;
    }
    ArrayList<Evenement> Evenement = new ArrayList<>();

    public ArrayList<Evenement> getEvenement(int idEvent) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CupCake_Web_VF-master/web/app_dev.php/Evenement/api/eventById/" + idEvent);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                EvenementService ser = new EvenementService();
                Evenement = ser.getEvenementById(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return Evenement;

    }

    public ArrayList<Evenement> getEvenementByPatisserie(String json) {
        ArrayList<Evenement> listEvenementsByPatisserie = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> evenements = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) evenements.get("root");
            for (Map<String, Object> obj : list) {
                Evenement e = new Evenement();
                float id = Float.parseFloat(obj.get("idEvenement").toString());
                System.out.println(id);
                e.setId_evenement((int) id);

//                float idPatisserie = Float.parseFloat(obj.get("idPatisserie").toString());
//                System.out.println(idPatisserie);
//                e.setId_evenement((int) idPatisserie);
                e.setLibelle_evenement(obj.get("libelleEvenement").toString());
                e.setAdresse_evenement(obj.get("adresseEvenement").toString());
                Map<String, Object> resultsDateDebut = (Map<String, Object>) obj.get("dateDebut");
                Map<String, Object> resultsDateFin = (Map<String, Object>) obj.get("dateFin");

                Double dateDebut = (Double) resultsDateDebut.get("timestamp");
                Date dateDebut2 = new Date((long) (dateDebut * 1000));
                e.setDate_debut(dateDebut2);

                Double dateFin = (Double) resultsDateFin.get("timestamp");
                Date dateFin2 = new Date((long) (dateFin * 1000));
                e.setDate_fin(dateFin2);
                e.setDescription(obj.get("description").toString());
                e.setImage(obj.get("image").toString());
                double longitude = Double.parseDouble(obj.get("longitude").toString());
                double latitude = Double.parseDouble(obj.get("latitude").toString());
                e.setLongitude(longitude);
                e.setLatitude(latitude);
                listEvenementsByPatisserie.add(e);
            }
        } catch (IOException ex) {
        }
        return listEvenementsByPatisserie;
    }
    ArrayList<Evenement> EvenementsByPatisserie = new ArrayList<>();

    public ArrayList<Evenement> getEvenementsByPatisserie(int idPatisserie) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CupCake_Web_VF-master/web/app_dev.php/Evenement/api/findEventByPatisserie?idPatisserie=" + idPatisserie);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                EvenementService ser = new EvenementService();
                EvenementsByPatisserie = ser.getEvenementByPatisserie(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return EvenementsByPatisserie;

    }

    public void modifierNoteEvent(Evenement ev, int note) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/CupCake_Web_VF-master/web/app_dev.php/Evenement/api/ratingMobile?idEvent=" + ev.getId_evenement() + "&note=" + note;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

}
