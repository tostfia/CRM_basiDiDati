package it.crm.bd.view;

import it.crm.bd.model.domain.Interaction;
import it.crm.bd.model.domain.OffersType;
import it.crm.bd.other.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Time;
import java.time.LocalDate;

public class InteractionView  extends CommonView{
    public InteractionView() {super();}
    public static Interaction insertInteraction() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Printer.printBlue("\n---------------Insert Interaction---------------\n");
        LocalDate date = inputDate(reader, "Date (YYYY-MM-DD)");
        Time time = inputTime(reader);
        String customer = inputString(reader, "Customer");
        OffersType offer = OffersType.valueOf(inputString(reader, "Offer (PROMOTIONAL,DISCOUNT,GIFT,OTHER)"));
        String operator = inputString(reader, "Operator");
        //Return un nuovo oggetto interazione
        return new Interaction(date, time, customer,offer,operator);
    }


}
