package game.pss.player;

import game.playable.Player;
import game.playable.PlayerStrategy;
import game.pss.strategy.PSSAction;
import game.pss.strategy.PSSSimpleStrategy;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Paper, scissors, stone player.
 */
public class PSSPlayer extends Player {
    private static final String DEFAULT_NAME = "UnnamedPlayer";
    private static final int DEFAULT_SCORE_INCREMENT = 1;

    private final PlayerStrategy<PSSAction> strategy;
    private PSSAction selectedAction;
    private int score = 0;

    private PSSPlayer(Builder builder) {
        super(builder.name);
        this.strategy = builder.strategy;
    }

    @Override
    public void play() {
        System.out.println(String.format("%s is playing", this));
        selectAction();
    }

    private void selectAction() {
        this.selectedAction = this.strategy.perform();
        System.out.println("Choose " + this.selectedAction);
    }

    @Override
    public void reset() {
        this.score = 0;
        this.selectedAction = null;
    }

    /**
     * Increments score by default value.
     *
     * @return the player
     */
    public PSSPlayer gain() {
        return gain(DEFAULT_SCORE_INCREMENT);
    }

    /**
     * Increments score.
     *
     * @param value the value
     * @return the player
     */
    public PSSPlayer gain(int value) {
        checkArgument(value >= 0);
        this.score += value;
        return this;
    }

    /**
     * Gets the current player action.
     *
     * @return the pss action
     */
    public PSSAction selectedAction() {
        return this.selectedAction;
    }

    /**
     * Sets selected action.
     *
     * @param selectedAction the selected action
     */
    public void setSelectedAction(PSSAction selectedAction) {
        this.selectedAction = selectedAction;
    }

    /**
     * Gets the score.
     *
     * @return the int
     */
    public int score() {
        return score;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return name() + " (SCORE " + this.score + ")";
    }

    /**
     * Gets builder.
     *
     * @param name the name
     * @return the builder
     */
    public static Builder builder(String name) {
        return new Builder(name);
    }

    /**
     * Unnamed player.
     *
     * @return the unnamedPlayer player
     */
    public static PSSPlayer unnamedPlayer() {
        return builder(DEFAULT_NAME).build();
    }

    /**
     * Builder.
     */
    public static class Builder {
        private final String name;
        private PlayerStrategy<PSSAction> strategy = PSSSimpleStrategy.RANDOM;

        private Builder(String name) {
            this.name = name;
        }

        /**
         * Build pss player.
         *
         * @return the pss player
         */
        public PSSPlayer build() {
            return new PSSPlayer(this);
        }

        /**
         * Strategy builder.
         *
         * @param strategy the strategy
         * @return the builder
         */
        public Builder strategy(PlayerStrategy<PSSAction> strategy) {
            this.strategy = strategy;
            return this;
        }
    }
}
