package game.battleship;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BattleshipTest {
  @Test
  void whenCreate_thenOk() {
    Battleship battleship = new Battleship();
    assertThat(battleship).isNotNull();
    assertThat(battleship.getPlayers()).isEmpty();
    assertThat(battleship.getMaxPlayers()).isEqualTo(2);
  }
}