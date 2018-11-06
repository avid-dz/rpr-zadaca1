package ba.unsa.etf.rpr;

public abstract class ChessPiece {

    public static enum Color {
        BLACK, WHITE
    }

    protected String position;
    protected Color color;

    public ChessPiece(String position, Color color) {
        this.position = position;
        this.color = color;
    }

    public String getPosition() {
        return position;
    }
    public Color getColor() {
        return color;
    }
    public abstract void move(String position) {

    }
}
