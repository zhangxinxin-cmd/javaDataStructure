package tree;

import javax.jnlp.IntegrationService;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

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
        System.out.println("从根节点到叶子节点路径：");
        System.out.println(bt.waySum());
        System.out.println("左叶子节点数量：");
        System.out.println(bt.sumOfLeftLeaves());
        System.out.println("左叶子结点值之和：");
        System.out.println(bt.sumOfLeftLeavesValues());
        System.out.println(bt.exitWayOfSum(11));
        System.out.println("翻转二叉树前：");
        bt.floorOrder();
        System.out.println("翻转二叉树后：");
        bt.exchange();
        bt.floorOrder();
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

    //返回从根节点到叶子节点的所有结点
    public List<LinkedList<Integer>> waySum() {
        if (root == null) {
            return null;
        }
        LinkedList<Integer> list = new LinkedList<>();
        LinkedList<LinkedList<Integer>> lists = new LinkedList<>();
        root.traversal(lists, list);
        return lists;
    }

    //返回左叶子节点数
    public int sumOfLeftLeaves() {
        if (root == null) {
            return 0;
        }
        return root.sumOfLeftLeaves();
    }

    public int sumOfLeftLeavesValues() {
        if (root == null) {
            return 0;
        }
        return root.sumOfLeftLeavesValues();
    }

    //给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和
    public boolean exitWayOfSum(int target) {
        if (root == null) {
            return false;
        }
        return root.exitWayOfSum(target);
    }

    //翻转二叉树
    public void exchange() {
        if (root == null) {
            try {
                throw new Exception("二叉树为空树");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        root.exchange();
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

    //判断一个二叉树是否对称，用递归实现
    public boolean compare(HeroNode left, HeroNode right) {
        if ((left == null && right == null)) {
            return true;
        }
        if ((left != null && right != null) && (left.num == right.num)) {
            return compare(left.left, right.right) && compare(left.right, right.left);
        }
        return false;
    }

    //判断一个二叉树是否对称，用迭代实现
    public boolean compare2(HeroNode root) {
        LinkedList<HeroNode> list = new LinkedList<>();
        list.add(root.left);
        list.add(root.right);
        while (!list.isEmpty()) {
            HeroNode leftNode = list.remove();
            HeroNode rightNode = list.remove();
            if (leftNode == null && rightNode == null) {
                continue;
            }
            if ((leftNode != null && rightNode != null) && (leftNode.num == rightNode.num)) {
                list.add(leftNode.left);
                list.add(rightNode.right);
                list.add(leftNode.right);
                list.add(rightNode.left);
            } else {
                return false;
            }
        }
        return true;
    }

    //找到从根节点到叶子节点的所有路径
    public void traversal(LinkedList<LinkedList<Integer>> lists, LinkedList<Integer> list) {
        list.add(this.num);
        if (this.left == null && this.right == null) {
            lists.add(new LinkedList<>(list));
        }
        if (this.left != null) {
            left.traversal(lists, list);
            list.removeLast();
        }
        if (this.right != null) {
            right.traversal(lists, list);
            list.removeLast();
        }
    }

    //求左叶子节点数
    public int sumOfLeftLeaves() {
        if (this.left == null && this.right == null) {
            return 0;
        }
        int sum = 0;
        if (this.left != null && this.left.left == null && this.left.right == null) {
            sum += 1;
        }
        if (this.left != null) {
            sum += left.sumOfLeftLeaves();
        }
        if (this.right != null) {
            sum += right.sumOfLeftLeaves();
        }
        return sum;
    }

    //求左叶子之和
    public int sumOfLeftLeavesValues() {
        if (this.left == null && this.right == null) {
            return 0;
        }
        int sum = 0;
        if (this.left != null && this.left.left == null && this.left.right == null) {
            sum += this.left.getNum();
        }
        if (this.left != null) {
            sum += left.sumOfLeftLeavesValues();
        }
        if (this.right != null) {
            sum += right.sumOfLeftLeavesValues();
        }
        return sum;
    }

    //给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和
    public boolean exitWayOfSum(int target, int sum) {
        if (this.left == null && this.right == null && (sum + this.num) == target) {
            return true;
        }
        sum += this.num;
        if (left != null && left.exitWayOfSum(target, sum)) {//如果为真，说明已找到这条路径，返回true，所以跟着返回真
            return true;
        } else if (right != null && right.exitWayOfSum(target, sum)) {//说明上一条策略行不通，执行
            return true;
        }
        return false;
    }

    public boolean exitWayOfSum(int target) {
        target -= this.num;
        if (this.left == null && this.right == null && target == 0) {
            return true;
        }
        if (left != null && left.exitWayOfSum(target)) {
            return true;
        }
        return right != null && right.exitWayOfSum(target);
    }

    public void exchange() {
        if (this.left == null && this.right == null) {
            return;
        }
        if (this.left != null) {
            left.exchange();
        }
        if (this.right != null) {
            right.exchange();
        }
        HeroNode temp;
        temp = left;
        left = right;
        right = temp;

    }
}
