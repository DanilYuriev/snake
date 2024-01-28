package game;

import models.Game;

import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel {
    private static final int SCREEN_WIDTH = 660;
    private static final int SCREEN_HEIGHT = 660;

    private Game game;

    private JPanel labelPanel;

    public GameOverPanel(Game game) {
        this.game = game;
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(new Color(27, 27, 27));
        this.setFocusable(true);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 270));
        labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(2, 1));
        labelPanel.setBackground(new Color(27, 27, 27));
        JLabel gameOverLabel = new JLabel();
        gameOverLabel.setText("GAME OVER");
        gameOverLabel.setFont(new Font("Calibri", Font.PLAIN, 72));
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setHorizontalAlignment(JLabel.CENTER);
        labelPanel.add(gameOverLabel);
        this.add(labelPanel);

    }

    public void prepare() {
        JLabel scoreLabel = new JLabel();
        scoreLabel.setText("Your score: " + game.getCurrentScore());
        scoreLabel.setFont(new Font("Calibri", Font.PLAIN, 38));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        labelPanel.add(scoreLabel);
    }

}
