package servletplayground;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@SuppressWarnings({"unused", "WeakerAccess"})
@Named
@ApplicationScoped
public class Counter {
    private Integer counter;

    public Counter() {
        this.counter = 0;
    }

    public Counter(Integer c) {
        this.counter = c;
    }

    synchronized public Integer getInteger() {
        return this.counter;
    }

    public synchronized void setInteger(Integer c) {
        this.counter = c;
    }

    public synchronized void increase() {
        this.counter += 1;
    }

    public synchronized void decrease() {
        this.counter -= 1;
    }
}
