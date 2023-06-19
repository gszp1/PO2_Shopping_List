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
    private JTextArea inputTextArea;

    private JButton addProductButton;
    private JButton showAllProductsButton;
    private JButton showProductsByCategoryButton;
    private JButton removeProductButton;
    private JButton removeAllProductButton;
    private JButton saveListButton;

    private JButton addToProductsListButton;

    private JButton removeFromProductsListButton;

    public MainMenu() {
        shoppingListHandler = new ShoppingListInputOutput();
        productsListHandler = new ProductsListInputOutput();

        try {
            productsList = productsListHandler.loadProductsList();
        } catch (IOException e) {
            System.out.println("Failed to load products list");
            productsList = new ProductsList();
        }

        try {
            shoppingList = shoppingListHandler.loadShoppingList();
        } catch (IOException | NumberFormatException e) {
            System.out.println("Failed to load shopping list");
            shoppingList = new ShoppingList();
        }

        initializeComponents();
        createLayout();
    }

    private void initializeComponents() {
        outputTextArea = new JTextArea(20, 30);
        outputTextArea.setEditable(false);

        inputTextArea = new JTextArea(10, 40);
        inputTextArea.setEditable(true);

        initButtons();
    }

    private void initButtons() {
        addProductButton = new JButton("Add product to list");
        addProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        removeProductButton = new JButton("Remove product from list");
        removeProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//
            }
        });

        removeAllProductButton= new JButton("Clear shopping list");
        removeAllProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearShoppingList();
            }
        });

        showAllProductsButton = new JButton("Display all products");
        showAllProductsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayProducts();
            }
        });

        showProductsByCategoryButton = new JButton("Display by category");
        showProductsByCategoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayProductsByCategory();
            }
        });

        saveListButton = new JButton("Save list to file");
        saveListButton.addActionListener(new ActionListener() {
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
    }

    private void createButtonsPanel() {
        buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(addProductButton);
        buttonsPanel.add(removeProductButton);
        buttonsPanel.add(removeAllProductButton);
        buttonsPanel.add(saveListButton);
        buttonsPanel.add(showAllProductsButton);
        buttonsPanel.add(showProductsByCategoryButton);
        buttonsPanel.add(addToProductsListButton);
        buttonsPanel.add(removeFromProductsListButton);
    }


    private void createLayout() {
        mainMenuPanel = new JPanel(new BorderLayout());
        createButtonsPanel();

        mainMenuPanel.add(outputTextArea, BorderLayout.NORTH);
        mainMenuPanel.add(buttonsPanel, BorderLayout.SOUTH);

        setContentPane(mainMenuPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Product Listing");
        pack();
        setVisible(true);
    }

    private void clearShoppingList() {
        shoppingList = new ShoppingList();
        JOptionPane.showMessageDialog(this, "Shopping list cleared successfully.");
    }

    private void removeProductFromShoppingList() {

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

        if (productsList.findProduct(productName) != null) {
            JOptionPane.showMessageDialog(this, "Product already exists.");
            return;
        }

        String categoryName = JOptionPane.showInputDialog(this, "Enter category name:");
        if(categoryName == null) {
            return;
        }

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
        String productName = JOptionPane.showInputDialog(this, "Enter product name:");
        if (productName == null) {
            return;
        }

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

    }

}
