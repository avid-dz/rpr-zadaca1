package ba.unsa.etf.rpr;

public abstract class ChessPiece {

    public static enum Color {
        BLACK, WHITE
    }

    public abstract ChessPiece(String position, Color color);

    public abstract String getPosition();
    public abstract Color getColor();
    public abstract void move(String position);
}
