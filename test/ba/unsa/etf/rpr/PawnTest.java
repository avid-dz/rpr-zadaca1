package ba.unsa.etf.rpr;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    @org.junit.jupiter.api.Test
    void move1() {
        Pawn p = new Pawn("E2", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> p.move("E4")
        );
    }

    @org.junit.jupiter.api.Test
    void illegalMove() {
        Pawn p = new Pawn("B2", ChessPiece.Color.WHITE);
        assertThrows(
                IllegalChessMoveException.class,
                () -> p.move("D4"),
                "Illegal move"
        );
    }
}