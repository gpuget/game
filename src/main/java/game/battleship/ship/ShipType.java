package game.battleship.ship;

public enum ShipType {
  CARRIER(5),
  BATTLESHIP(4),
  CRUISER(3),
  DESTROYER(3),
  PATROL_BOAT(2);

  private final int size;

  ShipType(int size) {
    this.size = size;
  }

  public int getSize() {
    return size;
  }
}
