package DataAccess.DocumentDetails;

import DataAccess.Document.DocumentDBAccess;
import Model.DocumentDetails.DocumentDetails;

import Model.DocumentDetails.MakeDocumentDetails;
import DataAccess.DataAccesUtils;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DocumentDetailsDBAccess implements DocumentDetailsDataAccess
{
    public DocumentDetailsDBAccess()
    {
    }

    @Override
    public void createDocumentDetails(DocumentDetails documentDetails) {
        throw new UnsupportedOperationException("Not implemented yet");
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

    public static DocumentDetails makeDocumentDetails(ResultSet resultSet) throws SQLException
    {
        if(!DataAccesUtils.hasColumn(resultSet, "document_details.id")) return null;

        return MakeDocumentDetails.getDocumentDetails(
                resultSet.getInt("document_details.id"),
                resultSet.getString("document_details.label"),
                resultSet.getFloat("document_details.quantity"),
                resultSet.getFloat("document_details.new_quantity"),
                resultSet.getFloat("document_details.unit_price"),
                DocumentDBAccess.makeDocument(resultSet)
        );
    }
}
