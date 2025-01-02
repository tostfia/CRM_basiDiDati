package it.crm.bd.model.dao;

import it.crm.bd.model.domain.Role;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
// Classe per la connessione al database

public class ConnectionFactory {

    private static ConnectionFactory instance; // Singleton instance
    private Connection connection; // Connessione al database
    private final String connectionUrl;
    private String currentRole;
    private static final String PROPERTY_FILE = "resources/db.properties";

    // Inizializzo la connessione al database
    private ConnectionFactory() throws IOException, SQLException {
        try (InputStream input = new FileInputStream(PROPERTY_FILE)) {
            Properties properties = new Properties();
            properties.load(input);
            connectionUrl = properties.getProperty("CONNECTION_URL");
            String defaultUser = properties.getProperty("LOGIN_USER");
            String defaultPass = properties.getProperty("LOGIN_PASS");
            this.connection = DriverManager.getConnection(connectionUrl, defaultUser, defaultPass);
            this.currentRole = defaultUser;
        }
    }

    // Metodo per ottenere l'istanza della classe
    public static synchronized ConnectionFactory getInstance() throws IOException, SQLException {
        if (instance == null) {
            instance = new ConnectionFactory();
        }
        return instance;
    }

    // Metodo per ottenere la connessione al database
    public synchronized Connection getConnection() throws SQLException {
        // Recreate the connection if it is closed or null
        if (this.connection == null || this.connection.isClosed()) {
            this.connection = DriverManager.getConnection(connectionUrl,this.currentRole,getPasswordForUser(this.currentRole));
        }
        return this.connection;
    }
    private String getPasswordForUser(String user) throws SQLException {
        Properties properties = loadProperties();
        return properties.getProperty(user + "_PASS");
    }

    private Properties loadProperties() throws SQLException {
        try (InputStream input = new FileInputStream(PROPERTY_FILE)) {
            Properties properties = new Properties();
            properties.load(input);
            return properties;
        } catch (IOException e) {
            throw new SQLException("Error while reading properties file: " + e.getMessage(), e);
        }
    }


    // Metodo per cambiare il ruolo dell'utente
    public synchronized void changeRole(Role role) throws SQLException, IOException {
        if (this.connection != null && !this.connection.isClosed()) {
            this.connection.close();
        }

        try (InputStream input = new FileInputStream("resources/db.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            String user = properties.getProperty(role.name() + "_USER");
            String pass = properties.getProperty(role.name() + "_PASS");
            this.connection = DriverManager.getConnection(connectionUrl, user, pass);
            this.currentRole = user;
        }catch (IOException | SQLException e){
            throw new SQLException("Error while reading properties file: " + e.getMessage(), e);
        }
    }

    // Metodo  in caso volessimo chiudere la connessione
    public synchronized void closeConnection() throws SQLException {
        if (this.connection != null && !this.connection.isClosed()) {
            this.connection.close();
        }
    }
    // Metodo per ottenere il ruolo corrente
    public String getCurrentRole(){
        return this.currentRole;
    }
}

