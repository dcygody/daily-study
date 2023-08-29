package cn.zing.lingsuihua.collection;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @description: list 遍历修改
 * @author: dcy
 * @create: 2023-08-29 10:26
 */
public class ListDemo {


    public static void main(String[] args) {
//        listRemove();
//        iteratorRemove();
        cowRemove();
    }

    /**
     * 通过 remove方式移除元素, 索引需要变化
     */
    public static void listRemove() {

        List<Integer> list = getList();

        for (int i = 0; i < list.size(); i++) {
            Integer integer = list.get(i);
            if (integer % 2 == 0) {
                list.remove(integer);
                //做一次i--，避免漏删
                i--;
            }
        }
        System.out.println(list);
    }

    /**
     * 迭代器删除
     */
    public static void iteratorRemove() {

        List<Integer> list = getList();
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next % 2 == 0) {
                iterator.remove();
            }
        }
        System.out.println(list);
    }

    /**
     * 通过线程安全的集合类
     */
    public static void cowRemove() {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(getList());

        for (Integer i : list) {
            if (i % 2 == 0) {
                list.remove(i);
            }
        }
        System.out.println(list);
    }


    public static List<Integer> getList() {

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            list.add(i);
        }
        return list;


    }
}


