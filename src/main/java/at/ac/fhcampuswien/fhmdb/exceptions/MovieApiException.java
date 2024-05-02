package at.ac.fhcampuswien.fhmdb.exceptions;

import java.io.IOException;

public class MovieApiException extends Exception {

    public MovieApiException() {
    }

    public MovieApiException(String message) {
        super(message);
    }

    public MovieApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieApiException(Throwable cause) {
        super(cause);
    }
}
