package jsfsimplehello;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class Hello implements Serializable {
    public Hello() { }
    private String world = "Hello, world!";
    public String getWorld() {
        this.world += "!";
        return this.world;
    }
}
