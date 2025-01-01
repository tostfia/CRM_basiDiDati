package it.crm.bd.model.dao;

import it.crm.bd.exception.DAOException;
import it.crm.bd.model.domain.Note;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class WriteNoteProcedureDAO implements GenericProcedureDAO<Note> {
    @Override
    public Note execute(Object... params) throws DAOException {
        if(params == null || params.length == 0 || !(params[0] instanceof Note note)){
            throw new DAOException("Invalid input parameters");
        }
        try(Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call writeNote(?,?)}")) {
            cs.setBoolean(1, note.getOutcome());
            cs.setString(2, note.getDescription());
            cs.executeUpdate();
            return note;
        } catch (SQLException e) {
            throw new DAOException("Error executing stored procedure 'writeNote': " + e.getMessage(), e);
        }
    }
}
