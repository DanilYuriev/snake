package game;

import models.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Класс SnakeForm является контейнером для всех графических элементов игры.
 */
public class SnakeForm extends JFrame implements Game.GameOverCallback, MenuPanel.StartGameCallback, MenuPanel.DisplayAboutCallback {
    private final CardLayout aboutCardLayout;
    private final CardLayout cardLayout;
    private final Game game;
    private final GameOverPanel gameOver;
    private final JPanel mainPanel;
    private final JPanel tabsContentPanel;

    /**
     * Создать экземпляр графического контейнера.
     * @param game Экземпляр текущей игры.
     */
    public SnakeForm(Game game) {
        this.game = game;
        game.registerGameOverCallback(this);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        MenuPanel menu = new MenuPanel(game);
        menu.registerDisplayAboutCallBack(this);
        menu.registerStartGameCallback(this);
        mainPanel.add(menu, "menu");

        gameOver = new GameOverPanel(game);
        mainPanel.add(gameOver, "gameOver");

        JPanel aboutPanel = new JPanel();
        aboutPanel.setBackground(new Color(40, 40, 40));
        aboutPanel.setLayout(new BoxLayout(aboutPanel, BoxLayout.Y_AXIS));
        mainPanel.add(aboutPanel, "about");

        JPanel tabsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tabsPanel.setBackground(new Color(40, 40, 40));
        aboutPanel.add(tabsPanel);

        JButton helpButton = new JButton("Help");
        helpButton.addActionListener(e -> displayHelp());
        tabsPanel.add(helpButton);

        JButton developerButton = new JButton("Developer");
        developerButton.addActionListener(e -> displayDeveloper());
        tabsPanel.add(developerButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "menu"));
        tabsPanel.add(backButton);

        aboutCardLayout = new CardLayout(0, 20);
        tabsContentPanel = new JPanel();
        tabsContentPanel.setBackground(new Color(40, 40, 40));
        tabsContentPanel.setLayout(aboutCardLayout);
        aboutPanel.add(tabsContentPanel);

        JPanel helpPanel = new JPanel();
        helpPanel.setBackground(new Color(40, 40, 40));
        tabsContentPanel.add(helpPanel, "help");

        JTextArea helpTextArea = new JTextArea("Welcome to Snake Game!\n\nHere's a quick guide:\nControl the snake using arrow keys: Up, Down, Left, Right.\n\nThe objective is to eat food, grow, and avoid hitting walls or yourself.\nPlan your moves, eat strategically, and avoid collisions.\nThe game ends if you hit a wall or yourself, displaying your score based on food and length.\n\nScore higher by growing longer.\nHave fun, improve, and aim for high scores!");
        helpTextArea.setBackground(new Color(40, 40, 40));
        helpTextArea.setEditable(false);
        helpTextArea.setFont(new Font("Calibri", Font.PLAIN, 22));
        helpTextArea.setForeground(Color.WHITE);
        helpTextArea.setLineWrap(true);
        helpTextArea.setPreferredSize(new Dimension(660, 500));
        helpPanel.add(helpTextArea);

        JPanel developerPanel = new JPanel();
        developerPanel.setBackground(new Color(40, 40, 40));
        tabsContentPanel.add(developerPanel, "developer");

        JTextArea developerTextArea = new JTextArea("Developer:\nDanil Yuriev\n\nGit:\nhttps://github.com/DanilYuriev");
        developerTextArea.setBackground(new Color(40, 40, 40));
        developerTextArea.setEditable(false);
        developerTextArea.setFont(new Font("Calibri", Font.PLAIN, 22));
        developerTextArea.setForeground(Color.WHITE);
        developerTextArea.setLineWrap(true);
        developerTextArea.setPreferredSize(new Dimension(660, 300));
        developerPanel.add(developerTextArea);

        add(mainPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Snake");
        this.setVisible(true);

        this.pack();

        cardLayout.show(mainPanel, "menu");
    }

    /**
     * Показать экран окончания игры.
     */
    public void callingBack() {
        gameOver.prepare();
        cardLayout.show(mainPanel, "gameOver");
    }

    /**
     * Показать вкладку с информацией о разработчике.
     */
    public void displayDeveloper() {
        aboutCardLayout.show(tabsContentPanel, "developer");
    }

    /**
     * Показать вкладку со справкой.
     */
    public void displayHelp() {
        aboutCardLayout.show(tabsContentPanel, "help");
    }

    /**
     * Показать справочный экран.
     */
    @Override
    public void onAboutButtonClick() {
        cardLayout.show(mainPanel, "about");
    }

    /**
     * Запустить игру.
     * @param snakeColor Цвет змеи.
     * @param backgroundColor Цвет фона.
     */
    @Override
    public void onStartingGame(SnakeColor snakeColor, Color backgroundColor) {
        startGame(snakeColor, backgroundColor);
    }

    /**
     * Подготовка интерфейса к запуску игры.
     * @param snakeColor Цвет змеи.
     * @param backgroundColor Цвет фона.
     */
    public void startGame(SnakeColor snakeColor, Color backgroundColor) {
        SnakePanel snake = new SnakePanel(game, snakeColor, backgroundColor);
        GameInfoPanel gameInfo = new GameInfoPanel(game);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(gameInfo);
        panel.add(snake);

        mainPanel.add(panel, "snake");
        cardLayout.show(mainPanel, "snake");
        snake.grabFocus();
     }

    private JPanel panel1;
}
