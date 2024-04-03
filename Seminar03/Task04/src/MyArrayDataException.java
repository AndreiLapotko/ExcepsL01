import java.util.Map;

public class MyArrayDataException extends IllegalArgumentException {
    public MyArrayDataException(int x, int y) {
        super(String.format("В ячейке [%s][%s] не число", x, y));
    }

    public MyArrayDataException(Map<Point2D, String> cache) {
        super(String.format("Невалидные данные %s", cache));
    }
}