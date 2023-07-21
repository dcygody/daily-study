package note;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-15 22:04
 */
public class ListTest {

    public static void main(String[] args) {
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();

        myLinkedList.addLast("1");
        myLinkedList.addLast("2");
        myLinkedList.addLast("3");
        myLinkedList.addLast("4");
        myLinkedList.addLast("5");

        myLinkedList.iter();
        myLinkedList.iterTail();

    }
}


