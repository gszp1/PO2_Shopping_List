import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class MainMenu extends JFrame {

    private ShoppingList shoppingList;
    private ProductsList productsList;

    private JPanel mainMenuPanel;
    private JPanel buttonsPanel;

    private final ShoppingListInputOutput shoppingListHandler;
    private final ProductsListInputOutput productsListHandler;

    private JTextArea outputTextArea;

    private JButton showAllProductsFromShoppingListButton;
    private JButton showProductsFromShoppingListByCategoryButton;
    private JButton saveShoppingListToFileButton;

    private JButton addProductToShoppingListButton;
    private JButton removeProductFromShoppingListButton;
    private JButton clearShoppingListButton;
    private JButton removeProductsFromShoppingListInCategoryButton;

    private JButton addToProductsListButton;
    private JButton removeFromProductsListButton;
    private JButton displayAvailableProductsButton;

    public MainMenu() {
        shoppingListHandler = new ShoppingListInputOutput();
        productsListHandler = new ProductsListInputOutput();

        try {
            productsList = productsListHandler.loadProductsList();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to load products list. Creating clean list.");
            productsList = new ProductsList();
        }

        try {
            shoppingList = shoppingListHandler.loadShoppingList();
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Failed to load shopping list. Creating clean list.");
            shoppingList = new ShoppingList();
        } catch (DataCorruptedException p) {
            JOptionPane.showMessageDialog(this, "Data in shopping list is corrupted. Creating clean list.");
            shoppingList = new ShoppingList();
        }

        initializeComponents();
        createLayout();
    }

    private void initializeComponents() {
        outputTextArea = new JTextArea(20, 30);
        outputTextArea.setEditable(false);

        initButtons();
    }

    private void initButtons() {
        addProductToShoppingListButton = new JButton("Add product to shopping list");
        addProductToShoppingListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addProductToShoppingList();
            }
        });

        removeProductFromShoppingListButton = new JButton("Remove product from shopping list");
        removeProductFromShoppingListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeProductFromShoppingList();
            }
        });

        clearShoppingListButton = new JButton("Clear shopping list");
        clearShoppingListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearShoppingList();
            }
        });

        showAllProductsFromShoppingListButton = new JButton("Display shopping list");
        showAllProductsFromShoppingListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayProducts();
            }
        });

        showProductsFromShoppingListByCategoryButton = new JButton("Display shopping list by category");
        showProductsFromShoppingListByCategoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayProductsByCategory();
            }
        });

        saveShoppingListToFileButton = new JButton("Save shopping list to file");
        saveShoppingListToFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveShoppingList();
            }
        });

        addToProductsListButton = new JButton("Add to products list");
        addToProductsListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addProductToProductsList();
            }
        });

        removeFromProductsListButton = new JButton("Remove from products list");
        removeFromProductsListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeProductFromProductsList();
            }
        });

        removeProductsFromShoppingListInCategoryButton = new JButton("Remove all products from shopping list in category");
        removeProductsFromShoppingListInCategoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeProductsFromCategory();
            }
        });

        displayAvailableProductsButton = new JButton("Show available products");

        displayAvailableProductsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayAvailableProducts();
            }
        });
    }

    private void createButtonsPanel() {
        buttonsPanel = new JPanel(new GridLayout(0,1));

        buttonsPanel.add(addProductToShoppingListButton);
        buttonsPanel.add(removeProductFromShoppingListButton);
        buttonsPanel.add(clearShoppingListButton);
        buttonsPanel.add(removeProductsFromShoppingListInCategoryButton);

        buttonsPanel.add(showAllProductsFromShoppingListButton);
        buttonsPanel.add(showProductsFromShoppingListByCategoryButton);
        buttonsPanel.add(saveShoppingListToFileButton);

        buttonsPanel.add(addToProductsListButton);
        buttonsPanel.add(removeFromProductsListButton);
        buttonsPanel.add(displayAvailableProductsButton);
    }


    private void createLayout() {
        mainMenuPanel = new JPanel(new GridLayout(1,2, 50 ,0));
        createButtonsPanel();

        mainMenuPanel.add(buttonsPanel);
        mainMenuPanel.add(outputTextArea);

        setContentPane(mainMenuPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Shopping list");
        pack();
        setVisible(true);
    }

    private void clearShoppingList() {
        shoppingList = new ShoppingList();
        JOptionPane.showMessageDialog(this, "Shopping list cleared successfully.");
    }

    private void removeProductFromShoppingList() {
        if (shoppingList.shoppingListIsEmpty()) {
            JOptionPane.showMessageDialog(this, "Shopping list is empty.");
            return;
        }

        ShoppingListModificationDialogBox input = new ShoppingListModificationDialogBox();
        String quantityString = input.getQuantityLabelContents();
        String type = input.getTypeLabelContents();
        String categoryName = input.getCategoryLabelContents();
        String productName = input.getProductLabelContents();

        if((categoryName ==  null) || (categoryName.length() == 0) &&
                (productName == null) || (productName.length() == 0) &&
                (type == null) || (type.length() == 0) &&
                (quantityString == null) || (quantityString.length() == 0)) {
            JOptionPane.showMessageDialog(this, "Not all data provided.");
            return;
        }

        float quantity;
        try {
            quantity = Float.parseFloat(quantityString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Provided quantity is not a number.");
            return;
        }

        Category category = shoppingList.getCategory(categoryName);
        if (category == null) {
            JOptionPane.showMessageDialog(this, "Category does not exist");
            return;
        }

        Product product = null;
        for (Product i : category.getProducts()) {
            if ((i.getName().equals(productName)) &&
                    (i.getType().equals(type)) &&
                    (i.getQuantity() == quantity)) {
                    product = i;
                    break;
            }
        }

        if (product != null) {
            if (category.getProducts().size() == 1) {
                shoppingList.getContents().remove(category);
            } else {
                category.getProducts().remove(product);
            }
            JOptionPane.showMessageDialog(this, "Product removed successfully");
            return;
        }

        JOptionPane.showMessageDialog(this, "Product was not found in selected category.");
    }

    private void displayProducts(){
        String result = "";
        for(Category i : shoppingList.getContents()) {
            result = result.concat(i.getName()).concat(":\n");
            for(Product j : i.getProducts()) {
                result = result.concat("\t").
                        concat(j.getName()).
                        concat(" ").
                        concat(j.typeParser()).
                        concat(" ").
                        concat(j.getType()).
                        concat("\n");
            }
        }

        outputTextArea.setText(result);
    }

    private void displayProductsByCategory() {
        String categoryName = JOptionPane.showInputDialog(this, "Enter category name:");
        if (categoryName == null) {
            return;
        }
        categoryName = categoryName.trim();

        Category category = null;
        for (Category i : shoppingList.getContents()) {
            if (i.getName().equals(categoryName)) {
                category = i;
                break;
            }
        }

        if (category == null) {
            JOptionPane.showMessageDialog(this, "Category does not exist.");
            return;
        }

        String result = "";
        for (Product i : category.getProducts()) {
            result = result.concat(i.getName()).
                        concat(" ").
                        concat(i.typeParser()).
                        concat(" ").
                        concat(i.getType()).
                        concat("\n");
        }

        outputTextArea.setText(result);
    }

    private void addProductToProductsList() {

        String productName = JOptionPane.showInputDialog(this, "Enter product name:");
        if (productName == null) {
            return;
        }
        productName = productName.trim();

        if (productsList.findProduct(productName) != null) {
            JOptionPane.showMessageDialog(this, "Product already exists.");
            return;
        }

        String categoryName = JOptionPane.showInputDialog(this, "Enter category name:");
        if(categoryName == null) {
            return;
        }
        categoryName = categoryName.trim();

        Category category = null;
        for(Category i : productsList.getContents()) {
            if(categoryName.compareTo(i.getName()) == 0) {
                category = i;
                break;
            }
        }

        if (category == null) {
            JOptionPane.showMessageDialog(this, "Category doesn't exist. Adding new category with new product to products list.");
            category = new Category(categoryName);
            category.addProductToCategory(new Product(productName, null, 0));
            productsList.addCategory(category);
        } else {
            category.addProductToCategory(new Product(productName, null, 0));
        }

        try {
            productsListHandler.saveProductsList(productsList);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to save changes.");
            productsList.getContents().remove(category);
            return;
        }
        JOptionPane.showMessageDialog(this, "Product successfully added.");
    }

    private void removeProductFromProductsList() {
        if(productsList.productsListIsEmpty()) {
            JOptionPane.showMessageDialog(this, "Products list is empty.");
            return;
        }

        String productName = JOptionPane.showInputDialog(this, "Enter product name:");
        if (productName == null) {
            return;
        }
        productName = productName.trim();

        Product product = productsList.findProduct(productName);
        if (product == null) {
            JOptionPane.showMessageDialog(this, "Product does not exist.");
            return;
        }

        Category categoryToRemove = null;
        for (Category i : productsList.getContents()) {
            boolean found = false;
            for (Product j : i.getProducts()) {
                if (j.getName().equals(productName)) {
                    found = true;
                    break;
                }
            }
            if(found) {
                if(i.getProducts().size() == 1) {
                    categoryToRemove = i;
                }
                i.getProducts().remove(product);
            }
        }

        if (categoryToRemove != null) {
            productsList.getContents().remove(categoryToRemove);
        }

        try {
            productsListHandler.saveProductsList(productsList);
            JOptionPane.showMessageDialog(this, "Product successfully removed");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to save changes.");
        }
    }

    private void saveShoppingList() {
        try {
            shoppingListHandler.saveShoppingList(shoppingList);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to save shopping list.");
        }
        JOptionPane.showMessageDialog(this, "Shopping list saved successfully.");
    }

    private void addProductToShoppingList() {
        if (productsList.productsListIsEmpty()) {
            JOptionPane.showMessageDialog(this, "No products available to add.");
            return;
        }

        ShoppingListModificationDialogBox input = new ShoppingListModificationDialogBox();
        String categoryName = input.getCategoryLabelContents();
        String productName = input.getProductLabelContents();
        String type = input.getTypeLabelContents();
        String quantityString = input.getQuantityLabelContents();

        if((categoryName ==  null) || (categoryName.length() == 0) ||
                (productName == null) || (productName.length() == 0) ||
                (type == null) || (type.length() == 0) ||
                (quantityString == null) || (quantityString.length() == 0)) {
            JOptionPane.showMessageDialog(this, "Not all data provided.");
            return;
        }

        float quantity;
        try {
            quantity = Float.parseFloat(quantityString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Provided quantity is not a number.");
            return;
        }

        if (productsList.findProduct(productName) == null) {
            JOptionPane.showMessageDialog(this, "Such product is unavailable.");
            return;
        }

        Category category = shoppingList.getCategory(categoryName);
        if (category == null) {
            category = new Category(categoryName);
            shoppingList.addCategory(category);
        }

        category.addProductToCategory(new Product(productName, type, quantity));

        JOptionPane.showMessageDialog(this, "Product added successfully.");
    }

    private void removeProductsFromCategory() {
        if (shoppingList.shoppingListIsEmpty()) {
            JOptionPane.showMessageDialog(this, "Shopping list is empty.");
            return;
        }

        String categoryName = JOptionPane.showInputDialog(this, "Enter category name:");
        if(categoryName == null) {
            return;
        }
        categoryName = categoryName.trim();

        Category toRemove = null;
        for (Category i : shoppingList.getContents()) {
            if (i.getName().equals(categoryName)) {
                toRemove = i;
            }
        }

        if (toRemove == null) {
            JOptionPane.showMessageDialog(this, "Category does not exist.");
            return;
        }

        shoppingList.getContents().remove(toRemove);
    }

    private void displayAvailableProducts() {
        String result = "";

        for (Category i : productsList.getContents()) {
            result = result.concat(i.getName()).concat(":\n");
            for (Product j : i.getProducts()) {
                result = result.concat("\t").concat(j.getName()).concat("\n");
            }
        }

        outputTextArea.setText(result);
    }

}
