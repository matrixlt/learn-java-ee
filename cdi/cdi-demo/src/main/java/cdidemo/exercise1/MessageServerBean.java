package cdidemo.exercise1;

import javax.ejb.Stateless;
import javax.inject.Named;

@Named
@Stateless
public class MessageServerBean {

    @SuppressWarnings("SameReturnValue")
    public String getMessage() {
        return "Hello Weld!!!!!!!!!!!!!";
    }
}
