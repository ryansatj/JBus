package RyanSafaTjendanaJbusAF;


/**
 * CS 1
 * Ryan Safa Tjendana - 2206826835
 */
public class Jbus
{

    public static void main(String args[])
    {
        
    }
    
    public static int getBusId()
    {
        return 0;
    }
    
    public static String getBusName()
    {
        return "Bus";
    }
    
    public static boolean isDiscount()
    {
        return true;
    }

    public static float getDiscountPercentage(int beforeDiscount, int afterDiscount)
    {
        if(beforeDiscount > afterDiscount)
        {
            return 0.0f;
        }
        
        return (beforeDiscount - afterDiscount)/beforeDiscount * 100;
    }
    
    public static int getDiscountedPrice(int price, float discountPercentage)
    {
        if (discountPercentage >= 100.0f)
        {
            return 0;
        }
        
        float a = price - (price * discountPercentage)/100;
        int ia = (int) a;
        return ia;
    }
    
    public static int getOriginalPrice(int discountedPrice, float discountPercentage)
    {
        float a = discountedPrice * (100/(100 - discountPercentage));
        int ia = (int) a;
        return ia;
    }
    
    public static float getAdminFeePercentage()
    {
        return 0.05f;
    }
    
    public static int getAdminFee(int price)
    {
        float fee = getAdminFeePercentage();
        float a = price * fee;
        return (int) a;
    }
    
    public static int getTotalPrice(int price, int numberOfSeat)
    {
        int a = (price * numberOfSeat);
        int total = getAdminFee(a);
        return total;
    }
}
