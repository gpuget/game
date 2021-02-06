package game;

public abstract class Game {
  private State state;

  protected Game() {
    this.state = State.CREATED;
  }

  public boolean isCreated() {
    return this.state.ordinal() >= State.CREATED.ordinal();
  }

  private enum State {
    CREATED, INITIALIZED, STARTED
  }
}
