package Controller.BusinessTasks;

import BusinessLogic.Item.ItemManager;
import BusinessLogic.Tasks.RestockItem.CustomerOrderManager;

import Controller.AppController;
import Controller.Document.DocumentController;
import Controller.DocumentDetails.DocumentDetailsController;
import Controller.DocumentStatus.DocumentStatusController;
import Controller.Item.ItemController;
import Controller.Process.ProcessController;
import Controller.ProcessStatus.ProcessStatusController;
import Controller.ProcessType.ProcessTypeController;
import Exceptions.Access.UnauthorizedAccessException;
import Exceptions.Document.CreateDocumentException;
import Exceptions.Document.DocumentException;
import Exceptions.DocumentStatus.GetDocumentStatusException;
import Exceptions.Item.ItemException;
import Exceptions.Item.UpdateItemException;
import Exceptions.Process.CreateProcessException;
import Exceptions.Process.ProcessException;
import Exceptions.ProcessStatus.GetProcessStatusException;
import Exceptions.ProcessType.GetProcessTypeException;
import Exceptions.Tasks.RestockItem.CustomerOrder.ExecuteOrderException;
import Exceptions.DocumentDetails.DocumentDetailsException;
import Model.Customer.Customer;
import Model.Document.Document;
import Model.DocumentDetails.DocumentDetails;
import Model.DocumentStatus.DocumentStatus;
import Model.Employee.Employee;
import Model.EmployeeStatus.EmployeeStatus;
import Model.Item.Item;
import Model.Locality.Locality;
import Model.Process.Process;
import Model.ProcessStatus.ProcessStatus;
import Model.ProcessType.ProcessType;
import Utils.Utils;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class CustomerOrderController
{
    private static final CustomerOrderManager customerOrderManager = new CustomerOrderManager();

    public static float getCustomerDepositMinimumAmount(Customer customer, float totalPrice)
    {
        return customerOrderManager.customerDepositMinimumAmount(customer, totalPrice);
    }

    public static void executeOrder(HashMap<Item, Integer> items, Customer customer, float[] values, float deposit, Date desiredDeliveryDate) throws UnauthorizedAccessException, ExecuteOrderException
    {
        if(!AppController.hasAccess(EmployeeStatus.Status.Manager))
        {
            System.out.println("Access Denied: You do not have permission to execute this order.");
            throw new UnauthorizedAccessException("Access Denied: You do not have permission to execute this order.");
        }

        if(deposit < customerOrderManager.customerDepositMinimumAmount(customer, values[3]))
        {
            System.out.println("Deposit amount is less than the minimum required.");
            throw new ExecuteOrderException("Deposit amount is less than the minimum required.");
        }

        if(!ItemController.enoughItemQuantity(items))
        {
            System.out.println("Not enough item quantity.");
            throw new ExecuteOrderException("Not enough item quantity.");
        }

        ProcessType processType;
        ProcessStatus processStatus;
        DocumentStatus documentStatus;
        Employee employee = AppController.getCurrentConnectedEmployee();


        Date currentDate = new Date();
        LocalDate localDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate deliveryLocalDate = localDate.plusDays(7);
        Date deliveryDate = Date.from(deliveryLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        try
           {
               processType = ProcessTypeController.getProcessType(5);
               processStatus = ProcessStatusController.getProcessStatus(1);
                documentStatus = DocumentStatusController.getDocumentStatus(1);
           } catch (GetProcessTypeException | GetProcessStatusException | GetDocumentStatusException e)
           {
               System.out.println("Error " + e.getMessage());
               throw new ExecuteOrderException("Error while getting information for your order");
           }

        Process process;

        try
        {
             process = new Process(10, "AUTO_CUSTOMER_ORDER_PROCESS", 1010, null, processType, processStatus, employee, customer);
        }
        catch (ProcessException e)
        {
          System.out.println("Error while creating process: " + e.getMessage());
            throw new ExecuteOrderException("Error while creating process");
        }


        Document document;
        try
        {
             document = new Document(10, "AUTO_CUSTOMER_ORDER_DOCUMENT",
                    currentDate, null, 0.f, "",
                    false, deliveryDate, deposit > 0,
                    deposit, desiredDeliveryDate,  values[3], values[0], values[3] - values[0], null, null, process, documentStatus);
        }
        catch (DocumentException e)
        {
            System.out.println("Error while creating document: " + e.getMessage());
            throw new ExecuteOrderException("Error while creating document");
        }


        ArrayList<DocumentDetails> itemDocumentDetails = new ArrayList<>();

        for (HashMap.Entry<Item, Integer> entry : items.entrySet())
        {
            Item item = entry.getKey();
            int quantity = entry.getValue();

            try
            {
                DocumentDetails documentDetails = new DocumentDetails(10, "CUSTOMER ORDER", quantity, null, item.getPrice(), document, item);
                itemDocumentDetails.add(documentDetails);

                item.setCurrentQuantity(item.getCurrentQuantity() - quantity);
            }
            catch (DocumentDetailsException | ItemException e)
            {
                System.out.println("Error while creating document details: " + e.getMessage());
                throw new ExecuteOrderException("Error while creating document details");
            }

            try
            {
                ItemController.updateItem(item);
            }
            catch (UpdateItemException e)
            {
                System.out.println("Error while updating item quantity: " + e.getMessage());
                throw new ExecuteOrderException("Error while updating item quantity");
            }
        }

        try
        {
            process.setId(ProcessController.createProcess(process));

            try
            {
                document.setId(DocumentController.createDocument(document));
            }
            catch (DocumentException e)
            {
                System.out.println("Error while creating document: " + e.getMessage());
                throw new ExecuteOrderException("Error while creating document");
            }

            for (DocumentDetails documentDetails : itemDocumentDetails)
            {
                DocumentDetailsController.createDocumentDetails(documentDetails);
            }
        }
        catch (CreateProcessException | CreateDocumentException | ProcessException e)
        {
            System.out.println("Error while creating : " + e.getMessage());
            throw new ExecuteOrderException("Error while creating");
        }
    }
}
