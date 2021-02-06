package game.battleship.ship;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import org.junit.jupiter.api.Test;

class ShipTest {
  @Test
  void givenNull_whenCreate_thenKo() {
    assertThatThrownBy(() -> new Ship(ShipType.CARRIER, null))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("No spot for ship !");
  }

  @Test
  void givenEmptyList_whenCreate_thenKo() {
    assertThatThrownBy(() -> new Ship(ShipType.CARRIER, Collections.emptyList()))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("No spot for ship !");
  }
}