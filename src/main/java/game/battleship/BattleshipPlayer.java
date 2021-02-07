package game.battleship;

import game.Player;
import game.battleship.board.Board;
import java.util.Objects;

public class BattleshipPlayer extends Player {
  private final Board board;

  public BattleshipPlayer(String name, Board board) {
    super(name);
    this.board = board;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    BattleshipPlayer that = (BattleshipPlayer) o;
    return Objects.equals(board, that.board);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), board);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("[" + getName() + "]").append('\n');
    sb.append(board);
    return sb.toString();
  }
}
