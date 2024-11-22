package org.example.controllers;

import org.example.entities.Client;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientController {

    public static void addClient(Client client, Connection connection) {
        String sql = "INSERT INTO clients (name, email, phone, address) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, client.getName());
            pstmt.setString(2, client.getEmail());
            pstmt.setString(3, client.getPhone());
            pstmt.setString(4, client.getAddress());
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Client added successfully.");
        }  catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "Error: Duplicate email or phone number.");
            } else if (e.getErrorCode() == 1452) {
                JOptionPane.showMessageDialog(null, "Error: No fields can be left empty.");
            } else {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error adding client.");
            }
        }
    }

    public static void viewClients(Connection connection) {
        String sql = "SELECT * FROM clients";
        StringBuilder result = new StringBuilder();
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Integer clientId = rs.getInt("client_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");

                Client client = new Client(clientId, name, email, phone, address);
                result.append(client.toString());
            }

            if (result.length() > 0) {
                JOptionPane.showMessageDialog(null, result.toString());
            } else {
                JOptionPane.showMessageDialog(null, "No clients found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving clients.");
        }
    }
}
