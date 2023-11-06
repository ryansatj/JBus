package RyanSafaTjendanaJBusAF;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
public class Payment extends Invoice
{
    private int busId;
    public Timestamp departureDate;
    public String busSeat;
    
    public Payment(int id, int buyerId, int renterId, int busId, String busSeat, Timestamp departureDate)
    {
        super(id, buyerId, renterId);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }
    
    public Payment(int id, Account buyer, Renter renter, int busId, String busSeat, Timestamp departureDate){
        super(id, buyer, renter);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }
    
    public String getDepartureInfo(){
        SimpleDateFormat SDF = new SimpleDateFormat("MMMM dd, yyyy hh:mm:ss");
        String tanggal = SDF.format(this.departureDate.getTime());
        return super.id +  ", " + super.buyerId + ", " + super.renterId + ", " + this.busId  + ", " + this.busSeat + ", " + tanggal;
    }
    
    public int getBusId(){
        return this.busId;
    }
    
    public String getTime(){
        SimpleDateFormat SDF = new SimpleDateFormat("MMMM dd, yyyy hh:mm:ss");
        String tanggal = SDF.format(super.time.getTime());
        return tanggal;
    }
    
    /*public static boolean isAvailable(Timestamp departureSchedule, String seat, Bus bus)
    {
        for(Schedule jadwal : bus.schedules)
        {
            if(jadwal.isSeatAvailable(seat) && jadwal.departureSchedule.equals(departureSchedule))
            {
                  return true;   
            }
        }
        return false;
    }*/
    
    public static boolean makeBooking(Timestamp departureSchedule, String seat, Bus bus)
    {
        for(Schedule jadwal : bus.schedules)
        {
            if(jadwal.departureSchedule.equals(departureSchedule) && jadwal.isSeatAvailable(seat))
            {
                jadwal.bookSeat(seat);
                return true;
            }
        }
        return false;
    }

    public static Schedule availableSchedule(Timestamp date, String seat, Bus bus)
    {
        for(Schedule schedule : bus.schedules) {
            if (schedule.departureSchedule.equals(date) && schedule.isSeatAvailable(seat))
                return schedule;
        }
        return null;
    }
    public static Schedule availableSchedule(Timestamp date, List<String> list, Bus bus)
    {
        int c = 0;
        for(Schedule schedule : bus.schedules) {
            for (int i = 0; i < list.size(); i++) {
                if (schedule.departureSchedule.equals(date) && schedule.isSeatAvailable(list.get(i)))
                    c++;
            }
            if(c == list.size())
            {
                return schedule;
            }
        }
        return null;
    }

    public static boolean makeBooking(Timestamp date, List<String> list, Bus bus) {
        for (Schedule schedule : bus.schedules) {
            if (schedule.departureSchedule.equals(date)) {
                for (int i = 0; i < list.size(); i++) {
                    schedule.bookSeat(list.get(i));
                }
                return true;
            }
        }
        return false;
    }
}
