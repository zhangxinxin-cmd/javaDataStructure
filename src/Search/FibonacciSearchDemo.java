package Search;

import java.util.Arrays;
import java.util.Scanner;

public class FibonacciSearchDemo {
    public static void main(String[] args) {
        int[] array = new int[21];
        for (int i = 0; i < array.length; i++) {
            array[i] = 2 * (i + 1);
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int value = scanner.nextInt();
            System.out.println(fibonacciSearch(array, value));
        }
    }

    //生成一个斐波那契数组
    private static int[] fib() {
        int[] fib = new int[20];
        fib[0] = 1;
        fib[2] = 1;
        for (int i = 2; i < fib.length; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }

    public static int fibonacciSearch(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        int[] fib = fib();
        int mid;
        int k = 0;
        while (high > fib[k] - 1) {
            k++;
        }
        int[] temp = Arrays.copyOf(array, fib[k]);
        for (int i = array.length; i < temp.length; i++) {
            temp[i] = array[high];
        }
        while (low <= high) {
            mid = low + fib[k - 1] - 1;
            if (temp[mid] > value) {
                high = mid - 1;
                k--;
            } else if (temp[mid] < value) {
                low = mid + 1;
                k -= 2;
            } else {
                return Math.min(mid, high);//如果mid<high,返回mid，否则返回high
            }
        }
        return -1;
    }
}
