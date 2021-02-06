package game;

import java.util.Objects;

public abstract class Game {
  private static final String TITLE = "==[%s]==";
  private final String name;
  private final String title;
  private State state;

  protected Game(String name) {
    this.name = name;
    this.title = String.format(TITLE, this.name);
    this.state = State.CREATED;
  }

  public State init() {
    System.out.println(this.title);
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

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Game game = (Game) o;
    return state == game.state;
  }

  @Override
  public int hashCode() {
    return Objects.hash(state);
  }

  enum State {
    CREATED, INITIALIZED, STARTED
  }
}
