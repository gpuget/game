package game;

public abstract class GenericGame implements Game {
  protected final String name;
  private State state;

  protected GenericGame(String name) {
    this.name = name;
    this.state = State.CREATED;
  }

  @Override
  public Game init() {
    if (doInit()) {
      this.state = State.INITIALIZED;
    }
    return this;
  }

  @Override
  public Game start() {
    this.state = State.STARTED;
    return this;
  }

  @Override
  public Game pause() {
    this.state = State.PAUSED;
    return this;
  }

  @Override
  public Game end() {
    this.state = State.ENDED;
    return this;
  }

  @Override
  public Game reset() {
    this.state = State.CREATED;
    return this;
  }

  @Override
  public State getState() {
    return this.state;
  }

  public String getName() {
    return name;
  }

  protected abstract boolean doInit();
}
