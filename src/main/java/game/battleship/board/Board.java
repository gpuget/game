package game.battleship.board;

import game.battleship.exceptions.ShipAlreadyPlacedException;
import game.battleship.ship.Ship;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.IntUnaryOperator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Board {
  private static final int SIZE = 10;

  private final Set<Ship> ships;
  private Screen mainScreen;

  private Board(Set<Ship> ships) {
    this.ships = new HashSet<>(ships);
    this.mainScreen = new Screen(SIZE, ships);
  }

  Board() {
    this.ships = new HashSet<>();
    this.mainScreen = new Screen(SIZE, Collections.emptySet());
  }

  public static Board empty() {
    return new Board();
  }

  public static Board with(Set<Ship> ships) {
    return new Board(ships);
  }

  public static List<Spot> spots(String code, int number) {
    if (checkLimit(number)) {
      return spots(Position.decode(code), number);
    }
    return Collections.emptyList();
  }

  private static List<Spot> spots(Position position, int number) {
    if (position.isValid()) {
      IntUnaryOperator rowShift;
      IntUnaryOperator colShift;
      switch (position.getOrientation()) {
        case LEFT:
          rowShift = value -> position.getRow();
          colShift = value -> position.getCol() - value;
          break;
        case RIGHT:
          rowShift = value -> position.getRow();
          colShift = value -> position.getCol() + value;
          break;
        case UP:
          rowShift = value -> position.getRow() - value;
          colShift = value -> position.getCol();
          break;
        case DOWN:
          rowShift = value -> position.getRow() + value;
          colShift = value -> position.getCol();
          break;
        default:
          rowShift = value -> position.getRow();
          colShift = value -> position.getCol();
      }
      if (checkLimit(rowShift.applyAsInt(number)) && checkLimit(colShift.applyAsInt(number))) {
        return IntStream.range(0, number)
            .mapToObj(
                shift -> Spot.occupied(rowShift.applyAsInt(shift), colShift.applyAsInt(shift)))
            .collect(Collectors.toList());
      }
    }
    return Collections.emptyList();
  }

  private static boolean checkLimit(int value) {
    return value >= 0 && value < SIZE;
  }

  public Board places(Ship ship) {
    if (alreadyOnBoard(ship)) {
      throw new ShipAlreadyPlacedException();
    }

    this.ships.add(ship);
    this.mainScreen.update(this.ships);
    return this;
  }

  private boolean alreadyOnBoard(Ship ship) {
    return this.ships.stream()
        .anyMatch(s -> s.getType() == ship.getType() ||
            s.getSpots().stream().anyMatch(spot -> ship.getSpots().contains(spot)));
  }

  public Set<Ship> getShips() {
    return ships;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Board board = (Board) o;
    return Objects.equals(ships, board.ships);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ships);
  }

  @Override
  public String toString() {
    return mainScreen.toString();
  }
}
