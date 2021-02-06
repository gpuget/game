package game.battleship;

import static org.assertj.core.api.Assertions.assertThat;

import game.battleship.board.Board;
import game.battleship.board.Spot;
import game.battleship.ship.Ship;
import game.battleship.ship.ShipType;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BattleshipTest {
  private static final Ship CARRIER = new Ship(ShipType.CARRIER, List.of(
      new Spot(0, 0),
      new Spot(1, 0),
      new Spot(2, 0),
      new Spot(3, 0),
      new Spot(4, 0)
  ));

  @BeforeEach
  void setUp() {
    System.setIn(new BufferedInputStream(new ByteArrayInputStream("A1D\nA1D\n".getBytes())));
  }

  @Test
  void whenCreate_thenOk() {
    Battleship battleship = new Battleship();

    assertThat(battleship).isNotNull();
    assertThat(battleship.getPlayers()).isEmpty();
    assertThat(battleship.getMaxPlayers()).isEqualTo(2);
    assertThat(battleship.getName()).isEqualTo(Battleship.NAME);
  }

  @Test
  void whenCreatePlayer_thenPlayer1() {
    Battleship battleship = new Battleship();
    Board board = Board.empty();
    board.places(CARRIER);

    BattleshipPlayer player1 = battleship.createPlayer();

    assertThat(player1).isEqualTo(new BattleshipPlayer("Player 1", board));
  }

  @Test
  void givenOnePlayerInGame_whenNewPlayer_then2Players() {
    Battleship battleship = new Battleship();
    BattleshipPlayer player1 = battleship.newPlayer();
    Board board = Board.empty();
    board.places(CARRIER);

    BattleshipPlayer player2 = battleship.newPlayer();

    assertThat(battleship.getPlayers()).hasSize(2);
    assertThat(battleship.getPlayers().get(1)).isEqualTo(new BattleshipPlayer("Player 2", board));
  }
}