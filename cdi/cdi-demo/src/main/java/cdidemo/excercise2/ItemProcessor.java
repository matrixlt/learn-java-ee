package cdidemo.excercise2;

import cdidemo.excercise3.ItemErrorHandler;
import cdidemo.excercise3.ItemValidator;
import cdidemo.excercise4.Notify;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ItemProcessor {
    
    static final Logger logger = Logger.getLogger(ItemProcessor.class.getName());

    @Inject @Demo
    private ItemDao itemDao;
    
    @Inject
    private ItemValidator itemValidator;
        
    public ItemProcessor() {
        logger.info("Item processor created");
    }

    @Inject @Notify
    private ItemErrorHandler itemErrorHandler;

    public void execute() {
        List<Item> items = itemDao.fetchItems();
        items.forEach((item) -> {
            Boolean isValid = itemValidator.isValid(item);
            logger.log(Level.INFO, "Item = {0} valid = {1}", new Object[]{item, isValid});
            if (isValid) {
                itemErrorHandler.handleItem(item);
            }
        });
    }
}
