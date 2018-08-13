package main.collections.map;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import main.collections.utils.MeasuringExecutionTimeKt;


public class Main {
    private static final int COUNT = 1000000;

    /**
     * HashMap:
     *
     * Interfaces:                  Map
     * Iteration Order:             no guarantee order, will remain constant over time.
     * Get/put remove containsKey:  O(1)
     * Interfaces:                  Map
     * Null values/keys:            allowed
     * Is synchronized:             implementation is not synchronized
     * Implementation:              buckets
     *
     * Description:
     * This implementation uses a hash table as the underlying data structure.
     * It implements all of the Map operations and allows "null values" and "one null key".
     * This class is roughly equivalent to "Hashtable" - a legacy data structure before Java Collections Framework, but it is not synchronized and permits nulls.
     * "HashMap" does not guarantee the order of its key-value elements.
     *
     * - Therefore, consider to use a HashMap when order does not matter and nulls are acceptable.
     */
    private static final String HASH_MAP = "HashMap";

    /**
     * LinkedHashMap:
     *
     * Interfaces:                  Map
     * Iteration Order:             insertion order.
     * Get/put remove containsKey:  O(1)
     * Interfaces:                  Map
     * Null values/keys:            allowed
     * Is synchronized:             implementation is not synchronized
     * Implementation:              double-linked buckets
     *
     * Description:
     * This implementation uses a hash table and a "LinkedList" as the underlying data structures,
     * thus the order of a LinkedHashMap is predictable, with "insertion-order" as the default order.
     * This implementation also allows nulls like "HashMap".
     *
     * - So consider using a LinkedHashMap when you want a Map with its key-value pairs are sorted by their insertion order.
     */
    private static final String LINKED_HASH_MAP = "LinkedHashMap";

    /**
     * TreeMap:
     *
     * Interfaces:                  Map
     * Iteration Order:             sorted according to the natural ordering.
     * Get/put remove containsKey:  O(log(n))
     * Interfaces:                  NavigableMap, Map, SortedMap
     * Null values/keys:            only values
     * Is synchronized:             implementation is not synchronized
     * Implementation:              Red-Black Tree
     *
     * Description:
     * This implementation uses a "red-black" tree as the underlying data structure.
     * A TreeMap is sorted according to the natural ordering of its keys, or by a Comparator provided at creation time.
     * This implementation does not allow nulls.
     *
     * - So consider using a TreeMap when you want a Map sorts its key-value pairs by the natural order of the keys
     * (e.g. alphabetic order or numeric order), or by a custom order you specify.
     */
    private static final String TREE_MAP = "TreeMap";

    /**
     * Legacy class from the days of Java 1.1, use "ConcurrentHashMap".
     */
    private static final String HASH_TABLE = "Hashtable";

    /**
     * ConcurrentHashMap:
     *
     * Interfaces:                  Map
     * Iteration Order:             no guarantee order, will remain constant over time.
     * Get/put remove containsKey:  O(1)
     * Interfaces:                  Map
     * Null values/keys:            disallowed
     * Is synchronized:             implementation is synchronized
     * Implementation:              buckets
     *
     * Description:
     * Unlike the legacy "Hashtable" which is synchronized, the "HashMap", "TreeMap" and "LinkedHashMap" are not synchronized.
     * If thread-safe is priority, consider using ConcurrentHashMap in place of HashMap.
     *
     * Or we can use the Collections.synchronizedMap() utility method that returns a synchronized (thread-safe) map backed by the specified map.
     *
     * For example:
     * Map<Integer, String> map = Collections.synchronizedMap(new HashMap<>());
     */
    private static final String CONCURRENT_HASH_MAP = "ConcurrentHashMap";

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
                    getItemTest();
                    break;
                case 4:
                    removeItemTest();
                    break;
            }

            questionMessage();
        }

        scanner.close();
    }

    private static void fillHashTest() {
        fillList(new HashMap<String, Person>(), HASH_MAP);
        fillList(new LinkedHashMap<String, Person>(), LINKED_HASH_MAP);
        fillList(new TreeMap<String, Person>(), TREE_MAP);
        fillList(new ConcurrentHashMap<String, Person>(), CONCURRENT_HASH_MAP);
    }

    private static void fillHashAndShowOrderTest() {
        fillShowOrderList(new HashMap<String, Person>(), HASH_MAP, 20);
        fillShowOrderList(new LinkedHashMap<String, Person>(), LINKED_HASH_MAP, 20);
        fillShowOrderList(new TreeMap<String, Person>(), TREE_MAP, 20);
        fillShowOrderList(new ConcurrentHashMap<String, Person>(), CONCURRENT_HASH_MAP, 20);
    }

    private static void getItemTest() {
        getItem(new HashMap<String, Person>(), HASH_MAP);
        getItem(new LinkedHashMap<String, Person>(), LINKED_HASH_MAP);
        getItem(new TreeMap<String, Person>(), TREE_MAP);
        getItem(new ConcurrentHashMap<String, Person>(), CONCURRENT_HASH_MAP);
    }

    private static void removeItemTest() {
        removeItem(new HashMap<String, Person>(), HASH_MAP);
        removeItem(new LinkedHashMap<String, Person>(), LINKED_HASH_MAP);
        removeItem(new TreeMap<String, Person>(), TREE_MAP);
        removeItem(new ConcurrentHashMap<String, Person>(), CONCURRENT_HASH_MAP);
    }

    private static void fillList(Map<String, Person> map, String title) {
        ListCollection l = seedingList(new ArrayList<>());

        long time = MeasuringExecutionTimeKt.start(title);

        seedingMap(l, map);

        MeasuringExecutionTimeKt.end(time);
    }

    private static void fillShowOrderList(Map<String, Person> map, String title, int count) {
        System.out.println("Start (" + title + "):");
        ListCollection l = seedingList(new ArrayList<>());

        MapCollection s = seedingMap(l, map);
        Map<String, Person> m = s.getItems();

        int index = 0;
        for(String key: m.keySet()) {
            System.out.println("key: " + key);

            if (index == count) {
                break;
            }
            index++;
        }

        System.out.println("-------END-------\n");
    }

    private static void getItem(Map<String, Person> map, String title) {
        ListCollection l = seedingList(new ArrayList<>());
        MapCollection m = seedingMap(l, map);

        long time = MeasuringExecutionTimeKt.start(title);
        for (int i = 0; i < COUNT; i++) {
            m.getItem("1");
            m.getItem("2");
            m.getItem("3");
            m.getItem("4");
            m.getItem("5");
            m.getItem("6");
        }
        MeasuringExecutionTimeKt.end(time);
    }

    private static void removeItem(Map<String, Person> map, String title) {
        ListCollection l = seedingList(new ArrayList<>());
        MapCollection m = seedingMap(l, map);

        long time = MeasuringExecutionTimeKt.start(title);

        for (int i = 0; i < COUNT; i++) {
            m.removeItem("1");
            m.removeItem("2");
            m.removeItem("3");
            m.removeItem("4");
            m.removeItem("5");
            m.removeItem("6");
            m.removeItem("7");
        }

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

    private static MapCollection seedingMap(ListCollection l, Map<String, Person> map) {
        MapCollection m = new MapCollection(map);

        for (int i = 1; i < l.getItems().size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            String strIndex = sb.toString();

            m.setItem(strIndex, new Person(20, "Name - " + i));
        }

        return m;
    }

    private static void questionMessage() {
        System.out.println("Enter collection test (fill - 1, show collection order - 2, get - 3, remove - 4): ");
    }
}
