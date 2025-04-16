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
public class M1_POO1_TP2_Exo3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Personne p1 = new Personne("Badr", "Amine", "11/11/1990", "Alger Centre", "Bab Ezzouar, Alger", "0555555555");
        Personne p2 = new Personne("Gali", "Mohamed", "16/01/1994", "Blida", "Dergana, Alger", "0777777777");
        Vehicule v1 = new Vehicule(p1, "Toyota", "Noir", "05263812316", 5);
        Vehicule v2 = new Vehicule(p2, "Seat", "Blanc", "12321211909", 5);
        Parking park = new Parking("El Hadaik", "12, Rue El Hadaik, Alger", 100, 50);
        Vehicule v3 = new Vehicule(p2, "Nissan", "Bleu", "22687231616", 2);

        park.entreeVehicule(v1, "08/04/2025", "18:00");
        park.entreeVehicule(v2, "09/04/2025", "09:00");
        park.sortieVehicule(v2, "09/04/2025", "20:00", 50);
        park.sortieVehicule(v1, "09/04/2025", "21:00", 150);
        park.entreeVehicule(v2, "10/04/2025", "08:00");
        park.entreeVehicule(v3, "10/04/2025", "17:00");

        System.out.println("Total Accumulé : " + park.avoirTotalAccumule() + " DA");
        System.out.println("Etat actuel au 09/04/2025 : \n" + park.etatActuel("09/04/2025"));
        System.out.println("Traçabilité Toyota : \n" + park.tracabiliteSelonMarque("Toyota"));
        System.out.println("Traçabilité Gali Mohamed : \n" + park.tracabiliteSelonProprietaire(p2));
    }
}
