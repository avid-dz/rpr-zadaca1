package ba.unsa.etf.rpr;

public class Knight extends ChessPiece {

    protected boolean legalForThatKindOfPiece(String position) {
        position = position.toUpperCase();
        if (!(Math.abs(ChessPiece.letterCoordinate(position)
                - ChessPiece.letterCoordinate(this.position)) == 2
                && Math.abs(ChessPiece.numberCoordinate(position)
                - ChessPiece.numberCoordinate(this.position)) == 1)
                && !(Math.abs(ChessPiece.numberCoordinate(position)
                - ChessPiece.numberCoordinate(this.position)) == 2
                && Math.abs(ChessPiece.letterCoordinate(position)
                - ChessPiece.letterCoordinate(this.position)) == 1))
            return false;
        return true;
    }

    public Knight(String position, ChessPiece.Color color) {
        super(position, color);
    }
}
