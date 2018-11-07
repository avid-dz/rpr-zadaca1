package ba.unsa.etf.rpr;

public class Bishop extends ChessPiece {

    protected boolean ispravnoZaTuFiguru(String position) {
        return true;
    }

    public Bishop(String position, ChessPiece.Color color) {
        super(position, color);
    }
}
