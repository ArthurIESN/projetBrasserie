package DataAccess.Process;

import DataAccess.DataAccessUtils;
import DataAccess.DatabaseConnexion;

import DataAccess.Employee.EmployeeDBAccess;
import DataAccess.ProcessStatus.ProcessStatusDBAccess;
import DataAccess.ProcessType.ProcessTypeDBAccess;
import DataAccess.Supplier.SupplierDBAccess;
import DataAccess.Customer.CustomerDBAccess;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Process.*;

import Model.Process.MakeProcess;
import Model.Process.Process;

import java.sql.*;
import java.util.ArrayList;

import static java.sql.Types.INTEGER;


public class ProcessDBAccess implements ProcessDataAccess
{
    public ProcessDBAccess()
    {
    }

    public int createProcess(Process process) throws CreateProcessException
    {
        String query = "INSERT INTO process (label, number, id_supplier, id_process_type, id_process_status, id_employee, num_customer) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        if(process == null)
        {
            throw new CreateProcessException("Process cannot be null");
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
            PreparedStatement statement = databaseConnexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, process.getLabel());
            statement.setInt(2, process.getNumber());
            statement.setObject(3, process.getSupplier() == null ? null : process.getSupplier().getId(), INTEGER);
            statement.setInt(4, process.getType().getId());
            statement.setInt(5, process.getProcessStatus().getId());
            statement.setObject(6, process.getEmployee() == null ? null : process.getEmployee().getId(), INTEGER);
            statement.setObject(7, process.getCustomer() == null ? null : process.getCustomer().getId(), INTEGER);


            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0)
            {
                throw new CreateProcessException("Failed to create process, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys())
            {
                if (generatedKeys.next())
                {
                    return generatedKeys.getInt(1);
                } else {
                    throw new CreateProcessException("Failed to create process, no ID obtained.");
                }
            }
        }
        catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            throw new CreateProcessException();
        }


    }

    public void updateProcess(Process process) throws UpdateProcessException
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

        if(process == null)
        {
            throw new UpdateProcessException("Process cannot be null");
        }
        else if(process.getType() == null)
        {
            throw new UpdateProcessException("Type cannot be null");
        }
        else if(process.getProcessStatus() == null)
        {
            throw new UpdateProcessException("Process status cannot be null");
        }

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();
            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            statement.setString(1, process.getLabel());
            statement.setInt(2, process.getNumber());
            statement.setObject(3, process.getSupplier() == null ? null : process.getSupplier().getId(), INTEGER);
            statement.setInt(4, process.getType().getId());
            statement.setInt(5, process.getProcessStatus().getId());
            statement.setObject(6, process.getEmployee() == null ? null : process.getEmployee().getId(), INTEGER);
            statement.setObject(7, process.getCustomer() == null ? null : process.getCustomer().getId(), INTEGER);
            statement.setInt(8, process.getId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0)
            {
                throw new UpdateProcessException("Invalid process ID: " + process.getId());
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

            if (rowsAffected == 0)
            {
                throw new DeleteProcessException("Invalid process ID: " + id);
            }
        }
        catch (SQLException  e)
        {
            System.err.println(e.getMessage());

            if(DataAccessUtils.isASQLForeignKeyConstraintFails(e.getErrorCode()))
            {
                throw new DeleteProcessException("Cannot delete process. This process is linked to an other entity");
            }

            throw new DeleteProcessException("Error while deleting process");
        }
        catch (DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            throw new DeleteProcessException();
        }
    }

    public Process getProcess(Integer id) throws GetProcessException
    {
        String query = "SELECT * " +
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

        String query = "SELECT * " +
                "FROM process " +
                "LEFT JOIN supplier          ON process.id_supplier = supplier.id " +
                "JOIN process_type              ON process.id_process_type = process_type.id " +
                "JOIN process_status    ON process.id_process_status = process_status.id " +
                "LEFT JOIN employee          ON process.id_employee = employee.id " +
                "LEFT JOIN employee_status   ON employee.id_employee_status = employee_status.id " +
                "LEFT JOIN customer          ON process.num_customer = customer.num_customer " +
                "LEFT JOIN customer_status   ON customer.id_customer_status = customer_status.id " +
                "ORDER BY process.id";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();

            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Process> processes = new ArrayList<>();

            while (resultSet.next())
            {
                Process process = makeProcess(resultSet);

                if(process != null)
                {
                    processes.add(process);
                }
            }

            return processes;
        }
        catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            throw new GetAllProcessesException();
        }
    }

    public ArrayList<Process> getProcessWithSpecificType(Integer id) throws GetProcessWithSpecificType
    {
        System.out.println("getProcessWithSpecificType called with id: " + id);

        String query = "SELECT * FROM process  " +
                "LEFT JOIN supplier ON process.id_supplier = supplier.id " +
                "JOIN process_type           ON process.id_process_type = process_type.id " +
                "JOIN process_status         ON process.id_process_status = process_status.id " +
                "LEFT JOIN employee          ON process.id_employee = employee.id " +
                "LEFT JOIN employee_status   ON employee.id_employee_status = employee_status.id " +
                "LEFT JOIN customer          ON process.num_customer = customer.num_customer " +
                "LEFT JOIN customer_status   ON customer.id_customer_status = customer_status.id " +
                "WHERE process_type.id = ?";

        try{
            Connection databaseConnexion = DatabaseConnexion.getInstance();
            PreparedStatement preparedStatement = databaseConnexion.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Process> processes = new ArrayList<>();

            while (resultSet.next())
            {
                Process process = makeProcess(resultSet);

                if(process != null)
                {
                    processes.add(process);
                }
            }

            return processes;
        }
        catch (SQLException | DatabaseConnectionFailedException e )
        {
            System.err.println(e.getMessage());
            throw new GetProcessWithSpecificType();
        }
    }


    public static Process makeProcess(ResultSet resultSet) throws SQLException
    {
        if(!DataAccessUtils.hasColumn(resultSet, "process.id")) return null;

        return MakeProcess.getProcess(
                resultSet.getInt("process.id"),
                resultSet.getString("process.label"),
                resultSet.getInt("process.number"),
                SupplierDBAccess.makeSupplier(resultSet),
                ProcessTypeDBAccess.makeProcessType(resultSet),
                ProcessStatusDBAccess.makeProcessStatus(resultSet),
                EmployeeDBAccess.makeEmployee(resultSet),
                CustomerDBAccess.makeCustomer(resultSet)
        );
    }
}
