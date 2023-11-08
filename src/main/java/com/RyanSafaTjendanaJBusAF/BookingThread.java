package com.RyanSafaTjendanaJBusAF;

import java.sql.Timestamp;
public class BookingThread extends Thread{
    private Bus bus;
    private Timestamp timestamp ;
    public BookingThread(String name, Bus bus, Timestamp timestamp){
        super(name);
        this.bus = bus;
        this.timestamp = timestamp;
        this.start();
    }
    public void run() {
        System.out.println(
                "Thread " + Thread.currentThread().getId() + " Id : " + Thread.currentThread().getName() + " is running"
        );

        synchronized (bus)
        {
            if(Payment.makeBooking(this.timestamp,"AF01", this.bus))
            {

                System.out.println("Thread " + Thread.currentThread().getId() + " Id : " + Thread.currentThread().getName() + " Thread berhasil booking");
            }
            else {
                System.out.println("Thread " + Thread.currentThread().getId() + " Id : " + Thread.currentThread().getName() + " Thread Tidak Berhasil Booking");

            }
        }

        synchronized(this) {
            Payment.makeBooking(this.timestamp, "AF01", this.bus);
        }
    }

}

