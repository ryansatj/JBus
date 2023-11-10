package com.RyanSafaTjendanaJBusAF;

import com.RyanSafaTjendanaJBusAF.dbjson.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Account extends Serializable
{
    public String email;
    public String name;
    public String password;
    public Renter company;
    public double balance;

    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z_]+?\\.[a-zA-Z.]+[a-zA-Z]+$";;
    public static final  String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[a-zA-Z0-9]{8,}$";
    
    public Account (String name, String email, String password){
        super();
        this.balance = 0;
        this.email = email;
        this.name = name;
        this.password = password;
    }
    
    public String toString()
    {
        return "id: " + super.id + "\n" + "email: "+ this.email + "\n" + "Nama: "+ this.name + "\n" + "Password: " + this.password;
    }
    
    public Object write(){
        return null;
    }
    public boolean read(String string){
        return false;
    }

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

    public boolean topUp(double amount)
    {
        if(amount > 0){
            this.balance += amount;
            return true;
        }
        return false;
    }
}
