package DataAccess.Search.SearchPayment;
import DataAccess.Search.SearchPayment.SearchPaymentDataAccess;

import Model.Packaging.Packaging;
import Model.Payment;

import java.util.HashMap;
import java.util.Map;

public class SearchPaymentDBAccess {
    private final Map<Integer, Packaging> packagingCache = new HashMap<>();

    public SearchPaymentDBAccess() {
    }
/*/
    public ArrayList<Payment> searchPayment(boolean isValidated, double minAmount, String year) throws DatabaseConnectionFailedException, SearchPaymentException {
        String query =
        return payments;
    }

 */
}
