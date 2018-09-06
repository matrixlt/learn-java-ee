package cdidemo.excercise2;

import java.util.ArrayList;
import java.util.List;

@Demo
public class DefaultItemDao implements ItemDao {

    @Override
    public List<Item> fetchItems() {
        List<Item> results = new ArrayList<>();
        results.add(new Item(34, 7));
        results.add(new Item(4, 37));
        results.add(new Item(24, 19));
        results.add(new Item(89, 32));
        return results;
    }
}
