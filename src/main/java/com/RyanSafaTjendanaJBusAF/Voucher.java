package com.RyanSafaTjendanaJBusAF;

import com.RyanSafaTjendanaJBusAF.dbjson.Serializable;

/**
 * Class untuk merepresentasikan objek Voucher dalam sistem.
 * Extends class Serializable untuk mendukung serialisasi objek.
 *
 * @see Serializable
 * @author Ryan Safa
 * @version 1.0
 */
public class Voucher extends Serializable
{
    /**
     * Nama voucher
     */
    public String name;
    private boolean used;
    /**
     * Nilai minimum pembelian untuk penggunaan voucher
     */
    public double minimum;
    /**
     * Nilai potongan atau pengembalian uang dari voucher
     */
    public double cut;
    /**
     * Kode voucher
     */
    public int code;
    /**
     * Jenis voucher (DISCOUNT atau REBATE)
     */
    public Type type;

    /**
     * Konstruktor untuk objek Voucher dengan ID, nama, kode, jenis, nilai minimum, dan nilai potongan.
     *
     * @param id      ID voucher
     * @param name    nama voucher
     * @param code    kode voucher
     * @param type    jenis voucher (DISCOUNT atau REBATE)
     * @param minimum nilai minimum pembelian untuk penggunaan voucher
     * @param cut     nilai potongan atau pengembalian uang dari voucher
     */
    public Voucher(int id, String name, int code, Type type, double minimum, double cut)
    {
        super();
        this.name = name;
        this.code = code;
        this.type = type;
        this.minimum = minimum;
        this.cut = cut;
        this.used = false;
    }
    
    public boolean isUsed (){
        return this.used;
    }
    
    public boolean canApply(Price price)
    {
        if (this.used == false && price.price > minimum)
        {
            return true;
        }
        return false;
    }
    
    public double apply(Price price){
        this.used = true;
        if(this.type == Type.DISCOUNT){
            if(this.cut >= 100 || this.cut < 0){
                return 0.0d;
            }
            else{
                return price.price - price.price * this.cut/100;
            }
        }
        
        else if(this.type == Type.REBATE){
            if(this.cut > price.price){
                return 0.0d;
            }
            else{
                return price.price - this.cut;
            }
        }
        
        return price.price;
    }
}
