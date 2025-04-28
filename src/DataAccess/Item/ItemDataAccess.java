package DataAccess.Item;

import Exceptions.Item.GetAllItemsException;
import Model.Item.Item;

import java.util.ArrayList;

public interface ItemDataAccess
{
    ArrayList<Item> getAllItems() throws GetAllItemsException;

}
