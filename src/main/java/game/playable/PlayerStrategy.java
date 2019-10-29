package game.playable;

/**
 * The interface Player strategy.
 *
 * @param <T> the type of action
 */
public interface PlayerStrategy<T> {
    /**
     * Performs the strategy.
     *
     * @return the type of action
     */
    T perform();
}
