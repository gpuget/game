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
          Spot.occupied(0, 0),
          Spot.occupied(1, 0),
          Spot.occupied(2, 0),
          Spot.occupied(3, 0),
          Spot.occupied(4, 0)
      )),
      new Ship(ShipType.BATTLESHIP, List.of(
          Spot.occupied(0, 1),
          Spot.occupied(1, 1),
          Spot.occupied(2, 1),
          Spot.occupied(3, 1)
      )),
      new Ship(ShipType.CRUISER, List.of(
          Spot.occupied(0, 2),
          Spot.occupied(1, 2),
          Spot.occupied(2, 2)
      )),
      new Ship(ShipType.DESTROYER, List.of(
          Spot.occupied(0, 3),
          Spot.occupied(1, 3),
          Spot.occupied(2, 3)
      )),
      new Ship(ShipType.PATROL_BOAT, List.of(
          Spot.occupied(0, 4),
          Spot.occupied(1, 4)
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