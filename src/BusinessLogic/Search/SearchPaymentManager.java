package BusinessLogic.Search;

import DataAccess.Search.SearchPayment.SearchPaymentDataAccess;
import DataAccess.Search.SearchPayment.SearchPaymentDBAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Search.GetAllPaymentYearsException;
import Exceptions.Search.SearchPaymentException;
import Model.Payment.Payment;

import java.sql.Date;
import java.util.ArrayList;

public class SearchPaymentManager {

    private final SearchPaymentDataAccess searchPaymentDataAccess;

    public SearchPaymentManager() {
        searchPaymentDataAccess = new SearchPaymentDBAccess();
    }

    public ArrayList<Payment> searchPayments(String status, double minAmount, Date year) throws SearchPaymentException {
        ArrayList<Payment> Payments;

        Payments = searchPaymentDataAccess.searchPayment(status, minAmount, year);

        return Payments;
    }

    public ArrayList<Integer> getAllPaymentYears() throws GetAllPaymentYearsException {
        return searchPaymentDataAccess.getAllPaymentYears();
    }
}
