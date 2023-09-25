package RyanSafaTjendanaJBusAF;

public class Station extends Serializable
{
    public City city;
    public String stationName;
    public String address;
    
    public Station(int id, String stationName, City city, String address)
    {
        super(id);
        this.stationName = stationName;
        this.city = city;
        this.address = address;
        
    }
    
    public String toString(){
        return super.id + ", " + this.stationName + ", " + this.city + ", " + this.address;
    }
}
