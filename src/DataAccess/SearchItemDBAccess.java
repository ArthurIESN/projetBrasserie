package DataAccess;

import Model.Item.Item;
import Model.Packaging;
import Model.Vat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchItemDBAccess
{
    public SearchItemDBAccess()
    {
    }

    public ArrayList<Item> searchItem(String tvaCode, int minItem, int maxItem, int minPrice, int maxPrice)
    {
        Connection databaseConnexion = DatabaseConnexion.getInstance();

        String sql = "SELECT *, packaging.label AS packaing_label " +
                "FROM item " +
                "JOIN vat ON item.code_vat LIKE vat.code " +
                "JOIN packaging ON item.id_packaging LIKE packaging.id " +
                "WHERE vat.code = ? " +
                "AND item.current_quantity BETWEEN ? AND ? " +
                "AND item.price BETWEEN ? AND ? ";

        try
        {
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
                Vat vat = new Vat(
                        statement.getResultSet().getString("code"),
                        statement.getResultSet().getFloat("rate"));

                Packaging packaging = new Packaging(
                        statement.getResultSet().getInt("id_packaging"),
                        statement.getResultSet().getString("packaing_label"),
                        statement.getResultSet().getInt("nb_articles"));

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
            throw new RuntimeException(e);
        }

    }
}
