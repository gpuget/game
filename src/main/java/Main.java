import game.pss.PaperScissorsStone;
import game.pss.player.PSSPlayer;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        PSSPlayer one = PSSPlayer.builder("Player 1").build();
        PSSPlayer two = PSSPlayer.builder(("Player 2")).build();
        PaperScissorsStone game = new PaperScissorsStone(Arrays.asList(one, two), 3);
        game.start();
    }
}
