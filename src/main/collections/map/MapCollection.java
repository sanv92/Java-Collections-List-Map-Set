package main.collections.map;

import java.util.Map;


public class MapCollection {
    private Map<String, Person> items;

    MapCollection(Map<String, Person> map) {
        this.items = map;
    }

    public void setItem(String key, Person item) {
        items.put(key, item);
    }

    public Person getItem(String key) {
        return items.get(key);
    }

    public Map<String, Person> getItems() {
        return items;
    }

    public void removeItem(String key) {
        items.remove(key);
    }
}
