package test;

public class NumSum {
    public static void main(String[] args) {
        /*int sum = fun1(3, 6);
        System.out.println(sum);*/
        int[] array={1,3,2,2};
        System.out.println(fun2(array));
    }
    //求数列1,1,2,1,2,3,1,2,3,4...第first个之后length长度的片段之和
    public static int fun1(int first,int length){
        int s=0;
        int[] array=new int[first+length-1];
        int row=0;
        int j=0;
        while (j<array.length){
            row++;
            j+=row;
        }
        int index=0;
        for (int i = 0; i < row; i++) {
            for (j=0;j<i+1;j++){
                if (index==array.length){
                    break;
                }
                array[index++]=j+1;
            }
        }
        for (int m=first-1;m<first+length-1;m++){
            s+=array[m];
        }
        return s;
    }
    public static int fun2(int[] array){
        int count=0;
        for (int i = 0; i < array.length; i++) {
            for (int j=i;j<array.length;j++){
                    if (judge(array,i,j)){
                        count++;
                    }
            }
        }
        return count;
    }
    public static boolean judge(int[] array,int first,int last){
        int sum=0;
        int div=1;
        for (int i=first;i<last+1;i++){
            sum+=array[i];
            div*=array[i];
        }
        return sum==div;
    }
}
