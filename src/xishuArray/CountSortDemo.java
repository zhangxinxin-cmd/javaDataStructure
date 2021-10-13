package xishuArray;

import java.util.Arrays;

public class CountSortDemo {
    public static void main(String[] args) {
        int[] array = {2, 3, 1, 0, 4, 5, 8};
        countSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void countSort(int[] array) {
        int max = array[0];
        for (int i : array) {
            if (i > max) max = i;
        }
        int[] countArray = new int[max + 1];
        for (int i = 0; i < array.length; i++) {
            countArray[array[i]]++;
        }
        int index = 0;
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                array[index++] = i;
            }
        }
    }
}
