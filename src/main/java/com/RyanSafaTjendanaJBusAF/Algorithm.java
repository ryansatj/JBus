package com.RyanSafaTjendanaJBusAF;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Class Algoritm untuk menghandle apapun dengan single point of error
 * @author Ryan Safa T
 */
public class Algorithm {
    private Algorithm(){}

    /**
     *
     * @param array menerima param array
     * @param value menerima suatu value
     * @return return boolean true untuk predikat benar
     * @param <T> generic untuk bentuk apapun
     */
    public static <T> boolean exists(T[] array, T value){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it, value);
    }

    /**
     *
     * @param iterable param untuk suatu bentuk yang dapat di iterasi
     * @param value nilai yang akan di cek
     * @return return boolean true untuk predikat benar
     * @param <T> generic untuk bentuk apapun
     */
    public static <T> boolean exists(Iterable<T> iterable, T value){
        final Iterator<T> it = iterable.iterator();
        return exists(it, value);
    }

    /**
     *
     * @param iterator param ini digunakan untuk suatu parameter dalam bentuk objek iterator
     * @param value nilai yang akan di cek
     * @return return boolean true untuk predikat benar
     * @param <T> generik untuk bentuk apapun
     */

    public static <T> boolean exists(Iterator<T> iterator, T value){
        final Predicate<T> pred = value::equals;
        return exists(iterator, pred);
    }

    /**
     *
     * @param array param untuk bentuk array apapun
     * @param pred predikat yang akan di cek
     * @return return boolean true untuk predikat benar
     * @param <T> generik untuk bentuk apapun
     */
    public static <T> boolean exists(T[] array, Predicate<T> pred){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it, pred);
    }

    /**
     *
     * @param iterable param untuk bentuk iterable
     * @param pred predikat yang akan di cek
     * @return return boolean true untuk predikat benar
     * @param <T> generik untuk bentuk apapun yang akan diterima
     */
    public static <T> boolean exists(Iterable<T> iterable, Predicate<T> pred){
        final Iterator<T> it = iterable.iterator();
        return exists(it, pred);
    }

    /**
     *
     * @param iterator param bentuk iterator
     * @param pred predikat yang akan di cek
     * @return return boolean true untuk predikat benar
     * @param <T> generik untuk bentuk apapun
     */
    public static <T> boolean exists(Iterator<T> iterator, Predicate<T> pred){
        while(iterator.hasNext()){
            T current  = iterator.next();
            if(pred.predicate(current)){
                return true;
            }
        }

        return false;
    }

    /**
     *
     * @param iterator param dalam bentuk iterator
     * @param value nilai yang akan di cek
     * @return counter dalam bentuk integer yang sesuai dengan predikat
     * @param <T> generic untuk menerima bentuk apapun
     */
    public static <T> int count(Iterator<T> iterator, T value)
    {
        final Predicate<T> pred = value::equals;
        return count(iterator, pred);
    }

    /**
     *
     * @param array menerima param array
     * @param value nilai yang akan di cek
     * @return counter dalam bentuk integer yang sesuai dengan predikat
     * @param <T> generic untuk menerima bentuk apapun
     */
    public static <T> int count(T[] array, T value)
    {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return count(it, value);
    }

    /**
     *
     * @param iterator untuk parameter iterator
     * @param pred predikat yang akan di cek
     * @return counter dalam bentuk integer yang sesuai dengan predikat
     * @param <T> generic untuk menerima bentuk apapun
     */
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

    /**
     *
     * @param array untuk parameter array
     * @param pred predikat yang akan di cek
     * @return counter dalam bentuk integer yang sesuai dengan predikat
     * @param <T> generic untuk menerima bentuk apapun
     */
    public static <T> int count(T[] array, Predicate<T> pred)
    {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return count(it, pred);
    }

    /**
     *
     * @param iterable param dalam bentuk iterable
     * @param value nilai yang akan di cek
     * @return counter dalam bentuk integer yang sesuai dengan predikat
     * @param <T> generic untuk menerima bentuk apapun
     */
    public static <T> int count(Iterable<T> iterable, T value){
        final Iterator<T> it = iterable.iterator();
        return count(it, value);
    }

    /**
     *
     * @param iterable param dalam bentuk iterable
     * @param pred predikat yang akan dicek
     * @return counter dalam bentuk integer yang sesuai dengan predikat
     * @param <T> generic untuk menerima bentuk apapun
     */
    public static <T> int count (Iterable<T> iterable, Predicate<T> pred){
        final Iterator<T> it = iterable.iterator();
        return count(it, pred);
    }

    /**
     *
     * @param iterator param dalam bentuk iterator
     * @param value nilai yang akan dicek
     * @return mencari bentuk generic yang diberikan dengan suatu predikat
     * @param <T> generic untuk menerima bentuk apapun
     */
    public static <T> T find(Iterator<T> iterator, T value)
    {
        final Predicate<T> pred = value::equals;
        return find(iterator, pred);
    }

    /**
     *
     * @param array param dalam bentuk array
     * @param value nilai yang akan dicek
     * @return mencari bentuk generic yang diberikan dengan suatu predikat
     * @param <T> generic untuk menerima bentuk apapun
     */
    public static <T> T find(T[] array, T value)
    {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return find(it, value);
    }

    /**
     *
     * @param iterator param dalam bentuk iterator
     * @param pred predikat yang akan dicek
     * @return mencari bentuk generic yang diberikan dengan suatu predikat
     * @param <T> generic untuk menerima bentuk apapun
     */
    public static <T> T find(Iterator<T> iterator, Predicate<T> pred){
        while(iterator.hasNext()){
            T current  = iterator.next();
            if(pred.predicate(current)){
                return current;
            }
        }
        return null;
    }

    /**
     *
     * @param array param dalam bentuk array
     * @param pred predikat yang akan dicek
     * @return mencari bentuk generic yang diberikan dengan suatu predikat
     * @param <T> generic untuk menerima bentuk apapun
     */
    public static <T> T find(T[] array, Predicate<T> pred)
    {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return find(it, pred);
    }

    /**
     *
     * @param iterable param dalam bentuk iterable
     * @param value nilai yang akan dicek
     * @return mencari bentuk generic yang diberikan dengan suatu predikat
     * @param <T> generic untuk menerima bentuk apapun
     */
    public static <T> T find(Iterable<T> iterable, T value){
        final Iterator<T> it = iterable.iterator();
        return find(it, value);
    }

    /**
     *
     * @param iterable param dalam bentuk iterable
     * @param pred predikat yang akan dicek
     * @return mencari bentuk generic yang diberikan dengan suatu predikat
     * @param <T> generic untuk menerima bentuk apapun
     */
    public static <T> T find (Iterable<T> iterable, Predicate<T> pred){
        final Iterator<T> it = iterable.iterator();
        return find(it, pred);
    }

    /**
     *
     * @param iterator param dalam bentuk iterator
     * @param value nilai yang akan dicek
     * @return List yang berisi objek dari generic yang sesuai dengan predikat/nilai
     * @param <T> generic untuk menerima bentuk apapun
     */
    public static <T> List<T> collect(Iterator<T> iterator, T value)
    {
        final Predicate<T> pred = value::equals;
        return collect(iterator, pred);
    }

    /**
     *
     * @param array param dalam bentuk array
     * @param value nilai yang akan dicek
     * @return List yang berisi objek dari generic yang sesuai dengan predikat/nilai
     * @param <T> generic untuk menerima bentuk apapun
     */
    public static <T> List<T> collect(T[] array, T value)
    {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return collect(it, value);
    }

    /**
     *
     * @param iterator param dalam bentuk iterator
     * @param pred predikat yang akan dicek
     * @return List yang berisi objek dari generic yang sesuai dengan predikat/nilai
     * @param <T> generic untuk menerima bentuk apapun
     */
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

    /**
     *
     * @param array param dalam bentuk array
     * @param pred predikat yang akan dicek
     * @return List yang berisi objek dari generic yang sesuai dengan predikat/nilai
     * @param <T> generic untuk menerima bentuk apapun
     */
    public static <T> List<T> collect(T[] array, Predicate<T> pred)
    {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return collect(it, pred);
    }

    /**
     *
     * @param iterable param dalam bentuk iterable
     * @param value nilai yang akan dicek
     * @return List yang berisi objek dari generic yang sesuai dengan predikat/nilai
     * @param <T> generic untuk menerima bentuk apapun
     */
    public static <T> List<T> collect(Iterable<T> iterable, T value){
        final Iterator<T> it = iterable.iterator();
        return collect(it, value);
    }

    /**
     *
     * @param iterable param dalam bentuk iterable
     * @param pred predikat yang akan dicek
     * @return List yang berisi objek dari generic yang sesuai dengan predikat/nilai
     * @param <T> generic untuk menerima bentuk apapun
     */
    public static <T> List<T> collect (Iterable<T> iterable, Predicate<T> pred){
        final Iterator<T> it = iterable.iterator();
        return collect(it, pred);
    }

    /**
     *
     * @param array param dalam bentuk array
     * @param page lokasi page keberapa
     * @param pagesize besar isi suatu page
     * @param pred predikat dari page
     * @return List objek dari generic yang diberikan yang sudah dibuat menjadi page
     * @param <T> generic untuk menerima bentuk apapun
     */
    public static <T> List<T> paginate (T[] array, int page, int pagesize, Predicate<T> pred){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return paginate(it, page, pagesize, pred);
    }

    /**
     *
     * @param iterable param dalam bentuk iterable
     * @param page lokasi page keberapa
     * @param pagesize besar isi suatu page
     * @param pred predikat dari page
     * @return List objek dari generic yang diberikan yang sudah dibuat menjadi page
     * @param <T> generic untuk menerima bentuk apapun
     */
    public static <T> List<T> paginate(Iterable<T> iterable, int page, int pagesize, Predicate<T> pred)
    {
        final Iterator<T> it = iterable.iterator();
        return paginate(it, page, pagesize, pred);
    }

    /**
     *
     * @param iterator param dalam bentuk iterator
     * @param page lokasi page keberapa
     * @param pagesize besar isi suatu page
     * @param pred predikat dari page
     * @return List objek dari generic yang diberikan yang sudah dibuat menjadi page
     * @param <T> generic untuk menerima bentuk apapun
     */
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
