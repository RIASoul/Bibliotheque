package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;



public class RetourModel implements RetourModelInterface {
    private List<Retour> retours = new ArrayList<>();

    @Override
    public void enregistrerRetour(int empruntId, LocalDate dateRetourEffective, double penalite) {
        Retour retour = new Retour(empruntId, dateRetourEffective, penalite);
        retours.add(retour);
    }

    @Override
    public Retour rechercherEmprunt(int empruntId) {
        for (Retour retour : retours) {
            if (retour.getEmpruntId() == empruntId) {
                return retour;
            }
        }
        return null;
    }

    @Override
    public double calculerPenalite(LocalDate dateRetourEffective, LocalDate dateRetourPrevue, double tauxPenaliteParJour) {
        if (dateRetourEffective.isAfter(dateRetourPrevue)) {
            long joursDeRetard = ChronoUnit.DAYS.between(dateRetourPrevue, dateRetourEffective);
            return joursDeRetard * tauxPenaliteParJour;
        }
        return 0.0;
    }
    
    
    
    public void chargerRetoursDepuisCSV(String cheminFichier) {
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] details = ligne.split(",");
                int empruntId = Integer.parseInt(details[0]);
                LocalDate dateRetourEffective = LocalDate.parse(details[1]);
                double penalite = Double.parseDouble(details[2]);
                Retour retour = new Retour(empruntId, dateRetourEffective, penalite);
                retours.add(retour);
            }
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement des retours depuis le fichier CSV : " + e.getMessage());
        }
    }
    
    
    
    
    public void sauvegarderRetoursDansCSV(String cheminFichier) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(cheminFichier))) {
            for (Retour retour : retours) {
                bw.write(retour.getEmpruntId() + "," +
                         retour.getDateRetourEffective() + "," +
                         retour.getPenalite());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des retours dans le fichier CSV : " + e.getMessage());
        }
    }
    
    
    
    
}
