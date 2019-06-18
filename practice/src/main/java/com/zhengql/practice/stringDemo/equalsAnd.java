package com.zhengql.practice.stringDemo;

import java.util.*;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2019/5/31 15:30
 */
public class equalsAnd {
    static class Person{
        private int i;

        public Person(int i) {
            this.i = i;
        }

        public int getI() {
            return i;
        }

        public Person setI(int i) {
            this.i = i;
            return this;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj==this){
                return true;
            }
            Person person = (Person) obj;
            if (person.getI()==i){
                return true;
            }
            return false;
        }

//        @Override
//        public int hashCode() {
//            return i*100-i/10;
//        }

        @Override
        public String toString() {
            return "Person{" +
                    "i=" + i +
                    '}';
        }
    }
    public static void main(String[] args) {
        int a = 10;
        int b = 10;
        System.out.println("a==b:"+(a==b));

        String s1 = "A";//新建对象s1,引用地址指向栈内存中的A
        String s2 = "A";//新建对象s1,引用地址指向栈内存中的A
        String s3 = new String("A");//新建对象s3,引用地址指向堆内存中的A对象
        String s4 = new String("A");//新建对象s3,引用地址指向堆内存中的另一个A对象
        System.out.println("s1==s2:"+(s1==s2));
        System.out.println("s1==s3:"+(s1==s3));
        System.out.println("s3==s4:"+(s3==s4));


        equalsDemo();

        concatTest();

        ObjectTest();

        HashSet set = new HashSet();
        HashMap<Person,Integer> map = new HashMap();
        Person p1 = new Person(1);
        Person p2 = new Person(1);
        Person p3 = new Person(1);
        set.add(p1);
        set.add(p2);
        set.add(p3);
//        原因分析：
//（1）当执行set.add(p1)时（1），集合为空，直接存入集合；
//
//（2）当执行set.add(p2)时（2），首先判断该对象（p2）的hashCode值所在的存储区域是否有相同的hashCode，因为没有覆盖hashCode方法，
// 所以jdk使用默认Object的hashCode方法，返回内存地址转换后的整数，因为不同对象的地址值不同，所以这里不存在与p2相同hashCode值的对象，
// 因此jdk默认不同hashCode值，equals一定返回false，所以直接存入集合。
//
// （3）当执行set.add(p1)时（3），时，因为p1已经存入集合，同一对象返回的hashCode值是一样的，继续判断equals是否返回true，
// 因为是同一对象所以返回true。此时jdk认为该对象已经存在于集合中，所以舍弃。

        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        map.put(p1,10);
        map.put(p2,20);
        map.put(p3,30);

        for (Map.Entry<Person, Integer> s : map.entrySet()) {
            System.out.println("键值对:" + s);
        }
//        for (Integer s : map.values()) {
//            System.out.println("values:" + s);
//        }
//        for (Person s : map.keySet()) {
//            System.out.println("key:" + s);
//            System.out.println("values:" + map.get(s));
//        }

    }

    private static void concatTest() {
        String string = "a";
        String string1 = string.concat("");
        System.out.println(string);
        System.out.println(string1);

        System.out.println(string==string1);
    }

    private static void ObjectTest() {
        Person p1 = new Person(1);
        Person p2 = new Person(1);
        System.out.println(p1==p2);
        System.out.println(p1.equals(p2));
    }


    private static void equalsDemo() {

        String s1 = "A";//新建对象s1,引用地址指向栈内存中的A
        String s2 = "A";//新建对象s1,引用地址指向栈内存中的A
        String s3 = new String("A");//新建对象s3,引用地址指向堆内存中的A对象
        String s4 = new String("A");//新建对象s3,引用地址指向堆内存中的另一个A对象
        System.out.println("s1.equals(s2):"+(s1.equals(s2)));
        System.out.println("s1.equals(s3):"+(s1.equals(s3)));
        System.out.println("s3.equals(s4):"+(s3.equals(s4)));


        System.out.println();
    }


}
