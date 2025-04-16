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
public class Personne {
    protected String nom, prenom, dateNaissance, lieuNaissance, adresse, telph;

    public Personne(String nom, String prenom, String dateNaissance, String lieuNaissance, String adresse,
            String telph) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.adresse = adresse;
        this.telph = telph;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelph() {
        return telph;
    }

    public void setTelph(String telph) {
        this.telph = telph;
    }

    @Override
    public String toString() {
        return nom + " " + prenom + "\nDate et lieu de Naissance: " + dateNaissance + " " + lieuNaissance
                + "\nAdresse: "
                + adresse + "\nTéléphone: " + telph;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Personne personne = (Personne) obj;
        return nom.equals(personne.nom) &&
                prenom.equals(personne.prenom) &&
                dateNaissance.equals(personne.dateNaissance) &&
                lieuNaissance.equals(personne.lieuNaissance);
    }

    @Override
    public int hashCode() {
        int result = nom.hashCode();
        result = 31 * result + prenom.hashCode();
        result = 31 * result + dateNaissance.hashCode();
        result = 31 * result + lieuNaissance.hashCode();
        return result;
    }
}
