package com.RyanSafaTjendanaJBusAF.controller;

import com.RyanSafaTjendanaJBusAF.*;
import com.RyanSafaTjendanaJBusAF.dbjson.JsonAutowired;
import com.RyanSafaTjendanaJBusAF.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Ryan Safa
 * @version 1.0
 */
@RestController
@RequestMapping("/bus")
public class BusController implements BasicGetController<Bus> {

    @JsonAutowired(value = Bus.class, filepath = "src\\main\\java\\com\\RyanSafaTjendanaJBusAF\\json\\bus.json")
    public static JsonTable<Bus> busTable;

    /**
     * Mendapatkan JsonTable yang terkait dengan kontroler ini.
     *
     * @return JsonTable yang berisi data Bus.
     */
    @Override
    public JsonTable<Bus> getJsonTable() {
        return busTable;
    }

    /**
     * Membuat objek Bus baru.
     *
     * @param accountId          ID akun pemilik bus.
     * @param name               Nama bus.
     * @param capacity           Kapasitas bus.
     * @param facilities         Fasilitas yang dimiliki bus.
     * @param busType            Jenis bus (BusType).
     * @param price              Harga tiket bus.
     * @param stationDepartureId ID stasiun keberangkatan.
     * @param stationArrivalId   ID stasiun kedatangan.
     * @return Respon berisi informasi tentang berhasil atau tidaknya pembuatan bus.
     */
    @PostMapping("/create")
    public BaseResponse<Bus> create
            (
                    @RequestParam int accountId,
                    @RequestParam String name,
                    @RequestParam int capacity,
                    @RequestParam List<Facility> facilities,
                    @RequestParam BusType busType,
                    @RequestParam int price,
                    @RequestParam int stationDepartureId,
                    @RequestParam int stationArrivalId
                    )
    {
        JsonTable<Account> NewaccTable = new AccountController().getJsonTable();
        Account akun = Algorithm.<Account>find(NewaccTable, a->a.id == accountId);
        if(akun == null || akun.company == null || !Algorithm.<Station>exists(new StationController().getJsonTable(), t -> t.id == stationArrivalId) || !Algorithm.<Station>exists(new StationController().getJsonTable(), b -> b.id == stationDepartureId))
        {
            return new BaseResponse<>(false, "Bus gagal dibuat", null);

        }
        Bus bus = new Bus(name, facilities, new Price(price), capacity, busType, Algorithm.<Station>find(new StationController().getJsonTable(), t->t.id == stationArrivalId), Algorithm.<Station>find(new StationController().getJsonTable(), t->t.id == stationDepartureId));
        bus.accountId = accountId;
        busTable.add(bus);
        return new BaseResponse<>(true, "Bus Berhasil dibuat", bus);
    }

    /**
     * Menambahkan jadwal keberangkatan untuk bus tertentu.
     *
     * @param busId ID bus.
     * @param time  Waktu keberangkatan baru (dalam format String).
     * @return Respon berisi informasi tentang berhasil atau tidaknya penambahan jadwal.
     */
    @PostMapping("/addSchedule")
    public BaseResponse<Bus> addSchedule(
            @RequestParam int busId,
            @RequestParam String time
    ){
        try{
            Bus bus = Algorithm.<Bus>find(busTable, t->t.id == busId);
            bus.addSchedule(Timestamp.valueOf(time));
            return new BaseResponse<>(true, "Schedule berhasil dibuat", bus);
        }catch (Exception e){
            return new BaseResponse<>(false, "Schedule gagal dibuat", null);
        }
    }

    /**
     * Mendapatkan daftar bus yang dimiliki oleh akun dengan ID tertentu.
     *
     * @param accountId ID akun pemilik bus.
     * @return Respon berisi daftar bus yang dimiliki oleh akun tersebut.
     */
    @GetMapping("/getMyBus")
    public BaseResponse<List<Bus>> getMyBus(@RequestParam int accountId) {
        return new BaseResponse<>(true, "Berhasil", Algorithm.<Bus>collect(getJsonTable(), b->b.accountId==accountId ));}

    /**
     * Mendapatkan daftar semua bus yang tersedia.
     *
     * @return Respon berisi daftar semua bus yang tersedia.
     */
    @GetMapping("/getAllBus")
    public BaseResponse<List<Bus>> getAllBus()
    {return new BaseResponse<>(true, "berhasil", getJsonTable());}

    /**
     * Mendapatkan informasi harga tiket untuk bus dengan ID tertentu.
     *
     * @param busId ID bus.
     * @return Respon berisi informasi harga tiket untuk bus tersebut.
     */
    @GetMapping("/getBusPrice")
    public BaseResponse<Bus> getBusPrice(@RequestParam int busId){
        return new BaseResponse<>(true, "", Algorithm.<Bus>find(getJsonTable(), t->t.id == busId));
    }

}
