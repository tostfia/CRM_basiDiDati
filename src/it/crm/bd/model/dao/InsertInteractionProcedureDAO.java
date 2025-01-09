package it.crm.bd.model.dao;

import it.crm.bd.exception.DAOException;
import it.crm.bd.model.domain.Interaction;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;


public class InsertInteractionProcedureDAO implements GenericProcedureDAO<Interaction> {

    @Override
    public Interaction execute(Object... params) throws DAOException {
        // Validazione parametri
        if (params == null || params.length == 0 || !(params[0] instanceof Interaction interaction) || !(params[1] instanceof Connection conn)) {
            throw new DAOException("Invalid input parameters: An Interaction object is required.");
        }

        try {
            // Verifica che la connessione non sia nulla (il cambio di ruolo avviene tramite la connessione per ruolo)

            if (conn.isClosed()) {
                throw new DAOException("Connection is closed or null.");
            }
            try (CallableStatement cs = conn.prepareCall("{call insertInteraction(?,?,?,?)}")) {
                // Imposta i parametri per la stored procedure
                cs.setDate(1, Date.valueOf(interaction.getDate()));
                cs.setTime(2, interaction.getTime());
                cs.setString(3, interaction.getCustomer());
                cs.setInt(4, interaction.getOffer().getId());

                // Esegue la stored procedure
                cs.executeUpdate();

                // Restituisce l'oggetto Interaction
                return interaction;

            } catch (SQLException e) {
                // Gestione eccezioni
                throw new DAOException("Error executing stored procedure 'insertInteraction': " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            // Gestione eccezioni
            throw new DAOException("Error while executing stored procedure 'insertInteraction': " + e.getMessage(), e);
        }
    }
}




