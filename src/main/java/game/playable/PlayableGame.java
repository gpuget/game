package game.playable;

import game.Game;

import java.util.List;
import java.util.Optional;

/**
 * The interface Playable game.
 *
 * @param <T> the type of player
 */
public interface PlayableGame<T extends Player> extends Game {
    /**
     * Gets the list.
     *
     * @return the list
     */
    List<T> players();

    /**
     * Gets the player in the list.
     *
     * @param index the index
     * @return the t
     */
    default T player(int index) {
        return players().get(index);
    }

    /**
     * Gets the game winner.
     *
     * @return the game winner, unnamedPlayer optional if draw game
     */
    Optional<T> gameWinner();
}
