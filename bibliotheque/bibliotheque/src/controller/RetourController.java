package controller;

import model.RetourModel;
import model.Retour;
import view.RetourView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class RetourController {
    private RetourModel retourModel;
    private RetourView retourView;

    public RetourController(RetourModel retourModel, RetourView retourView) {
        this.retourModel = retourModel;
        this.retourView = retourView;
        initController();
    }

    private void initController() {
        retourView.addEnregistrerButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleEnregistrerRetour();
            }
        });

        retourView.addRechercherButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRechercherRetour();
            }
        });

        retourView.addCalculerPenaliteButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCalculerPenalite();
            }
        });
    }

    private void handleEnregistrerRetour() {
        try {
            int empruntId = Integer.parseInt(retourView.getEmpruntId());
            LocalDate dateRetourEffective = LocalDate.parse(retourView.getDateRetourEffective());
            LocalDate dateRetourPrevue = LocalDate.parse(retourView.getDateRetourPrevue());
            double tauxPenalite = Double.parseDouble(retourView.getTauxPenalite());

            double penalite = retourModel.calculerPenalite(dateRetourEffective, dateRetourPrevue, tauxPenalite);
            retourModel.enregistrerRetour(empruntId, dateRetourEffective, penalite);

            retourView.afficherMessage("Retour enregistré avec succès.\nPénalité : " + penalite);
        } catch (NumberFormatException | DateTimeParseException ex) {
            retourView.afficherMessage("Erreur de saisie : Veuillez vérifier les champs.");
        } catch (Exception ex) {
            retourView.afficherMessage("Erreur : " + ex.getMessage());
        }
    }

    private void handleRechercherRetour() {
        try {
            int empruntId = Integer.parseInt(retourView.getEmpruntId());
            Retour retour = retourModel.rechercherEmprunt(empruntId);

            if (retour != null) {
                retourView.displayRetour(retour);
            } else {
                retourView.afficherMessage("Aucun retour trouvé pour l'emprunt ID : " + empruntId);
            }
        } catch (NumberFormatException ex) {
            retourView.afficherMessage("Erreur de saisie : ID Emprunt invalide.");
        } catch (Exception ex) {
            retourView.afficherMessage("Erreur : " + ex.getMessage());
        }
    }

    private void handleCalculerPenalite() {
        try {
            LocalDate dateRetourEffective = LocalDate.parse(retourView.getDateRetourEffective());
            LocalDate dateRetourPrevue = LocalDate.parse(retourView.getDateRetourPrevue());
            double tauxPenalite = Double.parseDouble(retourView.getTauxPenalite());

            double penalite = retourModel.calculerPenalite(dateRetourEffective, dateRetourPrevue, tauxPenalite);
            retourView.afficherMessage("Pénalité calculée : " + penalite);
        } catch (NumberFormatException | DateTimeParseException ex) {
            retourView.afficherMessage("Erreur de saisie : Veuillez vérifier les champs.");
        } catch (Exception ex) {
            retourView.afficherMessage("Erreur : " + ex.getMessage());
        }
    }
}
