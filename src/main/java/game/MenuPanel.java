package game;

import models.Game;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Класс MenuPanel отвечает за отображение стартового меню.
 */
public class MenuPanel extends JPanel {
    private static final int SCREEN_HEIGHT = 730;
    private static final int SCREEN_WIDTH = 660;

    private final JPanel backgroundBlackPanel;
    private final JPanel backgroundPurplePanel;
    private final JPanel backgroundPinkPanel;
    private final JLabel backgroundSelectedColorLabel;

    private final Game game;

    private final SnakeColor green;
    private final SnakeColor blue;
    private final SnakeColor yellow;

    private final JTextField lengthField;
    private final JTextField levelField;

    private final JPanel snakeGreenPanel;
    private final JPanel snakeBluePanel;
    private final JPanel snakeYellowPanel;
    private final JLabel snakeSelectedColorLabel;

    private final JButton startButton;

    private boolean isSnakeLengthCorrect = true;
    private boolean isLevelCorrect = true;

    private DisplayAboutCallback displayAboutCallback;
    private StartGameCallback startGameCallback;

    private SnakeColor selectedSnakeColor;
    private Color selectedBackgroundColor;

    /**
     * Создать экземпляр интерфейса стартового меню.
     * @param game Экземпляр текущей игры.
     */
    public MenuPanel(Game game) {
        this.game = game;

        this.green = new SnakeColor(new Color(35, 161, 64), new Color(35, 220, 86));
        this.blue = new SnakeColor(new Color(32, 60, 125), new Color(50, 92, 191));
        this.yellow = new SnakeColor(new Color(142, 150, 27), new Color(193, 204, 39));

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(new Color(40, 40, 40));
        this.setFocusable(true);
        this.setLayout(new GridLayout(7, 1, 100, 0));

        selectedSnakeColor = green;
        selectedBackgroundColor = new Color(27, 27, 27);

        JLabel titleLabel = new JLabel();
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 90));
        titleLabel.setForeground(new Color(58, 135, 79));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setText("SNAKE");
        this.add(titleLabel);

        JPanel startPanel = new JPanel();
        startPanel.setBackground(new Color(40, 40, 40));
        startPanel.setPreferredSize(new Dimension(600, 50));
        this.add(startPanel);

        startButton = new JButton("Start");
        startButton.addActionListener(e -> startGameCallback.onStartingGame(selectedSnakeColor, selectedBackgroundColor));
        startButton.setFont(new Font("Calibri", Font.PLAIN, 24));
        startButton.setPreferredSize(new Dimension(350, 50));
        startButton.setVerticalAlignment(JButton.BOTTOM);
        startPanel.add(startButton);

        JPanel lengthPanel = new JPanel(new GridLayout(1,2));
        lengthPanel.setBackground(new Color(40, 40, 40));
        this.add(lengthPanel);

        JLabel lengthLabel = new JLabel("Length: ");
        lengthLabel.setFont(new Font("Calibri", Font.PLAIN, 24));
        lengthLabel.setForeground(Color.WHITE);
        lengthLabel.setHorizontalAlignment(JLabel.RIGHT);
        lengthPanel.add(lengthLabel);

        JPanel lengthInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,37));
        lengthInputPanel.setBackground(new Color(40, 40, 40));
        lengthPanel.add(lengthInputPanel);

        lengthField = new JTextField();
        lengthField.setPreferredSize(new Dimension(100, 22));
        lengthField.setText(Integer.toString(game.getSnakeStartingLength()));
        lengthField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                parseSnakeLength();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                parseSnakeLength();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        lengthInputPanel.add(lengthField);

        JPanel levelPanel = new JPanel(new GridLayout(1,2));
        levelPanel.setBackground(new Color(40, 40, 40));
        this.add(levelPanel);

        JLabel levelLabel = new JLabel("Level: ");
        levelLabel.setFont(new Font("Calibri", Font.PLAIN, 24));
        levelLabel.setForeground(Color.WHITE);
        levelLabel.setHorizontalAlignment(JLabel.RIGHT);
        levelPanel.add(levelLabel);

        JPanel levelInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 37));
        levelInputPanel.setBackground(new Color(40, 40, 40));
        levelPanel.add(levelInputPanel);

        levelField = new JTextField();
        levelField.setPreferredSize(new Dimension(100, 22));
        levelField.setText(Integer.toString(game.getLevel()));
        levelField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                parseLevel();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                parseLevel();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        levelInputPanel.add(levelField);

        JPanel snakeColorPanel = new JPanel(new GridLayout(1, 2));
        snakeColorPanel.setBackground(new Color(40, 40, 40));
        this.add(snakeColorPanel);

        JLabel snakeColorLabel = new JLabel("Snake color: ");
        snakeColorLabel.setFont(new Font("Calibri", Font.PLAIN, 24));
        snakeColorLabel.setForeground(Color.WHITE);
        snakeColorLabel.setHorizontalAlignment(JLabel.RIGHT);
        snakeColorPanel.add(snakeColorLabel);

        JPanel snakeColorPickerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 23));
        snakeColorPickerPanel.setBackground(new Color(40, 40, 40));
        snakeColorPanel.add(snakeColorPickerPanel);

        snakeGreenPanel = new JPanel();
        snakeGreenPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectGreenSnakeColor();
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        snakeGreenPanel.setBackground(new Color(35, 220, 86));
        snakeGreenPanel.setPreferredSize(new Dimension(50, 50));
        snakeColorPickerPanel.add(snakeGreenPanel);

        snakeBluePanel = new JPanel();
        snakeBluePanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectBlueSnakeColor();
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        snakeBluePanel.setBackground(new Color(50, 92, 191));
        snakeBluePanel.setPreferredSize(new Dimension(50, 50));
        snakeColorPickerPanel.add(snakeBluePanel);

        snakeYellowPanel = new JPanel();
        snakeYellowPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectYellowSnakeColor();
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        snakeYellowPanel.setBackground(new Color(193, 204, 39));
        snakeYellowPanel.setPreferredSize(new Dimension(50, 50));
        snakeColorPickerPanel.add(snakeYellowPanel);

        snakeSelectedColorLabel = new JLabel("X");
        snakeSelectedColorLabel.setFont(new Font("Calibri", Font.PLAIN, 32));
        snakeSelectedColorLabel.setForeground(Color.WHITE);
        snakeSelectedColorLabel.setHorizontalAlignment(JLabel.CENTER);
        snakeSelectedColorLabel.setPreferredSize(new Dimension(50, 50));
        snakeSelectedColorLabel.setVerticalAlignment(JLabel.CENTER);
        snakeGreenPanel.add(snakeSelectedColorLabel);

        JPanel backgroundColorPanel = new JPanel(new GridLayout(1, 2));
        backgroundColorPanel.setBackground(new Color(40, 40, 40));
        backgroundColorPanel.setPreferredSize(new Dimension(300, 25));
        this.add(backgroundColorPanel);

        JLabel backgroundColorLabel = new JLabel("Background color: ");
        backgroundColorLabel.setFont(new Font("Calibri", Font.PLAIN, 24));
        backgroundColorLabel.setForeground(Color.WHITE);
        backgroundColorLabel.setHorizontalAlignment(JLabel.RIGHT);
        backgroundColorPanel.add(backgroundColorLabel);

        JPanel backgroundColorPickerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 23));
        backgroundColorPickerPanel.setBackground(new Color(40,40,40));
        backgroundColorPanel.add(backgroundColorPickerPanel);

        backgroundBlackPanel = new JPanel();
        backgroundBlackPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectBlackBackgroundColor();
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        backgroundBlackPanel.setBackground(new Color(27, 27, 27));
        backgroundBlackPanel.setPreferredSize(new Dimension(50, 50));
        backgroundColorPickerPanel.add(backgroundBlackPanel);

        backgroundPurplePanel = new JPanel();
        backgroundPurplePanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectPurpleBackgroundColor();
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        backgroundPurplePanel.setBackground(new Color(65, 49, 92));
        backgroundPurplePanel.setPreferredSize(new Dimension(50, 50));
        backgroundColorPickerPanel.add(backgroundPurplePanel);

        backgroundPinkPanel = new JPanel();
        backgroundPinkPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectPinkBackgroundColor();
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        backgroundPinkPanel.setBackground(new Color(92, 49, 73));
        backgroundPinkPanel.setPreferredSize(new Dimension(50, 50));
        backgroundColorPickerPanel.add(backgroundPinkPanel);

        backgroundSelectedColorLabel = new JLabel("X");
        backgroundSelectedColorLabel.setFont(new Font("Calibri", Font.PLAIN, 32));
        backgroundSelectedColorLabel.setForeground(Color.WHITE);
        backgroundSelectedColorLabel.setVerticalAlignment(JLabel.CENTER);
        backgroundSelectedColorLabel.setHorizontalAlignment(JLabel.CENTER);
        backgroundSelectedColorLabel.setPreferredSize(new Dimension(50, 50));
        backgroundBlackPanel.add(backgroundSelectedColorLabel);

        JPanel aboutPanel = new JPanel();
        aboutPanel.setBackground(new Color(40, 40, 40));
        aboutPanel.setPreferredSize(new Dimension(600, 50));
        this.add(aboutPanel);

        JButton aboutButton = new JButton("About");
        aboutButton.addActionListener(e -> displayAboutCallback.onAboutButtonClick());
        aboutButton.setFont(new Font("Calibri", Font.PLAIN, 24));
        aboutButton.setPreferredSize(new Dimension(350, 50));
        aboutButton.setVerticalAlignment(JButton.BOTTOM);
        aboutPanel.add(aboutButton);
    }

    /**
     * Зарегистрировать displayAboutCallback.
     * @param displayAboutCallback Обработчик события нажатия на кнопку about.
     */
    public void registerDisplayAboutCallBack(DisplayAboutCallback displayAboutCallback) {
        this.displayAboutCallback = displayAboutCallback;
    }

    /**
     * Зарегистрировать startGameCallback.
     * @param startGameCallback Обработчик события нажатия на кнопку start.
     */
    public void registerStartGameCallback(StartGameCallback startGameCallback) {
        this.startGameCallback = startGameCallback;
    }

    /**
     * Выбрать синий цвет змеи в интерфейсе.
     */
    public void selectBlueSnakeColor() {
        snakeGreenPanel.removeAll();
        snakeBluePanel.removeAll();
        snakeYellowPanel.removeAll();
        snakeBluePanel.add(snakeSelectedColorLabel);
        selectedSnakeColor = blue;
    }

    /**
     * Выбрать желтый цвет змеи в интерфейсе.
     */
    public void selectYellowSnakeColor() {
        snakeGreenPanel.removeAll();
        snakeBluePanel.removeAll();
        snakeYellowPanel.removeAll();
        snakeYellowPanel.add(snakeSelectedColorLabel);
        selectedSnakeColor = yellow;
    }

    /**
     * Выбрать зелёный цвет змеи в интерфейсе.
     */
    public void selectGreenSnakeColor() {
        snakeGreenPanel.removeAll();
        snakeBluePanel.removeAll();
        snakeYellowPanel.removeAll();
        snakeGreenPanel.add(snakeSelectedColorLabel);
        selectedSnakeColor = green;
    }

    /**
     * Выбрать черный цвет фона игрового поля в интерфейсе.
     */
    public void selectBlackBackgroundColor() {
        backgroundBlackPanel.removeAll();
        backgroundPurplePanel.removeAll();
        backgroundPinkPanel.removeAll();
        backgroundBlackPanel.add(backgroundSelectedColorLabel);
        selectedBackgroundColor = new Color(27, 27, 27);
    }

    /**
     * Выбрать фиолетовый цвет фона игрового поля в интерфейсе.
     */
    public void selectPurpleBackgroundColor() {
        backgroundBlackPanel.removeAll();
        backgroundPurplePanel.removeAll();
        backgroundPinkPanel.removeAll();
        backgroundPurplePanel.add(backgroundSelectedColorLabel);
        selectedBackgroundColor = new Color(65, 49, 92);
    }

    /**
     * Выбрать розовый цвет фона игрового поля в интерфейсе.
     */
    public void selectPinkBackgroundColor() {
        backgroundBlackPanel.removeAll();
        backgroundPurplePanel.removeAll();
        backgroundPinkPanel.removeAll();
        backgroundPinkPanel.add(backgroundSelectedColorLabel);
        selectedBackgroundColor = new Color(92, 49, 73);
    }

    private void checkIfCanStartGame() {
        startButton.setEnabled(isSnakeLengthCorrect && isLevelCorrect);
        repaint();
    }

    private void parseLevel() {
        var levelString = levelField.getText();
        try {
            var level = Integer.parseInt(levelString);
            if (level < 0 || level > 50) {
                isLevelCorrect = false;
            } else {
                game.setLevel(level);
                isLevelCorrect = true;
            }
        } catch (Exception exception) {
            isLevelCorrect = false;
        } finally {
            checkIfCanStartGame();
        }
    }

    private void parseSnakeLength() {
        var lengthString = lengthField.getText();
        try {
            var length = Integer.parseInt(lengthString);
            if (length <= 1 || length > 6) {
                isSnakeLengthCorrect = false;
            } else {
                game.setSnakeStartingLength(length);
                isSnakeLengthCorrect = true;
            }
        } catch (Exception exception) {
            isSnakeLengthCorrect = false;
        } finally {
            checkIfCanStartGame();
        }
    }

    /**
     * Обработчик события нажатия на кнопку about.
     */
    public interface DisplayAboutCallback {
        void onAboutButtonClick();
    }

    /**
     * Обработчик события нажатия на кнопку start.
     */
    public interface StartGameCallback {
        void onStartingGame(SnakeColor snakeColor, Color backgroundColor);
    }
}

