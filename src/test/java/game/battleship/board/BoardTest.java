package game.battleship.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import game.battleship.exceptions.ShipAlreadyPlacedException;
import game.battleship.ship.Ship;
import game.battleship.ship.ShipType;
import java.util.List;
import org.junit.jupiter.api.Test;

class BoardTest {
  private static final Ship CARRIER = new Ship(ShipType.CARRIER, List.of(
      Spot.occupied(0, 0),
      Spot.occupied(1, 0),
      Spot.occupied(2, 0),
      Spot.occupied(3, 0),
      Spot.occupied(4, 0)
  ));
  private static final Ship DESTROYER = new Ship(ShipType.DESTROYER, List.of(
      Spot.occupied(8, 0),
      Spot.occupied(7, 0),
      Spot.occupied(6, 0),
      Spot.occupied(5, 0),
      Spot.occupied(4, 0)
  ));

  @Test
  void empty() {
    assertThat(Board.empty()).isEqualTo(new Board());
  }

  @Test
  void givenNumberZero_whenCreateSpots_thenEmpty() {
    assertThat(Board.spots("A1D", 0)).isEmpty();
  }

  @Test
  void givenEmptyCode_whenCreateSpots_thenEmpty() {
    assertThat(Board.spots("", 1)).isEmpty();
  }

  @Test
  void givenWrongCodes_whenCreateSpots_thenEmpty() {
    assertThat(Board.spots("K1D", 1)).isEmpty();
    assertThat(Board.spots("A11D", 1)).isEmpty();
    assertThat(Board.spots("A1Z", 1)).isEmpty();
  }

  @Test
  void givenGoodCode_whenCreateSpots_thenOk() {
    int number = 1;
    List<Spot> spots = Board.spots("A1D", number);

    assertThat(spots).hasSize(number);
    assertThat(spots.get(0)).isEqualTo(Spot.occupied(0, 0));
  }

  @Test
  void givenNumberAndLeft_whenCreateSpots_thenOk() {
    List<Spot> spots = Board.spots("J10L", 3);
    assertThat(spots)
        .isEqualTo(List.of(Spot.occupied(9, 9), Spot.occupied(9, 8), Spot.occupied(9, 7)));
  }

  @Test
  void givenNumberAndRight_whenCreateSpots_thenOk() {
    List<Spot> spots = Board.spots("A1R", 3);
    assertThat(spots)
        .isEqualTo(List.of(Spot.occupied(0, 0), Spot.occupied(0, 1), Spot.occupied(0, 2)));
  }

  @Test
  void givenNumberAndUp_whenCreateSpots_thenOk() {
    List<Spot> spots = Board.spots("J10U", 3);
    assertThat(spots)
        .isEqualTo(List.of(Spot.occupied(9, 9), Spot.occupied(8, 9), Spot.occupied(7, 9)));
  }

  @Test
  void givenNumberAndDown_whenCreateSpots_thenOk() {
    List<Spot> spots = Board.spots("A1D", 3);
    assertThat(spots)
        .isEqualTo(List.of(Spot.occupied(0, 0), Spot.occupied(1, 0), Spot.occupied(2, 0)));
  }

  @Test
  void givenNumberThatOutOfBound_whenCreateSpots_thenEmpty() {
    List<Spot> spots = Board.spots("A1L", 3);

    assertThat(spots).isEmpty();
  }

  @Test
  void givenEmptyBoard_whenPlacesShip_thenOk() {
    assertThat(Board.empty().places(CARRIER).getShips()).hasSize(1);
  }

  @Test
  void givenBoardWithCarrier_whenPlacesCarrier_thenKo() {
    Board board = Board.empty().places(CARRIER);

    assertThatThrownBy(() -> board.places(CARRIER))
        .isInstanceOf(ShipAlreadyPlacedException.class);
  }

  @Test
  void givenBoardWithCarrier_whenPlacesDestroyerOnSameSpot_thenKo() {
    Board board = Board.empty().places(CARRIER);

    assertThatThrownBy(() -> board.places(DESTROYER))
        .isInstanceOf(ShipAlreadyPlacedException.class);
  }
}