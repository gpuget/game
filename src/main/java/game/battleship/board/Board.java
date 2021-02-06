package game.battleship.board;

import game.battleship.exceptions.ShipAlreadyPlacedException;
import game.battleship.ship.Ship;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public final class Board {
  private static final Pattern PATTERN = Pattern.compile("^[A-J]([1-9]|10)(LRUD)$");
  private static final int SIZE = 10;
  private final Set<Ship> ships = new HashSet<>();

  public static List<Spot> spots(String code) {
    if (PATTERN.matcher(code).matches()) {
      char row = code.charAt(0);
      String col = code.substring(1, code.length() - 1);
      char orientation = code.charAt(code.length() - 1);
      return spots(row - 'A', Integer.parseInt(col), Orientation.valueOf(orientation));
    }
    return Collections.emptyList();
  }

  private static List<Spot> spots(int row, int col, Orientation orientation) {
    return null;
  }

  public Board places(Ship ship) {
    if (alreadyOnBoard(ship)) {
      throw new ShipAlreadyPlacedException();
    }

    this.ships.add(ship);
    return this;
  }

  private boolean alreadyOnBoard(Ship ship) {
    return this.ships.stream().anyMatch(s -> s.getType() == ship.getType());
  }
}
