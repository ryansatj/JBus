package com.RyanSafaTjendanaJBusAF;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Algorithm {
    private Algorithm(){

    }

    public static <T> boolean exists(T[] array, T value){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it, value);
    }

    public static <T> boolean exists(Iterable<T> iterable, T value){
        final Iterator<T> it = iterable.iterator();
        return exists(it, value);
    }

    public static <T> boolean exists(Iterator<T> iterator, T value){
        final Predicate<T> pred = value::equals;
        return exists(iterator, pred);
    }

    public static <T> boolean exists(T[] array, Predicate<T> pred){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it, pred);
    }

    public static <T> boolean exists(Iterable<T> iterable, Predicate<T> pred){
        final Iterator<T> it = iterable.iterator();
        return exists(it, pred);
    }

    public static <T> boolean exists(Iterator<T> iterator, Predicate<T> pred){
        while(iterator.hasNext()){
            T current  = iterator.next();
            if(pred.predicate(current)){
                return true;
            }
        }

        return false;
    }

    public static <T> int count(Iterator<T> iterator, T value)
    {
        final Predicate<T> pred = value::equals;
        return count(iterator, pred);
    }

    public static <T> int count(T[] array, T value)
    {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return count(it, value);
    }

    public static <T> int count(Iterator<T> iterator, Predicate<T> pred)
    {
            int count = 0;
            while(iterator.hasNext()){
                if(pred.predicate(iterator.next())) {
                    count++;
                }
            }
            return count;
    }

    public static <T> int count(T[] array, Predicate<T> pred)
    {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return count(it, pred);
    }

    public static <T> int count(Iterable<T> iterable, T value){
        final Iterator<T> it = iterable.iterator();
        return count(it, value);
    }

    public static <T> int count (Iterable<T> iterable, Predicate<T> pred){
        final Iterator<T> it = iterable.iterator();
        return count(it, pred);
    }

    public static <T> T find(Iterator<T> iterator, T value)
    {
        final Predicate<T> pred = value::equals;
        return find(iterator, pred);
    }

    public static <T> T find(T[] array, T value)
    {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return find(it, value);
    }

    public static <T> T find(Iterator<T> iterator, Predicate<T> pred){
        while(iterator.hasNext()){
            T current  = iterator.next();
            if(pred.predicate(current)){
                return current;
            }
        }
        return null;
    }

    public static <T> T find(T[] array, Predicate<T> pred)
    {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return find(it, pred);
    }

    public static <T> T find(Iterable<T> iterable, T value){
        final Iterator<T> it = iterable.iterator();
        return find(it, value);
    }

    public static <T> T find (Iterable<T> iterable, Predicate<T> pred){
        final Iterator<T> it = iterable.iterator();
        return find(it, pred);
    }

    public static <T> List<T> collect(Iterator<T> iterator, T value)
    {
        final Predicate<T> pred = value::equals;
        return collect(iterator, pred);
    }

    public static <T> List<T> collect(T[] array, T value)
    {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return collect(it, value);
    }

    public static <T> List<T> collect(Iterator<T> iterator, Predicate<T> pred)
    {
        List<T> list = new ArrayList<>();
        T temp;
            int count = 0;
            while (iterator.hasNext()) {
                temp = iterator.next();
                if(pred.predicate(temp))
                    list.add(temp);
            }
            return list;
    }

    public static <T> List<T> collect(T[] array, Predicate<T> pred)
    {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return collect(it, pred);
    }

    public static <T> List<T> collect(Iterable<T> iterable, T value){
        final Iterator<T> it = iterable.iterator();
        return collect(it, value);
    }

    public static <T> List<T> collect (Iterable<T> iterable, Predicate<T> pred){
        final Iterator<T> it = iterable.iterator();
        return collect(it, pred);
    }

    public static <T> List<T> paginate (T[] array, int page, int pagesize, Predicate<T> pred){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return paginate(it, page, pagesize, pred);
    }

    public static <T> List<T> paginate(Iterable<T> iterable, int page, int pagesize, Predicate<T> pred)
    {
        final Iterator<T> it = iterable.iterator();
        return paginate(it, page, pagesize, pred);
    }

    public static <T> List<T> paginate(Iterator<T> iterator, int page, int pagesize, Predicate<T> pred)
    {
        List<T> list = new ArrayList<T>();
        int c = 0;
        int s = 0;
        while(iterator.hasNext()) {
            T temp;
                temp = iterator.next();
                if (pred.predicate(temp)) {
                    c++;
                    if (c > page * pagesize && c <= (page + 1) * pagesize) {
                        list.add(temp);
                        s++;
                    }
                }
                if(s == pagesize)
                    break;
        }
            return list;
    }
}
