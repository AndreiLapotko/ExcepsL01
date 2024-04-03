import java.io.IOException;

/**
 Создайте класс Счетчик, у которого есть метод add(), увеличивающий значение
 внутренней int переменной на 1. Сделайте так, чтобы с объектом такого типа
 можно было работать в блоке try-with-resources. Подумайте, что должно происходить
 при закрытии этого ресурса? Напишите метод для проверки, закрыт ли ресурс.
 При попытке вызвать add() у закрытого ресурса, должен выброситься IOException
 */
public class Counter implements AutoCloseable{
    public int num = 1;
    private boolean isClosed = false;

    public void add() throws IOException {
        if (isClosed) {
            throw new IOException("ресурс закрыт");
        }
        num++;
    }

    @Override
    public void close() throws Exception {
        isClosed = true;
    }
}

class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        try(counter) {
            System.out.println(counter.num);
            counter.add();
            System.out.println(counter.num);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            counter.add();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
