package game.battleship.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PositionTest {
  @Test
  void givenGoodCodes_whenDecode_thenOk() {
    Position position1 = Position.decode("A1L");
    Position position2 = Position.decode("Z99D");

    assertThat(position1.getRow()).isEqualTo(0);
    assertThat(position1.getCol()).isEqualTo(0);
    assertThat(position1.getOrientation()).isEqualTo(Orientation.LEFT);

    assertThat(position2.getRow()).isEqualTo(25);
    assertThat(position2.getCol()).isEqualTo(98);
    assertThat(position2.getOrientation()).isEqualTo(Orientation.DOWN);
  }

  @Test
  void givenCodeWithOnlyDigits_whenDecode_thenNone() {
    assertThat(Position.decode("666")).isSameAs(Position.none());
  }

  @Test
  void givenCodeWithOnlyLetters_whenDecode_thenNone() {
    assertThat(Position.decode("AAA")).isSameAs(Position.none());
  }

  @Test
  void givenWrongCodes_whenDecode_thenNone() {
    assertThat(Position.decode("1AL")).isSameAs(Position.none());
    assertThat(Position.decode("A999L")).isSameAs(Position.none());
    assertThat(Position.decode("A1Z")).isSameAs(Position.none());
  }
}