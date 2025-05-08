package DataAccess.Vat;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Vat.GetAllVatsException;
import Exceptions.Vat.UnkownVatCodeException;
import Model.Vat.Vat;

import java.util.ArrayList;

public interface VatDataAccess {
    Vat getVat(String code) throws UnkownVatCodeException;

    ArrayList<Vat> getAllVats() throws GetAllVatsException;

    void createVat(Vat vat);

    void deleteVat(String code);

    void updateVat(Vat vat);

    void updateVatCode(Vat vat);

    void updateVatPercentage(Vat vat);
}
