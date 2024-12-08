package it.crm.bd.controller;

import it.crm.bd.model.dao.ConnectionFactory;
import it.crm.bd.model.domain.Role;
import it.crm.bd.view.AmministratoreView;

import java.io.IOException;
import java.sql.SQLException;

public class AmministratoreController implements Controller {
    @Override
    public void start() {
        try {
            ConnectionFactory.changeRole(Role.AMMINISTRATORE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (true){
            int choice;
            try {
                choice = AmministratoreView.showMenu();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            switch(choice){
                //todo
            }
        }
    }
}
