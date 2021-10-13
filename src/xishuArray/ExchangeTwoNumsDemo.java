package xishuArray;
/*由于java中基本数据类型采用值传递，所以用方法传递两个值来交换无效，可以采用传递数组来交换
* */
public class ExchangeTwoNumsDemo {
    public static void main(String[] args) {
        int num1=10;
        int num2=5;
        System.out.println("before,num1="+num1+",num2="+num2);
        exchange(num1,num2);
        System.out.println("after,num1="+num1+",num2="+num2);
        int[] array={num1,num2};
        exchange(array);
        System.out.println("after,num1="+array[0]+",num2="+array[1]);
    }
    public static void exchange(int num1,int num2){
        int temp=num1;
        num1=num2;
        num2=temp;
    }
    public static void exchange(int[] array){
        if (array.length!=2){
            System.out.println("数组长度大于2");
            return;
        }
        int temp=array[0];
        array[0]=array[1];
        array[1]=temp;
    }
}
