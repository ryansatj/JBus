package com.RyanSafaTjendanaJBusAF.controller;

import com.RyanSafaTjendanaJBusAF.Account;
import com.RyanSafaTjendanaJBusAF.Algorithm;
import com.RyanSafaTjendanaJBusAF.Renter;
import com.RyanSafaTjendanaJBusAF.dbjson.JsonAutowired;
import com.RyanSafaTjendanaJBusAF.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;


@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
    @GetMapping
    String index() { return "account page"; }
    @JsonAutowired(value = Account.class, filepath = "src\\main\\java\\com\\RyanSafaTjendanaJBusAF\\json\\accountDatabase.json")
    public static JsonTable<Account> accountTable;

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

    /*@GetMapping("/{id}")
    String getById(@PathVariable int id) { return "account id " + id + " not found!"; }*/

    @Override
    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }

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

    @PostMapping("/{id}/topUp")
    BaseResponse<Double> topUp(
            @PathVariable int id,
            @RequestParam Double amount
    ){
       if(Algorithm.<Account>exists(getJsonTable(), t->t.id == id && t.topUp(amount)))
           return new BaseResponse<>(true, "Berhasil TopUp", amount);
        return new BaseResponse<>(false, "Tidak Berhasil TopUp", amount);
    }

}
