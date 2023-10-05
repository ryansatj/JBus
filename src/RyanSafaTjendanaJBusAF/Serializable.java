package RyanSafaTjendanaJBusAF;

import java.util.HashMap;

public class Serializable
{
    public final int id;
    private static HashMap <Class<?>, Integer> mapCounter;

    protected Serializable()
    {
        if (mapCounter == null){
            this.id = 0;
            mapCounter = new HashMap<>();
        } else {
            int count = mapCounter.getOrDefault(this.getClass(), 0);
            mapCounter.put(this.getClass(), count + 1);
            this.id = count;
        }
    }

    public static <T> Integer setLastAssignedId (Class<T> obj, int set)
    {
        mapCounter.replace(obj, set);
        return mapCounter.get(obj);
    }

    public static <T> Integer getLastAssignedId (Class<T> obj)
    {
        return mapCounter.get(obj);
    }
    public int compareTo(Serializable obj){
        if(this.id == obj.id)
        {
            return 0;
        }
        else{
            return 1;
        }
    }
    public boolean equals (Object obj){
        if (obj instanceof Serializable) {
            Serializable temp = (Serializable) obj;
            if (this.id == temp.id)
            {
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    public boolean equals (Serializable obj)
    {
        if(obj.id == this.id)
        {
            return true;
        }
        else{
            return false;
        }
    }

}