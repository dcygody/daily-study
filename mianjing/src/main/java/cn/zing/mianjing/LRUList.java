package cn.zing.mianjing;

import java.util.LinkedList;

/**
 * @description: 使用List实现LRU
 * LRU，即最近最少使用策略，基于时空局部性原理(最近访问的，未来也会被访问)，往往作为缓存淘汰的策略如Redis和GuavaMap都使用了这种淘汰策略。
 * @author: dcy
 * @create: 2023-07-28 14:40
 */
public class LRUList<E> {

    private final int maxSize;

    private final LinkedList<E> list = new LinkedList<>();

    public LRUList(int maxSize) {
        this.maxSize = maxSize;
    }

    public void add(E e) {
        if (list.size() > maxSize) {
            list.removeLast();
        }
        list.addFirst(e);
    }

    public E get(int index) {
        E e = list.get(index);
        list.remove(index);
        add(e);
        return e;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}

class Test {

    public static void main(String[] args) {
        LRUList<String> list = new LRUList<>(5);
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        System.out.println(list.toString());
        String s = list.get(4);
        System.out.println(list.toString());
    }
}


