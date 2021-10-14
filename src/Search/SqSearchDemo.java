package Search;

import java.util.Arrays;
import java.util.Random;

public class SqSearchDemo {
    static Random random=new Random(System.currentTimeMillis());
    static int max=8000000;
    public static void main(String[] args) {
    int[] array=new int[max];
        for (int i = 0; i < array.length; i++) {
            array[i]=random.nextInt(max);
        }
        Arrays.sort(array);
        long sortBeforeTime = System.currentTimeMillis();
        System.out.println("排序前时间："+sortBeforeTime);
        System.out.println(sqSearch(array,array[random.nextInt(max)]));
        long sortAfterTime = System.currentTimeMillis();
        System.out.println("排序后时间："+sortAfterTime);
        System.out.println("花费时间："+(double)(sortAfterTime-sortBeforeTime)/1000+"秒");
    }
    public static int sqSearch(int[] array,int value){
        for (int i = 0; i < array.length; i++) {
            if (array[i]==value){
                return i;
            }
        }
        return -1;
    }
}
