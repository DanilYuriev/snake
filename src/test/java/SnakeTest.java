import models.Directions;
import models.Snake;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SnakeTest {
    private Snake snake;

    @Before
    public void createSnake() {
        this.snake = new Snake(0, 0, 2);
    }

    @Test
    public void getNewHeadCoordinates_whenDirectionRight_thenReturnNewHeadCoordinates() {
        snake.setDirection(Directions.Right);

        int expectedXCoordinate = 1;
        int expectedYCoordinate = 0;

        int[] newHeadCoordinates = snake.getNewHeadCoordinates();
        int actualXCoordinate = newHeadCoordinates[0];
        int actualYCoordinate = newHeadCoordinates[1];

        Assert.assertEquals(expectedXCoordinate, actualXCoordinate);
        Assert.assertEquals(expectedYCoordinate, actualYCoordinate);
    }

    @Test
    public void getNewHeadCoordinates_whenDirectionLeft_thenReturnNewHeadCoordinates() {
        snake.setDirection(Directions.Left);

        int expectedXCoordinate = -1;
        int expectedYCoordinate = 0;

        int[] newHeadCoordinates = snake.getNewHeadCoordinates();
        int actualXCoordinate = newHeadCoordinates[0];
        int actualYCoordinate = newHeadCoordinates[1];

        Assert.assertEquals(expectedXCoordinate, actualXCoordinate);
        Assert.assertEquals(expectedYCoordinate, actualYCoordinate);
    }

    @Test
    public void getNewHeadCoordinates_whenDirectionUp_thenReturnNewHeadCoordinates() {
        snake.setDirection(Directions.Up);

        int expectedXCoordinate = 0;
        int expectedYCoordinate = -1;

        int[] newHeadCoordinates = snake.getNewHeadCoordinates();
        int actualXCoordinate = newHeadCoordinates[0];
        int actualYCoordinate = newHeadCoordinates[1];

        Assert.assertEquals(expectedXCoordinate, actualXCoordinate);
        Assert.assertEquals(expectedYCoordinate, actualYCoordinate);
    }

    @Test
    public void getNewHeadCoordinates_whenDirectionDown_thenReturnNewHeadCoordinates() {
        snake.setDirection(Directions.Down);

        int expectedXCoordinate = 0;
        int expectedYCoordinate = 1;

        int[] newHeadCoordinates = snake.getNewHeadCoordinates();
        int actualXCoordinate = newHeadCoordinates[0];
        int actualYCoordinate = newHeadCoordinates[1];

        Assert.assertEquals(expectedXCoordinate, actualXCoordinate);
        Assert.assertEquals(expectedYCoordinate, actualYCoordinate);
    }

    @Test
    public void move_whenEatenFood_thenMovedAndLengthIncreased() {
        int expectedLength = 3;
        int expectedHeadXCoordinate = 0;
        int expectedHeadYCoordinate = -1;
        int[] expectedBodyXCoordinates = { 0, 0 };
        int[] expectedBodyYCoordinates = { 0, 1 };

        snake.move(true);
        int actualLength = snake.getLength();
        int actualHeadXCoordinate = snake.getHeadX();
        int actualHeadYCoordinate = snake.getHeadY();
        int[] actualBodyXCoordinates = snake.getBodyCellsX();
        int[] actualBodyYCoordinates = snake.getBodyCellsY();

        Assert.assertEquals(expectedLength, actualLength);
        Assert.assertEquals(expectedHeadXCoordinate, actualHeadXCoordinate);
        Assert.assertEquals(expectedHeadYCoordinate, actualHeadYCoordinate);
        Assert.assertArrayEquals(expectedBodyXCoordinates, actualBodyXCoordinates);
        Assert.assertArrayEquals(expectedBodyYCoordinates, actualBodyYCoordinates );
    }

    @Test
    public void move_whenNotEatenFood_thenMoved() {
        int expectedLength = 2;
        int expectedHeadXCoordinate = 0;
        int expectedHeadYCoordinate = -1;
        int[] expectedBodyXCoordinates = { 0 };
        int[] expectedBodyYCoordinates = { 0 };

        snake.move(false);
        int actualLength = snake.getLength();
        int actualHeadXCoordinate = snake.getHeadX();
        int actualHeadYCoordinate = snake.getHeadY();
        int[] actualBodyXCoordinates = snake.getBodyCellsX();
        int[] actualBodyYCoordinates = snake.getBodyCellsY();

        Assert.assertEquals(expectedLength, actualLength);
        Assert.assertEquals(expectedHeadXCoordinate, actualHeadXCoordinate);
        Assert.assertEquals(expectedHeadYCoordinate, actualHeadYCoordinate);
        Assert.assertArrayEquals(expectedBodyXCoordinates, actualBodyXCoordinates);
        Assert.assertArrayEquals(expectedBodyYCoordinates, actualBodyYCoordinates );
    }
}
