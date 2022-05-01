package test;

import java.util.*;

/*
 * 找到数组中出现一次的数字
 * */
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(Arrays.toString(test.twoSum(new int[]{3, 2, 4}, 6)));
        System.out.println(test.lengthOfLongestSubstring("bbbbb"));
    }

    LinkedList<Integer> list = new LinkedList<>();

    public int[] twoSum(int[] nums, int target) {
        twoSum(nums, target, 0);
        return new int[]{list.get(0), list.get(1)};
    }

    public boolean twoSum(int[] nums, int target, int index) {
        if (list.size() == 2 && target == 0) {
            return true;
        }
        for (int i = index; i < nums.length; i++) {
            list.add(i);
            target -= nums[i];
            if (twoSum(nums, target, index + 1)) {
                return true;
            } else {
                target += nums[i];
                list.removeLast();
            }
        }
        return false;
    }

    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int length = s.length();
        int start = 0;
        int max = 0;
        for (int index = 0; index < length; index++) {
            char ch = s.charAt(index);
            if (map.containsKey(ch)) {
                start = map.get(ch) + 1;
            }
            max = Math.max(max, index - start + 1);
            map.put(ch, index);
        }
        return max;
    }
}
