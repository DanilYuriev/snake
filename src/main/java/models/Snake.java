package models;

/**
 * Класс Snake представляет собой модель змеи.
 */
public class Snake {
    private int[] bodyCellsX;

    /**
     * Получить значение поля bodyCellsX.
     * @return Массив с Х-координатами туловища змеи.
     */
    public int[] getBodyCellsX() {
        return bodyCellsX;
    }

    private int[] bodyCellsY;

    /**
     * Получить значение поля bodyCellsY.
     * @return Массив с Y-координатами туловища змеи.
     */
    public int[] getBodyCellsY() {
        return bodyCellsY;
    }

    private Directions direction;

    /**
     * Получить значение поля direction.
     * @return Текущее направление змеи.
     */
    public Directions getDirection() {
        return direction;
    }

    /**
     * Задать значение поля direction.
     * @param direction Текущее направление змеи.
     */
    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    private boolean hasChangedDirections;

    /**
     * Получить значение поля hasChangedDirections.
     * @return Изменилось ли направление движения змеи.
     */
    public boolean getHasChangedDirections() {
        return hasChangedDirections;
    }

    /**
     * Задать значение поля hasChangedDirections.
     * @param hasChangedDirections Изменилось ли направление движения змеи.
     */
    public void setHasChangedDirections(boolean hasChangedDirections) {
        this.hasChangedDirections = hasChangedDirections;
    }

    private int headX;

    /**
     * Получить значение поля headX.
     * @return Х-координата головы змеи.
     */
    public int getHeadX() {
        return headX;
    }

    private int headY;

    /**
     * Получить значение поля headY.
     * @return Y-координата головы змеи.
     */
    public int getHeadY() {
        return headY;
    }

    private int length;

    /**
     * Получить значение поля length.
     * @return Длина змеи.
     */
    public int getLength() {
        return length;
    }

    /**
     * Создать экземпляр змеи.
     * @param startingCellX Х-координата начальной позиции змеи на поле.
     * @param startingCellY Y-координата начальной позиции змеи на поле.
     * @param startingLength Начальная длина змеи.
     */
    public Snake(int startingCellX, int startingCellY, int startingLength) {
        length = startingLength;

        bodyCellsX = new int[length - 1];
        bodyCellsY = new int[length - 1];
        direction = Directions.Up;
        headX = startingCellX;
        headY = startingCellY;

        for (int i = 0; i < bodyCellsX.length; i++) {
            bodyCellsX[i] = headX;
            if (i == 0) {
                bodyCellsY[i] = headY + 1;
            } else {
                bodyCellsY[i] = bodyCellsY[i - 1] + 1;
            }
        }
    }

    /**
     * Получить новые координаты головы змеи.
     * @return Массив координат головы в формате [Х, Y].
     */
    public int[] getNewHeadCoordinates() {
        int[] headCoords = new int[2];
        if (direction == Directions.Right) {
            headCoords[0] = headX + 1;
            headCoords[1] = headY;
        } else if (direction == Directions.Left) {
            headCoords[0] = headX - 1;
            headCoords[1] = headY;
        } else if (direction == Directions.Up) {
            headCoords[0] = headX;
            headCoords[1] = headY - 1;
        } else if (direction == Directions.Down) {
            headCoords[0] = headX;
            headCoords[1] = headY + 1;
        }
        return headCoords;
    }

    /**
     * Выполнить передвижение змеи.
     * @param hasEatenFood Была ли съедена еда в этом ходе.
     */
    public void move(boolean hasEatenFood) {
        if (hasEatenFood) {
            increaseLength();
        }

        for (int i = bodyCellsX.length - 1; i > 0; i--) {
            bodyCellsX[i] = bodyCellsX[i - 1];
            bodyCellsY[i] = bodyCellsY[i - 1];
        }
        bodyCellsX[0] = headX;
        bodyCellsY[0] = headY;

        int[] newHeadCoordinates = getNewHeadCoordinates();
        headX = newHeadCoordinates[0];
        headY = newHeadCoordinates[1];
    }

    private void increaseLength() {
        length++;
        int[] newBodyCellsX = new int[length - 1];
        int[] newBodyCellsY = new int[length - 1];

        for (int i = 0; i < bodyCellsX.length; i++) {
            newBodyCellsX[i] = bodyCellsX[i];
            newBodyCellsY[i] = bodyCellsY[i];
        }

        bodyCellsX = newBodyCellsX;
        bodyCellsY = newBodyCellsY;
    }
}