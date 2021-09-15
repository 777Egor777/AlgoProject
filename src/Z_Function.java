/**
 * Теория. Блок 1.
 * Z-функция + Алгоритм КМП.
 *
 * 1) Пусть у нас есть строка s длиной n символов.
 * Индексация символов, как обычно, с 0: 0, 1, 2, ..., n-1
 *
 * 2) Подстроку s, огрангиченную индексами l и r,
 * будем обозначать как s{l, r}.
 * Например, вся строка является подстрокой самой себя,
 * и будет обозначаться как s{0, n-1}
 *
 * Например, для строки s = "abcdada" одними из подстрок являются:
 * s{0, 2} = "abc"
 * s{0, 4} = "abcda"
 * s{1, 3} = "bcd"
 * В Java подстроку мождно получить как s.substring(pos, len),
 * где pos - индекс начала подстроки, а n - длина подстроки.
 * То есть, в нашей терминологии s{l, r} = s.substring(l, r - l + 1)
 *
 * 3) Суффиксом № i (или i-м cуффиксом) строки s{0, n-1} называется
 * её подстрока s{i, n-1}. То есть подстрока, левая граница которой - элемент i,
 * а правая граница - конец строки. Будем обозначать i-й суффикс строки
 * как s.suf(i). То есть s.suf(i) = s{i, n-1}
 * Естественно что существует всего n суффиксов с началом в i = 0, ..., n-1
 * идексах строки.
 *
 * Например, у строки s = "abcd"
 * s.suf(0) = "abcd"
 * s.suf(1) = "bcd"
 * s.suf(2) = "cd"
 * s.suf(3) = "d"
 *
 * 4) Аналогично определяется и префикс строки.
 *    s.pref(i) = s{0, i}.
 * Например, s = "abcd",
 * s.pref(0) = "a"
 * s.pref(1) = "ab"
 * s.pref(2) = "abc"
 * s.pref(3) = "abcd"
 *
 * 5) Максимальный общий префикс строк a и b
 * mcp - maximal common prefix
 * mcp(a,b) = k, если:
 *      1. a.pref(k - 1) = b.pref(k - 1), то есть префиксы длины k строк a и b равны.
 *      2. Для всех q > k: a.pref(q - 1) != b.pref(q - 1)
 * Если у строк a и b не совпадают первые элементы,
 * то mcp(a,b) = 0
 *
 * Примеры:
 * mcp("abc", "abcd") = 3
 * mсp("abc", "xyz") = 0
 * mcp("gqef", "g") = 1
 *
 * 6) Z-функция для строки s длины n
 * Это массив int[] z длины n, где:
 *      1. z[0] = 0
 *      2. z[i] = mcp(s, s.suf(i)) для всех i = 1,..., n-1
 *
 * 7) Алгоритм КМП для поиска всех вхождений строки b в строку a.
 *      1. Делаем строку s = b + "#" + a
 *      2. Считаем z-функцию для s
 *      3. Если z[i] = b.length(), то есть вхождение
 *         строки b в строку а.
 *
 * 8) Тривиальный подсчёт z - функции за O(N^2)
 *      public int[] zFunc(String s) {
 *          int n = s.length();
 *          int[] z = new int[n];
 *          z[0] = 0;
 *          for (int i = 1; i < n; ++i) {
 *              for(int j = 0; j < n && s.charAt(i+j) == s.charAt(j); ++j) {
 *                  z[i]++;
 *              }
 *          }
 *          return z;
 *      }
 *
 *  9) Подсчёт за O(N).
 *     Допустим, мы подсчитали z-функцию
 *     для символа i, и она равна k.
 *     Значит, s{i, i + k - 1} == s.pref(k)
 *     И, соответственно s{i + 1, i + k - 1} == s{1, k - 1}
 *     А z[1] мы уже знаем, соответственно можем уже проапдейтить z[i+1]
 *     Итак, z[i+1] = min(z[1], k - 1).
 *
 *     Мы поддерживаем окно (l, r)  - самое правое на данный
 *     момент вхождение префикса строки, которое мы нашли,
 *     вычисляя z-функцию. Самое правое - значит с наибольшим r.
 *     Изначально l=r=0, и мы апдейтим его окном (i, i + z[i] - 1)
 *     после вычисления z-функции очередного элемента i.
 *
 *     Пока наверное выглядит сложно, попробуем написать код.
 *     public int[] zFunc(String s) {
 *          int n = s.length();
 *          int[] z = new int[n];
 *          z[0] = 0;
 *          // window
 *          int l = 0;
 *          int r = 0;
 *          for (int i = 1; i < n; ++i) {
 *              // update z[i] by current window
 *              if (r > i) {
 *                  int windowSize = r - l + 1;
 *                  int windowPos = i - l;
 *                  int maxUpdate = r - i + 1;
 *                  z[i] = Math.min(maxUpdate,  z[windowPos]);
 *              }
 *
 *              // Additional linear update
 *              while (i + z[i] < n && s.charAt(i + z[i]) == s.charAt(z[i])) {
 *                  z[i]++;
 *              }
 *
 *              // update window
 *              if (i + z[i] - 1 > r) {
 *                  l = i;
 *                  r = i + z[i] - 1;
 *              }
 *          }
 *          return z;
 *     }
 *
 * @author Geraskin Egor(geraskin@yandex-team.ru)
 * @since 9/15/21
 */
public class Z_Function {
    public static void main(String[] args) {

    }

    //Задание #1. Рализовать z-функцию за O(N^2)
    public static int[] zFuncSquare(String s) {
        return null;
    }

    //Задание #2. Рализовать z-функцию за O(N)
    public static int[] zFuncLinear(String s) {
        return null;
    }

    //Задание #3. Найти количество вхождений
    //строки b в строку a за O(N)
    public static int allEntries(String a, String b) {
        return 0;
    }

    //Задание #4. Найти первое вхождение
    //строки b в строку a за O(N)
    public static int firstEntry(String a, String b) {
        return -1;
    }
}
