import java.io.IOException;

public class Task1 {
    public static void main(String[] args) {
        try {
            doSomething();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void doSomething() throws IOException {
//        throw new IOException();
    }
}
