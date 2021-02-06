package game.battleship.board;

import game.battleship.exceptions.ShipAlreadyPlacedException;
import game.battleship.ship.Ship;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.IntUnaryOperator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Board {
  private static final Pattern PATTERN = Pattern.compile("^[A-J]([1-9]|10)(LRUD)$");
  private static final int SIZE = 10;
  private final Set<Ship> ships = new HashSet<>();

  private Board() {

  }

  public static Board empty() {
    return new Board();
  }

  public static List<Spot> spots(String code, int number) {
    if (PATTERN.matcher(code).matches()) {
      char row = code.charAt(0);
      String col = code.substring(1, code.length() - 1);
      char orientation = code.charAt(code.length() - 1);
      return spots(row - 'A' - 1, Integer.parseInt(col) - 1, Orientation.valueOf(orientation), number);
    }
    return Collections.emptyList();
  }

  private static List<Spot> spots(int row, int col, Orientation orientation, int number) {
    IntUnaryOperator rowShift;
    IntUnaryOperator colShift;
    switch (orientation) {
      case LEFT:
        rowShift = value -> row;
        colShift = value -> col - value;
        break;
      case RIGHT:
        rowShift = value -> row;
        colShift = value -> col + value;
        break;
      case UP:
        rowShift = value -> row - value;
        colShift = value -> col;
        break;
      case DOWN:
        rowShift = value -> row + value;
        colShift = value -> col;
        break;
      default:
        rowShift = value -> row;
        colShift = value -> col;
    }
    if (checkPosition(rowShift.applyAsInt(number)) && checkPosition(colShift.applyAsInt(number))) {
      return IntStream.range(0, SIZE)
          .mapToObj(shift -> new Spot(rowShift.applyAsInt(shift), colShift.applyAsInt(shift)))
          .collect(Collectors.toList());
    }
    return Collections.emptyList();
  }

  private static boolean checkPosition(int value) {
    return value >= 0 && value < SIZE;
  }

  public Board places(Ship ship) {
    if (alreadyOnBoard(ship)) {
      throw new ShipAlreadyPlacedException();
    }

    this.ships.add(ship);
    return this;
  }

  private boolean alreadyOnBoard(Ship ship) {
    return this.ships.stream()
        .anyMatch(s -> s.getType() == ship.getType() ||
            s.getSpots().stream().noneMatch(spot -> ship.getSpots().contains(spot)));
  }
}
