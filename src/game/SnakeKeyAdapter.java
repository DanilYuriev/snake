package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import models.Directions;
import models.Game;

public class SnakeKeyAdapter extends KeyAdapter {

    private final Game game;

    public SnakeKeyAdapter(Game game) {
        this.game = game;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (game.getSnake().getHasChangedDirections()) {
            return;
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT: {
                if (game.getSnake().getDirection() != Directions.Left) {
                    game.getSnake().setDirection(Directions.Right);
                    game.getSnake().setHasChangedDirections(true);
                }
                break;
            }
            case KeyEvent.VK_LEFT: {
                if (game.getSnake().getDirection() != Directions.Right) {
                    game.getSnake().setDirection(Directions.Left);
                    game.getSnake().setHasChangedDirections(true);
                }
                break;
            }
            case KeyEvent.VK_UP: {
                if (game.getSnake().getDirection() != Directions.Down) {
                    game.getSnake().setDirection(Directions.Up);
                    game.getSnake().setHasChangedDirections(true);
                }
                break;
            }
            case KeyEvent.VK_DOWN: {
                if (game.getSnake().getDirection() != Directions.Up) {
                    game.getSnake().setDirection(Directions.Down);
                    game.getSnake().setHasChangedDirections(true);
                }
                break;
            }
        }
    }
}
