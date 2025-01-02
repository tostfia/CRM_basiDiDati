package it.crm.bd.model.dao;

import it.crm.bd.exception.DAOException;
import it.crm.bd.model.domain.Note;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class WriteNoteProcedureDAO implements GenericProcedureDAO<Note> {

    @Override
    public Note execute(Object... params) throws DAOException {
        // Validazione degli input
        if (params == null || params.length == 0 || !(params[0] instanceof Note note)) {
            throw new DAOException("Invalid input: A valid Note object is required.");
        }

        try {
            // Ottieni l'istanza Singleton di ConnectionFactory
            ConnectionFactory factory = ConnectionFactory.getInstance();

            // Ottieni la connessione
            try (Connection conn = factory.getConnection();
                 CallableStatement cs = conn.prepareCall("{call writeNote(?,?,?)}")) {

                // Imposta i parametri per la stored procedure
                cs.setBoolean(1, note.getOutcome());
                cs.setString(2, note.getDescription());
                cs.setString(3, note.getCustomer());

                // Esegui la stored procedure
                cs.executeUpdate();

                // Restituisci l'oggetto Note
                return note;
            }
        } catch (SQLException | IOException e) {
            // Gestione delle eccezioni
            throw new DAOException("Error executing stored procedure 'writeNote': " + e.getMessage(), e);
        }
    }
}
