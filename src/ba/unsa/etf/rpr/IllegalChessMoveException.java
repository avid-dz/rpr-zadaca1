package ba.unsa.etf.rpr;

public class IllegalChessMoveException extends RuntimeException {

    public IllegalChessMoveException(String porukaIzuzetka) {
        super(porukaIzuzetka);
    }
}
