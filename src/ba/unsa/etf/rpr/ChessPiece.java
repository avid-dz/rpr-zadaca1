package ba.unsa.etf.rpr;

public abstract class ChessPiece {

    public static enum Color {
        BLACK, WHITE
    }

    protected String position;
    protected Color color;

    public static int slovnaKoordinata(String position) {
        return (int) position.charAt(0);
    }
    public static int brojevnaKoordinata(String position) {
        return (int) position.charAt(1);
    }

    private boolean ispravnaPozicija(String position) {
        if (position.length() != 2)
            return false;
        if (!"ABCDEFGHabcdefgh".contains(Character.toString(position.charAt(0))))
            return false;
        if (!Character.isDigit(position.charAt(1)))
            return false;
        if (position.charAt(1) < 1 || position.charAt(1) > 8)
            return false;
        return true;
    }

    protected abstract boolean ispravnoZaTuFiguru(String position);

    public ChessPiece(String position, Color color) {
        if (!ispravnaPozicija(position))
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
        if (!ispravnaPozicija(position))
            throw new IllegalArgumentException("Position invalid or out of range!");
        if (!ispravnoZaTuFiguru(position))
            throw new IllegalChessMoveException("Illegal move for that kind of chess piece!");
        this.position = position;
    }
}