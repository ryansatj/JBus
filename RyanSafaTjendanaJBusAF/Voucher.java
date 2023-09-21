package RyanSafaTjendanaJBusAF;

public class Voucher extends Serializable
{
    public String name;
    private boolean used;
    public double minimum;
    public double cut;
    public int code;
    public Type type;
    
    public Voucher(int id, String name, int code, Type type, double minimum, double cut)
    {
        super(id);
        this.name = name;
        this.code = code;
        this.type = type;
        this.minimum = minimum;
        this.cut = cut;
        this.used = false;
    }
    
    public boolean isUsed (){
        return this.used;
    }
    
    public boolean canApply(Price price)
    {
        if (this.used == false && price.price > minimum)
        {
            return true;
        }
        return false;
    }
    
    public double apply(Price price){
        this.used = true;
        if(this.type == Type.DISCOUNT){
            if(this.cut >= 100 || this.cut < 0){
                return 0.0d;
            }
            else{
                return price.price - price.price * this.cut/100;
            }
        }
        
        else if(this.type == Type.REBATE){
            if(this.cut > price.price){
                return 0.0d;
            }
            else{
                return price.price - this.cut;
            }
        }
        
        return price.price;
    }
    
}
