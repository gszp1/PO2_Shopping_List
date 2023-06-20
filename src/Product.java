public class Product {
    public static String [] allowedTypes;

    public static String [] integerTypes = {"sztuk/a"};

    public static String [] floatTypes = {"kg", "l", "metr"};

    static {
        allowedTypes = new String[integerTypes.length + floatTypes.length];
        int i = 0;
        for (String j : integerTypes) {
            allowedTypes[i++] = j;
        }
        for (String j : floatTypes) {
            allowedTypes[i++] = j;
        }
    }

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
        if(isFloatType(type)) {
            return Float.toString(quantity);
        }
        return Integer.toString((int)quantity);
    }

    private boolean isFloatType(String type) {
        for(String i: floatTypes) {
            if (i.equals(type)) {
                return true;
            }
        }

        return false;
    }


}