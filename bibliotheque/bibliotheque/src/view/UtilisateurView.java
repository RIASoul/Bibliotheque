package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class UtilisateurView extends JPanel {

    private JTextField nomField;
    private JTextField prenomField;
    private JTextField emailField;
    private JTextField roleField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton editButton;
    private JButton listButton;

    private JTable userTable;
    private DefaultTableModel tableModel;

    public UtilisateurView() {
        setSize(700, 500);
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout(10, 10)); // BorderLayout pour l'ensemble du JPanel

        // Inputs Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.add(new JLabel("Nom:"));
        nomField = new JTextField();
        inputPanel.add(nomField);

        inputPanel.add(new JLabel("Prénom:"));
        prenomField = new JTextField();
        inputPanel.add(prenomField);

        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);

        inputPanel.add(new JLabel("Role:"));
        roleField = new JTextField();
        inputPanel.add(roleField);

        // Buttons Panel
        addButton = new JButton("Ajouter Utilisateur");
        deleteButton = new JButton("Supprimer Utilisateur");
        editButton = new JButton("Modifier Utilisateur");
        listButton = new JButton("Afficher Utilisateurs");

        // Utilisation de GridLayout pour mieux organiser les boutons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10)); // 1 ligne et 4 colonnes
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);
        buttonPanel.add(listButton);

        // Table Panel
        tableModel = new DefaultTableModel(new String[]{"ID", "Nom", "Prénom", "Email", "Role"}, 0);
        userTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(userTable);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10)); // BorderLayout pour organiser les éléments

        // Ajouter les panels à mainPanel
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER); // Ajouter mainPanel au JPanel

        // Revalidate et Repaint pour s'assurer que l'interface se met à jour
        this.revalidate();
        this.repaint();
    }

    // Getter methods
    public String getNom() {
        return nomField.getText();
    }

    public String getPrenom() {
        return prenomField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getRole() {
        return roleField.getText();
    }

    public JTable getUserTable() {
        return userTable;
    }

    public void clearInputFields() {
        nomField.setText("");
        prenomField.setText("");
        emailField.setText("");
        roleField.setText("");
    }

    public void addUserToTable(Object[] rowData) {
        tableModel.addRow(rowData);
    }

    public void clearTable() {
        tableModel.setRowCount(0);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    // Listener methods
    public void addAddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void addDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void addEditButtonListener(ActionListener listener) {
        editButton.addActionListener(listener);
    }

    public void addListButtonListener(ActionListener listener) {
        listButton.addActionListener(listener);
    }
}
