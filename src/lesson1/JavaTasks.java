package lesson1;

import kotlin.NotImplementedError;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     *
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     *
     * Пример:
     *
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     *
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     *
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     * throw new NotImplementedError();
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка адресов
     *
     * Средняя
     *
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     *
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     *
     * Людей в городе может быть до миллиона.
     *
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     *
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     *
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     *
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     *
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     *
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */

    static public void sortTemperatures(String inputName, String outputName) throws IOException {

        //сложность O(n), где n - количество чисел в данном файле
        //память O(1) - всегда фиксированная

        int[] positive = new int[5001];
        int[] negative = new int[2731];

        try (BufferedReader br = new BufferedReader(new FileReader(inputName))) {

            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                if (!Pattern.matches("[\\s-]?\\d+[.]\\d", currentLine)) throw new NotImplementedError();
                double doub = Double.parseDouble(currentLine) * 10;
                int index = (int) doub;

                if (index < 0) negative[index * (-1)]++;
                else positive[index]++;
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputName))) {
            for (int i = 2730; i > 0; i--) {
                if (negative[i] > 0) {
                    for (int t = 1; t <= negative[i]; t++) bw.write("-" + ((double) i )/ 10 + "\n");
                }
            }

            for (int i = 0; i < 5001; i++) {
                if (positive[i] > 0) {
                    for (int t = 1; t <= positive[i]; t++) bw.write(((double) i )/ 10 + "\n");
                }
            }
        }
    }

    /**
     * Сортировка последовательности
     *
     * Средняя
     * (Задача взята с сайта acmp.ru)
     *
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     *
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     *
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     *
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    static public void sortSequence(String inputName, String outputName) throws IOException {

        //сложность O(n), где n - количество чисел в данном файле
        //память O(n), где n - количество чисел в данном файле

        ArrayList<Integer> sequence = new ArrayList<>();
        Map<Integer, Integer> pairs = new HashMap<>();
        int most = 0;
        Integer theNumber = 2147483647;

        try (BufferedReader br = new BufferedReader(new FileReader(inputName))) {

            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                if (!Pattern.matches("\\d+", currentLine)) throw new NotImplementedError();
                int number = Integer.parseInt(currentLine);
                sequence.add(number);
                if (pairs.containsKey(number)) pairs.put(number, pairs.get(number) + 1);
                else pairs.put(number, 1);
                if (pairs.get(number) > most) {
                    most = pairs.get(number);
                    theNumber = number;
                }
                if (pairs.get(number) == most) {
                    if (number <= theNumber)
                        theNumber = number;
                }
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputName))) {

            if (sequence.isEmpty()) return;

            for (Integer integer : sequence) {
                if (!integer.equals(theNumber)) bw.write(integer + "\n");
            }
            for (int i = pairs.get(theNumber); i > 0; i--) bw.write(theNumber + "\n" );
        }
    }

    /**
     * Соединить два отсортированных массива в один
     *
     * Простая
     *
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     *
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     *
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
