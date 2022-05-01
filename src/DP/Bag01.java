package DP;

public class Bag01 {
    public static void main(String[] args) {
        System.out.println(solution(10, new int[][]{{2, 1}, {3, 3}, {4, 5}, {7, 9}}));
        System.out.println(solution(8, new int[][]{{2, 3}, {3, 4}, {4, 5}, {5, 6}}));
    }

    public static int solution(int M, int[][] bags) {
        int[][] dp = new int[bags.length + 1][M + 1];
        //dp数组包含了物品 编号为0(也就是没有物品)和容量为0(也就是没有容量),因此下标为i的物品实际上对应bags数组中下标为i-1的物品
        for (int i = 1; i < dp.length; i++) {//第i行
            for (int j = 1; j < dp[0].length; j++) {//第j列
                if (j < bags[i - 1][0]) {//背包容量小于当前考虑放进的物品重量
                    dp[i][j] = dp[i - 1][j];
                } else {
                    //不放进当前物品，当前i个物品放置的最大价值等同于i-1个物品放置的最大价值
                    //要放进当前物品，就必须考虑当容量减去当前物品重量时的前i-1个物品放置情况，即前i-1个物品最大价值再加上要放进的物品价值即为放进当前物品的总价值
                    //上述两种情况求最大值即可】
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - bags[i - 1][0]] + bags[i - 1][1]);
                }
            }
        }
        return dp[bags.length][M];
    }
}
