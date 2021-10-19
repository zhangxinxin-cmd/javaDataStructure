package tree.huffman;

import java.util.*;

public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] array = {13, 7, 8, 3, 29, 6, 1};
        HuffmanTree huffmanTree = new HuffmanTree(array);
        huffmanTree.traverTree();
    }
}

class HuffmanTree {
    private Node root = null;

    public HuffmanTree() {
    }

    public HuffmanTree(int[] array) {
        this.root = createHuffmanTree(array);
    }

    private Node createHuffmanTree(int[] array) {
        //第一步为了操作方便
        //1.遍历array数组
        //2.将arr的每一个元素构成一个node
        List<Node> nodes = new ArrayList<>(array.length);
        for (int value : array) {
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node left = nodes.remove(0);
            Node right = nodes.remove(0);
            Node newNode = new Node(left.value + right.value);
            newNode.left = left;
            newNode.right = right;
            nodes.add(newNode);
        }
        return nodes.get(0);
    }

    public void traverTree() {
        if (root == null) {
            System.out.println("huffmanTree为空");
            return;
        }
        root.traverTree();
    }
}

class Node implements Comparable<Node> {
    int value;
    Node left;
    Node right;

    public Node() {
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public void traverTree() {
        if (this.left == null && this.right == null) {
            System.out.println(this);
            return;
        }
        if (left != null) {
            left.traverTree();
        }
        if (right != null) {
            right.traverTree();
        }
    }
}
