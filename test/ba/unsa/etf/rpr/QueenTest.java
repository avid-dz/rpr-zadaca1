package ba.unsa.etf.rpr;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    @org.junit.jupiter.api.Test
    void someLegalMoves() {
        Queen q = new Queen("D8", ChessPiece.Color.BLACK);
        assertDoesNotThrow(
                () -> {
                    q.move("D5");
                    q.move("D6");
                    q.move("F4");
                    q.move("E3");
                }
        );
    }

    @org.junit.jupiter.api.Test
    void illegalMove() {
        Queen q = new Queen("D1", ChessPiece.Color.WHITE);
        try {
            q.move("D5");
            q.move("D6");
            q.move("F4");
        }
        catch(Exception e) {
        }
        assertThrows(
                IllegalChessMoveException.class,
                () -> q.move("D5"),
                "Illegal move"
        );
    }
}