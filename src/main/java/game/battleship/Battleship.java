package game.battleship;

import game.PlayableGame;
import java.util.LinkedList;

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
    return new BattleshipPlayer(playerName);
  }
}
