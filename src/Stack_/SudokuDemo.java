package Stack_;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class SudokuDemo {
    Random r = new Random();

    public static void main(String[] args) {
        int[][] array = new int[3][3];
        SudokuDemo sudoku = new SudokuDemo();
        sudoku.createSudoku(array, 0, 0);
        sudoku.printArray(array);
    }

    //生成一个数独
    public boolean createSudoku(int[][] array, int m, int n) {
        if (m == array.length) {
            return true;
        }
        if (set(array, m, n)) {
            if (n < array.length - 1) {
                return createSudoku(array, m, n + 1);
            } else {
                return createSudoku(array, m + 1, 0);
            }
        } else {
            return false;
        }
    }

    public boolean set(int[][] array, int m, int n) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            list.add(i + 1);
        }
        for (int i = 0; i < n; i++) {
            if (list.contains(array[m][i])) {
                list.remove(Integer.valueOf(array[m][i]));
//                System.out.println("第"+i+"次删除"+array[m][i]+"list大小为"+list.size());
            }
        }
        for (int j = 0; j < m; j++) {
            if (list.contains(array[j][n])) {
                list.remove(Integer.valueOf(array[j][n]));
//                System.out.println("第"+j+"次删除"+array[m][j]+"list大小为"+list.size());
            }
        }
        int index = 0;
        /*try {
            index=r.nextInt(list.size());
        }catch (Exception e){
            System.out.println(e.getMessage());
            printArray(array);
        }*/
        if (list.size() == 0) {
            return false;
        }
        array[m][n] = list.get(index);
        return true;
    }

    public void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
