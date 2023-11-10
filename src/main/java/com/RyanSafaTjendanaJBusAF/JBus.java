package com.RyanSafaTjendanaJBusAF;
import java.sql.Timestamp;
import java.util.List;
import java.lang.Thread;
//import com.google.gson.*;
//import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

import com.RyanSafaTjendanaJBusAF.dbjson.JsonDBEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * CS 1
 * Ryan Safa Tjendana - 2206826835
 */

@SpringBootApplication
public class JBus
{
    public static void main(String args[]) throws InterruptedException {
        JsonDBEngine.Run(JBus.class);
        SpringApplication.run(JBus.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(()->JsonDBEngine.join()));

        //"C:\\Users\\Ryan\\Documents\\KULIAH\\Java\\Praktikum\\JBus\\data\\buses.json";
        /*try {
            String filepath = "C:\\Users\\Ryan\\Documents\\KULIAH\\Java\\Praktikum\\JBus\\data\\accountDatabase.json";
            JsonTable<Account> AccList = new JsonTable<>(Account.class, filepath);
            Account bush = new Account("Ryan", "ryansafa@gmail.com", "gYaj1akk");
            AccList.add(bush);
            JsonTable.writeJson(AccList, filepath);
            AccList.forEach(System.out::println);
        }
        catch (Throwable t){
            t.printStackTrace();
        }

        Bus bus = createBus();
        bus.schedules.forEach(Schedule::printSchedule);
        for(int i =0; i < 10; i++){
            BookingThread thread = new BookingThread("Thread " + i,bus,
                    Timestamp.valueOf("2023-07-27 19:00:00"));
        }
        Thread.sleep(1000);
        bus.schedules.forEach(Schedule::printSchedule);
        */
        /*Bus b = createBus();
        List<Timestamp> listOfSchedules = new ArrayList<>();
        listOfSchedules.add(Timestamp.valueOf("2023-7-18 15:00:00"));
        listOfSchedules.add(Timestamp.valueOf("2023-7-20 12:00:00"));
        listOfSchedules.add(Timestamp.valueOf("2023-7-22 10:00:00"));
        listOfSchedules.add(Timestamp.valueOf("2023-7-26 12:00:00"));

        listOfSchedules.forEach(b::addSchedule);
        System.out.println("Page 1");
        Algorithm.paginate(b.schedules, 0, 3, t -> true).forEach(System.out::println);
        System.out.println("========\=============================================");
        System.out.println("Page 2");
        Algorithm.paginate(b.schedules, 1, 3, t -> true).forEach(System.out::println);
        System.out.println("=====================================================");

        // Tes Booking
        String msgSuccess = "Booking Success!";
        String msgFailed = "Booking Failed";
        // valid date, invalid seat = Booking Failed
        Timestamp t1 = Timestamp.valueOf("2023-7-19 15:00:00");
        System.out.println("\nMake booking at July 19, 2023 15:00:00 Seats: AF17 AF18");
        System.out.println(Payment.makeBooking(t1, List.of("AF17", "AF18"), b)? msgSuccess : msgFailed);
        // valid date, invalid seat = Booking Failed
        Timestamp t2 = Timestamp.valueOf("2023-7-18 15:00:00");
        System.out.println("Make booking at July 18, 2023 15:00:00 Seat AF26");
        System.out.println(Payment.makeBooking(t2, "AF26", b)? msgSuccess : msgFailed);
        // valid date, valid seat = Booking Success
        System.out.println("Make booking at July 18, 2023 15:00:00 Seats: S7 S8");
        System.out.println(Payment.makeBooking(t2, List.of("AF7", "AF8"), b)? msgSuccess : msgFailed);
        // valid date, valid seat = Booking Success
        Timestamp t3 = Timestamp.valueOf("2023-7-20 12:00:00");
        System.out.println("Make booking at July 20, 2023 12:00:00 Seats: S1 S2");
        System.out.println(Payment.makeBooking(t3, List.of("AF1", "AF2"), b)? msgSuccess : msgFailed);
        // valid date, book the same seat = Booking Failed
        System.out.println("Make booking at July 20, 2023 12:00:00 Seat S1");
        System.out.println(Payment.makeBooking(t3, "AF1", b)? msgSuccess : msgFailed);
        // check if the data changed
        System.out.println("\nUpdated Schedule");
        Algorithm.paginate(b.schedules, 0, 4, t-> true).forEach(System.out::println);*/
        /*Integer[] numbers = {18, 10, 22, 43, 18, 67, 12, 11, 88, 22, 18};
        System.out.println("Number "+Arrays.toString(numbers));

        // Tes Algorithm
        System.out.print("1. ");
        testCount(numbers);
        System.out.print("2. ");
        testFind(numbers);
        System.out.print("3. ");
        testExist(numbers);
        System.out.println("4. Filtering");
        testCollect(numbers);*/
    }

    public static List<Bus> filterByDeparture(List<Bus> buses, City departure, int page, int pagesize)
    {
        List<Bus> FirstList = buses;

        List<Bus> list = new ArrayList<Bus>();
        for(Bus bus : FirstList)
        {
            if(bus.departure.city.equals(departure)){
                list.add(bus);
            }
        }
        return Algorithm.paginate(list, page, pagesize, t -> true);
    }

    public static List<Bus> filterByPrice(List<Bus> buses, int min, int max)
    {
        List<Bus> FirstList = buses;

        List<Bus> list = new ArrayList<Bus>();
        for(Bus bus : FirstList)
        {
            if(bus.price.price >= min && bus.price.price <= max){
                list.add(bus);
            }
        }
        return list;
    }
    public static Bus filterBusId(List<Bus> buses, int id)
    {
        List<Bus> FirstList = buses;

        for(Bus bus : FirstList)
        {
            if(bus.id == id){
                return bus;
            }
        }
        return null;
    }

