package it.crm.bd.dao;

import it.crm.bd.exception.DAOException;
import it.crm.bd.model.Appointment;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentProcedureDAO implements GenericProcedureDAO<List<Appointment>>{
    @Override
    public List<Appointment> execute(Object... params) throws SQLException, DAOException {
        if(params.length!=1 ||!(params[0] instanceof Connection conn)){
            throw new IllegalArgumentException("Invalid number of parameters.");
        }
        if(conn.isClosed()){
            throw new IllegalArgumentException("Connection is closed.");
        }
        List<Appointment> appointments= new ArrayList<>();
        try(CallableStatement cs=conn.prepareCall("{call getAppointment()}")){
            try(ResultSet rs=cs.executeQuery()){
                while(rs.next()){
                    Appointment a= new Appointment();
                    a.setCustomer(rs.getString("Cliente"));
                    a.setBranch(rs.getString("Sede"));
                    a.setDate(rs.getDate("Data").toLocalDate());
                    a.setTime(rs.getTime("Ora"));
                    a.setOperator(rs.getString("Operatore"));
                    appointments.add(a);
                }
            }
        }catch (SQLException e){
            throw new DAOException("Error while executing stored procedure 'getAppointment': "+e.getMessage(),e);
        }
        return appointments;
    }
}
