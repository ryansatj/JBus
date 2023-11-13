package com.RyanSafaTjendanaJBusAF;
import com.RyanSafaTjendanaJBusAF.dbjson.Serializable;

import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.List;

public class Bus extends Serializable
{
    public int capacity;
    public List<Facility> facilities;
    public String name;
    public Price price;
    public Station departure;
    public Station arrival;
    public BusType busType;
    public ArrayList <Schedule> schedules;
    public int accountId;
    

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
    
    public String toString()
    {
        return super.id + ", " + this.name + ", " + this.facilities + ", " + this.price + ", " + this.capacity  + ", " + this.departure + ", " + this.arrival + ", " + this.busType;
    }
    
    public void addSchedule(Timestamp calendar)
    {
        for(Schedule schedule : schedules){
            if(schedule.departureSchedule == calendar){
                throw new IllegalArgumentException("Schedule with the same timestamp already exists.");
            }
        }
        this.schedules.add(new Schedule(calendar, this.capacity));
    }
    
    public void printSchedule(Schedule schedule)
    {
        System.out.println(schedule.seatAvailability);
    }
    
    public Object write(){
        return null;
    }
    public boolean read(String string){
        return false;
    }
}

