package game.battleship.board;

import game.battleship.ship.Ship;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Screen {
  private final int size;
  private final String colHeader;
  private Spot[][] spots;

  public Screen(int size, Set<Ship> ships) {
    this.size = size;
    this.spots = fillSpots(ships);
    this.colHeader = "  " + IntStream.range(0, this.size)
        .mapToObj(i -> "" + (i + 1))
        .collect(Collectors.joining(" "));
  }

  public void update(Set<Ship> ships) {
    this.spots = fillSpots(ships);
  }

  private Spot[][] fillSpots(Set<Ship> ships) {
    Spot[][] spots = new Spot[this.size][this.size];
    for (int row = 0; row < this.size; row++) {
      for (int col = 0; col < this.size; col++) {
        spots[row][col] = Spot.free(row, col);
      }
    }
    ships.stream()
        .flatMap(ship -> ship.getSpots().stream())
        .forEach(s -> spots[s.getRow()][s.getCol()] = s);
    return spots;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(this.colHeader);
    for (int row = 0; row < this.size; row++) {
      sb.append('\n').append(((char) ('A' + row))).append(" ");
      for (int col = 0; col < this.size; col++) {
        sb.append(spots[row][col]).append(' ');
      }
    }
    return sb.toString();
  }
}
