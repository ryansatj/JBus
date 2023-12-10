package com.RyanSafaTjendanaJBusAF.controller;

/**
 * Kelas generik yang merepresentasikan respon dengan status keberhasilan, pesan, dan muatan data.
 *
 * @param <T> Tipe muatan dalam respon.
 *
 * @author Ryan Safa
 * @version 1.0
 */
public class BaseResponse<T> {
    /**
     * Flag yang menandakan status keberhasilan respon.
     */
    public boolean success;
    /**
     * Pesan yang memberikan informasi tambahan tentang respon.
     */
    public String message;
    /**
     * Muatan data yang berisi informasi terkait respon.
     */
    public T payload;

    /**
     * Membangun objek BaseResponse baru.
     *
     * @param success Flag boolean yang menandakan status keberhasilan respon.
     * @param message String yang memberikan informasi tambahan tentang respon.
     * @param payload Muatan data yang berisi informasi terkait respon.
     */
    public BaseResponse(boolean success, String message, T payload) {
        this.success = success;
        this.message = message;
        this.payload = payload;
    }
}
