package org.example.views;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

import static org.example.persistance.database.Database.openConnection;
import static org.example.views.ClientView.manageClients;
import static org.example.views.MachineView.manageMachines;
import static org.example.views.RentView.manageRents;

public class AdminView {

    public static void adminMenu() throws SQLException {
        JOptionPane.showMessageDialog(null, "Welcome Administrator");
        while (true) {
            String[] options = {"Manage Clients", "Manage Machines", "Manage Rents", "Exit"};
            int choice = JOptionPane.showOptionDialog(null,
                    "=== Main Menu ===",
                    "Main Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);
            Connection c = openConnection();
            switch (choice) {
                case 0:
                    manageClients(c);
                    break;
                case 1:
                    manageMachines(c);
                    break;
                case 2:
                    manageRents(c);
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Exiting...");
                    assert c != null;
                    c.close();
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
            }
        }
    }

}
