package note;

import java.util.Iterator;

/**
 * @description: 实现了双向链表
 * @author: dcy
 * @create: 2023-07-15 21:27
 */
public class MyLinkedList<T>{

    private Node<T> head;

    private Node<T> tail;

    private static class Node<T> {

        T value;

        Node<T> prev;

        Node<T> next;

        public Node(T value) {
            this.value = value;
        }

        public Node() {
        }
    }


    public Node<T> addLast(T value) {
        Node<T> node = new Node<>(value);
        Node<T> pred = tail;
        if (null != pred) {
            node.prev = pred;
            tail = node;
            pred.next = node;
            return node;
        }
        enq(node);
        return node;

    }


    private Node<T> enq(final Node<T> node) {

        for (;;) {
            Node<T> t = tail;
            if (null == t) { // 初始化链表
                head = new Node<>();
                tail = head;
            } else {
                node.prev = t;
                tail = node;
                t.next = node;
                return t;
            }
        }

    }

    public void iter() {
        Node<T> node = head;
        while (null != node.next) {
            System.out.print(node.next.value + ",");
            node = node.next;
        }
        System.out.println();
    }

    public void iterTail() {
        Node<T> node = tail;
        while (null != node.prev) {
            System.out.print(node.value + ",");
            node = node.prev;
        }
        System.out.println();
    }



    private class ListIter implements Iterator<T> {


        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            return null;
        }
    }



}


