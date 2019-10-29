package game.pss;

import game.playable.RoundedGame;
import game.pss.player.PSSPlayer;
import game.pss.strategy.PSSAction;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * The type Paper scissors stone.
 */
public class PaperScissorsStone extends RoundedGame<PSSPlayer> {
    /**
     * Instantiates a new paper scissors stone.
     *
     * @param players the players
     * @param bestOf  the best of
     */
    public PaperScissorsStone(List<PSSPlayer> players, int bestOf) {
        super(players, bestOf);
        checkArgument(players().size() == 2);
    }

    @Override
    public Optional<PSSPlayer> winner() {
        return decideWinner(PSSPlayer::score, Integer::compareTo);
    }

    private <U> Optional<PSSPlayer> decideWinner(Function<PSSPlayer, U> extractor, Comparator<U> comparator) {
        PSSPlayer winner = null;
        int comparison = comparator.compare(extractor.apply(playerOne()), extractor.apply(playerTwo()));
        if (comparison > 0) {
            winner = playerOne();
        } else if (comparison < 0) {
            winner = playerTwo();
        }
        return Optional.ofNullable(winner);
    }
    @Override
    public void resolveRound() {
        System.out.println("ROUND " + currentRound());
        players().forEach(PSSPlayer::play);

        Optional<PSSPlayer> roundWinner = getRoundWinner();
        String message;
        if (roundWinner.isPresent()) {
            PSSPlayer player = roundWinner.get();
            player.gain();
            message = player + " won the round.";
        } else {
            message = "Draw for this round.";
        }
        System.out.println(message);
        System.out.println();
    }

    @Override
    public boolean over() {
        return super.over() || players().stream().map(PSSPlayer::score).anyMatch(score -> score == minRoundsToWin());
    }

    /**
     * Gets the player 1.
     *
     * @return the player 1
     */
    public PSSPlayer playerOne() {
        return player(0);
    }

    /**
     * Gets the player 2.
     *
     * @return the player 2
     */
    public PSSPlayer playerTwo() {
        return player(1);
    }

    private Optional<PSSPlayer> getRoundWinner() {
        return decideWinner(PSSPlayer::selectedAction, PSSAction::usedAgainst);
    }

    /**
     * Gets the minimum rounds to win.
     *
     * @return the minimum rounds to win
     */
    public int minRoundsToWin() {
        return Math.round(bestOf() / 2F);
    }
}
