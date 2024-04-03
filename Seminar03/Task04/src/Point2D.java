import java.util.Objects;

public class Point2D {
    private int x;
    private int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("ячейка: (%s; %s)", x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Point2D))
            return false;
        Point2D point2d = (Point2D) o;
        return x == point2d.x && y == point2d.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}