import java.io.*;
import java.util.ArrayList;

public class ProductsListInputOutput {

    private final String ProductsFileName = "ProductsList";

    public void saveProductsList(ProductsList productsList) throws IOException {
        ArrayList<Category> content = productsList.getContents();

        try(FileWriter fileWriter = new FileWriter(ProductsFileName.concat(".txt"))) {
            for (Category i : content) {
                fileWriter.write("#".concat(i.getName().concat("\r\n")));
                for (Product j : i.getProducts()) {
                    fileWriter.write(j.getName().concat("\r\n"));
                }
            }
        } catch(IOException e) {
            System.out.println("Couldn't create file.");
            throw e;
        }
    }

    public ProductsList loadProductsList() throws FileNotFoundException, IOException {
        String string;
        ProductsList productsList = new ProductsList();

        Category category = null;
        try (BufferedReader br = new BufferedReader(new FileReader(ProductsFileName.concat(".txt")))) {
            while ((string = br.readLine()) != null) {
                if (string.charAt(0) == '#') {
                    category = new Category(string.substring(1));
                    productsList.addCategory(category);
                } else {
                    if (category != null) {
                        category.addProductToCategory(new Product(string, null, 0));
                    }
                }
            }
        } catch (FileNotFoundException p) {
            System.out.println("File does not exist");
            throw p;
        } catch (IOException e) {
            System.out.println("Input-output error detected");
            throw e;
        }

        return productsList;
    }

}
