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
                    chessPiece.move(position);  //  Trying to move the piece to see if the movement is legal for it
                    isFound = true;     //  If the movement is legal, the piece is found and we stop searching
                    movablePiece = chessPiece;
                    break;
                } catch(Exception e) {}  // If the movement is not legal we continue searching
            }
        }
        if (!isFound) throw new IllegalChessMoveException("Illegal move");
        for (ChessPiece chessPiece : piecesList) {
            if (chessPiece != movablePiece) {
                if (chessPiece.getPosition().equals(position) && chessPiece.getColor() == movablePiece.getColor()) {
                    movablePiece.position = olderPosition;
                    throw new IllegalChessMoveException("Illegal move");    //  Field already taken by the same color
                }
            }
        }
        if (movablePiece instanceof Pawn    //  Pawn cannot move vertically if destination field is not empty
                && ChessPiece.letterCoordinate(olderPosition)
                == ChessPiece.letterCoordinate(movablePiece.getPosition())) {
            for (ChessPiece chessPiece : piecesList) {
                if (chessPiece != movablePiece && chessPiece.getPosition().equals(movablePiece.getPosition())) {
                    movablePiece.position = olderPosition;
                    throw new IllegalChessMoveException("Illegal move");
                }
            }
        }
        if (movablePiece instanceof Pawn) {     //     Pawn's jumping is forbidden
            int middle = 0;
            String middleStringPosition = "";
            if (Math.abs(ChessPiece.numberCoordinate(olderPosition) - ChessPiece.numberCoordinate(position)) == 2) {
                middle = (ChessPiece.numberCoordinate(olderPosition) + ChessPiece.numberCoordinate(position))/2;
                middleStringPosition += ChessPiece.letterStringCoordinate(position);
                middleStringPosition += Integer.toString(middle);
                if (thereIsAPieceHere(middleStringPosition)) {
                    movablePiece.position = olderPosition;
                    throw new IllegalChessMoveException("Illegal move");
                }
            }
        }
        if (movablePiece instanceof Rook) {     //  Rook's jumping is forbidden
            if (ChessPiece.numberCoordinate(olderPosition) - ChessPiece.numberCoordinate(position) > 1) {
                for (int iterNumber = ChessPiece.numberCoordinate(position) + 1;
                     iterNumber < ChessPiece.numberCoordinate(olderPosition); iterNumber++) {
                    String middleStringPosition = "";
                    middleStringPosition += ChessPiece.letterStringCoordinate(position);
                    middleStringPosition += Integer.toString(iterNumber);
                    if (thereIsAPieceHere(middleStringPosition)) {
                        movablePiece.position = olderPosition;
                        throw new IllegalChessMoveException("Illegal move");
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
                        throw new IllegalChessMoveException("Illegal move");
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
                        throw new IllegalChessMoveException("Illegal move");
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
                        throw new IllegalChessMoveException("Illegal move");
                    }
                }
            }
        }
        if (movablePiece instanceof Bishop) {   //  Bishop's jumping is forbidden
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
                            throw new IllegalChessMoveException("Illegal move");
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
                            throw new IllegalChessMoveException("Illegal move");
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
                            throw new IllegalChessMoveException("Illegal move");
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
                            throw new IllegalChessMoveException("Illegal move");
                        }
                        iterLetter--;
                    }
                }
            }
        }
        if (movablePiece instanceof Queen) {    //  Queen's jumping is forbidden
            if (ChessPiece.letterCoordinate(olderPosition) == ChessPiece.letterCoordinate(position)) {
                if (ChessPiece.numberCoordinate(olderPosition) - ChessPiece.numberCoordinate(position) > 1) {
                    for (int iterNumber = ChessPiece.numberCoordinate(position) + 1;
                         iterNumber < ChessPiece.numberCoordinate(olderPosition); iterNumber++) {
                        String middleStringPosition = "";
                        middleStringPosition += ChessPiece.letterStringCoordinate(position);
                        middleStringPosition += Integer.toString(iterNumber);
                        if (thereIsAPieceHere(middleStringPosition)) {
                            movablePiece.position = olderPosition;
                            throw new IllegalChessMoveException("Illegal move");
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
                            throw new IllegalChessMoveException("Illegal move");
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
                            throw new IllegalChessMoveException("Illegal move");
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
                            throw new IllegalChessMoveException("Illegal move");
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
                            throw new IllegalChessMoveException("Illegal move");
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
                            throw new IllegalChessMoveException("Illegal move");
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
                            throw new IllegalChessMoveException("Illegal move");
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
                            throw new IllegalChessMoveException("Illegal move");
                        }
                        iterLetter--;
                    }
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
        if (removePiece) piecesList.remove(indexForRemoval);        //  Eating
    }
    public void move(String oldPosition, String newPosition) {
        oldPosition = oldPosition.toUpperCase();
        newPosition = newPosition.toUpperCase();
        if (!thereIsAPieceHere(oldPosition)) {
            throw new IllegalArgumentException("Illegal move");
        }
        String olderPosition = oldPosition;
        int iter = 0;
        for (ChessPiece chessPiece : piecesList) {      //  Finding the right piece to move
            if (chessPiece.getPosition().equals(oldPosition))
                break;
            iter++;
        }
        if (piecesList.get(iter) instanceof Pawn) {     //  Pawn cannot move diagonally if not eating
            if (Math.abs(ChessPiece.numberCoordinate(olderPosition) - ChessPiece.numberCoordinate(newPosition))
                    == Math.abs(ChessPiece.letterCoordinate(olderPosition)
                    - ChessPiece.letterCoordinate(newPosition))) {
                if (!thereIsAPieceHere(newPosition))
                    throw new IllegalChessMoveException("Illegal move");
            }
        }
        piecesList.get(iter).move(newPosition);     //  Moving
        ChessPiece movablePiece = piecesList.get(iter);
        for (ChessPiece chessPiece : piecesList) {
            if (chessPiece != movablePiece) {
                if (chessPiece.getPosition().equals(newPosition) && chessPiece.getColor() == movablePiece.getColor()) {
                    movablePiece.position = olderPosition;
                    throw new IllegalChessMoveException("Illegal move");
                }
            }
        }
        if (movablePiece instanceof Pawn
                && ChessPiece.letterCoordinate(olderPosition)
                == ChessPiece.letterCoordinate(movablePiece.getPosition())) {
            for (ChessPiece chessPiece : piecesList) {
                if (chessPiece != movablePiece && chessPiece.getPosition().equals(movablePiece.getPosition())) {
                    movablePiece.position = olderPosition;
                    throw new IllegalChessMoveException("Illegal move");
                }
            }
        }
        if (movablePiece instanceof Pawn) {
            int middle = 0;
            String middleStringPosition = "";
            if (Math.abs(ChessPiece.numberCoordinate(olderPosition) - ChessPiece.numberCoordinate(newPosition)) == 2) {
                middle = (ChessPiece.numberCoordinate(olderPosition) + ChessPiece.numberCoordinate(newPosition))/2;
                middleStringPosition += ChessPiece.letterStringCoordinate(newPosition);
                middleStringPosition += Integer.toString(middle);
                if (thereIsAPieceHere(middleStringPosition)) {
                    movablePiece.position = olderPosition;
                    throw new IllegalChessMoveException("Illegal move");
                }
            }
        }
        if (movablePiece instanceof Rook) {
            if (ChessPiece.numberCoordinate(olderPosition) - ChessPiece.numberCoordinate(newPosition) > 1) {
                for (int iterNumber = ChessPiece.numberCoordinate(newPosition) + 1;
                     iterNumber < ChessPiece.numberCoordinate(olderPosition); iterNumber++) {
                    String middleStringPosition = "";
                    middleStringPosition += ChessPiece.letterStringCoordinate(newPosition);
                    middleStringPosition += Integer.toString(iterNumber);
                    if (thereIsAPieceHere(middleStringPosition)) {
                        movablePiece.position = olderPosition;
                        throw new IllegalChessMoveException("Illegal move");
                    }
                }
            }
            if (ChessPiece.numberCoordinate(newPosition) - ChessPiece.numberCoordinate(olderPosition) > 1) {
                for (int iterNumber = ChessPiece.numberCoordinate(olderPosition) + 1;
                     iterNumber < ChessPiece.numberCoordinate(newPosition); iterNumber++) {
                    String middleStringPosition = "";
                    middleStringPosition += ChessPiece.letterStringCoordinate(newPosition);
                    middleStringPosition += Integer.toString(iterNumber);
                    if (thereIsAPieceHere(middleStringPosition)) {
                        movablePiece.position = olderPosition;
                        throw new IllegalChessMoveException("Illegal move");
                    }
                }
            }
            if (ChessPiece.letterCoordinate(newPosition) - ChessPiece.letterCoordinate(olderPosition) > 1) {
                for (int iterLetter = ChessPiece.letterCoordinate(olderPosition) + 1;
                     iterLetter < ChessPiece.letterCoordinate(newPosition); iterLetter++) {
                    String middleStringPosition = "";
                    middleStringPosition += Character.toString((char) iterLetter);
                    middleStringPosition += ChessPiece.numberStringCoordinate(newPosition);
                    if (thereIsAPieceHere(middleStringPosition)) {
                        movablePiece.position = olderPosition;
                        throw new IllegalChessMoveException("Illegal move");
                    }
                }
            }
            if (ChessPiece.letterCoordinate(olderPosition) - ChessPiece.letterCoordinate(newPosition) > 1) {
                for (int iterLetter = ChessPiece.letterCoordinate(newPosition) + 1;
                     iterLetter < ChessPiece.letterCoordinate(olderPosition); iterLetter++) {
                    String middleStringPosition = "";
                    middleStringPosition += Character.toString((char) iterLetter);
                    middleStringPosition += ChessPiece.numberStringCoordinate(newPosition);
                    if (thereIsAPieceHere(middleStringPosition)) {
                        movablePiece.position = olderPosition;
                        throw new IllegalChessMoveException("Illegal move");
                    }
                }
            }
        }
        if (movablePiece instanceof Bishop) {
            if (ChessPiece.numberCoordinate(newPosition) - ChessPiece.numberCoordinate(olderPosition) > 1) {
                if (ChessPiece.letterCoordinate(olderPosition) < ChessPiece.letterCoordinate(newPosition)) {
                    int iterLetter = ChessPiece.letterCoordinate(olderPosition) + 1;
                    for (int iterNumber = ChessPiece.numberCoordinate(olderPosition) + 1;
                         iterNumber < ChessPiece.numberCoordinate(newPosition); iterNumber++) {
                        String middleStringPosition = "";
                        middleStringPosition += Character.toString((char) iterLetter);
                        middleStringPosition += Integer.toString(iterNumber);
                        if (thereIsAPieceHere(middleStringPosition)) {
                            movablePiece.position = olderPosition;
                            throw new IllegalChessMoveException("Illegal move");
                        }
                        iterLetter++;
                    }
                }
                if (ChessPiece.letterCoordinate(olderPosition) > ChessPiece.letterCoordinate(newPosition)) {
                    int iterLetter = ChessPiece.letterCoordinate(olderPosition) - 1;
                    for (int iterNumber = ChessPiece.numberCoordinate(olderPosition) + 1;
                         iterNumber < ChessPiece.numberCoordinate(newPosition); iterNumber++) {
                        String middleStringPosition = "";
                        middleStringPosition += Character.toString((char) iterLetter);
                        middleStringPosition += Integer.toString(iterNumber);
                        if (thereIsAPieceHere(middleStringPosition)) {
                            movablePiece.position = olderPosition;
                            throw new IllegalChessMoveException("Illegal move");
                        }
                        iterLetter--;
                    }
                }
            }
            if (ChessPiece.numberCoordinate(olderPosition) - ChessPiece.numberCoordinate(newPosition) > 1) {
                if (ChessPiece.letterCoordinate(olderPosition) < ChessPiece.letterCoordinate(newPosition)) {
                    int iterLetter = ChessPiece.letterCoordinate(olderPosition) + 1;
                    for (int iterNumber = ChessPiece.numberCoordinate(olderPosition) - 1;
                         iterNumber > ChessPiece.numberCoordinate(newPosition); iterNumber--) {
                        String middleStringPosition = "";
                        middleStringPosition += Character.toString((char) iterLetter);
                        middleStringPosition += Integer.toString(iterNumber);
                        if (thereIsAPieceHere(middleStringPosition)) {
                            movablePiece.position = olderPosition;
                            throw new IllegalChessMoveException("Illegal move");
                        }
                        iterLetter++;
                    }
                }
                if (ChessPiece.letterCoordinate(olderPosition) > ChessPiece.letterCoordinate(newPosition)) {
                    int iterLetter = ChessPiece.letterCoordinate(olderPosition) - 1;
                    for (int iterNumber = ChessPiece.numberCoordinate(olderPosition) - 1;
                         iterNumber > ChessPiece.numberCoordinate(newPosition); iterNumber--) {
                        String middleStringPosition = "";
                        middleStringPosition += Character.toString((char) iterLetter);
                        middleStringPosition += Integer.toString(iterNumber);
                        if (thereIsAPieceHere(middleStringPosition)) {
                            movablePiece.position = olderPosition;
                            throw new IllegalChessMoveException("Illegal move");
                        }
                        iterLetter--;
                    }
                }
            }
        }
        if (movablePiece instanceof Queen) {
            if (ChessPiece.letterCoordinate(olderPosition) == ChessPiece.letterCoordinate(newPosition)) {
                if (ChessPiece.numberCoordinate(olderPosition) - ChessPiece.numberCoordinate(newPosition) > 1) {
                    for (int iterNumber = ChessPiece.numberCoordinate(newPosition) + 1;
                         iterNumber < ChessPiece.numberCoordinate(olderPosition); iterNumber++) {
                        String middleStringPosition = "";
                        middleStringPosition += ChessPiece.letterStringCoordinate(newPosition);
                        middleStringPosition += Integer.toString(iterNumber);
                        if (thereIsAPieceHere(middleStringPosition)) {
                            movablePiece.position = olderPosition;
                            throw new IllegalChessMoveException("Illegal move");
                        }
                    }
                }
                if (ChessPiece.numberCoordinate(newPosition) - ChessPiece.numberCoordinate(olderPosition) > 1) {
                    for (int iterNumber = ChessPiece.numberCoordinate(olderPosition) + 1;
                         iterNumber < ChessPiece.numberCoordinate(newPosition); iterNumber++) {
                        String middleStringPosition = "";
                        middleStringPosition += ChessPiece.letterStringCoordinate(newPosition);
                        middleStringPosition += Integer.toString(iterNumber);
                        if (thereIsAPieceHere(middleStringPosition)) {
                            movablePiece.position = olderPosition;
                            throw new IllegalChessMoveException("Illegal move");
                        }
                    }
                }
            }
            if (ChessPiece.numberCoordinate(olderPosition) == ChessPiece.numberCoordinate(newPosition)) {
                if (ChessPiece.letterCoordinate(newPosition) - ChessPiece.letterCoordinate(olderPosition) > 1) {
                    for (int iterLetter = ChessPiece.letterCoordinate(olderPosition) + 1;
                         iterLetter < ChessPiece.letterCoordinate(newPosition); iterLetter++) {
                        String middleStringPosition = "";
                        middleStringPosition += Character.toString((char) iterLetter);
                        middleStringPosition += ChessPiece.numberStringCoordinate(newPosition);
                        if (thereIsAPieceHere(middleStringPosition)) {
                            movablePiece.position = olderPosition;
                            throw new IllegalChessMoveException("Illegal move");
                        }
                    }
                }
                if (ChessPiece.letterCoordinate(olderPosition) - ChessPiece.letterCoordinate(newPosition) > 1) {
                    for (int iterLetter = ChessPiece.letterCoordinate(newPosition) + 1;
                         iterLetter < ChessPiece.letterCoordinate(olderPosition); iterLetter++) {
                        String middleStringPosition = "";
                        middleStringPosition += Character.toString((char) iterLetter);
                        middleStringPosition += ChessPiece.numberStringCoordinate(newPosition);
                        if (thereIsAPieceHere(middleStringPosition)) {
                            movablePiece.position = olderPosition;
                            throw new IllegalChessMoveException("Illegal move");
                        }
                    }
                }
            }
            if (ChessPiece.numberCoordinate(newPosition) - ChessPiece.numberCoordinate(olderPosition) > 1) {
                if (ChessPiece.letterCoordinate(olderPosition) < ChessPiece.letterCoordinate(newPosition)) {
                    int iterLetter = ChessPiece.letterCoordinate(olderPosition) + 1;
                    for (int iterNumber = ChessPiece.numberCoordinate(olderPosition) + 1;
                         iterNumber < ChessPiece.numberCoordinate(newPosition); iterNumber++) {
                        String middleStringPosition = "";
                        middleStringPosition += Character.toString((char) iterLetter);
                        middleStringPosition += Integer.toString(iterNumber);
                        if (thereIsAPieceHere(middleStringPosition)) {
                            movablePiece.position = olderPosition;
                            throw new IllegalChessMoveException("Illegal move");
                        }
                        iterLetter++;
                    }
                }
                if (ChessPiece.letterCoordinate(olderPosition) > ChessPiece.letterCoordinate(newPosition)) {
                    int iterLetter = ChessPiece.letterCoordinate(olderPosition) - 1;
                    for (int iterNumber = ChessPiece.numberCoordinate(olderPosition) + 1;
                         iterNumber < ChessPiece.numberCoordinate(newPosition); iterNumber++) {
                        String middleStringPosition = "";
                        middleStringPosition += Character.toString((char) iterLetter);
                        middleStringPosition += Integer.toString(iterNumber);
                        if (thereIsAPieceHere(middleStringPosition)) {
                            movablePiece.position = olderPosition;
                            throw new IllegalChessMoveException("Illegal move");
                        }
                        iterLetter--;
                    }
                }
            }
            if (ChessPiece.numberCoordinate(olderPosition) - ChessPiece.numberCoordinate(newPosition) > 1) {
                if (ChessPiece.letterCoordinate(olderPosition) < ChessPiece.letterCoordinate(newPosition)) {
                    int iterLetter = ChessPiece.letterCoordinate(olderPosition) + 1;
                    for (int iterNumber = ChessPiece.numberCoordinate(olderPosition) - 1;
                         iterNumber > ChessPiece.numberCoordinate(newPosition); iterNumber--) {
                        String middleStringPosition = "";
                        middleStringPosition += Character.toString((char) iterLetter);
                        middleStringPosition += Integer.toString(iterNumber);
                        if (thereIsAPieceHere(middleStringPosition)) {
                            movablePiece.position = olderPosition;
                            throw new IllegalChessMoveException("Illegal move");
                        }
                        iterLetter++;
                    }
                }
                if (ChessPiece.letterCoordinate(olderPosition) > ChessPiece.letterCoordinate(newPosition)) {
                    int iterLetter = ChessPiece.letterCoordinate(olderPosition) - 1;
                    for (int iterNumber = ChessPiece.numberCoordinate(olderPosition) - 1;
                         iterNumber > ChessPiece.numberCoordinate(newPosition); iterNumber--) {
                        String middleStringPosition = "";
                        middleStringPosition += Character.toString((char) iterLetter);
                        middleStringPosition += Integer.toString(iterNumber);
                        if (thereIsAPieceHere(middleStringPosition)) {
                            movablePiece.position = olderPosition;
                            throw new IllegalChessMoveException("Illegal move");
                        }
                        iterLetter--;
                    }
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
    public boolean isCheck(ChessPiece.Color color) {
        if (color == ChessPiece.Color.WHITE) {
            String kingPos = "";
            int kingIndex = 0;
            for (ChessPiece chessPiece : piecesList) {
                if (chessPiece instanceof King) {
                    if (chessPiece.getColor() == ChessPiece.Color.WHITE) {
                        kingPos = chessPiece.getPosition();
                        break;
                    }
                }
                kingIndex++;
            }
            boolean kingEatable = false;
            for (int i = 0; i < piecesList.size(); i++) {
                if (piecesList.get(i).getColor() == ChessPiece.Color.BLACK) {
                    try {
                        String oldPiecePosition = piecesList.get(i).getPosition();
                        move(piecesList.get(i).getClass(), ChessPiece.Color.BLACK, kingPos);
                        piecesList.get(i).position = oldPiecePosition;
                        piecesList.add(kingIndex, new King(kingPos, ChessPiece.Color.WHITE));
                        kingEatable = true;
                        break;
                    } catch (Exception e) {
                    }
                }
            }
            if (kingEatable) return true;
        }
        if (color == ChessPiece.Color.BLACK) {
            String kingPos = "";
            int kingIndex = 0;
            for (ChessPiece chessPiece : piecesList) {
                if (chessPiece instanceof King) {
                    if (chessPiece.getColor() == ChessPiece.Color.BLACK) {
                        kingPos = chessPiece.getPosition();
                        break;
                    }
                }
                kingIndex++;
            }
            boolean kingEatable = false;
            for (int i = 0; i < piecesList.size(); i++) {
                if (piecesList.get(i).getColor() == ChessPiece.Color.WHITE) {
                    try {
                        String oldPiecePosition = piecesList.get(i).getPosition();
                        move(piecesList.get(i).getClass(), ChessPiece.Color.WHITE, kingPos);
                        piecesList.get(i).position = oldPiecePosition;
                        piecesList.add(kingIndex, new King(kingPos, ChessPiece.Color.BLACK));
                        kingEatable = true;
                        break;
                    } catch (Exception e) {
                    }
                }
            }
            if (kingEatable) return true;
        }
        return false;
    }
}
