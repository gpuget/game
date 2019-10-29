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
     * Gets the game winner.
     *
     * @return the game winner, empty optional if draw game
     */
    Optional<T> winner();
}
