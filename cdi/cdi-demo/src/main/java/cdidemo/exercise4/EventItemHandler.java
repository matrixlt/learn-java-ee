package cdidemo.exercise4;

import cdidemo.exercise2.Item;
import cdidemo.exercise3.ItemErrorHandler;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

@Notify
public class EventItemHandler implements ItemErrorHandler {

    private static final Logger logger = Logger.getLogger(EventItemHandler.class.getName());

    @Inject
    private Event<Item> itemEvent;

    @Override
    public void handleItem(Item item) {
        logger.log(Level.INFO, "Firing Event for item {0}", item.toString());
        itemEvent.fire(item);
    }
}
