package pages;

// Custom Exception Class
public class CustomTestException extends RuntimeException {
    public CustomTestException(String message) {
        super(message);
    }

    public CustomTestException(String message, Throwable cause) {
        super(message, cause);
    }
}
