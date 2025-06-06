package DataAccess;


import java.sql.ResultSetMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataAccessUtils
{
    private DataAccessUtils() {
        // Prevent instantiation
    }
    public static boolean hasColumn(ResultSet rs, String columnName) {
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnLabel = metaData.getColumnLabel(i);
                String fullColumnName = metaData.getTableName(i) + "." + columnLabel;

                if (columnName.equalsIgnoreCase(columnLabel) || columnName.equalsIgnoreCase(fullColumnName)) {
                    // if value is null, it means the column is not present
                    Object value = rs.getObject(i);
                    return value != null;
                }
            }
        } catch (Exception e) {
            System.out.println("Error checking for column: " + e.getMessage());
        }
        return false;
    }

    /**
     * ⚠️ Use only for debugging.
     * @deprecated This method will go through all result set resulting in an empty result set !
     */
    @Deprecated
    public static void logResultSet(ResultSet resultSet) throws SQLException
    {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()) {
            System.out.println("Row:");
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                Object value = resultSet.getObject(i);
                System.out.println("  " + columnName + ": " + value);
            }
        }
    }

    public static boolean isASQLForeignKeyConstraintFails(int errorCode)
    {
            return errorCode == 1451;
    }


}
