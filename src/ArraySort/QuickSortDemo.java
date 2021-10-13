package ArraySort;

public class QuickSortDemo {
    static int max=80000;
    public static void main(String[] args) {
        int[] array=new int[max];
        for (int i = 0; i < array.length; i++) {
            array[i]=(int)(Math.random()*max);
        }
        long sortBeforeTime = System.currentTimeMillis();
        System.out.println("排序前时间："+sortBeforeTime);
        quickSort(array,0,array.length-1);
        long sortAfterTime = System.currentTimeMillis();
        System.out.println("排序后时间："+sortAfterTime);
        System.out.println("花费时间："+(double)(sortAfterTime-sortBeforeTime)/1000+"秒");
    }
    public static void quickSort(int[] array,int left,int right){
        if (left<right){
            int pivotIndex = partition(array, left, right);
            quickSort(array,left,pivotIndex-1);
            quickSort(array,pivotIndex+1,right);
        }
    }
    public static int partition(int[] array, int startIndex, int endIndex) {
        int pivot = array[startIndex];
        int index = startIndex;
        int left = startIndex;
        int right = endIndex;
        while (left <= right) {
            while (left <= right) {
                if (array[right] < pivot) {
                    array[left] = array[right];
                    index = right;
                    left++;
                    break;
                }
                right--;
            }
            while (left <= right) {
                if (array[left] > pivot) {
                    array[right] = array[left];
                    index = left;
                    right--;
                    break;
                }
                left++;
            }

        }
        array[index] = pivot;
        return index;
    }
}
