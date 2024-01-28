package game;

import javax.swing.*;
import models.Game;

import java.awt.*;

public class GameInfoPanel extends JPanel implements Game.ScoreChangedCallback, Game.LivesChangeCallback, Game.LevelUpCallback {
    private final Game game;
    private final JLabel scoreLabel;
    private final JLabel livesLabel;
    private final JLabel levelLabel;

    public GameInfoPanel(Game game) {
        this.game = game;
        game.registerScoreChangeCallback(this);
        game.registerLivesChangeCallback(this);
        game.registerLevelUpCallback(this);
        this.setPreferredSize(new Dimension(660, 70));
        this.setBackground(new Color(20, 20, 20));
        this.setLayout(new GridLayout(1, 3));
        scoreLabel = new JLabel("Your score: " + game.getCurrentScore());
        scoreLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setVerticalAlignment(JLabel.CENTER);
        this.add(scoreLabel);
        livesLabel = new JLabel("Lives: " + game.getLives());
        livesLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
        livesLabel.setForeground(Color.WHITE);
        livesLabel.setHorizontalAlignment(JLabel.CENTER);
        livesLabel.setVerticalAlignment(JLabel.CENTER);
        this.add(livesLabel);
        levelLabel = new JLabel("Level: " + game.getLevel());
        levelLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
        levelLabel.setForeground(Color.WHITE);
        levelLabel.setHorizontalAlignment(JLabel.CENTER);
        levelLabel.setVerticalAlignment(JLabel.CENTER);
        this.add(levelLabel);
    }

    @Override
    public void onScoreChange() {
        scoreLabel.setText("Your score: " + game.getCurrentScore());
    }

    @Override
    public void onLivesChange() {
        livesLabel.setText("Lives: " + game.getLives());
    }

    @Override
    public void onLevelUp() {
        levelLabel.setText("Level: " + game.getLevel());
    }
}