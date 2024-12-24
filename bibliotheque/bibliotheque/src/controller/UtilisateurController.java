package controller;

import model.UtilisateurModel;
import model.Utilisateur;
import view.UtilisateurView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import exception.CSVOperationException;
import exception.UtilisateurNotFoundException;

public class UtilisateurController {

    private UtilisateurModel utilisateurModel;
    private UtilisateurView utilisateurView;

    public UtilisateurController(UtilisateurModel utilisateurModel, UtilisateurView utilisateurView) {
        this.utilisateurModel = utilisateurModel;
        this.utilisateurView = utilisateurView;

        // Load users from CSV initially
        try {
			this.utilisateurModel.chargerUtilisateursDepuisCSV();
		} catch (CSVOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Attach listeners to the view buttons
        this.utilisateurView.addAddButtonListener(new AddButtonListener());
        this.utilisateurView.addDeleteButtonListener(new DeleteButtonListener());
        this.utilisateurView.addEditButtonListener(new EditButtonListener());
        this.utilisateurView.addListButtonListener(new ListButtonListener());
    }

    class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nom = utilisateurView.getNom();
            String prenom = utilisateurView.getPrenom();
            String email = utilisateurView.getEmail();
            String role = utilisateurView.getRole();

            if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || role.isEmpty()) {
                utilisateurView.showMessage("Tous les champs doivent être remplis !");
                return;
            }

            Utilisateur utilisateur = new Utilisateur(nom, prenom, email, role);
            utilisateurModel.ajouterUser(utilisateur);

            try {
                // Sauvegarde les utilisateurs dans le fichier CSV
                utilisateurModel.sauvegarderUtilisateursDansCSV();
                utilisateurView.addUserToTable(new Object[]{
                    utilisateur.getId(),
                    nom,
                    prenom,
                    email,
                    role
                });
                utilisateurView.clearInputFields();
                utilisateurView.showMessage("Utilisateur ajouté avec succès !");
            } catch (CSVOperationException ex) {
                // Affiche un message d'erreur si la sauvegarde échoue
                utilisateurView.showMessage("Erreur lors de la sauvegarde des utilisateurs : " + ex.getMessage());
            }
        }
    }


    class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = utilisateurView.getUserTable().getSelectedRow();
            if (selectedRow == -1) {
                utilisateurView.showMessage("Veuillez sélectionner un utilisateur à supprimer !");
                return;
            }

            int id = (int) utilisateurView.getUserTable().getValueAt(selectedRow, 0);
            try {
				utilisateurModel.supprimerUser(id);
			} catch (UtilisateurNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

            utilisateurView.clearTable();
            for (Utilisateur utilisateur : utilisateurModel.getUtilisateurs()) {
                utilisateurView.addUserToTable(new Object[]{
                        utilisateur.getId(),
                        utilisateur.getNom(),
                        utilisateur.getPrenom(),
                        utilisateur.getEmail(),
                        utilisateur.getRole()
                });
            }
            utilisateurView.showMessage("Utilisateur supprimé avec succès !");
        }
    }

    class EditButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = utilisateurView.getUserTable().getSelectedRow();
            if (selectedRow == -1) {
                utilisateurView.showMessage("Veuillez sélectionner un utilisateur à modifier !");
                return;
            }

            int id = (int) utilisateurView.getUserTable().getValueAt(selectedRow, 0);
            String nom = utilisateurView.getNom();
            String prenom = utilisateurView.getPrenom();
            String email = utilisateurView.getEmail();
            String role = utilisateurView.getRole();

            if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || role.isEmpty()) {
                utilisateurView.showMessage("Tous les champs doivent être remplis !");
                return;
            }

            try {
				utilisateurModel.modifierUser(id, nom, prenom, email, role);
			} catch (UtilisateurNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

            utilisateurView.clearTable();
            for (Utilisateur utilisateur : utilisateurModel.getUtilisateurs()) {
                utilisateurView.addUserToTable(new Object[]{
                        utilisateur.getId(),
                        utilisateur.getNom(),
                        utilisateur.getPrenom(),
                        utilisateur.getEmail(),
                        utilisateur.getRole()
                });
            }
            utilisateurView.showMessage("Utilisateur modifié avec succès !");
        }
    }

    class ListButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            utilisateurView.clearTable();
            for (Utilisateur utilisateur : utilisateurModel.getUtilisateurs()) {
                utilisateurView.addUserToTable(new Object[]{
                        utilisateur.getId(),
                        utilisateur.getNom(),
                        utilisateur.getPrenom(),
                        utilisateur.getEmail(),
                        utilisateur.getRole()
                });
            }
        }
    }
}
