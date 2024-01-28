package models;

import java.util.ArrayList;

public class Field {
    private final int height;
    private final int width;
    private int foodX;
    private int foodY;

    private ArrayList<Integer> obstaclesX;
    private ArrayList<Integer> obstaclesY;
    private CellTypes[][] cells;

    private Snake snake;

    public Field(Snake snake, int height, int width) {
        this.height = height;
        this.width = width;
        obstaclesX = new ArrayList<>();
        obstaclesY = new ArrayList<>();
        cells = new CellTypes[height][width];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = CellTypes.Empty;
            }
        }
        this.snake = snake;
        cells[snake.getHeadX()][snake.getHeadY()] = CellTypes.Snake;
        for (int i = 0; i < snake.getBodyCellsX().length; i++) {
            cells[snake.getBodyCellsX()[i]][snake.getBodyCellsY()[i]] = CellTypes.Snake;
        }
        System.out.println("Поле создалось.");
    }

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

    public void spawnObstacles() {
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

    public CellTypes getCellType(int[] coords) {
        if (coords[0] >= height || coords[0] < 0) {
            return CellTypes.Wall;
        }
        if (coords[1] >= width || coords[1] < 0) {
            return CellTypes.Wall;
        }

        int[] tailX = snake.getBodyCellsX();
        int[] tailY = snake.getBodyCellsY();
        for (int i = 0; i < snake.getLength() - 1; i++) {
            if (coords[0] == tailX[i] && coords[1] == tailY[i]) {
                return CellTypes.Snake;
            }
        }

        return cells[coords[0]][coords[1]];
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getFoodX() { return foodX; }
    public int getFoodY() { return foodY; }

    public ArrayList<Integer> getObstaclesX() { return obstaclesX; }
    public ArrayList<Integer> getObstaclesY() { return obstaclesY; }

    public Snake getSnake() { return snake; }

    private int generateRandomX() {
        return (int) ((Math.random() * height));
    }

    private int generateRandomY() {
        return (int) ((Math.random() * width));
    }
}
