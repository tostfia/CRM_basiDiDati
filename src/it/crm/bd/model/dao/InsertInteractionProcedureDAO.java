package it.crm.bd.model.dao;

import it.crm.bd.controller.LoginController;
import it.crm.bd.exception.DAOException;
import it.crm.bd.model.domain.Interaction;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class InsertInteractionProcedureDAO implements GenericProcedureDAO<Interaction> {
    private LoginController loginController;

    @Override
    public Interaction execute(Object... params) throws DAOException {
        if (params == null || params.length == 0 || !(params[0] instanceof Interaction interaction)) {
            throw new DAOException("Invalid input parameters");
        }
        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement cs = conn.prepareCall("{call insertInteraction(?,?,?,?,?,?,?,?)}")) {
            // Imposta i parametri per la stored procedure
            cs.setDate(1, Date.valueOf(interaction.getDate()));
            cs.setTime(2, interaction.getTime());
            cs.setString(3, interaction.getCustomer());
            cs.setString(4, interaction.getOffer());
            cs.setString(5, loginController.getCred().getUsername());
            cs.executeUpdate();
            return interaction;
        } catch (SQLException e) {
            throw new DAOException("Error executing stored procedure 'insertInteraction': " + e.getMessage(), e);
        }
    }
}



