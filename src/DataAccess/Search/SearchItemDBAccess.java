package DataAccess.Search;

import DataAccess.DatabaseConnexion;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.DataAccess.Search.SearchItemException;
import Model.Item.Item;
import Model.Packaging;
import Model.Vat;

import Exceptions.DataAccess.Search.GetMinMaxItemQuantityAndPriceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchItemDBAccess
{
    private Map<String, Vat> vatCache = new HashMap<>();
    private Map<Integer, Packaging> packagingCache = new HashMap<>();

    public SearchItemDBAccess()
    {
    }

    public int[] getMinMaxItemQuantityAndPrice() throws DatabaseConnectionFailedException, GetMinMaxItemQuantityAndPriceException
    {

        String sql = "SELECT " +
                "MIN(current_quantity) AS min_item_quantity, " +
                "MAX(current_quantity) AS max_item_quantity, " +
                "MIN(price) AS min_item_price, " +
                "MAX(price) AS max_item_price " +
                "FROM item";


        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();

            PreparedStatement statement = databaseConnexion.prepareStatement(sql);
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
        String sql = "SELECT *, packaging.label AS packaing_label " +
                "FROM item " +
                "JOIN vat ON item.code_vat = vat.code " +
                "JOIN packaging ON item.id_packaging = packaging.id " +
                "WHERE vat.code = ? " +
                "AND item.current_quantity BETWEEN ? AND ? " +
                "AND item.price BETWEEN ? AND ? ";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();

            PreparedStatement statement = databaseConnexion.prepareStatement(sql);

            statement.setString(1, tvaCode);
            statement.setInt(2, minItem);
            statement.setInt(3, maxItem);
            statement.setInt(4, minPrice);
            statement.setInt(5, maxPrice);

            statement.executeQuery();

            ArrayList<Item> items = new ArrayList<>();
            while (statement.getResultSet().next())
            {
                Vat vat;

                if(vatCache.containsKey(tvaCode))
                {
                    vat = vatCache.get(tvaCode);
                }
                else
                {
                    vat = new Vat(
                            tvaCode,
                            statement.getResultSet().getFloat("rate")
                    );
                    vatCache.put(tvaCode, vat);
                }

                int packagingId = statement.getResultSet().getInt("id_packaging");
                Packaging packaging = packagingCache.computeIfAbsent(packagingId, id -> {
                    try {
                        return new Packaging(
                                id,
                                statement.getResultSet().getString("packaing_label"),
                                statement.getResultSet().getInt("nb_articles")
                        );
                    } catch (SQLException e)
                    {
                        throw new RuntimeException(e);
                    }
                });

                Item item = new Item(
                        statement.getResultSet().getInt("id"),
                        statement.getResultSet().getString("label"),
                        statement.getResultSet().getFloat("price"),
                        statement.getResultSet().getInt("restock_quantity"),
                        statement.getResultSet().getInt("current_quantity"),
                        statement.getResultSet().getInt("emptyReturnableBottle_quantity"),
                        statement.getResultSet().getInt("emptyReturnableBottle_price"),
                        statement.getResultSet().getDate("forecast_date"),
                        statement.getResultSet().getInt("forecast_quantity"),
                        statement.getResultSet().getInt("min_quantity"),
                        packaging,
                        vat);

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
