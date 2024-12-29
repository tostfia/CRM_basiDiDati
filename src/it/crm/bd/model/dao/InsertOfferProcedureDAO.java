package it.crm.bd.model.dao;

import it.crm.bd.exception.DAOException;
import it.crm.bd.model.domain.Offer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class InsertOfferProcedureDAO implements GenericProcedureDAO<Offer>{
    @Override
    public Offer execute(Object... params) throws DAOException {
        //Validazione parametri
        if(params==null || params.length==0||!(params[0] instanceof Offer offer)){
            throw new DAOException("Invalid input");
        }
        //Chiamata alla procedura e gestione della connessione
        try(Connection conn=ConnectionFactory.getConnection();
            CallableStatement cs=conn.prepareCall("{call insert_offer(?,?)}")){
            //Settaggio dei parametri
            cs.setString(1,offer.getType());
            cs.setString(2,offer.getDescription());
            cs.executeUpdate();
            //Restituisco offer
            return offer;
        }catch (SQLException e){
            throw new DAOException("Error executing stored procedure 'insertOffer': "+e.getMessage(),e);
        }

    }

}
