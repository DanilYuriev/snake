package game;

import models.Game;

import javax.swing.*;
import java.awt.*;

public class SnakeForm extends JFrame implements Game.GameOverCallback {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private GameOverPanel gameOver;
    private SnakePanel snake;
    private GameInfoPanel gameInfo;
    private JPanel panel1;

    public SnakeForm(Game game) {
        game.registerGameOverCallback(this);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        snake = new SnakePanel(game);
        gameInfo = new GameInfoPanel(game);
        gameOver = new GameOverPanel(game);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(gameInfo);
        panel.add(snake);
        mainPanel.add(gameOver, "gameOver");
        mainPanel.add(panel, "snake");

        add(mainPanel);

        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.cardLayout.show(mainPanel, "snake");
    }

    public void callingBack() {
        gameOver.prepare();
        cardLayout.show(mainPanel, "gameOver");
    }
}

// TODO начать рисовать главный экран
