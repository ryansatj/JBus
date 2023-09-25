package RyanSafaTjendanaJBusAF;

public class Invoice extends Serializable
{
    public String time;
    public int buyerId;
    public int renterId;
    public BusRating rating;
    public PaymentStatus status;
    
    protected Invoice(int id, int buyerId, int renterId, String time)
    {
        super(id);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = time;
        this.rating = rating.NONE;
        this.status = status.WAITING;
    }
    
    public Invoice(int id, Account buyer, Renter renter, String time)
    {
        super(id);
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.time = time;
        this.rating = rating.NONE;
        this.status = status.WAITING;
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

