package com.RyanSafaTjendanaJBusAF;

public class Rating
{
    private long count;
    private long total;
    
    public Rating()
    {
        this.total = 0;
        this.count = 0;
    }
    
    public void insert (int rating)
    {
        this.total = this.total + rating;
        this.count = this.count + 1;
    }
    
    public double getAverage()
    {
        if(this.count == 0)
        {
            return 0.0d;
        }
        
        return this.total/this.count;
    }
    
    public long getCount ()
    {
        return this.count;
    }
    
    public long getTotal()
    {
        return this.total;
    }
    
    public String toString()
    {
        return this.count + ", " + this.total;
    }

}
