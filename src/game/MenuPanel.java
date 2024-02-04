package game;

import models.Game;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuPanel extends JPanel {
    private static final int SCREEN_WIDTH = 660;
    private static final int SCREEN_HEIGHT = 730;

    private StartGameCallback startGameCallback;
    private DisplayAboutCallback displayAboutCallback;

    private JLabel snakeSelectedColorLabel;
    private JPanel snakeGreenPanel;
    private JPanel snakeBluePanel;
    private JPanel snakeYellowPanel;
    private SnakeColor selectedSnakeColor;

    private JPanel backgroundBlackPanel;
    private JPanel backgroundPurplePanel;
    private JPanel backgroundPinkPanel;
    private JLabel backgroundSelectedColorLabel;
    private JTextField lengthField;
    private JTextField levelField;
    private JButton startButton;
    private boolean isSnakeLengthCorrect = true;
    private boolean isLevelCorrect = true;
    private Color selectedBackgroundColor;
    private Game game;

    private final SnakeColor green;
    private final SnakeColor blue;
    private final SnakeColor yellow;

    private JPanel labelPanel;

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
        titleLabel.setText("SNAKE");
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 90));
        titleLabel.setForeground(new Color(58, 135, 79));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(titleLabel);


        JPanel startPanel = new JPanel();
        startPanel.setPreferredSize(new Dimension(600, 50));
        startPanel.setBackground(new Color(40, 40, 40));

        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGameCallback.onStartingGame(selectedSnakeColor, selectedBackgroundColor);
            }
        });
        startButton.setPreferredSize(new Dimension(350, 50));
        startButton.setFont(new Font("Calibri", Font.PLAIN, 24));
        startButton.setVerticalAlignment(JButton.BOTTOM);
        startPanel.add(startButton);
        this.add(startPanel);

        JPanel lengthPanel = new JPanel(new GridLayout(1,2));
        lengthPanel.setBackground(new Color(40, 40, 40));
        JLabel lengthLabel = new JLabel("Length: ");
        lengthLabel.setFont(new Font("Calibri", Font.PLAIN, 24));
        lengthLabel.setForeground(Color.WHITE);
        lengthLabel.setHorizontalAlignment(JLabel.RIGHT);
        JPanel lengthInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,37));
        lengthInputPanel.setBackground(new Color(40, 40, 40));
        lengthField = new JTextField();
        lengthField.setText(Integer.toString(game.getSnakeStartingLength()));
        lengthField.setPreferredSize(new Dimension(100, 22));
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
        this.add(lengthPanel);
        lengthPanel.add(lengthLabel);
        lengthInputPanel.add(lengthField);
        lengthPanel.add(lengthInputPanel);

        JPanel levelPanel = new JPanel(new GridLayout(1,2));
        levelPanel.setBackground(new Color(40, 40, 40));
        JLabel levelLabel = new JLabel("Level: ");
        levelLabel.setFont(new Font("Calibri", Font.PLAIN, 24));
        levelLabel.setForeground(Color.WHITE);
        levelLabel.setHorizontalAlignment(JLabel.RIGHT);
        JPanel levelInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 37));
        levelInputPanel.setBackground(new Color(40, 40, 40));
        levelField = new JTextField();
        levelField.setText(Integer.toString(game.getLevel()));
        levelField.setPreferredSize(new Dimension(100, 22));
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
        this.add(levelPanel);
        levelPanel.add(levelLabel);
        levelPanel.add(levelInputPanel);
        levelInputPanel.add(levelField);

        JPanel snakeColorPanel = new JPanel(new GridLayout(1, 2));
        snakeColorPanel.setBackground(new Color(40, 40, 40));
        JLabel snakeColorLabel = new JLabel("Snake color: ");
        snakeColorLabel.setFont(new Font("Calibri", Font.PLAIN, 24));
        snakeColorLabel.setForeground(Color.WHITE);
        snakeColorLabel.setHorizontalAlignment(JLabel.RIGHT);
        this.add(snakeColorPanel);
        snakeColorPanel.add(snakeColorLabel);

        JPanel snakeColorPickerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 23));
        snakeColorPickerPanel.setBackground(new Color(40, 40, 40));
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
        snakeSelectedColorLabel = new JLabel("X");
        snakeSelectedColorLabel.setFont(new Font("Calibri", Font.PLAIN, 32));
        snakeSelectedColorLabel.setForeground(Color.WHITE);
        snakeSelectedColorLabel.setVerticalAlignment(JLabel.CENTER);
        snakeSelectedColorLabel.setHorizontalAlignment(JLabel.CENTER);
        snakeSelectedColorLabel.setPreferredSize(new Dimension(50, 50));
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
        snakeColorPickerPanel.add(snakeGreenPanel);
        snakeColorPickerPanel.add(snakeBluePanel);
        snakeColorPickerPanel.add(snakeYellowPanel);
        snakeGreenPanel.add(snakeSelectedColorLabel);
        snakeColorPanel.add(snakeColorPickerPanel);


        JPanel backgroundColorPanel = new JPanel(new GridLayout(1, 2));
        backgroundColorPanel.setPreferredSize(new Dimension(300, 25));
        backgroundColorPanel.setBackground(new Color(40, 40, 40));
        JLabel backgroundColorLabel = new JLabel("Background color: ");
        backgroundColorLabel.setFont(new Font("Calibri", Font.PLAIN, 24));
        backgroundColorLabel.setForeground(Color.WHITE);
        backgroundColorLabel.setHorizontalAlignment(JLabel.RIGHT);
        this.add(backgroundColorPanel);
        backgroundColorPanel.add(backgroundColorLabel);

        JPanel backgroundColorPickerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 23));
        backgroundColorPickerPanel.setBackground(new Color(40,40,40));
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
        backgroundSelectedColorLabel = new JLabel("X");
        backgroundSelectedColorLabel.setFont(new Font("Calibri", Font.PLAIN, 32));
        backgroundSelectedColorLabel.setForeground(Color.WHITE);
        backgroundSelectedColorLabel.setVerticalAlignment(JLabel.CENTER);
        backgroundSelectedColorLabel.setHorizontalAlignment(JLabel.CENTER);
        backgroundSelectedColorLabel.setPreferredSize(new Dimension(50, 50));
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
        backgroundColorPickerPanel.add(backgroundBlackPanel);
        backgroundColorPickerPanel.add(backgroundPurplePanel);
        backgroundColorPickerPanel.add(backgroundPinkPanel);
        backgroundBlackPanel.add(backgroundSelectedColorLabel);
        backgroundColorPanel.add(backgroundColorPickerPanel);

        JPanel aboutPanel = new JPanel();
        aboutPanel.setPreferredSize(new Dimension(600, 50));
        aboutPanel.setBackground(new Color(40, 40, 40));
        JButton aboutButton = new JButton("About");
        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAboutCallback.onAboutButtonClick();
            }
        });
        aboutButton.setPreferredSize(new Dimension(350, 50));
        aboutButton.setFont(new Font("Calibri", Font.PLAIN, 24));
        aboutButton.setVerticalAlignment(JButton.BOTTOM);
        aboutPanel.add(aboutButton);
        this.add(aboutPanel);
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

    private void checkIfCanStartGame() {
        startButton.setEnabled(isSnakeLengthCorrect && isLevelCorrect);
        repaint();
    }

    public void selectBlueSnakeColor() {
        snakeGreenPanel.removeAll();
        snakeBluePanel.removeAll();
        snakeYellowPanel.removeAll();
        snakeBluePanel.add(snakeSelectedColorLabel);
        selectedSnakeColor = blue;
    }

    public void selectYellowSnakeColor() {
        snakeGreenPanel.removeAll();
        snakeBluePanel.removeAll();
        snakeYellowPanel.removeAll();
        snakeYellowPanel.add(snakeSelectedColorLabel);
        selectedSnakeColor = yellow;
    }

    public void selectGreenSnakeColor() {
        snakeGreenPanel.removeAll();
        snakeBluePanel.removeAll();
        snakeYellowPanel.removeAll();
        snakeGreenPanel.add(snakeSelectedColorLabel);
        selectedSnakeColor = green;
    }

    public void selectBlackBackgroundColor() {
        backgroundBlackPanel.removeAll();
        backgroundPurplePanel.removeAll();
        backgroundPinkPanel.removeAll();
        backgroundBlackPanel.add(backgroundSelectedColorLabel);
        selectedBackgroundColor = new Color(27, 27, 27);
    }
    public void selectPurpleBackgroundColor() {
        backgroundBlackPanel.removeAll();
        backgroundPurplePanel.removeAll();
        backgroundPinkPanel.removeAll();
        backgroundPurplePanel.add(backgroundSelectedColorLabel);
        selectedBackgroundColor = new Color(65, 49, 92);
    }
    public void selectPinkBackgroundColor() {
        backgroundBlackPanel.removeAll();
        backgroundPurplePanel.removeAll();
        backgroundPinkPanel.removeAll();
        backgroundPinkPanel.add(backgroundSelectedColorLabel);
        selectedBackgroundColor = new Color(92, 49, 73);
    }

    public void registerStartGameCallback(StartGameCallback startGameCallback) {
        this.startGameCallback = startGameCallback;
    }

    public void registerDisplayAboutCallBack(DisplayAboutCallback displayAboutCallback) {
        this.displayAboutCallback = displayAboutCallback;
    }

    public interface StartGameCallback {
        void onStartingGame(SnakeColor snakeColor, Color backgroundColor);
    }

    public interface DisplayAboutCallback {
        void onAboutButtonClick();
    }
}

