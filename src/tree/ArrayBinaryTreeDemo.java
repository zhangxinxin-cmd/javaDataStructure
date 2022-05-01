package tree;

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(array);
//        arrayBinaryTree.preOrder(0);
//        arrayBinaryTree.InOrder(0);
        arrayBinaryTree.postOrder(0);
    }
}

class ArrayBinaryTree {
    private int[] array;//存储数据结点的数组

    public ArrayBinaryTree(int[] array) {
        this.array = array;
    }

    //编写一个方法，完成顺序存储二叉树的前序遍历
    public void preOrder(int index) {
        if (index < array.length) {
            System.out.println(array[index]);
            preOrder(2 * index + 1);
            preOrder(2 * index + 2);
        }
    }

    //中序遍历
    public void InOrder(int index) {
        if (index < array.length) {
            InOrder(2 * index + 1);
            System.out.println(array[index]);
            InOrder(2 * index + 2);
        }
    }

    //后序遍历
    public void postOrder(int index) {
        if (index < array.length) {
            postOrder(2 * index + 1);
            postOrder(2 * index + 2);
            System.out.println(array[index]);
        }
    }
}
