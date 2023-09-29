package RyanSafaTjendanaJBusAF;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Payment extends Invoice
{
    private int busId;
    public Calendar departureDate;
    public String busSeat;
    
    public Payment(int id, int buyerId, int renterId, int busId, String busSeat)
    {
        super(id, buyerId, renterId);
        this.busId = busId;
        this.departureDate = Calendar.getInstance();
        this.departureDate.add(Calendar.DATE, +2);
        this.busSeat = busSeat;
    }
    
    public Payment(int id, Account buyer, Renter renter, int busId, String busSeat){
        super(id, buyer, renter);
        this.busId = busId;
        this.departureDate = Calendar.getInstance();
        this.departureDate.add(Calendar.DATE, +2);
        this.busSeat = busSeat;
    }
    
    public String getDepartureInfo(){
        SimpleDateFormat SDF = new SimpleDateFormat("MMMM dd, yyyy hh:mm:ss");
        String tanggal = SDF.format(this.departureDate.getTime());
        return super.id +  ", " + super.buyerId + ", " + super.renterId + ", " + this.busId + ", " + this.departureDate + ", " + this.busSeat + ", " + tanggal;
    }
    
    public int getBusId(){
        return this.busId;
    }
    
    public String getTime(){
        SimpleDateFormat SDF = new SimpleDateFormat("MMMM dd, yyyy hh:mm:ss");
        String tanggal = SDF.format(this.departureDate.getTime());
        return tanggal;
    }

}
