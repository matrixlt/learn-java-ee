package ejbhellobean;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = "ejb/HelloFacade", mappedName="ejb/HelloFacade")
public class HelloBeanImpl implements HelloBean {
    
    private final List<String> history;
    
    public HelloBeanImpl() {
        this.history = new ArrayList<>();
    }

    @Override
    public void hello(String message) {
        history.add(message);
    }

    @Override
    public List<String> helloHistory() {
        return history;
    }
}
