package jsfsimplehello;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class Hello implements Serializable {
    private String world = "Hello, world!";

    public Hello() {
    }

    public String getWorld() {
        this.world += "!";
        return this.world;
    }
}
