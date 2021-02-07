package game;

public interface Game {
  Game init();
  Game start();
  Game pause();
  Game end();
  Game reset();
  default Game restart() {
    reset();
    init();
    return start();
  }
  State getState();

  enum State {
    CREATED,
    INITIALIZED,
    STARTED,
    PAUSED,
    ENDED
  }
}
