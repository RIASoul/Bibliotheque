package controller;

import model.Emprunt;
import model.EmpruntModel;
import model.Livre;
import model.Utilisateur;
import view.EmpruntView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class EmpruntController {

    private EmpruntModel empruntModel;
    private EmpruntView empruntView;

    public EmpruntController(EmpruntModel empruntModel, EmpruntView empruntView) {
        this.empruntModel = empruntModel;
        this.empruntView = empruntView;

        // Initialisation des données dans la vue
        initialiserListeUtilisateurs();
        initialiserListeLivres();

        // Ajout des listeners aux boutons
        empruntView.addEnregistrerListener(new EnregistrerListener());
        empruntView.addAfficherListener(new AfficherListener());
        empruntView.addConsulterHistoriqueListener(new ConsulterHistoriqueListener());
        empruntView.addProlongerListener(new ProlongerListener());
    }

    private void initialiserListeUtilisateurs() {
        List<String> utilisateurs = new ArrayList<>();
        for (Utilisateur utilisateur : empruntModel.getUtilisateurModel().getUtilisateurs()) {
            utilisateurs.add(utilisateur.getId() + " - " + utilisateur.getNom());
        }
        empruntView.setUtilisateurs(utilisateurs);
    }

    private void initialiserListeLivres() {
        List<String> livres = new ArrayList<>();
        for (Livre livre : empruntModel.getLivreModel().getLivres()) {
            livres.add(livre.getId() + " - " + livre.getTitre());
        }
        empruntView.setLivres(livres);
    }

    // Listener pour enregistrer un emprunt
    class EnregistrerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String utilisateurSelectionne = empruntView.getUtilisateurSelectionne();
                List<String> livresSelectionnes = empruntView.getLivresSelectionnes();
                String dateEmprunt = empruntView.getDateEmprunt();
                String dateRetour = empruntView.getDateRetour();

                if (utilisateurSelectionne == null || livresSelectionnes.isEmpty() || dateEmprunt.isEmpty() || dateRetour.isEmpty()) {
                    JOptionPane.showMessageDialog(empruntView, "Tous les champs doivent être remplis !");
                    return;
                }

                int utilisateurId = Integer.parseInt(utilisateurSelectionne.split(" - ")[0]);
                Utilisateur utilisateur = empruntModel.getUtilisateurModel().getUtilisateurById(utilisateurId);

                List<Livre> livres = new ArrayList<>();
                for (String livreStr : livresSelectionnes) {
                    int livreId = Integer.parseInt(livreStr.split(" - ")[0]);
                    livres.add(empruntModel.getLivreModel().getLivreById(livreId));
                }

                LocalDate dateEmpruntParsed = LocalDate.parse(dateEmprunt);
                LocalDate dateRetourParsed = LocalDate.parse(dateRetour);

                Emprunt emprunt = new Emprunt(utilisateur, livres, dateEmpruntParsed, dateRetourParsed);
                empruntModel.enregistrerEmprunt(emprunt);

                JOptionPane.showMessageDialog(empruntView, "Emprunt enregistré avec succès !");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(empruntView, "Erreur lors de l'enregistrement de l'emprunt : " + ex.getMessage());
            }
        }
    }

    // Listener pour afficher les emprunts
    class AfficherListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            empruntModel.afficherEmprunt();
        }
    }

    // Listener pour consulter l'historique d'un utilisateur
    class ConsulterHistoriqueListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String utilisateurSelectionne = empruntView.getUtilisateurSelectionne();
                if (utilisateurSelectionne == null) {
                    JOptionPane.showMessageDialog(empruntView, "Veuillez sélectionner un utilisateur !");
                    return;
                }

                int utilisateurId = Integer.parseInt(utilisateurSelectionne.split(" - ")[0]);
                List<Emprunt> historique = empruntModel.consulterHistoriqueParUtilisateur(utilisateurId);

                StringBuilder message = new StringBuilder("Historique des emprunts :\n");
                for (Emprunt emprunt : historique) {
                    message.append(emprunt).append("\n");
                }

                JOptionPane.showMessageDialog(empruntView, message.toString());
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(empruntView, "Erreur lors de la consultation de l'historique : " + ex.getMessage());
            }
        }
    }

    // Listener pour prolonger un emprunt
    class ProlongerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String empruntIdStr = JOptionPane.showInputDialog(empruntView, "Entrez l'ID de l'emprunt à prolonger :");
                if (empruntIdStr == null || empruntIdStr.isEmpty()) {
                    return;
                }

                int empruntId = Integer.parseInt(empruntIdStr);
                String nouvelleDateRetour = JOptionPane.showInputDialog(empruntView, "Entrez la nouvelle date de retour (YYYY-MM-DD) :");
                if (nouvelleDateRetour == null || nouvelleDateRetour.isEmpty()) {
                    return;
                }

                LocalDate dateRetourParsed = LocalDate.parse(nouvelleDateRetour);
                empruntModel.prolongerEmprunt(empruntId, dateRetourParsed);

                JOptionPane.showMessageDialog(empruntView, "Emprunt prolongé avec succès !");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(empruntView, "Erreur lors de la prolongation de l'emprunt : " + ex.getMessage());
            }
        }
    }
}
