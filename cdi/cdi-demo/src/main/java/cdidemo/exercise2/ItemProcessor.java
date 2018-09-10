package cdidemo.exercise2;

import cdidemo.exercise3.ItemErrorHandler;
import cdidemo.exercise3.ItemValidator;
import cdidemo.exercise4.Notify;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@RequestScoped
public class ItemProcessor {

    private static final Logger logger = Logger.getLogger(ItemProcessor.class.getName());

    @Inject
    @Demo
    private ItemDao itemDao;

    @Inject
    private ItemValidator itemValidator;
    @Inject
    @Notify
    private ItemErrorHandler itemErrorHandler;

    public ItemProcessor() {
        logger.info("Item processor created...");
    }

    public void execute() {
        List<Item> items = itemDao.fetchItems();
        items.forEach((item) -> {
            boolean isValid = itemValidator.isValid(item);
            logger.log(Level.INFO, "Item = {0} valid = {1}", new Object[]{item, isValid});
            if (isValid) {
                itemErrorHandler.handleItem(item);
            }
        });
    }
}
