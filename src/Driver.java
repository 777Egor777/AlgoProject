import java.util.Arrays;

/**
 *    Водителю надо попасть из города 1 в город N. Он едет последовательно(1->2->3->...->N).
 *    Расстояния между городами в км известны и даны в виде массива из N-1 числа dist[]
 *    Изначально он заправлен на 50 литров бензина. На 100км уходит 10 литров бензина.
 *    В каждом городе он может либо заправиться на patrol[i] литров бензина, либо вообще не заправляться.
 *    Какое минимальное количество раз ему нужно заправиться, чтобы доехать до конца?
 *    Если он не сможет доехать до конца, вернуть -1.
 *    N <= 100. 100 <= dist[i] <= 5000. 1 <= patrol[i] <= 10.
 *    dist[i] кратно 100
 *
 * @author Geraskin Egor
 * @since 9/6/21
 */
public class Driver {
    public static void main(String[] args) {
        int result = new Driver().solution(3, new int[]{500, 500}, new int[]{50, 1});
        System.out.println(result);
    }

    public static int MAX_PATROL = 1100;
    // Использовать Integer.MAX_VALUE в этой
    // задаче чревато переполнениями.
    public static int INF = (int)1e9;

    // Формально состояние динамики: d[i][j]
    // Где i - индекс города, j - количество литров в баке машины,
    // d[i][j] - минимальное количество заправок, необходимое чтобы
    // достичь этого состояния
    // Но мы не будем хранить двумерный массив, а будем
    // увеличивать i от 0 до n-1, и пересчитывать массив
    // d[j] - минимальное количество заправок, чтобы
    // иметь в баке j литров.
    public int solution(int n, int[] dist, int[] patrol) {
        int result = INF;
        int[] d = new int[MAX_PATROL];
        Arrays.fill(d, INF);
        // Задаём изначальные значения
        // В баке изначально по условию 50 литров.
        d[50] = 0;
        for (int i = 0; i < n - 1; ++i) {
            // Сначала проапдейтим состояния на предмет
            // заправки в i-м городе
            int addPatrol = patrol[i];
            for (int j = MAX_PATROL - 1; j >= addPatrol; j--) {
                d[j] = Math.min(d[j], d[j - addPatrol] + 1);
            }
            // Далее проапдейтим состояния на предмет
            // расхода бензина между городами.
            int spentPatrol = dist[i] / 10; // dist[i] / 100 * 10
            for (int j = 0; j < MAX_PATROL - spentPatrol; j++) {
                d[j] = d[j + spentPatrol];
            }

        }
        //  По конечным состояниям находим результат.
        for (int i = 0; i < MAX_PATROL; ++i) {
            result = Math.min(result, d[i]);
        }
        return result < Integer.MAX_VALUE ? result : -1;
    }
}
