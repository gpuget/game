package game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;

class PlayableGameTest {
  @Test
  void givenEmptyPlayersList_whenCreate_thenOk() {
    assertThat(new MockPlayableGame(Collections.emptyList(), 1)).isNotNull();
  }

  @Test
  void givenTooManyPlayers_whenCreate_thenKo() {
    assertThatThrownBy(() -> new MockPlayableGame(List.of(new Object(), new Object()), 1))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Too many players !");
  }

  @Test
  void givenOnePlayerInOnePlayerGame_whenNewPlayer_thenKo() {
    PlayableGame game = new MockPlayableGame(List.of(new Object()), 1);
    assertThatThrownBy(game::newPlayer)
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Too many players !");
  }

  @Test
  void whenNewPlayer_thenPlayersListIncreased() {
    PlayableGame game = new MockPlayableGame(new LinkedList(), 1);
    Player player = game.newPlayer();
    assertThat(game.getPlayers()).hasSize(1);
    assertThat(game.getPlayers().get(0)).isEqualTo(player);
  }

  @Test
  void whenInit_thenMaxPlayersAreCreatedAndPlayersListIsUnmodifiable() {
    int max = 1;
    PlayableGame game = new MockPlayableGame(new LinkedList(), max);

    game.doInit();

    assertThat(game.getPlayers()).hasSize(max);
    assertThatThrownBy(() -> game.getPlayers().add(new Object())).isInstanceOf(UnsupportedOperationException.class);
  }

  private static class MockPlayableGame extends PlayableGame {
    protected MockPlayableGame(List players, int maxPlayers) {
      super(players, maxPlayers);
    }

    @Override
    protected Player createPlayer() {
      return new Player("bob") {};
    }
  }
}