public class MyArraySizeException extends IndexOutOfBoundsException {
    public MyArraySizeException() {
        super("Массив должен быть 3х3");
    }
}