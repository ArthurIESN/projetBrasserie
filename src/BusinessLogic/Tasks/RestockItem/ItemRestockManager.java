package BusinessLogic.Tasks.RestockItem;

import Controller.Date.DateController;
import Controller.Event.EventController;
import Exceptions.Event.GetEventsBeforeDateException;
import Exceptions.Tasks.RestockItem.RestockQuantityAndDateException;
import Model.Customer.Customer;
import Model.Event.Event;
import Model.Item.Item;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class ItemRestockManager
{
    public ItemRestockManager()
    {
        // Constructor
    }

    public ArrayList<Item.RestockItem> calculateRestockQuantityAndDate(Item item, Date date) throws RestockQuantityAndDateException
    {
        if(item == null || date == null)
        {
            throw new RestockQuantityAndDateException("Item or date cannot be null");
        }

        ArrayList<Event> events;
        try
        {
            events = EventController.getEventsBeforeDate(date);
        }
        catch (GetEventsBeforeDateException e)
        {
            System.out.println("Error getting events before date: " + e.getMessage());
            throw new RestockQuantityAndDateException("Error getting events before a specific date");
        }

        Date currentDate = new Date();
        ArrayList<Item.RestockItem> restockItems = new ArrayList<>();

        if(events.isEmpty())
        {
            restockItems.add(calculateRestockItem(item, date, currentDate));
        }
        else
        {
            restockItems = calculateRestockItemWithEvents(item, date, currentDate, events);
        }

        return restockItems;
    }

    public float monthsImpact(int months)
    {
        if(months <= 0)
        {
            return 0.5f;
        }
        return 1 - (0.8f / months);
    }

    private Item.RestockItem calculateRestockItem(Item item, Date date, Date currentDate)
    {
        int restockQty = item.getRestockQuantity();
        System.out.println("Restock quantity: " + restockQty);
        int futureStock = (int)((restockQty + item.getForecastQuantity()) / (monthsImpact(DateController.getMonthsBetweenDates(currentDate, date)) + 1));
        System.out.println("Future stock: " + futureStock);
        int predictedQuantity = restockQty - futureStock > 0 ? restockQty : 0;

        return new Item.RestockItem(predictedQuantity, date, null);
    }

    private ArrayList<Item.RestockItem> calculateRestockItemWithEvents(Item item, Date date, Date currentDate, ArrayList<Event> events)
    {
        int currentMonth = 0;
        int totalPredictedQuantity = 0;
        ArrayList<Item.RestockItem> restockItems = new ArrayList<>();
        for(Event event : events)
        {
            float impact = EventController.getRealEventImpact(event.getImpact());
            int monthsBetween = DateController.getMonthsBetweenDates(currentDate, event.getStartDate());
            int monthsToProcess = monthsBetween - currentMonth + 1;
            currentMonth = monthsBetween;
            int futureStock = (int)((item.getCurrentQuantity() + item.getForecastQuantity() + totalPredictedQuantity) * monthsImpact(monthsToProcess));
            int additionalNeed = (int)Math.ceil(futureStock * impact);
            int predictedQuantity = additionalNeed > 0 ? Math.max(item.getRestockQuantity(), additionalNeed) : 0;
            totalPredictedQuantity += predictedQuantity;

            restockItems.add(new Item.RestockItem(totalPredictedQuantity, event.getStartDate(), event));
        }

        return restockItems;
    }
}
