
package model;

import exception.LivreNotFoundException;
import exception.CSVOperationException;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;

public class LivreModel implements LivreModelInterface {

    private ArrayList<Livre> livres = new ArrayList<>();

    public LivreModel() {
    	try {
            chargerLivresDepuisCSV();
        } catch (CSVOperationException e) {
            System.err.println("Erreur lors du chargement des livres : " + e.getMessage());
        }
    }

    @Override
    public void AjouterLivre(Livre livre) {
        livres.add(livre);
        try {
            sauvegarderLivresDansCSV(); // Sauvegarder après chaque ajout
        } catch (CSVOperationException e) {
            System.err.println("Erreur lors de la sauvegarde: " + e.getMessage());
        }
    }

    @Override
    public void SupprimerLivre(Livre livre) throws LivreNotFoundException {
        if (!livres.remove(livre)) {
            throw new LivreNotFoundException("Le livre avec ID " + livre.getId() + " n'existe pas.");
        }
        try {
            sauvegarderLivresDansCSV(); // Sauvegarder après chaque suppression
        } catch (CSVOperationException e) {
            System.err.println("Erreur lors de la sauvegarde: " + e.getMessage());
        }
    }

    @Override
    public void ModifierLivre(int idLivre, String nouvTitre) throws LivreNotFoundException {
        boolean modifie = false;
        for (Livre livre : livres) {
            if (livre.getId() == idLivre) {
                livre.setTitre(nouvTitre);
                modifie = true;
                break;
            }
        }
        if (!modifie) {
            throw new LivreNotFoundException("Le livre avec ID " + idLivre + " n'existe pas.");
        }
        try {
            sauvegarderLivresDansCSV(); // Sauvegarder après modification
        } catch (CSVOperationException e) {
            System.err.println("Erreur lors de la sauvegarde: " + e.getMessage());
        }
    }

    @Override
    public void AfficherLivre() {
        for (Livre livre : livres) {
            System.out.println(livre);
        }
    }

    @Override
    public List<Livre> RechercherLivre(String motCle) {
        List<Livre> resultats = new ArrayList<>();
        for (Livre livre : livres) {
            if (livre.getTitre().toLowerCase().contains(motCle.toLowerCase())) {
                resultats.add(livre);
            }
        }
        return resultats;
    }


    // Méthode pour charger les livres depuis le fichier CSV
    public void chargerLivresDepuisCSV() throws CSVOperationException {
        File file = new File("livres.csv");

        // Vérification si le fichier existe, sinon le créer avec une structure initiale
        if (!file.exists()) {
            try {
                file.createNewFile();
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                    bw.write("Titre,Auteur,Annee,Genre,Disponible,Prix"); // En-têtes
                    bw.newLine();
                }
            } catch (IOException e) {
                throw new CSVOperationException("Impossible de créer le fichier CSV.", e);
            }
            return;
        }

        // Lecture du fichier CSV
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                // Ignorer les en-têtes ou lignes invalides
                if (ligne.trim().isEmpty() || ligne.startsWith("Titre")) {
                    continue;
                }

                String[] details = ligne.split(",");
                if (details.length < 6) {
                    System.err.println("Ligne invalide dans le fichier CSV : " + ligne);
                    continue; // Ligne incorrecte, passer à la suivante
                }

                try {
                    String titre = details[0].trim();
                    String auteur = details[1].trim();
                    int anneePublication = Integer.parseInt(details[2].trim());
                    String genre = details[3].trim();
                    boolean disponible = Boolean.parseBoolean(details[4].trim());
                    double prix = Double.parseDouble(details[5].trim());

                    Livre livre = new Livre(titre, auteur, anneePublication, genre, disponible, prix);
                    livres.add(livre); // Ajouter le livre à la liste
                } catch (NumberFormatException e) {
                    System.err.println("Erreur de format pour la ligne : " + ligne);
                }
            }
        } catch (IOException e) {
            throw new CSVOperationException("Erreur lors de la lecture du fichier CSV.", e);
        }
    }


    // Méthode pour sauvegarder les livres dans le fichier CSV
    public void sauvegarderLivresDansCSV() throws CSVOperationException {
        // Vérification si la liste de livres est vide
        if (livres.isEmpty()) {
            System.err.println("La liste des livres est vide, rien à sauvegarder.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("livres.csv"))) {
            // Écrire les en-têtes du fichier
            bw.write("Titre,Auteur,Annee,Genre,Disponible,Prix");
            bw.newLine();

            // Écrire chaque livre dans le fichier
            for (Livre livre : livres) {
                bw.write(livre.getTitre() + "," +
                         livre.getAuteur() + "," +
                         livre.getAnneePublication() + "," +
                         livre.getGenre() + "," +
                         livre.isDisponible() + "," +
                         livre.getPrix());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new CSVOperationException("Erreur lors de l'écriture dans le fichier CSV.", e);
        }
    }

    
    

    public ArrayList<Livre> getLivres() {
        return livres;
    }

    public Livre getLivreById(int livreId) throws LivreNotFoundException {
        System.out.println("Recherche du livre avec l'ID : " + livreId);
        return livres.stream()
                     .filter(livre -> livre.getId() == livreId)
                     .findFirst()
                     .orElseThrow(() -> {
                         System.err.println("Livre avec ID " + livreId + " non trouvé.");
                         return new LivreNotFoundException("Livre avec ID " + livreId + " non trouvé.");
                     });
    }

    public List<Livre> getLivresByIds(List<Integer> livresIds) {
        List<Livre> result = new ArrayList<>();
        for (int id : livresIds) {
            try {
                System.out.println("Recherche du livre avec l'ID : " + id);
                Livre livre = getLivreById(id);
                result.add(livre);
            } catch (LivreNotFoundException e) {
                System.err.println(e.getMessage()); // Optionnel : journalisez l'erreur
            }
        }
        return result;
    }
    
    
    
    public List<Livre> FiltrerParGenre(String genre) {
        List<Livre> resultats = new ArrayList<>();
        for (Livre livre : livres) {
            if (livre.getGenre().equalsIgnoreCase(genre)) {
                resultats.add(livre);
            }
        }
        return resultats;
    }

    
    
    
    public List<Livre> FiltrerParPrix(double prixMin, double prixMax) {
        List<Livre> resultats = new ArrayList<>();
        for (Livre livre : livres) {
            if (livre.getPrix() >= prixMin && livre.getPrix() <= prixMax) {
                resultats.add(livre);
            }
        }
        return resultats;
    }

    
    
    
    
    
    
    
}
