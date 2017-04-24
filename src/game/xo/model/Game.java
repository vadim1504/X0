package game.xo.model;

public class Game {

    private final Player[] players;

    private final String name;

    private final Field field;

    public Game(Player[] players, String name, Field field) {
        this.players = players;
        this.name = name;
        this.field = field;
    }

    public Player[] getPlayers() {
        return players;
    }

    public String getName() {
        return name;
    }

    public Field getField() {
        return field;
    }
}
