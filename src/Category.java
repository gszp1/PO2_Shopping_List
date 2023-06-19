import javax.naming.Name;
import java.util.ArrayList;

public class Category {

    private final String name;

    private final ArrayList<Product> products;

    public Category(String name) {
        this.name = name;
        products = new ArrayList<>();
    }

    public void addProductToCategory(Product product) {
        products.add(product);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

}
