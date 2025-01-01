package it.crm.bd.view;

import it.crm.bd.model.domain.Interaction;
import it.crm.bd.model.domain.OffersType;
import it.crm.bd.other.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Time;
import java.time.LocalDate;

public class InteractionView {
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
    private static String inputString(BufferedReader reader, String prompt) throws IOException {
        Printer.print(prompt + ": ");
        return reader.readLine().trim();
    }
    private static LocalDate inputDate(BufferedReader reader, String prompt) throws IOException {
        LocalDate date=null;
        while (date==null) {
            Printer.print(prompt + ": ");
            String input = reader.readLine().trim();
            try {
                date=LocalDate.parse(input);
            } catch (Exception e) {
                Printer.errorPrint("Invalid date format. Please use the format YYYY-MM-DD.");
            }
        }
        return date;
    }
    private static Time inputTime(BufferedReader reader) throws IOException {
        Time time=null;
        while (time==null) {
            Printer.print("Time (HH:MM)" + ": ");
            String input = reader.readLine().trim();
            try {
                time=Time.valueOf(input + ":00");
            } catch (Exception e) {
                Printer.errorPrint("Invalid time format. Please use the format HH:MM.");
            }
        }
        return time;
    }
}
