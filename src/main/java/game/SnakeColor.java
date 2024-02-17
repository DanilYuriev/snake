package game;

import java.awt.*;

/**
 * SnakeColor хранит в себе информацию о цвете змеи.
 * @param head Цвет головы.
 * @param body Цвет тела.
 */
public record SnakeColor(Color head, Color body) { }
