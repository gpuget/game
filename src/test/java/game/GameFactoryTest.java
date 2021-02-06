package game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GameFactoryTest {
  @Test
  void create_battleship() {
    assertThat(GameFactory.BATTLESHIP.create()).isNotNull();
  }
}