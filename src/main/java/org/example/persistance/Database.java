package org.example.persistance.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    public Database() {
    }

    public static Connection openConnection() {

        String url = "jdbc:mysql://localhost:3306/mmrsm";
        String user = "root";
        String password = "Rlwl2023.";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}