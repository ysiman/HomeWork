package Common;

/**
 * Created by HOME on 14.05.2017.
 */
public class Person implements Comparable<Person>{
    Long id;
    String name;
    String phoneNumber;

    public Person(Long id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Person{id="+this.getId()+";name="+this.getName()+";phoneNumber="+this.getPhoneNumber()+"}";
    }


    @Override
    public boolean equals(Object obj) {
        return this.getId().equals(((Person)obj).getId());
    }

    @Override
    public int compareTo(Person o) {
        return this.getPhoneNumber().compareTo(o.getPhoneNumber());
    }

}