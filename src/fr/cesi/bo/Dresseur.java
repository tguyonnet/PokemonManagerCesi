package fr.cesi.bo;

/**
 * Classe Dresseur
 *
 * @author Caliendo Julien
 */
public class Dresseur {
    private String nom;
    private String prenom;

    /**
     * Le constructeur
     *
     * @param nom : le nom de famille
     * @param prenom : le prénom
     */
    public Dresseur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
