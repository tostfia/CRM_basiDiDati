package it.crm.bd.model.dao;

import it.crm.bd.exception.DAOException;
import it.crm.bd.model.domain.Offer;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class InsertOfferProcedureDAO implements GenericProcedureDAO<Offer> {

    @Override
    public Offer execute(Object... params) throws DAOException {
        // Validazione parametri
        if (params == null || params.length == 0 || !(params[0] instanceof Offer offer)) {
            throw new DAOException("Invalid input: An Offer object is required.");
        }
        Connection conn = null;
        try {
            // Verifica che la connessione non sia nulla (il cambio di ruolo avviene tramite la connessione per ruolo)
            conn = (Connection) params[1];
            if (conn == null || conn.isClosed()) {
                throw new DAOException("Connection is closed or null.");
            }
            try (CallableStatement cs = conn.prepareCall("{call insert_offer(?,?)}")) {

                // Imposta i parametri della stored procedure
                cs.setInt(1, offer.getType().getId());
                cs.setString(2, offer.getDescription());

                // Esegue la stored procedure
                cs.executeUpdate();

                // Restituisce l'oggetto Offer
                return offer;

            } catch (SQLException e) {
                // Gestione eccezioni
                throw new DAOException("Error executing stored procedure 'insertOffer': " + e.getMessage(), e);
            }
        }catch (SQLException e) {
            // Gestione eccezioni
            throw new DAOException("Error while executing stored procedure 'insertOffer': " + e.getMessage(), e);
        }
    }
}

