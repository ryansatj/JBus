package com.RyanSafaTjendanaJBusAF;

import java.util.ArrayList;

public class Validate
{
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
