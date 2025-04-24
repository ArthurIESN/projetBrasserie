package Controller.VAT;

import BusinessLogic.VAT.VatManager;
import Exceptions.Vat.GetAllVatsException;
import Model.Vat.Vat;

import java.util.ArrayList;

public class VATController {
    private static final VatManager vatManager = new VatManager();

    // VAT
    public static ArrayList<Vat> getAllVats() throws GetAllVatsException
    {
        return vatManager.getAllVats();
    }
}
