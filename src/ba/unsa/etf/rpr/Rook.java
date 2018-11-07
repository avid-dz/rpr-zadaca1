package ba.unsa.etf.rpr;

public class Rook extends ChessPiece {

    protected boolean ispravnoZaTuFiguru(String position) {
        return true;
    }

    public Rook(String position, ChessPiece.Color color) {
        super(position, color);
    }
}
