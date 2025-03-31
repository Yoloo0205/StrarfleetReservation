package strfleet.modele.personne;

public class Officier extends Personne {
    private String rang;
    private String specialite;

    public Officier(String nom, String prenom, String identifiant, String rang , String specialite){

    super(nom,prenom,identifiant);
    this.rang= rang;
    this.specialite= specialite; 
    }
   //Getters 
    public String getrang() {
        return rang;
    }
    public String getspecialite() {
        return specialite;
    }
        // Setters 
    
    public void setrang(String rang) {
        this.rang = rang;

    }
    
    public void setspecialite(String specialite) {
        this.specialite = specialite;
    }
   
    
    // Méthode abstraite
    @Override
    public String getDescription() {
        return "Officier : " + super.toString() + " Rang : " + rang + " Specialite : " + specialite;
    }
   





}
