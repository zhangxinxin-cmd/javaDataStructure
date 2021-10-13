package ArraySort;

import java.util.Arrays;

public class InsertSortDemo {
    public static void main(String[] args) {
        int[] array=new int[800000];
        for (int i = 0; i < array.length; i++) {
            array[i]=(int)(Math.random()*800000);
        }
        long sortBeforeTime = System.currentTimeMillis();
        System.out.println("排序前时间："+sortBeforeTime);
        insertSort2(array);
        long sortAfterTime = System.currentTimeMillis();
        System.out.println("排序后时间："+sortAfterTime);
        System.out.println("花费时间："+(double)(sortAfterTime-sortBeforeTime)/1000+"秒");
        System.out.println(Arrays.toString(array));
    }
    //插入排序就是将第j+1个数据插入前j个已经排好序的数据中。
    // 比前一个大，就直接退出循环，此时前j+2个数据有序，比前一个小，就交换第j个和第j+1个数据，继续比较第j个和第j-1个数据
    //交换式插入
    public static void insertSort(int[] array){
        int temp;
        for (int i=1;i<array.length;i++){
            for (int j=i;j>0;j--){
                if (array[j]<array[j-1]){
                    temp=array[j];
                    array[j]=array[j-1];
                    array[j-1]=temp;
                }else
                {
                    break;
                }
            }
        }
    }
    //移位式插入
    //时间要小于交换式
    public static void insertSort2(int[] array){
        for (int i=1;i<array.length;i++){
            int temp=array[i];
            int j;
            for (j=i;j>0&&temp<array[j-1];j--){
                array[j]=array[j-1];
            }
            array[j]=temp;
        }
    }
    public static void exch(int[] array,int i,int j){
        int temp=array[i];
        array[i]=array[j];
        array[j]=temp;
    }
}
