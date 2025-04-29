package DataAccess.Document;

import DataAccess.CollectionAgency.CollectionAgencyDBAccess;
import DataAccess.DatabaseConnexion;
import DataAccess.DataAccesUtils;

import DataAccess.DeliveryTruck.DeliveryTruckDBAccess;
import DataAccess.DocumentStatus.DocumentStatusDBAccess;
import DataAccess.Process.ProcessDBAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;

import Model.Document.Document;
import Model.Document.MakeDocument;
import Model.Item.Item;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DocumentDBAccess implements DocumentDataAccess
{
    public ArrayList<Document> getAllCurrentCommandsForAnItem(Item item)
    {
        // Document -> Document_details(id_document) -> item_document_details -> item

        String query = "SELECT * " +
                "FROM document " +
                "WHERE " +
                "  (SELECT label FROM document_status WHERE id = document.id_document_status) IN ('WAITING', 'VALIDATING') " +
                "  AND (SELECT label " +
                "       FROM process_type " +
                "       WHERE id = (SELECT id_process_type " +
                "                   FROM process " +
                "                   WHERE id = document.id_process) " +
                "      ) = 'Order' " +
                "  AND document.id IN ( " +
                "    SELECT id_document " +
                "    FROM document_details " +
                "    where id_item = ? " +
                ");";

        try
        {
            Connection connection = DatabaseConnexion.getInstance();

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, item.getId());
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Document> documents = new ArrayList<>();

            while (resultSet.next())
            {
                documents.add(makeDocument(resultSet));
            }

            return documents;

        } catch (DatabaseConnectionFailedException | SQLException e)
        {
            //@TODO: handle exception
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createDocument(Document document) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void updateDocument(Document document) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void deleteDocument(Integer id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Document getDocument(Integer id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ArrayList<Document> getAllDocuments() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static Document makeDocument(ResultSet resultSet) throws SQLException
    {
        if(!DataAccesUtils.hasColumn(resultSet, "document.id")) return null;

        return MakeDocument.getDocument(
                resultSet.getInt("document.id"),
                resultSet.getString("document.label"),
                resultSet.getDate("document.date"),
                resultSet.getDate("document.deadline"),
                resultSet.getFloat("document.reduction"),
                resultSet.getString("document.validity"),
                resultSet.getBoolean("document.is_delivered"),
                resultSet.getDate("document.delivery_date"),
                resultSet.getBoolean("document.deposit_is_paid"),
                resultSet.getFloat("document.deposit_amount"),
                resultSet.getDate("document.desired_delivery_date"),
                resultSet.getFloat("document.vat_amount"),
                resultSet.getFloat("document.total_inclusive_of_taxe"),
                resultSet.getFloat("document.total_vat"),
                resultSet.getFloat("document.total_excl_vat"),
                CollectionAgencyDBAccess.makeCollectionAgency(resultSet),
                DocumentStatusDBAccess.makeDocumentStatus(resultSet),
                DeliveryTruckDBAccess.makeDeliveryTruck(resultSet),
                ProcessDBAccess.makeProcess(resultSet)
        );
    }
}
