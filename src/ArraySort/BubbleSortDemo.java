package ArraySort;

import java.util.Arrays;

public class BubbleSortDemo {
    static int max = 800000;

    public static void main(String[] args) {
        System.out.println("hello,world");
        int[] array = new int[max];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * max);
        }
        long sortBeforeTime = System.currentTimeMillis();
        System.out.println("排序前时间：" + sortBeforeTime);
        BubbleSort(array);
        long sortAfterTime = System.currentTimeMillis();
        System.out.println("排序后时间：" + sortAfterTime);
        System.out.println("花费时间：" + (double) (sortAfterTime - sortBeforeTime) / 1000 + "秒");
        System.out.println(Arrays.toString(array));
    }

    public static void BubbleSort(int[] array) {
        boolean flag = true;
        int lastExchangeIndex = 0;
        int sortBorder = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            flag = true;
            for (int j = 0; j < sortBorder; j++) {
                if (array[j] > array[j + 1]) {
                    exch(array, j, j + 1);
                    lastExchangeIndex = j;
                    flag = false;
                }
            }
            sortBorder = lastExchangeIndex;
            if (flag) break;
        }
    }

    public static void exch(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
