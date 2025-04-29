package DataAccess.Process;

import DataAccess.DatabaseConnexion;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Process.*;

import Model.Customer.Customer;
import Model.Customer.MakeCustomer;
import Model.CustomerStatus.CustomerStatus;
import Model.Employee.Employee;
import Model.Employee.MakeEmployee;
import Model.EmployeeStatus.EmployeeStatus;
import Model.Process.MakeProcess;
import Model.Process.Process;
import Model.ProcessStatus.ProcessStatus;
import Model.ProcessStatus.MakeProcessStatus;
import Model.ProcessType.ProcessType;
import Model.ProcessType.MakeProcessType;
import Model.Supplier.Supplier;
import Model.Supplier.MakeSupplier;
import com.sun.source.tree.ArrayAccessTree;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static java.sql.Types.INTEGER;





public class ProcessDBAccess implements ProcessDataAccess
{
    public ProcessDBAccess()
    {
    }

    public void createProcess(Process process) throws CreateProcessException
    {
        String query = "INSERT INTO process (label, number, creation_date, id_supplier, id_process_type, id_process_status, id_employee, num_customer) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        if(process == null)
        {
            throw new CreateProcessException("Process cannot be null");
        }
        else if(process.getLabel().isEmpty()) //@todo : put a character limit (the same as the database) ?
        {
            throw new CreateProcessException("Label cannot be empty");
        }
        else if(process.getNumber() <= 0)
        {
            throw new CreateProcessException("Number cannot be 0");
        }
        else if(process.getType() == null)
        {
            throw new CreateProcessException("Type cannot be null");
        }
        else if(process.getProcessStatus() == null)
        {
            throw new CreateProcessException("Process status cannot be null");
        }

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();
            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            statement.setString(1, process.getLabel());
            statement.setInt(2, process.getNumber());
            statement.setDate(3, new java.sql.Date(process.getCreationDate().getTime()));

            if(process.getSupplier() == null)
            {
                statement.setNull(4, INTEGER);
            }
            else
            {
                statement.setInt(4, process.getSupplier().getId());
            }

            statement.setInt(5, process.getType().getId());
            statement.setInt(6, process.getProcessStatus().getId());

            if(process.getEmployee() == null)
            {
                statement.setNull(7, INTEGER);
            }
            else
            {
                statement.setInt(7, process.getEmployee().getId());
            }

            if(process.getCustomer() == null)
            {
                statement.setNull(8, INTEGER);
            }
            else
            {
                statement.setInt(8, process.getCustomer().getId());
            }

            statement.executeUpdate();
        }
        catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            throw new CreateProcessException();
        }


    }

    public void updateProcess(Integer id, String label, Integer number, Integer supplierId, Integer typeId, Integer processStatusId, Integer employeeId, Integer customerId) throws UpdateProcessException
    {
        String query = "UPDATE process SET " +
                "label = ?, " +
                "number = ?, " +
                "id_supplier = ?, " +
                "id_process_type = ?, " +
                "id_process_status = ?, " +
                "id_employee = ?, " +
                "num_customer = ? " +
                "WHERE id = ?";

        if(id == null)
        {
            throw new UpdateProcessException("ID cannot be null");
        }
        else if(label.isEmpty()) //@todo : put a character limit (the same as the database) ?
        {
            throw new UpdateProcessException("Label cannot be empty");
        }
        else if(number <= 0)
        {
            throw new UpdateProcessException("Number must be greater or equal to 0");
        }
        else if(typeId == null)
        {
            throw new UpdateProcessException("Type cannot be null");
        }
        else if(processStatusId == null)
        {
            throw new UpdateProcessException("Process status cannot be null");
        }

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();
            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            statement.setString(1, label);
            statement.setInt(2, number);

            if(supplierId == null)
            {
                statement.setNull(3, INTEGER);
            }
            else
            {
                statement.setInt(3, supplierId);
            }

            statement.setInt(4, typeId);
            statement.setInt(5, processStatusId);

            if(employeeId == null)
            {
                statement.setNull(6, INTEGER);
            }
            else
            {
                statement.setInt(6, employeeId);
            }

            if(customerId == null)
            {
                statement.setNull(7, INTEGER);
            }
            else
            {
                statement.setInt(7, customerId);
            }

            statement.setInt(8, id);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0)
            {
                throw new UpdateProcessException("Invalid process ID: " + id);
            }
        }
        catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            throw new UpdateProcessException();
        }
    }

    public void deleteProcess(Integer id) throws DeleteProcessException
    {
        String query = "DELETE FROM process WHERE id = ?";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();
            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                throw new DeleteProcessException("Invalid process ID: " + id);
            }
        }
        catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            throw new DeleteProcessException();
        }
    }

    public Process getProcess(Integer id) throws GetProcessException
    {
        String query = "SELECT *, process.id AS id, supplier.id AS id_supplier, process_type.id AS id_type, process_status.id AS id_process_status, employee.id AS id_employee, customer.num_customer AS id_customer " +
                "FROM process " +
                "LEFT JOIN supplier          ON process.id_supplier = supplier.id " +
                "JOIN process_type           ON process.id_process_type = process_type.id " +
                "JOIN process_status         ON process.id_process_status = process_status.id " +
                "LEFT JOIN employee          ON process.id_employee = employee.id " +
                "LEFT JOIN employee_status   ON employee.id_employee_status = employee_status.id " +
                "LEFT JOIN customer          ON process.num_customer = customer.num_customer " +
                "LEFT JOIN customer_status   ON customer.id_customer_status = customer_status.id " +
                "WHERE process.id = ?";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();

            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
            {
                return makeProcess(resultSet);
            }
            else
            {
                throw new GetProcessException("Process not found");
            }
        }
        catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            throw new GetProcessException();
        }
    }

    public ArrayList<Process> getAllProcesses() throws GetAllProcessesException
    {

        String query = "SELECT *, process.id AS id, supplier.id AS id_supplier, process_type.id AS id_type, process_status.id AS id_process_status, employee.id AS id_employee, customer.num_customer AS id_customer " +
                "FROM process " +
                "LEFT JOIN supplier          ON process.id_supplier = supplier.id " +
                "JOIN process_type              ON process.id_process_type = process_type.id " +
                "JOIN process_status    ON process.id_process_status = process_status.id " +
                "LEFT JOIN employee          ON process.id_employee = employee.id " +
                "LEFT JOIN employee_status   ON employee.id_employee_status = employee_status.id " +
                "LEFT JOIN customer          ON process.num_customer = customer.num_customer " +
                "LEFT JOIN customer_status   ON customer.id_customer_status = customer_status.id ";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();

            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Process> processes = new ArrayList<>();

            while (resultSet.next())
            {
                processes.add(makeProcess(resultSet));
            }

            return processes;
        }
        catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            throw new GetAllProcessesException();
        }
    }

    public ArrayList<Process> getProcessWithSpecificType(Integer id) throws GetProcessWithSpecificType {
        ArrayList<Process> processes = new ArrayList<>();

        String query = "SELECT *, process.id AS id, supplier.id AS id_supplier, process_type.id AS id_type, " +
                " process_status.id AS id_process_status, " +
                "employee.id AS id_employee, customer.num_customer AS id_customer FROM process  LEFT JOIN supplier " +
                "          ON process.id_supplier = supplier.id " +
                "                JOIN process_type           ON process.id_process_type = process_type.id " +
                "                JOIN process_status         ON process.id_process_status = process_status.id " +
                "                LEFT JOIN employee          ON process.id_employee = employee.id " +
                "                LEFT JOIN employee_status   ON employee.id_employee_status = employee_status.id " +
                "                LEFT JOIN customer          ON process.num_customer = customer.num_customer " +
                "                LEFT JOIN customer_status   ON customer.id_customer_status = customer_status.id " +
                "WHERE process_type.id = ?";

        try{
            Connection databaseConnexion = DatabaseConnexion.getInstance();
            PreparedStatement preparedStatement = databaseConnexion.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                processes.add(makeProcess(resultSet));
            }
        }catch (SQLException | DatabaseConnectionFailedException e ){
            System.err.println(e.getMessage());
        }

        return processes;
    }


    private Process makeProcess(ResultSet resultSet) throws SQLException
    {
        ProcessType processType = MakeProcessType.getProcessType(
                resultSet.getInt("id_type"),
                resultSet.getString("process_type.label")
        );

        ProcessStatus processStatus = MakeProcessStatus.getProcessStatus(
                resultSet.getInt("id_process_status"),
                resultSet.getString("process_status.label")
        );

        Supplier supplier = null;
        if(resultSet.getInt("id_supplier") != 0)
        {
            supplier = MakeSupplier.getSupplier(
                    resultSet.getInt("id_supplier"),
                    resultSet.getString("supplier.name")
            );
        }

        Employee employee = null;
        if(resultSet.getInt("id_employee") != 0)
        {
            EmployeeStatus employeeStatus = new EmployeeStatus(
                    resultSet.getInt("employee.id_employee_status"),
                    resultSet.getString("employee_status.label")
            );

            employee = MakeEmployee.getEmployee(
                    resultSet.getInt("id_employee"),
                    resultSet.getString("employee.last_name"),
                    resultSet.getString("employee.first_name"),
                    resultSet.getDate("employee.birth_date"),
                    employeeStatus
            );
        }

        Customer customer = null;
        if(resultSet.getInt("id_customer") != 0)
        {
            CustomerStatus customerStatus = new CustomerStatus(
                    resultSet.getInt("customer.id_customer_status"),
                    resultSet.getString("customer_status.label")
            );

            customer = MakeCustomer.getCustomer(
                    resultSet.getInt("id_customer"),
                    resultSet.getString("customer.last_name"),
                    resultSet.getString("customer.first_name"),
                    resultSet.getFloat("customer.credit_limit"),
                    resultSet.getString("customer.num_vat"),
                    customerStatus
            );
        }

        return MakeProcess.getProcess(
                resultSet.getInt("id"),
                resultSet.getString("label"),
                resultSet.getInt("number"),
                resultSet.getDate("creation_date"),
                supplier,
                processType,
                processStatus,
                employee,
                customer
        );
    }
}
