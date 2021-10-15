package ArraySort;

import java.util.Arrays;

public class MergeSortDemo {
    static int max = 8000000;

    public static void main(String[] args) {
        int[] array = new int[max];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * max);
        }
        long sortBeforeTime = System.currentTimeMillis();
        System.out.println("排序前时间：" + sortBeforeTime);
        mergeSort(array, 0, array.length - 1);
        long sortAfterTime = System.currentTimeMillis();
        System.out.println("排序后时间：" + sortAfterTime);
        System.out.println("花费时间：" + (double) (sortAfterTime - sortBeforeTime) / 1000 + "秒");
    }

    public static void mergeSort(int[] array, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(array, start, mid);
            mergeSort(array, mid + 1, end);
            merge(array, start, mid, end);
        }
    }

    public static void merge(int[] array, int start, int mid, int end) {
        int length = end - start + 1;
        int[] tempArray = new int[length];
        int p = 0;
        int p1 = start;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= end) {
            if (array[p1] < array[p2]) {
                tempArray[p++] = array[p1++];
            } else {
                tempArray[p++] = array[p2++];
            }
        }
        while (p1 <= mid) {
            tempArray[p++] = array[p1++];
        }
        while (p2 <= end) {
            tempArray[p++] = array[p2++];
        }
        for (int i = 0; i < tempArray.length; i++) {
            array[i + start] = tempArray[i];
        }
    }
}
