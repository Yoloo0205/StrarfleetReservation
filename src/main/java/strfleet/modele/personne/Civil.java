package strfleet.modele.personne;

public class Civil extends Personne {

    private String planète_d_origine;
    private String motif_de_voyage;



    public Civil(String nom, String prenom, String identifiant, String planète_d_origine, String motif_de_voyage) {
        super(nom, prenom, identifiant);
        this.planète_d_origine = planète_d_origine;
        this.motif_de_voyage = motif_de_voyage;
    }
    // Getters
    public String getPlanete_d_origine() {
        return planète_d_origine;
    }
    public String getMotif_de_voyage() {
        return motif_de_voyage;
    }
    // Setters
    public void setPlanete_d_origine(String planète_d_origine) {
        this.planète_d_origine = planète_d_origine;
    }
    public void setMotif_de_voyage(String motif_de_voyage) {
        this.motif_de_voyage = motif_de_voyage;
    }
    // Méthode toString
    @Override
    public String toString() {
        return super.toString() + " Planète d'origine : " + planète_d_origine + " Motif de voyage : " + motif_de_voyage;
    }

    // Méthode abstraite
    @Override
    public String getDescription() {
        return "Civil : " + super.toString() + " Planète d'origine : " + planète_d_origine + " Motif de voyage : " + motif_de_voyage;
    }
    // Méthode pour afficher les informations du civil
    public void afficherInformations() {
        System.out.println("Nom : " + getNom());
        System.out.println("Prénom : " + getPrenom());
        System.out.println("Identifiant : " + getIdentifiant());
        System.out.println("Planète d'origine : " + planète_d_origine);
        System.out.println("Motif de voyage : " + motif_de_voyage);
    }
    //

}
