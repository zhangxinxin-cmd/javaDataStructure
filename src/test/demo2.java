package test;

import java.lang.reflect.Field;

public class demo2 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class<String> stringClass = String.class;
        Field value = stringClass.getDeclaredField("value");
        String string = "abc";
        String string2 = "abc";
        String string3 = new String("abc");
        System.out.println("before===========");
        System.out.println(string);
        System.out.println(string2);
        System.out.println(string == string2);
        value.setAccessible(true);
        char[] chars = (char[]) value.get(string);
        chars[0] = 'A';
        System.out.println("after===========");
        System.out.println(string);
        System.out.println(string2);
        System.out.println(string == string2);
    }
}
