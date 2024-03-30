import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static String[] endMan = {"евич", "ович"};
    private  static String[] endWoman = {"евна", "овна"};
    public static void main(String[] args) {
        System.out.println("Чтобы завершить программу введите: exit");
        System.out.println("Введите ФИО и дату рождения:");
        // Регекс выражение
        var regex = "[А-Яа-яЁё]+\\s[А-Яа-яЁё]+\\s[А-Яа-яЁё]+\\s(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[012])\\.(\\d{4})";
        var scanner = new Scanner(System.in);
        // Пока пользователь не ведет exit, программа будет работать
        while (true)
        {
            // Считываем строчку
            var data = scanner.nextLine();
            if (data.equals("exit"))
                return;
            // Если у нас совпадает с регекс выражением, то идем дальше
            if (data.matches(regex)) {
                // Делим по пробелу и точкам
                var splitData = data.split("[\\s.]");
                // Будем по отчеству определять пол человека
                var gender = getGender(splitData[2]);
                // Получаем день, месяц и год
                var days = Integer.parseInt(splitData[3]);
                var month = Integer.parseInt(splitData[4]);
                var year = Integer.parseInt(splitData[5]);
                // Получаем текущую дату
                var currDate = LocalDate.now();
                // Вычитаем из текущей даты день, месяц и год, веденные пользователем
                currDate = currDate.minusYears(year).minusMonths(month).minusDays(days);
                var totalYears = currDate.getYear();
                // Если пользователь ввел дату, которая еще не наступила, то сообщим ему об этом
                if (totalYears < 0) {
                    System.out.println("Человек еще не родился");
                }
                // Иначе выведем данные на консоль в формате Фам Имя Отч Пол кол-во полных лет
                else System.out.printf("\n\nФИО: %s %s %s.\nПол: %s\nПолных лет: %d.\n\n", splitData[0], splitData[1], splitData[2], gender, totalYears);
            }
            // Иначе попросим ввести снова
            else {
                System.out.println("Неправильный формат введенных данных");
                System.out.println("Нужно вводить так: Фамилия Имя Отчество xx.xx.xxxx");
            }
            System.out.println("Введите ФИО и дату рождения:");
        }
    }
    public static String getGender(String patronymic){
        // Смотрим окончание отчества
        for (var end : endMan){
            if (patronymic.endsWith(end))
                return "Мужской.";
        }
        for (var end : endWoman){
            if (patronymic.endsWith(end))
                return "Женский.";
        }
        return "Не определен!";
    }
}