package it.crm.bd.view;

import it.crm.bd.model.domain.Offer;
import it.crm.bd.model.domain.OffersType;
import it.crm.bd.other.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OfferView {
    public static Offer insertOffer() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Printer.println("Insert offer type(PROMOTIONAL,DISCOUNT,GIFT,OTHER):");
        OffersType type = OffersType.valueOf(reader.readLine());
        Printer.println("Insert offer description:");
        String description = reader.readLine();
        return new Offer(type, description);

    }
}
