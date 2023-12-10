package com.RyanSafaTjendanaJBusAF;

/**
 * interface ini ketika diaplikasikan maka harus memiliki suatu predikat dalam bentuk boolean
 *
 * @author Ryan Safa Tjendana
 * @version 1.0
 * @param <T> generic untuk menerima objek apapun
 */
public interface Predicate <T> {
    public boolean predicate(T t);
}
