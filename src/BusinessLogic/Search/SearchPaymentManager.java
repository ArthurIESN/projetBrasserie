package BusinessLogic.Search;

import DataAccess.Search.SearchPayment.SearchPaymentDBAccess;
import DataAccess.Search.SearchPayment.SearchPaymentDataAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Search.SearchPaymentException;
import Model.Payment;

import java.util.ArrayList;

public class SearchPaymentManager {

    public ArrayList<Payment> searchPayments(boolean isValidated, double minAmount, String year) throws DatabaseConnectionFailedException, SearchPaymentException {
        ArrayList<Payment> payments;

        payments = SearchPaymentDataAccess.searchPayment(isValidated, minAmount, year);

        return payments;
    }
}
