package it.crm.bd.model.dao;

import it.crm.bd.exception.DAOException;
import it.crm.bd.model.domain.Offer;
import it.crm.bd.model.domain.OffersType;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfferProcedureDAO implements GenericProcedureDAO<List<Offer>>{
    @Override
    public List<Offer> execute(Object... params) throws DAOException, SQLException {
        if(params == null || params.length == 0 || !(params[0] instanceof Connection conn)){
            throw new DAOException("Invalid input parameters: A Connection object is required.");
        }

        if (conn.isClosed()) {
            throw new DAOException("Connection is closed or null.");
        }
        List<Offer> offers = new ArrayList<>();
        try (CallableStatement cs = conn.prepareCall("{call getOffers()}")) {
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Offer o = new Offer();
                    o.setType(OffersType.fromInt(rs.getInt("Tipo")));
                    o.setDescription(rs.getString("Descrizione"));
                    offers.add(o);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error while executing stored procedure 'getOffers': " + e.getMessage(), e);
        }

        return offers;
    }
}
