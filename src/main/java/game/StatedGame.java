package game;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The type Stated game.
 */
public abstract class StatedGame implements Game {
    private State state = State.INITIALISED;

    @Override
    public final void start() {
        System.out.println("Game starting...");
        setState(State.STARTED);
        doStart();

        do {
            resolve();
            if (over()) {
                setState(State.ENDED);
            }
        } while (state() != State.ENDED);

        end();
    }

    @Override
    public final void reset() {
        setState(State.INITIALISED);
        doReset();
    }

    /**
     * Performs the specific reset.
     */
    protected abstract void doReset();

    /**
     * Performs the specific start.
     */
    protected abstract void doStart();

    /**
     * Gets the game over condition.
     *
     * @return {@code true} if game is over, {@code false} otherwise
     */
    protected abstract boolean over();

    /**
     * Gets the game state.
     *
     * @return the state
     */
    public State state() {
        return this.state;
    }

    /**
     * Sets the state.
     *
     * @param state the state
     */
    public void setState(State state) {
        this.state = checkNotNull(state);
    }

    /**
     * The enum State.
     */
    public enum State {
        /**
         * Initialised state.
         */
        INITIALISED,
        /**
         * Started state.
         */
        STARTED,
        /**
         * Ended state.
         */
        ENDED
    }
}
