package org.example.controllers;

import org.example.entities.Machine;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MachineController {

    public static void addMachine(Machine machine, Connection connection) {
        String sql = "INSERT INTO machines (model, serial_number, status) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, machine.getModel());
            pstmt.setString(2, machine.getSerial_number());
            pstmt.setString(3, machine.getStatus());
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Machine added successfully.");
        }  catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "Error: Duplicated serial number.");
            } else if (e.getErrorCode() == 1452) {
                JOptionPane.showMessageDialog(null, "Error: No fields can be left empty.");
            } else {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error adding a machine.");
            }
        }
    }

    public static void showMachines(Connection connection, int page) {
        String sql = "SELECT * FROM machines LIMIT ?, 5";
        List<Machine> machines = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, (page - 1) * 5); // For example, if page = 1, this will be 0 (first 5 machines)
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Integer machineId = rs.getInt("machine_id");
                String model = rs.getString("model");
                String serialNumber = rs.getString("serial_number");
                Machine machine = new Machine(machineId, model, serialNumber);
                machines.add(machine);
            }
            StringBuilder result = new StringBuilder();
            if (machines.isEmpty()) {
                result.append("No machines found on this page.");
            } else {
                for (Machine m : machines) {
                    result.append(m.toString()).append("\n");
                }
            }
            JOptionPane.showMessageDialog(null, result.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving machines.");
        }
    }

    public static int getTotalPages(Connection connection) {
        String sql = "SELECT COUNT(*) FROM machines";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                int totalMachines = rs.getInt(1);
                return (int) Math.ceil(totalMachines / 5.0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
