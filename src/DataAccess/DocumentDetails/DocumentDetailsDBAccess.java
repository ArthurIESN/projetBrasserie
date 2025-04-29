package DataAccess.DocumentDetails;

import DataAccess.Document.DocumentDBAccess;
import Model.DocumentDetails.DocumentDetails;

import Model.DocumentDetails.MakeDocumentDetails;
import DataAccess.DataAccesUtils;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentDetailsDBAccess {
    private DocumentDetailsDBAccess()
    {
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
