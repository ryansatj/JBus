package RyanSafaTjendanaJBusAF;

public class Payment extends Invoice
{
    private int busId;
    public String depatureDate;
    public String busSeat;
    
    public Payment(int id, int buyerId, int renterId, String time, int busId, String depatureDate, String busSeat)
    {
        super(id, buyerId, renterId, time);
        this.busId = busId;
        this.depatureDate = depatureDate;
        this.busSeat = busSeat;
    }
    
    public Payment(int id, Account buyer, Renter renter, String time, int busId, String departureDate, String busSeat){
        super(id, buyer, renter, time);
        this.busId = busId;
        this.depatureDate = depatureDate;
        this.busSeat = busSeat;
    }
    
    public String print(){
        return super.id + ", " + super.time + ", " + super.buyerId + ", " + super.renterId + ", " + this.busId + ", " + this.depatureDate + ", " + this.busSeat;
    }
    
    public int getBusId(){
        return this.busId;
    }

}
