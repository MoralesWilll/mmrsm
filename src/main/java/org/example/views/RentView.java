package org.example.views;

import org.example.entities.Rent;

import javax.swing.*;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.example.controllers.RentController.*;
import static org.example.controllers.RentController.softDeleteRent;

public class RentView {

    public static void manageRents(Connection c) {
        while (true) {
            String[] options = {"Add Rent", "View active Rents", "View deactivated Rents", "Delete Rent", "Back to Main Menu"};
            int choice = JOptionPane.showOptionDialog(null,
                    "=== Manage Rents ===",
                    "Manage Rents",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);
            switch (choice) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Creating a Rent");
                    int idC = Integer.parseInt(JOptionPane.showInputDialog("Enter the id of the Client"));
                    int idM = Integer.parseInt(JOptionPane.showInputDialog("Enter the id of the Machine"));
                    String dateInput = JOptionPane.showInputDialog("Enter the finish date (yyyy-mm-dd):");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date fisnishDate = null;
                    try {
                        fisnishDate = dateFormat.parse(dateInput); // Parse the date
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Invalid date format. Please enter the date as yyyy-mm-dd.");
                    }
                    if (fisnishDate != null) {
                        Rent r1 = new Rent(idC, idM, fisnishDate);
                        addRent(r1, c);
                    }
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "View all the active Rents");
                    showActiveRents(c);
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "View all the deactivated Rents");
                    showDisabledRents(c);
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Delete a Rent");
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Enter the id of the Rent you want to delete"));
                    softDeleteRent(id, c);
                    break;
                case 4:
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
            }
        }
    }

}
