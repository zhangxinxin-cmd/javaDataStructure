package tree.binarySortTree;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import javax.print.attribute.standard.NumberUp;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        Node root = new Node(23);
        Node node2 = new Node(13);
        Node node3 = new Node(25);
        Node node4 = new Node(45);
        Node node5 = new Node(35);
        Node node6 = new Node(19);
        Node node7 = new Node(56);
        Node node8 = new Node(30);
        Node node9 = new Node(50);
        Node node10 = new Node(57);
        Node node11 = new Node(54);
        Node node12 = new Node(60);
        BinarySortTree bst = new BinarySortTree(root);
        bst.add(node2);
        bst.add(node3);
        bst.add(node4);
        bst.add(node5);
        bst.add(node6);
        bst.add(node7);
        bst.add(node8);
//        bst.add(node9);
        bst.add(node10);
//        bst.add(node11);
        bst.add(node12);
//        bst.inOrderTraver();
        bst.inOrderTraver();
        System.out.println("================");
        bst.delNode2(45);
        bst.inOrderTraver();
    }
}

class BinarySortTree {
    private Node root;

    public BinarySortTree(Node root) {
        this.root = root;
    }

    public BinarySortTree() {
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
            return;
        }
        root.add(node);
    }

    public void inOrderTraver() {
        if (root == null) {
            System.out.println("此二叉树为空");
            return;
        }
        root.InOrder();
    }

    public Node search(int value) {
        if (root == null) {
            System.out.println("此二叉树为空");
            return null;
        }
        return root.search(value);
    }

    public Node searchParent(int value) {
        if (root == null) {
            System.out.println("此二叉树为空");
            return null;
        }
        return root.searchParent(value);
    }

    public boolean delNode2(int value) {
        if (root == null) {
            System.out.println("此排序二叉树为空");
            return false;
        }
        if (root.value == value) {
            if (root.right != null) {
                int minVal = root.delTreeMinRight(root.right);
                if (root.right.left == null) {
                    root.right = root.right.right;
                }
                root.value = minVal;
                return true;
            }
            if (root.left != null) {
                int maxVal = root.delTreeMaxLeft(root.left);
                if (root.left.right == null) {
                    root.left = root.left.left;
                }
                root.value = maxVal;
                return true;
            }
            root = null;
            return true;
        }
        return root.delNode2(value);
    }

    //有bug，当删除头节点时，会出现空指针异常
    public boolean delNode(int value) {
        if (root == null) {
            return false;
        }
        Node targetNode = search(value);
        if (targetNode != null) {
            Node parentNode = searchParent(value);
            if (targetNode.left == null && targetNode.right == null) {
                //targetNode是叶子节点
                if (parentNode.left == targetNode) {
                    parentNode.left = null;
                } else {
                    parentNode.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                targetNode.value = delRightTreeMin(targetNode.right);
                if (targetNode.right.left == null) {
                    //targetNode.right就是要删除的结点
                    targetNode.right = null;
                }
            } else {
                //待删除结点只有一颗子树
                if (targetNode.left != null) {
                    //待删除结点左子树不为空
                    if (parentNode.left == targetNode) {
                        parentNode.left = targetNode.left;
                    } else {
                        parentNode.right = targetNode.left;
                    }
                } else {
                    //待删除结点右子树不为空
                    if (parentNode.left == targetNode) {
                        parentNode.left = targetNode.right;
                    } else {
                        parentNode.right = targetNode.right;
                    }
                }
            }
        }
        //没有找到要删除的结点
        return false;
    }

    //删除从树node中最小的结点，并返回其value值,如果node就是要删除的结点，则不删除(因为只有调用searchParent()方法才)，直接返回其value值
    public int delRightTreeMin(Node node) {
        if (node.left == null) {
            return node.value;
        }
        int value = delRightTreeMin(node.left);
        if (node.left.left == null) {//判断此时的node是否为待删除结点的头节点
            if (node.left.right == null) {
                node.left = null;
            } else {
                node.left = node.left.right;
            }
        }
        return value;
    }
}

class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    //添加结点的方法
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (this.value >= node.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void InOrder() {
        if (this.left != null) {
            this.left.InOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.InOrder();
        }
    }

    //查找要删除的结点
    public Node search(int value) {
        if (this.value == value) {
            return this;
        }
        if (this.value > value && this.left != null) {
            return this.left.search(value);
        }
        if (this.value < value && this.right != null) {
            return this.right.search(value);
        }
        return null;
    }

    //查找要删除的结点的父节点
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        }
        if (this.left != null && this.value > value) {
            return this.left.searchParent(value);
        }
        if (this.right != null && this.value < value) {
            return this.right.searchParent(value);
        }
        return null;
    }

    public boolean delNode2(int value) {
        if (this.value == value) {
            return true;
        }
        if (this.left != null && this.left.value > value) {
            boolean result = this.left.delNode2(value);
            if (this.left.value == value) {
                //this.left就是要删除的结点
                if (this.left.left == null && this.left.right == null) {
                    //待删除结点为叶子节点
                    this.left = null;
                } else if (this.left.left != null && this.left.right != null) {
                    //待删除结点是有左右子树的结点
                    int valMin = delTreeMinRight(this.left.right);
                    if (this.left.right.left == null) {
                        //this.left.right就是value值最小的结点
                        this.left.right = this.left.right.right;
                    }
                    this.left.value = valMin;
                } else {
                    //待删除结点只有一颗子树
                    if (this.left.left != null) {
                        //待删除结点左子树不为空
                        this.left = this.left.left;
                    } else {
                        //待删除结点右子树不为空
                        this.left = this.left.right;
                    }
                }
            }
            return result;
        } else if (this.right != null) {
            boolean result = this.right.delNode2(value);
            if (this.right.value == value) {
                //this.right就是要删除的结点
                if (this.right.left == null && this.right.right == null) {
                    //待删除结点为叶子节点
                    this.right = null;
                } else if (this.right.left != null && this.right.right != null) {
                    //待删除结点是有左右子树的结点
                    int valMin = delTreeMinRight(this.right.right);
                    if (this.right.right.left == null) {
                        //this.right.right就是value值最小的结点
                        this.right.right = this.right.right.right;
                    }
                    this.right.value = valMin;
                } else {
                    //待删除结点只有一颗子树
                    if (this.right.left != null) {
                        //待删除结点左子树不为空
                        this.right = this.right.left;
                    } else {
                        //待删除结点右子树不为空
                        this.right = this.right.right;
                    }
                }
            }
            return result;
        }
        return false;
    }

    //删除从树node中最小的结点，并返回其value值,如果node就是要删除的结点，则不删除(因为只有调用searchParent()方法才)，直接返回其value值
    public int delTreeMinRight(Node node) {
        if (node.left == null) {
            return node.value;
        }
        int value = delTreeMinRight(node.left);
        if (node.left.left == null) {//判断此时的node是否为待删除结点的头节点
            if (node.left.right == null) {
                node.left = null;
            } else {
                node.left = node.left.right;
            }
        }
        return value;
    }

    public int delTreeMaxLeft(Node node) {
        if (node.right == null) {
            return node.value;
        }
        int value = delTreeMaxLeft(node.right);
        if (node.right.right == null) {
            if (node.right.left == null) {
                node.right = null;
            } else {
                node.right = node.right.left;
            }
        }
        return value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

}