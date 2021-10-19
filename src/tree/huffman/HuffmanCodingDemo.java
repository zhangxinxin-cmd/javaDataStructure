package tree.huffman;

import java.util.*;

public class HuffmanCodingDemo {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] bytes = content.getBytes();
        List<HuffNode> nodes = getNodes(bytes);
        Collections.sort(nodes);
        System.out.println(nodes);
        HuffNode root = createHuffmanTree(nodes);
        traverTree(root);
    }

    /**
     * function:根据字节数组返回HuffNode集合
     */
    private static List<HuffNode> getNodes(byte[] bytes) {
        List<HuffNode> nodes = new ArrayList<>();
        Map<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            if (map.containsKey(b)) {
                Integer counts = map.get(b);
                map.put(b, counts + 1);
            } else {
                map.put(b, 1);
            }
        }
        Set<Map.Entry<Byte, Integer>> entries = map.entrySet();
        for (Map.Entry<Byte, Integer> entry : entries) {
            nodes.add(new HuffNode(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    private static HuffNode createHuffmanTree(List<HuffNode> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            HuffNode left = nodes.remove(0);
            HuffNode right = nodes.remove(0);
            HuffNode parent = new HuffNode((byte) 0, left.weight + right.weight);
            parent.left = left;
            parent.right = right;
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    private static void traverTree(HuffNode root) {
        if (root == null) {
            System.out.println("空树");
            return;
        }
        root.preOrder();
    }
}

class HuffNode implements Comparable<HuffNode> {
    byte data;
    int weight;
    HuffNode left;
    HuffNode right;

    public HuffNode(byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(HuffNode o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "HuffNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}