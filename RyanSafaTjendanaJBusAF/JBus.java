package RyanSafaTjendanaJBusAF;

/**
 * CS 1
 * Ryan Safa Tjendana - 2206826835
 */


public class JBus
{

    
    public static void main(String args[])
    {
        Price[] unfilteredArray = new Price[5];
        for(int i = 0; i < unfilteredArray.length; i++){
            int j = 5000;
            unfilteredArray[i] = new Price((i+1)*j);
        }
        System.out.println("Price List");
        for(Price price : unfilteredArray){
            System.out.println(price.price);
        }
        System.out.println("Below 12000.0");
        System.out.println(Validate.filter(unfilteredArray, 12000, true));
        System.out.println("Above 10000.0");
        System.out.println(Validate.filter(unfilteredArray, 10000, false));
        
        /** 
        Review testReview = new Review(1, "23 August 2023", "Bad Quality");
        Price testPrice = new Price(100000, 20000);
        Station testDeparture = new Station(2, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya");
        Station testArrival = new Station(3, "Halte UI", City.JAKARTA, "Universitas Indonesia");
        Bus testBus = new Bus(1, "Busway", Facility.AC, testPrice, 50, BusType.REGULER, City.DEPOK, testDeparture, testArrival);
        Account testAccount = new Account (1, "Bob", "bob@gmail.com", "bob");
        Rating testRating = new Rating();
        System.out.println(testReview);
        System.out.println(testBus);
        System.out.println(testAccount);
        System.out.println(testPrice);
        System.out.println(testRating);
        **/
        
        
        /**
        Bus testBus = createBus();
        System.out.println(testBus.name);
        System.out.println(testBus.facility);
        System.out.println(testBus.price.price);
        System.out.println(testBus.capacity);
        **/
    }
    
    /**
    public static Bus createBus()
    {
        Price price = new Price(750000, 5);
        Bus bus = new Bus("Netlab Bus", Facility.LUNCH, price, 25);
        return bus;
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
    **/
}