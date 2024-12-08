package it.crm.bd.controller;

import it.crm.bd.exception.DAOException;
import it.crm.bd.model.dao.LoginProcedureDAO;
import it.crm.bd.model.domain.Credentials;
import it.crm.bd.view.LoginView;

import java.io.IOException;

public class LoginController implements Controller {

    Credentials cred=null;
    @Override
    public void start(){
        try{
            cred=LoginView.authenticate();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        try{
            cred=new LoginProcedureDAO().execute(cred.getUsername(),cred.getPassword());
        }catch(DAOException e){
            throw new RuntimeException(e);
        }
    }
    public Credentials getCred(){
        return cred;
    }


}
