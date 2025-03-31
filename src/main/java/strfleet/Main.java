package strfleet;

import strfleet.modele.mission.Mission;
import strfleet.modele.personne.*;
import strfleet.modele.reservation.Reservation;
import strfleet.modele.vaisseau.Vaisseau;
import strfleet.systeme.SystemeReservation;

import java.util.*;

import modele.personne.Civil;
import modele.personne.Officier;
import modele.personne.Personne;

import java.text.SimpleDateFormat;

public class Main {
    //C:\Users\guybe\Downloads\StarFleetReservation\StarFleetReservation\data
    private static final String FICHIER_SAUVEGARDE = "data/starfleet_data.dat";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        // Initialisation du système
        SystemeReservation systeme = new SystemeReservation();
        
        System.out.println("=== DÉBUT DU TEST AUTOMATISÉ STARFLEET ===");

        // 1. Création des vaisseaux
        System.out.println("\nÉtape 1: Création des vaisseaux...");
        List<Vaisseau> vaisseaux = creerVaisseauxTest(systeme);
        afficherResultatEtape("vaisseaux", systeme.getVaisseaux().size(), 3);

        // 2. Ajout des personnes
        System.out.println("\nÉtape 2: Ajout des équipages et civils...");
        List<Personne> personnes = creerPersonnesTest(systeme);
        afficherResultatEtape("personnes", systeme.getPersonnes().size(), 6);

        // 3. Création des missions
        System.out.println("\nÉtape 3: Planification des missions...");
        List<Mission> missions = creerMissionsTest(systeme);
        afficherResultatEtape("missions", systeme.getMissions().size(), 3);

        // 4. Effectuer des réservations
        System.out.println("\nÉtape 4: Réservations en cours...");
        List<Reservation> reservations = effectuerReservationsTest(systeme, personnes, missions);
        afficherResultatEtape("réservations", systeme.getReservations().size(), 4);

        // 5. Gestion des réservations
        System.out.println("\nÉtape 5: Gestion des réservations...");
        gererReservationsTest(systeme, reservations);

        // 6. Affichage des passagers par mission
        System.out.println("\nÉtape 6: Liste des passagers par mission :");
        afficherPassagersParMission(systeme);

        // 7. Sauvegarde des données
        System.out.println("\nÉtape 7: Sauvegarde des données...");
        systeme.sauvegarderDonnees(FICHIER_SAUVEGARDE);
        System.out.println("✅ Données sauvegardées dans " + FICHIER_SAUVEGARDE);

        // 8. Chargement des données
        System.out.println("\nÉtape 8: Chargement des données...");
        SystemeReservation systemeCharge = new SystemeReservation();
        systemeCharge.chargerDonnees(FICHIER_SAUVEGARDE);

        // 9. Vérification
        System.out.println("\nÉtape 9: Vérification des données chargées :");
        verifierDonneesChargees(systemeCharge);

