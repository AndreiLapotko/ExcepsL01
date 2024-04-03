import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Запишите в файл следующие строки:
 * <p>
 * Анна=4
 * Елена=5
 * Марина=6
 * Владимир=?
 * Полина=?
 * Константин=?
 * Иван=4
 * <p>
 * Реализуйте метод, который считывает данные из файла и сохраняет в двумерный массив (либо HashMap,
 * если студенты с ним знакомы). В отдельном методе нужно будет пройти по структуре данных,
 * если сохранено значение ?, заменить его на соответствующее число.Если на каком-то месте
 * встречается символ, отличный от числа или ?, бросить подходящее исключение.
 * Записать в тот же файл данные с замененными символами ?.
 */
public class Task3 {

    private static final List<String> strings = List.of
            ("Анна=4",
                    "Елена=5",
                    "Марина=6",
                    "Полина=?",
                    "Владимир=?",
                    "Константин=?",
                    "Иван=4");
//для проверки, заменить какое-то из значений на null

    public static void main(String[] args) {
        String path = "task3.txt";
        write(path, strings);
        rewrite(path);
    }

    private static void write(String path, List<String> strings) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(path);
            for (String string : strings) {
                fileWriter.write(string + "\n");
            }
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Map<String, Integer> analyze(String path) {
        Map<String, Integer> resultMap = new LinkedHashMap<>(7);
        BufferedReader br = null;
        int lineNumber = 1;
        try {
            br = new BufferedReader(new FileReader(path));
            String line;

            while ((line = br.readLine()) != null) {
                String[] arr = line.split("=");
                String key = arr[0];
                String temp = arr[1];
                int value;
                if (temp.equals("?")) value = key.length();
                else value = Integer.parseInt(temp);
                resultMap.put(key, value);
                lineNumber++;
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid data in line " + lineNumber);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultMap;
    }

    private static void rewrite(String path) {
        Map<String, Integer> data = analyze(path);
        List<String> newStrings = new ArrayList<>(7);

        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            newStrings.add(entry.toString());
        }
        write(path, newStrings);
    }
}
