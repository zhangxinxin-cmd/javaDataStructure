package LinkedList;

public class CircleLinkedListDemo {
    public static void main(String[] args) {
        circleLinkedList circleLinkedList=new circleLinkedList();
        circleLinkedList.add(5);
        circleLinkedList.list();
        circleLinkedList.countBoy(1,2,5);
    }
}
class circleLinkedList{
    private Boy first=null;
    private Boy curBoy=null;
    //添加节点到链表中
    public void add(int nums){
        if (nums<1){
            System.out.println("nums违法");
            return;
        }
        for (int i = 0; i < nums; i++) {
            Boy boy=new Boy(i+1);
            if (i==0){
                first=boy;
                boy.setNext(first);
                curBoy=boy;
            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy=boy;
            }
        }
    }

    //遍历链表
    public void list(){
        if (first==null){
            System.out.println("链表为空");
            return;
        }
        Boy temp=first;
        do {
            System.out.println(temp);
            temp=temp.getNext();
        }while (temp!=first);
    }
    //根据用户的输入，计算出小孩出圈的顺序
    /**
     * @param startNo 表示第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少小孩在圈中
    * */
    public void countBoy(int startNo,int countNum,int nums){
        if (first==null||startNo<1||startNo>nums){
            System.out.println("输入数据非法");
            return;
        }
        //创建辅助指针，帮助完成小孩出圈
        Boy helper=first;
        while (helper.getNext()!=first){
            helper=helper.getNext();
        }
        for (int i = 0; i < startNo-1; i++) {
            helper=first;
            first=first.getNext();
        }
        while (first.getNext()!=first){
            for (int j = 0; j < countNum - 1; j++) {
                helper=first;
                first=first.getNext();
            }
            System.out.printf("小孩%d出圈\n",first.getNo());
            first=first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d\n",first.getNo());
    }
}
class Boy{
    private int no;
    private Boy next;
    public Boy(int no){
        this.no=no;
    }
    public int getNo(){
        return this.no;
    }
    public Boy getNext(){
        return this.next;
    }
    public void setNext(Boy next){
        this.next=next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
