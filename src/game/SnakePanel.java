package game;

import models.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakePanel extends JPanel implements ActionListener, Game.LevelUpCallback {
    private static final int SCREEN_WIDTH = 660;
    private static final int SCREEN_HEIGHT = 660;
    private static final int UNIT_SIZE = 60;
    private static final int FIELD_SIZE = SCREEN_HEIGHT/UNIT_SIZE;
    private static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    private boolean isRunning = false;

    private Timer timer;

    private final Game game;

    public SnakePanel(Game game) {
        this.game = game;
        game.registerLevelUpCallback(this);
        this.addKeyListener(new SnakeKeyAdapter(game));
        this.game.start(FIELD_SIZE, FIELD_SIZE);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(new Color(27, 27, 27));
        this.setFocusable(true);
        isRunning = true;
        timer = new Timer(game.getPeriod(), this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isRunning){
            isRunning = game.doStep();

        }
        repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g) {
        for (int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE; i++) {
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
        }
        g.setColor(Color.red);
        g.fillOval(game.getField().getFoodX() * UNIT_SIZE, game.getField().getFoodY() * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);

        g.setColor(new Color(35, 161, 64));
        g.fillRect(game.getField().getSnake().getHeadX() * UNIT_SIZE, game.getField().getSnake().getHeadY() * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);

        for (int i = 0; i < game.getField().getSnake().getLength() - 1; i++) {
            g.setColor(new Color(35, 220, 86));
            g.fillRect(game.getField().getSnake().getBodyCellsX()[i] * UNIT_SIZE, game.getField().getSnake().getBodyCellsY()[i] * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
        }

        for (int i = 0; i < game.getField().getObstaclesX().size(); i++) {
            g.setColor(Color.gray);
            g.fillRect(game.getField().getObstaclesX().get(i) * UNIT_SIZE, game.getField().getObstaclesY().get(i) * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
        }
    }

    @Override
    public void onLevelUp() {
        timer.stop();
        timer = new Timer(game.getPeriod(), this);
        timer.start();
    }
}
