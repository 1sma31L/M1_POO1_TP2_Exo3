/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m1_poo1_tp2_exo3;

import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class Parking {
    protected String nom, adresse;
    protected int capacite, tarif;
    protected ArrayList<String> heuresEntrees = new ArrayList(), datesEntrees = new ArrayList();
    protected ArrayList<String> heuresSorties = new ArrayList(), datesSorties = new ArrayList();
    protected ArrayList<Vehicule> vehiculesEntrees = new ArrayList(), vehiculesSorties = new ArrayList();
    protected int totalAccumule;

    public Parking(String nom, String adresse, int capacite, int tarif) {
        this.nom = nom;
        this.adresse = adresse;
        this.capacite = capacite;
        this.tarif = tarif;
        this.totalAccumule = 0;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public int getTarif() {
        return tarif;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }

    public ArrayList<String> getHeuresEntrees() {
        return heuresEntrees;
    }

    public ArrayList<String> getDatesEntrees() {
        return datesEntrees;
    }

    public ArrayList<Vehicule> getVehiculesEntrees() {
        return vehiculesEntrees;
    }

    public ArrayList<String> getHeuresSorties() {
        return heuresSorties;
    }

    public ArrayList<String> getDatesSorties() {
        return datesSorties;
    }

    public ArrayList<Vehicule> getVehiculesSorties() {
        return vehiculesSorties;
    }

    public void entreeVehicule(Vehicule vehicule, String dateEntree, String heureEntree) {
        this.vehiculesEntrees.add(vehicule);
        this.datesEntrees.add(dateEntree);
        this.heuresEntrees.add(heureEntree);
    }

    public void sortieVehicule(Vehicule vehicule, String dateSortie, String heureSortie, int aPayer) {
        this.vehiculesSorties.add(vehicule);
        this.datesSorties.add(dateSortie);
        this.heuresSorties.add(heureSortie);
        this.totalAccumule += aPayer;
    }

    private int[] extractDate(String date) {
        int[] d = new int[3];// jj/mm/aaaa
        d[0] = Integer.valueOf(date.substring(0, 2));
        d[1] = Integer.valueOf(date.substring(3, 5));
        d[2] = Integer.valueOf(date.substring(6, 10));
        return d;
    }

    private int[] extractHeure(String heure) {
        int[] h = new int[2];// hh:mm
        h[1] = Integer.valueOf(heure.substring(0, 2));
        h[0] = Integer.valueOf(heure.substring(3, 5));
        return h;
    }

    private boolean avant(String date1, String heure1, String date2, String heure2) {
        int[] d1 = extractDate(date1);
        int[] d2 = extractDate(date2);
        int[] h1 = extractHeure(heure1);
        int[] h2 = extractHeure(heure2);
        if (d1[2] < d2[2]) {
            return true;
        } else if (d1[2] == d2[2]) {
            if (d1[1] < d2[1]) {
                return true;
            } else if (d1[1] == d2[1]) {
                if (d1[0] < d2[0]) {
                    return true;
                } else if (d1[0] == d2[0]) {
                    if (h1[1] < h2[1]) {
                        return true;
                    } else if (h1[1] == h2[1] && h1[0] < h2[0]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String etatComplet() {
        String str = "Véchicule\t|\tDate\t|\tHeure\t|\tEtat (entrée/sortie)\t|\n";
        int i = 0, j = 0;
        while (i < this.vehiculesEntrees.size() && j < this.heuresSorties.size()) {
            if (avant(this.datesEntrees.get(i), this.heuresEntrees.get(i), this.datesSorties.get(j),
                    this.heuresSorties.get(j))) {
                str += this.vehiculesEntrees.get(i) + "\t|\t";
                str += this.datesEntrees.get(i) + "\t|\t";
                str += this.heuresEntrees.get(i) + "\t|\tEntrée\t|\n";
                i++;
            } else {
                str += this.vehiculesSorties.get(j) + "\t|\t";
                str += this.datesSorties.get(j) + "\t|\t";
                str += this.heuresSorties.get(j) + "\t|\tSortie\t|\n";
                j++;
            }
        }
        if (i < this.vehiculesEntrees.size()) {
            while (i < this.vehiculesEntrees.size()) {
                str += this.vehiculesEntrees.get(i) + "\t|\t";
                str += this.datesEntrees.get(i) + "\t|\t";
                str += this.heuresEntrees.get(i) + "\t|\tEntrée\t|\n";
                i++;
            }
        } else {
            while (j < this.vehiculesSorties.size()) {
                str += this.vehiculesSorties.get(j) + "\t|\t";
                str += this.datesSorties.get(j) + "\t|\t";
                str += this.heuresSorties.get(j) + "\t|\tSortie\t|\n";
                j++;
            }
        }
        return str;
    }

    public String etatActuel(String dateActuel) {
        String str = "Etat actuel du parking : ";
        ArrayList<Integer> indEntree = new ArrayList(), indSortie = new ArrayList();
        for (int i = 0; i < this.vehiculesEntrees.size(); i++) {
            indEntree.add(i);
            for (int j = 0; j < this.vehiculesSorties.size(); j++) {
                if (this.vehiculesEntrees.get(i).equals(this.vehiculesSorties.get(j))) {
                    if (!indSortie.contains(j)) {
                        indSortie.add(j);
                        break;
                    }
                }
            }
            if (indEntree.size() != indSortie.size()) {
                indSortie.add(-1);
            }
        }
        for (int i = 0; i < this.vehiculesEntrees.size(); i++) {
            if (indSortie.get(i) == -1) {
                str += this.vehiculesEntrees.get(i);
            }
        }
        return str;
    }

    public int avoirTotalAccumule() {
        return this.totalAccumule;
    }

    public String tracabiliteSelonMarque(String marque) {
        String str = "Traçabilité des véhicules de type : " + marque + "\n";
        int i = 0, j = 0;
        while (i < this.vehiculesEntrees.size() && j < this.heuresSorties.size()) {
            if (!this.vehiculesEntrees.get(i).getMarque().equals(marque)) {
                i++;
                continue;
            }
            if (!this.vehiculesSorties.get(j).getMarque().equals(marque)) {
                j++;
                continue;
            }
            if (avant(this.datesEntrees.get(i), this.heuresEntrees.get(i), this.datesSorties.get(j),
                    this.heuresSorties.get(j))) {
                str += this.vehiculesEntrees.get(i) + "\t|\t";
                str += this.datesEntrees.get(i) + "\t|\t";
                str += this.heuresEntrees.get(i) + "\t|\tEntrée\t|\n";
                i++;
            } else {
                str += this.vehiculesSorties.get(j) + "\t|\t";
                str += this.datesSorties.get(j) + "\t|\t";
                str += this.heuresSorties.get(j) + "\t|\tSortie\t|\n";
                j++;
            }
        }
        if (i < this.vehiculesEntrees.size()) {
            while (i < this.vehiculesEntrees.size()) {
                if (!this.vehiculesEntrees.get(i).getMarque().equals(marque)) {
                    i++;
                    continue;
                }
                str += this.vehiculesEntrees.get(i) + "\t|\t";
                str += this.datesEntrees.get(i) + "\t|\t";
                str += this.heuresEntrees.get(i) + "\t|\tEntrée\t|\n";
                i++;
            }
        } else {
            while (j < this.vehiculesSorties.size()) {
                if (!this.vehiculesSorties.get(j).getMarque().equals(marque)) {
                    j++;
                    continue;
                }
                str += this.vehiculesSorties.get(j) + "\t|\t";
                str += this.datesSorties.get(j) + "\t|\t";
                str += this.heuresSorties.get(j) + "\t|\tSortie\t|\n";
                j++;
            }
        }
        return str;
    }

    public String tracabiliteSelonProprietaire(Personne proprietaire) {
        String str = "Traçabilité des véhicules du propriétaire : " + proprietaire + "\n";
        int i = 0, j = 0;
        while (i < this.vehiculesEntrees.size() && j < this.heuresSorties.size()) {
            if (!this.vehiculesEntrees.get(i).getProprietaire().equals(proprietaire)) {
                i++;
                continue;
            }
            if (!this.vehiculesSorties.get(j).getProprietaire().equals(proprietaire)) {
                j++;
                continue;
            }
            if (avant(this.datesEntrees.get(i), this.heuresEntrees.get(i), this.datesSorties.get(j),
                    this.heuresSorties.get(j))) {
                str += this.vehiculesEntrees.get(i) + "\t|\t";
                str += this.datesEntrees.get(i) + "\t|\t";
                str += this.heuresEntrees.get(i) + "\t|\tEntrée\t|\n";
                i++;
            } else {
                str += this.vehiculesSorties.get(j) + "\t|\t";
                str += this.datesSorties.get(j) + "\t|\t";
                str += this.heuresSorties.get(j) + "\t|\tSortie\t|\n";
                j++;
            }
        }
        if (i < this.vehiculesEntrees.size()) {
            while (i < this.vehiculesEntrees.size()) {
                if (!this.vehiculesEntrees.get(i).getProprietaire().equals(proprietaire)) {
                    i++;
                    continue;
                }
                str += this.vehiculesEntrees.get(i) + "\t|\t";
                str += this.datesEntrees.get(i) + "\t|\t";
                str += this.heuresEntrees.get(i) + "\t|\tEntrée\t|\n";
                i++;
            }
        } else {
            while (j < this.vehiculesSorties.size()) {
                if (!this.vehiculesSorties.get(j).getProprietaire().equals(proprietaire)) {
                    j++;
                    continue;
                }
                str += this.vehiculesSorties.get(j) + "\t|\t";
                str += this.datesSorties.get(j) + "\t|\t";
                str += this.heuresSorties.get(j) + "\t|\tSortie\t|\n";
                j++;
            }
        }
        return str;
    }
}
