package DataAccess.Vat;

import DataAccess.DatabaseConnexion;
import DataAccess.DataAccesUtils;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Vat.GetAllVatsException;
import Exceptions.Vat.UnkownVatCodeException;

import Model.Vat.Vat;
import Model.Vat.MakeVat;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VatDBAccess implements VatDataAccess
{
    public Vat getVat(String code) throws UnkownVatCodeException
    {
        String sql = "SELECT * FROM vat WHERE code = ?";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();
            PreparedStatement statement = databaseConnexion.prepareStatement(sql);
            statement.setString(1, code);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
            {
                return makeVat(resultSet);
            }
            else
            {
                throw new UnkownVatCodeException(code);
            }
        } catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println("Vat Error: " + e.getMessage());
            throw new UnkownVatCodeException(code);
        }
    }

    public ArrayList<Vat> getAllVats() throws GetAllVatsException
    {
        String sql = "SELECT * FROM vat";
        ArrayList<Vat> vats = new ArrayList<>();

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();
            PreparedStatement statement = databaseConnexion.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                vats.add(makeVat(resultSet));
            }
        } catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println("Vat Error : " + e.getMessage());
            throw new GetAllVatsException();
        }

        return vats;
    }

    @Override
    public void createVat(Vat vat) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void deleteVat(String code) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void updateVat(Vat vat) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void updateVatCode(Vat vat) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void updateVatPercentage(Vat vat) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static Vat makeVat(ResultSet resultSet) throws SQLException
    {
        if(!DataAccesUtils.hasColumn(resultSet, "vat.code")) return null;

        return MakeVat.getVat(
                resultSet.getString("vat.code"),
                resultSet.getFloat("vat.rate")
        );
    }
}
