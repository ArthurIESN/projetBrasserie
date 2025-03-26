package DataAccess.Item;

import DataAccess.DatabaseConnexion;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Model.Item.Item;
import Model.Packaging;
import Model.Vat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DataAccessItem {

    private Map<String, Vat> vatCache = new HashMap<>();
    private Map<Integer, Packaging> packagingCache = new HashMap<>();

    public DataAccessItem(){}

    public List<Item> getAllItems() throws DatabaseConnectionFailedException{
        List<Item> items = new ArrayList<>();

        String query = "SELECT *,packaging.id AS id_packaging,packaging.label AS packaging_label, " +
                "packaging.nb_articles AS packaging_nb_articles,vat.code AS vat_code,vat.rate AS vat_rate " +
                " FROM item INNER JOIN packaging ON item.id_packaging = packaging.id " +
                "INNER JOIN vat ON item.code_vat = vat.code";

        try
        {
            Connection dataBaseConnection = DatabaseConnexion.getInstance().getConnection();

            PreparedStatement statement = dataBaseConnection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){

                int id = resultSet.getInt("id");
                String label = resultSet.getString("label");
                float price = resultSet.getFloat("price");
                int restockQuantity = resultSet.getInt("restock_quantity");
                int currentQuantity = resultSet.getInt("current_quantity");
                int emptyReturnableBottleQuantity = resultSet.getInt("emptyReturnableBottle_quantity");
                int emptyReturnableBottlePrice = resultSet.getInt("emptyReturnableBottle_price");
                Date forecastDate = resultSet.getDate("forecast_date");
                int forecastQuantity = resultSet.getInt("forecast_quantity");
                int minQuantity = resultSet.getInt("min_quantity");
                int idPackaging = resultSet.getInt("id_packaging");
                String labelPackaging = resultSet.getString("packaging_label");
                int nbArticlesPackaging = resultSet.getInt("packaging_nb_articles");
                String vatCode = resultSet.getString("vat_code");


                Vat vat;

                if(vatCache.containsKey(vatCode)){
                    vat = vatCache.get(vatCode);
                }
                else
                {
                    vat = new Vat(
                            vatCode,
                            statement.getResultSet().getFloat("rate")
                    );

                    vatCache.put(vatCode,vat);
                }

                Packaging packaging;
                if(packagingCache.containsKey(idPackaging)){
                    packaging = packagingCache.get(idPackaging);
                }else{
                    packaging = new Packaging(
                            idPackaging,
                            statement.getResultSet().getString("packaging_label"),
                            statement.getResultSet().getInt("packaging_nb_articles")
                    );

                    packagingCache.put(idPackaging,packaging);
                }

                Item item = new Item(id,label,price,restockQuantity,currentQuantity,
                        emptyReturnableBottleQuantity,emptyReturnableBottlePrice,forecastDate,forecastQuantity,
                        minQuantity,packaging,vat);

                items.add(item);

            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
            throw new DatabaseConnectionFailedException("Database connection failed");
        }

        return items;

    }


}
