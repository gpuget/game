package game;

import java.util.function.Supplier;

public enum GameFactory {
  ;

  public final Supplier<Game> gameSupplier;

  GameFactory(Supplier<Game> gameSupplier) {
    this.gameSupplier = gameSupplier;
  }

  public Game create() {
    return this.gameSupplier.get();
  }
}
