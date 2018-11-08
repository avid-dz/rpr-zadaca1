package ba.unsa.etf.rpr;

import java.lang.Math;

public class King extends ChessPiece {

    protected boolean ispravnoZaTuFiguru(String position) {
        if (Math.abs(ChessPiece.slovnaKoordinata(position) - ChessPiece.slovnaKoordinata(this.position)) > 1)
            return false;
        if (Math.abs(ChessPiece.brojevnaKoordinata(position) - ChessPiece.brojevnaKoordinata(this.position)) > 1)
            return false;
        return true;
    }

    public King(String position, ChessPiece.Color color) {
        super(position, color);
    }
}