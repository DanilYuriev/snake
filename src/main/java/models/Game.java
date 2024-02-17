package models;

/**
 * Класс Game представляет собой игру.
 */
public class Game {
    private final double CHANCE_TO_SPAWN_OBSTACLE = 0.25;

    private int currentScore;

    /**
     * Получить значение поля currentScore.
     * @return Текущий счёт.
     */
    public int getCurrentScore() {
        return currentScore;
    }

    private Field field;

    /**
     * Получить значение поля field.
     * @return Игровое поле.
     */
    public Field getField() {
        return field;
    }

    private GameOverCallback gameOverCallback;

    private int level = 0;

    /**
     * Получить значение поля level.
     * @return Уровень сложности игры.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Задать значение поля level.
     * @param level Уровень сложности игры.
     */
    public void setLevel(int level) {
        this.level = level;
    }

    private LevelUpCallback levelUpCallback;

    private int lives;

    public int getLives() { return lives;}

    private LivesChangeCallback livesChangeCallback;

    private int period;

    /**
     * Получить значение поля period.
     * @return Период одного шага игры в секундах.
     */
    public int getPeriod() {
        return period;
    }

    private ScoreChangedCallback scoreChangedCallback;

    private Snake snake;

    /**
     * Получить значение поля snake.
     * @return Змея.
     */
    public Snake getSnake() {
        return snake;
    }

    private int snakeStartingLength = 2;

    /**
     * Получить значение поля snakeStartingLength.
     * @return Начальная длина змеи.
     */
    public int getSnakeStartingLength() {
        return snakeStartingLength;
    }

    /**
     * Задать значение поля snakeStartingLength.
     * @param snakeStartingLength Начальная длина змеи.
     */
    public void setSnakeStartingLength(int snakeStartingLength) {
        this.snakeStartingLength = snakeStartingLength;
    }

    /**
     * Выполнить шаг игры.
     * @return Продолжается ли игра.
     */
    public boolean doStep() {
        int[] newCellCoords = snake.getNewHeadCoordinates();
        var newCellType = field.getCellType(newCellCoords);
        snake.setHasChangedDirections(false);
        if (newCellType == CellTypes.Obstacle || newCellType == CellTypes.Wall || newCellType == CellTypes.Snake) {
            if (lives == 0) {
                gameOverCallback.callingBack();
                return false;
            } else {
                lives--;
                livesChangeCallback.onLivesChange();
                snake = new Snake(field.getWidth()/2, field.getHeight()/2, snakeStartingLength);
                field = new Field(snake, field.getHeight(), field.getWidth());
                field.spawnFood();
            }
        } else if (newCellType == CellTypes.Food) {
            snake.move(true);
            field.fillCellTypes();
            currentScore = currentScore + 1;
            scoreChangedCallback.onScoreChange();
            if (currentScore % 5 == 0) {
                level = currentScore / 5;
                period -= (int) (120 - Math.pow(Math.log (1150 * level - 920), 2));
                levelUpCallback.onLevelUp();
                System.out.println("период: " + period);
            }
            if (needToSpawnObstacle()) {
                field.spawnObstacle();
            }
            field.spawnFood();
        } else if (newCellType == CellTypes.Empty) {
            snake.move(false);
            field.fillCellTypes();
        }
        return true;
    }

    /**
     * Зарегистрировать gameOverCallback.
     * @param gameOverCallback Обработчик события окончания игры.
     */
    public void registerGameOverCallback(GameOverCallback gameOverCallback) {
        this.gameOverCallback = gameOverCallback;
    }

    /**
     * Зарегистрировать levelUpCallback.
     * @param levelUpCallback Обработчик события повышения уровня сложности игры.
     */
    public void registerLevelUpCallback(LevelUpCallback levelUpCallback) {
        this.levelUpCallback = levelUpCallback;
    }

    /**
     * Зарегистрировать livesChangeCallback.
     * @param livesChangeCallback Обработчик события изменения количества жизней.
     */
    public void registerLivesChangeCallback(LivesChangeCallback livesChangeCallback) {
        this.livesChangeCallback = livesChangeCallback;
    }

    /**
     * Зарегистрировать scoreChangedCallback.
     * @param scoreChangedCallback Обработчик события изменения счёта игры.
     */
    public void registerScoreChangeCallback(ScoreChangedCallback scoreChangedCallback) {
        this.scoreChangedCallback = scoreChangedCallback;
    }

    /**
     * Запуск игры.
     * @param width Ширина игрового поля (в клетках).
     * @param height Высота игрового поля (в клетках).
     */
    public void start(int width, int height) {
        currentScore = 0;
        lives = 3;
        period = 500;
        snake = new Snake(width/2, height/2, snakeStartingLength);
        field = new Field(snake, height, width);
        field.spawnFood();
    }

    private boolean needToSpawnObstacle() {
        return Math.random() <= CHANCE_TO_SPAWN_OBSTACLE;
    }

    /**
     * Обработчик события окончания игры.
     */
    public interface GameOverCallback {
        void callingBack();
    }

    /**
     * Обработчик события изменения количества жизней.
     */
    public interface LivesChangeCallback {
        void onLivesChange();
    }

    /**
     * Обработчик события повышения уровня сложности игры.
     */
    public interface LevelUpCallback {
        void onLevelUp();
    }

    /**
     * Обработчик события изменения счёта игры.
     */
    public interface ScoreChangedCallback {
        void onScoreChange();
    }
}