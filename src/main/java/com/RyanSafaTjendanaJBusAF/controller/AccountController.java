package com.RyanSafaTjendanaJBusAF.controller;

import com.RyanSafaTjendanaJBusAF.Account;
import com.RyanSafaTjendanaJBusAF.Algorithm;
import com.RyanSafaTjendanaJBusAF.Renter;
import com.RyanSafaTjendanaJBusAF.dbjson.JsonAutowired;
import com.RyanSafaTjendanaJBusAF.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

/**
 * Class Controller untuk mengelola operasi-operasi terkait akun.
 * Implementasi dari BasicGetController dengan model objek Account.
 *
 * @author Ryan Safa
 * @version 1.0
 */

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
    /**
     * Metode untuk menampilkan halaman akun.
     *
     * @return string berisi halaman akun
     */
    @GetMapping
    String index() { return "account page"; }
    /**
     * JsonTable yang digunakan untuk menyimpan data akun.
     */
    @JsonAutowired(value = Account.class, filepath = "src\\main\\java\\com\\RyanSafaTjendanaJBusAF\\json\\accountDatabase.json")
    public static JsonTable<Account> accountTable;

    /**
     * Metode untuk registrasi akun baru.
     *
     * @param name     nama akun
     * @param email    email akun
     * @param password password akun
     * @return BaseResponse yang berisi informasi tentang hasil registrasi
     */
    @PostMapping("/register")
    BaseResponse<Account> register
            (
                    @RequestParam String name,
                    @RequestParam String email,
                    @RequestParam String password
            )
    {
        String generatedPass = null;
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
           md.update(password.getBytes());
           byte[] bytes = md.digest();
           StringBuilder sb = new StringBuilder();
           for(int i = 0; i < bytes.length; i++){
               sb.append((Integer.toString((bytes[i] & 0xff)+ 0x100, 16).substring(1)));
           }
           generatedPass = sb.toString();
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        Account account = new Account(name, email, password);
        Account responseAcc = new Account(name, email, generatedPass);
        if(!account.name.isBlank() && account.validate() &&!Algorithm.<Account>exists(getJsonTable(),
                t -> t.email.equals(account.email))) {
            accountTable.add(responseAcc);
            return new BaseResponse<>(true, "Register Berhasil!", responseAcc);
        }
        return new BaseResponse<>(false, "Gagal register", null);
    }

    /**
     * Metode untuk proses login.
     *
     * @param email    email akun
     * @param password password akun
     * @return BaseResponse yang berisi informasi tentang hasil login
     */
    @PostMapping("/login")
    BaseResponse<Account> login(
            @RequestParam String email,
            @RequestParam String password
    ){
        String generatedPass = null;
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < bytes.length; i++){
                sb.append((Integer.toString((bytes[i] & 0xff)+ 0x100, 16).substring(1)));
            }
            generatedPass = sb.toString();
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        Account account = new Account("", email, generatedPass);
        if (Algorithm.<Account>exists(getJsonTable(), akun -> akun.email.equals(account.email) && akun.password.equals(account.password))) {
            Account logininfo = Algorithm.<Account>find(getJsonTable(), akun->akun.email.equals(account.email));
            return new BaseResponse<>(true, "Berhasil Login!!", logininfo);
        }
        return new BaseResponse<>(false, "Gagal Login", null);
    }
    /**
     * Metode untuk mendapatkan JsonTable yang berisi data akun.
     *
     * @return JsonTable yang berisi data akun
     */
    @Override
    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }

    /**
     * Metode untuk registrasi Renter baru.
     *
     * @param id          ID akun
     * @param companyName nama perusahaan
     * @param address     alamat perusahaan
     * @param phoneNumber nomor telepon perusahaan
     * @return BaseResponse yang berisi informasi tentang hasil registrasi Renter
     */
    @PostMapping("/{id}/registerRenter")
    BaseResponse<Renter> registerRenter(
            @PathVariable int id,
            @RequestParam String companyName,
            @RequestParam String address,
            @RequestParam String phoneNumber
            ){
        Account account = Algorithm.<Account>find(accountTable, acc -> acc.id == id);
        if (account != null && account.company == null) {
            Renter renter = new Renter(companyName, phoneNumber, address);
            account.company = renter;
            return new BaseResponse<>(true, "Renter berhasil dibuat", renter);
        }
        return new BaseResponse<>(false, "Renter Gagal dibuat", null);
    }

    /**
     * Metode untuk melakukan top-up saldo akun.
     *
     * @param id     ID akun
     * @param amount jumlah saldo yang akan di-top-up
     * @return BaseResponse yang berisi informasi tentang hasil top-up
     */
    @PostMapping("/{id}/topUp")
    BaseResponse<Double> topUp(
            @PathVariable int id,
            @RequestParam double amount
    ){
       if(Algorithm.<Account>exists(getJsonTable(), t->t.id == id && t.topUp(amount)))
           return new BaseResponse<>(true, "Berhasil TopUp", amount);
        return new BaseResponse<>(false, "Tidak Berhasil TopUp", amount);
    }

}
