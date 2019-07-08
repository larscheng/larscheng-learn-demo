package com.larscheng.practice.stringDemo;

import java.util.HashSet;
import java.util.Objects;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2019/6/5 10:11
 */
public class HashEqualsDemo {

    static class Person {
        private String age;

        Person(String age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof Person)) {
                return false;
            }
            //地址相同必相等
            if (obj == this) {
                return true;
            }
            Person person = (Person) obj;
            //地址不同比较值是否相同
            return person.age.equals(this.age);
        }

        @Override
        public int hashCode() {
            return Objects.hash(age);
        }


        @Override
        public String toString() {
            return "Person{" + "age='" + age + '\'' + '}';
        }
    }

    static class People {
        private String age;

        People(String age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof People)) {
                return false;
            }
            //地址相同必相等
            if (obj == this) {
                return true;
            }
            People people = (People) obj;
            //地址不同比较值是否相同
            return people.age.equals(this.age);
        }

        @Override
        public int hashCode() {
            return Objects.hash(age);
        }


        @Override
        public String toString() {
            return "People{" + "age='" + age + '\'' + '}';
        }
    }

    public static void main(String[] args) {
        HashSet set1 = new HashSet();
        set1.add("1");
        set1.add("1");
        set1.add("1");

        for (Object a : set1) {
            System.out.println(a);
        }


        HashSet set2 = new HashSet();
        Person p1 = new Person("1");
        Person p2 = new Person("1");
        Person p3 = new Person("1");
        set2.add(p1);
        set2.add(p2);
        set2.add(p3);

        for (Object a : set2) {
            System.out.println(a);
        }

        HashSet set3 = new HashSet();
        People o1 = new People("6");
        People o2 = new People("6");
        People o3 = new People("6");
        set3.add(o1);
        set3.add(o2);
        set3.add(o3);
        for (Object a : set3) {
            System.out.println(a);
        }



    }
}
