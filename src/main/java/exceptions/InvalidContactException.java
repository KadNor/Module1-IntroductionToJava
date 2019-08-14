package exceptions;

/**
 * Thrown whenever an invalid {@link models.Contact} is specified.
 */
public class InvalidContactException extends RuntimeException {

    public InvalidContactException() {
    }

    public InvalidContactException(final String message) {
        super(message);
    }

    public InvalidContactException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
