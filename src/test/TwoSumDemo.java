package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
* 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
你可以按任意顺序返回答案。
* */
public class TwoSumDemo {
    public static void main(String[] args) {
        int[] array = {1, 3, 4, 6, 5};
        System.out.println(Arrays.toString(twoSum(array, 7)));
    }

    public static int[] twoSum(int[] array, int target) {
        Map<Integer, Integer> hashtable = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (hashtable.containsKey(target - array[i])) {
                return new int[]{hashtable.get(target - array[i]), i};
            }
            hashtable.put(array[i], i);
        }
        return new int[0];
    }
}
