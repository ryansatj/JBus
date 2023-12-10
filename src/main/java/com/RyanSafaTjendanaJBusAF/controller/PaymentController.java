package com.RyanSafaTjendanaJBusAF.controller;

import com.RyanSafaTjendanaJBusAF.*;
import com.RyanSafaTjendanaJBusAF.dbjson.JsonAutowired;
import com.RyanSafaTjendanaJBusAF.dbjson.JsonTable;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment>{

    @JsonAutowired(value = Payment.class, filepath = "src\\main\\java\\com\\RyanSafaTjendanaJBusAF\\json\\payment.json")
    /**
     * Mendapatkan JsonTable yang terkait dengan kontroler ini.
     *
     * @return JsonTable yang berisi data Payment.
     */
    public static JsonTable<Payment> paymentTable;

    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }
    /**
     * Membuat booking untuk pembelian tiket bus.
     *
     * @param buyerId        ID pembeli.
     * @param renterId       ID penyewa.
     * @param busId          ID bus.
     * @param busSeats       Daftar nomor kursi yang dibeli.
     * @param departureDate  Tanggal keberangkatan.
     * @return Respon berisi informasi tentang berhasil atau tidaknya pembuatan booking.
     */
    @RequestMapping(value="/makeBooking", method = RequestMethod.POST)
    public BaseResponse<Payment> makeBooking(
            @RequestParam int buyerId,
            @RequestParam int renterId,
            @RequestParam int busId,
            @RequestParam List<String> busSeats,
            @RequestParam String departureDate
            ) {

        Account buyer = Algorithm.<Account>find(new AccountController().getJsonTable(), t -> t.id == buyerId);
        Bus bus = Algorithm.<Bus>find(new BusController().getJsonTable(), a -> a.id == busId);
        if (buyer == null || bus == null)
            return new BaseResponse<>(false, "Ngga ada kayaknya", null);

        if(bus.price.price > buyer.balance)
            return new BaseResponse<>(false, "Top Up dulu ya", null);

        if(!Payment.makeBooking(Timestamp.valueOf(departureDate), busSeats, bus))
        {
            return new BaseResponse<>(false, "Bus tidak ada", null);
        }
        if(!Algorithm.<Schedule>exists(bus.schedules, a -> a.departureSchedule.equals(Timestamp.valueOf(departureDate))) ) {
            return new BaseResponse<>(false, "Gagal membuat booking", null);
        }
        List paymentMax = Algorithm.<Payment>collect(getJsonTable(), v->v.buyerId == buyerId);
        if(paymentMax.size() == 10){
            return new BaseResponse<>(false, "Cart sudah penuh", null);
        }
        Payment payment = new Payment(buyerId, renterId, busId, busSeats, Timestamp.valueOf(departureDate));
        payment.status = Invoice.PaymentStatus.WAITING;
        paymentTable.add(payment);
        return new BaseResponse<>(true, "Berhasil membuat booking", payment);
    }

    /**
     * Menerima pembayaran untuk booking tertentu.
     *
     * @param id ID pembayaran.
     * @return Respon berisi informasi tentang berhasil atau tidaknya penerimaan pembayaran.
     */
    @RequestMapping(value="/{id}/accept", method = RequestMethod.POST)
    public BaseResponse<Payment> accept(@PathVariable int id)
    {
        try{
            Payment payment = getById(id);
            Bus bus = Algorithm.<Bus>find(BusController.busTable, a->a.id == payment.getBusId());
            Account account = Algorithm.<Account>find(AccountController.accountTable, v->v.id == payment.buyerId);
            if(account.balance > bus.price.price){
                account.balance = account.balance - bus.price.price;
                payment.status = Invoice.PaymentStatus.SUCCESS;
                return new BaseResponse<>(true, "Pembayaran berhasil", payment);
            }
            else{
                return new BaseResponse<>(false, "Saldo Tidak Cukup", null);
            }
        }
        catch(Exception e){
            return new BaseResponse<>(false, "Pembayaran Tidakberhasil", null);
        }
    }

    /**
     * Membatalkan pembayaran untuk booking tertentu.
     *
     * @param id ID pembayaran.
     * @return Respon berisi informasi tentang berhasil atau tidaknya pembatalan pembayaran.
     */
    @RequestMapping(value="/{id}/cancel", method = RequestMethod.POST)
    public BaseResponse<Payment> cancel(@PathVariable int id)
    {
        try{
            Payment payment = getById(id);
            payment.status = Invoice.PaymentStatus.FAILED;
            return new BaseResponse<>(true, "Pembayaran Tidak berhasil", payment);
        }
        catch(Exception e){
            return new BaseResponse<>(false, "Pembayaran undefined", null);
        }
    }

    /**
     * Mendapatkan daftar kursi yang telah dibeli oleh pembeli tertentu.
     *
     * @param buyerId ID pembeli.
     * @return Respon berisi daftar pembayaran (kursi yang dibeli) oleh pembeli tersebut.
     */
    @GetMapping("/getMySeat")
    public BaseResponse<List<Payment>> getMySeat(@RequestParam int buyerId){
        try{
            return new BaseResponse<>(true, "List Payment", Algorithm.<Payment>collect(getJsonTable(), t->t.buyerId == buyerId));
        }
        catch (Exception e){
            return new BaseResponse<>(false, "tidak berhasil", null);
        }
    }

    /**
     * Menghapus data pembayaran tertentu berdasarkan ID.
     *
     * @param id ID pembayaran.
     * @return Respon berisi informasi tentang berhasil atau tidaknya penghapusan pembayaran.
     */
    @PostMapping("/deletePayment")
    public BaseResponse<Payment> deletePayment(@RequestParam int id){
        try{
            Payment payment = getById(id);
            paymentTable.remove(payment);
            return new BaseResponse<>(true, "Pengahapusan Payment berhasil", payment);
        }catch (Exception e){
            return new BaseResponse<>(false, "Penghapusan gagal", null);
        }
    }

}


