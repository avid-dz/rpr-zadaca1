package ba.unsa.etf.rpr;

import java.util.ArrayList;

public class Board {

    private ArrayList<ChessPiece> piecesList;

    public Board() {
        piecesList = new ArrayList<>();
        piecesList.add(new Rook("A1", ChessPiece.Color.WHITE));
        piecesList.add(new Knight("B1", ChessPiece.Color.WHITE));
        piecesList.add(new Bishop("C1", ChessPiece.Color.WHITE));
        piecesList.add(new Queen("D1", ChessPiece.Color.WHITE));
        piecesList.add(new King("E1", ChessPiece.Color.WHITE));
        piecesList.add(new Bishop("F1", ChessPiece.Color.WHITE));
        piecesList.add(new Knight("G1", ChessPiece.Color.WHITE));
        piecesList.add(new Rook("H1", ChessPiece.Color.WHITE));
        piecesList.add(new Pawn("A2", ChessPiece.Color.WHITE));
        piecesList.add(new Pawn("B2", ChessPiece.Color.WHITE));
        piecesList.add(new Pawn("C2", ChessPiece.Color.WHITE));
        piecesList.add(new Pawn("D2", ChessPiece.Color.WHITE));
        piecesList.add(new Pawn("E2", ChessPiece.Color.WHITE));
        piecesList.add(new Pawn("F2", ChessPiece.Color.WHITE));
        piecesList.add(new Pawn("G2", ChessPiece.Color.WHITE));
        piecesList.add(new Pawn("H2", ChessPiece.Color.WHITE));
        piecesList.add(new Rook("A8", ChessPiece.Color.BLACK));
        piecesList.add(new Knight("B8", ChessPiece.Color.BLACK));
        piecesList.add(new Bishop("C8", ChessPiece.Color.BLACK));
        piecesList.add(new Queen("D8", ChessPiece.Color.BLACK));
        piecesList.add(new King("E8", ChessPiece.Color.BLACK));
        piecesList.add(new Bishop("F8", ChessPiece.Color.BLACK));
        piecesList.add(new Knight("G8", ChessPiece.Color.BLACK));
        piecesList.add(new Rook("H8", ChessPiece.Color.BLACK));
        piecesList.add(new Pawn("A7", ChessPiece.Color.BLACK));
        piecesList.add(new Pawn("B7", ChessPiece.Color.BLACK));
        piecesList.add(new Pawn("C7", ChessPiece.Color.BLACK));
        piecesList.add(new Pawn("D7", ChessPiece.Color.BLACK));
        piecesList.add(new Pawn("E7", ChessPiece.Color.BLACK));
        piecesList.add(new Pawn("F7", ChessPiece.Color.BLACK));
        piecesList.add(new Pawn("G7", ChessPiece.Color.BLACK));
        piecesList.add(new Pawn("H7", ChessPiece.Color.BLACK));
    }

    public void move(Class type, ChessPiece.Color color, String position) {
        String olderPosition = "";
        boolean isFound = false;
        ChessPiece movablePiece = null;
        for (ChessPiece chessPiece : piecesList) {
            if (chessPiece.getClass().equals(type) && chessPiece.getColor() == color) {
                try {
                    olderPosition = chessPiece.getPosition();
                    chessPiece.move(position);
                    isFound = true;
                    movablePiece = chessPiece;
                    break;
                } catch(Exception e) {}
            }
        }
        if (!isFound) throw new IllegalChessMoveException("No legal chess pieces found for that move!");
        for (ChessPiece chessPiece : piecesList) {
            if (chessPiece != movablePiece) {
                if (chessPiece.getPosition().equals(position) && chessPiece.getColor() == movablePiece.getColor()) {
                    movablePiece.position = olderPosition;
                    throw new IllegalChessMoveException("Field already taken by the same color!");
                }
            }
        }
        if (movablePiece instanceof Pawn
                && ChessPiece.slovnaKoordinata(olderPosition)
                == ChessPiece.slovnaKoordinata(movablePiece.getPosition())) {
            for (ChessPiece chessPiece : piecesList) {
                if (chessPiece != movablePiece && chessPiece.getPosition().equals(movablePiece.getPosition())) {
                    movablePiece.position = olderPosition;
                    throw new IllegalChessMoveException("Illegal move for that kind of chess piece!");
                }
            }
        }
        int indexForRemoval = 0;
        boolean removePiece = false;
        for (ChessPiece chessPiece : piecesList) {
            if (chessPiece != movablePiece && chessPiece.getPosition().equals(movablePiece.getPosition())) {
                removePiece = true;
                break;
            }
            indexForRemoval++;
        }
        if (removePiece) piecesList.remove(indexForRemoval);
    }
    public void move(String oldPosition, String newPosition) {

    }
    public boolean isCheck(ChessPiece.Color color) {
        return false;
    }
}
