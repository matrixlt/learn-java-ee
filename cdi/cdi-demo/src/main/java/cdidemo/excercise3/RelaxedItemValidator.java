package cdidemo.excercise3;

import cdidemo.excercise2.Item;
import javax.enterprise.inject.Alternative;

@Alternative
public class RelaxedItemValidator implements ItemValidator {

    @Override
    public boolean isValid(Item item) {
        return item.getValue() < (item.getLimit() * 2);
    }
}
