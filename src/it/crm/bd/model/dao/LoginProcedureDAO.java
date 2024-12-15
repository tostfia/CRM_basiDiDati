package it.crm.bd.model.dao;

import it.crm.bd.exception.DAOException;
import it.crm.bd.model.domain.Credentials;
import it.crm.bd.model.domain.Role;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class LoginProcedureDAO implements GenericProcedureDAO<Credentials> {
    @Override
    public Credentials execute(Object... params) throws DAOException {
        String username = (String) params[0];
        String password = (String) params[1];
        int role;
        //Validazione input
        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
            throw new DAOException("Invalid input");
        }
        try{
            Connection conn=ConnectionFactory.getConnection();
            CallableStatement cs=conn.prepareCall("{call login(?,?,?)}");
            //Settaggio dei parametri
            cs.setString(1,username);
            cs.setString(2,password);
            cs.registerOutParameter(3, Types.INTEGER);
            //Esecuzione della procedura
            cs.execute();
            role=cs.getInt(3);
            if(cs.wasNull()){
                role=0;//Login fallito
            }
        }catch (SQLException e){
            throw new DAOException("Login error: "+e.getMessage());
        }
        //Restituisco l'oggetto credentials con il ruolo determinato
        return new Credentials(username,password, Role.fromInt(role));
    }
}
