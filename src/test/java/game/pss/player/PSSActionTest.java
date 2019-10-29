package game.pss.player;

import game.pss.strategy.PSSAction;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PSSActionTest {
    @Test
    public void paper() {
        assertThat(PSSAction.PAPER.usedAgainst(PSSAction.PAPER)).isZero();
        assertThat(PSSAction.PAPER.usedAgainst(PSSAction.SCISSORS)).isNegative();
        assertThat(PSSAction.PAPER.usedAgainst(PSSAction.STONE)).isPositive();
    }

    @Test
    public void scissors() {
        assertThat(PSSAction.SCISSORS.usedAgainst(PSSAction.PAPER)).isPositive();
        assertThat(PSSAction.SCISSORS.usedAgainst(PSSAction.SCISSORS)).isZero();
        assertThat(PSSAction.SCISSORS.usedAgainst(PSSAction.STONE)).isNegative();
    }

    @Test
    public void stone() {
        assertThat(PSSAction.STONE.usedAgainst(PSSAction.PAPER)).isNegative();
        assertThat(PSSAction.STONE.usedAgainst(PSSAction.SCISSORS)).isPositive();
        assertThat(PSSAction.STONE.usedAgainst(PSSAction.STONE)).isZero();
    }
}