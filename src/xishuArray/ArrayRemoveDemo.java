package xishuArray;

import java.util.Arrays;

public class ArrayRemoveDemo {
    public static void main(String[] args) {
        int[] array = {1, 2, 4, 3, 2, 5, 6, 2, 1, 7};
        System.out.println(removeElement(array, array.length, 2));
    }

    //删除2，并返回删除后的长度
    public static int removeElement(int[] array, int size, int value) {
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < size; fastIndex++) {
            if (value != array[fastIndex]) {
                array[slowIndex++] = array[fastIndex];
            }
        }
        return slowIndex;
    }
}
