package game.battleship.board;

import java.util.Objects;

public class Spot {
  private final int row;
  private final int col;

  public Spot(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Spot spot = (Spot) o;
    return row == spot.row && col == spot.col;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, col);
  }

  @Override
  public String toString() {
    return "" + ((char)('A' + row)) + (col + 1);
  }
}
