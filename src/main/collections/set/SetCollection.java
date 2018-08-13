package main.collections.set;

import java.util.Set;


public class SetCollection {
    private Set<Person> items;

    SetCollection(Set<Person> map) {
        this.items = map;
    }

    public void setItem(Person item) {
        items.add(item);
    }

    public Set<Person> getItems() {
        return items;
    }

    public void removeItem(Person item) {
        items.remove(item);
    }
}
