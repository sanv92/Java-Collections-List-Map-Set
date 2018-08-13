package main.collections.list;

import java.util.*;
import main.collections.utils.MeasuringExecutionTimeKt;


public class Main {
    private static final int COUNT = 1000000;

    /**
     * ArrayList:
     *
     * Interfaces:                  List
     * Null values:                 allowed
     * Is synchronized:             implementation is not synchronized
     *
     * Description:
     * This "ArrayList" is implemented as a resizable array.
     * As more elements are added to ArrayList, its size is increased dynamically.
     * It's elements can be accessed directly by using the get and set methods, since "ArrayList" is essentially an array.
     */
    private static final String ARRAY_LIST = "ArrayList";

    /**
     * LinkedList:
     *
     * Interfaces:                  List
     * Null values:                 allowed
     * Is synchronized:             implementation is not synchronized
     * Implementation:              buckets
     *
     * Description:
     * This "LinkedList" is implemented as a double linked list.
     * Its performance on add and remove is better than "ArrayList", but worse on get and set methods.
     */
    private static final String LINKED_LIST = "LinkedList";

    /**
     * Vectors:
     *
     * Interfaces:                  List
     * Null values:                 allowed
     * Is synchronized:             implementation is synchronized
     *
     * Description:
     * Any method that touches the Vector's contents is thread safe.
     * ArrayList, on the other hand, is not synchronized, making them, therefore, not thread safe.
     * With that difference in mind, using synchronization will incur a performance hit.
     *
     * So if you don't need a thread-safe collection, use the ArrayList.
     * Normally, most Java programmers use "ArrayList" instead of "Vector" because they can synchronize explicitly by themselves.
     */
    private static final String VECTOR = "Vector";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        questionMessage();
        while (scanner.hasNextLong()) {
            int i = scanner.nextInt();
            switch (i) {
                case 1:
                    fillListTest();
                    break;
                case 2:
                    getItemTest();
                    break;
                case 3:
                    removeMiddleItemTest();
                    break;
                case 4:
                    removeEndItemTest();
                    break;
                case 5:
                    addMiddleItemTest();
            }

            questionMessage();
        }

        scanner.close();
    }

    private static void fillListTest() {
        fillList(new ArrayList<>(), ARRAY_LIST);
        fillList(new LinkedList<>(), LINKED_LIST);
        fillList(new Vector<>(), VECTOR);
    }

    private static void getItemTest() {
        getItem(new ArrayList<>(), ARRAY_LIST);
        getItem(new LinkedList<>(), LINKED_LIST);
        getItem(new Vector<>(), VECTOR);
    }

    private static void removeMiddleItemTest() {
        removeMiddleItem(new ArrayList<>(), ARRAY_LIST);
        removeMiddleItem(new LinkedList<>(), LINKED_LIST);
        removeMiddleItem(new Vector<>(), VECTOR);
    }

    private static void removeEndItemTest() {
        removeEndItem(new ArrayList<>(), ARRAY_LIST);
        removeEndItem(new LinkedList<>(), LINKED_LIST);
        removeEndItem(new Vector<>(), VECTOR);
    }

    private static void addMiddleItemTest() {
        addItemMiddle(new ArrayList<>(), ARRAY_LIST);
        addItemMiddle(new LinkedList<>(), LINKED_LIST);
        addItemMiddle(new Vector<>(), VECTOR);
    }

    private static void fillList(List<Person> list, String title) {
        long time = MeasuringExecutionTimeKt.start(title);
        seedingList(list);
        MeasuringExecutionTimeKt.end(time);
    }

    private static void getItem(List<Person> list, String title) {
        ListCollection l = seedingList(list);

        long time = MeasuringExecutionTimeKt.start(title);
        int index = COUNT / 2;
        Person p = l.getItem(index);
        System.out.println("Person2: " + p.getName());
        MeasuringExecutionTimeKt.end(time);
    }

    private static void removeMiddleItem(List<Person> list, String title) {
        ListCollection l = seedingList(list);

        long time = MeasuringExecutionTimeKt.start(title);
        int index = COUNT / 2;
        l.removeItem(index);
        MeasuringExecutionTimeKt.end(time);
    }

    private static void removeEndItem(List<Person> list, String title) {
        ListCollection l = seedingList(list);

        long time = MeasuringExecutionTimeKt.start(title);
        l.removeItem(COUNT);
        MeasuringExecutionTimeKt.end(time);
    }

    private static void addItemMiddle(List<Person> list, String title) {
        ListCollection l = seedingList(list);

        long time = MeasuringExecutionTimeKt.start(title);
        int index = COUNT / 2;
        l.setItem(new Person(25, "Name 4"), index);
        MeasuringExecutionTimeKt.end(time);
    }

    private static ListCollection seedingList(List<Person> list) {
        ListCollection l = new ListCollection(list);
        for (int i = 0; i < COUNT; i++) {
            l.setItem(new Person(30, "Name 1"));
            l.setItem(new Person(22, "Name 2"));
            l.setItem(new Person(40, "Name 3"));
        }

        return l;
    }

    private static void questionMessage() {
        System.out.println("Enter collection test (fill - 1, get - 2, remove middle - 3, remove end - 4, add middle - 5): ");
    }
}
