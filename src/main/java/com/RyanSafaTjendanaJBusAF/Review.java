package com.RyanSafaTjendanaJBusAF;

import com.RyanSafaTjendanaJBusAF.dbjson.Serializable;

/**
 * Class untuk merepresentasikan objek Review yang dapat diserialisasi.
 * Extends class Serializable untuk mendukung serialisasi objek.
 *
 * @see Serializable
 * @author Ryan Safa
 * @version 1.0
 */
public class Review extends Serializable
{
    /**
     * Tanggal penulisan review
     */
    public String date;
    /**
     * Deskripsi dari review
     */
    public String desc;

    /**
     * Konstruktor untuk objek Review dengan ID, tanggal, dan deskripsi.
     *
     * @param id   ID review
     * @param date tanggal penulisan review
     * @param desc deskripsi dari review
     */
    public Review(int id, String date, String desc)
    {
        super();
        this.date = date;
        this.desc = desc;
    }

    /**
     * Representasi string dari objek Review.
     *
     * @return String yang berisi ID, tanggal, dan deskripsi review
     */
    public String toString(){
        return super.id + ", " + this.date + ", " + this.desc;
    }
}
