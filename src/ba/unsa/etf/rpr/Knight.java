package ba.unsa.etf.rpr;

public class Knight extends ChessPiece {

    protected boolean ispravnoZaTuFiguru(String position) {
        return true;
    }

    public Knight(String position, ChessPiece.Color color) {
        super(position, color);
    }
}
