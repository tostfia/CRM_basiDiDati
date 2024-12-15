package it.crm.bd.model.dao;

import it.crm.bd.exception.DAOException;

import java.sql.CallableStatement;
import java.sql.Connection;

public class AdminRegistrationDAO implements GenericProcedureDAO<Boolean> {
    @Override
    public Boolean execute(Object... params) throws DAOException {
        return null;
    }
        /*String username= (String) params[0];
        String password= (String) params[1];
        String message;
        try(Connection con= ConnectionFactory. getConnection();
            CallableStatement cs=con.prepareCall("{call register_admin(?,?,?)}")){
            cs.setString(1,username);
            cs.setString(2,password);
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.execute();
            message=cs.getString(3);
        )catch (SQLException e){
            throw new DAOException("Registration error: "+e.getMessage());
        }
        return message;
    }*/

}