package ArraySort;

public class CountSortDemo {
    static int max=8000000;
    public static void main(String[] args) {
        int[] array=new int[max];
        for (int i = 0; i < array.length; i++) {
            array[i]=(int)(Math.random()*max);
        }
        long sortBeforeTime = System.currentTimeMillis();
        System.out.println("排序前时间："+sortBeforeTime);
        countSort(array);
        long sortAfterTime = System.currentTimeMillis();
        System.out.println("排序后时间："+sortAfterTime);
        System.out.println("花费时间："+(double)(sortAfterTime-sortBeforeTime)/1000+"秒");
    }
    public static void countSort(int[] array){
        int max=array[0];
        int index=0;
        for (int i : array) {
            if (i>max)max=i;
        }
        int[] countArray=new int[max+1];
        for (int j = 0; j < array.length; j++) {
            countArray[array[j]]++;
        }
        for (int m = 0; m < countArray.length; m++) {
            for (int n = 0; n < countArray[m]; n++) {
                array[index++]=m;
            }
        }
    }
}
