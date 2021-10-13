package queue;
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        /*CircleArrayQueue circleArrayQueue=new CircleArrayQueue(4);
        circleArrayQueue.addQueue(1);
        circleArrayQueue.addQueue(2);
        circleArrayQueue.addQueue(4);
        circleArrayQueue.addQueue(3);
        System.out.println(circleArrayQueue.getQueue());
        System.out.println("============");
        circleArrayQueue.addQueue(5);
        System.out.println(circleArrayQueue.getQueue());
        System.out.println(circleArrayQueue.getQueue());
        circleArrayQueue.addQueue(8);
        circleArrayQueue.addQueue(12);
        System.out.println(circleArrayQueue.getQueue());
        System.out.println(circleArrayQueue.getQueue());
        System.out.println(circleArrayQueue.getQueue());
        System.out.println(circleArrayQueue.getQueue());*/
        String name="abcdabdcad";
        System.out.println(name.codePointAt(0));
    }
}
class CircleArrayQueue{
    public int[] array;
    private int front;
    private int real;
    private int maxSize;
    public CircleArrayQueue(int maxSize){
        this.maxSize=maxSize+1;
        array=new int[this.maxSize];
        real=0;
        front=0;
    }
    public boolean isFull(){
        return real%maxSize==front&&real!=front;
    }
    private boolean isEmpty(){
        return real==front;
    }
    public void addQueue(int data){
        if (isFull()){
            System.out.println("队列已满");
            return;
        }
        real=real%maxSize;
        array[real]=data;
        real++;
    }
    public int getQueue(){
        if (isEmpty()) throw new RuntimeException("队列为空");
        front=front%maxSize;
        return array[front++];//front++表示先赋值后自加。
    }
}
