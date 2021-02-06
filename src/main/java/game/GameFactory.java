package game;

import game.battleship.Battleship;
import java.util.function.Supplier;

public enum GameFactory {
  BATTLESHIP(Battleship::new);

  public final Supplier<GenericGame> gameSupplier;

  GameFactory(Supplier<GenericGame> gameSupplier) {
    this.gameSupplier = gameSupplier;
  }

  public GenericGame create() {
    return this.gameSupplier.get();
  }
}
