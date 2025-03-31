package strfleet.modele.vaisseau;

import java.io.Serializable;
import java.util.List;

import strfleet.modele.mission.Mission;
/**
 * Représente un vaisseau spatial avec ses caractéristiques.
 */

public class Vaisseau implements Serializable {
    private String nom;
    private String immatriculation;
    private int capaciteMaximale;
    private List<Mission> missions;
    /**
     * Constructeur créant un nouveau vaisseau.
     * 
     * @param nom Nom du vaisseau (ex: "USS Enterprise")
     * @param immatriculation Numéro d'immatriculation (ex: "NCC-1701-D")
     * @param capaciteMaximale Nombre maximum de passagers
     * @param missions Liste des missions assignées
     */

    // Constructeur
    public Vaisseau(String nom, String immatriculation, int capaciteMaximale, List<Mission> missions) {
        this.nom = nom;
        this.immatriculation = immatriculation;
        this.capaciteMaximale = capaciteMaximale;
        this.missions = missions;
    }

        //getters
        public String getNom() {
            return nom;
        }
        public String getImmatriculation() {
            return immatriculation;
        }
        public int getCapaciteMaximale() {
            return capaciteMaximale;
        }
        public List<Mission> getMissions() {
            return missions;
        }
        //setters
        public void setNom(String nom) {
            this.nom = nom;
        }
        public void setImmatriculation(String immatriculation) {
            this.immatriculation = immatriculation;
        }
        public void setCapaciteMaximale(int capaciteMaximale) {
            this.capaciteMaximale = capaciteMaximale;
        }
        public void setMissions(List<Mission> missions) {
            this.missions = missions;
        }
                
        

    


            // Méthode pour ajouter une mission au vaisseau
        public void ajouterMission(Mission mission) {
            if (missions.size() < capaciteMaximale) {
                missions.add(mission);
            } else {
                System.out.println("Capacité maximale atteinte. Impossible d'ajouter la mission.");
            }
        }
        
       
        // Méthode pour supprimer une mission par son nom
        public void supprimerMission(String nommission) {
            for (int i = 0; i < missions.size(); i++) {
                if (missions.get(i).getCode().equals(nommission)) {
                    missions.remove(i);
                    return;
                }
            }
            System.out.println("Mission non trouvée.");
        }
        
        // Méthode pour afficher les informations du vaisseau
        public void afficherInformations() {
            System.out.println("Nom : " + nom);
            System.out.println("Immatriculation : " + immatriculation);
            System.out.println("Capacité maximale : " + capaciteMaximale);
            System.out.println("Missions : ");
            for (Mission mission : missions) {
                System.out.println("- " + mission.getCode());
            }
        }

       

    
}


