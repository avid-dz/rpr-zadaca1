package ba.unsa.etf.rpr;

public class Bishop extends ChessPiece {

    protected boolean ispravnoZaTuFiguru(String position) {
        if (Math.abs(ChessPiece.slovnaKoordinata(position) - ChessPiece.slovnaKoordinata(this.position)) !=
                Math.abs(ChessPiece.brojevnaKoordinata(position) - ChessPiece.brojevnaKoordinata(this.position)))
            return false;
        return true;
    }

    public Bishop(String position, ChessPiece.Color color) {
        super(position, color);
    }
}
