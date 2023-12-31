import java.util.ArrayList;

public class ProductsList {

    private final ArrayList<Category> contents;

    public ProductsList() {
        contents = new ArrayList<>();
    }


    public void addCategory(Category category) {
        contents.add(category);
    }

    public ArrayList<Category> getContents() {
        return contents;
    }

    public Product findProduct(String name) {
        for (Category i : contents) {
            for (Product j : i.getProducts()) {
                if (name.equals(j.getName())) {
                    return j;
                }
            }
        }
        return null;
    }

    public boolean productsListIsEmpty() {
        return contents.size() == 0;
    }

}
