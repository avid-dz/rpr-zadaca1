package ba.unsa.etf.rpr;

import static org.junit.jupiter.api.Assertions.*;

class IllegalChessMoveExceptionTest {

    @org.junit.jupiter.api.Test
    void constructor() {
        assertDoesNotThrow(
                () -> new IllegalChessMoveException("Illegal move")
        );
    }
}