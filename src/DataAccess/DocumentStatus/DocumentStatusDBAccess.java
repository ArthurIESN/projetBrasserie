package DataAccess.DocumentStatus;

import DataAccess.DataAccessUtils;
import DataAccess.DatabaseConnexion;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.DocumentStatus.GetAllDocumentStatusException;
import Exceptions.DocumentStatus.GetDocumentStatusException;
import Model.DocumentStatus.DocumentStatus;
import Model.DocumentStatus.MakeDocumentStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DocumentStatusDBAccess implements DocumentStatusDataAccess{
    public ArrayList<DocumentStatus> getAllDocumentStatus() throws GetAllDocumentStatusException
    {
        String query = "SELECT * FROM document_status";

        try{
            Connection databaseConnexion = DatabaseConnexion.getInstance();
            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<DocumentStatus> documentStatuses = new ArrayList<>();

            while (resultSet.next())
            {
                documentStatuses.add(makeDocumentStatus(resultSet));
            }

            return documentStatuses;

        }catch (SQLException | DatabaseConnectionFailedException e){
            System.err.println(e.getMessage());
            throw new GetAllDocumentStatusException();
        }
    }

    @Override
    public void createDocumentStatus(DocumentStatus documentStatus) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void updateDocumentStatus(DocumentStatus documentStatus) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void deleteDocumentStatus(Integer id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public DocumentStatus getDocumentStatus(Integer id) throws GetDocumentStatusException
    {
        String query = "SELECT * FROM document_status WHERE id = ?";

        try{
            Connection databaseConnexion = DatabaseConnexion.getInstance();
            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next())
            {
                return makeDocumentStatus(resultSet);
            }
            else
            {
                throw new GetDocumentStatusException("Document status not found");
            }

        }catch (SQLException | DatabaseConnectionFailedException e){
            System.err.println(e.getMessage());
            throw new GetDocumentStatusException("Error while getting document status");
        }
    }

    @Override
    public ArrayList<DocumentStatus> getAllDocumentStatusByDocumentId(Integer documentId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static DocumentStatus makeDocumentStatus(ResultSet resultSet) throws SQLException
    {
        if(!DataAccessUtils.hasColumn(resultSet, "document_status.id")) return null;

        return MakeDocumentStatus.getDocumentStatus(
                resultSet.getInt("document_status.id"),
                resultSet.getString("document_status.label")
        );
    }
}
