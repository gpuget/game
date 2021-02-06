package game.battleship;

import static org.assertj.core.api.Assertions.assertThat;

import game.battleship.board.Board;
import game.battleship.board.Spot;
import game.battleship.ship.Ship;
import game.battleship.ship.ShipType;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BattleshipTest {
  private static final Set<Ship> SHIPS = Set.of(
      new Ship(ShipType.CARRIER, List.of(
          new Spot(0, 0),
          new Spot(1, 0),
          new Spot(2, 0),
          new Spot(3, 0),
          new Spot(4, 0)
      )),
      new Ship(ShipType.BATTLESHIP, List.of(
          new Spot(0, 1),
          new Spot(1, 1),
          new Spot(2, 1),
          new Spot(3, 1)
      )),
      new Ship(ShipType.CRUISER, List.of(
          new Spot(0, 2),
          new Spot(1, 2),
          new Spot(2, 2)
      )),
      new Ship(ShipType.DESTROYER, List.of(
          new Spot(0, 3),
          new Spot(1, 3),
          new Spot(2, 3)
      )),
      new Ship(ShipType.PATROL_BOAT, List.of(
          new Spot(0, 4),
          new Spot(1, 4)
      ))
  );

  @BeforeEach
  void setUp() {
    System.setIn(new BufferedInputStream(
        new ByteArrayInputStream("A1D\nA2D\nA3D\nA4D\nA5D\nA1D\nA2D\nA3D\nA4D\nA5D\n".getBytes())));
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
    Board board = Board.with(SHIPS);

    BattleshipPlayer player1 = battleship.createPlayer();

    assertThat(player1).isEqualTo(new BattleshipPlayer("Player 1", board));
  }

  @Test
  void givenOnePlayerInGame_whenNewPlayer_then2Players() {
    Battleship battleship = new Battleship();
    battleship.newPlayer();
    Board board = Board.with(SHIPS);

    battleship.newPlayer();

    assertThat(battleship.getPlayers()).hasSize(2);
    assertThat(battleship.getPlayers().get(1)).isEqualTo(new BattleshipPlayer("Player 2", board));
  }
}