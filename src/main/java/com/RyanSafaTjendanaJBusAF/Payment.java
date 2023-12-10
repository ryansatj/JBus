package com.RyanSafaTjendanaJBusAF;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Class payment ini akan membuat objek payment dimana class ini adalah child class dari Invoice
 *
 * @author Ryan Safa Tjendana
 * @version 1.0
 */
public class Payment extends Invoice
{
    /**
     * field ini akan menyimpan bus id
     */
    private int busId;
    /**
     * field ini akan menyimpan departure date yaitu tanggal keberangkatan dalam bentuk Timestamp
     */
    public Timestamp departureDate;
    /**
     * list ini akan berisi string dari suatu list
     */
    public List<String> busSeat;

    /**
     * constructor untuk pembuatan Payment
     *
     * @param buyerId buyer id untuk memasukan ke dalam super buyer id
     * @param renterId renter id untuk memasukkan ke dalam super renter id
     * @param busId bus id untuk id bus
     * @param busSeat bus seat
     * @param departureDate parameter keberangkatan
     */
    public Payment(int buyerId, int renterId, int busId, List<String> busSeat, Timestamp departureDate)
    {
        super(buyerId, renterId);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }

    /**
     * constructor untuk pembuatan Payment
     *
     * @param buyer objek buyer untuk memanggil constructor dari parent class
     * @param renter objek renter untuk memanggil constructor dari parent class
     * @param busId param busId id bus
     * @param busSeat param busSeat untuk bangku bangku yang dipesan
     * @param departureDate param departure date untuk keberangkatan
     */
    public Payment(Account buyer, Renter renter, int busId, List<String> busSeat, Timestamp departureDate){
        super(buyer, renter);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }

    /**
     *
     * @return return info keberangkatan dalam bentuk string
     */
    public String getDepartureInfo(){
        SimpleDateFormat SDF = new SimpleDateFormat("MMMM dd, yyyy hh:mm:ss");
        String tanggal = SDF.format(this.departureDate.getTime());
        return super.id +  ", " + super.buyerId + ", " + super.renterId + ", " + this.busId  + ", " + this.busSeat + ", " + tanggal;
    }

    /**
     *
     * @return menjadi getter untuk busId
     */
    public int getBusId(){
        return this.busId;
    }

    /**
     *
     * @return mereturn waktu dari parent class
     */
    public String getTime(){
        SimpleDateFormat SDF = new SimpleDateFormat("MMMM dd, yyyy hh:mm:ss");
        String tanggal = SDF.format(super.time.getTime());
        return tanggal;
    }

    /**
     * Method untuk membuat booking
     *
     * @param departureSchedule parameter untuk menerima keberangkatan dalam bentuk Timestamp
     * @param seat parameter untuk menerima bangku
     * @param bus parameter yang akan menerima objek Bus
     * @return akan mereturn boolean true ketika berhasil membuat booking
     */
    public static boolean makeBooking(Timestamp departureSchedule, String seat, Bus bus)
    {
        for(Schedule jadwal : bus.schedules)
        {
            if(jadwal.departureSchedule.equals(departureSchedule) && jadwal.isSeatAvailable(seat))
            {
                jadwal.bookSeat(seat);
                return true;
            }
        }
        return false;
    }

    /**
     * method untuk menampilkan schedule yang tersedia
     *
     * @param date parameter tanggal dalam bentuk timestamp
     * @param seat parameter seat dalam bentuk String
     * @param bus parameter bus dalam bentuk Bus
     * @return akan mereturn schedule yang tersedia dalam bentuk Schedule
     */
    public static Schedule availableSchedule(Timestamp date, String seat, Bus bus)
    {
        for(Schedule schedule : bus.schedules) {
            if (schedule.departureSchedule.equals(date) && schedule.isSeatAvailable(seat))
                return schedule;
        }
        return null;
    }

    /**
     * method untuk menampilkan schedule yang tersedia
     *
     * @param date param ini akan menerima dalam bentuk Timestamp
     * @param list parameter list yang akan di cek dalam bentuk list dari string
     * @param bus menerima parameter dalam bentuk Bus
     * @return return schedule yang tersedia dalam bentuk Schedule
     */
    public static Schedule availableSchedule(Timestamp date, List<String> list, Bus bus)
    {
        int c = 0;
        for(Schedule schedule : bus.schedules) {
            for (int i = 0; i < list.size(); i++) {
                if (schedule.departureSchedule.equals(date) && schedule.isSeatAvailable(list.get(i)))
                    c++;
            }
            if(c == list.size())
            {
                return schedule;
            }
        }
        return null;
    }

    /**
     * method untuk make booking dari suatu schedule
     *
     * @param date tanggal yang akan diterima dalam bentuk Timestamp
     * @param list parameter dalam bentuk list String
     * @param bus paramater bus dalam bentuk objek Bus
     * @return return boolean ketika berhasil membuat booking
     */
    public static boolean makeBooking(Timestamp date, List<String> list, Bus bus) {
        for (Schedule schedule : bus.schedules) {
            if (schedule.departureSchedule.equals(date)) {
                for (int i = 0; i < list.size(); i++) {
                    schedule.bookSeat(list.get(i));
                }
                return true;
            }
        }
        return false;
    }
}
