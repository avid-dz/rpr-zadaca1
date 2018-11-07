package ba.unsa.etf.rpr;

public class Queen extends ChessPiece {

    protected boolean ispravnoZaTuFiguru(String position) {
        return true;
    }

    public Queen(String position, ChessPiece.Color color) {
        super(position, color);
    }
}
