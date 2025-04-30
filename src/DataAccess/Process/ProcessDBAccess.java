package DataAccess.Process;

import DataAccess.DataAccesUtils;
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.sql.Types.INTEGER;


public class ProcessDBAccess implements ProcessDataAccess
{
    public ProcessDBAccess()
    {
    }

    public void createProcess(Process process) throws CreateProcessException
    {
        String query = "INSERT INTO process (label, number, id_supplier, id_process_type, id_process_status, id_employee, num_customer) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

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

            if(process.getSupplier() == null)
            {
                statement.setNull(3, INTEGER);
            }
            else
            {
                statement.setInt(3, process.getSupplier().getId());
            }

            statement.setInt(4, process.getType().getId());
            statement.setInt(5, process.getProcessStatus().getId());

            if(process.getEmployee() == null)
            {
                statement.setNull(6, INTEGER);
            }
            else
            {
                statement.setInt(6, process.getEmployee().getId());
            }

            if(process.getCustomer() == null)
            {
                statement.setNull(7, INTEGER);
            }
            else
            {
                statement.setInt(7, process.getCustomer().getId());
            }

            statement.executeUpdate();
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
        else if(process.getId() == null)
        {
            throw new UpdateProcessException("ID cannot be null");
        }
        else if(process.getLabel().isEmpty()) //@todo : put a character limit (the same as the database) ?
        {
            throw new UpdateProcessException("Label cannot be empty");
        }
        else if(process.getNumber() <= 0)
        {
            throw new UpdateProcessException("Number must be greater or equal to 0");
        }
        else if(process.getType() == null)
        {
            throw new UpdateProcessException("Type cannot be null");
        }
        else if(process.getType().getId() == null)
        {
            throw new UpdateProcessException("Type ID cannot be null");
        }
        else if(process.getProcessStatus() == null)
        {
            throw new UpdateProcessException("Process status cannot be null");
        }
        else if(process.getProcessStatus().getId() == null)
        {
            throw new UpdateProcessException("Process status ID cannot be null");
        }

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();
            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            statement.setString(1, process.getLabel());
            statement.setInt(2, process.getNumber());

            if(process.getSupplier() == null || process.getSupplier().getId() == null)
            {
                statement.setNull(3, INTEGER);
            }
            else
            {
                statement.setInt(3, process.getSupplier().getId());
            }

            statement.setInt(4, process.getType().getId());
            statement.setInt(5, process.getProcessStatus().getId());

            if(process.getEmployee() == null || process.getEmployee().getId() == null)
            {
                statement.setNull(6, INTEGER);
            }
            else
            {
                statement.setInt(6, process.getEmployee().getId());
            }

            if(process.getCustomer() == null || process.getCustomer().getId() == null)
            {
                statement.setNull(7, INTEGER);
            }
            else
            {
                statement.setInt(7, process.getCustomer().getId());
            }

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


    public static Process makeProcess(ResultSet resultSet) throws SQLException
    {
        if(!DataAccesUtils.hasColumn(resultSet, "process.id")) return null;

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
