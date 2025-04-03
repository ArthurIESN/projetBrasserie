package Exceptions.Search;

public class SearchPaymentException extends RuntimeException {
        private final String message;
        public SearchPaymentException()
        {
            this.message = "Error while searching for payment.";
        }
        public String getMessage()
        {
            return this.message;
        }
}