    public static List<Bus> filterByDepartureAndArrival(List<Bus> buses, City departure, City arrival, int page, int pagesize)
    {
        List<Bus> FirstList = buses;
        List<Bus> list = new ArrayList<Bus>();

        for(Bus bus : FirstList)
        {
            if(bus.departure.city.equals(departure) && bus.arrival.city.equals(arrival)){
                list.add(bus);
            }
        }
        return Algorithm.paginate(list, page, pagesize, t->true);
    }
    private static void testExist(Integer[] t) {
        int valueToCheck = 67;
        boolean result3 = Algorithm.exists(t, valueToCheck);
        if (result3) {
            System.out.println(valueToCheck + " exist in the array.");
        } else {
            System.out.println(valueToCheck + " doesn't exists in the array.");
        }
    }
    public static void testCount(Integer[] t) {
        int valueToCount = 18;
        int result = Algorithm.count(t, valueToCount);
        System.out.println("Number " + valueToCount + " appears " + result + " times");
    }
    public static void testFind(Integer[] t) {
        Integer valueToFind = 69;
        Integer result2 = Algorithm.find(t, valueToFind);
        System.out.print("Finding " + valueToFind + " inside the array : ");
        if (result2 != null) {
            System.out.println("Found!" + result2);
        } else {
            System.out.println("Not Found");
        }
    }
    private static void testCollect(Integer[] t) {
        Predicate<Integer> below = (val)->val<=22;
        Predicate<Integer> above = (val)->val>43;

        List<Integer> integerBelow = Algorithm.collect(t, below);
        List<Integer> integerAbove = Algorithm.collect(t, above);

        System.out.println("Below 22");
        System.out.println(integerBelow);
        System.out.println("Above 43");
        System.out.println(integerAbove);

        /*Bus bus1 = createBus();
        Bus bus2 = createBus();
        Bus bus3 = createBus();
        Bus bus4 = createBus();
        Bus bus5 = createBus();
        System.out.println(bus1);
        System.out.println(bus2);
        System.out.println(bus3);
        System.out.println(bus4);
        System.out.println(bus5);

        Integer[] numbers = {10, 20, 30, 40, 50};
        int valueToCheck = 30;
        boolean result = Algorithm.exists(numbers, valueToCheck);
        if (result) {
            System.out.println(valueToCheck + " terdapat dalam array.");
        } else {
            System.out.println(valueToCheck + " tidak terdapat dalam array.");
        }
        Bus b = createBus();
        // Payment
        Timestamp schedule1 = Timestamp.valueOf("2023-7-18 15:00:00");
        Timestamp schedule2 = Timestamp.valueOf("2023-7-20 12:00:00");

        b.addSchedule(schedule1);
        b.addSchedule(schedule2);

        b.schedules.forEach(Schedule :: printSchedule);

        Timestamp t1 = Timestamp.valueOf("2023-7-19 15:00:00");
        System.out.println("Make booking at July 19, 2023 15:00:00 Seat AF12");
        System.out.println(Payment.makeBooking(t1, "AF12", b));
        
        Timestamp t2 = Timestamp.valueOf("2023-7-18 15:00:00");
        System.out.println("\nMake booking at July 19, 2023 15:00:00 Seat AF20");
        System.out.println(Payment.makeBooking(t2, "AF20", b));
        
        System.out.println("\nMake booking at July 18, 2023 15:00:00 Seat AF07");
        System.out.println(Payment.makeBooking(t2, "AF07", b));
        
        Timestamp t3 = Timestamp.valueOf("2023-7-20 12:00:00");
        System.out.println("\nMake booking at July 20, 2023 12:00:00 Seat AF01");
        System.out.println(Payment.makeBooking(t3, "AF01", b));

        System.out.println("\nMake booking at July 20, 2023 12:00:00 Seat AF01 again");
        System.out.println(Payment.makeBooking(t3, "AF01", b));

        System.out.println("\nUpdated Schedule\n");
        b.schedules.forEach(Schedule :: printSchedule);

        /Price[] unfilteredArray = new Price[5];
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
        
        Bus testBus = createBus();
        // Payment
        Payment testPayment = new Payment(1, 1, 1, testBus.id, "S1");
        System.out.println(testPayment.getDepartureInfo());
        System.out.println(testPayment.getTime());
        // Tes Schedule
        Calendar schedule1 = Calendar.getInstance();
        testBus.addSchedule(schedule1);
        Calendar schedule2 = Calendar.getInstance();
        schedule2.add(Calendar.DAY_OF_MONTH, 3);
        testBus.addSchedule(schedule2);
        for(Schedule s: testBus.schedules){
            testBus.printSchedule(s);
        }

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

        Bus testBus = createBus();
        System.out.println(testBus.name);
        System.out.println(testBus.facility);
        System.out.println(testBus.price.price);
        System.out.println(testBus.capacity);
        */
    }

    public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus("Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station("Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station("Halter UI", City.JAKARTA, "Universitas Indonesia"));
        Timestamp timestamp = Timestamp.valueOf("2023-07-27 19:00:00");
        bus.addSchedule(timestamp);
        return bus;
    }
    /*public static Bus createBus()
    {
        Price price = new Price(750000, 5);
        Bus bus = new Bus(1, "Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station(1, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station(2, "Halte UI", City.JAKARTA, "Universitas Indonesia"));
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
    */
}