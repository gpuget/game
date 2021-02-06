package game;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public abstract class PlayableGame<P extends Player> extends Game {
  private List<P> players;
  private final int maxPlayers;

  protected PlayableGame(List<P> players, int maxPlayers) {
    checkMax(players, maxPlayers);
    this.players = players;
    this.maxPlayers = maxPlayers;
  }

  private static void checkMax(List<? extends Player> players, int limit) {
    if (players.size() > limit) {
      throw new IllegalArgumentException("Too many players !");
    }
  }

  public P newPlayer() {
    checkMax(this.players, this.maxPlayers - 1);
    P player = createPlayer();
    this.players.add(player);
    return player;
  }

  public List<P> getPlayers() {
    return players;
  }

  public int getMaxPlayers() {
    return maxPlayers;
  }

  protected abstract P createPlayer();

  @Override
  protected boolean doInit() {
    IntStream.range(0, this.maxPlayers).forEach(i -> newPlayer());
    this.players = List.copyOf(this.players); // Set immutable
    return true;
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
}
