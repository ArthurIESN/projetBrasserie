package DataAccess.Vat;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Vat.GetAllVatsException;
import Exceptions.Vat.UnkownVatCodeException;
import Model.Vat.Vat;

import java.util.ArrayList;

public interface VatDataAccess
{
    Vat getVat(String code) throws DatabaseConnectionFailedException, UnkownVatCodeException;

    ArrayList<Vat> getAllVats() throws GetAllVatsException;
}
