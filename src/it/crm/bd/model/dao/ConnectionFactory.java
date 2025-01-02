package it.crm.bd.model.dao;
import it.crm.bd.model.domain.Role;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private ConnectionFactory() {}

    public static Connection getLoginConnection() throws SQLException, IOException {
        return getConnection("LOGIN");
    }

    public static Connection getConnection(Role role) throws SQLException, IOException {
        return getConnection(role.name());
    }

    private static Connection getConnection(String role) throws SQLException, IOException {
        try (InputStream input = new FileInputStream("resources/db.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            String connectionUrl = properties.getProperty("CONNECTION_URL");
            String user = properties.getProperty(role + "_USER");
            String pass = properties.getProperty(role + "_PASS");

            if (user == null || pass == null) {
                throw new IllegalArgumentException("Invalid role: " + role);
            }

            return DriverManager.getConnection(connectionUrl, user, pass);
        }
    }
}

