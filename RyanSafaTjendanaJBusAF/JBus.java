package RyanSafaTjendanaJBusAF;

/**
 * CS 1
 * Ryan Safa Tjendana - 2206826835
 */

public class JBus
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
        if(beforeDiscount < afterDiscount)
        {
            return 0.0f;
        }
        
        if(beforeDiscount == 0)
        {
            return 0.0f;
        }
        
        float diskon = (beforeDiscount - afterDiscount) * 100/beforeDiscount;
        return diskon;
    }
    
    public static int getDiscountedPrice(int price, float discountPercentage)
    {
        if (discountPercentage >= 100.0f)
        {
            return 0;
        }
        
        float hargadiskon = price - (price * discountPercentage)/100;
        int hargaakhir = (int) hargadiskon;
        return hargaakhir;
    }
    
    public static int getOriginalPrice(int discountedPrice, float discountPercentage)
    {
        float original = discountedPrice * (100/(100 - discountPercentage));
        int original_int = (int) original;
        return original_int;
    }
    
    public static float getAdminFeePercentage()
    {
        return 0.05f;
    }
    
    public static int getAdminFee(int price)
    {
        float fee = getAdminFeePercentage();
        float akhir = price * fee;
        return (int) akhir;
    }
    
    public static int getTotalPrice(int price, int numberOfSeat)
    {
        int harga = (price * numberOfSeat);
        int total = getAdminFee(harga);
        int totalsemua = harga + total;
        return totalsemua;
    }
}
