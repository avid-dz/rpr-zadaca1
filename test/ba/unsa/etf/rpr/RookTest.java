package ba.unsa.etf.rpr;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {

    @org.junit.jupiter.api.Test
    void moveBack() {
        Rook r = new Rook("G5", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> r.move("G1")
        );
    }

    @org.junit.jupiter.api.Test
    void moveDiagonally() {
        Rook r = new Rook("H1", ChessPiece.Color.WHITE);
        assertThrows(
                IllegalChessMoveException.class,
                () -> r.move("A8"),
                "Illegal move"
        );
    }
}