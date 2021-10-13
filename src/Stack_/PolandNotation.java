package Stack_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //先定义逆波兰表达式
        //(3+4)x5-6 =>3 4 + 5 x 6 -
        //4 * 5 - 8 + 60 + 8 / 2 => 4 5 * 8 - 60 + 8 2 / +
        //说明为了方便，逆波兰表达式和符号使用空格隔开
        String suffixExpression = "3 4 10 3 * + 1 - * 4 + 2 - ";
        //思路
        //1.先将"3 4 + 5 x 6 -" =>放到ArrayList中
        //2.将ArrayList 传递一个方法，遍历ArrayList配合栈 完成计算
        System.out.println(calculate(suffixExpression));
    }

    public static int calculate(String suffixExpression) {
        ArrayList<String> list = getList(suffixExpression);
        Stack<Integer> numStack = new Stack<>();
        for (int i = 0; i < list.size(); i++) {
            if (!isOper(list.get(i))) {
                numStack.push(Integer.parseInt(list.get(i)));
            } else {
                int num1 = 0;
                int num2 = 0;
                try {
                    num1 = numStack.pop();
                    num2 = numStack.pop();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                int result = cal(num1, num2, list.get(i).charAt(0));
                numStack.push(result);
            }
        }
        return numStack.pop();
    }

    //将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
    public static ArrayList<String> getList(String suffixExpression) {
        String[] strings = suffixExpression.split(" ");
        return new ArrayList<>(Arrays.asList(strings));//将strings转换成ArrayList,并返回
    }

    public static boolean isOper(String val) {
        return val.equals("+") || val.equals("-") || val.equals("x") || val.equals("/") || val.equals("X") || val.equals("*");
    }

    public static int cal(int num1, int num2, int oper) {
        int result = 0;
        switch (oper) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;
                break;
            case 'x':
            case '*':
            case 'X':
                result = num2 * num1;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                throw new RuntimeException("运算符有误");
        }
        return result;
    }
}