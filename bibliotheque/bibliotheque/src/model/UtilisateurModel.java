package model;

import exception.UtilisateurNotFoundException;
import exception.CSVOperationException;

import java.io.*;
import java.util.ArrayList;

public class UtilisateurModel {
    private ArrayList<Utilisateur> utilisateurs = new ArrayList<>();

    public ArrayList<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }
    
    
    
    public UtilisateurModel() {
    	try {
    		chargerUtilisateursDepuisCSV();
        } catch (CSVOperationException e) {
            e.printStackTrace();
        }
    }
    
    
    

    public void ajouterUser(Utilisateur utilisateur) {
        utilisateurs.add(utilisateur);
    }

    public void modifierUser(int id, String nom, String prenom, String email, String role) throws UtilisateurNotFoundException {
        for (Utilisateur u : utilisateurs) {
            if (u.getId() == id) {
                // Validation des données utilisateur
                if (email == null || !email.contains("@")) {
                    throw new IllegalArgumentException("Format de l'email invalide");
                }
                u.setNom(nom);
                u.setPrenom(prenom);
                u.setEmail(email);
                u.setRole(role);
                return;
            }
        }
        throw new UtilisateurNotFoundException("Utilisateur avec l'ID " + id + " non trouvé");
    }

    public void supprimerUser(int id) throws UtilisateurNotFoundException {
        boolean removed = utilisateurs.removeIf(u -> u.getId() == id);
        if (!removed) {
            throw new UtilisateurNotFoundException("Utilisateur avec l'ID " + id + " non trouvé");
        }
    }

    public void chargerUtilisateursDepuisCSV() throws CSVOperationException {
        try (BufferedReader br = new BufferedReader(new FileReader("utilisateurs.csv"))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] details = ligne.split(",");
                if (details.length == 4) {
                    String nom = details[0];
                    String prenom = details[1];
                    String email = details[2];
                    String role = details[3];
                    ajouterUser(new Utilisateur(nom, prenom, email, role));
                }
            }
        } catch (IOException e) {
            // Ajout de la cause (IOException) dans l'exception CSVOperationException
            throw new CSVOperationException("Erreur lors de la lecture du fichier CSV : " + e.getMessage(), e);
        }
    }

    public void sauvegarderUtilisateursDansCSV() throws CSVOperationException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("utilisateurs.csv"))) {
            for (Utilisateur utilisateur : utilisateurs) {
                bw.write(utilisateur.getNom() + "," +
                         utilisateur.getPrenom() + "," +
                         utilisateur.getEmail() + "," +
                         utilisateur.getRole());
                bw.newLine();
            }
        } catch (IOException e) {
            // Ajout de la cause (IOException) dans l'exception CSVOperationException
            throw new CSVOperationException("Erreur lors de l'écriture dans le fichier CSV : " + e.getMessage(), e);
        }
    }

    
    
    
    public Utilisateur getUtilisateurById(int utilisateurId) {
        System.out.println("Recherche de l'utilisateur avec l'ID : " + utilisateurId);
        for (Utilisateur utilisateur : utilisateurs) {
            System.out.println("Vérification de l'utilisateur : " + utilisateur.getId());
            if (utilisateur.getId() == utilisateurId) {
                return utilisateur;
            }
        }
        System.out.println("Utilisateur avec l'ID " + utilisateurId + " non trouvé.");
        return null;
    }

    
    
    public void copierCredentialsVersUtilisateursCSV() throws CSVOperationException {
        try (BufferedReader br = new BufferedReader(new FileReader("credentials.csv"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("utilisateurs.csv", true))) {
            
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] details = ligne.split(",");
                if (details.length == 2) { // Assuming credentials.csv has email, password
                    String email = details[0];
                    String password = details[1];  // We don't need to use the password
                    
                    // Find or create the utilisateur object (Modify as per your logic)
                    Utilisateur utilisateur = getUtilisateurByEmail(email);
                    if (utilisateur != null) {
                        // Add the user to the list if not already present
                        if (!utilisateurs.contains(utilisateur)) {
                            utilisateurs.add(utilisateur);
                        }

                        // Write to utilisateurs.csv (without password)
                        bw.write(utilisateur.getNom() + "," + utilisateur.getPrenom() + "," + utilisateur.getEmail() + "," + utilisateur.getRole());
                        bw.newLine();
                    }
                }
            }
        } catch (IOException e) {
            throw new CSVOperationException("Erreur lors de la copie des données de credentials.csv vers utilisateurs.csv : " + e.getMessage(), e);
        }
    }

    
    
    
    private Utilisateur getUtilisateurByEmail(String email) {
        return utilisateurs.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
    
    
    public String getUserRoleByEmail(String email) {
        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getEmail().equals(email)) {
                return utilisateur.getRole();
            }
        }
        return null; // Retourne null si l'utilisateur n'est pas trouvé
    }
    
    
    
    
    
}
