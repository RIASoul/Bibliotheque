package model;

import exception.EmpruntNotFoundException;
import exception.UtilisateurNotFoundException;
import exception.LivreNotFoundException;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpruntModel implements EmpruntModelInterface {

    private ArrayList<Emprunt> emprunts = new ArrayList<>();
    private UtilisateurModel utilisateurModel;
    private LivreModel livreModel;

    public EmpruntModel(UtilisateurModel utilisateurModel, LivreModel livreModel) {
        this.utilisateurModel = utilisateurModel;
        this.livreModel = livreModel;
        try {
            chargerEmpruntsDepuisCSV(); // Charger les emprunts depuis le fichier CSV dès que l'instance est créée
        } catch (IOException | UtilisateurNotFoundException | LivreNotFoundException e) {
            e.printStackTrace();  // Gérer ou loguer les erreurs
        }
    }
    
    
    public EmpruntModel() {
    	this.utilisateurModel = new UtilisateurModel();
    	this.livreModel = new LivreModel();
    }
    

    @Override
    public void enregistrerEmprunt(Emprunt emprunt) throws IOException {
        emprunts.add(emprunt);
        sauvegarderEmpruntsDansCSV(); // Sauvegarder après chaque ajout
    }

    @Override
    public void afficherEmprunt() {
        for (Emprunt emprunt : emprunts) {
            System.out.println(emprunt);
        }
    }

    @Override
    public List<Emprunt> consulterHistoriqueParUtilisateur(int utilisateurId) throws UtilisateurNotFoundException {
        List<Emprunt> historique = new ArrayList<>();
        boolean utilisateurTrouve = false;

        for (Emprunt emprunt : emprunts) {
            if (emprunt.getUtilisateur().getId() == utilisateurId) {
                historique.add(emprunt);
                utilisateurTrouve = true;
            }
        }

        if (!utilisateurTrouve) {
            throw new UtilisateurNotFoundException("Utilisateur avec ID " + utilisateurId + " non trouvé.");
        }

        return historique;
    }

    @Override
    public void prolongerEmprunt(int empruntId, LocalDate dateRetour) throws EmpruntNotFoundException, IOException {
        boolean empruntTrouve = false;
        for (Emprunt emprunt : emprunts) {
            if (emprunt.getId() == empruntId) {
                emprunt.setDateRetourPrevue(dateRetour);
                sauvegarderEmpruntsDansCSV(); // Sauvegarder après modification
                empruntTrouve = true;
                break;
            }
        }

        if (!empruntTrouve) {
            throw new EmpruntNotFoundException("Emprunt avec ID " + empruntId + " non trouvé.");
        }
    }

    @Override
    public void chargerEmpruntsDepuisCSV() throws IOException, UtilisateurNotFoundException, LivreNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader("emprunts.csv"))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] details = ligne.split(",");
                if (details.length >= 4) {
                    // Recherche de l'utilisateur
                    Utilisateur utilisateur = utilisateurModel.getUtilisateurs().stream()
                        .filter(u -> u.getId() == Integer.parseInt(details[0]))
                        .findFirst().orElseThrow(() -> new UtilisateurNotFoundException("Utilisateur avec ID " + details[0] + " non trouvé."));

                    // Recherche des livres
                    List<Livre> livres = new ArrayList<>();
                    for (int i = 1; i < details.length - 2; i++) { // Avoid parsing dates as book IDs
                        try {
                            int livreId = Integer.parseInt(details[i]);
                            Livre livre = livreModel.getLivres().stream()
                                .filter(l -> l.getId() == livreId)
                                .findFirst()
                                .orElseThrow(() -> new LivreNotFoundException("Livre avec ID " + livreId + " non trouvé."));
                            livres.add(livre);
                        } catch (NumberFormatException e) {
                            // Handle potential parsing errors (e.g., non-integer data in details[i])
                            System.out.println("Invalid book ID: " + details[i]);
                        }
                    }

                    // Ensure the user and books are valid
                    if (utilisateur != null && !livres.isEmpty()) {
                        // Parse the last two elements as dates
                        LocalDate dateEmprunt = LocalDate.parse(details[details.length - 2]);
                        LocalDate dateRetour = LocalDate.parse(details[details.length - 1]);
                        Emprunt emprunt = new Emprunt(utilisateur, livres, dateEmprunt, dateRetour);
                        emprunts.add(emprunt);
                    }
                }
            }
        }
    }

    @Override
    public void sauvegarderEmpruntsDansCSV() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("emprunts.csv"))) {
            for (Emprunt emprunt : emprunts) {
                StringBuilder ligne = new StringBuilder();
                ligne.append(emprunt.getUtilisateur().getId()); // Utilisateur ID
                for (Livre livre : emprunt.getLivres()) {
                    ligne.append(",").append(livre.getId()); // Livre IDs
                }
                ligne.append(",").append(emprunt.getDateEmprunt()).append(",").append(emprunt.getDateRetourPrevue());
                bw.write(ligne.toString());
                bw.newLine();
            }
        }
    }

    public ArrayList<Emprunt> getEmprunts() {
        return emprunts;
    }

    public UtilisateurModel getUtilisateurModel() {
        return utilisateurModel;
    }

    public LivreModel getLivreModel() {
        return livreModel;
    }
}
