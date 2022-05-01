package test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class QiaokeliBuyDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入天数");
        int day = scanner.nextInt();
        System.out.println("请输入巧克力种类数量:");
        int kind = scanner.nextInt();
        qiaokeli[] qiaokelis = new qiaokeli[kind];
        for (int i = 0; i < qiaokelis.length; i++) {
            System.out.print("day");
            qiaokelis[i] = new qiaokeli();
            qiaokelis[i].price = scanner.nextInt();
            System.out.print("price:");
            qiaokelis[i].dayLeft = scanner.nextInt();
            System.out.print("quantity:");
            qiaokelis[i].Quantity = scanner.nextInt();
        }
        //从大到小排
        Arrays.sort(qiaokelis, new Comparator<qiaokeli>() {
            @Override
            public int compare(qiaokeli o1, qiaokeli o2) {
                double aver1 = (double) o1.dayLeft / o1.price;
                double aver2 = (double) o2.dayLeft / o2.price;
                if (aver1 > aver2) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        int num = day;
        int cost = 0;
        int index = 0;
        while (num > 0) {
            qiaokeli maxAver = qiaokelis[index++];
            if (maxAver.Quantity >= num) {
                cost += maxAver.price * num;
                System.out.println(maxAver);
                num = 0;
            } else {
                cost += maxAver.price * maxAver.Quantity;
                System.out.println(maxAver);
                num -= maxAver.Quantity;
            }
        }
        System.out.println(cost);
    }
}

class qiaokeli {
    public int dayLeft;
    public int price;
    public int Quantity;

    @Override
    public String toString() {
        return "qiaokeli{" +
                "dayLeft=" + dayLeft +
                ", price=" + price +
                ", Quantity=" + Quantity +
                '}';
    }
}