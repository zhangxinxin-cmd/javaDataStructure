package xishuArray;

public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始二维数组
        //0表示没有棋子，1表示黑子,2表示蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 5;
        chessArr1[4][2] = 12;
        int sum = 0;
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
                if (data != 0) sum++;
            }
            System.out.println();
        }

        int[][] spareArr = new int[sum + 1][3];
        int count = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    spareArr[count][0] = i;
                    spareArr[count][1] = j;
                    spareArr[count][2] = chessArr1[i][j];
                }
            }
        }

        spareArr[0][0] = chessArr1.length;
        spareArr[0][1] = chessArr1[0].length;
        spareArr[0][2] = sum;
        System.out.println("得到的稀疏数组为：");
        for (int[] rows : spareArr) {
            for (int datas : rows) {
                System.out.printf("%d\t", datas);
            }
            System.out.println();
        }
        System.out.println("将稀疏数组恢复为原始数组");
        int[][] chessArr2 = new int[spareArr[0][0]][spareArr[0][1]];
        for (int i = 1; i < spareArr.length; i++) {
            chessArr2[spareArr[i][0]][spareArr[i][1]] = spareArr[i][2];
        }
        for (int[] rows : chessArr2) {
            for (int data : rows) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
