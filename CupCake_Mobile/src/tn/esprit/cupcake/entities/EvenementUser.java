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
public class EvenementUser {
    private int id_evenement;
    private long id_user;
    private int participe;
    private int note ;

    public EvenementUser() {
    }

    public EvenementUser(int id_evenement, long id_user, int participe, int note) {
        this.id_evenement = id_evenement;
        this.id_user = id_user;
        this.participe = participe;
        this.note = note;
    }

 

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public int getParticipe() {
        return participe;
    }

    public void setParticipe(int participe) {
        this.participe = participe;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "EvenementUser{" + "id_evenement=" + id_evenement + ", id_user=" + id_user + ", participe=" + participe + ", note=" + note + '}';
    }
    
    
}
