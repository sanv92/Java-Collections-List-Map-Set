package main.collections.set;


public class Person implements Comparable<Person> {
    private int id;
    private int age;
    private String name;

    public Person(int id, int age, String name) {
        super();

        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Person)) return false;
        Person p = (Person) obj;

        if(p.getName() == this.getName() || this.getName().equals(p.getName())) return true;
        return false;
    }

    @Override
    public int hashCode(){
        return getName().hashCode();
    }

    @Override
    public int compareTo(Person p) {
        return name.compareTo(p.getName());
    }
}
