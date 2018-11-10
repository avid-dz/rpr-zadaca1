package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IllegalChessMoveExceptionTest {

    @Test
    void constructor() {
        assertDoesNotThrow(
                () -> new IllegalChessMoveException("Illegal move")
        );
    }
}