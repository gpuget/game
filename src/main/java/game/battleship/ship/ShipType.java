package game.battleship.ship;

public enum ShipType {
  CARRIER(5);

  private final int size;

  ShipType(int size) {
    this.size = size;
  }

  public int getSize() {
    return size;
  }
}
