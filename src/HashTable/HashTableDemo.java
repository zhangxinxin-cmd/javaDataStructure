package HashTable;

public class HashTableDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(10);
        Employ e1 = new Employ(1, "李然");
        Employ e2 = new Employ(3, "李想");
        Employ e3 = new Employ(5, "李敢");
        Employ e4 = new Employ(10, "李让");
        Employ e5 = new Employ(13, "张想");
        Employ e6 = new Employ(24, "张梅");
        Employ e7 = new Employ(1, "刘翔");
        hashTab.add(e1);
        hashTab.add(e2);
        hashTab.add(e3);
        hashTab.add(e4);
        hashTab.add(e5);
        hashTab.add(e6);
        hashTab.add(e7);
//        hashTab.HashTabTraver();
        Employ employ = hashTab.HashSearchEm(3);
        if (employ != null) {
            System.out.println(employ);
        }
    }
}

class HashTab {
    private EmployLinkedList[] employLinkedLists;
    private int size;

    public HashTab(int size) {
        if (size == 0) {
            try {
                throw new Exception("数组大小为0");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.size = size;
        employLinkedLists = new EmployLinkedList[this.size];
        for (int i = 0; i < employLinkedLists.length; i++) {
            employLinkedLists[i] = new EmployLinkedList();
        }
    }

    public void add(Employ em) {
        int index = HashFun(em.id);
        employLinkedLists[index].add(em);
    }

    private int HashFun(int id) {
        return id % size;
    }

    //遍历hash表
    public void HashTabTraver() {
        for (int i = 0; i < size; i++) {
            if (!employLinkedLists[i].IsEmpty()) {
                System.out.print("第" + (i + 1) + "条链表：");
                employLinkedLists[i].listTraver();
                System.out.println();
            }
        }
    }

    public Employ HashSearchEm(int id) {
        Employ result;
        result = employLinkedLists[HashFun(id)].searchEm(id);
        if (result != null) {
            System.out.println("在第" + (HashFun(id) + 1) + "条链表找到");
            return result;
        } else {
            System.out.println("在该条链表中未找到");
            return null;
        }
    }
}

//雇员类
class Employ {
    public int id;
    public String name;
    public Employ next;

    public Employ(int id, String name) {
        this.name = name;
        this.id = id;
        this.next = null;
    }

    @Override
    public String toString() {
        return "Employ{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

//该链表的头节点储存员工id，更适合称为首元节点
class EmployLinkedList {
    private Employ head;

    //添加雇员到链表
    public void add(Employ emp) {
        if (head == null) {//emp是首个结点
            head = emp;
            return;
        }
        Employ temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = emp;
    }

    //判断链表是否为空
    public boolean IsEmpty() {
        return head == null;
    }

    //遍历链表
    public void listTraver() {
        if (IsEmpty()) {
            System.out.println("链表为空");
            return;
        }
        Employ temp = head;
        while (temp != null) {
            System.out.print(temp);
            if (temp.next != null) {
                System.out.print(",");
            }
            temp = temp.next;
        }
    }

    public Employ searchEm(int id) {
        Employ temp = head;
        while (temp != null && temp.id != id) {
            temp = temp.next;
        }
        return temp;
    }
}
