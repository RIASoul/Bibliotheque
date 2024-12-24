package model;

import java.time.LocalDate;
import java.util.List;

public class Emprunt {
    private static int idCounter = 1; // Compteur pour les IDs auto-incrémentés
    private int id;
    private Utilisateur utilisateur;
    private List<Livre> livres; // Liste de livres
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;

    public Emprunt(Utilisateur utilisateur, List<Livre> livres, LocalDate dateEmprunt, LocalDate dateRetourPrevue) {
        this.id = idCounter++; // Génère un ID unique
        this.utilisateur = utilisateur;
        this.livres = livres;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        
    }

    public int getId() {
        return id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public List<Livre> getLivres() {
        return livres;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public LocalDate getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public void setDateRetourPrevue(LocalDate dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }

    @Override
    public String toString() {
        StringBuilder livresInfo = new StringBuilder();
        for (Livre livre : livres) {
            livresInfo.append(livre.getTitre()).append(", ");
        }
        return "Emprunt [ID=" + id +
               ", Utilisateur=" + utilisateur.getNom() +
               ", Livres=" + livresInfo.toString() +
               ", Date Emprunt=" + dateEmprunt +
               ", Date Retour Prévue=" + dateRetourPrevue + "]";
    }
}
