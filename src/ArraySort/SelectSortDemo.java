package ArraySort;

import java.util.Arrays;

public class SelectSortDemo {
    public static void main(String[] args) {
        System.out.println("hello,world");
        int[] array = new int[80000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 80000);
        }
        long sortBeforeTime = System.currentTimeMillis();
        System.out.println("排序前时间：" + sortBeforeTime);
        selectSort(array);
        long sortAfterTime = System.currentTimeMillis();
        System.out.println("排序后时间：" + sortAfterTime);
        System.out.println("花费时间：" + (double) (sortAfterTime - sortBeforeTime) / 1000 + "秒");
        System.out.println(Arrays.toString(array));
    }

    /*选择排序就是每次遍历数组，找出最大的和最后面的交换，此时最后一个的位置已经排好，只需确定前length-1个数据的顺序
     * */
    public static void selectSort(int[] array) {
        int maxIndex;
        for (int i = 0; i < array.length; i++) {
            maxIndex = 0;
            for (int j = 1; j < array.length - i; j++) {
                if (array[j] > array[maxIndex]) {
                    maxIndex = j;
                }
            }
            exch(array, array.length - i - 1, maxIndex);
            //选择排序的时间会少于冒泡排序，原因是它仅在每次遍历结束后再交换一次，交换次数少于冒泡排序
        }
    }

    public static void exch(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
