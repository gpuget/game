package game.battleship.board;

import java.util.Objects;
import java.util.regex.Pattern;

public class Position {
  private static final Pattern PATTERN = Pattern.compile("^[A-Z]([1-9]?\\d)[LRUD]$");
  private static final Position NONE = new Position(-1, -1, null);

  private final int row;
  private final int col;
  private final Orientation orientation;

  private Position(int row, int col, Orientation orientation) {
    this.row = row;
    this.col = col;
    this.orientation = orientation;
  }

  public static Position decode(String code) {
    if (PATTERN.matcher(code).matches()) {
      char row = code.charAt(0);
      String col = code.substring(1, code.length() - 1);
      char orientation = code.charAt(code.length() - 1);
      return new Position(row - 'A', Integer.parseInt(col) - 1, Orientation.valueOf(orientation));
    }
    return Position.none();
  }

  public static Position none() {
    return NONE;
  }

  public boolean isValid() {
    return orientation != null;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public Orientation getOrientation() {
    return orientation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Position position = (Position) o;
    return row == position.row && col == position.col && orientation == position.orientation;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, col, orientation);
  }
}
