package ba.unsa.etf.rpr;

public class Knight extends ChessPiece {

    protected boolean ispravnoZaTuFiguru(String position) {
        if (!(Math.abs(ChessPiece.slovnaKoordinata(position)
                - ChessPiece.slovnaKoordinata(this.position)) == 2
                && Math.abs(ChessPiece.brojevnaKoordinata(position)
                - ChessPiece.brojevnaKoordinata(this.position)) == 1)
                && !(Math.abs(ChessPiece.brojevnaKoordinata(position)
                - ChessPiece.brojevnaKoordinata(this.position)) == 2
                && Math.abs(ChessPiece.slovnaKoordinata(position)
                - ChessPiece.slovnaKoordinata(this.position)) == 1))
            return false;
        return true;
    }

    public Knight(String position, ChessPiece.Color color) {
        super(position, color);
    }
}
