package game.battleship.board;

import static org.assertj.core.api.Assertions.assertThat;

import game.battleship.ship.Ship;
import game.battleship.ship.ShipType;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ScreenTest {
  @Test
  void givenEmptyScreen_whenToString_thenOk() {
    assertThat(new Screen(10, Collections.emptySet()).toString()).isEqualTo(
        "  1 2 3 4 5 6 7 8 9 10\n" +
        "A . . . . . . . . . . \n" +
        "B . . . . . . . . . . \n" +
        "C . . . . . . . . . . \n" +
        "D . . . . . . . . . . \n" +
        "E . . . . . . . . . . \n" +
        "F . . . . . . . . . . \n" +
        "G . . . . . . . . . . \n" +
        "H . . . . . . . . . . \n" +
        "I . . . . . . . . . . \n" +
        "J . . . . . . . . . . "
    );
  }

  @Test
  void givenFilledScreen_whenToString_thenOk() {
    List<Spot> spots = List.of(Spot.occupied(0, 0), Spot.occupied(0, 1));
    Set<Ship> ships = Set.of(new Ship(ShipType.PATROL_BOAT, spots));
    Screen screen = new Screen(10, ships);

    assertThat(screen.toString()).isEqualTo(
        "  1 2 3 4 5 6 7 8 9 10\n" +
        "A O O . . . . . . . . \n" +
        "B . . . . . . . . . . \n" +
        "C . . . . . . . . . . \n" +
        "D . . . . . . . . . . \n" +
        "E . . . . . . . . . . \n" +
        "F . . . . . . . . . . \n" +
        "G . . . . . . . . . . \n" +
        "H . . . . . . . . . . \n" +
        "I . . . . . . . . . . \n" +
        "J . . . . . . . . . . "
    );
  }
}