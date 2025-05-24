package Controller.BusinessTasks;

import BusinessLogic.Tasks.RestockItem.ItemRestockManager;

import Controller.AppController;
import Exceptions.Access.UnauthorizedAccessException;
import Exceptions.Tasks.RestockItem.RestockQuantityAndDateException;
import Model.EmployeeStatus.EmployeeStatus;
import Model.Item.Item;
import java.util.ArrayList;
import java.util.Date;

public class RestockItemController
{
    private static final ItemRestockManager itemRestockManager = new ItemRestockManager();

    public static ArrayList<Item.RestockItem> calculateRestockQuantityAndDate(Item item, Date date) throws RestockQuantityAndDateException, UnauthorizedAccessException
    {
        if(!AppController.hasAccess(EmployeeStatus.Status.Manager))
        {
            throw new UnauthorizedAccessException("You don't have the right to restock this item");
        }

        return itemRestockManager.calculateRestockQuantityAndDate(item, date);
    }
}
