import models.CellTypes;
import models.Field;
import models.Snake;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FieldTest {
    private Field field;

    @Before
    public void createField() {
        Snake snake = new Snake(2, 2, 2);
        this.field = new Field(snake, 5, 5);
    }

    @Test
    public void fillCellTypes_thenFillCells() throws NoSuchFieldException, IllegalAccessException {
        CellTypes[][] expectedCellsTypes = {
                {CellTypes.Empty, CellTypes.Empty, CellTypes.Empty, CellTypes.Empty, CellTypes.Empty},
                {CellTypes.Empty, CellTypes.Empty, CellTypes.Empty, CellTypes.Empty, CellTypes.Empty},
                {CellTypes.Empty, CellTypes.Empty, CellTypes.Snake, CellTypes.Snake, CellTypes.Empty},
                {CellTypes.Empty, CellTypes.Empty, CellTypes.Empty, CellTypes.Empty, CellTypes.Empty},
                {CellTypes.Empty, CellTypes.Empty, CellTypes.Empty, CellTypes.Empty, CellTypes.Empty},
        };

        field.fillCellTypes();
        java.lang.reflect.Field cellsField = field.getClass().getDeclaredField("cells");
        cellsField.setAccessible(true);
        CellTypes[][] actualCellsTypes = (CellTypes[][]) cellsField.get(field);

        Assert.assertArrayEquals(expectedCellsTypes, actualCellsTypes);
    }

    @Test
    public void getCellType_whenWall_thenReturnWallCellType() {
        int[] coords = { -1, -3 };

        CellTypes expectedCellType = CellTypes.Wall;

        CellTypes actualCellType = field.getCellType(coords);

        Assert.assertEquals(expectedCellType, actualCellType);
    }

    @Test
    public void getCellType_whenNotWall_thenReturnActualCellType() {
        int[] coords = { 0, 3 };

        CellTypes expectedCellType = CellTypes.Empty;

        CellTypes actualCellType = field.getCellType(coords);

        Assert.assertEquals(expectedCellType, actualCellType);
    }

    @Test
    public void spawnFood_thenSpawnOnlyOneFood() {
        int expectedFoodAmount = 1;

        field.spawnFood();
        field.spawnFood();
        field.spawnFood();
        int actualFoodAmount = 0;
        for (int i = 0; i < field.getHeight(); i++) {
            for (int j = 0; j < field.getWidth(); j++) {
                int[] coords = { i, j };
                if (field.getCellType(coords) == CellTypes.Food) {
                    actualFoodAmount++;
                }
            }
        }

        Assert.assertEquals(expectedFoodAmount, actualFoodAmount);
    }

    @Test
    public void spawnObstacle_thenSpawnMultipleObstacles() {
        int expectedObstacleAmount = 3;

        field.spawnObstacle();
        field.spawnObstacle();
        field.spawnObstacle();
        int actualObstacleAmount = field.getObstaclesX().size();

        Assert.assertEquals(expectedObstacleAmount, actualObstacleAmount);
    }
}
