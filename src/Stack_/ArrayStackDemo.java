package Stack_;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试一下ArrayStack是否正确
        //先创建一个ArrayStack对象->表示栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show:表示显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:表示添加数据到栈");
            System.out.println("pop:表示从栈取出数据");
            System.out.println("请输入你的选择：");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.show();
                    break;
                case "exit":
                    loop = false;
                    scanner.close();
                    break;
                case "push":
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.println("出栈的数据是" + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("输入错误,请重新输入");
                    break;
            }
        }
        System.out.println("程序已退出");
    }
}

class ArrayStack {
    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈，数据就放在该数组
    private int top = -1;//表示栈顶,初始化为-1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //判断栈满否
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //判断栈空否
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈已满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈已空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈
    public void show() {
        if (isEmpty()) {
            System.out.println("栈已空");
            return;
        }
        for (int j = top; j > -1; j--) {
            System.out.println("stack[" + j + "]" + "=" + stack[j]);
        }
    }
}
