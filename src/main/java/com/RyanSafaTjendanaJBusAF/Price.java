package com.RyanSafaTjendanaJBusAF;

/**
 * Class Price untuk menyimpan objek price
 *
 * @author Ryan Safa T
 * @version 1.0
 */
public class Price
{
    /**
     * field price untuk menyimpan harga
     */
    public double price;
    /**
     * field price
     */
    public double rebate;

    /**
     * constructor objek Price tanpa rebate
     *
     * @param price parameter untuk menambahkan price
     */
    public Price(double price)
    {
        this.price = price;
        this.rebate = 0;
    }

    /**
     * constructor objek Price
     *
     * @param price parameter untuk menambahkan price
     * @param rebate paramater untuk menambahkan rebate
     */
    public Price(double price, double rebate)
    {
        this.price = price;
        this.rebate = rebate;
    }

    /**
     *
     * @return mereturn class ini dalam bentuk String
     */
    public String toString()
    {
        return this.price + ", " + this.rebate;
    }

}