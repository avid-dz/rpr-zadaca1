package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import javax.swing.text.BadLocationException;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    // Is the board usable after isCheck
    void someLegalMoves() {
        Board b = new Board();
        boolean no = b.isCheck(ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> {
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "E4");
                    b.move(Bishop.class, ChessPiece.Color.WHITE, "A6");
                    b.move(Knight.class, ChessPiece.Color.WHITE, "C3");
                    b.move(King.class, ChessPiece.Color.WHITE, "E2");
                    b.move(King.class, ChessPiece.Color.WHITE, "E3");
                }
        );
    }

    @Test
        // The field already taken by the same color
    void takenBySameColor0() {
        Board b = new Board();
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move(Rook.class, ChessPiece.Color.WHITE, "A2")
        );
    }

    @Test
    // Pawn eats diagonally
    void pawnDiagonal() {
        Board b = new Board();
        assertDoesNotThrow(
            () -> {
                b.move(Pawn.class, ChessPiece.Color.WHITE, "E4");
                b.move(Pawn.class, ChessPiece.Color.WHITE, "E5");
                b.move(Pawn.class, ChessPiece.Color.WHITE, "E6");
                b.move(Pawn.class, ChessPiece.Color.WHITE, "D7");
                b.move(Pawn.class, ChessPiece.Color.WHITE, "C8");
            }
        );
    }

    @Test
    // Check by pawn
    void isCheck() {
        Board b = new Board();
        try {
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E4");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E5");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E6");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "D7");
        } catch(Exception e) {
            // Do nothing
        }
        assertTrue(b.isCheck(ChessPiece.Color.BLACK));
    }

    @Test
    // Will queen be moved by isCheck
    void isCheckUsable() {
        Board b = new Board();
        try {
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E4");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E5");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E6");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "D7");
            b.move(Queen.class, ChessPiece.Color.WHITE, "E2");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "F4");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "F5");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "F6");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E7");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "F8");
        } catch(Exception e) {
            // Do nothing
        }
        b.isCheck(ChessPiece.Color.BLACK);
        assertDoesNotThrow(
            () -> {
                b.move(Queen.class, ChessPiece.Color.WHITE, "D3");
            }
        );
    }

    @Test
    // No check
    void isCheck2() {
        Board b = new Board();
        try {
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E4");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E5");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E6");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "D7");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "C8");
            b.move(Queen.class, ChessPiece.Color.WHITE, "E2");
        } catch(Exception e) {
            // Do nothing
        }
        assertFalse(b.isCheck(ChessPiece.Color.BLACK));
    }

    @Test
    // Check by queen
    void isCheck3() {
        Board b = new Board();
        try {
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E4");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E5");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E6");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "D7");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "C8");
            b.move(Queen.class, ChessPiece.Color.WHITE, "E2");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "F4");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "F5");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "F6");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E7");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "F8");
        } catch(Exception e) {
            // Do nothing
        }
        assertTrue(b.isCheck(ChessPiece.Color.BLACK));
    }

    @Test
    // Queen, bishop and rook can't jump pieces
    void jumpPiece() {
        Board b = new Board();
        assertAll(
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move(Rook.class, ChessPiece.Color.BLACK, "H6")
                ),
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move(Bishop.class, ChessPiece.Color.BLACK, "H6")
                ),
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move(Queen.class, ChessPiece.Color.BLACK, "A5")
                )
        );
    }

    @Test
        // Rook cannot jump pieces
    void jumpPieceRook() {
        Board b = new Board();
        assertAll(
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move(Rook.class, ChessPiece.Color.BLACK, "A6")
                ),
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move(Rook.class, ChessPiece.Color.WHITE, "H3")
                ),
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move(Rook.class, ChessPiece.Color.WHITE, "A3")
                )
        );
    }

    @Test
        // Rook cannot jump pieces
    void jumpPieceRook1() {
        Board b = new Board();
        assertAll(
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move(Rook.class, ChessPiece.Color.BLACK, "A6")
                ),
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move(Rook.class, ChessPiece.Color.WHITE, "H3")
                ),
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move(Rook.class, ChessPiece.Color.WHITE, "A3")
                )
        );
    }


    // Same test with other move method

    @Test
    // Is the board usable after isCheck
    void someLegalMoves1() {
        Board b = new Board();
        boolean no = b.isCheck(ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> {
                    b.move("E2", "E4");
                    b.move("F1", "A6");
                    b.move("B1", "C3");
                    b.move("E1", "E2");
                    b.move("E2", "E3");
                }
        );
    }

    @Test
    // Pawn eats diagonally, check by queen
    void pawnDiagonal1() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move("E2", "E4");
                    b.move("E4", "E5");
                    b.move("E5", "E6");
                    b.move("E6", "D7");
                    b.move("D7", "C8");
                }
        );
    }

    @Test
        // Pawn cannot move diagonally if not eating
    void pawnDiagonal2() {
        Board b = new Board();
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move("B2", "C3")
        );
    }

    @Test
    // Check by pawn
    void isCheck1() {
        Board b = new Board();
        try {
            b.move("E2", "E4");
            b.move("E4", "E5");
            b.move("E5", "E6");
            b.move("E6", "D7");
        } catch(Exception e) {
            // Do nothing
        }
        assertTrue(b.isCheck(ChessPiece.Color.BLACK));
    }

    @Test
    // No check
    void isCheck12() {
        Board b = new Board();
        try {
            b.move("E2", "E4");
            b.move("E4", "E5");
            b.move("E5", "E6");
            b.move("E6", "D7");
            b.move("D7", "C8");
            b.move("D1", "E2");
        } catch(Exception e) {
            // Do nothing
        }
        assertFalse(b.isCheck(ChessPiece.Color.BLACK));
    }

    @Test
    // Check by queen
    void isCheck13() {
        Board b = new Board();
        try {
            b.move("E2", "E4");
            b.move("E4", "E5");
            b.move("E5", "E6");
            b.move("E6", "D7");
            b.move("D7", "C8");
            b.move("D1", "E2");
            b.move("F2", "F4");
            b.move("F4", "F5");
            b.move("F5", "F6");
            b.move("F6", "E7");
            b.move("E7", "F8");
        } catch(Exception e) {
            // Do nothing
        }
        assertTrue(b.isCheck(ChessPiece.Color.BLACK));
    }

    @Test
    // Queen, bishop and rook can't jump pieces
    void jumpPiece1() {
        Board b = new Board();
        assertAll(
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move("H8", "H6")
                ),
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move("F8", "H6")
                ),
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move("D8", "A5")
                )
        );
    }

    @Test
    // Check by queen
    void isCheckUsable1() {
        Board b = new Board();
        try {
            b.move("E2", "E4");
            b.move("E4", "E5");
            b.move("E5", "E6");
            b.move("E6", "D7");
            b.move("D7", "C8");
            b.move("D1", "E2");
        } catch(Exception e) {
            // Do nothing
        }
        b.isCheck(ChessPiece.Color.BLACK);
        assertDoesNotThrow(
                () -> {
                    b.move("E2", "D3");
                }
        );
    }

    @Test
        // Field taken by the same color
    void takenBySameColor() {
        Board b = new Board();
        assertAll(
                () -> assertDoesNotThrow(
                        () -> b.move("E2", "E4")
                ),
                () -> assertDoesNotThrow(
                        () -> b.move("D1", "E2")
                ),
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move("E2", "E4")
                )
        );
    }

    @Test
        // If pawn goes vertically, the destination field must be free
    void pawnVertically() {
        Board b = new Board();
        assertAll(
                () -> assertDoesNotThrow(
                        () -> b.move("B7", "B5")
                ),
                () -> assertDoesNotThrow(
                        () -> b.move("B5", "B4")
                ),
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move("B2", "B4")
                )
        );
    }

    @Test
        // A pawn cannot jump pieces
    void pawnJumping() {
        Board b = new Board();
        assertAll(
                () -> assertDoesNotThrow(
                        () -> b.move("B1", "C3")
                ),
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move("C2", "C4")
                )
        );
    }

    @Test
        // A rook cannot jump pieces
    void rookJumping() {
        Board b = new Board();
        b.move("H2", "H4");
        b.move("H1", "H3");
        b.move("G2", "G3");
        assertAll(
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move("H3", "B3")
                )
        );
    }

    @Test
        // A rook cannot jump pieces
    void rookJumping1() {
        Board b = new Board();
        assertAll(
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move("H1", "H3")
                )
        );
    }

    @Test
        // A rook cannot jump pieces
    void rookJumping2() {
        Board b = new Board();
        assertAll(
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move("H1", "H3")
                )
        );
    }

    @Test
        // A rook cannot jump pieces
    void rookJumping3() {
        Board b = new Board();
        b.move("A2", "A4");
        b.move("A1", "A3");
        b.move("B2", "B3");
        assertAll(
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move("A3", "C3")
                )
        );
    }

    @Test
        // A rook cannot jump pieces
    void rookJumping4() {
        Board b = new Board();
        b.move("A2", "A4");
        b.move("A1", "A3");
        b.move("C2", "C3");
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move(Rook.class, ChessPiece.Color.WHITE, "G3")
        );
    }

    @Test
        // A rook cannot jump pieces
    void rookJumping5() {
        Board b = new Board();
        b.move("H2", "H4");
        b.move("H1", "H3");
        b.move("F2", "F3");
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move(Rook.class, ChessPiece.Color.WHITE, "B3")
        );
    }

    @Test
        // A bishop cannot jump pieces
    void bishopJumping() {
        Board b = new Board();
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move("F1", "C4")
        );
    }

    @Test
        // A bishop cannot jump pieces
    void bishopJumping1() {
        Board b = new Board();
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move("C1", "F4")
        );
    }

    @Test
        // A bishop cannot jump pieces
    void bishopJumping2() {
        Board b = new Board();
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move("C8", "F5")
        );
    }

    @Test
        // A bishop cannot jump pieces
    void bishopJumping3() {
        Board b = new Board();
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move("F8", "B4")
        );
    }

    @Test
        // A bishop cannot jump pieces
    void bishopJumping4() {
        Board b = new Board();
        b.move("F7", "F5");
        b.move("G2", "G3");
        b.move("F1", "H3");
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move("H3", "E6")
        );
    }

    @Test
        // A bishop cannot jump pieces
    void bishopJumping5() {
        Board b = new Board();
        b.move("F2", "F4");
        b.move("G7", "G6");
        b.move("F8", "H6");
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move("H6", "E3")
        );
    }

    @Test
        // A bishop cannot jump pieces
    void bishopJumping6() {
        Board b = new Board();
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move(Bishop.class, ChessPiece.Color.WHITE, "E3")
        );
    }

    @Test
        // A bishop cannot jump pieces
    void bishopJumping7() {
        Board b = new Board();
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move(Bishop.class, ChessPiece.Color.BLACK, "D6")
        );
    }

    @Test
        // A bishop cannot jump pieces
    void bishopJumping8() {
        Board b = new Board();
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move(Bishop.class, ChessPiece.Color.WHITE, "C4")
        );
    }

    @Test
        // A rook cannot jump pieces
    void move2Exception() {
        Board b = new Board();
        assertThrows(
                IllegalArgumentException.class,
                () -> b.move("F5", "F6")
        );
    }

    @Test
        // Queen cannot jump pieces
    void queenJump() {
        Board b = new Board();
        assertAll(
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move(Queen.class, ChessPiece.Color.BLACK, "D5")
                ),
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move(Queen.class, ChessPiece.Color.WHITE, "D4")
                )
        );
    }

    @Test
        // Queen cannot jump pieces
    void queenJump1() {
        Board b = new Board();
        assertAll(
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move(Queen.class, ChessPiece.Color.BLACK, "G5")
                ),
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move(Queen.class, ChessPiece.Color.WHITE, "G4")
                )
        );
    }

    @Test
        // Queen cannot jump pieces
    void queenJump2() {
        Board b = new Board();
        assertAll(
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move(Queen.class, ChessPiece.Color.BLACK, "B6")
                ),
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move(Queen.class, ChessPiece.Color.WHITE, "B3")
                )
        );
    }

    @Test
        // Queen cannot jump pieces
    void queenJump3() {
        Board b = new Board();
        b.move("F2", "F3");
        b.move("D2", "D4");
        b.move("D1", "D3");
        b.move("D7", "D5");
        b.move("D8", "D6");
        b.move("F7", "F6");
        assertAll(
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move(Queen.class, ChessPiece.Color.BLACK, "H6")
                ),
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move(Queen.class, ChessPiece.Color.WHITE, "H3")
                )
        );
    }

    @Test
        // Queen cannot jump pieces
    void queenJump4() {
        Board b = new Board();
        b.move("B2", "B3");
        b.move("D2", "D4");
        b.move("D1", "D3");
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move(Queen.class, ChessPiece.Color.WHITE, "A3")
        );
    }

    @Test
        // Check by pawn
    void isCheck4() {
        Board b = new Board();
        try {
            b.move("E7", "E5");
            b.move("E5", "E4");
            b.move("E4", "E3");
            b.move("E3", "D2");
        } catch(Exception e) {
            // Do nothing
        }
        assertTrue(b.isCheck(ChessPiece.Color.WHITE));
    }

    @Test
        // Queen cannot jump pieces
    void queenJump5() {
        Board b = new Board();
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move(Queen.class, ChessPiece.Color.WHITE, "D4")
        );
    }

    @Test
        // Queen cannot jump pieces
    void queenJump6() {
        Board b = new Board();
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move(Queen.class, ChessPiece.Color.BLACK, "D4")
        );
    }

    @Test
        // Queen cannot jump pieces
    void queenJump7() {
        Board b = new Board();
        b.move("B2", "B3");
        b.move("D2", "D4");
        b.move("D1", "D3");
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move(Queen.class, ChessPiece.Color.WHITE, "A3")
        );
    }

    @Test
        // Queen cannot jump pieces
    void queenJump8() {
        Board b = new Board();
        b.move("F2", "F3");
        b.move("D2", "D4");
        b.move("D1", "D3");
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move(Queen.class, ChessPiece.Color.WHITE, "H3")
        );
    }

    @Test
        // Queen cannot jump pieces
    void queenJump9() {
        Board b = new Board();
        b.move("B2", "B3");
        b.move("D2", "D4");
        b.move("D1", "D3");
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move("D3", "A3")
        );
    }

    @Test
        // Queen cannot jump pieces
    void queenJump10() {
        Board b = new Board();
        b.move("F2", "F3");
        b.move("D2", "D4");
        b.move("D1", "D3");
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move("D3", "H3")
        );
    }

    @Test
        // Queen cannot jump pieces
    void queenJump11() {
        Board b = new Board();
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move("D1", "A4")
        );
    }

    @Test
        // Queen cannot jump pieces
    void queenJump12() {
        Board b = new Board();
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move("D1", "G4")
        );
    }

    @Test
        // Queen cannot jump pieces
    void queenJump13() {
        Board b = new Board();
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move("D8", "G5")
        );
    }

    @Test
        // Queen cannot jump pieces
    void queenJump14() {
        Board b = new Board();
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move("D8", "D4")
        );
    }

    @Test
        // Queen cannot jump pieces
    void queenJump15() {
        Board b = new Board();
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move("D1", "D4")
        );
    }

    @Test
        //  Pawn cannot jump pieces
    void pawnJumping1() {
        Board b = new Board();
        b.move("E2", "E4");
        b.move("E4", "E5");
        b.move("E5", "E6");
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move(Pawn.class, ChessPiece.Color.BLACK, "E5")
        );
    }

    @Test
        //  Pawn can only move vertically if the destination field is empty
    void pawnVertically1() {
        Board b = new Board();
        b.move("E2", "E4");
        b.move("E7", "E5");
        assertThrows(
                IllegalChessMoveException.class,
                () -> b.move(Pawn.class, ChessPiece.Color.WHITE, "E5")
        );
    }
}