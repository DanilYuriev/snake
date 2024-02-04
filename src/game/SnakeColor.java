package game;

import javax.swing.*;
import java.awt.*;

public class SnakeColor {
    private final Color head;
    private final Color body;

    public SnakeColor(Color head, Color body) {
        this.head = head;
        this.body = body;
    }

    public Color getHead() {
        return head;
    }
    public Color getBody() {
        return body;
    }

}
