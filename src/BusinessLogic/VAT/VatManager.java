package BusinessLogic.VAT;

import DataAccess.Vat.VatDBAccess;
import DataAccess.Vat.VatDataAccess;
import Exceptions.Vat.GetAllVatsException;
import Exceptions.Vat.UnkownVatCodeException;
import Model.Vat.Vat;

import java.util.ArrayList;

public class VatManager
{
    private final VatDataAccess vatDataAccess;

    public VatManager()
    {
        vatDataAccess = new VatDBAccess();
    }

    public ArrayList<Vat> getAllVats() throws GetAllVatsException
    {
        return vatDataAccess.getAllVats();
    }

    public Vat getVat(String code) throws UnkownVatCodeException
    {
        return vatDataAccess.getVat(code);
    }

    public void createVat(Vat vat)
    {
        vatDataAccess.createVat(vat);
    }

    public void deleteVat(String code)
    {
        vatDataAccess.deleteVat(code);
    }

    public void updateVat(Vat vat)
    {
        vatDataAccess.updateVat(vat);
    }
    public void updateVatCode(Vat vat)
    {
        vatDataAccess.updateVatCode(vat);
    }
}
