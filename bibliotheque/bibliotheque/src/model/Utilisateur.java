package model;

public class Utilisateur {

    private static int nextId = 1; // Variable statique pour l'incrémentation automatique de l'ID

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String role;

    // Constructeur sans paramètre pour les tests ou les valeurs par défaut
    public Utilisateur() {
        this.id = nextId++; // ID auto-incrémenté
    }

    // Constructeur avec paramètres (sauf ID)
    public Utilisateur(String nom, String prenom, String email, String role) {
        this.id = nextId++; // ID auto-incrémenté
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.role = role;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    // Supprimer ou désactiver le setter pour l'ID si l'on veut éviter qu'il soit modifiable
    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Utilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", role=" + role + "]";
    }
}
