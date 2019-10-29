package game.pss;

import game.playable.Player;
import game.StatedGame;
import game.pss.player.PSSPlayer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static game.pss.strategy.PSSSimpleStrategy.ONLY_PAPER;
import static game.pss.strategy.PSSSimpleStrategy.ONLY_STONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaperScissorsStoneTest {
    private final List<PSSPlayer> players = Arrays.asList(PSSPlayer.builder("").strategy(ONLY_PAPER).build(), PSSPlayer.builder("").strategy(ONLY_STONE).build());
    private final PaperScissorsStone game = new PaperScissorsStone(this.players, 3);

    @AfterEach
    public void tearDown() {
        this.game.reset();
    }

    @Test
    public void player() {
        int index = 0;
        Player player = this.game.players().get(index);
        assertThat(this.game.player(index)).isEqualTo(player);
    }

    @Test
    public void playersNull() {
        assertThrows(IllegalArgumentException.class, () -> new PaperScissorsStone(null, 0));
    }

    @Test
    public void moreThanZeroRounds() {
        assertThrows(IllegalArgumentException.class, () -> new PaperScissorsStone(this.players, 0));
    }

    @Test
    public void notEnoughPlayers() {
        assertThrows(IllegalArgumentException.class, () -> new PaperScissorsStone(Collections.emptyList(), 0));
    }

    @Test
    public void start() {
        this.game.start();
        assertThat(this.game.state()).isEqualTo(StatedGame.State.ENDED);
        assertThat(this.game.currentRound()).isNotZero();
    }

    @Test
    public void resolveWonRound() {
        this.game.resolve();
        assertThat(this.game.playerOne().score()).isNotZero();
    }

    @Test
    public void resolveDrawRound() {
        this.game.players().set(1, this.players.get(0));
        this.game.resolve();
        assertThat(this.game.playerOne().score()).isZero();
        assertThat(this.game.playerTwo().score()).isZero();
    }

    @Test
    public void reset() {
        this.game.setState(StatedGame.State.STARTED);
        this.game.setCurrentRound(1);
        this.game.reset();
        assertThat(this.game.state()).isEqualTo(StatedGame.State.INITIALISED);
        assertThat(this.game.currentRound()).isZero();
    }

    @Test
    public void playerOneWins() {
        this.game.playerOne().gain();
        assertThat(this.game.winner()).isNotEmpty().hasValue(this.game.playerOne());
    }

    @Test
    public void playerOneLoses() {
        this.game.playerTwo().gain();
        assertThat(this.game.winner()).isNotEmpty().hasValue(this.game.playerTwo());
    }

    @Test
    public void draw() {
        assertThat(this.game.winner()).isEmpty();
    }

    @Test
    public void minRoundsToWin() {
        assertThat(this.game.minRoundsToWin()).isEqualTo(2);
    }

    @Test
    public void maxNumberOfRounds() {
        this.game.setCurrentRound(this.game.bestOf() + 1);
        assertThat(this.game.over()).isTrue();
    }

    @Test
    public void earlyWin() {
        this.game.playerOne().gain().gain();
        assertThat(this.game.over()).isTrue();
    }
}