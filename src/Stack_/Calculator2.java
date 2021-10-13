package Stack_;

public class Calculator2 {
    public static void main(String[] args) {
        String answer = calc("2+3*(7-2)+3");
        System.out.println(answer);
    }
    public static String calc(String expression){
    int answer=0;
    if (!expression.contains("(")){
        return calc2(expression);
    }else{
        int first = expression.indexOf('(');
        int last = expression.lastIndexOf(')');
        String[] strings = delete(expression, first, last);
        expression=strings[0]+calc(strings[1]);
        return calc2(expression);
    }
    }
    public static String[] delete(String string ,int first,int last){
        StringBuilder str= new StringBuilder();
        StringBuilder str2=new StringBuilder();
        for (int i=first;i<last-1;i++){
            str2.append(string.charAt(i+1));
        }
        for (int i = 0; i < string.length(); i++) {
            if (i<first){
                str.append(string.charAt(i));
            }
            else if (i==first&&(last+1)< string.length()) {
                i=last+1;
                str.append(string.charAt(i));
            }
        }
        return new String[]{str.toString(),str2.toString()};
    }
    public static String calc2(String expression){
        ArrayStack3 numStack=new ArrayStack3(10);
        ArrayStack3 operStack=new ArrayStack3(10);
        int num1=0;
        int num2=0;
        int oper=0;
        int result=0;
        char ch='a';
        for (int index = 0; index < expression.length(); index++) {
            char charAt = expression.charAt(index);
            if(!operStack.isOper(charAt))numStack.push(Integer.parseInt(String.valueOf(charAt)));
                //判断第一个数为负数
            else if (charAt=='-'&&index==0){
                int value;
                value=Integer.parseInt(expression.substring(0,2));
                numStack.push(value);
                index++;
            }
            else if (operStack.isEmpty()||operStack.priority(charAt)>operStack.priority(operStack.pick()))
                operStack.push(charAt);
            else{
                num1=numStack.pop();
                num2=numStack.pop();
                oper=operStack.pop();
                result = numStack.cal(num1, num2, oper);
                numStack.push(result);
                operStack.push(charAt);
            }

        }
        while (!operStack.isEmpty()){
            num1=numStack.pop();
            num2=numStack.pop();
            oper=operStack.pop();
            result = numStack.cal(num1, num2, oper);
            numStack.push(result);
        }
        return String.valueOf(result);
    }
}
class ArrayStack3{
    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈，数据就放在该数组
    private int top=-1;//表示栈顶,初始化为-1
    public ArrayStack3(int maxSize){
        this.maxSize=maxSize;
        stack=new int[this.maxSize];
    }
    //判断栈满否
    public boolean isFull(){
        return top==maxSize-1;
    }
    //判断栈空否
    public boolean isEmpty(){
        return top==-1;
    }
    //入栈
    public void push(int value){
        if (isFull()){
            System.out.println("栈已满");
            return;
        }
        top++;
        stack[top]=value;
    }
    //出栈
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈已空");
        }
        int value=stack[top];
        top--;
        return value;
    }
    //遍历栈
    public void  show(){
        if (isEmpty()){
            System.out.println("栈已空");
            return;
        }
        for (int j=top;j>-1;j--){
            System.out.println("stack["+j+"]"+"="+stack[j]);
        }
    }
    //     返回运算符的优先级，优先级是程序员来确定，优先级使用数字来表示
//    数字越大，优先级越高
    public int priority(int oper){
        if (oper=='+'||oper=='-'||oper=='('){
            return 0;
        }else if (oper=='*'||oper=='/'){
            return 1;
        }
        else if (oper==')'){
            return 2;
        }
        else{
            return -1;
        }
    }
    public boolean isOper(char val){
        return val=='+'||val=='-'||val=='*'||val=='/';
    }
    public int cal(int num1,int num2,int oper){
        int result=0;
        switch (oper){
            case '+':
                result=num1+num2;
                break;
            case '-':
                result=num2-num1;
                break;
            case '*':
                result=num2*num1;
                break;
            case '/':
                result=num2/num1;
                break;
            default:
                break;
        }
        return result;
    }
    //增加一个方法，返回当前栈顶元素，但不出栈
    public int pick(){
        return stack[top];
    }
}
