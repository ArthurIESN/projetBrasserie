package Controller;

import DataAccess.Vat.VatDBAccess;
import DataAccess.Vat.VatDataAccess;
import Exceptions.Vat.GetAllVatsException;
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
}
