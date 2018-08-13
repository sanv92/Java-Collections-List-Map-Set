package main.collections.map;

import java.util.List;


public class ListCollection {
    private List<Person> items;

    ListCollection(List<Person> list) {
        this.items = list;
    }

    public List<Person> getItems() {
        return items;
    }

    public void setItem(Person item) {
        items.add(item);
    }

    public void setItem(Person item, int index) {
        items.add(index, item);
    }

    public Person getItem(int index) {
        return items.get(index);
    }

    public void removeItem(int index) {
        items.remove(index);
    }
}
