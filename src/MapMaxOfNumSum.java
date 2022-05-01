import java.util.LinkedList;
import java.util.List;

/*A
 * 0 0 0 0 0 0 0  0 0
 * 0 0 0 0 0 0 30 0 0
 * 0 23 0 0 0 0 0 0 0
 * 0 0  0 0 0 0 0 0 0
 * 0 0 10 0 0 0 0 0 0
 * 0 0  0 0 0 0 0 0 0
 * 0 14 0 0 0 9 0 0 0
 * 0 0 0 0 0 0 20 0 0
 * 0 0 0 0 0 0  8 0 0 B
 * */
public class MapMaxOfNumSum {
    static LinkedList<Integer> list = new LinkedList<>();
    static List<List<Integer>> lists = new LinkedList<>();

    public static void main(String[] args) {
        int[][] array = new int[9][9];
        array[2][1] = 23;
        array[1][2] = 30;
        array[0][0] = 10;
        array[6][1] = 14;
        array[6][5] = 9;
        array[7][6] = 20;
        int[] indexArray = {2, 1, 1, 6, 4, 3, 6, 1, 6, 5, 7, 6};
        int[] ints = MapMaxOfNumSumMethod(array, indexArray);
    }

    public static int[] MapMaxOfNumSumMethod(int[][] array, int[] indexArray) {
        MapMaxOfNumSumMethod2(array, indexArray, 0, 0);
        List<Integer> list = new LinkedList<>();
        for (List<Integer> integers : lists) {
            int sum = 0;
            for (Integer integer : integers) {
                sum += integer;
            }
            list.add(sum);
        }
        list.sort(Integer::compareTo);
        return new int[]{list.get(0), list.get(1)};
    }

    public static void MapMaxOfNumSumMethod2(int[][] array, int[] indexArray, int x, int y) {
        if ((x < array.length && y < array.length) || getDistance(array, x, y) <= getMin(indexArray)) {
            lists.add(new LinkedList<Integer>(list));
        }
        for (int i = 0; i < indexArray.length - 1; i += 2) {
            int m = indexArray[i];//纵坐标
            int n = indexArray[i + 1];//横坐标
            if (x == n || (y - m) / (x - n) >= 0) {
                list.add(array[m][n]);
                MapMaxOfNumSumMethod2(array, indexArray, n, m);
                list.removeLast();
            }
        }

    }

    public static int getMin(int[] indexArray) {
        int Min = indexArray[0] * indexArray[0] + indexArray[1] * indexArray[1];
        for (int i = 2; i < indexArray.length - 1; i += 2) {
            int temp = indexArray[i] * indexArray[i] + indexArray[i + 1] * indexArray[i + 1];
            if (temp < Min) {
                Min = temp;
            }
        }
        return Min;
    }

    public static int getDistance(int[][] array, int x, int y) {
        return (x - array[array.length - 1][array[0].length]) * (x - array[array.length - 1][array[0].length]) + (y - array[array.length - 1][array[0].length]) * (y - array[array.length - 1][array[0].length]);
    }
}
