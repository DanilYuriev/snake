package models;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Класс Field представляет собой модель игрового поля.
 */
public class Field {
    private final CellTypes[][] cells;

    private final int height;

    /**
     * Получить значение поля height.
     * @return Высота игрового поля.
     */
    public int getHeight() {
        return height;
    }

    private final ArrayList<Integer> obstaclesX;

    /**
     * Получить значение поля obstaclesX.
     * @return Список Х-координат препятствий.
     */
    public ArrayList<Integer> getObstaclesX() { return obstaclesX; }

    private final ArrayList<Integer> obstaclesY;

    /**
     * Получить значение поля obstaclesY.
     * @return Список Y-координат препятствий.
     */
    public ArrayList<Integer> getObstaclesY() { return obstaclesY; }

    private final Snake snake;

    /**
     * Получить значение поля snake.
     * @return Змея.
     */
    public Snake getSnake() { return snake; }

    private final int width;

    /**
     * Получить значение поля width.
     * @return Ширина игрового поля.
     */
    public int getWidth() {
        return width;
    }

    private int foodX;

    /**
     * Получить значение поля foodX.
     * @return Х-координата еды.
     */
    public int getFoodX() { return foodX; }

    private int foodY;

    /**
     * Получить значение поля foodY.
     * @return Y-координата еды.
     */
    public int getFoodY() { return foodY; }

    /**
     * Создать экземпляр игрового поля.
     * @param snake Змея.
     * @param height Высота игрового поля (в клетках).
     * @param width Ширина игрового поля (в клетках).
     */
    public Field(Snake snake, int height, int width) {
        this.snake = snake;
        this.height = height;
        this.width = width;

        obstaclesX = new ArrayList<>();
        obstaclesY = new ArrayList<>();

        cells = new CellTypes[height][width];
        for (CellTypes[] cell : cells) {
            Arrays.fill(cell, CellTypes.Empty);
        }

        cells[snake.getHeadX()][snake.getHeadY()] = CellTypes.Snake;
        for (int i = 0; i < snake.getBodyCellsX().length; i++) {
            cells[snake.getBodyCellsX()[i]][snake.getBodyCellsY()[i]] = CellTypes.Snake;
        }
    }

    /**
     * Заполнить клетки игрового поля в соответствии с текущими типами клеток.
     */
    public void fillCellTypes() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j] == CellTypes.Snake) {
                    cells[i][j] = CellTypes.Empty;
                }
            }
        }
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (i == snake.getHeadX() && j == snake.getHeadY()) {
                    cells[i][j] = CellTypes.Snake;
                    continue;
                }
                for (int k = 0; k < snake.getBodyCellsX().length; k++) {
                    if (i == snake.getBodyCellsX()[k] && j == snake.getBodyCellsY()[k]) {
                        cells[i][j] = CellTypes.Snake;
                        break;
                    }
                }
            }
        }
    }

    /**
     * Получить тип клетки игрового поля по её координатам.
     * @param coords Координаты клетки игрового поля.
     * @return Тип клетки игрового поля.
     */
    public CellTypes getCellType(int[] coords) {
        if (coords[0] >= height || coords[0] < 0 || coords[1] >= width || coords[1] < 0) {
            return CellTypes.Wall;
        }

        return cells[coords[0]][coords[1]];
    }

    /**
     * Создать еду на игровом поле.
     */
    public void spawnFood() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cells[i][j] == CellTypes.Food) {
                    cells[i][j] = CellTypes.Empty;
                }
            }
        }

        do {
            foodX = generateRandomX();
            foodY = generateRandomY();
        } while (cells[foodX][foodY] != CellTypes.Empty);

        cells[foodX][foodY] = CellTypes.Food;
    }

    /**
     * Создать препятствие на игровом поле.
     */
    public void spawnObstacle() {
        int obstacleX;
        int obstacleY;

        do {
            obstacleX = generateRandomX();
            obstacleY = generateRandomY();
        } while (cells[obstacleX][obstacleY] != CellTypes.Empty);

        cells[obstacleX][obstacleY] = CellTypes.Obstacle;
        obstaclesX.add(obstacleX);
        obstaclesY.add(obstacleY);
    }

    private int generateRandomX() {
        return (int) ((Math.random() * height));
    }

    private int generateRandomY() {
        return (int) ((Math.random() * width));
    }
}