        System.out.println("\n=== TEST TERMINÉ AVEC SUCCÈS ===");
    }

    private static List<Vaisseau> creerVaisseauxTest(SystemeReservation systeme) {
        Vaisseau v1 = new Vaisseau("USS Enterprise", "NCC-1701-D", 1000, new ArrayList<>());
        Vaisseau v2 = new Vaisseau("USS Voyager", "NCC-74656", 500, new ArrayList<>());
        Vaisseau v3 = new Vaisseau("USS Defiant", "NX-74205", 300, new ArrayList<>());
        
        systeme.ajouterVaisseau(v1);
        systeme.ajouterVaisseau(v2);
        systeme.ajouterVaisseau(v3);
        
        return Arrays.asList(v1, v2, v3);
    }

    private static List<Personne> creerPersonnesTest(SystemeReservation systeme) {
        // Officiers
        Personne p1 = new Officier("Picard", "Jean-Luc", "PIC001", "Capitaine", "Commandement");
        Personne p2 = new Officier("Riker", "William", "RIK002", "Commandant", "Premier officier");
        Personne p3 = new Officier("Data", "", "DAT003", "Lieutenant Commander", "Opérations");
        // Civils
        Personne p4 = new Civil("Troi", "Deanna", "TRO004", "Betazed", "Conseillère");
        Personne p5 = new Civil("Crusher", "Beverly", "CRU005", "Terre", "Médecin");
        Personne p6 = new Civil("Touriste", "John", "TOU006", "Terre", "Vacances");
        
        systeme.ajouterPersonne(p1);
        systeme.ajouterPersonne(p2);
        systeme.ajouterPersonne(p3);
        systeme.ajouterPersonne(p4);
        systeme.ajouterPersonne(p5);
        systeme.ajouterPersonne(p6);
        
        return Arrays.asList(p1, p2, p3, p4, p5, p6);
    }

    private static List<Mission> creerMissionsTest(SystemeReservation systeme) {
        try {
            Date aujourdhui = new Date();
            Date demain = new Date(aujourdhui.getTime() + 86400000);
            Date semaineProchaine = new Date(aujourdhui.getTime() + 604800000);
            
            Mission m1 = new Mission("M001", "Mission diplomatique", aujourdhui, semaineProchaine, "Vulcain", 10);
            Mission m2 = new Mission("M002", "Retour sur Terre", demain, semaineProchaine, "Terre", 6);
            Mission m3 = new Mission("M003", "Mission de détente", semaineProchaine, 
                                    new Date(semaineProchaine.getTime() + 86400000), "Risa", 6);
            
            systeme.ajouterMission(m1);
            systeme.ajouterMission(m2);
            systeme.ajouterMission(m3);
            
            return Arrays.asList(m1, m2, m3);
        } catch (Exception e) {
            System.out.println("Erreur dans la création des missions: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    private static List<Reservation> effectuerReservationsTest(SystemeReservation systeme, 
                                                             List<Personne> personnes, 
                                                             List<Mission> missions) {
        List<Reservation> reservations = new ArrayList<>();
        
        // Associer des missions à des vaisseaux
        if (!missions.isEmpty() && !systeme.getVaisseaux().isEmpty()) {
            systeme.associerMissionAVaisseau(missions.get(0), systeme.getVaisseaux().get(0));
            systeme.associerMissionAVaisseau(missions.get(1), systeme.getVaisseaux().get(1));
            systeme.associerMissionAVaisseau(missions.get(2), systeme.getVaisseaux().get(2));
        }

        // Effectuer des réservations
        Reservation res1 = systeme.effectuerReservation("PIC001", "M001");
        Reservation res2 = systeme.effectuerReservation("RIK002", "M001");
        Reservation res3 = systeme.effectuerReservation("TRO004", "M002");
        Reservation res4 = systeme.effectuerReservation("TOU006", "M003");
        
        if (res1 != null) reservations.add(res1);
        if (res2 != null) reservations.add(res2);
        if (res3 != null) reservations.add(res3);
        if (res4 != null) reservations.add(res4);
        
        return reservations;
    }

    private static void gererReservationsTest(SystemeReservation systeme, List<Reservation> reservations) {
        if (!reservations.isEmpty()) {
            // Confirmer la première réservation
            systeme.confirmerReservation(reservations.get(0).getIdReservation());
            System.out.println("- Réservation " + reservations.get(0).getIdReservation() + " confirmée");
            
            // Annuler la dernière réservation
            systeme.annulerReservation(reservations.get(reservations.size()-1).getIdReservation());
            System.out.println("- Réservation " + reservations.get(reservations.size()-1).getIdReservation() + " annulée");
        }
    }

    private static void afficherPassagersParMission(SystemeReservation systeme) {
        for (Mission m : systeme.getMissions()) {
            System.out.println("\nMission " + m.getCode() + " (" + m.getDestination() + ") :");
            systeme.afficherReservationsMission(m.getCode());
        }
    }

    private static void verifierDonneesChargees(SystemeReservation systeme) {
        System.out.println("- Vaisseaux chargés : " + systeme.getVaisseaux().size());
        System.out.println("- Personnes chargées : " + systeme.getPersonnes().size());
        System.out.println("- Missions chargées : " + systeme.getMissions().size());
        System.out.println("- Réservations chargées : " + systeme.getReservations().size());
        
        if (systeme.getVaisseaux().size() == 3 && 
            systeme.getPersonnes().size() == 6 && 
            systeme.getMissions().size() == 3 && 
            systeme.getReservations().size() == 3) {
            System.out.println("✅ Toutes les données ont été correctement chargées");
        } else {
            System.out.println("❌ Problème lors du chargement des données");
        }
    }

    private static void afficherResultatEtape(String element, int quantite, int attendu) {
        System.out.println("- " + quantite + " " + element + " créé(s)");
        if (quantite == attendu) {
            System.out.println("✅ Succès (" + quantite + "/" + attendu + ")");
        } else {
            System.out.println("❌ Échec (" + quantite + "/" + attendu + ")");
        }
    }
}