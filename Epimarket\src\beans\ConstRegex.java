
package beans;

public interface ConstRegex {
    
    public static final String IDENT_PATTERN            = "^[_A-Za-z0-9- .?!]*$";
    public static final String NUMERICAL_PATTERN        = "^[0-9]*$";   
    public static final String PRICE_PATTERN            = "^[0-9.,]*$";
    public static final String EMAIL_PATTERN            = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

}
