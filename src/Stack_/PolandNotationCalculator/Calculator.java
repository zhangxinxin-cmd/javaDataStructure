package Stack_.PolandNotationCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Calculator {
    private String suffixExpression;
    public Calculator(String expression) {
        suffixExpression = toSuffix(expression);
    }
//逆波兰计算
    public int getResult() {
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
//有中缀表达式得到后缀表达式
    private String toSuffix(String expression) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] strings = expression.split(" ");
        Stack<String> numStack = new Stack<>();
        Stack<String> operStack = new Stack<>();
        for (int i = 0; i < strings.length; i++) {
            if (isOper(strings[i])) {
                if (operStack.empty() || operStack.peek().equals("(") || strings[i].equals("(") || priority(operStack.peek()) < priority(strings[i])) {
                    operStack.push(strings[i]);
                } else {

                    while (priority(operStack.peek()) >= priority(strings[i])) {
                        numStack.push(operStack.pop());
                        if (operStack.empty()) break;
                        if (operStack.peek().equals("(")) {
                            operStack.pop();
                            break;
                        }
                    }
                    if (!strings[i].equals(")")) operStack.push(strings[i]);
                }
            } else {
                numStack.push(strings[i]);
            }

        }
        while (!operStack.empty()) {
            numStack.push(operStack.pop());
        }
        Stack<String> SuffixStackClone = new Stack<>();
        //将numStack中的字符串反向push进SuffixStackClone。
        while (!numStack.empty()) {
            SuffixStackClone.push(numStack.pop());
        }
        while (!SuffixStackClone.empty()) {
            stringBuilder.append(SuffixStackClone.pop());
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    private boolean isOper(String val) {
        return val.equals("+") || val.equals("-") || val.equals("x") || val.equals("/") || val.equals("X") || val.equals("*") || val.equals("(") || val.equals(")");
    }

    private int priority(String oper) {
        switch (oper) {
            case ")":
                return -1;
            case "+":
            case "-":
                return 0;
            case "*":
            case "/":
                return 1;
            default:
                throw new RuntimeException();
        }
    }

    private ArrayList<String> getList(String suffixExpression) {
        String[] strings = suffixExpression.split(" ");
        return new ArrayList<>(Arrays.asList(strings));//将strings转换成ArrayList,并返回
    }

    private static int cal(int num1, int num2, int oper) {
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
