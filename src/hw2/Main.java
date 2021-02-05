package hw2;

import hw2.exceptions.MyArrayDataException;
import hw2.exceptions.MyArraySizeException;

import java.util.Arrays;

public class Main {
    private final static int ROWS = 4;
    private final static int COLS = 4;

    public static void main(String[] args) {
        String[][] array = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"},
        };

        Integer sumArrayElements = null;

        // Передаем массив array в метод getDeepSumArray() для подсчета суммы его элементов, обрабатываем все исключения
        // который может бросить вызываемый метод
        try {
            sumArrayElements = getDeepSumArray(array);
        } catch (MyArraySizeException e) {
            System.err.println(e.getMessage());
            System.out.println();
        } catch (MyArrayDataException e) {
            System.err.printf("Элемент %s: %s\n\n", e.getPosition(), e.getMessage());
        }

        // Если сумма элементов была опдсчитана выводим ее результат
        if (sumArrayElements != null) {
            System.out.println("Сумма элементов массива равна " + sumArrayElements);
        } else {
            System.out.println("Не удалось посчитать сумму элементов массива");
        }
    }

    /**
     * Преобразует строковые элементы массива в число и считает их сумму
     * @param array массив сумму элементов которого необходимо посчитать
     * @return сумму элементов массива
     * @throws MyArraySizeException если размер массива не сообветствует заданному (ROWS x COlS)
     * @throws MyArrayDataException если элемент массива не возможно преобразовать в число
     */
    private static int getDeepSumArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;

        // Если размер массива не корректен бросаем исключение MyArraySizeException
        if (!isCorrectSize(array)) {
            throw new MyArraySizeException("Не корректный размер массива");
        }

        int i = 0;
        int j = 0;

        // Преобразуем каждый элемент массива к числу и считаем сумму всех элементов, если возникло исключение
        // NumberFormatException перехватываем его и бросаем новое исключение MyArrayDataException передавая ему
        // позицию элемента который не удалось преобразовать.
        try {
            for (; i < array.length; i++) {
                for (j = 0; j < array[i].length; j++) {
                    sum += Integer.parseInt(array[i][j]);
                }
            }
        } catch (NumberFormatException e) {
            throw new MyArrayDataException("Ошибка преобразования в число", i, j);
        }

        return sum;
    }

    /**
     * Проверяет что размер массива соответствует заданному. Число строк равно ROWS и число столбцов каждой строки
     * равно COLS
     * @param array массив для проверки
     * @return true если размер корректный, иначе false
     */
    private static boolean isCorrectSize(String[][] array) {
        return array.length == ROWS && Arrays.stream(array).allMatch(a -> a.length == COLS);
    }
}
