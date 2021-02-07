package game;

import java.util.function.Supplier;

public enum GameFactory {
  ;

  public final Supplier<GenericGame> gameSupplier;

  GameFactory(Supplier<GenericGame> gameSupplier) {
    this.gameSupplier = gameSupplier;
  }

  public GenericGame create() {
    return this.gameSupplier.get();
  }
}
