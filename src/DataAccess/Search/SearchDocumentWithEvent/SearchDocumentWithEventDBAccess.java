package DataAccess.Search.SearchDocumentWithEvent;

import DataAccess.DatabaseConnexion;
import DataAccess.Document.DocumentDBAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Search.GetDocumentWithSpecificEventException;
import Exceptions.Search.GetQuantityItemWithSpecificEventException;
import Model.Document.Document;
import Model.Document.MakeDocument;
import Model.DocumentStatus.DocumentStatus;
import Model.DocumentStatus.MakeDocumentStatus;
import Model.Employee.Employee;
import Model.Employee.MakeEmployee;
import Model.EmployeeStatus.EmployeeStatus;
import Model.EmployeeStatus.MakeEmployeeStatus;
import Model.Process.MakeProcess;
import Model.Process.Process;
import Model.ProcessStatus.MakeProcessStatus;
import Model.ProcessStatus.ProcessStatus;
import Model.ProcessType.MakeProcessType;
import Model.ProcessType.ProcessType;
import Model.Supplier.MakeSupplier;
import Model.Supplier.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchDocumentWithEventDBAccess implements SearchDocumentWithEventDataAccess {
    public List<Integer> getDatesEvents(Integer idEvent)  throws DatabaseConnectionFailedException {

        String query = "SELECT DISTINCT YEAR(start_date) AS start_date FROM event WHERE id = ?";


        try
        {
            Connection connection = DatabaseConnexion.getInstance();

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,idEvent);
            ResultSet resultSet = statement.executeQuery();
            List<Integer> dates = new ArrayList<>();

            while (resultSet.next()) {
                dates.add(resultSet.getInt("start_date"));
            }
            return dates;
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
            throw new DatabaseConnectionFailedException();
        }

    }

    public ArrayList<Integer> getQuantityItemWithSpecificEvent(int idEvent) throws DatabaseConnectionFailedException, GetQuantityItemWithSpecificEventException {
        String query = "SELECT  new_quantity  FROM document_details dd " +
                "JOIN event_document_details edd ON edd.id_document_details = dd.id " +
                "WHERE edd.id_event = ?";

        try{
            Connection dataBaseConnection = DatabaseConnexion.getInstance();
            PreparedStatement preparedStatement = dataBaseConnection.prepareStatement(query);
            preparedStatement.setInt(1,idEvent);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Integer> quantities = new ArrayList<>();
            while(resultSet.next()){
                quantities.add(resultSet.getInt("new_quantity"));
            }
            return quantities;
        }catch (SQLException  e){
            System.err.println(e.getMessage());
            throw new GetQuantityItemWithSpecificEventException();
        }
    }

    public ArrayList<Document> getDocumentsWithSpecificEvent(int idItem, int idEvent, int quantity, int year) throws DatabaseConnectionFailedException, GetDocumentWithSpecificEventException {
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
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, 1);
            preparedStatement.setInt(3, 10);
            preparedStatement.setInt(4, 2025);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Document> documents = new ArrayList<>();

            while (resultSet.next())
            {
                documents.add(DocumentDBAccess.makeDocument(resultSet));
            }

            return documents;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new GetDocumentWithSpecificEventException();
        }
    }
}
