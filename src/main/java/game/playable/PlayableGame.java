package game.playable;

import game.StatedGame;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

public abstract class PlayableGame<T extends Player> extends StatedGame {
    private final List<T> players;

    protected PlayableGame(List<T> players) {
        this.players = players;
        checkArgument(this.players != null && !this.players.isEmpty());
    }

    /**
     * Gets the list.
     *
     * @return the list
     */
    public List<T> players() {
        return this.players;
    }

    /**
     * Gets the player in the list.
     *
     * @param index the index
     * @return the t
     */
    public T player(int index) {
        return players().get(index);
    }

    /**
     * Gets the game winner.
     *
     * @return the game winner, empty optional if draw game
     */
    public abstract Optional<T> winner();
}
