import java.util.ArrayList;

public class ShoppingList {

    private final ArrayList<Category> contents;

    public ShoppingList() {
        contents = new ArrayList<>();
    }

    public ArrayList<Category> getContents() {
        return contents;
    }

    public void addCategory(Category category) {
        contents.add(category);
    }

    public Category getCategory(String name) {
        for(Category i : contents) {
            if(i.getName().equals(name)) {
                return i;
            }
        }
        return null;
    }
}
