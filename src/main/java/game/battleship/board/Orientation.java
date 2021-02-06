package game.battleship.board;

import java.util.Map;

public enum Orientation {
  LEFT, RIGHT, UP, DOWN;

  private static final Map<Character, Orientation> MAP = Map.of('L', LEFT, 'R', RIGHT, 'U', UP, 'D', DOWN);

  public static Orientation valueOf(char c) {
    return MAP.get(c);
  }
}
