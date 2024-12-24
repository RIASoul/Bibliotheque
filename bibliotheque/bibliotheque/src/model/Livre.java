package model;

public class Livre {

    private static int idCounter = 1; // Gestion de l'incrémentation automatique
    private int id;
    private String titre;
    private String auteur;
    private int anneePublication;
    private String genre;
    private boolean disponible;
    private double prix;
    private int nombreDeLivres;

    public int getNombreDeLivres() {
		return nombreDeLivres;
	}

	public void setNombreDeLivres(int nombreDeLivres) {
		this.nombreDeLivres = nombreDeLivres;
	}

	// Constructeur sans paramètre ID, avec prix ajouté
    public Livre(String titre, String auteur, int anneePublication, String genre, boolean disponible, double prix) {
        this.id = idCounter++; // Génère automatiquement un ID unique
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.genre = genre;
        this.disponible = disponible;
        this.prix = prix;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getAnneePublication() {
        return anneePublication;
    }

    public void setAnneePublication(int anneePublication) {
        this.anneePublication = anneePublication;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    // toString pour afficher les détails du livre
    @Override
    public String toString() {
        return "Livre [id=" + id + ", titre=" + titre + ", auteur=" + auteur + ", anneePublication="
                + anneePublication + ", genre=" + genre + ", disponible=" + disponible + ", prix=" + prix + "]";
    }
}
