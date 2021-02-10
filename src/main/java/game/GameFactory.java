package game;

import game.tetris.Tetris;
import java.util.function.Supplier;

public enum GameFactory {
  TETRIS(Tetris::new);

  private final Supplier<Game> gameSupplier;

  GameFactory(Supplier<Game> gameSupplier) {
    this.gameSupplier = gameSupplier;
  }

  public Game create() {
    return this.gameSupplier.get();
  }
}
