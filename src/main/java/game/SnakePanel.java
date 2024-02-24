package game;

import models.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс SnakePanel отвечает за отображение игрового поля.
 */
public class SnakePanel extends JPanel implements ActionListener, Game.LevelUpCallback {
    private static final int SCREEN_HEIGHT = 660;
    private static final int SCREEN_WIDTH = 660;
    private static final int UNIT_SIZE = 60;
    private static final int FIELD_SIZE = SCREEN_HEIGHT/UNIT_SIZE;
    private final SnakeColor currentSnakeColor;
    private final Game game;
    private boolean isRunning = false;
    private Timer timer;

    /**
     * Создать экземпляр интерфейса игрового поля.
     * @param game Экземпляр текущей игры.
     * @param snakeColor Цвет змеи.
     * @param backgroundColor Цвет фона.
     */
    public SnakePanel(Game game, SnakeColor snakeColor, Color backgroundColor) {
        game.registerLevelUpCallback(this);
        this.game = game;

        this.setBackground(backgroundColor);
        this.currentSnakeColor = snakeColor;
        this.addKeyListener(new SnakeKeyAdapter(game));
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setFocusable(true);

        this.game.start(FIELD_SIZE, FIELD_SIZE);
        timer = new Timer(game.getPeriod(), this);
        timer.start();
        isRunning = true;
    }

    /**
     * Обработчик события завершения хода игры.
     * @param e the event to be processed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isRunning) {
            isRunning = game.doStep();
        }
        repaint();
    }

    /**
     * Прорисовка интерфейса игрового поля.
     * @param g Графический компонет игрового поля.
     */
    public void draw(Graphics g) {
        for (int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE; i++) {
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
        }
        g.setColor(Color.red);
        g.fillOval(game.getField().getFoodX() * UNIT_SIZE, game.getField().getFoodY() * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);

        g.setColor(currentSnakeColor.head());
        g.fillRect(game.getSnake().getHeadX() * UNIT_SIZE, game.getField().getSnake().getHeadY() * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);

        for (int i = 0; i < game.getSnake().getLength() - 1; i++) {
            g.setColor(currentSnakeColor.body());
            g.fillRect(game.getSnake().getBodyCellsX()[i] * UNIT_SIZE, game.getField().getSnake().getBodyCellsY()[i] * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
        }

        for (int i = 0; i < game.getField().getObstaclesX().size(); i++) {
            g.setColor(Color.gray);
            g.fillRect(game.getField().getObstaclesX().get(i) * UNIT_SIZE, game.getField().getObstaclesY().get(i) * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
        }
    }

    /**
     * Обработчик события повышения уровня сложности игры.
     */
    @Override
    public void onLevelUp() {
        timer.stop();
        timer = new Timer(game.getPeriod(), this);
        timer.start();
    }

    /**
     * Отрисовка графического компонента.
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
}
