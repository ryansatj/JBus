package RyanSafaTjendanaJBusAF;

public class Price
{
    public double price;
    public int discount;
    public double rebate;
    
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
    
    private double getDiscountedPrice()
    {
        if (this.discount >= 100)
        {
            return 0.0d;
        }
        
        return this.price - this.price * ((100 - this.discount)/100);
    }
    
    private double getRebatedPrice()
    {
         
         if(this.rebate > this.price){
             return 0.0d;
         }
         
         return this.price - this.rebate;
    }

}