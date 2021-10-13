package queue;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.CancellationException;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入队列长度：");
        int maxSize = scanner.nextInt();
        ArrayQueue arrayQueue=new ArrayQueue(maxSize);
        boolean loop=true;
        while (loop){
            System.out.println("s(显示队列数据)");
            System.out.println("e(退出)");
            System.out.println("a(添加队列数据)");
            System.out.println("g(出队列)");
            System.out.println("h(显示头部数据)");
            char key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入：");
                    int data = scanner.nextInt();
                    arrayQueue.addQueue(data);
                    break;
                case 'g':
                    System.out.println(arrayQueue.getQueue());
                    break;
                case 'h':
                    System.out.println(arrayQueue.headQueue());
                    break;
                case 'e':
                    scanner.close();
                    loop=false;
                    break;
                default:
                    System.out.println("输入错误，情重新输入:");
            }
        }

    }
}

//使用数组模拟队列，编写一个ArrayQueue类
class ArrayQueue {
    private int maxSize;//表示数组最大容量
    private int front;//队列头
    private int real;//队列尾
    private int[] arr;//该数组用于存放数据，模拟队列

    //创建队列的构造器
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;//指向队列头部
        real = -1;//指向队列尾部
    }

    public boolean isFull() {
        return real == maxSize - 1;
    }

    public boolean isEmpty() {
        return real == front;
    }

    //入队列
    public void addQueue(int data) {
        if (isFull()) {
            System.out.println("队列不能加入数据");
            return;
        }
        real++;//real后移
        arr[real] = data;
    }

    public void addQueue(int... datas) {
        for (int data : datas) {
            if (isFull()) {
                System.out.println("队列不能加入数据");
                return;
            }
            real++;
            arr[real] = data;
        }
    }

    //出队列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        front++;
        return arr[front];
    }

    public void showQueue() {
        if (isEmpty()) System.out.println("队列已空");
        for (int i = front + 1; i <= real; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    //显示队列的头部数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        return arr[front + 1];
    }
}
