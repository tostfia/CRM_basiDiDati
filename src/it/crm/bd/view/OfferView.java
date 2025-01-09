package it.crm.bd.view;

import it.crm.bd.model.domain.Offer;
import it.crm.bd.model.domain.OffersType;
import it.crm.bd.other.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OfferView extends CommonView {
    public OfferView() {super();}
    public static Offer insertOffer() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Printer.printBlue("\n---------------Insert Offer---------------\n");
        OffersType type = OffersType.valueOf(inputString(reader, "Offer (PROMOTIONAL,DISCOUNT,GIFT,OTHER)"));
        String description = inputString(reader, "Description");
        return new Offer(type, description);

    }
}
