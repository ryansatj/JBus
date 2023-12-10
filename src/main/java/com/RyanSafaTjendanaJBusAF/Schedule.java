package com.RyanSafaTjendanaJBusAF;

import java.sql.Timestamp;
import java.util.Map;
import java.util.LinkedHashMap;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Class untuk merepresentasikan jadwal keberangkatan dan ketersediaan kursi pada suatu penerbangan.
 *
 * @author Ryan Safa Tjendana
 * @version 1.0
 */
public class Schedule
{
    /**
     * Waktu keberangkatan penerbangan
     */
    public Timestamp departureSchedule;
    /**
     * Peta ketersediaan kursi pada penerbangan.
     * Key: Nomor kursi, Value: Status ketersediaan kursi (true jika tersedia, false jika sudah dipesan)
     */
    public Map<String, Boolean> seatAvailability;

    /**
     * Konstruktor untuk objek Schedule dengan waktu keberangkatan dan jumlah kursi.
     *
     * @param departureSchedule waktu keberangkatan penerbangan
     * @param numberOfSeats jumlah kursi pada penerbangan
     */
    public Schedule(Timestamp departureSchedule, int numberOfSeats)
    {
        this.departureSchedule = departureSchedule;
        initializeSeatAvailability(numberOfSeats);
    }

    /**
     * Inisialisasi ketersediaan kursi pada penerbangan.
     *
     * @param numberOfSeats jumlah kursi pada penerbangan
     */
    private void initializeSeatAvailability(int numberOfSeats)
    {
        this.seatAvailability = new LinkedHashMap<String, Boolean>();
        for(int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++)
        {
            String sn = seatNumber < 10 ? "0"+ seatNumber : "" + seatNumber;
            seatAvailability.put("AF"+ sn, true);
        }
    }

    /**
     * Memeriksa ketersediaan kursi berdasarkan nomor kursi.
     *
     * @param seat nomor kursi yang akan diperiksa
     * @return true jika kursi tersedia, false jika sudah dipesan
     */
    public boolean isSeatAvailable(String seat)
    {
        if(this.seatAvailability.get(seat))
        {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Memeriksa ketersediaan kursi berdasarkan daftar nomor kursi.
     *
     * @param list daftar nomor kursi yang akan diperiksa
     * @return true jika semua kursi tersedia, false jika ada yang sudah dipesan
     */
    public boolean isSeatAvailable(List<String> list)
    {
        int s = 0;
        int tempsum = 0;
        while(s < list.size()) {
            if (this.seatAvailability.get(list.get(s))) {
                tempsum++;
            }
            s++;
        }
        if (tempsum == list.size())
        {
            return true;
        }
        return false;
    }

    /**
     * Membuat reservasi untuk satu kursi.
     *
     * @param seat nomor kursi yang akan dipesan
     */
    public void bookSeat (String seat)
    {
        this.seatAvailability.put(seat, false);
    }

    /**
     * Membuat reservasi untuk beberapa kursi.
     *
     * @param list daftar nomor kursi yang akan dipesan
     */
    public void bookSeat (List<String> list)
    {
        for(int i = 0; i < list.size(); i++)
        {
            this.seatAvailability.put(list.get(i), false);
        }
    }

    /**
     * Representasi string dari objek Schedule.
     *
     * @return String yang berisi waktu keberangkatan dan informasi ketersediaan kursi
     */
    public String toString ()
    {
        int availableSeat = Algorithm.count(this.seatAvailability.values().iterator(), true);
        int temptotal = Algorithm.count(this.seatAvailability.values().iterator(), false);
        SimpleDateFormat formatTanggal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.Ms");
        return "Schedule: " + formatTanggal.format(this.departureSchedule.getTime()) + "\n" + "Occupied: " + temptotal + "/" + availableSeat;
    }

    /**
     * Menampilkan jadwal keberangkatan dan ketersediaan kursi pada penerbangan.
     */
    public void printSchedule() 
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule.getTime());
        // Print tanggal keberangkatan
        System.out.println("Tanggal keberangkatan: " + formattedDepartureSchedule);
        // Print daftar kursi dan ketersediaan kursinya
        System.out.println("Daftar kursi dan ketersediaan kursinya: ");
        // Create a list of seats and sort them numerically
        int maxSeatsPerRow = 4; 
        // Assuming there are 4 seats per row
        int currentSeat = 1;
        for (String seat : this.seatAvailability.keySet()) 
        {
            String symbol = this.seatAvailability.get(seat)? "O" : "X";
            System.out.print(seat + " : " + symbol + "\t");
            if (currentSeat % maxSeatsPerRow == 0) 
            {
            System.out.println();
            }
            currentSeat++;
        }
        System.out.println("\n");
    }

    /**
     * Mengembalikan status ketersediaan kursi menjadi true.
     *
     * @param seat nomor kursi yang akan diubah statusnya
     */
    public void toTrue (String seat){
        if (this.seatAvailability.containsKey(seat)) {
            this.seatAvailability.put(seat, true);
        } else {
            System.out.println("Bangku " + seat + " Tidak tersedia.");
        }
    }
    
}
