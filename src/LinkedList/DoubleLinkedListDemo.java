package LinkedList;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
//  测试
        System.out.println("双向链表的测试");
//  先创建节点
        HeroNode2 hero1=new HeroNode2(1,"宋江","及时雨");
        HeroNode2 hero2=new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero3=new HeroNode2(3,"林冲","豹子头");
        HeroNode2 newHero=new HeroNode2(1,"卢俊义","玉麒麟") ;
        DoubleLinkedList doubleLinkedList=new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.list();
        doubleLinkedList.del(3);
        System.out.println();
        doubleLinkedList.list();
        doubleLinkedList.update(newHero);
        System.out.println();
        doubleLinkedList.list();
    }
}
class HeroNode2{
    public int no;
    public  String name;
    public String nickName;
    public HeroNode2 next;//指向下一个节点
    public HeroNode2 pre;//指向上一个节点
    public HeroNode2(int no,String name,String nickName){
        this.no=no;
        this.name=name;
        this.nickName=nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName +
                "'}";
    }

}
class DoubleLinkedList{
    private HeroNode2 head=new HeroNode2(0,"","");

    public HeroNode2 getHead() {
        return head;
    }

//    添加节点
    public void add(HeroNode2 heroNode){
        HeroNode2 temp=head;
        while (temp.next!=null){
            temp=temp.next;
        }
        temp.next=heroNode;
        heroNode.pre=temp;
        heroNode.next=null;
    }
//    修改节点
public void update(HeroNode2 newHeroNode){
    if (head.next==null){
        System.out.println("链表为空");
        return;
    }
    HeroNode2 temp=head.next;
    while (temp.no!=newHeroNode.no&&temp.next!=null){
        temp=temp.next;
    }
    if (temp.no==newHeroNode.no){
        temp.name=newHeroNode.name;
        temp.nickName=newHeroNode.nickName;
    }else{
        System.out.println("要修改的节点不存在");
    }
}
//  删除节点
    public void del(int no){
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp=head.next;
        while (temp.no!=no&&temp.next!=null){
            temp=temp.next;
        }
        if (temp.no==no){
            temp.pre.next=temp.next;
            if (temp.next!=null)temp.next.pre=temp.pre;
        }else {
            System.out.println("要删除的节点存在");
        }
    }
    //遍历链表
    public void list(){
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp=head.next;
        while (temp!=null){
            System.out.println(temp);
            temp=temp.next;
        }
    }
}

