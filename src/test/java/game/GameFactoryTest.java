package game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GameFactoryTest {
  @Test
  void create_battleship() {
    Game game = GameFactory.BATTLESHIP.create();
    assertThat(game).isNotNull();
    assertThat(game.isCreated()).isTrue();
  }
}