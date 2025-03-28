package DataAccess.Vat;

import DataAccess.DatabaseConnexion;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Vat.UnkownVatCodeException;

import Model.Vat.Vat;
import Model.Vat.MakeVat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VatDBAccess implements VatDataAccess
{
    public Vat getVat(String code) throws DatabaseConnectionFailedException, UnkownVatCodeException
    {
        String sql = "SELECT * FROM vat WHERE code = ?";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance().getConnection();
            PreparedStatement statement = databaseConnexion.prepareStatement(sql);
            statement.setString(1, code);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
            {
                return MakeVat.getVat(resultSet.getString("code"), resultSet.getFloat("rate"));
            }
            else
            {
                throw new UnkownVatCodeException(code);
            }
        } catch (SQLException e)
        {
            System.err.println("Sql error: " + e.getMessage());
            throw new DatabaseConnectionFailedException();
        }
    }
}
