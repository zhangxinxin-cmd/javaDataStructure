package LinkedList;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "林冲", "豹子头");
        HeroNode newHero = new HeroNode(1, "卢俊义", "玉麒麟");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
//        singleLinkedList.showList();
//        singleLinkedList.deleteList(2);
        System.out.println();
        /*singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.deleteList(3);*/
//        singleLinkedList.showList();
//        Turn(singleLinkedList.getHead());
        reversePrint(singleLinkedList.getHead());
    }


    //链表反转
    public static void Turn(HeroNode head) {
        HeroNode cur = head.next;
        HeroNode next;
        HeroNode reverseHead = null;
        while (cur != null) {
            next = cur.next;
            cur.next = reverseHead;
            reverseHead = cur;
            cur = next;
        }
        head.next = reverseHead;
    }

    public static HeroNode Turn2(HeroNode head) {
        if (head == null || head.next == null) return head;
        HeroNode newHead = Turn2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    //用递归方式从尾到头打印链表
    public static void PrintHeroNode(HeroNode head) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        } else if (head.next.next == null) {
            System.out.println(head.next);
            return;
        }
        PrintHeroNode(head.next);
        System.out.println(head.next);
    }

    //用栈的先进后出方式完成
    public static void reversePrint(HeroNode head) {
        if (head.next == null) return;
        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = head.next;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    //归并两个有序的链表，使得归并后仍有序
    public static HeroNode mergeTwoLists(HeroNode list1, HeroNode list2) {
        if (list1 == null) return list2;
        else if (list2 == null) return list1;
        if (list1.no < list2.no) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}

//定义HeroNode,每个HeroNode对象就是一个节点.
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;//指向下一个节点

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
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

class SingleLinkedList {
    //先初始化一个头节点，头结点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");
    private HeroNode tail = head;

    public HeroNode getHead() {
        return head;
    }

    //第一种方式添加英雄，不考虑顺序
    public void add(HeroNode heroNode) {
        tail.next = heroNode;
        tail = heroNode;
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置（如果有这个排名，则添加失败，并给出提示）
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) break;
            if (temp.next.no > heroNode.no) break;
            else if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("准备插入的英雄编号%d已经存在，不能加入\n", heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点的信息，根据no编号来修改，则no编号不能改。
    public void update(HeroNode newHeroNode) {
        HeroNode temp = head;
        while (temp.no != newHeroNode.no) {
            if (temp.next == null) {
                System.out.println("未找到");
                return;
            }
            temp = temp.next;
        }
        temp.name = newHeroNode.name;
        temp.nickName = newHeroNode.nickName;
    }

    //删除节点
    public void deleteList(int no) {
        HeroNode temp = head;
        while (temp.next.no != no) {
            if (temp.next.next == null) {
                System.out.println("编号对应的节点不存在");
                return;
            }
            temp = temp.next;
        }
        temp.next = temp.next.next;
    }

    //显示链表节点数量（不包括头结点)
    public int getLength() {
        if (head.next == null) return 0;
        int length = 0;
        HeroNode cur = head;
        while (cur.next != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    //查找链表的倒数第n个节点
    public HeroNode getLastHeroNode(int lastN) {
        HeroNode temp = head;
        if (lastN > getLength()) throw new RuntimeException("n超出链表长度");
        for (int i = 0; i < getLength() - lastN + 1; i++) {
            temp = temp.next;
        }
        return temp;
    }

    //显示链表(遍历)
    public void showList() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}
