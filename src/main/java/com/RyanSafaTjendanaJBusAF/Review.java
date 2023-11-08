package com.RyanSafaTjendanaJBusAF;

public class Review extends Serializable
{
    public String date;
    public String desc;
    
    public Review(int id, String date, String desc)
    {
        super();
        this.date = date;
        this.desc = desc;
    }
    
    public String toString(){
        return super.id + ", " + this.date + ", " + this.desc;
    }
}
