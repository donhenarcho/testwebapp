package beans;

import item.Item;
import item.Location;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@ApplicationScoped
public class ItemsBean implements Serializable {
    private Map<Long, Item> items = new HashMap<>();
    private long lastId = 0;

    public List<Item> getItems(Location location) {
        List<Item> items = new ArrayList<>();
        this.items.forEach((key, value) -> {
            if (value.getLocation() == location) {
                items.add(value);
            }
        });
        return items;
    }

    public Item getItem(Long id) {
        if (items.containsKey(id))
            return items.get(id);
        return null;
    }

    public Item add(Item item) {
        lastId++;
        item.setId(lastId);
        items.put(item.getId(), item);
        return item;
    }

    public Item update(Item item) {
        items.put(item.getId(), item);
        return item;
    }

    public Item delete(Long id) {
        if (!items.containsKey(id))
            return null;

        Item item = items.get(id);
        items.remove(id);
        return item;
    }
}
