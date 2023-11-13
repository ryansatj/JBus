package com.RyanSafaTjendanaJBusAF.controller;

import com.RyanSafaTjendanaJBusAF.*;
import com.RyanSafaTjendanaJBusAF.dbjson.JsonAutowired;
import com.RyanSafaTjendanaJBusAF.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment>{

    @JsonAutowired(value = Payment.class, filepath = "src\\main\\java\\com\\RyanSafaTjendanaJBusAF\\json\\payment.json")
    public static JsonTable<Payment> paymentTable;
    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

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

        if(!Algorithm.<Schedule>exists(bus.schedules, a -> a.departureSchedule.equals(Timestamp.valueOf(departureDate)))
                || !Payment.makeBooking(Timestamp.valueOf(departureDate), busSeats, bus)) {
            return new BaseResponse<>(false, "Gagal membuat booking", null);
        }
        Payment payment = new Payment(buyerId, renterId, busId, busSeats, Timestamp.valueOf(departureDate));
        payment.status = Invoice.PaymentStatus.WAITING;
        paymentTable.add(payment);
        return new BaseResponse<>(true, "Berhasil membuat booking", payment);
    }

    @RequestMapping(value="/{id}/accept", method = RequestMethod.POST)
    public BaseResponse<Payment> accept(@PathVariable int id)
    {
        try{
            Payment payment = getById(id);
            payment.status = Invoice.PaymentStatus.SUCCESS;
            return new BaseResponse<>(true, "Pembayaran berhasil", payment);
        }
        catch(Exception e){
            return new BaseResponse<>(false, "Pembayaran Tidakberhasil", null);
        }
    }

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
}


