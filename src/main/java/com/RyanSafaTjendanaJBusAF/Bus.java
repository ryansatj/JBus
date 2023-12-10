package com.RyanSafaTjendanaJBusAF;
import com.RyanSafaTjendanaJBusAF.dbjson.Serializable;

import java.sql.Time;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.List;

/**
 * Class Bus yang akan membuat suatu Objek bus yang berisi detail detail tentang bus dan Class ini mengextend Serializable yang akan memberikan id kepada Objek Bus yang terbuat
 *
 * @author Ryan Safa T
 * @version 1.0
 */
public class Bus extends Serializable
{
    /**
     * field capacity untuk kapasitas bus
     */
    public int capacity;
    /**
     * List yang akan berisi fasilitas-fasilitas dari suatu bus
     */
    public List<Facility> facilities;
    /**
     * field nama bus
     */
    public String name;
    /**
     * field yang berisi objek Price dalam bus
     */
    public Price price;
    /**
     * field yang berisi Objek Station dalam bus untuk keberangkatan
     */
    public Station departure;
    /**
     * field yang berisi Objek Station dalam bus untuk kedatangan
     */
    public Station arrival;
    /**
     * field yang berisi enum berupa tipe Bus
     */
    public BusType busType;
    /**
     * field ArrayList dari jadwal jadwal keberangkatan yang akan berbentuk dalam list objek Schedule
     */
    public ArrayList <Schedule> schedules;
    /**
     * field account id yang memiliki bus
     */
    public int accountId;

    /**
     *
     * @param name nama bus dalam bentuk String
     * @param facilities fasilitas- fasilitas bus berbentuk enum FACILITY
     * @param price harga bus dalam bentuk Price
     * @param capacity kapasitas bus dalam bentuk int
     * @param busType tipe bus dalam bentuk enum BusType
     * @param departure kedatangan bus dalam bentuk objek Station
     * @param arrival kedatangan bus dalam bentuk objek Station
     */
    public Bus(String name, List<Facility> facilities, Price price, int capacity, BusType busType, Station departure, Station arrival)
    {
        super();
        this.name = name;
        this.facilities = facilities;
        this.price = price;
        this.capacity = capacity;
        this.departure = departure;
        this.arrival = arrival;
        this.busType = busType;
        this.schedules = new ArrayList<Schedule>();
    }

    /**
     *
     * @return mereturn objek Class ini kedalam String
     */
    public String toString()
    {
        return super.id + ", " + this.name + ", " + this.facilities + ", " + this.price + ", " + this.capacity  + ", " + this.departure + ", " + this.arrival + ", " + this.busType;
    }

    /**
     * method ini digunakan untuk menambahkan objek schedule kedalam bus
     *
     * @param calendar parameter kalender dalam bentuk Timestamp
     */
    public void addSchedule(Timestamp calendar)
    {
        for(Schedule schedule : schedules){
            if(schedule.departureSchedule == calendar){
                throw new IllegalArgumentException("Schedule with the same timestamp already exists.");
            }
        }
        this.schedules.add(new Schedule(calendar, this.capacity));
    }
}

