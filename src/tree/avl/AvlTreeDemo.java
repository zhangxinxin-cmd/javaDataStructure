package tree.avl;


public class AvlTreeDemo {
    public static void main(String[] args) {
        AvlTree avlTree = new AvlTree();
        avlTree.add(new Node(3));
//        int[] array = {4, 3, 6, 5, 7, 8,1,6,24};
//        int[] array = {10, 12, 8, 9, 7, 6};
        int[] array = {10, 11, 7, 6, 8, 9};
        for (int i = 0; i < array.length; i++) {
            avlTree.add(new Node(array[i]));
        }
        System.out.println("平衡后：");
        avlTree.inOrderTraver();
        System.out.println("树高:" + avlTree.height());
        System.out.println("左子树高度:" + avlTree.getRoot().leftHeight());
        System.out.println("右子树高度:" + avlTree.getRoot().rightHeight());
        String string = "张";
        System.out.println(string.getBytes()[0]);
        System.out.println(string.getBytes()[1]);
        System.out.println((int) string.charAt(0));
    }
}

class AvlTree {
    private Node root;

    public AvlTree() {
    }

    public Node getRoot() {
        return root;
    }

    public AvlTree(Node root) {
        this.root = root;
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
            return;
        }
        root.add(node);
    }

    public int height() {
        if (root == null) {
            System.out.println("此二叉树为空");
            return -1;
        }
        return root.height();
    }

    public void inOrderTraver() {
        if (root == null) {
            System.out.println("此二叉树为空");
        }
        root.InOrder();
    }
}