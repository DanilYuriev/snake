package models;

public class Snake {
    private int length;
    private Directions direction;

    private int headX;
    private int headY;

    private int[] bodyCellsX;
    private int[] bodyCellsY;

    private boolean hasChangedDirections;

    public Snake(int startingCellX, int startingCellY, int startingLength) {
        length = startingLength;
        direction = Directions.Up;
        headX = startingCellX;
        headY = startingCellY;
        bodyCellsX = new int[length - 1];
        bodyCellsY = new int[length - 1];
        for (int i = 0; i < bodyCellsX.length; i++) {
            bodyCellsX[i] = headX;
            if (i == 0) {
                bodyCellsY[i] = headY + 1;
            } else {
                bodyCellsY[i] = bodyCellsY[i - 1] + 1;
            }
        }
        System.out.printf("Змейка появилась в клетке: %d, %d%n", headX, headY);
    }

    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    public int getHeadX() {
        return headX;
    }

    public int getHeadY() {
        return headY;
    }

    public int getLength() {
        return length;
    }

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
    public int[] getBodyCellsX() {
        return bodyCellsX;
    }

    public int[] getBodyCellsY() {
        return bodyCellsY;
    }

    public boolean getHasChangedDirections() {
        return hasChangedDirections;
    }

    public void setHasChangedDirections(boolean hasChangedDirections) {
        this.hasChangedDirections = hasChangedDirections;
    }
    // FIXME убрать смерть о невидимый конец хвоста
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
        length = length + 1;
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