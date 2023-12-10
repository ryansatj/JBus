package com.RyanSafaTjendanaJBusAF;

import com.RyanSafaTjendanaJBusAF.dbjson.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class ini untuk membuat objek renter yang akan ada dalam Account
 *
 * @author Ryan Safa Tjendana
 * @version 1.0
 */
public class Renter extends Serializable
{
    private static final String REGEX_NAME = "^[A-Z][A-Za-z0-9_]{3,19}$";
    private static final String REGEX_PHONE = "[0-9]{8,11}$";
    public String address;
    public String companyName;
    public String phoneNumber;

    /**
     * Konstruktor untuk objek Renter dengan companyName
     *
     * @param companyName nama perusahaan penyewa
     */
    public Renter(String companyName)
    {
        super();
        this.companyName = companyName;
        this.address = "";
        this.phoneNumber = "";
    }

    /**
     * Konstruktor untuk objek Renter dengan companyName dan phoneNumber
     *
     * @param companyName nama perusahaan penyewa
     * @param phoneNumber nomor telepon perusahaan penyewa
     */
    public Renter(String companyName, String phoneNumber){
        super();
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = "";
    }

    /**
     * Konstruktor untuk objek Renter dengan companyName, phoneNumber, dan address
     *
     * @param companyName nama perusahaan penyewa
     * @param phoneNumber nomor telepon perusahaan penyewa
     * @param address alamat perusahaan penyewa
     */
    public Renter(String companyName, String phoneNumber, String address){
        super();
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    /**
     * Validasi data Renter, memeriksa apakah companyName dan phoneNumber memenuhi format yang benar.
     *
     * @return true jika valid, false jika tidak valid
     */
    public boolean validate()
    {
        Pattern patternPhone = Pattern.compile(REGEX_PHONE);
        Matcher matcherPhone = patternPhone.matcher(this.phoneNumber);
        boolean matchPhoneFound = matcherPhone.find();

        Pattern patternName = Pattern.compile(REGEX_NAME);
        Matcher matcherName = patternName.matcher(companyName);
        boolean matchNameFound = matcherName.find();

        return matchNameFound && matchPhoneFound;
    }
}

