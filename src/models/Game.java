package models;

public class Game {
    private final double CHANCE_TO_SPAWN_OBSTACLE = 0.25;
    private int currentScore;
    private Field field;
    private Snake snake;
    private GameOverCallback gameOverCallback;
    private ScoreChangedCallback scoreChangedCallback;
    private LivesChangeCallback livesChangeCallback;
    private LevelUpCallback levelUpCallback;
    private int lives;
    private int snakeStartingLength = 2;
    private int level;

    private int period;

    public void start(int width, int height) {
        currentScore = 0;
        level = 0;
        lives = 3;
        period = 500;
        snake = new Snake(width/2, height/2, snakeStartingLength);
        field = new Field(snake, height, width);
        field.spawnFood();
        System.out.println("Игра началась.");
    }

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
                period -= 120 - Math.pow(Math.log (1150 * level - 920), 2);
                levelUpCallback.onLevelUp();
                System.out.println("период: " + period);
            }
            if (needToSpawnObstacle()) {
                field.spawnObstacles();
            }
            field.spawnFood();
        } else if (newCellType == CellTypes.Empty) {
            snake.move(false);
            field.fillCellTypes();
        }
        return true;
    }

    public Field getField() {
        return field;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public int getPeriod() {
        return period;
    }

    public Snake getSnake() {
        return snake;
    }

    public int getLevel() {
        return level;
    }

    public int getLives() { return lives;}

    private boolean needToSpawnObstacle() {
        return Math.random() <= CHANCE_TO_SPAWN_OBSTACLE;
    }

    public void registerGameOverCallback(GameOverCallback gameOverCallback) {
        this.gameOverCallback = gameOverCallback;
    }

    public void registerScoreChangeCallback(ScoreChangedCallback scoreChangedCallback) {
        this.scoreChangedCallback = scoreChangedCallback;
    }

    public void registerLivesChangeCallback(LivesChangeCallback livesChangeCallback) {
        this.livesChangeCallback = livesChangeCallback;
    }

    public void registerLevelUpCallback(LevelUpCallback levelUpCallback) {
        this.levelUpCallback = levelUpCallback;
    }


    public interface GameOverCallback {
        void callingBack();

    }

    public interface ScoreChangedCallback {
        void onScoreChange();

    }

    public interface LivesChangeCallback {
        void onLivesChange();
    }

    public interface LevelUpCallback {
        void onLevelUp();
    }
}
