package hw2.exceptions;

public class MyArrayDataException extends RuntimeException {
    private final int row;
    private final int col;

    public MyArrayDataException(String message, int row, int col) {
        super(message);
        this.row = row;
        this.col = col;
    }

    /**
     * Формирует строковое представление позиции элемента в массиве
     * @return позицию элемента в массиве
     */
    public String getPosition() {
        return String.format("[%d, %d]", row, col);
    }
}
