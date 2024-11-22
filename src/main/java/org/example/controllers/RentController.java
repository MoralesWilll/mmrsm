package org.example.controllers;

import org.example.entities.Rent;
import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class RentController {

    public static void addRent(Rent rent, Connection c) {
        boolean isMachineRented = false;
        String machineStatusQuery = "SELECT status FROM machines WHERE machine_id = ?";
        try (PreparedStatement pstmt = c.prepareStatement(machineStatusQuery)) {
            pstmt.setInt(1, rent.getMachine_id());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String machineStatus = rs.getString("status");
                    if ("Rented".equalsIgnoreCase(machineStatus)) {
                        JOptionPane.showMessageDialog(null, "The machine is already rented");
                        isMachineRented = true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error checking machine status.");
        }
        if (isMachineRented) {
            return;
        }
        String sql = "INSERT INTO rents (client_id, machine_id, start_date, finish_date, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = c.prepareStatement(sql)) {
            pstmt.setInt(1, rent.getClient_id());
            pstmt.setInt(2, rent.getMachine_id());
            pstmt.setDate(3, new java.sql.Date(rent.getStart_date().getTime()));
            pstmt.setDate(4, new java.sql.Date(rent.getFinish_date().getTime()));
            pstmt.setString(5, rent.getStatus());
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Rent added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding rent.");
        }
        String updateMachineStatusSql = "UPDATE machines SET status = 'Rented' WHERE machine_id = ?";
        try (PreparedStatement pstmt = c.prepareStatement(updateMachineStatusSql)) {
            pstmt.setInt(1, rent.getMachine_id());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1452) {
                JOptionPane.showMessageDialog(null, "Error: No fields can be left empty.");
            } else {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error adding a machine.");
            }
        }
    }

    public static void showActiveRents(Connection c) {
        String sql = "SELECT * FROM rents WHERE status = 'Active'";
        StringBuilder result = new StringBuilder();
        try (Statement stmt = c.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int rentId = rs.getInt("rent_id");
                int clientId = rs.getInt("client_id");
                int machineId = rs.getInt("machine_id");
                Date startDate = rs.getDate("start_date");
                Date finishDate = rs.getDate("finish_date");
                String status = rs.getString("status");
                Rent rent = new Rent(rentId, clientId, machineId, status, startDate, finishDate);
                result.append(rent).append("\n");
            }
            if (result.length() > 0) {
                JOptionPane.showMessageDialog(null, result.toString());
            } else {
                JOptionPane.showMessageDialog(null, "No active rents found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void showDisabledRents(Connection c) {
        String sql = "SELECT * FROM rents WHERE status = 'Disabled'";
        StringBuilder result = new StringBuilder();
        try (Statement stmt = c.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int rentId = rs.getInt("rent_id");
                int clientId = rs.getInt("client_id");
                int machineId = rs.getInt("machine_id");
                Date startDate = rs.getDate("start_date");
                Date finishDate = rs.getDate("finish_date");
                String status = rs.getString("status");
                Rent rent = new Rent(rentId, clientId, machineId, status, startDate, finishDate);
                result.append(rent).append("\n");
            }
            if (result.length() > 0) {
                JOptionPane.showMessageDialog(null, result.toString());
            } else {
                JOptionPane.showMessageDialog(null, "No deactivated rents found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void softDeleteRent(int rentId, Connection c) {
        String rentQuery = "SELECT machine_id, status FROM rents WHERE rent_id = ?";
        String updateRentQuery = "UPDATE rents SET status = 'Disabled' WHERE rent_id = ?";
        String updateMachineQuery = "UPDATE machines SET status = 'Available' WHERE machine_id = ?";
        try {
            int machineId = -1;
            String rentStatus = "";
            try (PreparedStatement rentStmt = c.prepareStatement(rentQuery)) {
                rentStmt.setInt(1, rentId);
                try (ResultSet rs = rentStmt.executeQuery()) {
                    if (rs.next()) {
                        machineId = rs.getInt("machine_id");
                        rentStatus = rs.getString("status");
                    } else {
                        JOptionPane.showMessageDialog(null, "Rent not found.");
                        return;
                    }
                }
            }
            if ("Disabled".equalsIgnoreCase(rentStatus)) {
                JOptionPane.showMessageDialog(null, "This rent has already been deleted.");
                return;
            }
            try (PreparedStatement updateRentStmt = c.prepareStatement(updateRentQuery)) {
                updateRentStmt.setInt(1, rentId);
                int rowsUpdated = updateRentStmt.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Rent deactivated successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Error deactivating rent.");
                    return;
                }
            }
            if (machineId != -1) {
                try (PreparedStatement updateMachineStmt = c.prepareStatement(updateMachineQuery)) {
                    updateMachineStmt.setInt(1, machineId);
                    int rowsUpdated = updateMachineStmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(null, "Machine status updated to Available.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error updating machine status.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "No associated machine found for the rent.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error during operation.");
        }
    }


}
