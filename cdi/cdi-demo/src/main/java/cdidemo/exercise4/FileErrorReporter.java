package cdidemo.exercise4;

import cdidemo.exercise2.Item;
import cdidemo.exercise3.ItemErrorHandler;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
public class FileErrorReporter implements ItemErrorHandler {

    private static final Logger logger = Logger.getLogger(FileErrorReporter.class.getName());
    
    public void eventFired(@Observes Item item) {
        handleItem(item);
    }

    @Override
    public void handleItem(Item item) {
        logger.log(Level.INFO, "Reporter handle item {0}", item);
    }
}
