package it.crm.bd.controller;

import it.crm.bd.model.dao.ConnectionFactory;
import it.crm.bd.model.domain.Role;
import it.crm.bd.view.SegreteriaView;

import java.io.IOException;
import java.sql.SQLException;

public class SegreteriaController implements Controller {

    @Override
    public void start() {
        try {
            ConnectionFactory.changeRole(Role.SEGRETERIA);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while(true){
            int choice;
            try {
                choice = SegreteriaView.showMenu();
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
            switch(choice) {
                //todo
            }
        }
    }
}
