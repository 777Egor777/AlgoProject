import java.util.Map;

/**
 * Строка a является гибридом строки b,
 * если в ней можно переставить символы так,
 * чтобы она стала равна строке b.
 *
 * Иными словами, строки являются гибридами
 * друг друга, если их словари вхождений
 * символов совпадают.
 *
 * В задачах такого типа нам надо считать
 * и оптимальн опересчитывать словари
 * символов строк. То есть
 * Map<Character, Integer> - считать для
 * каждого символа строки количество его вхождений.
 *
 * @author Geraskin Egor(geraskin@yandex-team.ru)
 * @since 9/17/21
 */
public class StringHybridsTask {
    public static void main(String[] args) {

    }

    // Посчитать словарь вхождений символов в строку
    public static Map<Character, Integer> charDict(String pattern) {
        return null;
    }

    // Является ли строка pattern гибридом target?
    public static boolean isHybrid(String target, String pattern) {
        return false;
    }

    /*
        Зачем считать словари?
        Чтобы по ним сравнивать строки на гибридность.

        Допустим, у нас есть строка target = "abcdabc"
        и строка pattern = "abc".

        Нам нужно найти все вхождения гибридов
        pattern в строку target.

        Что мы будем делать?
        Идти подряд по всем подстрокам target длины
        pattern( то есть, в этом случае, длины 2)
        и сравнивать словари этих подстрок со
        словарём строки pattern
        Примерно так:
        int n = target.length();
        int m = pattern.length();
        for (int i = 0; i < n - m; ++i) {
            .. Сравниваем словари
            строк target{i, i + m - 1}
            и pattern
        }
     */

    // Напомню обозначение подстроки,
    // которое мы ввели в прошлом материале по
    // z-функции.
    // s{l, r} - подстрока строки s от символа l
    // до символа r включительно.
    // то есть включает подрояд символы: s[l], s[l+1], ..., s[r]

    // В этом варианте задачи надо
    // реализовать самый простой вариант -
    // всё время пересчитывать заново словарь
    // target{i, i + m - 1} и сравнивать его
    // со словарём pattern.
    public static int simpleCountOfHybrids(String target, String pattern) {
        return 0;
    }

    /*
        Теперь давайте подумаем, как внести первую
        оптимизацию, и всё время не пересчитывать
        словарь, переходя от подстроки target{i, ...}
        к подстроке target{i + 1, ...}.
        Заметим, что i-й символ надо удалить,
        а (i+m) - й символ добавить.
     */

    // Дана строка target длины n + 1.
    // Подсчитан словарь её подстроки target{0, n - 1}
    // Нужно пересчитать словарь для target{1, n} и
    // вернуть его за O(1)
    public static Map<Character, Integer> recomputeHybridMap(String target,
                                                             Map<Character, Integer> map) {
        return null;
    }

    /*
        Но уметь пересчитывать словарь мало.
        Нам ведь надо ещё уметь за O(1) отвечать на
        главный вопрос: совпадает ли словарь pattern
        с текущим словарём target?

        Для этого мы будем поддерживать целочисленную
        характеристику: текущее количество совпадений
        между словарём pattern и текущей подстроки
        target. Напоминаю, что мы перебираем подстроки
        строки target, которые по длине совпадают
        со строкой pattern.

        Эту характеристику назовём matches.
        matches = количество символов c, таких
        что mapTarget.get(c) == mapPattern.get(c).
        То есть они входят в обе строки в равных
        количествах.

        Важно! Величину matches есть смысл высчитывать
        только для строк равной длины, и только
        с той целью, чтобы сравнивать их на гибридность.
     */

    // Гарантируется, что target.length() = pattern.length()
    // Вернуть их matches
    public static int getMatches(String target, String pattern) {
        return 0;
    }

    /*
        Для удобства введём другую величину, notMatches.
        Она наоборот равна количеству символов c таких,
        что по ним словари не совпадают.
        То есть, notMatches - это количество таких
        уникальных c, что
        mapTarget.get(c) != mapPattern.get(c).

        Прошлую величину мы искали для тренировки
        и для понимания, эта величина notMatches
        нам будет очень важна в дальнейшем.
     */

    // Гарантируется, что target.length() = pattern.length()
    // Вернуть их notMatches
    public static int getNotMatches(String target, String pattern) {
        return 0;
    }


    /*
        Окей, теперь мы должны понять, как эту величину
        пересчитывать.

        Да точно так же, как пересчитываем словарь
        подстрок target.

        Один символ уходит из словаря -
        если по нему раньше было совпадение словарей,
        значит notMatches++.

        Другой символ приходит в словарь.
        Если по нему словари стали совпадать,
        значит notMatches--.

        Очевидно, что если после пересчёта notMatches == 0,
        то гибриды совпадают и мы должны увеличить результат
        на 1( в задаче поиска количества вхождений гибридов)
     */

    /**
     * Пересчитать величину notMatches.
     *
     * По условию этой подзадачи
     * длина строки target = n + 1.
     * Длина образца pattern = n.
     *
     * То есть target на 1 символ длиннее,
     * чем pattern.
     *
     * На вход приходят уже подсчитанные словари
     * для pattern и target{0, n - 1}.
     *
     * А так же величина notMatches
     * для строк pattern и target{0, n - 1}.
     *
     * Вернуть notMatches для следующей
     * подстроки target, т.е. для target{1, n}
     * и pattern
     *
     * @param target - строка, вхождения гибридов в
     *                 которую мы считаем.
     * @param targetMap - словарь количеств вхождений
     *                    символов в эту строку.
     * @param patternMap - словарь количеств вхождений
     *                     символов в строку образец -
     *                     pattern
     * @param notMatches - величина notMatches для строк
     *                     target{0, n-1} и pattern
     * @return величина notMatches для строк
     *         target{1, n} и pattern
     */
    public static int recomputeNotMatches(String target,
                                   Map<Character, Integer> targetMap,
                                   Map<Character, Integer> patternMap,
                                   int notMatches) {
        return 0;
    }

    /**
     *  Теперь мы готовы объединить
     *  все наработки и решить
     *  финальную задачу -
     *  найти количество вхождений
     *  гибридов строки pattern
     *  в строку target за
     *  время O(N+M),
     *  где N - длина target
     *  а M - длина pattern
     */
    public static int hardCountOfHybrids(String target, String pattern) {
        return 0;
    }
}
