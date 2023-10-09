package RyanSafaTjendanaJBusAF;

import java.sql.Timestamp;
import java.util.Map;
import java.util.LinkedHashMap;
import java.text.SimpleDateFormat;
import java.util.List;

public class Schedule
{
    public Timestamp departureSchedule;
    public Map<String, Boolean> seatAvailability;
    
    public Schedule(Timestamp departureSchedule, int numberOfSeats)
    {
        this.departureSchedule = departureSchedule;
        initializeSeatAvailability(numberOfSeats);
    }
    
    private void initializeSeatAvailability(int numberOfSeats)
    {
        this.seatAvailability = new LinkedHashMap<String, Boolean>();
        for(int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++)
        {
            String sn = seatNumber < 10 ? "0"+ seatNumber : "" + seatNumber;
            seatAvailability.put("AF"+ sn, true);
        }
    }
    
    public boolean isSeatAvailable(String seat)
    {
        if(this.seatAvailability.get(seat))
        {
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isSeatAvailable(List<String> list)
    {
        int s = 0;
        int tempsum = 0;
        while(s < list.size()) {
            if (this.seatAvailability.get(list.get(s))) {
                tempsum++;
            }
            s++;
        }
        if (tempsum == list.size())
        {
            return true;
        }
        return false;
    }


    
    public void bookSeat (String seat)
    {
        this.seatAvailability.put(seat, false);
    }

    public void bookSeat (List<String> list)
    {
        for(int i = 0; i < list.size(); i++)
        {
            this.seatAvailability.put(list.get(i), false);
        }
    }

    public String toString ()
    {
        int availableSeat = Algorithm.count(this.seatAvailability.values().iterator(), true);
        int temptotal = Algorithm.count(this.seatAvailability.values().iterator(), false);
        int total = availableSeat + temptotal;
        SimpleDateFormat formatTanggal = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.Ms");
        return "Schedule: " + formatTanggal.format(this.departureSchedule.getTime()) + "\n" + "Occupied: " + availableSeat + "/" + total;
    }
    
    public void printSchedule() 
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule.getTime());
        // Print tanggal keberangkatan
        System.out.println("Tanggal keberangkatan: " + formattedDepartureSchedule);
        // Print daftar kursi dan ketersediaan kursinya
        System.out.println("Daftar kursi dan ketersediaan kursinya: ");
        // Create a list of seats and sort them numerically
        int maxSeatsPerRow = 4; 
        // Assuming there are 4 seats per row
        int currentSeat = 1;
        for (String seat : this.seatAvailability.keySet()) 
        {
            String symbol = this.seatAvailability.get(seat)? "O" : "X";
            System.out.print(seat + " : " + symbol + "\t");
            if (currentSeat % maxSeatsPerRow == 0) 
            {
            System.out.println();
            }
            currentSeat++;
        }
        System.out.println("\n");
    }
    
}
