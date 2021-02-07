package game;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public abstract class PlayableGame<P extends Player> extends GenericGame {
  private final int maxPlayers;
  private List<P> players;

  protected PlayableGame(String name, List<P> players) {
    super(name);
    checkArgument(players.size() > 0, "At least 1 player");
    this.players = List.copyOf(players);
    this.maxPlayers = players.size();
  }

  protected PlayableGame(String name, int maxPlayers) {
    super(name);
    checkArgument(maxPlayers > 0, "At least 1 player");
    this.players = new ArrayList<>(maxPlayers);
    this.maxPlayers = maxPlayers;
  }

  public P newPlayer() {
    checkMaxPlayer();
    P player = createPlayer();
    this.players.add(player);
    if (this.players.size() == this.maxPlayers) {
      this.players = List.copyOf(players); // Set immutable
    }
    return player;
  }

  private void checkMaxPlayer() {
    if (this.players.size() > this.maxPlayers - 1) {
      throw new IllegalArgumentException("Too many players !");
    }
  }

  @Override
  protected boolean doInit() {
    IntStream.range(0, this.maxPlayers).forEach(i -> newPlayer());
    this.players = List.copyOf(this.players); // Set immutable
    return true;
  }

  public List<P> getPlayers() {
    return players;
  }

  public int getMaxPlayers() {
    return maxPlayers;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    PlayableGame that = (PlayableGame) o;
    return Objects.equals(players, that.players);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), players);
  }

  protected abstract P createPlayer();
}
