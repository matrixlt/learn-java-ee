package cdidemo.excercise4;

import cdidemo.excercise2.Item;
import cdidemo.excercise3.ItemErrorHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;

@RequestScoped
public class FileErrorReporter implements ItemErrorHandler {
    static final Logger logger = Logger.getLogger(FileErrorReporter.class.getName());
    
    public void eventFired(@Observes Item item) {
        handleItem(item);
    }

    @Override
    public void handleItem(Item item) {
        logger.log(Level.INFO, "Reporter handle item {0}", item);
    }
}
