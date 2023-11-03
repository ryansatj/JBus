package RyanSafaTjendanaJBusAF;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Renter extends Serializable
{
    private final String REGEX_NAME = "^[A-Z][A-Za-z0-9_]{3,19}$";
    private final String REGEX_PHONE = "[0-9]{8,11}$";
    public String address;
    public String companyName;
    public int phoneNumber;
    
    public Renter(int id, String companyName)
    {
        super();
        this.companyName = companyName;
        this.address = "";
        this.phoneNumber = 0;
    }
    
    public Renter(int id, String companyName, String address){
        super();
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = 0;
    }
    
    public Renter(int id, String companyName, int phoneNumber){
        super();
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = "";
    }
    
    public Renter(int id, String companyName, int phoneNumber, String address){
        super();
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = "";
    }

    public boolean validate()
    {
        Pattern patternPhone = Pattern.compile(REGEX_PHONE);
        Matcher matcherPhone = patternPhone.matcher(Integer.toString(this.phoneNumber));
        boolean matchPhoneFound = matcherPhone.find();

        Pattern patternName = Pattern.compile(REGEX_NAME);
        Matcher matcherName = patternName.matcher(companyName);
        boolean matchNameFound = matcherName.find();

        if(matchNameFound && matchPhoneFound)
            return true;

        return false;
    }

}
