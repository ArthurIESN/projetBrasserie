package DataAccess.Document;

import DataAccess.CollectionAgency.CollectionAgencyDBAccess;
import DataAccess.DatabaseConnexion;
import DataAccess.DataAccesUtils;

import DataAccess.DeliveryTruck.DeliveryTruckDBAccess;
import DataAccess.DocumentStatus.DocumentStatusDBAccess;
import DataAccess.Process.ProcessDBAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;

import Exceptions.Document.CreateDocumentException;
import Model.Document.Document;
import Model.Document.MakeDocument;
import Model.Item.Item;


import java.sql.*;
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
    public int createDocument(Document document) throws CreateDocumentException {
        String query = "INSERT INTO document (label, date, deadline, reduction, validity, is_delivered, delivery_date, deposit_is_paid, deposit_amount, desired_delivery_date, vat_amount, total_inclusive_of_taxe, total_vat, total_excl_vat, id_collection_agency, id_document_status, id_delivery_truck, id_process) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        if(document == null){
            throw new CreateDocumentException("Document is null");
        }
        else if (document.getLabel().isEmpty()) {
            throw new CreateDocumentException("Label is empty");
        }
        else if(document.getDate() == null){
            throw new CreateDocumentException("Date is null");
        }
        else if(document.getDocumentStatus() == null){
            throw new CreateDocumentException("Document status is null");
        }
        else if(document.getProcess() == null){
            throw new CreateDocumentException("Process is null");
        }

        try{
            Connection dataBaseConnection = DatabaseConnexion.getInstance();
            PreparedStatement statement = dataBaseConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, document.getLabel());
            statement.setDate(2, new java.sql.Date(document.getDate().getTime()));

            if (document.getDeliveryTruck() != null) {
                statement.setDate(3, new java.sql.Date(document.getDeadLine().getTime()));
            } else {
                statement.setNull(3, java.sql.Types.DATE);
            }

            statement.setFloat(4, document.getReduction());
            statement.setString(5, document.getValidity());
            statement.setBoolean(6, document.getIsDelivered());

            if (document.getDeliveryDate() != null) {
                statement.setDate(7, new java.sql.Date(document.getDeliveryDate().getTime()));
            } else {
                statement.setNull(7, java.sql.Types.DATE);
            }

            statement.setBoolean(8, document.getDepositIsPaid());
            statement.setFloat(9, document.getDepositAmount());

            if (document.getDesiredDeliveryDate() != null) {
                statement.setDate(10, new java.sql.Date(document.getDesiredDeliveryDate().getTime()));
            } else {
                statement.setNull(10, java.sql.Types.DATE);
            }

            statement.setFloat(11, document.getVatAmount());
            statement.setFloat(12, document.getTotalInclusiveOfTaxe());
            statement.setFloat(13, document.getTotalVat());
            statement.setFloat(14, document.getTotalExclVat());

            if (document.getCollectionAgency() != null) {
                statement.setInt(15, document.getCollectionAgency().getId());
            } else {
                statement.setNull(15, java.sql.Types.INTEGER);
            }

            statement.setInt(16, document.getDocumentStatus().getId());

            if (document.getDeliveryTruck() != null) {
                statement.setInt(17, document.getDeliveryTruck().getId());
            } else {
                statement.setNull(17, java.sql.Types.INTEGER);
            }

            statement.setInt(18, document.getProcess().getId());


            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new CreateDocumentException("Failed to create document, no rows affected.");
            }


            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new CreateDocumentException("Failed to create document, no ID obtained.");
                }
            }

        }catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            throw new CreateDocumentException();
        }
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
