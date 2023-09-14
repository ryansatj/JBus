package RyanSafaTjendanaJBusAF;

public class Price
{
    double price;
    int discount;
    double rebate;
    
    public Price(double price)
    {
        discount = 0;
        rebate = 0;
    }
    
    public Price(double price, int discount)
    {
        rebate = 0;
    }
    
    public Price(double price, double rebate)
    {
        discount = 0;
    }

}