package game.battleship;

import game.PlayableGame;
import java.util.LinkedList;

public class Battleship extends PlayableGame<BattleshipPlayer> {
  private static final int NUMBER_OF_PLAYER = 2;
  private static final String DEFAULT_NAME_PLAYER = "Player ";

  public Battleship() {
    super(new LinkedList<>(), NUMBER_OF_PLAYER);
  }

  @Override
  protected boolean doInit() {
    return false;
  }

  @Override
  protected BattleshipPlayer createPlayer() {
    return new BattleshipPlayer(DEFAULT_NAME_PLAYER + getPlayers().size() + 1);
  }
}
