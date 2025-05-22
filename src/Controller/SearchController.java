package Controller;


import BusinessLogic.Event.EventManager;
import BusinessLogic.Search.SearchDocumentWithEventManager;

import BusinessLogic.Payment.PaymentManager;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Event.GetEventsWithItemException;
import Exceptions.Search.*;
import Exceptions.Vat.UnkownVatCodeException;
import Exceptions.Vat.WrongVatCodeException;
import Model.Document.Document;
import Model.Event.Event;
import Model.Item.Item;
import Model.Payment.Payment;
import Model.Vat.Vat;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class SearchController {

    //@todo : refactor this in specific controllers

    private static final SearchDocumentWithEventManager searchDocumentWithEventManager = new SearchDocumentWithEventManager();
    private static final EventManager searchEventManager = new EventManager();

    // fonction qui récupères toutes les années des event (recherche par années des documents impliquant des events)
    public static List<Integer> getDatesEvents(Integer idEvent) throws DatabaseConnectionFailedException{
        return searchDocumentWithEventManager.getDatesEvents(idEvent);
    }

    public static ArrayList<Event> getEventsWithSpecificItem(int idItem) throws GetEventsWithItemException {
        return  searchEventManager.getEventsWithSpecificItem(idItem);
    }

    public static ArrayList<Integer> getQuantityItemWithSpecificEvent(int idEvent, int idItem) throws GetQuantityItemWithSpecificEventException {
        return searchDocumentWithEventManager.getQuantityItemWithSpecificEvent(idEvent, idItem);
    }

    public static ArrayList<Document> getDocumentsWithSpecificEvent(int idItem, int idEvent, int quantity, int year) throws GetDocumentWithSpecificEventException {
        return searchDocumentWithEventManager.getDocumentsWithSpecificEvent(idItem, idEvent, quantity, year);
    }

}