package game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GenericGameTest {
  @Test
  void whenGameIsInstancied_thenStateIsCreated() {
    GenericGame game = MockGame.NORMAL.value;
    assertThat(game.getState()).isEqualTo(Game.State.CREATED);
  }

  @Test
  void whenInit_thenStateIsInitialized() {
    GenericGame game = MockGame.NORMAL.value;
    assertThat(game.init().getState()).isEqualTo(Game.State.INITIALIZED);
  }

  @Test
  void givenGameThatCannotBeInitialized_whenInit_thenStateIsNotInitialized() {
    GenericGame game = MockGame.CANNOT_BE_INITIALIZED.value;
    assertThat(game.init().getState()).isNotEqualTo(Game.State.INITIALIZED);
  }

  private enum MockGame {
    NORMAL(new GenericGame("Normal game") {
      @Override
      protected boolean doInit() {
        return true;
      }
    }),
    CANNOT_BE_INITIALIZED(new GenericGame("Cannot be initialized") {

      @Override
      protected boolean doInit() {
        return false;
      }
    });

    private final GenericGame value;

    MockGame(GenericGame game) {
      this.value = game;
    }
  }
}