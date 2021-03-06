/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.gui;

import com.codename1.maps.Coord;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import java.io.IOException;
import tn.esprit.cupcake.entities.Evenement;
import tn.esprit.cupcake.entities.Patisserie;
import tn.esprit.cupcake.services.UtilisateurService;
import tn.esprit.cupcake.utils.MapContainer;

/**
 *
 * @author Basly
 */
public class MapPatisserieGui {

    private static final String HTML_API_KEY = "AIzaSyBWeRU02YUYPdwRuMFyTKIXUbHjq6e35Gw";
    Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
    FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_BACKSPACE, s);
    private final Geocoder geocoder = new Geocoder();

    public MapPatisserieGui(Patisserie patisserie) {
        Form hi = new Form("Native Maps Test");
        hi.getToolbar().addCommandToLeftBar("", icon, (e) -> new ListPatisseriesGui().show());
        hi.setLayout(new BorderLayout());
        final MapContainer cnt = new MapContainer();
        Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, 3);
        hi.addComponent(BorderLayout.CENTER, cnt);
        String[] latlongUser = getLatLng(UtilisateurService.user.getAdresse());
        float latitudeUser = Float.parseFloat(latlongUser[0]);
        float longitudeUser = Float.parseFloat(latlongUser[1]);
        Coord coordMarkerUser = new Coord(Float.parseFloat(latlongUser[0]), Float.parseFloat(latlongUser[1]));

        String[] latlongPatisserie = getLatLng(patisserie.getAdresse_patisserie());
        float latitudePatisserie = Float.parseFloat(latlongPatisserie[0]);
        float longitudePatisserie = Float.parseFloat(latlongPatisserie[1]);
        Coord coordMarkerPatisserie = new Coord(Float.parseFloat(latlongPatisserie[0]), Float.parseFloat(latlongPatisserie[1]));

        cnt.setCameraPosition(new Coord(latitudePatisserie, longitudePatisserie));
        Coord crd = new Coord(latitudePatisserie, longitudePatisserie);

        cnt.zoom(crd, 10);
        try {
            cnt.addMarker(EncodedImage.create("/maps-pin.png"),
                    new Coord(latitudePatisserie, longitudePatisserie), "Hi marker", "Optional long description",
                    new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    Dialog.show("Marker Clicked!", "Adresse patisserie", "OK", null);
                }
            });
        } catch (IOException ex) {
        }
        try {
            cnt.addMarker(EncodedImage.create("/maps-pin.png"),
                    new Coord(latitudeUser, longitudeUser), "Hi marker", "Optional long description",
                    new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    Dialog.show("Marker Clicked!", "Adresse utilisateur", "OK", null);
                }
            });
        } catch (IOException ex) {
        }
        cnt.addPath(new Coord(latitudePatisserie, longitudePatisserie),
                new Coord(latitudeUser, longitudeUser)
        );
        hi.show();

    }

    private String[] getLatLng(String address) {

        GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(address).setLanguage("en").getGeocoderRequest();
        GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
        String latLng = geocoderResponse.getResults().get(0).getGeometry().getLocation().toString();
        String lat = latLng.substring(latLng.indexOf('=') + 1, latLng.indexOf(','));
        String partLng = latLng.substring(latLng.indexOf(',') + 2);
        String lng = partLng.substring(partLng.indexOf('=') + 1, partLng.indexOf('}'));

        return new String[]{lat, lng};
    }
}
