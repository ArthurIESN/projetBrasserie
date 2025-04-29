package DataAccess.DocumentStatus;

import DataAccess.DataAccesUtils;
import DataAccess.DatabaseConnexion;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.DocumentStatus.GetAllDocumentStatusException;
import Model.DocumentStatus.DocumentStatus;
import Model.DocumentStatus.MakeDocumentStatus;
import com.sun.source.tree.ArrayAccessTree;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DocumentStatusDBAccess implements DocumentStatusDataAccess{
    public ArrayList<DocumentStatus> getAllDocumentStatus() throws DatabaseConnectionFailedException, GetAllDocumentStatusException {
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

        }catch (SQLException e){
            System.err.println(e.getMessage());
            throw new GetAllDocumentStatusException();
        }
    }

    public static DocumentStatus makeDocumentStatus(ResultSet resultSet) throws SQLException
    {
        if(!DataAccesUtils.hasColumn(resultSet, "document_status.id")) return null;

        return MakeDocumentStatus.getDocumentStatus(
                resultSet.getInt("document_status.id"),
                resultSet.getString("document_status.label")
        );
    }
}
