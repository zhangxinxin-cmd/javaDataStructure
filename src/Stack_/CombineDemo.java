package Stack_;

import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.List;

//给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。你可以按 任何顺序 返回答案。
public class CombineDemo {
    private int startIndex = 1;
    LinkedList<Integer> list = new LinkedList<>();
    List<List<Integer>> lists = new LinkedList<>();

    public static void main(String[] args) {
        CombineDemo combineDemo = new CombineDemo();
        List<List<Integer>> combine = combineDemo.combine(4, 2);
        System.out.println(combine);
    }

    public void backTracking(int n, int k) {
        if (list.size() == k) {
            lists.add(new LinkedList<>(list));
            return;
        }
        for (int i = startIndex; i <= n; i++) {
            list.add(i);
            startIndex = i + 1;
            backTracking(n, k);
            list.removeLast();
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        backTracking(n, k);
        return lists;
    }
}
