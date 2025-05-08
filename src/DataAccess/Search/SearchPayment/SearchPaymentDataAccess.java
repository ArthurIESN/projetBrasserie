package DataAccess.Search.SearchPayment;

import java.sql.Date;
import java.util.ArrayList;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Search.GetAllPaymentYearsException;
import Exceptions.Search.SearchPaymentException;

import Model.Payment.Payment;

public interface SearchPaymentDataAccess {
    ArrayList<Payment> searchPayment(String status, double minAmount, Date year) throws  SearchPaymentException;
    ArrayList<Integer> getAllPaymentYears() throws GetAllPaymentYearsException;
}
