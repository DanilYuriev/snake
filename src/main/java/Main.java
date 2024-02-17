import game.SnakeForm;
import models.Game;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        new SnakeForm(game);
    }
}