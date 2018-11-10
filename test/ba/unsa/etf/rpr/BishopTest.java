package ba.unsa.etf.rpr;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    @org.junit.jupiter.api.Test
    void moveDiagonal() {
        Bishop b = new Bishop("C8", ChessPiece.Color.BLACK);
        assertDoesNotThrow(
                () -> b.move("H3")
        );
    }

    @org.junit.jupiter.api.Test
    void moveHorizontally() {
        Bishop b = new Bishop("F1", ChessPiece.Color.BLACK);
        b.move("D3");
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move("G3"),
                "Illegal move"
        );
    }
}