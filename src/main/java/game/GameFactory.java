package game;

import java.util.function.Supplier;

public enum GameFactory {
  ;

  public final Supplier<Game> gameSupplier;

  GameFactory(Supplier<Game> gameSupplier) {
    this.gameSupplier = gameSupplier;
  }

  GameFactory() {
    this.gameSupplier = () -> null;
  }

  public Game create() {
    return this.gameSupplier.get();
  }
}
