import java.io.FileNotFoundException;
import java.io.FileReader;

public class Task3 {
    public static void main(String[] args) {
//        System.out.println(divideByZero(5, 0));

        nonExistedFile("1111");
    }
    public static double divideByZero(int a, int b) {
        if (b == 0) throw new DivideByZeroException();
        double res = a / b;
        return res;
    }

    public static void nonExistedFile(String path) {
        try {
            FileReader fr = new FileReader(path);
        } catch (FileNotFoundException e) {
            try {
                throw new NonExistedFileException();
            } catch (NonExistedFileException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
