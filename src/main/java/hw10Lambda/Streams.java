package hw10Lambda;

/**
 * Created by HOME on 14.05.2017.
 */


import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by HOME on 14.05.2017.
 */
public class Streams<T>  {
    private T[] arr;

    public Streams(T[] arr) {
        this.arr = arr;
    }

    public static<T> Streams<T> of(T... values) {
        return new Streams(values);
    }
    //public Streams filter(........) {
    //    return this;
    //}
    public Streams<T> filter(Predicate<? super T> predicate){
        List<T> list = new ArrayList<T>();
        for(int i=0;i<arr.length;i++)
        {
            if (predicate.test(arr[i]))
            {
                list.add(arr[i]);
            }
        }
        this.arr = (T[])list.toArray();
        return this;
    }

    public  Streams<T> transform(Function<T,T> mapper) {
        List<T> list = new ArrayList<T>();
        for(int i=0;i<arr.length;i++)
        {
            list.add(mapper.apply( arr[i]));
        }
        this.arr = (T[])list.toArray();
        return this;
    }
    <R> Streams<R> map(Function<? super T, ? extends R> fun){
        List<R> list = new ArrayList<R>();
        for(int i=0;i<arr.length;i++)
        {
            list.add(fun.apply( arr[i]));
        }
        R[] newArr = (R[])list.toArray();
        return (Streams<R>) new Streams<R>(newArr);
    }

    <K,V> Map<K,V> toMap(Function<? super T, ? extends K> funK,Function<? super T, ? extends V> funV){
        Map<K,V> map = new HashMap<K, V>();
        for(int i=0;i<arr.length;i++)
        {
            map.put(funK.apply( arr[i]),funV.apply( arr[i]));
        }
        return map;
    }

    public void print(){
        for(int i=0;i<arr.length;i++)
            System.out.println(arr[i]);
    }
}
