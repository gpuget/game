package game.battleship.ship;

import game.battleship.board.Spot;
import java.util.List;

public class Ship {
  private final ShipType type;
  private final List<Spot> spots;

  public Ship(ShipType type, List<Spot> spots) {
    this.type = type;
    this.spots = List.copyOf(spots);
  }

  public ShipType getType() {
    return type;
  }

  public List<Spot> getSpots() {
    return spots;
  }
}
