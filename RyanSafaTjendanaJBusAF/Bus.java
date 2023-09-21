package RyanSafaTjendanaJBusAF;

public class Bus extends Serializable
{
    public int capacity;
    public Facility facility;
    public String name;
    public Price price;

    public Bus(int id, String name, Facility facility, Price price, int capacity)
    {
        super(id);
        this.name = name;
        this.facility = facility;
        this.price = price;
        this.capacity = capacity;
    }
}

