package ArraySort;

public class ShellSortDemo {
    static int max = 8000000;

    public static void main(String[] args) {
        int[] array = new int[max];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * max);
        }
        long sortBeforeTime = System.currentTimeMillis();
        System.out.println("排序前时间：" + sortBeforeTime);
        shellSort2(array);
        long sortAfterTime = System.currentTimeMillis();
        System.out.println("排序后时间：" + sortAfterTime);
        System.out.println("花费时间：" + (double) (sortAfterTime - sortBeforeTime) / 1000 + "秒");
//        System.out.println(Arrays.toString(array));
    }

    //希尔排序就是将数据按gap的间隔分组，gap等于组内相邻元素下标之差。在分好的组内进行插入排序
    //每一轮排序后各组元素都是有序的。然后gap按一定的规则减小，再进行相同的组内插入排序，直至gap==1，最后一轮排序结束，此时gap==0，表示已经排完序
    //gap=1时的排序就是间隔为一的普通插入排序，
    //交换式
    public static void shellSort(int[] array) {
        int gap = array.length;
        while (gap > 0) {
            gap = gap / 2;
            for (int i = gap; i < array.length; i++) {
                for (int j = i; j > gap - 1 && array[j] < array[j - gap]; j -= gap) {
                    exch(array, j, j - gap);
                }
            }
        }
    }

    public static void exch(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //移位式
    //时间要小于交换式
    public static void shellSort2(int[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                int temp = array[gap];
                int j;
                for (j = i; j > gap - 1 && temp < array[j - gap]; j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = temp;
            }
        }
    }
}
