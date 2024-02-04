package game;

import models.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeForm extends JFrame implements Game.GameOverCallback, MenuPanel.StartGameCallback, MenuPanel.DisplayAboutCallback {
    private CardLayout cardLayout;
    private CardLayout aboutCardLayout;
    private JPanel mainPanel;
    private JPanel tabsContentPanel;
    private GameOverPanel gameOver;
    private SnakePanel snake;
    private MenuPanel menu;
    private Game game;
    private GameInfoPanel gameInfo;
    private JPanel panel;
    private JPanel panel1;
    private JTextField asdasdTextField;

    public SnakeForm(Game game) {
        game.registerGameOverCallback(this);
        this.game = game;
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        menu = new MenuPanel(game);
        menu.registerStartGameCallback(this);
        menu.registerDisplayAboutCallBack(this);
        gameOver = new GameOverPanel(game);
        mainPanel.add(gameOver, "gameOver");
        mainPanel.add(menu, "menu");

        JPanel aboutPanel = new JPanel();
        aboutPanel.setLayout(new BoxLayout(aboutPanel, BoxLayout.Y_AXIS));
        aboutPanel.setBackground(new Color(40, 40, 40));

        JPanel tabsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tabsPanel.setBackground(new Color(40, 40, 40));
        JButton helpButton = new JButton("Help");
        JButton developerButton = new JButton("Developer");
        JButton backButton = new JButton("Back");
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayHelp();
            }
        });
        developerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayDeveloper();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "menu");
            }
        });
        tabsPanel.add(helpButton);
        tabsPanel.add(developerButton);
        tabsPanel.add(backButton);
        aboutPanel.add(tabsPanel);

        tabsContentPanel = new JPanel();
        aboutCardLayout = new CardLayout(0, 20);
        tabsContentPanel.setBackground(new Color(40, 40, 40));
        tabsContentPanel.setLayout(aboutCardLayout);
        aboutPanel.add(tabsContentPanel);



        JPanel helpPanel = new JPanel();
        helpPanel.setBackground(new Color(40, 40, 40));
        JTextArea helpTextArea = new JTextArea("Welcome to Snake Game!\n\nHere's a quick guide:\nControl the snake using arrow keys: Up, Down, Left, Right.\n\nThe objective is to eat food, grow, and avoid hitting walls or yourself.\nPlan your moves, eat strategically, and avoid collisions.\nThe game ends if you hit a wall or yourself, displaying your score based on food and length.\n\nScore higher by growing longer.\nHave fun, improve, and aim for high scores!");
        helpTextArea.setEditable(false);
        helpTextArea.setPreferredSize(new Dimension(660, 500));
        helpTextArea.setFont(new Font("Calibri", Font.PLAIN, 22));
        helpTextArea.setBackground(new Color(40, 40, 40));
        helpTextArea.setForeground(Color.WHITE);
        helpTextArea.setLineWrap(true);
        helpPanel.add(helpTextArea);
        tabsContentPanel.add(helpPanel, "help");

        JPanel developerPanel = new JPanel();
        developerPanel.setBackground(new Color(40, 40, 40));
        JTextArea developerTextArea = new JTextArea("Developer:\nDanil Yuriev\n\nGit:\nhttps://github.com/DanilYuriev");
        developerTextArea.setEditable(false);
        developerTextArea.setPreferredSize(new Dimension(660, 300));
        developerTextArea.setFont(new Font("Calibri", Font.PLAIN, 22));
        developerTextArea.setBackground(new Color(40, 40, 40));
        developerTextArea.setForeground(Color.WHITE);
        developerTextArea.setLineWrap(true);
        developerPanel.add(developerTextArea);
        tabsContentPanel.add(developerPanel, "developer");

        mainPanel.add(aboutPanel, "about");

        add(mainPanel);

        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        cardLayout.show(mainPanel, "menu");
    }

    public void startGame(SnakeColor snakeColor, Color backgroundColor) {
        snake = new SnakePanel(game, snakeColor, backgroundColor);
        gameInfo = new GameInfoPanel(game);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(gameInfo);
        panel.add(snake);
        mainPanel.add(panel, "snake");
        cardLayout.show(mainPanel, "snake");
        snake.grabFocus();
    }

    public void displayHelp() {
        aboutCardLayout.show(tabsContentPanel, "help");
    }
    public void displayDeveloper() {
        aboutCardLayout.show(tabsContentPanel, "developer");
    }


    public void callingBack() {
        gameOver.prepare();
        cardLayout.show(mainPanel, "gameOver");
    }

    @Override
    public void onStartingGame(SnakeColor snakeColor, Color backgroundColor) {
        startGame(snakeColor, backgroundColor);
    }

    @Override
    public void onAboutButtonClick() {
        cardLayout.show(mainPanel, "about");
    }
}
