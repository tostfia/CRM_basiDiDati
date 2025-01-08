package it.crm.bd.model.dao;


import it.crm.bd.exception.DAOException;
import it.crm.bd.model.domain.Contact;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class UpdateContactsProcedureDAO implements GenericProcedureDAO<Contact> {
    @Override
    public Contact execute(Object... params) throws DAOException {
        if(params == null || params.length == 0 || !(params[0] instanceof Contact contact)) {
            throw new DAOException("Invalid input parameters: A Contact object is required.");
        }
        Connection conn;
        try{
            conn=(Connection) params[1];
            if(conn == null || conn.isClosed()){
                throw new DAOException("Connection is closed or null.");
            }
            try(CallableStatement cs = conn.prepareCall("{call updateContacts(?,?,?)}")){
                cs.setString(1, contact.getValue());
                cs.setString(2, contact.getType());
                cs.setString(3, contact.getFiscalCode());


                cs.executeUpdate();
                return contact;
            } catch (SQLException e) {
                throw new DAOException("Error executing stored procedure 'updateContacts': " + e.getMessage(), e);
            }
        }catch (SQLException e){
            throw new DAOException("Error while executing stored procedure 'updateContacts': " + e.getMessage(), e);
        }
    }
}

