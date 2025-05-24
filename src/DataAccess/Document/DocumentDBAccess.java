package DataAccess.Document;

import DataAccess.CollectionAgency.CollectionAgencyDBAccess;
import DataAccess.DatabaseConnexion;
import DataAccess.DataAccessUtils;

import DataAccess.DeliveryTruck.DeliveryTruckDBAccess;
import DataAccess.DocumentStatus.DocumentStatusDBAccess;
import DataAccess.Process.ProcessDBAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;

import Exceptions.Document.*;
import Exceptions.DocumentStatus.DocumentStatusException;
import Exceptions.Process.DeleteProcessException;
import Exceptions.Search.GetDocumentWithSpecificEventException;
import Model.Document.Document;
import Model.Document.MakeDocument;
import Model.Item.Item;


import java.sql.*;
import java.util.ArrayList;

public class DocumentDBAccess implements DocumentDataAccess
{
    public ArrayList<Document> getAllCurrentCommandsForAnItem(Item item) throws GetAllCurrentCommandsForAnItemException
    {
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
                Document document = makeDocument(resultSet);

                if (document != null)
                {
                    documents.add(document);
                }
            }

            return documents;

        } catch (DatabaseConnectionFailedException | SQLException e)
        {
            System.out.println("Error while getting all current commands for an item: " + e.getMessage());
            throw new  GetAllCurrentCommandsForAnItemException("Error while getting all current commands for an item");
        }
    }

    @Override
    public int createDocument(Document document) throws CreateDocumentException {
        String query = "INSERT INTO document (label, date, deadline, reduction, validity, is_delivered, delivery_date, deposit_is_paid, deposit_amount, desired_delivery_date, total_inclusive_of_taxe, total_vat, total_excl_vat, id_collection_agency, id_document_status, id_delivery_truck, id_process) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            statement.setObject(3, document.getDeadLine() != null ? new java.sql.Date(document.getDeadLine().getTime()) : null, Types.DATE);
            statement.setFloat(4, document.getReduction());
            statement.setString(5, document.getValidity());
            statement.setBoolean(6, document.getIsDelivered());
            statement.setObject(7, document.getDeliveryDate() != null ? new java.sql.Date(document.getDeliveryDate().getTime()) : null, Types.DATE);
            statement.setBoolean(8, document.getDepositIsPaid());
            statement.setFloat(9, document.getDepositAmount());
            statement.setDate(10, document.getDesiredDeliveryDate() != null ? new java.sql.Date(document.getDesiredDeliveryDate().getTime()) : null);
            statement.setFloat(11, document.getTotalInclusiveOfTax());
            statement.setFloat(12, document.getTotalVat());
            statement.setFloat(13, document.getTotalExclVat());
            statement.setObject(14, document.getCollectionAgency() != null ? document.getCollectionAgency().getId() : null, Types.INTEGER);
            statement.setObject(15, document.getDocumentStatus() != null ? document.getDocumentStatus().getId() : null, Types.INTEGER);
            statement.setObject(16, document.getDeliveryTruck() != null ? document.getDeliveryTruck().getId() : null, Types.INTEGER);
            statement.setObject(17, document.getProcess() != null ? document.getProcess().getId() : null, Types.INTEGER);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new CreateDocumentException("Failed to create document, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys())
            {
                if (generatedKeys.next())
                {
                    return generatedKeys.getInt(1);
                } else
                {
                    throw new CreateDocumentException("Failed to create document, no ID obtained.");
                }
            }

        }catch (SQLException | DatabaseConnectionFailedException e){
            System.err.println(e.getMessage());
            throw new CreateDocumentException();
        }
    }

    @Override
    public void updateDocument(Document document) {

        String query = "UPDATE document SET " +
                "label = ?, " +
                "date = ?, " +
                "reduction = ?, " +
                "validity = ?, " +
                "is_delivered = ?, " +
                "delivery_date = ?, " +
                "deposit_is_paid = ?, " +
                "deposit_amount = ?, " +
                "desired_delivery_date = ?, " +
                "id_document_status = ?, " +
                "id_delivery_truck = ?, " +
                "id_process = ? " +
                "WHERE document.id = ? ";

        if(document == null)
        {
            throw new UpdateDocumentException("Document cannot be null");
        }else if(document.getLabel().isEmpty())
        {
            throw new UpdateDocumentException("Label cannot be empty");
        }else if(document.getDate() == null){
            throw new UpdateDocumentException("Date cannot be null");
        }else if(document.getValidity() == null)
        {
            throw new UpdateDocumentException("Validity cannot be null");
        }else if(document.getDocumentStatus() == null)
        {
            throw new UpdateDocumentException("Document status cannot be null");
        }else if(document.getProcess() == null)
        {
            throw new UpdateDocumentException("Process cannot be null");
        }else if(document.getDesiredDeliveryDate() == null)
        {
            throw new UpdateDocumentException("Desired delivery date cannot be null");
        }else if(document.getDeliveryDate() == null){
            throw new UpdateDocumentException("Delivery date cannot be null");
        }
        else if(document.getDepositIsPaid() == null)
        {
            throw new UpdateDocumentException("Deposit is paid cannot be null");
        }else if(document.getDepositAmount() == null)
        {
            throw new UpdateDocumentException("Deposit amount cannot be null");
        }

        try{
            Connection databaseConnection = DatabaseConnexion.getInstance();
            PreparedStatement statement = databaseConnection.prepareStatement(query);

            statement.setString(1, document.getLabel());
            statement.setDate(2, new java.sql.Date(document.getDate().getTime()));
            statement.setObject(3, document.getReduction() != null ? document.getReduction() : null, Types.FLOAT);
            statement.setString(4, document.getValidity());
            statement.setBoolean(5, document.getIsDelivered());
            statement.setDate(6, new java.sql.Date(document.getDeliveryDate().getTime()));
            statement.setBoolean(7, document.getDepositIsPaid());
            statement.setObject(8, document.getDepositAmount() != null ? document.getDepositAmount() : null, Types.FLOAT);
            statement.setDate(9, new java.sql.Date(document.getDesiredDeliveryDate().getTime()));
            statement.setInt(10, document.getDocumentStatus().getId());
            statement.setObject(11, document.getDeliveryTruck() != null ? document.getDeliveryTruck().getId() : null, Types.INTEGER);
            statement.setInt(12, document.getProcess().getId());
            statement.setInt(13, document.getId());

            int rowsAffected = statement.executeUpdate();
            if(rowsAffected == 0)
            {
                throw new UpdateDocumentException("Invalid document ID : " + document.getId());
            }
        }catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            throw new UpdateDocumentException();
        }
    }

    @Override
    public void deleteDocument(Integer id) throws DeleteDocumentException {
        String query = "DELETE FROM document WHERE id = ?";
        try{
            Connection databaseConnection = DatabaseConnexion.getInstance();
            PreparedStatement statement = databaseConnection.prepareStatement(query);
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();
            if(rowsAffected == 0){
                throw new DeleteDocumentException("Invalid document ID : " + id);
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());

            if(DataAccessUtils.isASQLForeignKeyConstraintFails(e.getErrorCode()))
            {
                throw new DeleteDocumentException("Cannot delete document. This document is linked to an other entity");
            }

            throw new DeleteDocumentException("Error while deleting document");
        }catch (DatabaseConnectionFailedException e){
            System.err.println(e.getMessage());
            throw new DeleteDocumentException();
        }
    }

    @Override
    public Document getDocument(Integer id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ArrayList<Document> getAllDocuments() {
        String query = "SELECT * FROM document " +
                " LEFT JOIN collection_agency ON document.id_collection_agency = collection_agency.id " +
                " LEFT JOIN document_status ON document.id_document_status = document_status.id " +
                " LEFT JOIN delivery_truck ON document.id_delivery_truck = delivery_truck.id " +
                " LEFT JOIN process ON document.id_process = process.id " +
                " LEFT JOIN supplier ON process.id_supplier = supplier.id " +
                " LEFT JOIN customer ON process.num_customer = customer.num_customer " +
                " LEFT JOIN customer_status ON customer.id_customer_status = customer_status.id " +
                " LEFT JOIN  process_type ON process.id_process_type = process_type.id " +
                " LEFT JOIN  process_status ON process.id_process_status = process_status.id " +
                " LEFT JOIN  employee ON process.id_employee = employee.id " +
                " LEFT JOIN employee_status ON employee.id_employee_status = employee_status.id " +
                "ORDER BY document.id ";
        try{
            Connection databaseConnection = DatabaseConnexion.getInstance();
            PreparedStatement statement = databaseConnection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Document> documents = new ArrayList<>();

            while(resultSet.next())
            {
                Document document = makeDocument(resultSet);

                if(document !=null)
                {
                    documents.add(document);
                }

            }
            return documents;
        }
        catch (SQLException | DatabaseConnectionFailedException e){
            System.err.println(e.getMessage());
            throw new GetAllDocumentsException();
        }
    }

    public ArrayList<Document> getDocumentsWithSpecificEvent(int idItem, int idEvent, int quantity, int year) throws GetDocumentWithSpecificEventException
    {
        String query = "SELECT * " +
                "FROM document " +
                "INNER JOIN document_details ON document.id = document_details.id_document " +
                "INNER JOIN document_status ON document.id_document_status = document_status.id " +
                "INNER JOIN item ON document_details.id_item = item.id " +
                "INNER JOIN process ON document.id_process = process.id " +
                "INNER JOIN supplier ON supplier.id = process.id_supplier " +
                "INNER JOIN process_type ON process.id_process_type = process_type.id " +
                "INNER JOIN process_status ON process.id_process_status = process_status.id " +
                "LEFT JOIN employee ON employee.id = process.id_employee  " +
                "LEFT JOIN employee_status ON employee_status.id = employee.id_employee_status  " +
                "LEFT JOIN event_document_details ON document_details.id = event_document_details.id_document_details  " +
                "LEFT JOIN event ON event_document_details.id_event = event.id  " +
                "WHERE process_type.label = 'Order' AND " +
                "    item.id = ? AND " +
                "    event_document_details.id_event = ? AND " +
                "    document_details.quantity = ? AND " +
                "    YEAR(event.start_date) = ?;";

        try {
            Connection dataBaseConnection = DatabaseConnexion.getInstance();
            PreparedStatement preparedStatement = dataBaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, idItem);
            preparedStatement.setInt(2, idEvent);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setInt(4, year);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Document> documents = new ArrayList<>();

            while (resultSet.next())
            {
                Document document = DocumentDBAccess.makeDocument(resultSet);

                if(document != null)
                {
                    documents.add(document);
                }
            }

            return documents;
        } catch (SQLException | DatabaseConnectionFailedException e) {
            System.err.println(e.getMessage());
            throw new GetDocumentWithSpecificEventException();
        }
    }

    public static Document makeDocument(ResultSet resultSet) throws SQLException
    {
        if(!DataAccessUtils.hasColumn(resultSet, "document.id")) return null;

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
