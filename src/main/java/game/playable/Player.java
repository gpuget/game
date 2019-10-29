package game.playable;

/**
 * Player.
 */
public abstract class Player {
    private final String name;

    /**
     * Instantiates a new named Player.
     *
     * @param name the name
     */
    protected Player(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Gets the name.
     *
     * @return the string
     */
    public String name() {
        return name;
    }

    /**
     * Plays.
     */
    public abstract void play();

    /**
     * Resets the player.
     */
    public abstract void reset();
}
