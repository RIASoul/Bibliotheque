package model;

import java.time.LocalDate;

public interface RetourModelInterface {
  
    void enregistrerRetour(int empruntId, LocalDate dateRetourEffective, double penalite);
    Retour rechercherEmprunt(int empruntId);
    double calculerPenalite(LocalDate dateRetourEffective, LocalDate dateRetourPrevue, double tauxPenaliteParJour);
}
