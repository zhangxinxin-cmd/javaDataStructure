package Search;

import ArraySort.CountSortDemo;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/*
二分查找的前提是数组有序，这里默认数组从大到小排列
* 二分法查找就是找到数组(left---right)中间值和value比较，
* value大则标明其在中间值右边，left=mid+1,
* 反之则表明value在中间值左边，right=mid-1;
* */
public class BinarySearchDemo {
    static Random random = new Random(System.currentTimeMillis());
    static int max = 80000;

    public static void main(String[] args) {
        int[] array = new int[max];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(max);
        }
        CountSortDemo.countSort(array);
        long sortBeforeTime = System.currentTimeMillis();
        System.out.println("排序前时间：" + sortBeforeTime);
        System.out.println(binarySearch3(array, array[random.nextInt(max)]));
        long sortAfterTime = System.currentTimeMillis();
        System.out.println("排序后时间：" + sortAfterTime);
        System.out.println("花费时间：" + (double) (sortAfterTime - sortBeforeTime) / 1000 + "秒");
    }

    //递归实现二分法
    public static int binarySearch(int[] array, int left, int right, int value) {
        if (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] < value) {
                return binarySearch(array, mid + 1, right, value);
            } else if (array[mid] > value) {
                return binarySearch(array, left, mid - 1, value);
            } else {
                return mid;
            }
        }
        return -1;
    }

    //循环实现二分法
    public static int binarySearch2(int[] array, int value) {
        int left = 0;
        int right = array.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (array[mid] == value) {
                return mid;
            } else if (array[mid] < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    //改进，找到所有满足要求的下标并返回
    public static List<Integer> binarySearch3(int[] array, int value) {
        List<Integer> list = new LinkedList<>();
        int left = 0;
        int right = array.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (array[mid] == value) {
                list.add(mid);
                int i = mid - 1;
                int j = mid + 1;
                while (i >= 0 && array[i] == value) {
                    list.add(i--);
                }
                while (j <= array.length && array[j] == value) {
                    list.add(j++);
                }
                return list;
            } else if (array[mid] < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }
}
