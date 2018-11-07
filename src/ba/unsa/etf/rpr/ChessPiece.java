package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.ArrayList.*;

public abstract class ChessPiece {

    public static enum Color {
        BLACK, WHITE
    }

    private String ispravni = "ABCDEFGHabcdefgh";

    protected String position;
    protected Color color;

    private boolean ispravnaPozicija(String position) {
        if (position.length() != 2)
            return false;
        if (!ispravni.contains(Character.toString(position.charAt(0))))
            return false;
        if (!Character.isDigit(position.charAt(1)))
            return false;
        if (position.charAt(1) < 1 || position.charAt(1) > 8)
            return false;
        return true;
    }

    public ChessPiece(String position, Color color) {
        if (!ispravnaPozicija(position))
            throw new IllegalArgumentException("Pozicija je neispravnog formata ili je van table!");
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
            throw new IllegalArgumentException("Pozicija je neispravnog formata ili je van table!");
        this.position = position;
    }
}
