package at.ac.fhcampuswien.fhmdb.exceptions;

public class DatabaseException extends Exception {
    public DatabaseException(){}

    public DatabaseException(String massage){
        super(massage);
    }

    public DatabaseException(Throwable cause){
        super(cause);
    }

    public DatabaseException(String massage, Throwable cause){
        super(massage, cause);
    }
}
