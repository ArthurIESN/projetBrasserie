package DataAccess.Item;

import DataAccess.DatabaseConnexion;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Item.GetAllItemsException;
import Model.Item.Item;

import Model.Packaging.Packaging;
import Model.Vat.Vat;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ItemDBAccess {

    private Map<String, Vat> vatCache = new HashMap<>();
    private Map<Integer, Packaging> packagingCache = new HashMap<>();

    public ItemDBAccess(){}

    public List<Item> getAllItems() throws GetAllItemsException {
        List<Item> items = new ArrayList<>();

        String query = "SELECT *,packaging.id AS id_packaging,packaging.label AS packaging_label, " +
                "packaging.quantity AS packaging_quantity,vat.code AS vat_code,vat.rate AS vat_rate " +
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
                int emptyReturnableBottleQuantity = resultSet.getInt("empty_returnable_bottle_quantity");
                int emptyReturnableBottlePrice = resultSet.getInt("empty_returnable_bottle_price");
                Date forecastDate = resultSet.getDate("forecast_date");
                int forecastQuantity = resultSet.getInt("forecast_quantity");
                int minQuantity = resultSet.getInt("min_quantity");
                int idPackaging = resultSet.getInt("id_packaging");
                String labelPackaging = resultSet.getString("packaging_label");
                int nbArticlesPackaging = resultSet.getInt("packaging_quantity");
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
                            statement.getResultSet().getInt("packaging_quantity")
                    );

                    packagingCache.put(idPackaging,packaging);
                }

                Item item = new Item(id,label,price,restockQuantity,currentQuantity,
                        emptyReturnableBottleQuantity,emptyReturnableBottlePrice,forecastDate,forecastQuantity,
                        minQuantity,packaging,vat);

                items.add(item);

            }
        }catch (SQLException | DatabaseConnectionFailedException  e)
        {
            System.err.println(e.getMessage());
            throw new GetAllItemsException();
        }

        return items;

    }


}
