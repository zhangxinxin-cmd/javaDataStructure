package test;

//斐波那契数列：1，1，2，3，5，8，13，...
public class demo {
    public static void main(String[] args) {
        /*try {

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        long before1 = System.currentTimeMillis();
        System.out.println(fun1(50));
        long after1 = System.currentTimeMillis();
        System.out.println("花费时间："+(after1-before1)/1000);
        long before2 = System.currentTimeMillis();
        System.out.println(fun2(50));
        long after2 = System.currentTimeMillis();
        System.out.println("花费时间："+(after2-before2)/1000);*/
    }

    public static long fun1(int n) {
        long[] dp = new long[n];
        return fei(n, dp);
    }

    public static long fei(int n, long[] dp) {
        if (dp[n - 1] != 0) {
            return dp[n - 1];
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        dp[n - 1] = fei(n - 1, dp) + fei(n - 2, dp);
        return dp[n - 1];
    }

    public static int fun2(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fun2(n - 1) + fun2(n - 2);
    }
}
