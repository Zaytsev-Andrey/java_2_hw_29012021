package hw5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private final static int SIZE = 10000000;
    private final static float DEFAULT_VALUE = 1.0f;

    public static void main(String[] args) {
        System.out.printf("Создание массива размером %d элементов заполненного значениями %.1f и выполнение операций" +
                        " над ним в одном потоке:\n", SIZE, DEFAULT_VALUE);
        arrayCalculations();

        System.out.println();
        int threadCount = 2;
        System.out.printf("Создание массива размером %d элементов заполненного значениями %.1f и выполнение операций" +
                " над ним в %d потоках:\n", SIZE, DEFAULT_VALUE, threadCount);
        multithreadedArrayCalculations(threadCount);
    }

    private static void arrayCalculations() {
        float[] arr = new float[SIZE];

        Arrays.fill(arr, DEFAULT_VALUE);

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        System.out.printf("Время работы метода %d ms\n", System.currentTimeMillis() - startTime);
    }

    private static void multithreadedArrayCalculations(int threadCount) {
        float[] arr = new float[SIZE];
        List<Thread> threads = new ArrayList<>();

        Arrays.fill(arr, DEFAULT_VALUE);

        long startTime = System.currentTimeMillis();

        // Создаем двухмерный массив для копирования в него частей основного массива
        float[][] partsArr = createArrayParts(threadCount);

        // Копируем части основного массива в двухмерный массив частей
        // Кождую часть запускам на обработку в отдельном потоке
        int startIndexOfPart = 0;
        for (int i = 0; i < threadCount; i++) {
            System.arraycopy(arr, startIndexOfPart, partsArr[i], 0, partsArr[i].length);
            ArrayService service = new ArrayService(partsArr[i]);
            Thread thread = new Thread(service);
            threads.add(thread);
            thread.start();
            startIndexOfPart += partsArr[i].length;
        }


        // Ожидаем завершения работы всех потоков
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Копируем все обработанные части в исходный массив
        int startIndex = 0;
        for (int i = 0; i < threadCount; i++) {
            System.arraycopy(partsArr[i], 0, arr, startIndex, partsArr[i].length);

            startIndex += partsArr[i].length;
        }

        System.out.printf("Время работы метода %d ms", System.currentTimeMillis() - startTime);
    }

    /**
     * Создает двухмерный массив, число строк в котором равно параметру "count", а число столбцов в строке равно целому
     * значению от деления SIZE на count, при этом к количеству столбцов в первой строке добавляеся остаток от деления
     * SIZE на count
     * @param count количество строк
     * @return новый массив
     */
    private static float[][] createArrayParts(int count) {
        float[][] partsArr = new float[count][];
        int partSize = SIZE / count;
        int remainder = SIZE % count;

        partsArr[0] = new float[partSize + remainder];
        for (int i = 1; i < count; i++) {
            partsArr[i] = new float[partSize];
        }

        return partsArr;
    }
}
