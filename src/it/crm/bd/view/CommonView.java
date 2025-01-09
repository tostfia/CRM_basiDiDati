package it.crm.bd.view;

import it.crm.bd.other.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;


public abstract class CommonView {
    //Metodo che ha lo scopo di leggere una stringa da tastiera
    protected static String inputString(BufferedReader reader, String prompt) throws IOException {
        Printer.print(prompt+": ");
        return reader.readLine().trim();
    }
    //Metodo che ha lo scopo di leggere una data da tastiera
    static LocalDate inputDate(BufferedReader reader, String prompt) throws IOException {
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
    //Metodo che ha lo scopo di leggere un orario da tastiera
    static Time inputTime(BufferedReader reader) throws IOException {
        Time time = null;
        while (time == null) {
            Printer.print("Time (HH:MM)" + ": ");
            String input = reader.readLine().trim();
            try {
                time = Time.valueOf(input + ":00");
            } catch (Exception e) {
                Printer.errorPrint("Invalid time format. Please use the format HH:MM.");
            }
        }
        return time;
    }
    //Metodo che ha lo scopo di leggere un intero da tastiera
    static Integer inputInt(BufferedReader reader) throws IOException {
        Integer number = null;
        while (number == null) {
            Printer.print("\nChoose an option" + ": ");
            String input = reader.readLine().trim();
            try {
                number = Integer.parseInt(input);
            } catch (Exception e) {
                Printer.errorPrint("Invalid number format. Please use an integer.");
            }
        }
        return number;
    }


}

