package game;

public abstract class Game {
  private State state;

  protected Game() {
    this.state = State.CREATED;
  }

  public State init() {
    boolean result = doInit();
    if (result) {
      this.state = State.INITIALIZED;
    }
    return this.state;
  }

  private boolean is(State state) {
    return this.state.ordinal() >= state.ordinal();
  }

  public boolean isCreated() {
    return is(State.CREATED);
  }

  public boolean isInitialized() {
    return is(State.INITIALIZED);
  }

  protected abstract boolean doInit();

  enum State {
    CREATED, INITIALIZED, STARTED
  }
}
