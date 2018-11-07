package ba.unsa.etf.rpr;

public class Pawn extends ChessPiece {

    protected boolean ispravnoZaTuFiguru(String position) {
        return true;
    }

    public Pawn(String position, ChessPiece.Color color) {
        super(position, color);
    }
}
