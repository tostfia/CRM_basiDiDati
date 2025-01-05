package it.crm.bd.model.dao;

import it.crm.bd.exception.DAOException;

import it.crm.bd.model.domain.OffersType;
import it.crm.bd.model.domain.ReportCustomer;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReportCustomerProcedureDAO implements GenericProcedureDAO<List<ReportCustomer>> {

    @Override
    public List<ReportCustomer> execute(Object... params) throws DAOException {
        // Validazione parametri
        if (params == null || params.length < 3 || !(params[0] instanceof LocalDate start) ||
                !(params[1] instanceof LocalDate end) || !(params[2] instanceof Connection conn)) {
            throw new DAOException("Invalid input parameters: expected LocalDate start, LocalDate end, and a valid Connection object.");
        }

        List<ReportCustomer> reportCustomers = new ArrayList<>();

        try {
            if (conn.isClosed()) {
                throw new DAOException("Database connection is closed. Please check the connection.");
            }

            try (CallableStatement cs = conn.prepareCall("{call reportCustomers(?,?)}")) {
                cs.setDate(1, Date.valueOf(start));
                cs.setDate(2, Date.valueOf(end));

                // Esecuzione e gestione del risultato
                try (ResultSet rs = cs.executeQuery()) {
                    while (rs.next()) {
                        ReportCustomer reportCustomer = new ReportCustomer();
                        reportCustomer.setName(rs.getString("cliente_nome") );
                        reportCustomer.setSurname(rs.getString("cliente_cognome"));
                        reportCustomer.setFiscalCode(rs.getString("cliente_codicefiscale"));
                        reportCustomer.setInteractions(rs.getInt("interazioni_cliente"));
                        reportCustomer.setAcceptedOffers(rs.getInt("numero_offerte_accettate"));

                        // Gestione del tipo di offerta
                        int type = rs.getInt("tipi_offerte_accettate");
                        if (rs.wasNull()) {
                            reportCustomer.setAcceptedOffersType("Unknown");
                        } else {
                            reportCustomer.setAcceptedOffersType(OffersType.fromInt(type).toString());
                        }

                        reportCustomers.add(reportCustomer);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error while executing the procedure: " + e.getMessage(), e);
        }

        return reportCustomers;
    }
}

