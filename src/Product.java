public class Product {
    public static String [] allowedTypes = {"kg", "l", "sztuk/a", "metr"};

    private final String name;

    private final String type;

    private final float quantity;

    public Product(String name, String type, float quantity) {
        this.name = name;
        this.type = type;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public final float getQuantity() {
        return quantity;
    }

    public String typeParser() {
        if(("kg".compareTo(type) == 0) || ("l".compareTo(type) == 0)) {
            return Float.toString(quantity);
        }
        return Integer.toString((int)quantity);
    }

}