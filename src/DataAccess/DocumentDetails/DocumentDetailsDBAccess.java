package DataAccess.DocumentDetails;

import DataAccess.DatabaseConnexion;
import DataAccess.Document.DocumentDBAccess;
import DataAccess.Item.ItemDBAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.DocumentDetails.CreateDocumentDetailsException;
import Exceptions.DocumentDetails.GetDocumentDetailsFromDocumentsException;
import Exceptions.Search.GetQuantityItemWithSpecificEventException;
import Model.Document.Document;
import Model.DocumentDetails.DocumentDetails;

import Model.DocumentDetails.MakeDocumentDetails;
import DataAccess.DataAccessUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DocumentDetailsDBAccess implements DocumentDetailsDataAccess
{
    public DocumentDetailsDBAccess()
    {
    }

    @Override
    public void createDocumentDetails(DocumentDetails documentDetails) throws CreateDocumentDetailsException
    {
        if(documentDetails == null)
        {
            throw new CreateDocumentDetailsException("Document details cannot be null");
        }
        else if(documentDetails.getDocument() == null)
        {
            throw new CreateDocumentDetailsException("Document cannot be null");
        }
        else if(documentDetails.getItem() == null)
        {
            throw new CreateDocumentDetailsException("Item cannot be null");
        }

        String query = "INSERT INTO document_details (label, quantity, new_quantity, unit_price, id_document, id_item) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try
        {
            Connection connection = DatabaseConnexion.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, documentDetails.getLabel());
            preparedStatement.setInt(2, documentDetails.getQuantity());
            preparedStatement.setObject(3, documentDetails.getNewQuantity(), java.sql.Types.INTEGER);
            preparedStatement.setFloat(4, documentDetails.getUnitPrice());
            preparedStatement.setInt(5, documentDetails.getDocument().getId());
            preparedStatement.setInt(6, documentDetails.getItem().getId());

            preparedStatement.executeUpdate();


        } catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.out.println("Error while creating document details: " + e.getMessage());
            throw new CreateDocumentDetailsException("Error while creating document details: ");
        }
    }

    @Override
    public void updateDocumentDetails(DocumentDetails documentDetails) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void deleteDocumentDetails(Integer id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public DocumentDetails getDocumentDetails(Integer id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ArrayList<DocumentDetails> getAllDocumentDetails() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public ArrayList<Integer> getQuantityItemWithSpecificEvent(int idEvent, int idItem) throws GetQuantityItemWithSpecificEventException
    {

        String query = "SELECT DISTINCT new_quantity  FROM document_details " +
                "JOIN event_document_details ON event_document_details.id_document_details = document_details.id " +
                "JOIN item ON item.id = document_details.id_item " +
                "WHERE event_document_details.id_event = ? " +
                "AND item.id = ?";

        try{
            Connection dataBaseConnection = DatabaseConnexion.getInstance();
            PreparedStatement preparedStatement = dataBaseConnection.prepareStatement(query);

            preparedStatement.setInt(1, idEvent);
            preparedStatement.setInt(2, idItem);
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Integer> quantities = new ArrayList<>();

            while(resultSet.next())
            {
                quantities.add(resultSet.getInt("new_quantity"));
            }
            return quantities;
        }catch (SQLException  | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            throw new GetQuantityItemWithSpecificEventException();
        }
    }

    public static DocumentDetails makeDocumentDetails(ResultSet resultSet) throws SQLException
    {
        if(!DataAccessUtils.hasColumn(resultSet, "document_details.id")) return null;

        return MakeDocumentDetails.getDocumentDetails(
                resultSet.getInt("document_details.id"),
                resultSet.getString("document_details.label"),
                resultSet.getInt("document_details.quantity"),
                resultSet.getInt("document_details.new_quantity"),
                resultSet.getFloat("document_details.unit_price"),
                DocumentDBAccess.makeDocument(resultSet),
                ItemDBAccess.makeItem(resultSet)
        );
    }


}
