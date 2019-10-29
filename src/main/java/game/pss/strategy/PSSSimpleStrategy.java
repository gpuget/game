package game.pss.strategy;

import game.playable.PlayerStrategy;

import java.util.Random;
import java.util.function.Supplier;

/**
 * The paper, scissors, stone simple strategies.
 */
public enum PSSSimpleStrategy implements PlayerStrategy<PSSAction> {
    /**
     * Choose randomly strategy.
     */
    RANDOM(() -> PSSAction.values()[new Random().nextInt(PSSAction.values().length)]),
    /**
     * Choose only paper strategy.
     */
    ONLY_PAPER(() -> PSSAction.PAPER),
    /**
     * Choose only stone strategy.
     */
    ONLY_STONE(() -> PSSAction.STONE);

    private final Supplier<PSSAction> action;

    PSSSimpleStrategy(Supplier<PSSAction> action) {
        this.action = action;
    }

    @Override
    public PSSAction perform() {
        return this.action.get();
    }
}
