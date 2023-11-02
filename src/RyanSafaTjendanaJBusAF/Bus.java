package RyanSafaTjendanaJBusAF;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Timestamp;

public class Bus extends Serializable
{
    public int capacity;
    public Facility facility;
    public String name;
    public Price price;
    public City city;
    public Station departure;
    public Station arrival;
    public BusType busType;
    ArrayList <Schedule> schedules;
    

    public Bus(int id, String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival)
    {
        super();
        this.name = name;
        this.facility = facility;
        this.price = price;
        this.capacity = capacity;
        this.city = city;
        this.departure = departure;
        this.arrival = arrival;
        this.busType = busType;
        this.schedules = new ArrayList<Schedule>();
    }
    
    public String toString()
    {
        return super.id + ", " + this.name + ", " + this.facility + ", " + this.price + ", " + this.capacity + ", " + this.city + ", " + this.departure + ", " + this.arrival + ", " + this.busType;
    }
    
    public void addSchedule(Timestamp calendar)
    {

        try{
            this.schedules.add(new Schedule(calendar, this.capacity));
        }catch(Exception e)
        {
            System.out.println("Error");
        }
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

