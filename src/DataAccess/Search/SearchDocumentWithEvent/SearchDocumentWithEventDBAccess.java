package DataAccess.Search.SearchDocumentWithEvent;

import DataAccess.DatabaseConnexion;
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

    public ArrayList<Float> getQuantityItemWithSpecificEvent(int idEvent) throws DatabaseConnectionFailedException, GetQuantityItemWithSpecificEventException {
        String query = "SELECT  new_quantity  FROM document_details dd " +
                "JOIN event_document_details edd ON edd.id_document_details = dd.id " +
                "WHERE edd.id_event = ?";

        try{
            Connection dataBaseConnection = DatabaseConnexion.getInstance();
            PreparedStatement preparedStatement = dataBaseConnection.prepareStatement(query);
            preparedStatement.setInt(1,idEvent);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Float> quantities = new ArrayList<>();
            while(resultSet.next()){
                quantities.add(resultSet.getFloat("new_quantity"));
            }
            return quantities;
        }catch (SQLException  e){
            System.err.println(e.getMessage());
            throw new GetQuantityItemWithSpecificEventException();
        }
    }

    public ArrayList<Document> getDocumentsWithSpecificEvent(int idItem, int idEvent, float quantity, int year) throws DatabaseConnectionFailedException, GetDocumentWithSpecificEventException {
         String query = "SELECT document.id AS document_id,document.label AS document_label,document.date AS document_date, " +
                 "document.is_delivered AS document_isDelivered, " +
                 "document.delivery_date AS document_deliveryDate, document.desired_delivery_date AS document_desiredDeliveryDate, " +
                 "document.VAT_amount AS document_vatAmount, document.total_inclusive_of_taxe AS document_totalInclusiveOfTaxe, " +
                 "document.total_VAT AS document_totalVat, document.total_excl_VAT AS document_totalExclVat, " +
                 "document_status.id AS documentStatus_id, document_status.label AS documentStatus_label, " +
                 "supplier.id AS supplier_id, supplier.name AS supplier_name, " +
                 "process_type.id AS processType_id, process_type.label AS processType_label, " +
                 "process_status.id AS processStatus_id, process_status.label AS processStatus_label, " +
                 "employee.id AS employee_id, employee.last_name AS employee_lastName, employee.first_name AS employee_firstName, " +
                 "employee.birth_date AS employee_birthDate, " +
                 "employee_status.id AS employeeStatus_id, employee_status.label AS employeeStatus_label, " +
                 "process.id AS process_id, process.label AS process_label, process.number AS process_number, " +
                 "process.creation_date AS process_creationDate " +
                 "FROM document document INNER JOIN document_details document_details ON document.id = document_details.id_document " +
                 "                         INNER JOIN document_status document_status ON " +
                 "                         document.id_document_status = document_status.id " +
                 "                         INNER JOIN item_document_details idd ON document_details.id = idd.id_document_details " +
                 "                         INNER JOIN process process ON document.id_process = process.id " +
                 "                         INNER JOIN supplier supplier ON supplier.id = process.id_supplier " +
                 "                         INNER JOIN process_type process_type ON process.id_process_type = process_type.id " +
                 "                         INNER JOIN process_status process_status ON process.id_process_status = process_status.id " +
                 "                         INNER JOIN employee employee ON employee.id = process.id_employee " +
                 "                         INNER JOIN employee_status employee_status ON employee_status.id = employee.id_employee_status " +
                 "                         INNER JOIN event_document_details event_document_details ON document_details.id = event_document_details.id_document_details " +
                 "                         INNER JOIN event e ON event_document_details.id_event = e.id " +
                 "WHERE process_type.label = 'order_fourn' AND " +
                 "    idd.id_item = ? AND " +
                 "    event_document_details.id_event = ? AND " +
                 "    document_details.new_quantity = ? AND " +
                 "    YEAR(e.start_date) = ?;";

         try{
             Connection dataBaseConnection = DatabaseConnexion.getInstance();
             PreparedStatement preparedStatement = dataBaseConnection.prepareStatement(query);
             preparedStatement.setInt(1,idItem);
             preparedStatement.setInt(2,idEvent);
             preparedStatement.setFloat(3,quantity);
             preparedStatement.setInt(4,year);
             ResultSet resultSet = preparedStatement.executeQuery();
             ArrayList<Document> documents = new ArrayList<>();
             while(resultSet.next()){
                 DocumentStatus documentStatus = MakeDocumentStatus.getDocumentStatus(
                        resultSet.getInt("documentStatus_id"),
                         resultSet.getString("documentStatus_label")
                 );

                 Supplier supplier = MakeSupplier.getSupplier(
                        resultSet.getInt("supplier_id"),
                         resultSet.getString("supplier_name")
                 );

                 ProcessType processType = MakeProcessType.getProcessType(
                         resultSet.getInt("processType_id"),
                            resultSet.getString("processType_label")
                 );

                 ProcessStatus processStatus = MakeProcessStatus.getProcessStatus(
                   resultSet.getInt("processStatus_id"),
                         resultSet.getString("processStatus_label")
                 );

                 EmployeeStatus employeeStatus = MakeEmployeeStatus.getEmployeeStatus(
                   resultSet.getInt("employeeStatus_id"),
                         resultSet.getString("employeeStatus_label")
                 );

                 Employee employee = MakeEmployee.getEmployee(
                   resultSet.getInt("employee_id"),
                         resultSet.getString("employee_lastName"),
                         resultSet.getString("employee_firstName"),
                         resultSet.getDate("employee_birthDate"),
                         employeeStatus
                 );

                 Process process = MakeProcess.getProcess(
                         resultSet.getInt("process_id"),
                         resultSet.getString("process_label"),
                         resultSet.getInt("process_number"),
                         resultSet.getDate("process_creationDate"),
                         supplier,
                         processType,
                         processStatus,
                         employee,
                         null
                 );


                 Document document = MakeDocument.getDocument(
                         resultSet.getInt("document_id"),
                         resultSet.getString("document_label"),
                         resultSet.getDate("document_date"),
                         null,
                         null,
                         null,
                         resultSet.getBoolean("document_isDelivered"),
                         resultSet.getDate("document_deliveryDate"),
                         null,
                         null,
                         resultSet.getDate("document_desiredDeliveryDate"),
                         resultSet.getFloat("document_vatAmount"),
                         resultSet.getFloat("document_totalInclusiveOfTaxe"),
                         resultSet.getFloat("document_totalVat"),
                         resultSet.getFloat("document_totalExclVat"),
                         null,
                         documentStatus,
                         null,
                         process
                 );

                 documents.add(document);
             }

             return documents;
         }catch (SQLException e){
             System.err.println(e.getMessage());
             throw new GetDocumentWithSpecificEventException();
         }
    }
}
