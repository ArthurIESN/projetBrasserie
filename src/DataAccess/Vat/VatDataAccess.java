package DataAccess.Vat;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.DataAccess.Vat.UnkownVatCodeException;
import Model.Vat.Vat;

public interface VatDataAccess
{
    Vat getVat(String code) throws DatabaseConnectionFailedException, UnkownVatCodeException;
}
