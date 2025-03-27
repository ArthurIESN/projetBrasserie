package DataAccess.Process;

import DataAccess.DatabaseConnexion;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.DataAccess.Process.DeleteProcessException;
import Exceptions.DataAccess.Process.GetAllProcessesException;
import Exceptions.DataAccess.Process.CreateProcessException;

import Exceptions.DataAccess.Process.GetProcessException;
import Exceptions.DataAccess.Process.UpdateProcessException;
import Model.Process.MakeProcess;
import Model.Process.Process;
import Model.Supplier.Supplier;
import Model.Type;
import Model.ProcessStatus.ProcessStatus;
import Model.Employee.Employee;
import Model.EmployeeStatus.EmployeeStatus;
import Model.Customer.Customer;
import Model.CustomerStatus.CustomerStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static java.sql.Types.INTEGER;





public class ProcessDBAccess implements ProcessDataAccess
{
    public ProcessDBAccess()
    {
    }

    public void createProcess(String label, Integer number, Integer supplierId, Integer typeId, Integer processStatusId, Integer employeeId, Integer customerId) throws DatabaseConnectionFailedException, CreateProcessException
    {
        String query = "INSERT INTO process (label, number, id_supplier, id_type, id_process_status, id_employee, num_customer) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        if(label.isEmpty()) //@todo : put a character limit (the same as the database) ?
        {
            throw new CreateProcessException("Label cannot be empty");
        }
        else if(number <= 0)
        {
            throw new CreateProcessException("Number cannot be 0");
        }
        else if(typeId == null)
        {
            throw new CreateProcessException("Type cannot be null");
        }
        else if(processStatusId == null)
        {
            throw new CreateProcessException("Process status cannot be null");
        }

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance().getConnection();
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

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
            throw new CreateProcessException();
        }


    }

    public void updateProcess(Integer id, String label, Integer number, Integer supplierId, Integer typeId, Integer processStatusId, Integer employeeId, Integer customerId) throws DatabaseConnectionFailedException, UpdateProcessException
    {
        String query = "UPDATE process SET " +
                "label = ?, " +
                "number = ?, " +
                "id_supplier = ?, " +
                "id_type = ?, " +
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
            Connection databaseConnexion = DatabaseConnexion.getInstance().getConnection();
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
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
            throw new UpdateProcessException();
        }
    }

    public void deleteProcess(Integer id) throws DatabaseConnectionFailedException, DeleteProcessException
    {
        String query = "DELETE FROM process WHERE id = ?";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance().getConnection();
            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                throw new DeleteProcessException("Invalid process ID: " + id);
            }
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
            throw new DeleteProcessException();
        }
    }

    public Process getProcess(Integer id) throws DatabaseConnectionFailedException, GetProcessException
    {
        String query = "SELECT *, process.id AS id, supplier.id AS id_supplier, type.id AS id_type, process_status.id AS id_process_status, employee.id AS id_employee, customer.num_customer AS id_customer " +
                "FROM process " +
                "LEFT JOIN supplier          ON process.id_supplier = supplier.id " +
                "JOIN type              ON process.id_type = type.id " +
                "JOIN process_status    ON process.id_process_status = process_status.id " +
                "LEFT JOIN employee          ON process.id_employee = employee.id " +
                "LEFT JOIN employee_status   ON employee.id_employee_status = employee_status.id " +
                "LEFT JOIN customer          ON process.num_customer = customer.num_customer " +
                "LEFT JOIN customer_status   ON customer.id_customer_status = customer_status.id " +
                "WHERE process.id = ?";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance().getConnection();

            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                /*
                return MakeProcess.getProcess(
                        resultSet.getInt("id"),
                        resultSet.getString("label"),
                        resultSet.getInt("number"),
                        resultSet.getInt("id_supplier"),
                        resultSet.getInt("id_type"),
                        resultSet.getInt("id_process_status"),
                        resultSet.getInt("id_employee"),
                        resultSet.getInt("id_customer") */

                return null;
            }
            else
            {
                throw new GetProcessException("Process not found");
            }
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
            throw new GetProcessException();
        }
    }

    public ArrayList<Process> getAllProcesses() throws DatabaseConnectionFailedException, GetAllProcessesException
    {

        String query = "SELECT *, process.id AS id, supplier.id AS id_supplier, type.id AS id_type, process_status.id AS id_process_status, employee.id AS id_employee, customer.num_customer AS id_customer " +
                "FROM process " +
                "LEFT JOIN supplier          ON process.id_supplier = supplier.id " +
                "JOIN type              ON process.id_type = type.id " +
                "JOIN process_status    ON process.id_process_status = process_status.id " +
                "LEFT JOIN employee          ON process.id_employee = employee.id " +
                "LEFT JOIN employee_status   ON employee.id_employee_status = employee_status.id " +
                "LEFT JOIN customer          ON process.num_customer = customer.num_customer " +
                "LEFT JOIN customer_status   ON customer.id_customer_status = customer_status.id ";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance().getConnection();

            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Process> processes = new ArrayList<>();

            while (resultSet.next())
            {
                Process process = createProcessClass(resultSet);
                processes.add(process);
            }

            return processes;
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
            throw new GetAllProcessesException();
        }
    }
}
