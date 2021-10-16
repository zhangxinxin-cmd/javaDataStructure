package tree;

import com.sun.org.apache.xerces.internal.impl.dv.dtd.NMTOKENDatatypeValidator;

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
        bt.delNode(3);
        bt.preOrder();
    }
}

class BinaryTree {
    private HeroNode root;

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
            System.out.println("未找到");
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
}
