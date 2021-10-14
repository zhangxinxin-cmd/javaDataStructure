package Search;

import java.util.Scanner;
/*
* 插值查找使用前提：数组有序
* */
public class InterpolationSearchDemo {
    public static void main(String[] args) {
        int[] array=new int[30];
        for (int i = 0; i < array.length; i++) {
            array[i]=2*(i+1);
        }
        Scanner scanner=new Scanner(System.in);
        while (true){
            int value=scanner.nextInt();
            System.out.println(InterpolationSearch(array, value));
        }
    }
    public static int InterpolationSearch(int[] array,int value){
        int left=0;
        int right=array.length-1;
        int mid;
        while (value>=array[left]&&value<=array[right]&&left<=right){
            mid=left+(value-array[left])*(right-left)/(array[right]-array[left]);
            if (array[mid]==value){
                return mid;
            }else if (array[mid]< value){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        return -1;
    }
}
