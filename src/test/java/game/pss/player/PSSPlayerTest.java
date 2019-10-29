package game.pss.player;

import game.pss.strategy.PSSAction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PSSPlayerTest {
    private final PSSPlayer player = PSSPlayer.unnamedPlayer();

    @AfterEach
    void tearDown() {
        this.player.reset();
    }

    @Test
    void play() {
        assertThat(this.player.selectedAction()).isNull();
        this.player.play();
        assertThat(player.selectedAction()).isNotNull();
    }

    @Test
    void reset() {
        this.player.setScore(10);
        this.player.setSelectedAction(PSSAction.PAPER);
        this.player.reset();
        assertThat(this.player.score()).isZero();
        assertThat(this.player.selectedAction()).isNull();
    }

    @Test
    void gain() {
        int value = 2;
        assertThat(this.player.score()).isZero();
        this.player.gain(value);
        assertThat(this.player.score()).isEqualTo(value);
    }

    @Test
    void gainNegative() {
        assertThat(this.player.score()).isZero();
        assertThrows(IllegalArgumentException.class, () -> this.player.gain(-2));
    }
}