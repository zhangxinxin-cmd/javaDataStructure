package Stack_;

public class Queen8 {
    int max = 8;
    int[] array = new int[max];
    int count = 0;
    int judgeCount = 0;

    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.println(queen8.count);
        System.out.println(queen8.judgeCount);
        System.out.println("hello,world");
    }

    private void print() {
        count++;
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public void check(int n) {
        if (n == max) {
            print();
            return;
        }
        //依次放皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            array[n] = i;
            if (judge(n)) {
                //不冲突
                check(n + 1);
            }
        }
    }

    //    判断n+1行的皇后是否和前n行的皇后冲突
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(i - n) == Math.abs(array[i] - array[n]))
                return false;
        }
        return true;
    }
}
