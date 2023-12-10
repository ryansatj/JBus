package com.RyanSafaTjendanaJBusAF.dbjson;

import java.util.HashMap;

/**
 * Class dasar untuk objek yang mendukung serialisasi.
 * Implementasi antarmuka Comparable untuk memungkinkan perbandingan objek berdasarkan ID.
 * @author Ryan Safa
 * @version 1.0
 */
public class Serializable implements Comparable<Serializable> {
    /**
     * ID unik untuk setiap objek serializable.
     */
    public final int id;
    /**
     * Peta untuk menyimpan counter terakhir berdasarkan kelas objek serializable.
     */
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<Class <?>, Integer>();
    /**
     * Konstruktor untuk objek Serializable.
     * Setiap objek memiliki ID unik yang diberikan berdasarkan kelasnya.
     */
    protected Serializable(){
        Integer counter = mapCounter.get(getClass());
        counter = counter == null ? 0 : counter + 1;
        mapCounter.put(getClass(), counter);
        this.id = counter;
    }

    /**
     * Metode untuk mendapatkan ID terakhir yang diberikan untuk suatu kelas objek serializable.
     *
     * @param getter kelas objek serializable
     * @param <T>    tipe kelas objek serializable
     * @return ID terakhir yang diberikan untuk kelas tersebut
     */
    public static <T> Integer getLastAssignedId(Class<T> getter ){
        return mapCounter.get(getter);
    }

    /**
     * Metode untuk mengatur ID terakhir yang diberikan untuk suatu kelas objek serializable.
     *
     * @param setter kelas objek serializable
     * @param number nilai ID terakhir yang akan diatur
     * @param <T>    tipe kelas objek serializable
     * @return ID terakhir sebelum diatur
     */
    public static <T> Integer setLastAssignedId(Class<T> setter, int number){
        return mapCounter.put(setter, number);
    }

    /**
     * Metode perbandingan objek berdasarkan ID.
     *
     * @param temp objek serializable yang akan dibandingkan
     * @return hasil perbandingan
     */
    public int compareTo(Serializable temp){
        return ((Integer)this.id).compareTo(temp.id);
    }

    /**
     * Metode untuk menentukan apakah objek sama dengan objek lain berdasarkan ID.
     *
     * @param temp objek serializable yang akan dibandingkan
     * @return true jika ID objek sama, false jika tidak sama
     */
    public boolean equals(Serializable temp){
        return temp.id == this.id;
    }

    /**
     * Metode untuk menentukan apakah objek sama dengan objek lain berdasarkan ID.
     *
     * @param object objek yang akan dibandingkan
     * @return true jika objek merupakan instance dari Serializable dan memiliki ID yang sama, false jika tidak sama
     */
    public boolean equals(Object object){
        return object instanceof Serializable && ((Serializable) object).id == this.id;
    }
}
