package DataAccess.Search.SearchItem;

import DataAccess.DatabaseConnexion;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Search.SearchItemException;
import Model.Item.Item;
import Model.Packaging.Packaging;

import Exceptions.Search.GetMinMaxItemQuantityAndPriceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchItemDBAccess implements SearchItemDataAccess
{
    private final Map<Integer, Packaging> packagingCache = new HashMap<>();

    public SearchItemDBAccess()
    {
    }

    public int[] getMinMaxItemQuantityAndPrice() throws DatabaseConnectionFailedException, GetMinMaxItemQuantityAndPriceException
    {

        String query = "SELECT " +
                "MIN(current_quantity)  AS min_item_quantity, " +
                "MAX(current_quantity)  AS max_item_quantity, " +
                "MIN(price)             AS min_item_price, " +
                "MAX(price)             AS max_item_price " +
                "FROM item";


        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance().getConnection();

            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            int[] minMaxItem = new int[4];
            if (resultSet.next())
            {
                minMaxItem[0] = (resultSet.getInt("min_item_quantity"));
                minMaxItem[1] = (resultSet.getInt("max_item_quantity"));
                minMaxItem[2] = (resultSet.getInt("min_item_price"));
                minMaxItem[3] = (resultSet.getInt("max_item_price"));
            }

            return minMaxItem;

        } catch (SQLException e)
        {
            System.err.println("Sql error: " + e.getMessage());
            throw new GetMinMaxItemQuantityAndPriceException();
        }
    }

    public ArrayList<Item> searchItem(String tvaCode, int minItem, int maxItem, int minPrice, int maxPrice)  throws DatabaseConnectionFailedException, SearchItemException
    {
        String query = "SELECT *, packaging.label AS packaging_label " +
                "FROM item " +
                "JOIN vat ON item.code_vat = vat.code " +
                "JOIN packaging ON item.id_packaging = packaging.id " +
                "WHERE vat.code = ? " +
                "AND item.current_quantity BETWEEN ? AND ? " +
                "AND item.price BETWEEN ? AND ? ";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance().getConnection();;

            PreparedStatement statement = databaseConnexion.prepareStatement(query);

            statement.setString(1, tvaCode);
            statement.setInt(2, minItem);
            statement.setInt(3, maxItem);
            statement.setInt(4, minPrice);
            statement.setInt(5, maxPrice);

            statement.executeQuery();

            ArrayList<Item> items = new ArrayList<>();
            while (statement.getResultSet().next())
            {
                Packaging packaging;

                if(packagingCache.containsKey(statement.getResultSet().getInt("id_packaging")))
                {
                    packaging = packagingCache.get(statement.getResultSet().getInt("id_packaging"));
                }
                else
                {
                    packaging = new Packaging(
                            statement.getResultSet().getInt("id_packaging"),
                            statement.getResultSet().getString("packaging_label"),
                            statement.getResultSet().getInt("quantity")
                    );
                    packagingCache.put(statement.getResultSet().getInt("id_packaging"), packaging);
                }

                Item item = new Item(
                        statement.getResultSet().getInt("id"),
                        statement.getResultSet().getString("label"),
                        statement.getResultSet().getFloat("price"),
                        statement.getResultSet().getInt("restock_quantity"),
                        statement.getResultSet().getInt("current_quantity"),
                        statement.getResultSet().getInt("empty_returnable_bottle_quantity"),
                        statement.getResultSet().getInt("empty_returnable_bottle_price"),
                        statement.getResultSet().getDate("forecast_date"),
                        statement.getResultSet().getInt("forecast_quantity"),
                        statement.getResultSet().getInt("min_quantity"),
                        packaging,
                        null);

                items.add(item);
            }

            return items;

        } catch (SQLException e)
        {
            System.err.println("Sql error: " + e.getMessage());
            throw new SearchItemException();
        }

    }
}
