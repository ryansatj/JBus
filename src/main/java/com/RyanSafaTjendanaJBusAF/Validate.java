package com.RyanSafaTjendanaJBusAF;

import java.util.ArrayList;

/**
 * Class untuk melakukan filtrasi harga berdasarkan kriteria tertentu.
 * @author Ryan Safa T
 * @version 1.0
 */
public class Validate
{
    /**
     * Memfilter daftar harga berdasarkan nilai dan kriteria.
     *
     * @param list   daftar harga yang akan difilter
     * @param value  nilai batasan untuk filtrasi
     * @param less   true jika filtrasi berdasarkan harga kurang dari atau sama dengan nilai, false jika lebih dari nilai
     * @return       ArrayList berisi harga yang memenuhi kriteria filtrasi
     */
    public static ArrayList filter(Price[] list, int value, boolean less)
    {
        int i;
        ArrayList temp = new ArrayList();
        
        for(i = 0; i < list.length; i++){
            if(less == true)
            {
                if(list[i].price <= value)
                {
                    temp.add(list[i].price);
                }
            }
            
            else{
                if(list[i].price > value)
                {
                    temp.add(list[i].price);
                }
            }
        }
        return temp;
    }
}
