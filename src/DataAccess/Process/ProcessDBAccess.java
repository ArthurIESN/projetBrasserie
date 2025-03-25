package DataAccess.Process;

import DataAccess.DatabaseConnexion;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.DataAccess.Process.DeleteProcessException;
import Exceptions.DataAccess.Process.GetAllProcessesException;
import Exceptions.DataAccess.Process.CreateProcessException;

import Exceptions.DataAccess.Process.GetProcessException;
import Exceptions.DataAccess.Process.UpdateProcessException;
import Model.Process;
import Model.Supplier;
import Model.Type;
import Model.ProcessStatus;
import Model.Employee;
import Model.EmployeeStatus;
import Model.Customer;
import Model.CustomerStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static java.sql.Types.INTEGER;





public class ProcessDBAccess
{
    private final Map<Integer, Supplier> supplierCache = new HashMap<>();
    private final Map<Integer, Type> typeCache = new HashMap<>();
    private final Map<Integer, ProcessStatus> processStatusCache = new HashMap<>();
    private final Map<Integer, Employee> employeeCache = new HashMap<>();
    private final Map<Integer, EmployeeStatus> employeeStatusCache = new HashMap<>();
    private final Map<Integer, Customer> customerCache = new HashMap<>();
    private final Map<Integer, CustomerStatus> customerStatusCache = new HashMap<>();

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

            if (resultSet.next())
            {
                return createProcessClass(resultSet);
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

    private Process createProcessClass(ResultSet resultSet)
    {
        try
        {
            Supplier supplier = supplierCache.getOrDefault(resultSet.getInt("id_supplier"),
                    new Supplier(
                            resultSet.getInt("id_supplier"),
                            resultSet.getString("name")));

            supplierCache.putIfAbsent(resultSet.getInt("id_supplier"), supplier);

            Type type = typeCache.getOrDefault(resultSet.getInt("id_type"),
                    new Type(
                            resultSet.getInt("id_type"),
                            resultSet.getString("label")));

            typeCache.putIfAbsent(resultSet.getInt("id_type"), type);

            ProcessStatus processStatus = processStatusCache.getOrDefault(resultSet.getInt("id_process_status"),
                    new ProcessStatus(
                            resultSet.getInt("id_process_status"),
                            resultSet.getString("label")));

            processStatusCache.putIfAbsent(resultSet.getInt("id_process_status"), processStatus);

            EmployeeStatus employeeStatus = employeeStatusCache.getOrDefault(resultSet.getInt("id_employee_status"),
                    new EmployeeStatus(
                            resultSet.getInt("id_employee_status"),
                            resultSet.getString("label")));

            employeeStatusCache.putIfAbsent(resultSet.getInt("id_employee_status"), employeeStatus);

            Employee employee = employeeCache.getOrDefault(resultSet.getInt("id_employee"),
                    new Employee(
                            resultSet.getInt("id_employee"),
                            resultSet.getString("name"),
                            resultSet.getString("first_name"),
                            resultSet.getDate("birth_date"),
                            employeeStatus));

            employeeCache.putIfAbsent(resultSet.getInt("id_employee"), employee);

            CustomerStatus customerStatus = customerStatusCache.getOrDefault(resultSet.getInt("id_customer_status"),
                    new CustomerStatus(
                            resultSet.getInt("id_customer_status"),
                            resultSet.getString("label")));

            customerStatusCache.putIfAbsent(resultSet.getInt("id_customer_status"), customerStatus);

            Customer customer = customerCache.getOrDefault(resultSet.getInt("id_customer"),
                    new Customer(resultSet.getInt("id_customer"),
                            resultSet.getString("last_name"),
                            resultSet.getString("first_name"),
                            resultSet.getFloat("credit_limit"),
                            resultSet.getString("num_vat"),
                            customerStatus));

            customerCache.putIfAbsent(resultSet.getInt("id_customer"), customer);

            return new Process(
                    resultSet.getInt("id"),
                    resultSet.getString("label"),
                    resultSet.getInt("number"),
                    supplier,
                    type,
                    processStatus,
                    employee,
                    customer
            );


        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
            //@todo : throw a custom exception
            return null;
        }
    }


}
