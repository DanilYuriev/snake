package game;

import models.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Класс GameOverPanel отвечает за отображение экрана окончания игры.
 */
public class GameOverPanel extends JPanel {
    private static final int SCREEN_HEIGHT = 660;
    private static final int SCREEN_WIDTH = 660;

    private final Game game;
    private final JPanel labelPanel;

    /**
     * Создать экземпляр экрана окончания игры.
     * @param game Экземпляр текущей игры.
     */
    public GameOverPanel(Game game) {
        this.game = game;
        this.setBackground(new Color(27, 27, 27));
        this.setFocusable(true);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 270));
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

        labelPanel = new JPanel();
        labelPanel.setBackground(new Color(27, 27, 27));
        labelPanel.setLayout(new GridLayout(2, 1));
        this.add(labelPanel);

        JLabel gameOverLabel = new JLabel();
        gameOverLabel.setFont(new Font("Calibri", Font.PLAIN, 72));
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setHorizontalAlignment(JLabel.CENTER);
        gameOverLabel.setText("GAME OVER");
        labelPanel.add(gameOverLabel);
    }

    /**
     * Подготовка экрана к показу.
     */
    public void prepare() {
        JLabel scoreLabel = new JLabel();
        scoreLabel.setText("Your score: " + game.getCurrentScore());
        scoreLabel.setFont(new Font("Calibri", Font.PLAIN, 38));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        labelPanel.add(scoreLabel);
    }
}
