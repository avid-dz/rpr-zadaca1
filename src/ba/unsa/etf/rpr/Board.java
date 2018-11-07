package ba.unsa.etf.rpr;

import com.sun.xml.internal.bind.v2.runtime.reflect.ListTransducedAccessorImpl;

import java.util.ArrayList;

public class Board {

    private ArrayList<ChessPiece> listaFigura;

    public Board() {
        listaFigura = new ArrayList<>();
        listaFigura.add(new Rook("A1", ChessPiece.Color.WHITE));
        listaFigura.add(new Knight("B1", ChessPiece.Color.WHITE));
        listaFigura.add(new Bishop("C1", ChessPiece.Color.WHITE));
        listaFigura.add(new Queen("D1", ChessPiece.Color.WHITE));
        listaFigura.add(new King("E1", ChessPiece.Color.WHITE));
        listaFigura.add(new Bishop("F1", ChessPiece.Color.WHITE));
        listaFigura.add(new Knight("G1", ChessPiece.Color.WHITE));
        listaFigura.add(new Rook("H1", ChessPiece.Color.WHITE));
        listaFigura.add(new Pawn("A2", ChessPiece.Color.WHITE));
        listaFigura.add(new Pawn("B2", ChessPiece.Color.WHITE));
        listaFigura.add(new Pawn("C2", ChessPiece.Color.WHITE));
        listaFigura.add(new Pawn("D2", ChessPiece.Color.WHITE));
        listaFigura.add(new Pawn("E2", ChessPiece.Color.WHITE));
        listaFigura.add(new Pawn("F2", ChessPiece.Color.WHITE));
        listaFigura.add(new Pawn("G2", ChessPiece.Color.WHITE));
        listaFigura.add(new Pawn("H2", ChessPiece.Color.WHITE));
        listaFigura.add(new Rook("A8", ChessPiece.Color.WHITE));
        listaFigura.add(new Knight("B8", ChessPiece.Color.WHITE));
        listaFigura.add(new Bishop("C8", ChessPiece.Color.WHITE));
        listaFigura.add(new Queen("D8", ChessPiece.Color.WHITE));
        listaFigura.add(new King("E8", ChessPiece.Color.WHITE));
        listaFigura.add(new Bishop("F8", ChessPiece.Color.WHITE));
        listaFigura.add(new Knight("G8", ChessPiece.Color.WHITE));
        listaFigura.add(new Rook("H8", ChessPiece.Color.WHITE));
        listaFigura.add(new Pawn("A7", ChessPiece.Color.WHITE));
        listaFigura.add(new Pawn("B7", ChessPiece.Color.WHITE));
        listaFigura.add(new Pawn("C7", ChessPiece.Color.WHITE));
        listaFigura.add(new Pawn("D7", ChessPiece.Color.WHITE));
        listaFigura.add(new Pawn("E7", ChessPiece.Color.WHITE));
        listaFigura.add(new Pawn("F7", ChessPiece.Color.WHITE));
        listaFigura.add(new Pawn("G7", ChessPiece.Color.WHITE));
        listaFigura.add(new Pawn("H7", ChessPiece.Color.WHITE));
    }

    public void move(Class type, ChessPiece.Color color, String position) {

    }
    public void move(String oldPosition, String newPosition) {

    }
    public boolean isCheck(ChessPiece.Color color) {
        return false;
    }
}
