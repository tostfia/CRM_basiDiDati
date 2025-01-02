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

        try {
            // Ottieni l'istanza Singleton di ConnectionFactory
            ConnectionFactory factory = ConnectionFactory.getInstance();

            // Ottieni la connessione dal Singleton
            try (Connection conn = factory.getConnection();
                 CallableStatement cs = conn.prepareCall("{call insert_offer(?,?)}")) {

                // Imposta i parametri della stored procedure
                cs.setInt(1, offer.getType().getId());
                cs.setString(2, offer.getDescription());

                // Esegue la stored procedure
                cs.executeUpdate();

                // Restituisce l'oggetto Offer
                return offer;
            }
        } catch (IOException | SQLException e) {
            // Gestione eccezioni
            throw new DAOException("Error executing stored procedure 'insertOffer': " + e.getMessage(), e);
        }
    }
}

