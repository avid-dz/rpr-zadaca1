package ba.unsa.etf.rpr;

public class Rook extends ChessPiece {

    protected boolean ispravnoZaTuFiguru(String position) {
        if ((Math.abs(ChessPiece.slovnaKoordinata(position) - ChessPiece.slovnaKoordinata(this.position)) != 0) &&
                (Math.abs(ChessPiece.brojevnaKoordinata(position) - ChessPiece.brojevnaKoordinata(this.position)) != 0))
            return false;
        return true;
    }

    public Rook(String position, ChessPiece.Color color) {
        super(position, color);
    }
}
