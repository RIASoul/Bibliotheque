package controller;

import model.Livre;
import model.LivreModel;
import view.LivreView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class LivreController {
    private LivreModel livreModel;
    private LivreView livreView;

    public LivreController(LivreModel model, LivreView view) {
        this.livreModel = model;
        this.livreView = view;

        // Ajouter des écouteurs d'événements pour les boutons avec des expressions lambda
        livreView.addAddButtonListener(e -> {
            try {
                String titre = livreView.getTitre();
                String auteur = livreView.getAuteur();
                int annee = livreView.getAnnee();
                String genre = livreView.getGenre();
                double prix = livreView.getPrix();
                boolean disponible = livreView.isDisponible();

                Livre livre = new Livre(titre, auteur, annee, genre, disponible, prix);
                livreModel.AjouterLivre(livre);

                JOptionPane.showMessageDialog(livreView, "Livre ajouté avec succès!");
                livreView.updateLivreList(convertLivreListToStringList(livreModel.getLivres()));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(livreView, "Erreur lors de l'ajout du livre: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        livreView.addDeleteButtonListener(e -> {
            try {
                int selectedIndex = livreView.livreList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Livre livre = livreModel.getLivres().get(selectedIndex);
                    livreModel.SupprimerLivre(livre);

                    JOptionPane.showMessageDialog(livreView, "Livre supprimé avec succès!");
                    livreView.updateLivreList(convertLivreListToStringList(livreModel.getLivres()));
                } else {
                    JOptionPane.showMessageDialog(livreView, "Veuillez sélectionner un livre à supprimer", "Erreur", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(livreView, "Erreur lors de la suppression du livre: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        livreView.addEditButtonListener(e -> {
            try {
                int selectedIndex = livreView.livreList.getSelectedIndex();
                if (selectedIndex != -1) {
                    // Récupérer le livre sélectionné
                    Livre livre = livreModel.getLivres().get(selectedIndex);
                    int idLivre = livre.getId();

                    // Demander le nouveau titre via une boîte de dialogue
                    String nouveauTitre = JOptionPane.showInputDialog(
                        livreView,
                        "Entrez le nouveau titre pour le livre :",
                        "Modifier le titre",
                        JOptionPane.PLAIN_MESSAGE
                    );

                    // Vérifier si l'utilisateur a entré un titre
                    if (nouveauTitre != null && !nouveauTitre.trim().isEmpty()) {
                        livreModel.ModifierLivre(idLivre, nouveauTitre); // Modifier le titre

                        JOptionPane.showMessageDialog(livreView, "Livre modifié avec succès!");
                        livreView.updateLivreList(convertLivreListToStringList(livreModel.getLivres())); // Rafraîchir la liste
                    } else {
                        JOptionPane.showMessageDialog(livreView, "Le titre ne peut pas être vide.", "Erreur", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(livreView, "Veuillez sélectionner un livre à modifier", "Erreur", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(livreView, "Erreur lors de la modification du livre: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });


        livreView.addListButtonListener(e -> livreView.updateLivreList(convertLivreListToStringList(livreModel.getLivres())));

        // Ajouter un écouteur pour le bouton de recherche
        livreView.addSearchButtonListener(e -> {
            String titreRecherche = livreView.getSearchTitre();
            String genreFiltre = livreView.getFilterGenre();
            double prixMax = livreView.getFilterPrix();
            double prixMin = 0; // Prix minimum par défaut

            // Récupérer tous les livres
            List<Livre> resultats = livreModel.getLivres();

            // Filtrer par titre
            if (!titreRecherche.isEmpty()) {
                resultats = resultats.stream()
                        .filter(livre -> livre.getTitre().toLowerCase().contains(titreRecherche.toLowerCase()))
                        .toList();
            }

            // Filtrer par genre
            if (!genreFiltre.isEmpty()) {
                List<Livre> genreResultats = livreModel.FiltrerParGenre(genreFiltre);
                resultats = resultats.stream()
                        .filter(genreResultats::contains)
                        .toList(); // Intersection avec les résultats précédents
            }

            // Filtrer par prix
            if (prixMax != Double.MAX_VALUE) {
                List<Livre> prixResultats = livreModel.FiltrerParPrix(prixMin, prixMax);
                resultats = resultats.stream()
                        .filter(prixResultats::contains)
                        .toList(); // Intersection avec les résultats précédents
            }

            // Mettre à jour la vue avec les résultats
            livreView.updateLivreList(convertLivreListToStringList(resultats));
        });
    }

    private List<String> convertLivreListToStringList(List<Livre> livres) {
        List<String> stringList = new ArrayList<>();
        for (Livre livre : livres) {
            stringList.add(String.format("ID: %d - %s by %s (%d)", 
                livre.getId(), livre.getTitre(), livre.getAuteur(), livre.getAnneePublication()));
        }
        return stringList;
    }
}
