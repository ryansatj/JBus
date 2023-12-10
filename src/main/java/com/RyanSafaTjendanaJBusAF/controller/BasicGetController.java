package com.RyanSafaTjendanaJBusAF.controller;

import com.RyanSafaTjendanaJBusAF.Algorithm;
import com.RyanSafaTjendanaJBusAF.dbjson.JsonTable;
import com.RyanSafaTjendanaJBusAF.dbjson.Serializable;
import org.apache.coyote.RequestInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.RyanSafaTjendanaJBusAF.Algorithm.find;
import static com.RyanSafaTjendanaJBusAF.Algorithm.paginate;

/**
 * Interface untuk kontroler dasar yang menangani pengambilan data.
 *
 * @param <T> Jenis data yang akan diambil, harus memperluas Serializable.
 * @author Ryan Safa
 * @version 1.0
 */
@RequestMapping("/page")
@RestController
public interface BasicGetController<T extends Serializable> {
    /**
     * Mendapatkan JsonTable yang terkait dengan kontroler ini.
     *
     * @return JsonTable yang berisi data dari jenis T.
     */
    public abstract JsonTable<T> getJsonTable();

    /**
     * Mengambil halaman data dengan penomoran halaman.
     *
     * @param page     Nomor halaman (default adalah 0).
     * @param pagesize Ukuran setiap halaman (default adalah 5).
     * @return Daftar objek data untuk halaman yang ditentukan.
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public default List<T> getPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int pagesize
    ){
        return Algorithm.paginate(getJsonTable(), page, pagesize, t->true);
    }

    /**
     * Mendapatkan data berdasarkan ID.
     *
     * @param id ID data yang akan diambil.
     * @return Objek data dengan ID yang sesuai atau null jika tidak ditemukan.
     */
    @GetMapping("/{id}")
    public default T getById(@PathVariable int id)
    {
        return Algorithm.<T>find(getJsonTable(), e->e.id == id);
    }
}

