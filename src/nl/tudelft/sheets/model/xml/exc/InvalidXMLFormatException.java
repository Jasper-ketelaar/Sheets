package nl.tudelft.sheets.model.xml.exc;

/**
 * Created by Jasper on 10/30/2015.
 */
public class InvalidXMLFormatException extends Exception {

    public InvalidXMLFormatException() {
    }

    public InvalidXMLFormatException(final String message) {
        super(message);
    }

    public InvalidXMLFormatException(final Throwable cause) {
        super(cause);
    }

    public InvalidXMLFormatException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
