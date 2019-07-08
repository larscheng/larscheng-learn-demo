package com.larscheng.practice.leetcode;

import java.util.HashMap;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2019/6/12 14:18
 */
public class DesignerHash {


    /**
     * 借助HashMap
     */
    static class myHashSet1 {
        private HashMap map = null;

        public myHashSet1() {
            map = new HashMap();
        }

        public void add(int key) {
            map.put(key, key);
        }

        public void remove(int key) {
            map.remove(key);
        }

        public boolean contains(int key) {
            return map.containsValue(key);
        }
    }


    static class MyHashSet2 {

        int[] data = null;
        /**
         * Initialize your data structure here.
         */
        public MyHashSet2() {
            data = new int[1000005];
            for (int i =0 ;i<data.length;i++){
                data[i]=-1;
            }
        }

        public void add(int key) {
            data[key] = key;
        }

        public void remove(int key) {
            data[key]=-1;
        }

        /**
         * Returns true if this set contains the specified element
         */
        public boolean contains(int key) {
            return data[key]>-1;
        }
    }

    class MyHashSet {

        /** Initialize your data structure here. */
        boolean[] map = new boolean[1000005];
        public MyHashSet() {

        }

        public void add(int key) {
            map[key] = true;
        }

        public void remove(int key) {
            map[key] = false;
        }

        /** Returns true if this set contains the specified element */
        public boolean contains(int key) {
            return map[key] == true;
        }
    }
}
