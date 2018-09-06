package jsfformhello;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Hello {
    public Hello() { }
    private String name;
    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }
}
