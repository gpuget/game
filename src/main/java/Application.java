import game.Game;
import game.GameFactory;

public class Application {
  public static void main(String[] args) {
    Game game = GameFactory.BATTLESHIP.create();
  }
}
