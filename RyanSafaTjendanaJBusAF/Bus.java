package RyanSafaTjendanaJBusAF;

public abstract class Bus extends Serializable implements FileParser
{
    public int capacity;
    public Facility facility;
    public String name;
    public Price price;
    public City city;
    public Station departure;
    public Station arrival;
    public BusType busType;

    public Bus(int id, String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival)
    {
        super(id);
        this.name = name;
        this.facility = facility;
        this.price = price;
        this.capacity = capacity;
        this.city = city;
        this.departure = departure;
        this.arrival = arrival;
        this.busType = busType;
    }
    
    public String toString()
    {
        return super.id + ", " + this.name + ", " + this.facility + ", " + this.price + ", " + this.capacity + ", " + this.city + ", " + this.departure + ", " + this.arrival + ", " + this.busType;
    }
}

