package game.battleship;

import game.PlayableGame;
import game.battleship.board.Board;
import game.battleship.board.Spot;
import game.battleship.ship.Ship;
import game.battleship.ship.ShipType;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Battleship extends PlayableGame<BattleshipPlayer> {
  static final String NAME = "BATTLESHIP";
  private static final int NUMBER_OF_PLAYER = 2;
  private static final String DEFAULT_NAME_PLAYER = "Player ";
  private final Scanner SCANNER = new Scanner(System.in);

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
    Board board = new Board();
    Arrays.stream(ShipType.values())
        .map(this::createShip)
        .forEach(board::places);
    return board;
  }

  private Ship createShip(ShipType type) {
    List<Spot> spots = Board.spots(SCANNER.nextLine());
    return new Ship(type, spots);
  }
}
