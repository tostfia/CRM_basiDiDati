package it.crm.bd.model.dao;

import it.crm.bd.exception.DAOException;
import it.crm.bd.model.domain.Note;

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
        Connection conn;
        try {
            // Verifica che la connessione non sia nulla (il cambio di ruolo avviene tramite la connessione per ruolo)
            conn = (Connection) params[1];
            if (conn == null || conn.isClosed()) {
                throw new DAOException("Connection is closed or null.");
            }

            // Ottieni la connessione
            try (CallableStatement cs = conn.prepareCall("{call writeNote(?,?,?)}")) {

                // Imposta i parametri per la stored procedure
                cs.setBoolean(1, note.getOutcome());
                cs.setString(2, note.getDescription());
                cs.setString(3, note.getCustomer());

                // Esegui la stored procedure
                cs.executeUpdate();

                // Restituisci l'oggetto Note
                return note;

            } catch (SQLException e) {
                // Gestione delle eccezioni
                throw new DAOException("Error executing stored procedure 'writeNote': " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            // Gestione delle eccezioni
            throw new DAOException("Error while executing stored procedure 'writeNote': " + e.getMessage(), e);
        }
    }
}
