package com.RyanSafaTjendanaJBusAF;

import com.RyanSafaTjendanaJBusAF.dbjson.Serializable;

/**
 * Class untuk merepresentasikan stasiun dalam sistem perjalanan bus.
 * Extends class Serializable untuk mendukung serialisasi objek.
 *
 * @see Serializable
 * @author Ryan Safa
 * @version 1.0
 */

public class Station extends Serializable
{
    /**
     * Kota tempat stasiun berada
     */
    public City city;
    /**
     * Nama stasiun
     */
    public String stationName;
    /**
     * Alamat stasiun
     */
    public String address;

    /**
     * Konstruktor untuk objek Station dengan nama stasiun, kota, dan alamat.
     *
     * @param stationName nama stasiun
     * @param city kota tempat stasiun berada
     * @param address alamat stasiun
     */
    public Station(String stationName, City city, String address)
    {
        super();
        this.stationName = stationName;
        this.city = city;
        this.address = address;
        
    }

    /**
     * Representasi string dari objek Station.
     *
     * @return String yang berisi ID, nama stasiun, kota, dan alamat
     */
    public String toString(){
        return super.id + ", " + this.stationName + ", " + this.city + ", " + this.address;
    }
}
