import java.util.*;

import java.text.SimpleDateFormat;
import java.text.ParseException;

import strfleet.modele.mission.Mission;
import strfleet.modele.personne.*;
import strfleet.modele.reservation.Reservation;
import strfleet.modele.vaisseau.*;
import strfleet.systeme.*;
/**
 * Classe principale fournissant une interface utilisateur en ligne de commande
 * pour le système de gestion Starfleet.
 * <p>
 * Permet d'interagir avec les différentes fonctionnalités du système via des menus textuels.
 * </p>
 * 
 * @author Sam Carlo
 * @version 1.0
 */

public class InterfaceControl {
    private SystemeReservation systeme;
    private Scanner scanner;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    /**
     * Constructeur initialisant l'interface de contrôle.
     * 
     * @param systeme Le système de réservation à gérer
     */

    // Constructeur
    public InterfaceControl(SystemeReservation systeme) {
        this.systeme = systeme;
        this.scanner = new Scanner(System.in);
    }
    /**
     * Affiche le menu principal de l'application.
     */
    public void afficherMenu() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Gestion des vaisseaux");
        System.out.println("2. Gestion des personnes");
        System.out.println("3. Gestion des missions");
        System.out.println("4. Gestion des réservations");
        System.out.println("0. Quitter");
    }
    /**
     * Lance l'interface utilisateur et gère la navigation dans les menus.
     */
    // Méthode principale pour démarrer l'interface
    public void demarrer() {
        boolean continuer = true;

        while (continuer) {
            afficherMenu();
            System.out.print("Votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Pour consommer le retour à la ligne

            switch (choix) {
                case 1:
                    gererVaisseaux();
                    break;
                case 2:
                    gererPersonnes();
                    break;
                case 3:
                    gererMissions();
                    break;
                case 4:
                    gererReservations();
                    break;
                case 0:
                    continuer = false;
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }

    private void gererVaisseaux() {
        System.out.println("\n=== GESTION DES VAISSEAUX ===");
        System.out.println("1. Ajouter un vaisseau");
        System.out.println("2. Modifier un vaisseau");
        System.out.println("3. Supprimer un vaisseau");
        System.out.println("4. Afficher tous les vaisseaux");
        System.out.println("5. Rechercher un vaisseau");
        System.out.println("0. Retour au menu principal");

        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1:
                ajouterVaisseau();
                break;
            case 2:
                modifierVaisseau();
                break;
            case 3:
                supprimerVaisseau();
                break;
            case 4:
                afficherVaisseaux();
                break;
            case 5:
                rechercherVaisseau();
                break;
            case 0:
                break;
            default:
                System.out.println("Choix invalide, veuillez réessayer.");
        }
    }

    private void gererPersonnes() {
        System.out.println("\n=== GESTION DES PERSONNES ===");
        System.out.println("1. Ajouter un officier");
        System.out.println("2. Ajouter un civil");
        System.out.println("3. Afficher toutes les personnes");
        System.out.println("4. Rechercher une personne");
        System.out.println("0. Retour au menu principal");

        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1:
                ajouterOfficier();
                break;
            case 2:
                ajouterCivil();
                break;
            case 3:
                afficherPersonnes();
                break;
            case 4:
                rechercherPersonne();
                break;
            case 0:
                break;
            default:
                System.out.println("Choix invalide, veuillez réessayer.");
        }
    }

    private void gererMissions() {
        System.out.println("\n=== GESTION DES MISSIONS ===");
        System.out.println("1. Créer une mission");
        System.out.println("2. Afficher toutes les missions");
        System.out.println("3. Rechercher des missions");
        System.out.println("4. Associer une mission à un vaisseau");
        System.out.println("0. Retour au menu principal");

        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1:
                creerMission();
                break;
            case 2:
                afficherMissions();
                break;
            case 3:
                rechercherMissions();
                break;
            case 4:
                associerMissionVaisseau();
                break;
            case 0:
                break;
            default:
                System.out.println("Choix invalide, veuillez réessayer.");
        }
    }

    private void gererReservations() {
        System.out.println("\n=== GESTION DES RÉSERVATIONS ===");
        System.out.println("1. Créer une réservation");
        System.out.println("2. Afficher les réservations d'une personne");
        System.out.println("3. Afficher les réservations pour une mission");
        System.out.println("0. Retour au menu principal");

        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1:
                creerReservation();
                break;
            case 2:
                afficherReservationsPersonne();
                break;
            case 3:
                afficherReservationsMission();
                break;
            case 0:
                break;
            default:
                System.out.println("Choix invalide, veuillez réessayer.");
        }
    }

    // Méthodes pour la gestion des vaisseaux
    private void ajouterVaisseau() {
        System.out.println("\n=== AJOUTER UN VAISSEAU ===");
        System.out.print("Nom du vaisseau : ");
        String nom = scanner.nextLine();
        System.out.print("Immatriculation : ");
        String immatriculation = scanner.nextLine();
        System.out.print("Capacité maximale : ");
        int capaciteMaximale = scanner.nextInt();
        scanner.nextLine();
        Vaisseau v = new Vaisseau(nom, immatriculation, capaciteMaximale, new ArrayList<Mission>());
        systeme.ajouterVaisseau(v);
        System.out.println("Vaisseau ajouté avec succès !");
    }

    private void modifierVaisseau() {
        System.out.println("\n=== MODIFIER UN VAISSEAU ===");
        System.out.print("Immatriculation du vaisseau à modifier : ");
        String immatriculation = scanner.nextLine();
        
        System.out.print("Nouveau nom : ");
        String nouveauNom = scanner.nextLine();
        System.out.print("Nouvelle capacité : ");
        int nouvelleCapacite = scanner.nextInt();
        scanner.nextLine();

        systeme.modifierVaisseau(immatriculation, nouveauNom, nouvelleCapacite);
        System.out.println("Vaisseau modifié avec succès !");
    }

    private void supprimerVaisseau() {
        System.out.println("\n=== SUPPRIMER UN VAISSEAU ===");
        System.out.print("Immatriculation du vaisseau à supprimer : ");
        String immatriculation = scanner.nextLine();

        systeme.supprimerVaisseau(immatriculation);
        System.out.println("Vaisseau supprimé avec succès !");
    }

    private void afficherVaisseaux() {
        System.out.println("\n=== LISTE DES VAISSEAUX ===");
        List<Vaisseau> vaisseaux = systeme.getVaisseaux();

        if (vaisseaux.isEmpty()) {
            System.out.println("Aucun vaisseau enregistré.");
        } else {
            for (Vaisseau v : vaisseaux) {
                System.out.println(v.getNom() + " (" + v.getImmatriculation() + 
                                  ") - Capacité: " + v.getCapaciteMaximale());
            }
        }
    }

    private void rechercherVaisseau() {
        System.out.println("\n=== RECHERCHER UN VAISSEAU ===");
        System.out.print("Nom ou immatriculation : ");
        String recherche = scanner.nextLine();

        List<Vaisseau> resultats = systeme.rechercherVaisseaux(recherche, recherche);

        if (resultats.isEmpty()) {
            System.out.println("Aucun vaisseau trouvé.");
        } else {
            for (Vaisseau v : resultats) {
                System.out.println(v.getNom() + " (" + v.getImmatriculation() + 
                                  ") - Capacité: " + v.getCapaciteMaximale());
            }
        }
    }

    // Méthodes pour la gestion des personnes
    private void ajouterOfficier() {
        System.out.println("\n=== AJOUTER UN OFFICIER ===");
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Identifiant : ");
        String identifiant = scanner.nextLine();
        System.out.print("Rang : ");
        String rang = scanner.nextLine();
        System.out.print("Spécialité : ");
        String specialite = scanner.nextLine();
        Personne p = new Officier(nom, prenom, identifiant, rang, specialite);

        systeme.ajouterPersonne(p);
        System.out.println("Officier ajouté avec succès !");
    }

    private void ajouterCivil() {
        System.out.println("\n=== AJOUTER UN CIVIL ===");
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Identifiant : ");
        String identifiant = scanner.nextLine();
        System.out.print("Planète d'origine : ");
        String planete = scanner.nextLine();
        System.out.print("Motif de voyage : ");
        String motif = scanner.nextLine();

        Personne p = new Civil(nom, prenom, identifiant, planete, motif);

        systeme.ajouterPersonne(p);
        System.out.println("Civil ajouté avec succès !");
    }

    private void afficherPersonnes() {
        System.out.println("\n=== LISTE DES PERSONNES ===");
        List<Personne> personnes = systeme.getPersonnes();

        if (personnes.isEmpty()) {
            System.out.println("Aucune personne enregistrée.");
        } else {
            for (Personne p : personnes) {
                System.out.println(p.getNom() + " " + p.getPrenom() + 
                                  " (" + p.getIdentifiant() + ")");
            }
        }
    }

    private void rechercherPersonne() {
        System.out.println("\n=== RECHERCHER UNE PERSONNE ===");
        System.out.print("Nom ou identifiant : ");
        String recherche = scanner.nextLine();

        List<Personne> resultats = systeme.rechercherPersonnes(recherche, recherche);

        if (resultats.isEmpty()) {
            System.out.println("Aucune personne trouvée.");
        } else {
            for (Personne p : resultats) {
                System.out.println(p.getNom() + " " + p.getPrenom() + 
                                  " (" + p.getIdentifiant() + ")");
            }
        }
    }

    // Méthodes pour la gestion des missions
    private void creerMission() {
        System.out.println("\n=== CRÉER UNE MISSION ===");
        System.out.print("Code : ");
        String code = scanner.nextLine();
        System.out.print("Description : ");
        String description = scanner.nextLine();
        System.out.print("Destination : ");
        String destination = scanner.nextLine();
        System.out.print("Capacité maximale : ");
        int capacite = Integer.parseInt(scanner.nextLine());

        try {
            System.out.print("Date de départ (jj/mm/aaaa) : ");
            Date dateDepart = dateFormat.parse(scanner.nextLine());
            System.out.print("Date de retour (jj/mm/aaaa) : ");
            Date dateRetour = dateFormat.parse(scanner.nextLine());
            Mission m = new Mission(code, description, dateDepart, dateRetour, destination, capacite);
            systeme.ajouterMission(m);
            System.out.println("Mission créée avec succès !");
        } catch (ParseException e) {
            System.out.println("Format de date invalide !");
        }
    }

    private void afficherMissions() {
        System.out.println("\n=== LISTE DES MISSIONS ===");
        List<Mission> missions = systeme.getMissions();

        if (missions.isEmpty()) {
            System.out.println("Aucune mission enregistrée.");
        } else {
            for (Mission m : missions) {
                System.out.println(m.getCode() + " - " + m.getDestination() + 
                                 " (" + dateFormat.format(m.getDateDepart()) + " au " + 
                                 dateFormat.format(m.getDateRetour()) + ")");
            }
        }
    }

    private void rechercherMissions() {
        System.out.println("\n=== RECHERCHER DES MISSIONS ===");
        System.out.print("Destination : ");
        String destination = scanner.nextLine();

        List<Mission> resultats = systeme.rechercherMissionParDestination(destination);

        if (resultats.isEmpty()) {
            System.out.println("Aucune mission trouvée pour cette destination.");
        } else {
            for (Mission m : resultats) {
                System.out.println(m.getCode() + " - " + m.getDestination() + 
                                 " (" + dateFormat.format(m.getDateDepart()) + " au " + 
                                 dateFormat.format(m.getDateRetour()) + ")");
            }
        }
    }

    private void associerMissionVaisseau() {
        System.out.println("\n=== ASSOCIER MISSION À VAISSEAU ===");
        System.out.print("Code de la mission : ");
        String codeMission = scanner.nextLine();
        System.out.print("Immatriculation du vaisseau : ");
        String immatriculation = scanner.nextLine();

        Mission mission = null;
        Vaisseau vaisseau = null;
        
        // Trouver la mission
        for (Mission m : systeme.getMissions()) {
            if (m.getCode().equals(codeMission)) {
                mission = m;
                break;
            }
        }
        
        // Trouver le vaisseau
        for (Vaisseau v : systeme.getVaisseaux()) {
            if (v.getImmatriculation().equals(immatriculation)) {
                vaisseau = v;
                break;
            }
        }

        if (mission != null && vaisseau != null) {
            systeme.associerMissionAVaisseau(mission, vaisseau);
            System.out.println("Mission associée au vaisseau avec succès !");
        } else {
            System.out.println("Mission ou vaisseau non trouvé.");
        }
    }

    // Méthodes pour la gestion des réservations
    private void creerReservation() {
        System.out.println("\n=== CRÉER UNE RÉSERVATION ===");
        System.out.print("Identifiant de la personne : ");
        String idPersonne = scanner.nextLine();

        // Afficher les missions disponibles
        afficherMissions();
        System.out.print("Code de la mission : ");
        String codeMission = scanner.nextLine();

        // Trouver la personne
        Personne personne = null;
        for (Personne p : systeme.getPersonnes()) {
            if (p.getIdentifiant().equals(idPersonne)) {
                personne = p;
                break;
            }
        }

        // Trouver la mission
        Mission mission = null;
        for (Mission m : systeme.getMissions()) {
            if (m.getCode().equals(codeMission)) {
                mission = m;
                break;
            }
        }

        if (personne != null && mission != null) {
            Reservation reservation = systeme.effectuerReservation(idPersonne, codeMission);
            if (reservation != null) {
                System.out.println("Réservation créée avec succès !");
                System.out.println("ID: " + reservation.getIdReservation());
            } else {
                System.out.println("Impossible de créer la réservation (plus de places ?).");
            }
        } else {
            System.out.println("Personne ou mission non trouvée.");
        }
    }

    private void afficherReservationsPersonne() {
        System.out.println("\n=== RÉSERVATIONS D'UNE PERSONNE ===");
        System.out.print("Identifiant de la personne : ");
        String idPersonne = scanner.nextLine();

        System.out.println("Réservations :");
        systeme.afficherReservationsPersonne(idPersonne);
    }

    private void afficherReservationsMission() {
        System.out.println("\n=== RÉSERVATIONS POUR UNE MISSION ===");
        System.out.print("Code de la mission : ");
        String codeMission = scanner.nextLine();

        System.out.println("Réservations :");
        systeme.afficherReservationsMission(codeMission);
    }

    public static void main(String[] args) {
        SystemeReservation systeme = new SystemeReservation();
        InterfaceControl interfaceControl = new InterfaceControl(systeme);
        System.out.println("Bienvenue dans l'interface de contrôle");
        
        interfaceControl.demarrer();
    }
}