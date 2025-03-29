package DataAccess.Search.SearchPayment;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Search.GetMinMaxItemQuantityAndPriceException;

import java.util.ArrayList;

import Exceptions.Search.SearchPaymentException;
import Model.Payment;

public interface SearchPaymentDataAccess {
    ArrayList<Payment> searchPayment(boolean isValidated, double minAmount, String year) throws DatabaseConnectionFailedException, SearchPaymentException;
}
