package it.crm.bd.dao;

import it.crm.bd.exception.DAOException;

import java.sql.SQLException;

public interface GenericProcedureDAO<P> {

        P execute(Object... params) throws DAOException, SQLException;
}
