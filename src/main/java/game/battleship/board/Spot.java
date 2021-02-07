package game.battleship.board;

import java.util.Objects;

public class Spot {
  private final int row;
  private final int col;
  private char state;

  private Spot(int row, int col, char state) {
    this.row = row;
    this.col = col;
    this.state = state;
  }

  public static Spot occupied(int row, int col) {
    return new Spot(row, col, 'O');
  }

  public static Spot free(int row, int col) {
    return new Spot(row, col, '.');
  }

  public boolean isOccupied() {
    return this.state == 'O';
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public char getState() {
    return state;
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
    return row == spot.row && col == spot.col && state == spot.state;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, col, state);
  }

  @Override
  public String toString() {
    return "" + state;
  }
}
