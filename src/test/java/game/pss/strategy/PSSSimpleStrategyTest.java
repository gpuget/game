package game.pss.strategy;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class PSSSimpleStrategyTest {
    @Test
    public void random() {
        IntStream.rangeClosed(1, 100).forEach(i -> assertThat(PSSSimpleStrategy.RANDOM.perform()).isNotNull());
    }

    @Test
    public void onlyPaper() {
        assertThat(PSSSimpleStrategy.ONLY_PAPER.perform()).isEqualTo(PSSAction.PAPER);
    }

    @Test
    public void onlyStone() {
        assertThat(PSSSimpleStrategy.ONLY_STONE.perform()).isEqualTo(PSSAction.STONE);
    }
}