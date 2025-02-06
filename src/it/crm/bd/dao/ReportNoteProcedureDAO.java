package it.crm.bd.dao;


import it.crm.bd.exception.DAOException;
import it.crm.bd.model.Appointment;
import it.crm.bd.model.Note;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportNoteProcedureDAO implements GenericProcedureDAO<List<Note>> {

    @Override
    public List<Note> execute(Object... params) throws DAOException {
        // Validazione parametri
        if (params == null || params.length < 2 || !(params[0] instanceof String customer) || !(params[1] instanceof Connection conn)) {
            throw new DAOException("Invalid input parameters: expected a String (customer) and a Connection object.");
        }

        if (customer.isBlank()) {
            throw new DAOException("Customer code cannot be null or blank.");
        }

        // Risultato da restituire
        List<Note> notes = new ArrayList<>();

        try {
            if (conn.isClosed()) {
                throw new DAOException("Database connection is closed.");
            }

            // Preparazione della chiamata alla procedura
            try (CallableStatement cs = conn.prepareCall("{call reportNotes(?)}")) {

                // Imposta il parametro della procedura
                cs.setString(1, customer);

                // Esecuzione e gestione del risultato
                try (ResultSet rs = cs.executeQuery()) {
                    while (rs.next()) {
                        // Mappatura del risultato sul modello `Note`
                        Note note = new Note();
                        note.setOutcome(rs.getBoolean("nota_risultato"));
                        note.setDescription(rs.getString("nota_dettagli"));
                        note.setOperator(rs.getString("operatore_interazione"));
                        note.setDate(rs.getDate("data_interazione").toLocalDate());
                        note.setTime(rs.getTime("ora_interazione"));
                        note.setOffer(rs.getString("offerta_scelta"));
                        note.setCustomerName(rs.getString("cliente_nome"));
                        note.setCustomerSurname(rs.getString("cliente_cognome"));
                        if(rs.getDate("appuntamento_data") != null && rs.getTime("appuntamento_ora") != null && rs.getString("appuntamento_sede") != null){
                            note.setAppointment(new Appointment( rs.getString("appuntamento_sede"), rs.getDate("appuntamento_data").toLocalDate(), rs.getTime("appuntamento_ora")));
                        }else{
                            note.setAppointment(null);
                        }

                        // Aggiunta alla lista
                        notes.add(note);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error executing stored procedure 'reportNotes': " + e.getMessage(), e);
        }

        return notes;
    }
}
