import java.util.HashMap;
import java.util.Map;

/**
 * Напишите метод, на вход которого подаётся двумерный строковый массив размером
 * 3х3.
 * При подаче массива другого размера необходимо бросить исключение
 * MyArraySizeException.
 * <p>
 * 1. Далее метод должен пройтись по всем элементам массива, преобразовать в
 * число и просуммировать.
 * Если в каком-то элементе массива преобразование не удалось
 * (например, в ячейке лежит символ или текст вместо числа), должно быть брошено
 * исключение MyArrayDataException
 * с детализацией, в какой именно ячейке лежат неверные данные.
 * <p>
 * 2. В методе main() вызвать полученный метод, обработать возможные исключения
 * MyArraySizeException и MyArrayDataException и вывести результат расчета
 * (сумму элементов, при условии, что подали на вход корректный массив).
 */
public class Task4 {
    static String[][] arr = new String[][]{
            {"1", "h", "f"},
            {"1", "1.5", "1.5"},
            {"1", "t", "1.5"}
    };

    public static void main(String[] args) {
        System.out.println(sum2dv2(arr));
    }

    public static double sum2d(String[][] arr) {
        double res = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr.length != arr[i].length) {
                throw new MyArraySizeException();
            }
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    res += Double.parseDouble(arr[i][j]);
                } catch (IllegalArgumentException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return res;
    }

    public static double sum2dv2(String[][] arr) {
        Map<Point2D, String> cache = new HashMap<>();
        double res = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr.length != arr[i].length) {
                throw new MyArraySizeException();
            }
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    res += Double.parseDouble(arr[i][j]);
                } catch (IllegalArgumentException e) {
                    cache.put(new Point2D(i, j), arr[i][j]);
                }
            }
        }
        if (!cache.isEmpty()) {
            throw new MyArrayDataException(cache);
        }
        return res;
    }
}