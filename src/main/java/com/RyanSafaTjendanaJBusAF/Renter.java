package com.RyanSafaTjendanaJBusAF;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Renter extends Serializable
{
    private final String REGEX_NAME = "^[A-Z][A-Za-z0-9_]{3,19}$";
    private final String REGEX_PHONE = "[0-9]{8,11}$";
    public String address;
    public String companyName;
    public String phoneNumber;
    
    public Renter(String companyName)
    {
        super();
        this.companyName = companyName;
        this.address = "";
        this.phoneNumber = "";
    }
    public Renter(String companyName, String phoneNumber){
        super();
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = "";
    }
    
    public Renter(String companyName, String phoneNumber, String address){
        super();
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = "";
    }

    public boolean validate()
    {
        Pattern patternPhone = Pattern.compile(REGEX_PHONE);
        Matcher matcherPhone = patternPhone.matcher(this.phoneNumber);
        boolean matchPhoneFound = matcherPhone.find();

        Pattern patternName = Pattern.compile(REGEX_NAME);
        Matcher matcherName = patternName.matcher(companyName);
        boolean matchNameFound = matcherName.find();

        if(matchNameFound && matchPhoneFound)
            return true;

        return false;
    }

}
