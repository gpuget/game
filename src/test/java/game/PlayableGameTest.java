package game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class PlayableGameTest {
  private static final Player PLAYER = new Player("Stanley");

  @Test
  void givenPlayers_whenInstance_thenOk() {
    PlayableGame game = new MockPlayableGame(Collections.singletonList(PLAYER));

    assertThat(game.getPlayers()).hasSize(1);
    assertThat(game.getPlayers().get(0)).isEqualTo(PLAYER);
    assertThat(game.getMaxPlayers()).isEqualTo(1);
    assertThatThrownBy(() -> game.getPlayers().add(PLAYER)).isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  void givenInstanceWithPlayers_whenNewPlayer_thenException() {
    PlayableGame game = new MockPlayableGame(Collections.singletonList(PLAYER));

    assertThatThrownBy(game::newPlayer)
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Too many players !");
  }

  @Test
  void givenMaxPlayers_whenInstance_thenOk() {
    PlayableGame game = new MockPlayableGame(1);

    assertThat(game.getMaxPlayers()).isEqualTo(1);
    assertThat(game.getPlayers()).isEmpty();
  }

  @Test
  void givenGameWithNotEnoughPlayers_whenNewPlayer_thenAddPlayer() {
    PlayableGame game = new MockPlayableGame(2);

    game.newPlayer();

    assertThat(game.getPlayers()).isNotEmpty();
    assertThat(game.getPlayers().get(0)).isEqualTo(PLAYER);
  }

  @Test
  void givenGameWithNotEnoughPlayers_whenNewPlayerForLastPlayer_thenAddPlayerAndPlayersListIsUnmodifiable() {
    PlayableGame game = new MockPlayableGame(1);

    game.newPlayer();

    assertThat(game.getPlayers()).isNotEmpty();
    assertThat(game.getPlayers().get(0)).isEqualTo(PLAYER);
    assertThatThrownBy(() -> game.getPlayers().add(PLAYER))
        .isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  void givenGameWithEnoughPlayers_whenNewPlayer_thenException() {
    PlayableGame game = new MockPlayableGame(1);
    game.newPlayer();

    assertThatThrownBy(game::newPlayer)
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Too many players !");
  }

  @Test
  void whenInit_thenAllPlayerAreAddedAndPlayersListIsUnmodifiable() {
    int max = 1;
    PlayableGame game = new MockPlayableGame(max);

    game.doInit();

    assertThat(game.getPlayers()).hasSize(max);
    assertThatThrownBy(() -> game.getPlayers().add(PLAYER))
        .isInstanceOf(UnsupportedOperationException.class);
  }

  private static class MockPlayableGame extends PlayableGame<Player> {
    protected MockPlayableGame(int maxPlayers) {
      super("The Stanley Parable", maxPlayers);
    }

    protected MockPlayableGame(List<Player> players) {
      super("Stanley Parable", players);
    }

    @Override
    protected Player createPlayer() {
      return PLAYER;
    }
  }
}