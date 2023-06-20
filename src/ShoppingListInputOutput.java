import java.io.*;

public class ShoppingListInputOutput {

    private final String shoppingListFileName = "ShoppingList";

    public void saveShoppingList(ShoppingList shoppingList) throws IOException {
        try(FileWriter fileWriter = new FileWriter(shoppingListFileName.concat(".txt"))) {
            for(Category i : shoppingList.getContents()) {
                fileWriter.write("#".concat(i.getName().concat("\r\n")));
                for(Product j : i.getProducts()) {
                    fileWriter.write(j.getName().
                                        concat("|").
                                        concat(j.getType()).
                                        concat("|").
                                        concat(j.typeParser()).
                                        concat("\r\n"));
                }
            }
        } catch (IOException e) {
            System.out.println("IO error occurred.");
            throw e;
        }
    }

    public ShoppingList loadShoppingList() throws FileNotFoundException, IOException, NumberFormatException {
        String string;
        ShoppingList shoppingList = new ShoppingList();

        Category category = null;
        try (BufferedReader br = new BufferedReader(new FileReader(shoppingListFileName.concat(".txt")))) {
            while ((string = br.readLine()) != null) {
                if (string.charAt(0) == '#') {
                    category = new Category(string.substring(1));
                    shoppingList.addCategory(category);
                } else {
                    if (category != null) {
                        String[] split = string.split("\\|");
                        category.addProductToCategory(new Product(split[0], split[1], Float.parseFloat(split[2])));
                    }
                }
            }
        } catch (FileNotFoundException p) {
            System.out.println("File does not exist");
            throw p;
        } catch (IOException e) {
            System.out.println("Input-output error detected");
            throw e;
        } catch (NumberFormatException g) {
            System.out.println("Record is damaged");
            throw g;
        }

        return shoppingList;
    }

}
