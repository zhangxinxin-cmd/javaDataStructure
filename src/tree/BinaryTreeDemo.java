package tree;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "卢俊义");
        HeroNode node3 = new HeroNode(3, "吴用");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "扈三娘");
        HeroNode node6 = new HeroNode(6, "杨志");
        HeroNode node7 = new HeroNode(7, "鲁智深");
        //先手动创建二叉树，后面学习递归方式创建二叉树
        bt.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);
        bt.preOrder();
//        System.out.println(bt.preSearch(3));
//        System.out.println(bt.inOrderSearch(2));
//        System.out.println(bt.postOrderSearch(3));
//        bt.delNode(3);
//        bt.preOrder();
        System.out.println("================");
        System.out.println("非递归中序遍历：");
        bt.InCircleOrder();
        System.out.println("================");
        System.out.println("递归中序遍历");
        bt.InOrder();
        System.out.println("================");
        System.out.println("层次遍历");
        bt.floorOrder();
        System.out.println("================");
        System.out.println("节点数：");
        System.out.println(bt.nodeCount());
        System.out.println("===============");
        System.out.println("叶子节点数：");
        System.out.println(bt.leadCount());
    }
}

class BinaryTree {
    private HeroNode root;
    private HeroNode pre;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public HeroNode getRoot() {
        return root;
    }

    //先序遍历
    public void preOrder() {
        if (this.root != null) {
            root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void InOrder() {
        if (this.root != null) {
            root.inOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void PostOrder() {
        if (root != null) {
            root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //非递归中序遍历
    public void InCircleOrder() {
        if (root == null) {
            System.out.println("此二叉树为空");
            return;
        }
        root.InCircleOrder();
    }

    //层次遍历
    public void floorOrder() {
        if (root == null) {
            System.out.println("此二叉树为空");
            return;
        }
        root.floorOrder();
    }

    //先序查找
    public HeroNode preSearch(int num) {
        HeroNode res = root.inOrderSearch(num);
        if (res == null) {
            System.out.println("未找到");
        }
        return res;
    }

    //中序查找
    public HeroNode inOrderSearch(int num) {
        HeroNode res = root.inOrderSearch(num);
        if (res == null) {
            System.out.println(" 未找到");
        }
        return res;
    }

    //后序查找
    public HeroNode postOrderSearch(int num) {
        HeroNode res = root.postOrderSearch(num);
        if (res == null) {
            System.out.println("未找到");
        }
        return res;
    }

    public void delNode(int num) {
        if (root == null) {
            System.out.println("二叉树为空树");
        }
        if (root.getNum() == num) {
            root = null;
            System.out.println("删除成功");
            return;
        }
        boolean res = root.delNode(num);
        if (res) {
            System.out.println("删除成功");
            return;
        }
        System.out.println("未找到该结点");
    }

    //统计结点数
    public int nodeCount() {
        if (root == null) {
            return 0;
        }
        return root.nodeCount();
    }

    //统计叶子节点数
    public int leadCount() {
        if (root == null) {
            return 0;
        }
        return root.LeadCount();
    }
}

//先创建HerNode
class HeroNode {
    private int num;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int num, String name) {
        this.num = num;
        this.name = name;
        this.left = null;
        this.right = null;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }

    //先序遍历
    public void preOrder() {
        System.out.println(this);
        if (left != null) {
            left.preOrder();
        }
        if (right != null) {
            right.preOrder();
        }
    }

    //中序遍历
    public void inOrder() {
        if (left != null) {
            left.inOrder();
        }
        System.out.println(this);
        if (right != null) {
            right.inOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (left != null) {
            left.postOrder();
        }
        if (right != null) {
            right.postOrder();
        }
        System.out.println(this);
    }

    //非递归中序遍历
    public void InCircleOrder() {
        Stack<HeroNode> stack = new Stack<>();
        HeroNode node = this;
        do {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            System.out.println(node);
            node = node.right;
        } while (!stack.empty() || node != null);

    }

    //层次遍历(就是从根节点一层一层地遍历此二叉树)
    public void floorOrder() {
        LinkedList<HeroNode> list = new LinkedList<>();
        list.add(this);
        while (!list.isEmpty()) {
            HeroNode remove = list.remove();
            System.out.println(remove);
            if (remove.left != null) {
                list.add(remove.left);
            }
            if (remove.right != null) {
                list.add(remove.right);
            }
        }
    }

    //先序查找
    public HeroNode preOrderSearch(int num) {
        if (this.num == num) {
            return this;
        }
        HeroNode res = null;
        if (this.left != null) {
            res = this.left.preOrderSearch(num);
        }
        if (res != null) {
            return res;
        }
        if (this.right != null) {
            res = this.right.preOrderSearch(num);
        }
        return res;
    }

    //中序查找
    public HeroNode inOrderSearch(int num) {
        HeroNode res = null;
        if (this.left != null) {
            res = this.left.inOrderSearch(num);
        }
        if (res != null) {
            return res;
        }
        if (this.num == num) {
            return this;
        }
        if (this.right != null) {
            res = this.right.inOrderSearch(num);
        }
        return res;
    }

    //后序查找
    public HeroNode postOrderSearch(int num) {
        HeroNode res = null;
        if (this.left != null) {
            res = this.left.inOrderSearch(num);
        }
        if (res != null) {
            return res;
        }
        if (this.right != null) {
            res = this.right.inOrderSearch(num);
        }
        if (res != null) {
            return res;
        }
        if (this.num == num) {
            return this;
        }
        return null;
    }

    //删除结点
    public boolean delNode(int num) {
        boolean res = false;
        if (this.left != null && this.left.num == num) {
            this.left = null;
            return true;
        }
        if (this.left != null) {
            res = this.left.delNode(num);
            if (res) {
                return true;
            }
        }
        if (this.right != null && this.right.num == num) {
            this.right = null;
            return true;
        }
        if (this.right != null) {
            res = this.right.delNode(num);
            return res;
        }
        return false;
    }

    //统计结点数
    public int nodeCount() {
        if (this.left == null && this.right == null) {
            return 1;
        }
        int count = 1;
        if (this.left != null) {
            count += this.left.nodeCount();
        }
        if (this.right != null) {
            count += this.right.nodeCount();
        }
        return count;
    }

    //统计叶子节点数
    public int LeadCount() {
        if (this.left == null && this.right == null) {
            return 1;
        }
        int count = 0;
        if (this.left != null) {
            count += this.left.LeadCount();
        }
        if (this.right != null) {
            count += this.right.LeadCount();
        }
        return count;
    }
}
