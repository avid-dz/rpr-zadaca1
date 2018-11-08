package ba.unsa.etf.rpr;

public class Pawn extends ChessPiece {

    protected boolean ispravnoZaTuFiguru(String position) {
        if (Math.abs(ChessPiece.slovnaKoordinata(position) - ChessPiece.slovnaKoordinata(this.position)) > 1)
            return false;
        if (Math.abs(ChessPiece.brojevnaKoordinata(position) - ChessPiece.brojevnaKoordinata(this.position)) > 2)
            return false;
        if ((ChessPiece.brojevnaKoordinata(this.position) == 4 || ChessPiece.brojevnaKoordinata(this.position) == 5)
                && (Math.abs(ChessPiece.brojevnaKoordinata(position)
                - ChessPiece.brojevnaKoordinata(this.position)) > 1))
            return false;
        return true;
    }

    public Pawn(String position, ChessPiece.Color color) {
        super(position, color);
    }
}
