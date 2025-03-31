package strfleet.modele.mission;
import strfleet.modele.reservation.Reservation;
import  strfleet.modele.vaisseau.Vaisseau;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Représente une mission spatiale avec ses paramètres.
 */


public class Mission implements Serializable{
    private String code;
    private String description;
    private Date dateDepart;
    private Date dateRetour;
    private String destination;
    private Vaisseau vaisseau;
    private List<Reservation> reservations;
    private int capaciteMaximale;
    /**
     * Crée une nouvelle mission.
     * 
     * @param code Code unique identifiant la mission
     * @param description Brève description de la mission
     * @param dateDepart Date de départ de la mission
     * @param dateRetour Date de retour prévue
     * @param destination Planète de destination
     * @param capaciteMaximale Nombre maximum de passagers
     * @throws IllegalArgumentException si les dates sont incohérentes
     */

    // Constructeur
    public Mission(String code, String description, Date dateDepart, Date dateRetour, String destination,int capaciteMaximale) {
        this.code = code;
        this.description = description;
        this.dateDepart = dateDepart;
        this.dateRetour = dateRetour;
        this.destination = destination;
        this.reservations=new ArrayList<>();
        this.capaciteMaximale=capaciteMaximale;
    }
    // Getters
    public String getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    public Date getDateDepart() {
        return dateDepart;
    }
    public Date getDateRetour() {
        return dateRetour;
    }
    public String getDestination() {
        return destination;
    }
    public Vaisseau getVaisseau() {
        return vaisseau;
    }
    public List<Reservation> getReservations() {
        return reservations;
    }
    public int getCapaciteMaximale() {
        return capaciteMaximale;
    }
    // Setters
    public void setCode(String code) {
        this.code = code;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }
    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public void setVaisseau(Vaisseau vaisseau) {
        this.vaisseau = vaisseau;
    }
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    public void setCapaciteMaximale(int capaciteMaximale) {
        this.capaciteMaximale = capaciteMaximale;
    }
    
    
    
    // Méthode pour ajouter une réservation
    public void ajouterReservation(Reservation reservation) {
        if (reservations.size() < capaciteMaximale) {
            reservations.add(reservation);
        } else {
            System.out.println("Capacité maximale atteinte. Impossible d'ajouter la réservation.");
        }
    }
    //Méthode pour annuler une réservation
    public void supprimerReservation(Reservation reservation) {
        reservations.remove(reservation);
    }
    //Méthode pour afficher le nombre de places disponibles
    public int getPlacesDisponibles() {
        return capaciteMaximale - reservations.size();
    }
    //

   
        
}
