package ba.unsa.etf.rpr;

import static org.junit.jupiter.api.Assertions.*;

class ChessPieceTest {

    @org.junit.jupiter.api.Test
    void numberStringCoordinate() {
        assertEquals("7", ChessPiece.numberStringCoordinate("A7"));
    }

    @org.junit.jupiter.api.Test
    void illegalConstructor() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Pawn("AB", ChessPiece.Color.BLACK)
        );
    }
}