package RyanSafaTjendanaJBusAF;


public class Account extends Serializable
{
    public String email;
    public String name;
    public String password;
    
    public Account (int id, String name, String email, String password){
        super(id);
        this.email = email;
        this.name = name;
        this.password = password;
    }
    
    public String toString()
    {
        return super.id + ", " + this.email + ", " + this.name + ", " + this.password;
    }
}
