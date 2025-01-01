package it.crm.bd.model.dao;

import it.crm.bd.exception.DAOException;
import it.crm.bd.model.domain.Appointment;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class InsertAppointmentProcedureDAO implements GenericProcedureDAO<Appointment> {
    @Override
    public Appointment execute(Object... params) throws DAOException {
        if(params == null || params.length == 0 || !(params[0] instanceof Appointment appointment)){
            throw new DAOException("Invalid input parameters");
        }
        try(Connection conn=ConnectionFactory.getConnection();
            CallableStatement cs=conn.prepareCall("{call insertAppointment(?,?,?,?)}")) {
            cs.setDate(1, Date.valueOf(appointment.getDate()));
            cs.setTime(2, appointment.getTime());
            cs.setString(3, appointment.getCustomer());
            cs.setString(4, appointment.getBranch());

            cs.executeUpdate();
            return appointment;
        } catch (SQLException e) {
            throw new DAOException("Error executing stored procedure 'insertAppointment': " + e.getMessage(), e);
        }

    }
}
