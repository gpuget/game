package game.battleship.ship;

import game.battleship.board.Spot;
import java.util.List;
import java.util.Objects;

public class Ship {
  private final ShipType type;
  private final List<Spot> spots;

  public Ship(ShipType type, List<Spot> spots) {
    if (spots == null || spots.isEmpty()) {
      throw new IllegalArgumentException("No spot for ship !");
    }
    this.type = type;
    this.spots = List.copyOf(spots);
  }

  public ShipType getType() {
    return type;
  }

  public List<Spot> getSpots() {
    return spots;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ship ship = (Ship) o;
    return type == ship.type && Objects.equals(spots, ship.spots);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, spots);
  }

  @Override
  public String toString() {
    return type + " " + spots;
  }
}
