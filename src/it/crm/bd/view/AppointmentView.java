package it.crm.bd.view;

import it.crm.bd.model.domain.Appointment;
import it.crm.bd.other.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Time;
import java.time.LocalDate;

public class AppointmentView extends CommonView {
    public AppointmentView() {super();}
    public static Appointment scheduleAppointment() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Printer.printlnBlue("\n---------------Schedule Appointment---------------\n");
        String customer = inputString(reader, "Customer");
        String branch = inputString(reader, "Branch");
        LocalDate date = inputDate(reader, "Date (YYYY-MM-DD)");
        Time time = inputTime(reader);
        String operator = inputString(reader, "Operator");
        return new Appointment(customer, date, time, branch,operator);

    }



}
