package game.battleship;

import static org.assertj.core.api.Assertions.assertThat;

import game.battleship.board.Board;
import org.junit.jupiter.api.Test;

class BattleshipTest {
  @Test
  void whenCreate_thenOk() {
    Battleship battleship = new Battleship();

    assertThat(battleship).isNotNull();
    assertThat(battleship.getPlayers()).isEmpty();
    assertThat(battleship.getMaxPlayers()).isEqualTo(2);
    assertThat(battleship.getName()).isEqualTo(Battleship.NAME);
  }

  @Test
  void whenCreatePlayer_thenPlayer1() {
    Battleship battleship = new Battleship();

    BattleshipPlayer player1 = battleship.createPlayer();

    assertThat(player1).isEqualTo(new BattleshipPlayer("Player 1", Board.empty()));
  }

  @Test
  void givenOnePlayerInGame_whenNewPlayer_then2Players() {
    Battleship battleship = new Battleship();
    BattleshipPlayer player1 = battleship.newPlayer();

    BattleshipPlayer player2 = battleship.newPlayer();

    assertThat(battleship.getPlayers()).hasSize(2);
    assertThat(battleship.getPlayers().get(1)).isEqualTo(new BattleshipPlayer("Player 2", Board.empty()));
  }
}