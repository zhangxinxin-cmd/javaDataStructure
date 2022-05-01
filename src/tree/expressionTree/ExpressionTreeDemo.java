package tree.expressionTree;

import java.util.LinkedList;
import java.util.Stack;

/*
将算数表达式以二叉树的形式存储
思路:准备两个栈，一个数字栈，一个符号栈
遍历算数表达式字符串:
如果是'(',则continue;
如果是数字,则入数字栈
如果是符号,若栈顶元素为空或者该符号是(或者栈顶元素是'('或者该符号优先级大于栈顶符号优先级，则入栈，
否则
进入while(符号栈栈顶元素优先级>=待插入元素优先级){
数字栈弹出两个元素a,b，符号栈弹出一个元素e,设置e的左节点b,右节点a,将e入数字栈
判断符号栈是否为空，空则break;
判断符号栈栈顶元素是否为'(',是则弹出栈顶元素,然后break;
}
* */
public class ExpressionTreeDemo {
    public static void main(String[] args) throws Exception {
        ExpressionTree expressionTree = new ExpressionTree("(2*3-5)*2-4*3");
        Node node = expressionTree.createExpressionBinaryTree();
//        expressionTree.floorOrder(node);
        System.out.println(expressionTree.getSuffix(node, new StringBuilder()));

    }
}

class ExpressionTree {
    private String expression;
    private Node node;
    private Node root;

    public String getExpression() {
        return expression;
    }

    public ExpressionTree(String expression) {
        this.expression = expression;
    }

    public Node createExpressionBinaryTree() throws Exception {
        Stack<Node> numStack = new Stack<>();
        Stack<Node> operationStack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char element = expression.charAt(i);
            if (element >= '0' && element <= '9') {
                numStack.push(new Node(element));
            } else {//element是符号
                if (operationStack.empty() || element == '(' || operationStack.peek().element == '(' || getPriority(element) > getPriority(operationStack.peek().element)) {
                    operationStack.push(new Node(element));
                } else {
                    while (getPriority(element) <= getPriority(operationStack.peek().element)) {
                        Node num1 = numStack.pop();
                        Node num2 = numStack.pop();
                        Node operation = operationStack.pop();
                        operation.left = num2;
                        operation.right = num1;
                        numStack.push(operation);
                        if (operationStack.empty()) break;
                        if (operationStack.peek().element == '(') {
                            operationStack.pop();
                            break;
                        }
                    }
                    if (element != ')') {
                        operationStack.push(new Node(element));
                    }
                }
            }
        }
        while (!operationStack.empty()) {
            Node newNode = new Node(operationStack.pop().element);
            Node num1 = numStack.pop();
            Node num2 = numStack.pop();
            newNode.left = num2;
            newNode.right = num1;
            numStack.push(newNode);
        }
        return numStack.pop();
    }

    public String getSuffix(Node node, StringBuilder stringBuilder) {
        if (node == null) {
            return null;
        }
        getSuffix(node.left, stringBuilder);
        getSuffix(node.right, stringBuilder);
        stringBuilder.append(node.element).append(" ");
        return stringBuilder.toString();
    }

    private int getPriority(char ch) throws Exception {
        switch (ch) {
            case '*':
            case '/':
                return 1;
            case ')':
                return -1;
            case '+':
            case '-':
                return 0;
            default:
                throw new Exception("无法解析的符号");
        }
    }

    private int calculate(int num1, int num2, char operation) {
        switch (operation) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            default:
                try {
                    throw new Exception("无法解析的运算符号");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return Integer.MAX_VALUE;
        }
    }

    public void postOrder(Node root) {
        root.postOrder();
    }

    public void floorOrder(Node root) {
        root.floorOrder();
    }
}

class Node {
    public char element;
    public Node left;
    public Node right;

    public Node(char element) {
        this.element = element;
        this.left = null;
        this.right = null;
    }

    public void floorOrder() {
        LinkedList<Node> list = new LinkedList<>();
        list.add(this);
        while (!list.isEmpty()) {
            Node remove = list.remove();
            System.out.println(remove);
            if (remove.left != null) {
                list.add(remove.left);
            }
            if (remove.right != null) {
                list.add(remove.right);
            }
        }
    }

    public void postOrder() {
        if (left != null) {
            left.postOrder();
        }
        if (right != null) {
            right.postOrder();
        }
        System.out.print(this.element + " ");
    }

    @Override
    public String toString() {
        return "Node{" +
                "element=" + element +
                '}';
    }
}