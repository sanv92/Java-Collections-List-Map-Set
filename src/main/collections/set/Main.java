package main.collections.set;


import main.collections.utils.MeasuringExecutionTimeKt;
import java.util.*;


public class Main {
    private static final int COUNT = 1000000;

    /**
     * HashSet:
     *
     * Interfaces:                  Set
     * Iteration Order:             no guarantee order, will remain constant over time.
     * Interfaces:                  Set
     * Null values/keys:            disallowed
     * Is synchronized:             implementation is not synchronized
     * Implementation:              buckets
     *
     * Description:
     * The important points about Java HashSet class are:
     * - It stores unique elements and permits nulls
     * - It’s backed by a HashMap
     * - It doesn’t maintain insertion order
     * - It’s not thread-safe
     *
     * The add() method can be used for adding elements to a set.
     * The method contract states that an element will be added only when it isn’t already present in a set.
     * If an element was added, the method returns true, otherwise – false.
     */
    private static final String HASH_SET = "HashSet";

    /**
     * LinkedHashSet:
     *
     * Interfa2ces:                  Set
     * Iteration Order:             insertion order
     * Null values:                 disallowed
     * Is synchronized:             implementation is not synchronized
     * Implementation:              double-linked buckets
     *
     * Description:
     * A LinkedHashSet is an ordered version of HashSet that maintains a doubly-linked List across all elements.
     * When the iteration order is needed to be maintained this class is used.
     * When iterating through a HashSet the order is unpredictable,
     * while a LinkedHashSet lets us iterate through the elements in the order in which they were inserted.
     * When cycling through LinkedHashSet using an iterator, the elements will be returned in the order in which they were inserted.
     */
    private static final String LINKED_HASH_SET = "LinkedHashSet";

    /**
     * TreeSet:
     *
     * Interfaces:                  Set
     * Iteration Order:             sorted according to the natural ordering.
     * Interfaces:                  NavigableSet, Set, SortedSet
     * Null values/keys:            disallowed
     * Is synchronized:             implementation is not synchronized
     * Implementation:              Red-Black Tree
     *
     * Description:
     * This implementation uses a "red-black" tree as the underlying data structure.
     * A TreeSet is sorted according to the natural ordering of its keys, or by a Comparator provided at creation time.
     * This implementation does not allow nulls.
     *
     * - So consider using a "TreeSet" when you want a Set sorts its key-value pairs by the natural order of the keys
     * (e.g. alphabetic order or numeric order), or by a custom order you specify.
     */
    private static final String TREE_SET = "TreeSet";

    private static final String QUESTION_MESSAGE = "Enter collection test (fill - 1, show collection order - 2, remove - 3): ";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        questionMessage();
        while (scanner.hasNextLong()) {
            int i = scanner.nextInt();

            switch (i) {
                case 1:
                    fillHashTest();
                    break;
                case 2:
                    fillHashAndShowOrderTest();
                    break;
                case 3:
                    removeItemTest();
                    break;
            }

            questionMessage();
        }

        scanner.close();
    }

    private static void fillHashTest() {
        fillSet(new HashSet<Person>(), HASH_SET);
        fillSet(new LinkedHashSet<Person>(), LINKED_HASH_SET);
        fillSet(new TreeSet<Person>(), TREE_SET);
    }

    private static void fillHashAndShowOrderTest() {
        fillShowOrderList(new HashSet<Person>(), HASH_SET, 20);
        fillShowOrderList(new LinkedHashSet<Person>(), LINKED_HASH_SET, 20);
        fillShowOrderList(new TreeSet<Person>(), TREE_SET, 20);
    }

    private static void removeItemTest() {
        removeItem(new HashSet<Person>(), HASH_SET);
        removeItem(new LinkedHashSet<Person>(), LINKED_HASH_SET);
        removeItem(new TreeSet<Person>(), TREE_SET);
    }

    private static void fillSet(Set<Person> set, String title) {
        ListCollection l = seedingList(new ArrayList<>());

        long time = MeasuringExecutionTimeKt.start(title);

        seedingSet(l, set);

        MeasuringExecutionTimeKt.end(time);
    }

    private static void fillShowOrderList(Set<Person> set, String title, int count) {
        System.out.println("Start (" + title + "):");
        ListCollection l = seedingList(new ArrayList<>());

        SetCollection s = seedingSet(l, set);
        Set<Person> items = s.getItems();

        int index = 0;
        for(Person i : items) {
            System.out.println("name: " + i.getName());

            if (index == count) {
                break;
            }
            index++;
        }

        System.out.println("-------END-------\n");
    }

    private static void removeItem(Set<Person> set, String title) {
        ListCollection l = seedingList(new ArrayList<>());
        SetCollection m = seedingSet(l, set);

        long time = MeasuringExecutionTimeKt.start(title);

        for (int i = 0; i < COUNT; i++) {
            m.removeItem(new Person(1, 30, "Name 1"));
            m.removeItem(new Person(2, 30, "Name 1"));
            m.removeItem(new Person(3, 30, "Name 1"));
            m.removeItem(new Person(1, 30, "Name 1"));
            m.removeItem(new Person(2, 30, "Name 1"));
            m.removeItem(new Person(3, 30, "Name 1"));
        }

        MeasuringExecutionTimeKt.end(time);
    }

    private static ListCollection seedingList(List<Person> list) {
        ListCollection l = new ListCollection(list);
        for (int i = 0; i < COUNT; i++) {
            l.setItem(new Person(1, 30, "Name 1"));
            l.setItem(new Person(2, 30, "Name 1"));
            l.setItem(new Person(3, 30, "Name 1"));
        }

        return l;
    }

    private static SetCollection seedingSet(ListCollection l, Set<Person> set) {
        SetCollection m = new SetCollection(set);

        for (int i = 1; i < l.getItems().size(); i++) {
            m.setItem(new Person(1, 20, "Name - " + i));
            m.setItem(new Person(2, 20, "Name - " + i));
            m.setItem(new Person(3, 20, "Name - " + i));
        }

        return m;
    }

    private static void questionMessage() {
        System.out.println(QUESTION_MESSAGE);
    }
}
