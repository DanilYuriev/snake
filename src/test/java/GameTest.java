import models.Game;
import org.junit.Assert;
import org.junit.Test;

public class GameTest {
    @Test
    public void start_thenInitializesGameCorrectly() {
        Game game = new Game();

        int expectedCurrentScore = 0;
        int expectedLives = 3;
        int expectedPeriod = 500;

        game.start(10, 10);
        int actualCurrentScore = game.getCurrentScore();
        int actualLives = game.getLives();
        int actualPeriod = game.getPeriod();

        Assert.assertEquals(expectedCurrentScore, actualCurrentScore);
        Assert.assertEquals(expectedLives, actualLives);
        Assert.assertEquals(expectedPeriod, actualPeriod);
        Assert.assertNotNull(game.getSnake());
        Assert.assertNotNull(game.getField());
    }
}
