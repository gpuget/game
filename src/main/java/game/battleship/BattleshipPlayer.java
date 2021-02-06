package game.battleship;

import game.Player;
import game.battleship.board.Board;

public class BattleshipPlayer extends Player {
  private final Board board;

  public BattleshipPlayer(String name, Board board) {
    super(name);
    this.board = board;
  }
}
