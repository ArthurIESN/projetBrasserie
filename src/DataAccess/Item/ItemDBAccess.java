package DataAccess.Item;

import DataAccess.DataAccesUtils;
import DataAccess.DatabaseConnexion;
import DataAccess.Packaging.PackagingDBAccess;
import DataAccess.Vat.VatDBAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Item.GetAllItemsException;
import Exceptions.Search.GetMinMaxItemQuantityAndPriceException;
import Exceptions.Search.SearchItemException;
import Model.Item.Item;

import Model.Packaging.Packaging;
import Model.Vat.Vat;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ItemDBAccess implements ItemDataAccess
{
    public ItemDBAccess(){}


    public ArrayList<Item> getAllItems() throws GetAllItemsException
    {
        ArrayList<Item> items = new ArrayList<>();

        String query = "SELECT *,packaging.id AS id_packaging,packaging.label AS packaging_label, " +
                "packaging.quantity AS packaging_quantity,vat.code AS vat_code,vat.rate AS vat_rate " +
                " FROM item INNER JOIN packaging ON item.id_packaging = packaging.id " +
                "INNER JOIN vat ON item.code_vat = vat.code";

        try
        {
            Connection dataBaseConnection = DatabaseConnexion.getInstance();

            PreparedStatement statement = dataBaseConnection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                items.add(makeItem(resultSet));
            }
        }catch (SQLException | DatabaseConnectionFailedException  e)
        {
            System.err.println(e.getMessage());
            throw new GetAllItemsException();
        }

        return items;

    }

    @Override
    public int[] getMinMaxItemQuantityAndPrice(Vat vat) throws GetMinMaxItemQuantityAndPriceException
    {
        String query = "SELECT " +
                "MIN(current_quantity)  AS min_item_quantity, " +
                "MAX(current_quantity)  AS max_item_quantity, " +
                "MIN(price)             AS min_item_price, " +
                "MAX(price)             AS max_item_price " +
                "FROM item " +
                "WHERE code_vat = ?";


        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();

            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            statement.setString(1, vat.getCode());

            ResultSet resultSet = statement.executeQuery();

            int[] minMaxItem = new int[4];
            if (resultSet.next())
            {
                minMaxItem[0] = (resultSet.getInt("min_item_quantity"));
                minMaxItem[1] = (resultSet.getInt("max_item_quantity"));
                minMaxItem[2] = (resultSet.getInt("min_item_price"));
                minMaxItem[3] = (resultSet.getInt("max_item_price"));
            }
            else
            {
                throw new GetMinMaxItemQuantityAndPriceException("No items found for the given VAT code.");
            }

            return minMaxItem;

        } catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println("Sql error: " + e.getMessage());
            throw new GetMinMaxItemQuantityAndPriceException();
        }
    }

    @Override
    public ArrayList<Item> searchItem(String tvaCode, int minItem, int maxItem, int minPrice, int maxPrice) throws SearchItemException
    {
        String query = "SELECT * " +
                "FROM item " +
                "JOIN vat ON item.code_vat = vat.code " +
                "JOIN packaging ON item.id_packaging = packaging.id " +
                "WHERE vat.code = ? " +
                "AND item.current_quantity BETWEEN ? AND ? " +
                "AND item.price BETWEEN ? AND ? ";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();;
            PreparedStatement statement = databaseConnexion.prepareStatement(query);

            statement.setString(1, tvaCode);
            statement.setInt(2, minItem);
            statement.setInt(3, maxItem);
            statement.setInt(4, minPrice);
            statement.setInt(5, maxPrice);

            ResultSet resultSet = statement.executeQuery();

            ArrayList<Item> items = new ArrayList<>();
            while (resultSet.next())
            {
                items.add(makeItem(resultSet));
            }

            return items;

        } catch (SQLException | DatabaseConnectionFailedException  e)
        {
            System.err.println("Sql error: " + e.getMessage());
            throw new SearchItemException();
        }

    }

    public static Item makeItem(ResultSet resultSet) throws SQLException
    {
        if(!DataAccesUtils.hasColumn(resultSet, "item.id")) return null;

        return new Item(
                resultSet.getInt("item.id"),
                resultSet.getString("item.label"),
                resultSet.getFloat("item.price"),
                resultSet.getInt("item.restock_quantity"),
                resultSet.getInt("item.current_quantity"),
                resultSet.getInt("item.empty_returnable_bottle_quantity"),
                resultSet.getInt("item.empty_returnable_bottle_price"),
                resultSet.getDate("item.forecast_date"),
                resultSet.getInt("item.forecast_quantity"),
                resultSet.getInt("item.min_quantity"),
                PackagingDBAccess.makePackaging(resultSet),
                VatDBAccess.makeVat(resultSet)
        );
    }


}
