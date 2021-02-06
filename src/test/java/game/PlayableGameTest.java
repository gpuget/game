package game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;

class PlayableGameTest {
  @Test
  public void givenEmptyPlayersList_whenCreate_thenOk() {
    assertThat(new MockPlayableGame(Collections.emptyList(), 1)).isNotNull();
  }

  @Test
  public void givenTooManyPlayers_whenCreate_thenKo() {
    assertThatThrownBy(() -> new MockPlayableGame(List.of(new Object(), new Object()), 1))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Too many players !");
  }

  @Test
  public void givenOnePlayerInOnePlayerGame_whenNewPlayer_thenKo() {
    PlayableGame game = new MockPlayableGame(List.of(new Object()), 1);
    assertThatThrownBy(game::newPlayer)
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Too many players !");
  }

  @Test
  public void whenNewPlayer_thenPlayersListIncreased() {
    PlayableGame game = new MockPlayableGame(new LinkedList(), 1);
    Player player = game.newPlayer();
    assertThat(game.getPlayers()).hasSize(1);
    assertThat(game.getPlayers().get(0)).isEqualTo(player);
  }

  private static class MockPlayableGame extends PlayableGame {
    protected MockPlayableGame(List players, int maxPlayers) {
      super(players, maxPlayers);
    }

    @Override
    protected boolean doInit() {
      return false;
    }

    @Override
    protected Player createPlayer() {
      return null;
    }
  }
}