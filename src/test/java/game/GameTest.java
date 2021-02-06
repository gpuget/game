package game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GameTest {
  @Test
  void whenGameIsCreated_thenStateIsCreated() {
    Game game = MockGame.NORMAL.value;
    assertThat(game.isCreated()).isTrue();
  }

  @Test
  void whenInit_thenStateIsInitialized() {
    Game game = MockGame.NORMAL.value;
    assertThat(game.init()).isEqualTo(Game.State.INITIALIZED);
    assertThat(game.isInitialized()).isTrue();
  }

  @Test
  void givenGameThatCannotBeInitialized_whenInit_thenStateIsStillCreated() {
    Game game = MockGame.CANNOT_BE_INITIALIZED.value;
    assertThat(game.init()).isEqualTo(Game.State.CREATED);
    assertThat(game.isInitialized()).isFalse();
  }

  private enum MockGame {
    NORMAL(new Game("Normal game") {
      @Override
      protected boolean doInit() {
        return true;
      }
    }),
    CANNOT_BE_INITIALIZED(new Game("Cannot be initialized") {

      @Override
      protected boolean doInit() {
        return false;
      }
    });

    private final Game value;

    MockGame(Game game) {
      this.value = game;
    }
  }
}