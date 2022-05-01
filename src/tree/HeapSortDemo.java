package tree;

import java.util.Arrays;

public class HeapSortDemo {
    public static void main(String[] args) {
        int[] array = {1, 3, 2, 6, 5, 7, 8, 9, 10, 0};
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 将数组以下标为parentIndex的元素为根节点构成的子树变成大顶堆
     *
     * @param parentIndex 子树的根节点下标
     * @param length      待操作的元素下标限制
     */
    public static void downAdjust(int[] array, int parentIndex, int length) {
        int temp = array[parentIndex];
        //定义左子节点
        int childIndex = 2 * parentIndex + 1;
        while (childIndex < length) {
            if (childIndex + 1 < length && array[childIndex + 1] > array[childIndex]) {
                childIndex++;
            }
            if (temp >= array[childIndex]) {
                break;
            }
            array[parentIndex] = array[childIndex];
            //更新父节点和左子节点
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
        }
        array[parentIndex] = temp;
    }

    public static void heapSort(int[] array) {
        for (int i = (array.length - 2) / 2; i >= 0; i--) {//array.length-2)/2为最下一个根节点
            downAdjust(array, i, array.length);//调用该方法，使得以i下标开始的子树，成为大顶堆
        }
        System.out.println(Arrays.toString(array));
        for (int i = array.length - 1; i > 0; i--) {
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            downAdjust(array, 0, i);//此处的是为了排除最右边的array[i],也就是array长度减一
        }
    }
}
