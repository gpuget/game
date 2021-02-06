package game.battleship.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class OrientationTest {
  @Test
  void givenWrongCharacter_whenValueOf_thenNull() {
    assertThat(Orientation.valueOf('l')).isNull();
  }

  @Test
  void givenL_whenValueOf_thenLeft() {
    assertThat(Orientation.valueOf('L')).isEqualTo(Orientation.LEFT);
  }

  @Test
  void givenR_whenValueOf_thenRight() {
    assertThat(Orientation.valueOf('R')).isEqualTo(Orientation.RIGHT);
  }

  @Test
  void givenU_whenValueOf_thenUp() {
    assertThat(Orientation.valueOf('U')).isEqualTo(Orientation.UP);
  }

  @Test
  void givenD_whenValueOf_thenDown() {
    assertThat(Orientation.valueOf('D')).isEqualTo(Orientation.DOWN);
  }
}