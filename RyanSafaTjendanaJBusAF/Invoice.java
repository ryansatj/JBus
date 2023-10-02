package RyanSafaTjendanaJBusAF;

import java.sql.Timestamp;

public class Invoice extends Serializable
{
    public Timestamp time;
    public int buyerId;
    public int renterId;
    public BusRating rating;
    public PaymentStatus status;
    
    protected Invoice(int id, int buyerId, int renterId)
    {
        super(id);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.rating = rating.NONE;
        this.status = status.WAITING;
        this.time = Timestamp.from(java.time.Instant.now());
    }
    
    public Invoice(int id, Account buyer, Renter renter)
    {
        super(id);
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.rating = rating.NONE;
        this.status = status.WAITING;
        this.time = Timestamp.from(java.time.Instant.now());
    }
    
    public String toString()
    {
        return super.id + ", " + this.time + ", " + buyerId + ", " + renterId; 
    }


    public enum BusRating
    {
        NONE, NEUTRAL, GOOD, BAD
    }
    
    public enum PaymentStatus
    {
        FAILED, WAITING, SUCCESS
    }
    
}

