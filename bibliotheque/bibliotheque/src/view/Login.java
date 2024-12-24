package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import model.UtilisateurModel;
import model.Utilisateur;
import main.Main; // Add this import to use the MenuView

public class Login extends JFrame {
    private UtilisateurModel utilisateurModel = new UtilisateurModel();

    public Login() {
        setTitle("Gestion des Utilisateurs");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Titre en haut
        JLabel titleLabel = new JLabel("Bibliothèque", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Bouton de Connexion
        JButton btnConnexion = new JButton("Connexion");
        btnConnexion.setPreferredSize(new Dimension(150, 30));
        panel.add(btnConnexion, BorderLayout.CENTER);

        add(panel, BorderLayout.CENTER);

        // Action pour le bouton Connexion
        btnConnexion.addActionListener(e -> afficherFormulaireConnexion());
    }

    private void afficherFormulaireConnexion() {
        // Création du formulaire de connexion
        JDialog dialog = new JDialog(this, "Connexion", true);
        dialog.setSize(300, 250);
        dialog.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();

        JLabel lblPassword = new JLabel("Mot de passe:");
        JPasswordField txtPassword = new JPasswordField();

        JCheckBox chkAdmin = new JCheckBox("Admin");
        JCheckBox chkBibliothecaire = new JCheckBox("Bibliothécaire");

        JButton btnConnexion = new JButton("Connexion");
        JButton btnAnnuler = new JButton("Annuler");

        dialog.add(lblEmail);
        dialog.add(txtEmail);
        dialog.add(lblPassword);
        dialog.add(txtPassword);
        dialog.add(chkAdmin);
        dialog.add(chkBibliothecaire);
        dialog.add(btnConnexion);
        dialog.add(btnAnnuler);

        // Action pour le bouton Connexion
        btnConnexion.addActionListener(e -> {
            String emailInput = txtEmail.getText();
            String passwordInput = new String(txtPassword.getPassword());
            boolean credentialsValid = false;
            try (BufferedReader br = new BufferedReader(new FileReader("credentials.csv"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts[0].equals(emailInput) && parts[1].equals(passwordInput)) {
                        credentialsValid = true;
                        break;
                    }
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(dialog, "Erreur lors de la vérification des identifiants : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }

            if (credentialsValid) {
                if (chkBibliothecaire.isSelected()) {
                    JOptionPane.showMessageDialog(dialog, "Connexion réussie en tant que Bibliothécaire !");
                    dialog.dispose();

                    // Rediriger vers l'interface MenuView
                    MenuView menuView = new MenuView();
                    menuView.setVisible(true);
                } else if (chkAdmin.isSelected()) {
                    JOptionPane.showMessageDialog(dialog, "Connexion réussie en tant qu'Admin !", "Information", JOptionPane.INFORMATION_MESSAGE);
                  MenuView menuView = new MenuView();
                    menuView.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(dialog, "Veuillez sélectionner un rôle.", "Erreur", JOptionPane.ERROR_MESSAGE);
                  //AdminView menuView = new AdminView();
                    //AdminView.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(dialog, "Email ou mot de passe incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Action pour le bouton Annuler
        btnAnnuler.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }
}
