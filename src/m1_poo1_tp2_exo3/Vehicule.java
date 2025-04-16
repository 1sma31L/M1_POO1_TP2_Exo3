/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m1_poo1_tp2_exo3;

/**
 *
 * @author HP
 */
public class Vehicule {
    protected Personne proprietaire;
    protected String marque, couleur, matricule;
    protected int nbrPlace;

    public Vehicule(Personne proprietaire, String marque, String couleur, String matricule, int nbrPlace) {
        this.proprietaire = proprietaire;
        this.marque = marque;
        this.couleur = couleur;
        this.matricule = matricule;
        this.nbrPlace = nbrPlace;
    }

    public Personne getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Personne proprietaire) {
        this.proprietaire = proprietaire;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        if (matricule != null && !matricule.trim().isEmpty()) {
            this.matricule = matricule;
        }
    }

    public int getNbrPlace() {
        return nbrPlace;
    }

    public void setNbrPlace(int nbrPlace) {
        if (nbrPlace >= 2) {
            this.nbrPlace = nbrPlace;
        }
    }

    @Override
    public String toString() {
        return marque + ", couleur:" + couleur + ", matricule:" + matricule;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Vehicule vehicule = (Vehicule) obj;
        return matricule != null && matricule.equals(vehicule.matricule);
    }

    @Override
    public int hashCode() {
        return matricule != null ? matricule.hashCode() : 0;
    }

}
