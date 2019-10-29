package game.playable;

/**
 * Player.
 */
public abstract class Player {
    private final String name;

    protected Player(Builder<?> builder) {
        this.name = builder.name;
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

    protected static abstract class Builder<T extends Player> {
        protected final String name;

        protected Builder(String name) {
            this.name = name;
        }

        public abstract T build();
    }
}
