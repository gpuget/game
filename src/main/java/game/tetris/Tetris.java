package game.tetris;

import game.GenericGame;

public class Tetris extends GenericGame {
  public Tetris() {
    super("TETRIS");
  }

  @Override
  protected boolean doInit() {
    displayTitle();
    return true;
  }

  private String displayTitle() {
    String title =
        "   [#][#][#][#][#][#][#][#][#][#][#][#][#][#][#][#][#][#][#][#][#][#][#]   " + '\n' +
            "                                                                           " + '\n' +
            "   [#][#][#]   [#][#][#]   [#][#][#]   [#][#]      [#][#][#]   [#][#][#]   " + '\n' +
            "      [#]      [#]            [#]      [#]   [#]      [#]      [#]         " + '\n' +
            "      [#]      [#][#]         [#]      [#][#]         [#]      [#][#][#]   " + '\n' +
            "      [#]      [#]            [#]      [#]   [#]      [#]            [#]   " + '\n' +
            "      [#]      [#][#][#]      [#]      [#]   [#]   [#][#][#]   [#][#][#]   " + '\n' +
            "                                                                           " + '\n' +
            "   [#][#][#][#][#][#][#][#][#][#][#][#][#][#][#][#][#][#][#][#][#][#][#]   " + '\n';
    System.out.println(title);
    return title;
  }
}
