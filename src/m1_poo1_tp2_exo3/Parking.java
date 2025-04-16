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
    protected ArrayList<String> heuresEntrees = new ArrayList<String>(), datesEntrees = new ArrayList<String>();
    protected ArrayList<String> heuresSorties = new ArrayList<String>(), datesSorties = new ArrayList<String>();
    protected ArrayList<Vehicule> vehiculesEntrees = new ArrayList<Vehicule>(),
            vehiculesSorties = new ArrayList<Vehicule>();
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
        if (vehicule == null) {
            throw new IllegalArgumentException("Le véhicule ne peut pas être null");
        }
        validateDateTime(dateEntree, heureEntree);

        if (vehiculesEntrees.size() - vehiculesSorties.size() >= capacite) {
            throw new IllegalStateException("Le parking est complet");
        }

        // Vérifier si le véhicule est déjà dans le parking
        int entreesCount = 0;
        int sortiesCount = 0;
        for (Vehicule v : vehiculesEntrees) {
            if (v.equals(vehicule))
                entreesCount++;
        }
        for (Vehicule v : vehiculesSorties) {
            if (v.equals(vehicule))
                sortiesCount++;
        }

        if (entreesCount > sortiesCount) {
            throw new IllegalStateException("Ce véhicule est déjà dans le parking");
        }

        this.vehiculesEntrees.add(vehicule);
        this.datesEntrees.add(dateEntree);
        this.heuresEntrees.add(heureEntree);
    }

    public void sortieVehicule(Vehicule vehicule, String dateSortie, String heureSortie, int aPayer) {
        if (vehicule == null) {
            throw new IllegalArgumentException("Le véhicule ne peut pas être null");
        }
        validateDateTime(dateSortie, heureSortie);

        // Trouver l'entrée correspondante du véhicule
        int indexEntree = -1;
        for (int i = 0; i < vehiculesEntrees.size(); i++) {
            if (vehiculesEntrees.get(i).equals(vehicule)) {
                boolean dejaSorti = false;
                // Vérifier si ce véhicule est déjà sorti pour cette entrée
                for (int j = 0; j < vehiculesSorties.size(); j++) {
                    if (vehiculesSorties.get(j).equals(vehicule) &&
                            datesEntrees.get(i).equals(datesEntrees.get(j)) &&
                            heuresEntrees.get(i).equals(heuresEntrees.get(j))) {
                        dejaSorti = true;
                        break;
                    }
                }
                if (!dejaSorti) {
                    indexEntree = i;
                    break;
                }
            }
        }

        if (indexEntree == -1) {
            throw new IllegalStateException("Ce véhicule n'est pas dans le parking");
        }

        // Vérifier que la date/heure de sortie est après la date/heure d'entrée
        if (!avant(datesEntrees.get(indexEntree), heuresEntrees.get(indexEntree), dateSortie, heureSortie)) {
            throw new IllegalArgumentException("La date/heure de sortie doit être après la date/heure d'entrée");
        }

        // Vérifier que le montant à payer est positif
        if (aPayer < 0) {
            throw new IllegalArgumentException("Le montant à payer ne peut pas être négatif");
        }

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
        h[0] = Integer.valueOf(heure.substring(0, 2));
        h[1] = Integer.valueOf(heure.substring(3, 5));
        return h;
    }

    private boolean avant(String date1, String heure1, String date2, String heure2) {
        if (date1 == null || date2 == null || heure1 == null || heure2 == null) {
            throw new IllegalArgumentException("Les dates et heures ne peuvent pas être nulles");
        }
        int[] d1 = extractDate(date1);
        int[] d2 = extractDate(date2);
        int[] h1 = extractHeure(heure1);
        int[] h2 = extractHeure(heure2);

        if (d1[2] < d2[2])
            return true; // Année
        if (d1[2] > d2[2])
            return false;

        if (d1[1] < d2[1])
            return true; // Mois
        if (d1[1] > d2[1])
            return false;

        if (d1[0] < d2[0])
            return true; // Jour
        if (d1[0] > d2[0])
            return false;

        if (h1[0] < h2[0])
            return true; // Heure
        if (h1[0] > h2[0])
            return false;

        return h1[1] < h2[1]; // Minutes
    }

    private void validateDateTime(String date, String heure) {
        if (date == null || heure == null) {
            throw new IllegalArgumentException("La date et l'heure ne peuvent pas être nulles");
        }
        try {
            int[] d = extractDate(date);
            int[] h = extractHeure(heure);

            if (d[0] < 1 || d[0] > 31 || d[1] < 1 || d[1] > 12 || d[2] < 2000) {
                throw new IllegalArgumentException("Format de date invalide: " + date);
            }
            if (h[0] < 0 || h[0] > 23 || h[1] < 0 || h[1] > 59) {
                throw new IllegalArgumentException("Format d'heure invalide: " + heure);
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new IllegalArgumentException("Format de date/heure invalide");
        }
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
        String str = "Etat actuel du parking : \n";
        int vehiculesPresents = 0;

        for (int i = 0; i < vehiculesEntrees.size(); i++) {
            boolean estSorti = false;
            if (avant(datesEntrees.get(i), heuresEntrees.get(i), dateActuel, "23:59")) {
                for (int j = 0; j < vehiculesSorties.size(); j++) {
                    if (vehiculesEntrees.get(i).equals(vehiculesSorties.get(j)) &&
                            avant(datesEntrees.get(i), heuresEntrees.get(i), datesSorties.get(j), heuresSorties.get(j))
                            &&
                            !avant(dateActuel, "00:00", datesSorties.get(j), heuresSorties.get(j))) {
                        estSorti = true;
                        break;
                    }
                }
                if (!estSorti) {
                    str += "- " + vehiculesEntrees.get(i).toString() + "\n";
                    vehiculesPresents++;
                }
            }
        }
        if (vehiculesPresents == 0) {
            str += "Aucun véhicule présent\n";
        }
        str += "Nombre de places disponibles: " + (capacite - vehiculesPresents) + "\n";
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
