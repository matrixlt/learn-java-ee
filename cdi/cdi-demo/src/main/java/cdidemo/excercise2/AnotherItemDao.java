package cdidemo.excercise2;

import java.util.ArrayList;
import java.util.List;

public class AnotherItemDao implements ItemDao {

    @Override
    public List<Item> fetchItems() {
        List<Item> results = new ArrayList<Item>();
        results.add(new Item(99, 9));
        return results;
    }
}