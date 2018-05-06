/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.entities;

/**
 *
 * @author Basly
 */
public class ProduitPromotion {
    int id_produit;
    int id_promotion;
    double prix_promotion;

    public ProduitPromotion() {
    }

    public ProduitPromotion(int id_produit, int id_promotion, double prix_promotion) {
        this.id_produit = id_produit;
        this.id_promotion = id_promotion;
        this.prix_promotion = prix_promotion;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getId_promotion() {
        return id_promotion;
    }

    public void setId_promotion(int id_promotion) {
        this.id_promotion = id_promotion;
    }

    public double getPrix_promotion() {
        return prix_promotion;
    }

    public void setPrix_promotion(double prix_promotion) {
        this.prix_promotion = prix_promotion;
    }

    @Override
    public String toString() {
        return "ProduitPromotion{" + "id_produit=" + id_produit + ", id_promotion=" + id_promotion + ", prix_promotion=" + prix_promotion + '}';
    }
    
    
}
