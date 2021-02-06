package game.battleship;

import game.PlayableGame;
import game.battleship.board.Board;
import game.battleship.board.Ship;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Set;

public class Battleship extends PlayableGame<BattleshipPlayer> {
  static final String NAME = "BATTLESHIP";
  private static final int NUMBER_OF_PLAYER = 2;
  private static final String DEFAULT_NAME_PLAYER = "Player ";

  public Battleship() {
    super(NAME, new LinkedList<>(), NUMBER_OF_PLAYER);
  }

  @Override
  protected BattleshipPlayer createPlayer() {
    String playerName = DEFAULT_NAME_PLAYER + (getPlayers().size() + 1);
    Board board = createBoard();
    return new BattleshipPlayer(playerName, board);
  }

  private Board createBoard() {
    return Board.places(ships());
  }

  private Set<Ship> ships() {
    return Collections.emptySet();
  }
}
