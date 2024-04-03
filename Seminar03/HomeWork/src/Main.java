import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

/*
* Напишите приложение, которое будет запрашивать у пользователя следующие данные, разделенные пробелом:

Фамилия Имя Отчество дата _ рождения номер _ телефона пол

Форматы данных:

фамилия, имя, отчество - строки
дата _ рождения - строка формата dd.mm.yyyy
номер _ телефона - целое беззнаковое число без форматирования
пол - символ латиницей f или m.

Приложение должно проверить введенные данные по количеству. Если количество не совпадает, вернуть код ошибки, обработать его и показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.

Приложение должно распарсить полученную строку и выделить из них требуемые значения. Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы. Можно использовать встроенные типы java и создать свои. Исключение должно быть корректно обработано, пользователю выведено сообщение с информацией, что именно неверно.

Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну строку должны записаться полученные данные, вида
<Фамилия> <Имя> <Отчество> <дата _ рождения> <номер _ телефона> <пол>

Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
Не забудьте закрыть соединение с файлом.
При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано, пользователь должен увидеть стектрейс ошибки.
* */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] data;
        do {
            System.out.println("""
                    Введите данные в следующем формате:\s
                    Фамилия Имя Отчество дата_рождения номер_телефона пол
                    где:\s
                    дата рождения - в виде dd.mm.yyyy
                    номер телефона - ########## (десять цифр)
                    пол - символ f или m""");
            String input = scanner.nextLine();
            data = input.split(" ");
        } while (checkInput(data) != 0);
        if (checkInfo(data)) {
            Person person01 = new Person(data);

            String fileName = person01.getLastName() + ".txt";
            try (FileWriter fr = new FileWriter(fileName, true)) {
                fr.write(person01 + "\n");
                fr.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int checkInput(String[] arr) {
        if (arr.length < 6) {
            System.out.println("Данных недостаточно!");
            return -1;
        }
        if (arr.length > 6) {
            System.out.println("Избыточные данные!");
            return 1;
        } else return 0;
    }

    public static boolean containsAlphabeticOnly(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isDigit(c) || !Character.isAlphabetic(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkBirthDay(String str) {
        boolean result = true;
        int day, month, year;
        if (str.length() != 10) {
//            result = false;
            throw new IllegalArgumentException("Формат даты рождения некорректен");
        }
        String[] dateArr = str.split("\\.");
        try {
            day = Integer.parseInt(dateArr[0]);
            month = Integer.parseInt(dateArr[1]);
            year = Integer.parseInt(dateArr[2]);
        } catch (NumberFormatException e) {
//            result = false;
            throw new IllegalArgumentException("Ошибки при вводе даты рождения!");
        }
        try {
            LocalDate localDate = LocalDate.of(year, month, day);
        } catch (Exception e) {
            throw new DateTimeException("Внимательнее вводите дату рождения!");
        }
//        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return result;
    }

    public static boolean checkPhone(String str) {
        boolean result = false;
        if (str.length() != 10) throw new IllegalArgumentException("Формат номера телефона некорректен");
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("Номер телефона содержит недопустимые символы");
//                result = false;
//                break;
            } else result = true;
        }
        return result;
    }

    public static boolean checkGender(String str) {
        boolean result = false;
        char c = str.charAt(0);
        switch (c) {
            case ('m'), ('f') -> result = true;
        }
        if (str.length() != 1 || (c != 'm' && c != 'f')) throw new IllegalArgumentException("Пол указан некорректно");
        return result;
    }

    public static boolean checkInfo(String[] arr) {
        boolean result;
        if (containsAlphabeticOnly(arr[0])) {
            result = false;
            throw new IllegalArgumentException("Некорректная фамилия");
        }
        if (containsAlphabeticOnly(arr[1])) {
            result = false;
            throw new IllegalArgumentException("Некорректное имя");
        }
        if (containsAlphabeticOnly(arr[2])) {
            result = false;
            throw new IllegalArgumentException("Некорректное отчество");
        } //else result = true;
        result = checkBirthDay(arr[3]);
        result = checkPhone(arr[4]);
        result = checkGender(arr[5]);
        return result;
    }
}
