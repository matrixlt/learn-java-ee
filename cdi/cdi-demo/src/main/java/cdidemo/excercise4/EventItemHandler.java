package cdidemo.excercise4;

import cdidemo.excercise2.Item;
import cdidemo.excercise3.ItemErrorHandler;import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@Notify
public class EventItemHandler implements ItemErrorHandler{
    
    static final Logger logger = Logger.getLogger(EventItemHandler.class.getName());
    
    @Inject
    private Event<Item> itemEvent;

    @Override
    public void handleItem(Item item) {
        logger.log(Level.INFO, "Firing Event for item {0}", item.toString());
        itemEvent.fire(item);
    }
}
