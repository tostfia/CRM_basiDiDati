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
    private static Connection connection;

    private ConnectionFactory() {}
    static{
        try (InputStream input = new FileInputStream("resources/db.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            String connection_url = properties.getProperty("CONNECTION_URL");
            String user = properties.getProperty("LOGIN_USER");
            String pass = properties.getProperty("LOGIN_PASS");
            connection = DriverManager.getConnection(connection_url, user, pass);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() throws SQLException {
        return connection;
    }

    public static void changeRole(Role role) throws SQLException {
        connection.close();
        try (InputStream input = new FileInputStream("resources/db.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            String connection_url = properties.getProperty("CONNECTION_URL");
            String user = properties.getProperty(role.name() + "_USER");
            String pass = properties.getProperty(role.name() + "_PASS");
            connection = DriverManager.getConnection(connection_url, user, pass);
            System.out.println("Connection changed to role: " + role);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
