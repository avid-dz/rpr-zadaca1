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

    @org.junit.jupiter.api.Test
    void illegalMove1() {
        Pawn p = new Pawn("C2", ChessPiece.Color.WHITE);
        assertThrows(
                IllegalChessMoveException.class,
                () -> p.move("E2"),
                "Illegal move"
        );
    }

    @org.junit.jupiter.api.Test
    void illegalMove2() {
        Pawn p = new Pawn("C2", ChessPiece.Color.WHITE);
        assertThrows(
                IllegalChessMoveException.class,
                () -> p.move("C5"),
                "Illegal move"
        );
    }

    @org.junit.jupiter.api.Test
    void illegalMove3() {
        Pawn p = new Pawn("C2", ChessPiece.Color.WHITE);
        p.move("C4");
        assertThrows(
                IllegalChessMoveException.class,
                () -> p.move("C6"),
                "Illegal move"
        );
    }

    @org.junit.jupiter.api.Test
    void illegalMove4() {
        Pawn p = new Pawn("A7", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalChessMoveException.class,
                () -> p.move("B5"),
                "Illegal move"
        );
    }

    @org.junit.jupiter.api.Test
    void moveBack1() {
        Pawn p = new Pawn("A7", ChessPiece.Color.BLACK);
        p.move("A5");
        assertThrows(
                IllegalChessMoveException.class,
                () -> p.move("A6"),
                "Illegal move"
        );
    }

    @org.junit.jupiter.api.Test
    void moveBack2() {
        Pawn p = new Pawn("H2", ChessPiece.Color.WHITE);
        p.move("H3");
        assertThrows(
                IllegalChessMoveException.class,
                () -> p.move("H2"),
                "Illegal move"
        );
    }
}