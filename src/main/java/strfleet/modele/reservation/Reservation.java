package strfleet.modele.reservation;
import java.io.Serializable;
import java.util.Date;
import strfleet.modele.personne.*;
import strfleet.modele.mission.*;

public class Reservation implements Serializable{
    private String idReservation;
    private Personne passager;
    private Mission mission;
    private Date dateReservation;
    private boolean confirmee;

    // Constructeur
    public Reservation(String idReservation, Personne passager, Mission mission, Date dateReservation) {
        this.idReservation = idReservation;
        this.passager = passager;
        this.mission = mission;
        this.dateReservation = dateReservation;
        this.confirmee = false; // Par défaut, la réservation n'est pas confirmée
    }
    // Getters
    public String getIdReservation() {
        return idReservation;
    }
    public Personne getPassager() {
        return passager;
    }
    public Mission getMission() {
        return mission;
    }
    public Date getDateReservation() {
        return dateReservation;
    }
    public boolean isConfirmee() {
        return confirmee;
    }
    // Setters
    public void setIdReservation(String idReservation) {
        this.idReservation = idReservation;
    }
    public void setPassager(Personne passager) {
        this.passager = passager;
    }
    
    public void setMission(Mission mission) {
        this.mission = mission;
    }
    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }
    public void setConfirmee(boolean confirmee) {
        this.confirmee = confirmee;
    }

    // Méthode pour confirmer la réservation
    public void confirmerReservation() {
        this.confirmee = true;
    }
    // Méthode pour annuler la réservation
    public void annulerReservation() {
        this.confirmee = false;
    }
    //Methode pour créer une réservation d'une Personne sur une mission
    public void creerReservation(String idReservation, Personne passager, Mission mission, Date dateReservation) {
        this.idReservation = idReservation;
        this.passager = passager;
        this.mission = mission;
        this.dateReservation = dateReservation;
    }
    

    
}
