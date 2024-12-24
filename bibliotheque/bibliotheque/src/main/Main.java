package main;

import view.Login;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create and display the Login window first
                Login login = new Login();
                login.setVisible(true);
            }
        });
    }
}
