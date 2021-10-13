package Stack_;

public class Calculator {
    public static void main(String[] args) {
//    根据前面老师的思路完成表达式的运算
        String expression="35*76";
        ArrayStack2 numStack=new ArrayStack2(10);
        ArrayStack2 operStack=new ArrayStack2(10);
        int num1=0;
        int num2=0;
        int oper=0;
        int result=0;
        char ch='a';
        for (int index = 0; index < expression.length(); index++) {
            char charAt = expression.charAt(index);
            //判断非符号
            if(!operStack.isOper(charAt)){
                int i=index;
                while (i<(expression.length()-1)&&expression.charAt(i+1)>48&&expression.charAt(i+1)<=57){
                    i++;
                }
                numStack.push(Integer.parseInt(expression.substring(index,i+1)));
                index=i;
            }
            //判断第一个数为负数
            //这一步有代码冗余
            else if (charAt=='-'&&index==0){
                int i=index;
                while (i<(expression.length()-1)&&expression.charAt(i+1)>48&&expression.charAt(i+1)<=57){
                    i++;
                }
                numStack.push(Integer.parseInt(expression.substring(index,i+1)));
                index=i;
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
        System.out.println(numStack.pop());
    }
}
class ArrayStack2{
    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈，数据就放在该数组
    private int top=-1;//表示栈顶,初始化为-1
    public ArrayStack2(int maxSize){
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
        if (oper=='+'||oper=='-'){
            return 0;
        }else if (oper=='*'||oper=='/'){
            return 1;
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

