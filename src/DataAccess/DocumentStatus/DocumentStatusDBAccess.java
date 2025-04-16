package DataAccess.DocumentStatus;

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
            Connection databaseConnexion = DatabaseConnexion.getInstance().getConnection();
            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<DocumentStatus> documentStatuses = new ArrayList<>();

            while (resultSet.next()){
                DocumentStatus documentStatus = MakeDocumentStatus.getDocumentStatus(
                        resultSet.getInt("id"),
                        resultSet.getString("label")
                );

                documentStatuses.add(documentStatus);
            }

            return documentStatuses;

        }catch (SQLException e){
            System.err.println(e.getMessage());
            throw new GetAllDocumentStatusException();
        }
    }
}
