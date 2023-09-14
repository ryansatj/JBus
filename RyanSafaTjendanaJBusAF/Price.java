package RyanSafaTjendanaJBusAF;

public class Price
{
    double price;
    int discount;
    double rebate;
    
    public Price(double price)
    {
        this.price = price;
        this.discount = 0;
        this.rebate = 0;
    }
    
    public Price(double price, int discount)
    {
        this.price = price;
        this.discount = discount;
        this.rebate = 0;
    }
    
    public Price(double price, double rebate)
    {
        this.price = price;
        this.rebate = rebate;
        this.discount = 0;
    }

}