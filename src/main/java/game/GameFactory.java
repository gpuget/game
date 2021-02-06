package game;

import game.battleship.Battleship;
import java.util.function.Supplier;

public enum GameFactory {
  BATTLESHIP(Battleship::new);

  public final Supplier<Game> gameSupplier;

  GameFactory(Supplier<Game> gameSupplier) {
    this.gameSupplier = gameSupplier;
  }

  public Game create() {
    return this.gameSupplier.get();
  }
}
