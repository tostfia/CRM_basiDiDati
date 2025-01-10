package it.crm.bd.view;

import it.crm.bd.controller.OperatoreController;
import it.crm.bd.exception.DataBaseOperationException;
import it.crm.bd.model.domain.Interaction;
import it.crm.bd.other.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Time;
import java.time.LocalDate;

public class InteractionView  extends CommonView{
    public InteractionView() {super();}
    static OperatoreController controller = new OperatoreController();
    public static Interaction insertInteraction() throws IOException, DataBaseOperationException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Printer.printBlue("\n---------------Insert Interaction---------------\n");
        //Input per l'interazione
        LocalDate date = inputDate(reader, "Date (YYYY-MM-DD)");
        Time time = inputTime(reader);
        String customer = inputString(reader, "Customer");
        //Visualizza le offerte disponibili
        Printer.print("\nWhich offer do you have proposed?");
        controller.showOffers();
        String offer=inputString(reader, "\nInsert the offer description:");
        String operator = inputString(reader, "Operator");
        //Registra l'interazione
        return new Interaction(date, time, customer, offer,operator);
    }


}
