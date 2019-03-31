package item;

public class Item {
    private Long id;
    private int size;
    private int cost;
    private Color color;
    private Type type;
    private String description;
    private Location location;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setSize(int size) {
        if (size < 42) {
            size = 42;
        } else if (size > 54) {
            size = 54;
        }

        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
