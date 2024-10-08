package com.RyanSafaTjendanaJBusAF.controller;

import com.RyanSafaTjendanaJBusAF.Bus;
import com.RyanSafaTjendanaJBusAF.City;
import com.RyanSafaTjendanaJBusAF.Station;
import com.RyanSafaTjendanaJBusAF.dbjson.JsonAutowired;
import com.RyanSafaTjendanaJBusAF.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ryan Safa
 * @version 1.0
 */
@RestController
@RequestMapping("/station")
public class StationController implements BasicGetController<Station> {
    public static @JsonAutowired(value = Station.class, filepath = "src\\main\\java\\com\\RyanSafaTjendanaJBusAF\\json\\station.json") JsonTable<Station> stationTable;
    /**
     * Mendapatkan JsonTable yang terkait dengan kontroler ini.
     *
     * @return JsonTable yang berisi data Station.
     */
    @Override
    public JsonTable<Station> getJsonTable() {
        return stationTable;
    }

    /**
     * Membuat stasiun baru.
     *
     * @param stationName Nama stasiun.
     * @param city        Nama kota.
     * @param address     Alamat stasiun.
     * @return Respon berisi informasi tentang berhasil atau tidaknya pembuatan stasiun baru.
     */
    @PostMapping("/create")
    public BaseResponse<Station> createStation(
            @RequestParam String stationName,
            @RequestParam String city,
            @RequestParam String address
    ) {
        try {
            // Validate parameters
            if (stationName.isBlank() || city.isBlank() || address.isBlank()) {
                return new BaseResponse<>(false, "Parameter values cannot be blank or null", null);
            }

            // Validate city as a valid enum value
            City cityEnum = City.valueOf(city.toUpperCase());

            // Create a new station using the provided details
            Station newStation = new Station(stationName, cityEnum, address);

            // Add the new station to the stationTable
            stationTable.add(newStation);

            //Success response message
            return new BaseResponse<>(true, "Station added successfully", newStation);
        } catch (IllegalArgumentException e) {
            // Handle invalid enum value
            return new BaseResponse<>(false, "Invalid city value", null);
        } catch (Exception e) {
            // Handle other unexpected errors
            return new BaseResponse<>(false, "An error occurred while adding the station", null);
        }
    }
    /**
     * Mendapatkan daftar semua stasiun.
     *
     * @return Daftar stasiun yang tersedia.
     */
    @GetMapping("/getAll")
    public List<Station> getAllStation()
    { return getJsonTable();}

}
