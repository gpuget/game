package game.pss.strategy;

/**
 * The enum Pss action.
 */
public enum PSSAction {
    /**
     * The Paper.
     */
    PAPER {
        @Override
        public int usedAgainst(PSSAction action) {
            if (action == SCISSORS) {
                return -1;
            }

            if (action == STONE) {
                return 1;
            }

            return 0;
        }
    },
    /**
     * The Scissors.
     */
    SCISSORS {
        @Override
        public int usedAgainst(PSSAction action) {
            if (action == STONE) {
                return -1;
            }

            if (action == PAPER) {
                return 1;
            }

            return 0;
        }
    },
    /**
     * The Stone.
     */
    STONE {
        @Override
        public int usedAgainst(PSSAction action) {
            if (action == PAPER) {
                return -1;
            }

            if (action == SCISSORS) {
                return 1;
            }

            return 0;
        }

    };

    /**
     * Compares the current action to another.
     *
     * @param action the action to compare
     * @return 1 if action beats the provided action, -1 if action is beaten by the provided action and 0 otherwise
     */
    public abstract int usedAgainst(PSSAction action);
}
