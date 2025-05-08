package DataAccess.DocumentDetails;

import DataAccess.DatabaseConnexion;
import DataAccess.Document.DocumentDBAccess;
import DataAccess.Item.ItemDBAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Model.Document.Document;
import Model.DocumentDetails.DocumentDetails;

import Model.DocumentDetails.MakeDocumentDetails;
import DataAccess.DataAccesUtils;

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
    public void createDocumentDetails(DocumentDetails documentDetails)
    {
        String query = "INSERT INTO document_details (label, quantity, new_quantity, unit_price, id_document, id_item) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try
        {
            Connection connection = DatabaseConnexion.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, documentDetails.getLabel());
            preparedStatement.setInt(2, documentDetails.getQuantity());

            if(documentDetails.getNewQuantity() == null)
            {
                preparedStatement.setNull(3, java.sql.Types.INTEGER);
            }
            else
            {
                preparedStatement.setInt(3, documentDetails.getNewQuantity());
            }

            preparedStatement.setFloat(4, documentDetails.getUnitPrice());
            preparedStatement.setInt(5, documentDetails.getDocument().getId());
            preparedStatement.setInt(6, documentDetails.getItem().getId());

            preparedStatement.executeUpdate();


        } catch (SQLException | DatabaseConnectionFailedException e) {
            //@todo : throw exception
            System.out.println("Error while creating document details: " + e.getMessage());
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

    @Override
    public ArrayList<DocumentDetails> getDocumentsDetailsFromDocuments(ArrayList<Document> documents)
    {
        ArrayList<Integer> documentIds = Utils.Utils.transformData(documents, Document::getId);
        String placeholders = String.join(",", documentIds.stream().map(id -> "?").toArray(String[]::new));

        String query = "SELECT DISTINCT * " +
                "FROM document_details " +
                "WHERE id_document IN (" + placeholders + ")";

        try (Connection connection = DatabaseConnexion.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (int i = 0; i < documentIds.size(); i++)
            {
                preparedStatement.setInt(i + 1, documentIds.get(i));
            }

            // log the query
            System.out.println("Executing query: " + preparedStatement.toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<DocumentDetails> documentDetails = new ArrayList<>();

            while (resultSet.next())
            {
                documentDetails.add(makeDocumentDetails(resultSet));
            }

            return documentDetails;

        } catch (SQLException | DatabaseConnectionFailedException e)
        {
            //@TODO: handle exception
            System.out.println("Error while getting documents details from documents: " + e.getMessage());
        }

        return null;
    }

    public static DocumentDetails makeDocumentDetails(ResultSet resultSet) throws SQLException
    {
        if(!DataAccesUtils.hasColumn(resultSet, "document_details.id")) return null;

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
