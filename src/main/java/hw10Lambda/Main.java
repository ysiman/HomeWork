package hw10Lambda;

import Common.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by HOME on 14.05.2017.
 */
public class Main {
    public static void main(String[] args) {

        Person p1 = new Person(1L,"Person1","1-1-1");
        Person p2 = new Person(2L,"Person2","2-2-2");
        Person p3 = new Person(3L,"Person3","3-3-3");
        Person p4 = new Person(4L,"Person4","4-4-4");
        Streams<Person> personStream =Streams.of(p1,p2,p3,p4);
        System.out.println("Вывод потока personStream:");
        personStream.print();
        System.out.println("Поток после применения фильтра:");
        personStream = personStream.filter(s->s.getId()< 4L);
        personStream.print();
        System.out.println("Поток после применения transform:");
        personStream = personStream.transform(p -> new Person(p.getId() + 1L,p.getName()+"Transformed",p.getPhoneNumber()) );
        personStream.print();
        System.out.println("Перевели поток в мапу:");
        Map<Long,String> map = personStream.toMap(p ->p.getId(),p -> p.getName());
        for(Map.Entry<Long, String> m : map.entrySet()){

            System.out.printf("Ключ: %s  Значение: %s \n", m.getKey(), m.getValue());
        }

        System.out.println("Поток после применения map:");
        Streams<String> strStream = personStream.map(p -> p.getName());
        strStream.print();


    }
}
