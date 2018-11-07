package ba.unsa.etf.rpr;

public class King extends ChessPiece {

    protected boolean ispravnoZaTuFiguru(String position) {
        if (ChessPiece.slovnaKoordinata(position) - ChessPiece.slovnaKoordinata(this.position) == 0) return false;
        return true;
    }

    public King(String position, ChessPiece.Color color) {
        super(position, color);
    }
}
