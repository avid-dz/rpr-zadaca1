package ba.unsa.etf.rpr;

public abstract class ChessPiece {

    public static enum Color {
        BLACK, WHITE
    }

    protected String position;
    protected Color color;

    public static int letterCoordinate(String position) {
        return (int) position.toUpperCase().charAt(0);
    }
    public static int numberCoordinate(String position) {
        return position.toUpperCase().charAt(1) - '0';
    }
    public static String letterStringCoordinate(String position) {
        return Character.toString(position.toUpperCase().charAt(0));
    }
    public static String numberStringCoordinate(String position) {
        return Character.toString(position.toUpperCase().charAt(1));
    }

    private boolean validPosition(String position) {
        if (position.length() != 2)
            return false;
        if (!("ABCDEFGHabcdefgh".contains(Character.toString(position.charAt(0)))))
            return false;
        if (!Character.isDigit(position.charAt(1)))
            return false;
        if (numberCoordinate(position) < 1 || numberCoordinate(position) > 8)
            return false;
        return true;
    }

    protected abstract boolean legalForThatKindOfPiece(String position);

    public ChessPiece(String position, Color color) {
        if (!validPosition(position))
            throw new IllegalArgumentException("Position invalid or out of range!");
        this.position = position;
        this.color = color;
    }

    public String getPosition() {
        return position;
    }
    public Color getColor() {
        return color;
    }
    public void move(String position) {
        if (!validPosition(position))
            throw new IllegalArgumentException("Position invalid or out of range!");
        if (!legalForThatKindOfPiece(position))
            throw new IllegalChessMoveException("Illegal move for that kind of chess piece!");
        this.position = position.toUpperCase();
    }
}