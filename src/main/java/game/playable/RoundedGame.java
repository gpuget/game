package game.playable;

import game.StatedGame;
import game.playable.PlayableGame;
import game.playable.Player;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Rounded game.
 *
 * @param <T> the type of player
 */
public abstract class RoundedGame<T extends Player> extends StatedGame implements PlayableGame<T> {
    private final List<T> players;
    private final int bestOf;
    private int currentRound = 0;

    /**
     * Instantiates a new Rounded game.
     *
     * @param players the players
     * @param bestOf  the best of
     */
    public RoundedGame(List<T> players, int bestOf) {
        this.players = players;
        this.bestOf = bestOf;
        checkArgument(players != null);
        checkArgument(this.bestOf > 0);
    }

    @Override
    public void resolve() {
        resolveRound();
        nextRound();
    }

    @Override
    public void end() {
        System.out.println(String.format("Game is over (%d/%d) : %s", this.currentRound - 1, this.bestOf, endMessage()));
    }

    /**
     * Gets the end message.
     *
     * @return the string
     */
    protected String endMessage() {
        return gameWinner().map(p -> p + " is the winner.").orElse("DRAW");
    }

    @Override
    protected void doReset() {
        this.currentRound = 0;
        this.players.forEach(Player::reset);
    }

    @Override
    protected void doStart() {
        setCurrentRound(1);
    }

    @Override
    public boolean over() {
        return this.currentRound > this.bestOf;
    }

    @Override
    public List<T> players() {
        return this.players;
    }

    /**
     * Increments the round.
     */
    public void nextRound() {
        this.currentRound++;
    }

    /**
     * Resolves the round.
     */
    public abstract void resolveRound();

    /**
     * Gets the the best of.
     *
     * @return the best of
     */
    public int bestOf() {
        return this.bestOf;
    }

    /**
     * Gets the current round.
     *
     * @return the current round
     */
    public int currentRound() {
        return currentRound;
    }

    /**
     * Sets current round.
     *
     * @param currentRound the current round
     */
    public void setCurrentRound(int currentRound) {
        checkArgument(currentRound >= 0);
        this.currentRound = currentRound;
    }
}
