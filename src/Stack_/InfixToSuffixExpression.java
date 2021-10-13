package Stack_;

import java.util.Stack;

public class InfixToSuffixExpression {
    public static void main(String[] args) {
        String expression = "3 * ( 4 + 10 * 3 - 1 ) + 4 - 2";
        Stack<String> SuffixStack = GetSuffixExpression(expression);
        Stack<String> SuffixStackClone = new Stack<>();
        while (!SuffixStack.empty()) {
            SuffixStackClone.push(SuffixStack.pop());
        }
        while (!SuffixStackClone.empty()) {
            System.out.print(SuffixStackClone.pop() + " ");
        }
    }

    public static Stack<String> GetSuffixExpression(String expression) {
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
        return numStack;
    }

    public static boolean isOper(String val) {
        return val.equals("+") || val.equals("-") || val.equals("x") || val.equals("/") || val.equals("X") || val.equals("*") || val.equals("(") || val.equals(")");
    }

    public static int priority(String oper) {
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
}