package tree.huffman;

import java.util.*;

public class HuffmanCodingDemo {
    public static Map<Byte, String> huffmanCodes = new HashMap<>();//将赫夫曼编码表放在Map<Byte,String>内
    public static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] bytes = content.getBytes();
        byte[] huffmanZips = huffmanZip(bytes);
//        System.out.print("压缩的结果是：");
        System.out.println(Arrays.toString(huffmanZips));
        System.out.println(byteToBitString(true, (byte) (-3)));
        System.out.println(byteToBitString(true, (byte) (3)));
        System.out.println(byteToBitString(false, (byte) 5));
        System.out.println((byte) Integer.parseInt("011", 2));
        System.out.println((byte) (256));
    }

    private static byte[] huffmanZip(byte[] bytes) {
        List<HuffNode> nodes = getNodes(bytes);
        HuffNode huffmanTree = createHuffmanTree(nodes);
        getCodes(huffmanTree, "", stringBuilder);
        return zip(bytes, huffmanCodes);
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
            HuffNode parent = new HuffNode(null, left.weight + right.weight);
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

    private static void getCodes(HuffNode node, String code, StringBuilder strings) {
        StringBuilder stringBuilder2 = new StringBuilder(strings);
        stringBuilder2.append(code);
        if (node != null) {
            if (node.data == null) {
                getCodes(node.left, "0", stringBuilder2);//向左递归
                getCodes(node.right, "1", stringBuilder2);//向右递归
            } else {
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    //编写一个方法，将字符串对应的byte[]数组通过生成的赫夫曼编码表，返回一个赫夫曼编码压缩后的byte数组

    /**
     *
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //统计返回byte[] huffmanCodeBytes长度
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            int end = i + 8;
            if (i + 8 > stringBuilder.length()) {
                end = stringBuilder.length();
            }
            String substring = stringBuilder.substring(i, end);
            huffmanCodeBytes[index++] = (byte) Integer.parseInt(substring, 2);
        }
        return huffmanCodeBytes;
    }

    /**
     * 将一个byte转成一个二进制的字符串
     */
    private static String byteToBitString(boolean flag, byte b) {
        /*int temp = b;
        StringBuilder string = new StringBuilder();
        if (flag && temp >= 0) {
            string.append("0000000");
        }
        string.append(Integer.toBinaryString(temp));
        if (flag) {
            return string.substring(string.length() - 8);
        } else {
            return string.toString();
        }*/
        int temp = b;
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    private static String decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean flag;
        int length = huffmanBytes.length;
        for (int i = 0; i < length; i++) {
            if (huffmanBytes[i] < 0) {
                stringBuilder.append(byteToBitString(true, huffmanBytes[i]));
            } else {
                stringBuilder.append(byteToBitString(false, huffmanBytes[i]));
            }
        }
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        List<Byte> list = new ArrayList<>();
        int max = 0;
        for (String s : map.keySet()) {
            if (s.length() > max) {
                max = s.length();
            }
        }
        for (int i = 0; i < stringBuilder.length(); ) {
            for (int j = i + 1; j < i + max + 1; j++) {
                if (map.containsKey(stringBuilder.substring(i, j))) {
                    list.add(map.get(stringBuilder.substring(i, j)));
                    i = j + 1;
                    break;
                }
            }
        }
        return null;
    }
}

class HuffNode implements Comparable<HuffNode> {
    Byte data;
    int weight;
    HuffNode left;
    HuffNode right;

    public HuffNode(Byte data, int weight) {
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