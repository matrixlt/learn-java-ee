package cdidemo.excercise3;

import cdidemo.excercise2.Item;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class FileErrorReporter implements ItemErrorHandler {

    @PostConstruct
    public void init() {
        System.out.println("Creating file error reporter");
    }

    @PreDestroy
    public void release() {
        System.out.println("Closing file error reporter");
    }
    
    @Override
    public void handleItem(Item item) {
        System.out.println("Saving " + item + " to file");
    }
}
