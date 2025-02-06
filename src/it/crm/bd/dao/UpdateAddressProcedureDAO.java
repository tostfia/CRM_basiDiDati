package it.crm.bd.dao;

import it.crm.bd.exception.DAOException;
import it.crm.bd.model.Customer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class UpdateAddressProcedureDAO implements GenericProcedureDAO<Customer> {
    @Override
    public Customer execute(Object... params) throws DAOException {
        if(params == null || params.length == 0 || !(params[0] instanceof Customer customer)) {
            throw new DAOException("Invalid input parameters: A Customer object is required.");
        }
        Connection conn;
        try{
            conn=(Connection) params[1];
            if(conn == null || conn.isClosed()){
                throw new DAOException("Connection is closed or null.");
            }
            try(CallableStatement cs = conn.prepareCall("{call updateIndirizzi(?,?,?,?)}")){
                cs.setString(1, customer.getAddress());
                cs.setString(2, customer.getCap());
                cs.setString(3, customer.getCity());
                cs.setString(4, customer.getFiscalCode());

                cs.executeUpdate();
                return customer;
            } catch (SQLException e) {
                throw new DAOException("Error executing stored procedure 'updateAddress': " + e.getMessage(), e);
            }
        }catch (SQLException e){
            throw new DAOException("Error while executing stored procedure 'updateAddress': " + e.getMessage(), e);
        }
    }
}
