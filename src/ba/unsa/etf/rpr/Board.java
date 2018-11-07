package ba.unsa.etf.rpr;

import java.util.ArrayList;

public class Board {

    private ArrayList<ChessPiece> listaCrnih;
    private ArrayList<ChessPiece> listaBijelih;

    public Board() {
        listaCrnih = new ArrayList<>();
        listaBijelih = new ArrayList<>();
        listaCrnih.add(new Rook("A1", ChessPiece.Color.WHITE));
    }

    public void move(Class type, ChessPiece.Color color, String position) {

    }
    public void move(String oldPosition, String newPosition) {

    }
    public boolean isCheck(ChessPiece.Color color) {
        return false;
    }
}
