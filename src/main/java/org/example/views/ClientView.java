package org.example.views;

import org.example.entities.Client;

import javax.swing.*;
import java.sql.Connection;

import static org.example.controllers.ClientController.addClient;
import static org.example.controllers.ClientController.viewClients;

public class ClientView {

    public static void manageClients(Connection c) {
        while (true) {
            String[] options = {"Add Clients", "View Clients", "Main Menu"};
            int choice = JOptionPane.showOptionDialog(null,
                    "=== Manage Clients ===",
                    "Manage Clients",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);
            switch (choice) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Creating a Client");
                    String name = JOptionPane.showInputDialog("Enter the name");
                    String email = JOptionPane.showInputDialog("Enter the email");
                    String phone = JOptionPane.showInputDialog("Enter the phone");
                    String address = JOptionPane.showInputDialog("Enter the address");
                    Client c1 = new Client(name, email, phone, address);
                    addClient(c1, c);
                    break;
                case 1:
                    viewClients(c);
                    break;
                case 2:
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
            }
        }
    }

}
