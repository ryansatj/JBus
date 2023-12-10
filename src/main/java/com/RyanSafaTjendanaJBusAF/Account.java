package com.RyanSafaTjendanaJBusAF;

import com.RyanSafaTjendanaJBusAF.dbjson.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class Account untuk membuat objek yang akan berupa sebuah akun yang akan mengextend Serializeable dimana Serializable akan memberikan id kepada akun
 *
 * @author Ryan Safa Tjendana
 * @version 1.0
 */
public class Account extends Serializable
{
    /**
     * field email untuk menyimpan email dari suatu objek akun
     */
    public String email;

    /**
     * field nama untuk menyimpan nama dari suatu objek akun
     */
    public String name;

    /**
     * field password untuk menyimpan password dari suatu objek akun
     */
    public String password;

    /**
     * field company untuk menyimpan Renter dari suatu objek akun ketika ingin menjadi renter
     */
    public Renter company;

    /**
     * field balance untuk menyimpan balance dari suatu objek akun untuk melakukan pemesanan
     */
    public double balance;

    /**
     * field REGEX_EMAIL untuk memvaliadasi suatu email agar sesuai dari ketentuan email dari suatu objek akun
     */
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z_]+?\\.[a-zA-Z.]+[a-zA-Z]+$";

    /**
     * field REGEX_PASSWORD untuk memvaliadasi suatu password agar sesuai dari ketentuan email dari suatu objek akun
     */
    public static final  String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[a-zA-Z0-9]{8,}$";

    /**
     * constructor ini dibuat untuk dipanggil ketika ingin membuat suatu akun
     *
     * @param name berisi nama untuk akun
     * @param email berisi email untuk akun
     * @param password berisi password untuk akun
     */
    
    public Account (String name, String email, String password){
        super();
        this.balance = 0;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    /**
     * akan me return isi class ini dalam bentuk String
     * @return semua isi class
     */
    public String toString()
    {
        return "id: " + super.id + "\n" + "email: "+ this.email + "\n" + "Nama: "+ this.name + "\n" + "Password: " + this.password;
    }

    /**
     * akan mengecek regex apakah sudah sesuai apa belum dengan mereturn boolean
     * @return boolean untuk true ketika sudah sesuai regexnya
     */
    public boolean validate(){
        Pattern patternEmail = Pattern.compile(REGEX_EMAIL);
        Matcher matcherEmail = patternEmail.matcher(this.email);
        boolean matchEmailFound = matcherEmail.find();

        Pattern patternPassword = Pattern.compile(REGEX_PASSWORD);
        Matcher matcherPassword = patternPassword.matcher(this.password);
        boolean matchPasswordFound = matcherPassword.find();

        if(matchEmailFound && matchPasswordFound)
            return true;

        return false;
    }

    /**
     * untuk topUp akun atau menambahkan balance
     *
     * @param amount adalah parameter untuk menerima jumlah untuk topUp
     * @return boolean true ketika topUp berhasil
     */
    public boolean topUp(double amount)
    {
        if(amount > 0){
            this.balance += amount;
            return true;
        }
        return false;
    }
}
