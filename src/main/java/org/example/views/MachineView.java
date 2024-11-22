package org.example.views;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.example.controllers.MachineController;
import org.example.entities.Machine;
import org.example.importer.MachineImporter;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.example.controllers.MachineController.addMachine;

public class MachineView {

    public  static void manageMachines(Connection c) {
        while (true) {
            String[] options = {"Add Machine", "View Machines", "Import machines from excel", "Main Menu"};
            int choice = JOptionPane.showOptionDialog(null,
                    "=== Manage Machines ===",
                    "Manage Machines",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);
            switch (choice) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Creating a Machine");
                    String model = JOptionPane.showInputDialog("Enter the model");
                    String snumber = JOptionPane.showInputDialog("Enter the serial number");
                    String status = JOptionPane.showInputDialog("Enter the status");
                    Machine m1 = new Machine(model, snumber, status);
                    addMachine(m1, c);
                    break;
                case 1:
                    int page = 1;
                    int totalPages = MachineController.getTotalPages(c);
                    while (true) {
                        MachineController.showMachines(c, page);
                        String message = "Page " + page + " of " + totalPages + "\n" +
                                "Choose an option:";
                        String[] options1 = {"Previous Page", "Back to Main Menu", "Next Page"};
                        int choice1 = JOptionPane.showOptionDialog(null, message, "Pagination Options",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null, options1, options1[0]);
                        switch (choice1) {
                            case 0:
                                if (page > 1) {
                                    page--;
                                } else {
                                    JOptionPane.showMessageDialog(null, "You are already on the first page.");
                                }
                                break;
                            case 1:
                                return;
                            case 2:
                                if (page < totalPages) {
                                    page++;
                                } else {
                                    JOptionPane.showMessageDialog(null, "You are already on the last page.");
                                }
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
                        }
                    }
                case 2:
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Select the Excel file to import");
                    int result = fileChooser.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                        try {
                            MachineImporter.importAndAddMachines(filePath, c);
                        } catch (IOException | InvalidFormatException e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error importing machines: " + e.getMessage());
                        }
                    }
                    break;
                case 3:
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
            }
        }
    }

}
