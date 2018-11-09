package ba.unsa.etf.rpr;

import java.util.ArrayList;

public class Board {

    private ArrayList<ChessPiece> piecesList;

    private boolean thereIsAPieceHere(String position) {
        for (ChessPiece chessPiece : piecesList) {
            if (chessPiece.getPosition().equals(position.toUpperCase())) return true;
        }
        return false;
    }

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
        position = position.toUpperCase();
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
                && ChessPiece.letterCoordinate(olderPosition)
                == ChessPiece.letterCoordinate(movablePiece.getPosition())) {
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
        if (movablePiece instanceof Pawn) {
            int middle = 0;
            String middleStringPosition = "";
            if (Math.abs(ChessPiece.numberCoordinate(olderPosition) - ChessPiece.numberCoordinate(position)) == 2) {
                middle = (ChessPiece.numberCoordinate(olderPosition) + ChessPiece.numberCoordinate(position))/2;
                middleStringPosition += ChessPiece.letterStringCoordinate(position);
                middleStringPosition += Integer.toString(middle);
                if (thereIsAPieceHere(middleStringPosition)) {
                    movablePiece.position = olderPosition;
                    throw new IllegalChessMoveException("A pawn cannot jump over other chess pieces!");
                }
            }
        }
        if (movablePiece instanceof Rook) {
            if (ChessPiece.numberCoordinate(olderPosition) - ChessPiece.numberCoordinate(position) > 1) {
                for (int iterNumber = ChessPiece.numberCoordinate(position) + 1;
                     iterNumber < ChessPiece.numberCoordinate(olderPosition); iterNumber++) {
                    String middleStringPosition = "";
                    middleStringPosition += ChessPiece.letterStringCoordinate(position);
                    middleStringPosition += Integer.toString(iterNumber);
                    if (thereIsAPieceHere(middleStringPosition)) {
                        movablePiece.position = olderPosition;
                        throw new IllegalChessMoveException("A rook cannot jump over other chess pieces!");
                    }
                }
            }
            if (ChessPiece.numberCoordinate(position) - ChessPiece.numberCoordinate(olderPosition) > 1) {
                for (int iterNumber = ChessPiece.numberCoordinate(olderPosition) + 1;
                     iterNumber < ChessPiece.numberCoordinate(position); iterNumber++) {
                    String middleStringPosition = "";
                    middleStringPosition += ChessPiece.letterStringCoordinate(position);
                    middleStringPosition += Integer.toString(iterNumber);
                    if (thereIsAPieceHere(middleStringPosition)) {
                        movablePiece.position = olderPosition;
                        throw new IllegalChessMoveException("A rook cannot jump over other chess pieces!");
                    }
                }
            }
            if (ChessPiece.letterCoordinate(position) - ChessPiece.letterCoordinate(olderPosition) > 1) {
                for (int iterLetter = ChessPiece.letterCoordinate(olderPosition) + 1;
                     iterLetter < ChessPiece.letterCoordinate(position); iterLetter++) {
                    String middleStringPosition = "";
                    middleStringPosition += Character.toString((char) iterLetter);
                    middleStringPosition += ChessPiece.numberStringCoordinate(position);
                    if (thereIsAPieceHere(middleStringPosition)) {
                        movablePiece.position = olderPosition;
                        throw new IllegalChessMoveException("A rook cannot jump over other chess pieces!");
                    }
                }
            }
            if (ChessPiece.letterCoordinate(olderPosition) - ChessPiece.letterCoordinate(position) > 1) {
                for (int iterLetter = ChessPiece.letterCoordinate(position) + 1;
                     iterLetter < ChessPiece.letterCoordinate(olderPosition); iterLetter++) {
                    String middleStringPosition = "";
                    middleStringPosition += Character.toString((char) iterLetter);
                    middleStringPosition += ChessPiece.numberStringCoordinate(position);
                    if (thereIsAPieceHere(middleStringPosition)) {
                        movablePiece.position = olderPosition;
                        throw new IllegalChessMoveException("A rook cannot jump over other chess pieces!");
                    }
                }
            }
        }
        if (movablePiece instanceof Bishop) {
            if (ChessPiece.numberCoordinate(position) - ChessPiece.numberCoordinate(olderPosition) > 1) {
                if (ChessPiece.letterCoordinate(olderPosition) < ChessPiece.letterCoordinate(position)) {
                    int iterLetter = ChessPiece.letterCoordinate(olderPosition) + 1;
                    for (int iterNumber = ChessPiece.numberCoordinate(olderPosition) + 1;
                         iterNumber < ChessPiece.numberCoordinate(position); iterNumber++) {
                        String middleStringPosition = "";
                        middleStringPosition += Character.toString((char) iterLetter);
                        middleStringPosition += Integer.toString(iterNumber);
                        if (thereIsAPieceHere(middleStringPosition)) {
                            movablePiece.position = olderPosition;
                            throw new IllegalChessMoveException("A bishop cannot jump over other chess pieces!");
                        }
                        iterLetter++;
                    }
                }
                if (ChessPiece.letterCoordinate(olderPosition) > ChessPiece.letterCoordinate(position)) {
                    int iterLetter = ChessPiece.letterCoordinate(olderPosition) - 1;
                    for (int iterNumber = ChessPiece.numberCoordinate(olderPosition) + 1;
                         iterNumber < ChessPiece.numberCoordinate(position); iterNumber++) {
                        String middleStringPosition = "";
                        middleStringPosition += Character.toString((char) iterLetter);
                        middleStringPosition += Integer.toString(iterNumber);
                        if (thereIsAPieceHere(middleStringPosition)) {
                            movablePiece.position = olderPosition;
                            throw new IllegalChessMoveException("A bishop cannot jump over other chess pieces!");
                        }
                        iterLetter--;
                    }
                }
            }
            if (ChessPiece.numberCoordinate(olderPosition) - ChessPiece.numberCoordinate(position) > 1) {
                if (ChessPiece.letterCoordinate(olderPosition) < ChessPiece.letterCoordinate(position)) {
                    int iterLetter = ChessPiece.letterCoordinate(olderPosition) + 1;
                    for (int iterNumber = ChessPiece.numberCoordinate(olderPosition) - 1;
                         iterNumber > ChessPiece.numberCoordinate(position); iterNumber--) {
                        String middleStringPosition = "";
                        middleStringPosition += Character.toString((char) iterLetter);
                        middleStringPosition += Integer.toString(iterNumber);
                        if (thereIsAPieceHere(middleStringPosition)) {
                            movablePiece.position = olderPosition;
                            throw new IllegalChessMoveException("A bishop cannot jump over other chess pieces!");
                        }
                        iterLetter++;
                    }
                }
                if (ChessPiece.letterCoordinate(olderPosition) > ChessPiece.letterCoordinate(position)) {
                    int iterLetter = ChessPiece.letterCoordinate(olderPosition) - 1;
                    for (int iterNumber = ChessPiece.numberCoordinate(olderPosition) - 1;
                         iterNumber > ChessPiece.numberCoordinate(position); iterNumber--) {
                        String middleStringPosition = "";
                        middleStringPosition += Character.toString((char) iterLetter);
                        middleStringPosition += Integer.toString(iterNumber);
                        if (thereIsAPieceHere(middleStringPosition)) {
                            movablePiece.position = olderPosition;
                            throw new IllegalChessMoveException("A bishop cannot jump over other chess pieces!");
                        }
                        iterLetter--;
                    }
                }
            }
        }
        if (movablePiece instanceof Queen) {
            if (ChessPiece.letterCoordinate(olderPosition) == ChessPiece.letterCoordinate(position)) {
                if (ChessPiece.numberCoordinate(olderPosition) - ChessPiece.numberCoordinate(position) > 1) {
                    for (int iterNumber = ChessPiece.numberCoordinate(position) + 1;
                         iterNumber < ChessPiece.numberCoordinate(olderPosition); iterNumber++) {
                        String middleStringPosition = "";
                        middleStringPosition += ChessPiece.letterStringCoordinate(position);
                        middleStringPosition += Integer.toString(iterNumber);
                        if (thereIsAPieceHere(middleStringPosition)) {
                            movablePiece.position = olderPosition;
                            throw new IllegalChessMoveException("A queen cannot jump over other chess pieces!");
                        }
                    }
                }
                if (ChessPiece.numberCoordinate(position) - ChessPiece.numberCoordinate(olderPosition) > 1) {
                    for (int iterNumber = ChessPiece.numberCoordinate(olderPosition) + 1;
                         iterNumber < ChessPiece.numberCoordinate(position); iterNumber++) {
                        String middleStringPosition = "";
                        middleStringPosition += ChessPiece.letterStringCoordinate(position);
                        middleStringPosition += Integer.toString(iterNumber);
                        if (thereIsAPieceHere(middleStringPosition)) {
                            movablePiece.position = olderPosition;
                            throw new IllegalChessMoveException("A queen cannot jump over other chess pieces!");
                        }
                    }
                }
            }
            if (ChessPiece.numberCoordinate(olderPosition) == ChessPiece.numberCoordinate(position)) {
                if (ChessPiece.letterCoordinate(position) - ChessPiece.letterCoordinate(olderPosition) > 1) {
                    for (int iterLetter = ChessPiece.letterCoordinate(olderPosition) + 1;
                         iterLetter < ChessPiece.letterCoordinate(position); iterLetter++) {
                        String middleStringPosition = "";
                        middleStringPosition += Character.toString((char) iterLetter);
                        middleStringPosition += ChessPiece.numberStringCoordinate(position);
                        if (thereIsAPieceHere(middleStringPosition)) {
                            movablePiece.position = olderPosition;
                            throw new IllegalChessMoveException("A queen cannot jump over other chess pieces!");
                        }
                    }
                }
                if (ChessPiece.letterCoordinate(olderPosition) - ChessPiece.letterCoordinate(position) > 1) {
                    for (int iterLetter = ChessPiece.letterCoordinate(position) + 1;
                         iterLetter < ChessPiece.letterCoordinate(olderPosition); iterLetter++) {
                        String middleStringPosition = "";
                        middleStringPosition += Character.toString((char) iterLetter);
                        middleStringPosition += ChessPiece.numberStringCoordinate(position);
                        if (thereIsAPieceHere(middleStringPosition)) {
                            movablePiece.position = olderPosition;
                            throw new IllegalChessMoveException("A queen cannot jump over other chess pieces!");
                        }
                    }
                }
            }
            if (ChessPiece.numberCoordinate(position) - ChessPiece.numberCoordinate(olderPosition) > 1) {
                if (ChessPiece.letterCoordinate(olderPosition) < ChessPiece.letterCoordinate(position)) {
                    int iterLetter = ChessPiece.letterCoordinate(olderPosition) + 1;
                    for (int iterNumber = ChessPiece.numberCoordinate(olderPosition) + 1;
                         iterNumber < ChessPiece.numberCoordinate(position); iterNumber++) {
                        String middleStringPosition = "";
                        middleStringPosition += Character.toString((char) iterLetter);
                        middleStringPosition += Integer.toString(iterNumber);
                        if (thereIsAPieceHere(middleStringPosition)) {
                            movablePiece.position = olderPosition;
                            throw new IllegalChessMoveException("A queen cannot jump over other chess pieces!");
                        }
                        iterLetter++;
                    }
                }
                if (ChessPiece.letterCoordinate(olderPosition) > ChessPiece.letterCoordinate(position)) {
                    int iterLetter = ChessPiece.letterCoordinate(olderPosition) - 1;
                    for (int iterNumber = ChessPiece.numberCoordinate(olderPosition) + 1;
                         iterNumber < ChessPiece.numberCoordinate(position); iterNumber++) {
                        String middleStringPosition = "";
                        middleStringPosition += Character.toString((char) iterLetter);
                        middleStringPosition += Integer.toString(iterNumber);
                        if (thereIsAPieceHere(middleStringPosition)) {
                            movablePiece.position = olderPosition;
                            throw new IllegalChessMoveException("A queen cannot jump over other chess pieces!");
                        }
                        iterLetter--;
                    }
                }
            }
            if (ChessPiece.numberCoordinate(olderPosition) - ChessPiece.numberCoordinate(position) > 1) {
                if (ChessPiece.letterCoordinate(olderPosition) < ChessPiece.letterCoordinate(position)) {
                    int iterLetter = ChessPiece.letterCoordinate(olderPosition) + 1;
                    for (int iterNumber = ChessPiece.numberCoordinate(olderPosition) - 1;
                         iterNumber > ChessPiece.numberCoordinate(position); iterNumber--) {
                        String middleStringPosition = "";
                        middleStringPosition += Character.toString((char) iterLetter);
                        middleStringPosition += Integer.toString(iterNumber);
                        if (thereIsAPieceHere(middleStringPosition)) {
                            movablePiece.position = olderPosition;
                            throw new IllegalChessMoveException("A queen cannot jump over other chess pieces!");
                        }
                        iterLetter++;
                    }
                }
                if (ChessPiece.letterCoordinate(olderPosition) > ChessPiece.letterCoordinate(position)) {
                    int iterLetter = ChessPiece.letterCoordinate(olderPosition) - 1;
                    for (int iterNumber = ChessPiece.numberCoordinate(olderPosition) - 1;
                         iterNumber > ChessPiece.numberCoordinate(position); iterNumber--) {
                        String middleStringPosition = "";
                        middleStringPosition += Character.toString((char) iterLetter);
                        middleStringPosition += Integer.toString(iterNumber);
                        if (thereIsAPieceHere(middleStringPosition)) {
                            movablePiece.position = olderPosition;
                            throw new IllegalChessMoveException("A queen cannot jump over other chess pieces!");
                        }
                        iterLetter--;
                    }
                }
            }
        }
    }
    public void move(String oldPosition, String newPosition) {
        oldPosition = oldPosition.toUpperCase();
        newPosition = newPosition.toUpperCase();
        if (!thereIsAPieceHere(oldPosition)) {
            throw new IllegalArgumentException("There is no chess piece there!");
        }
        else {
            int iter = 0;
            for (ChessPiece chessPiece : piecesList) {
                if (chessPiece.getPosition().equals(oldPosition))
                    break;
                iter++;
            }
            move(piecesList.get(iter).getClass(), piecesList.get(iter).getColor(), newPosition);
        }
    }
    public boolean isCheck(ChessPiece.Color color) {
        return false;
    }
}
