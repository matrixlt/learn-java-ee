package ejbhellobean;

import java.util.List;
import javax.ejb.Remote;

@Remote
public interface HelloBean {
    public void hello(String message);
    public List<String> helloHistory();
}