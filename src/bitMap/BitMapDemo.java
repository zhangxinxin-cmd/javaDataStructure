package bitMap;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

/*
 * bitMap(位图)
 * 一个字节为8bit,假设初始为0000 0000(2)
 * 现在要存储0~7的数字，要求不能重复
 * 0000 0000(2)每一位都可以设置为1，1所在的下标就可以表示成存储的数字，最多能存储的数字为0~7,且可以做到不重复。
 * */
public class BitMapDemo {
    public static void main(String[] args) {
        int[] array = {4, 2, 3, 5, 6, 8, 3, 2, 1};
        int i = bitMap(array);
        /*System.out.println(i);
        System.out.println(getValues(i));*/
        BitSet bitSet = new BitSet();
        for (int value : array) {
            bitSet.set(value);
        }
        System.out.println(bitSet);
    }

    public static int bitMap(int[] array) {
        int collect = 0;
        int temp = 1;
        for (int value : array) {
            temp = temp << value;
            collect = collect | temp;
            temp = 1;
        }
        return collect;
    }

    public static List<Integer> getValues(int collect) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 32; i++) {
            if (((collect >> i) & 1) != 0) {
                list.add(i);
            }
        }
        return list;
    }
}
