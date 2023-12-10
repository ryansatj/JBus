package com.RyanSafaTjendanaJBusAF;

import com.RyanSafaTjendanaJBusAF.dbjson.Serializable;

import java.sql.Timestamp;

/**
 * Class Invoice untuk membuat objek Invoice
 *
 * @author Ryan Safa T
 * @version 1.0
 */
public class Invoice extends Serializable
{
    /**
     * field waktu yang akan mengisi waktu invoice dibuat
     */
    public Timestamp time;
    /**
     * field yang akan menampung id dari akun buyer
     */
    public int buyerId;
    /**
     * field yang akan menampung id dari renter bus
     */
    public int renterId;
    /**
     * field yang akan memberikan rating dari bus dalam bentuk enum
     */
    public BusRating rating;
    /**
     * field status pembayaran yang akan dalam bentuk enum
     */
    public PaymentStatus status;

    /**
     * Constructor ini dibuat untuk membuat invoice dengan parameter buyer id dan renter id
     *
     * @param buyerId parameter ini akan mengambil id dari buyer
     * @param renterId parameter ini akan mengambil id dari renter
     */
    protected Invoice(int buyerId, int renterId)
    {
        super();
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.rating = rating.NONE;
        this.status = status.WAITING;
        this.time = Timestamp.from(java.time.Instant.now());
    }

    /**
     * Constructor ini dibuat untuk membuat invoice dengan parameter buyer dan renter dalam bentuk class
     *
     * @param buyer paramater ini akan mengambil buyer dalam bentuk objek Account
     * @param renter parameter ini akan mengambil renter dalam bentuk objek Renter
     */
    public Invoice(Account buyer, Renter renter)
    {
        super();
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.rating = rating.NONE;
        this.status = status.WAITING;
        this.time = Timestamp.from(java.time.Instant.now());
    }

    /**
     *
     * @return akan mereturn class ini dalam bentuk String
     */
    public String toString()
    {
        return super.id + ", " + this.time + ", " + buyerId + ", " + renterId; 
    }


    /**
     * Enum untuk Bus Rating
     */
    public enum BusRating
    {
        NONE, NEUTRAL, GOOD, BAD
    }

    /**
     * Enum untuk Payment Status
     */
    public enum PaymentStatus
    {
        FAILED, WAITING, SUCCESS
    }
    
}

