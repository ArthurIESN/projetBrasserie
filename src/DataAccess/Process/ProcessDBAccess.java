package DataAccess.Process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DataAccess.DatabaseConnexion;

import Model.Process;
import Model.Supplier;
import Model.Type;
import Model.ProcessStatus;
import Model.Employee;
import Model.EmployeeStatus;
import Model.Customer;
import Model.CustomerStatus;




public class ProcessDBAccess
{
    public ProcessDBAccess()
    {
    }

    public void createProcess(String label, int number, Supplier supplier, Type type, ProcessStatus processStatus, Employee employee, Customer customer)
    {

    }

    public void updateProcess(Integer id, String label, int number, Supplier supplier, Type type, ProcessStatus processStatus, Employee employee, Customer customer)
    {
    }

    public void deleteProcess(Integer id)
    {
    }

    public Process getProcess(Integer id)
    {
        return null;
    }

    public ArrayList<Process> getAllProcess()
    {
        String sql = "SELECT *, process.id AS id, supplier.id AS id_supplier, type.id AS id_type, process_status.id AS id_process_status, employee.id AS id_employee, customer.id AS id_customer " +
                "JOIN supplier ON process.id_supplier = supplier.id " +
                "JOIN type ON process.id_type = type.id " +
                "JOIN process_status ON process.id_process_status = process_status.id " +
                "JOIN employee ON process.id_employee = employee.id " +
                "JOIN customer ON process.id_customer = customer.id "
                ;

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance().getConnection();

            PreparedStatement statement = databaseConnexion.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Process> processes = new ArrayList<>();

            while (resultSet.next())
            {
                Process process = new Process(
                        resultSet.getInt("id"),
                        resultSet.getString("label"),
                        resultSet.getInt("number"),
                        new Supplier(
                                resultSet.getInt("id_supplier"),
                                resultSet.getString("name")),
                        new Type(
                                resultSet.getInt("id_type"),
                                resultSet.getString("label")),
                        new ProcessStatus(
                                resultSet.getInt("id_process_status"),
                                resultSet.getString("label")),
                        new Employee(
                                resultSet.getInt("id_employee"),
                                resultSet.getString("name"),
                                resultSet.getString("first_name"),
                                resultSet.getDate("birth_date"),
                                new EmployeeStatus(
                                        resultSet.getInt("id_employee_status"),
                                        resultSet.getString("label")
                                )
                        ),
                        new Customer(
                                resultSet.getInt("id_customer"),
                                resultSet.getString("last_name"),
                                resultSet.getString("first_name"),
                                resultSet.getFloat("credit_limit"),
                                resultSet.getString("num_vat"),
                                new CustomerStatus(
                                        resultSet.getInt("id_customer_status"),
                                        resultSet.getString("label")
                                )
                        )
                );

                processes.add(process);
            }

            return processes;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


}
