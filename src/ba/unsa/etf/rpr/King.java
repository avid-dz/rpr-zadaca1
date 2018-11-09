package ba.unsa.etf.rpr;

public class King extends ChessPiece {

    protected boolean legalForThatKindOfPiece(String position) {
        position = position.toUpperCase();
        if (Math.abs(ChessPiece.letterCoordinate(position) - ChessPiece.letterCoordinate(this.position)) > 1)
            return false;
        if (Math.abs(ChessPiece.numberCoordinate(position) - ChessPiece.numberCoordinate(this.position)) > 1)
            return false;
        return true;
    }

    public King(String position, ChessPiece.Color color) {
        super(position, color);
    }
}