package xishuArray;

import java.util.Arrays;

public class MergeSortDemo {
    public static void main(String[] args) {
        int[] array = new int[]{2, 3, 12, 10, 9, 13, 11};
        mergeSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
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
        int p1 = start;
        int p2 = mid + 1;
        int i = 0;
        int pElse;
        for (i = 0; i < length; i++) {
            if (array[p1] < array[p2]) {
                tempArray[i] = array[p1++];
            } else {
                tempArray[i] = array[p2++];
            }
            if (p1 == mid + 1 || p2 == end + 1)
                break;
        }
        if (p1 == mid + 1)
            pElse = p2;

        else
            pElse = p1;
        while (i < length - 1) {
            tempArray[++i] = array[pElse++];
        }
        for (int j = 0; j < length; j++) {
            array[j + start] = tempArray[j];
        }
    }
}
