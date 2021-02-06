package game.battleship.board;

import java.util.Set;

public final class Board {
  private final Set<Ship> ships;

  private Board(Set<Ship> ships) {
    this.ships = ships;
  }

  public static Board places(Set<Ship> ships) {
    return new Board(ships);
  }
}
