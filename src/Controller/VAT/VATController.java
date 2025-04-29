package Controller.VAT;

import BusinessLogic.VAT.VatManager;
import Exceptions.Vat.GetAllVatsException;
import Exceptions.Vat.UnkownVatCodeException;
import Model.Vat.Vat;

import java.util.ArrayList;

public class VATController {
    private static final VatManager vatManager = new VatManager();

    // VAT
    public static ArrayList<Vat> getAllVats() throws GetAllVatsException
    {
        return vatManager.getAllVats();
    }

    public static Vat getVat(String code) throws UnkownVatCodeException
    {
        return vatManager.getVat(code);
    }

    public static void createVat(Vat vat)
    {
        vatManager.createVat(vat);
    }

    public static void deleteVat(String code)
    {
        vatManager.deleteVat(code);
    }

    public static void updateVat(Vat vat)
    {
        vatManager.updateVat(vat);
    }

    public static void updateVatCode(Vat vat)
    {
        vatManager.updateVatCode(vat);
    }
}
