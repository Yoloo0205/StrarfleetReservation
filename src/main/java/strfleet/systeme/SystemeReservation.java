package strfleet.systeme;
import strfleet.modele.mission.Mission;
import strfleet.modele.personne.*;
import strfleet.modele.reservation.Reservation;
import strfleet.modele.vaisseau.*;

import java.util.*;

import java.io.*;
/**
 * Classe cœur du système gérant la logique métier de l'application.
 * <p>
 * Contient les données et opérations principales sur les vaisseaux, personnes,
 * missions et réservations.
 * </p>
 */

public class SystemeReservation {
    private List<Vaisseau> vaisseaux;
    private List<Personne> personnes;
    private List<Mission> missions;
    private List<Reservation> reservations;

    // Constructeur par défaut
    public SystemeReservation() {
        this.vaisseaux = new ArrayList<>();
        this.personnes = new ArrayList<>();
        this.missions = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    // Constructeur avec paramètres
    public SystemeReservation(List<Vaisseau> vaisseaux, List<Personne> personnes, 
                            List<Mission> missions, List<Reservation> reservations) {
        this.vaisseaux = vaisseaux != null ? vaisseaux : new ArrayList<>();
        this.personnes = personnes != null ? personnes : new ArrayList<>();
        this.missions = missions != null ? missions : new ArrayList<>();
        this.reservations = reservations != null ? reservations : new ArrayList<>();
    }

    // Getters
    public List<Vaisseau> getVaisseaux() {
        return vaisseaux;
    }

    public List<Personne> getPersonnes() {
        return personnes;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    // Setters
    public void setVaisseaux(List<Vaisseau> vaisseaux) {
        this.vaisseaux = vaisseaux != null ? vaisseaux : new ArrayList<>();
    }

    public void setPersonnes(List<Personne> personnes) {
        this.personnes = personnes != null ? personnes : new ArrayList<>();
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions != null ? missions : new ArrayList<>();
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations != null ? reservations : new ArrayList<>();
    }

    // Méthodes de gestion
    /**
     * Ajoute un nouveau vaisseau à la flotte.
     * 
     * @param vaisseau Le vaisseau à ajouter
     
     */
    public void ajouterVaisseau(Vaisseau vaisseau) {
        if (vaisseau != null) {
            vaisseaux.add(vaisseau);
        }
    }

    public void ajouterPersonne(Personne personne) {
        if (personne != null) {
            personnes.add(personne);
        }
    }

    public void ajouterMission(Mission mission) {
        if (mission != null) {
            missions.add(mission);
        }
    }
    /**
     * Effectue une réservation pour une personne sur une mission.
     *
     * @param idPersonne Identifiant de la personne
     * @param codeMission Code de la mission
     * @return La réservation créée ou null si échec
     */
    public Reservation effectuerReservation(String idPersonne, String codeMission) {
        Personne personne = trouverPersonneParId(idPersonne);
        Mission mission = trouverMissionParCode(codeMission);

        if (personne != null && mission != null) {
            if (mission.getReservations().size() < mission.getCapaciteMaximale()) {
                Reservation reservation = new Reservation(genererIdReservation(), personne, mission, new Date());
                reservations.add(reservation);
                mission.ajouterReservation(reservation);
                return reservation;
            } else {
                System.out.println("Capacité maximale atteinte pour cette mission.");
            }
        }
        return null;
    }

    private String genererIdReservation() {
        return "RES" + (reservations.size() + 1);
    }

    private Personne trouverPersonneParId(String id) {
        for (Personne p : personnes) {
            if (p.getIdentifiant().equals(id)) {
                return p;
            }
        }
        return null;
    }

    private Mission trouverMissionParCode(String code) {
        for (Mission m : missions) {
            if (m.getCode().equals(code)) {
                return m;
            }
        }
        return null;
    }

    public List<Mission> rechercherMissionParDestination(String destination) {
        List<Mission> resultats = new ArrayList<>();
        if (destination != null) {
            for (Mission m : missions) {
                if (m.getDestination().equalsIgnoreCase(destination)) {
                    resultats.add(m);
                }
            }
        }
        return resultats;
    }

    public void supprimerVaisseau(String immatriculation) {
        Iterator<Vaisseau> iterator = vaisseaux.iterator();
        while (iterator.hasNext()) {
            Vaisseau v = iterator.next();
            if (v.getImmatriculation().equals(immatriculation)) {
                iterator.remove();
                return;
            }
        }
        System.out.println("Vaisseau non trouvé.");
    }

    public void modifierVaisseau(String immatriculation, String nouveauNom, int nouvelleCapacite) {
        for (Vaisseau v : vaisseaux) {
            if (v.getImmatriculation().equals(immatriculation)) {
                if (nouveauNom != null && !nouveauNom.isEmpty()) {
                    v.setNom(nouveauNom);
                }
                if (nouvelleCapacite > 0) {
                    v.setCapaciteMaximale(nouvelleCapacite);
                }
                return;
            }
        }
        System.out.println("Vaisseau non trouvé.");
    }

    public void supprimerMission(String code) {
        Iterator<Mission> iterator = missions.iterator();
        while (iterator.hasNext()) {
            Mission m = iterator.next();
            if (m.getCode().equals(code)) {
                iterator.remove();
                return;
            }
        }
        System.out.println("Mission non trouvée.");
    }

    public void associerMissionAVaisseau(Mission mission, Vaisseau vaisseau) {
        if (mission != null && vaisseau != null && 
            missions.contains(mission) && vaisseaux.contains(vaisseau)) {
            vaisseau.ajouterMission(mission);
            mission.setVaisseau(vaisseau);
        } else {
            System.out.println("Mission ou vaisseau non trouvé.");
        }
    }

    public void creerReservation(String idReservation, Personne passager, Mission mission, Date dateReservation) {
        if (passager != null && mission != null && dateReservation != null) {
            Reservation reservation = new Reservation(idReservation, passager, mission, dateReservation);
            reservations.add(reservation);
            mission.ajouterReservation(reservation);
        }
    }

    public void confirmerReservation(String idReservation) {
        for (Reservation r : reservations) {
            if (r.getIdReservation().equals(idReservation)) {
                r.confirmerReservation();
                return;
            }
        }
        System.out.println("Réservation non trouvée.");
    }

    public void annulerReservation(String idReservation) {
        Iterator<Reservation> iterator = reservations.iterator();
        while (iterator.hasNext()) {
            Reservation r = iterator.next();
            if (r.getIdReservation().equals(idReservation)) {
                r.getMission().supprimerReservation(r);
                iterator.remove();
                return;
            }
        }
        System.out.println("Réservation non trouvée.");
    }

    public void afficherReservationsPersonne(String identifiant) {
        for (Reservation r : reservations) {
            if (r.getPassager().getIdentifiant().equals(identifiant)) {
                System.out.println(r);
            }
        }
    }

    public void afficherReservationsMission(String codeMission) {
        for (Reservation r : reservations) {
            if (r.getMission().getCode().equals(codeMission)) {
                System.out.println(r.getPassager());
            }
        }
    }

    public List<Mission> rechercherMissions(String destination, Date dateDepart, Date dateRetour) {
        List<Mission> resultats = new ArrayList<>();
        for (Mission m : missions) {
            boolean matches = true;
            if (destination != null && !m.getDestination().equalsIgnoreCase(destination)) {
                matches = false;
            }
            if (dateDepart != null && !m.getDateDepart().equals(dateDepart)) {
                matches = false;
            }
            if (dateRetour != null && !m.getDateRetour().equals(dateRetour)) {
                matches = false;
            }
            if (matches) {
                resultats.add(m);
            }
        }
        return resultats;
    }

    public List<Personne> rechercherPersonnes(String nom, String identifiant) {
        List<Personne> resultats = new ArrayList<>();
        for (Personne p : personnes) {
            boolean matches = true;
            if (nom != null && !p.getNom().equalsIgnoreCase(nom)) {
                matches = false;
            }
            if (identifiant != null && !p.getIdentifiant().equalsIgnoreCase(identifiant)) {
                matches = false;
            }
            if (matches) {
                resultats.add(p);
            }
        }
        return resultats;
    }

    public List<Vaisseau> rechercherVaisseaux(String nom, String immatriculation) {
        List<Vaisseau> resultats = new ArrayList<>();
        for (Vaisseau v : vaisseaux) {
            boolean matches = true;
            if (nom != null && !v.getNom().equalsIgnoreCase(nom)) {
                matches = false;
            }
            if (immatriculation != null && !v.getImmatriculation().equalsIgnoreCase(immatriculation)) {
                matches = false;
            }
            if (matches) {
                resultats.add(v);
            }
        }
        return resultats;
    }

    public void sauvegarderDonnees(String fichier) {
        try {
            File dossier = new File("data");
            if (!dossier.exists()) {
                dossier.mkdirs();
            }

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier));
            oos.writeObject(vaisseaux);
            oos.writeObject(personnes);
            oos.writeObject(missions);
            oos.writeObject(reservations);
            oos.close();
            
            System.out.println("✅ Données sauvegardées dans " + fichier);
        } catch (IOException e) {
            System.out.println("❌ Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void chargerDonnees(String fichier) {
        try {
            File file = new File(fichier);
            if (!file.exists()) {
                System.out.println("⚠️ Aucun fichier trouvé, chargement ignoré.");
                return;
            }

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier));
            vaisseaux = (List<Vaisseau>) ois.readObject();
            personnes = (List<Personne>) ois.readObject();
            missions = (List<Mission>) ois.readObject();
            reservations = (List<Reservation>) ois.readObject();
            ois.close();
            
            System.out.println("✅ Données chargées depuis " + fichier);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ Erreur lors du chargement des données : " + e.getMessage());
        }
    }
}