import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainMenu extends JFrame {

    private JTextArea outputArea;

    private JTextArea inputArea;

    private JButton addProductButton;

    private JButton displayByCategoryButton;

    private JButton displayAllProductsButton;

    private JButton removeAllProductsButton;

    private JButton removeProductButton;

    private JButton saveProductsToListFileButton;

    private JButton updateProductsListButton;

    private JPanel MainMenuPanel;

    private JPanel buttonsPanel;

    public MainMenu() {
        initMenuComponents();
        InitDisplay();
    }

    private void initMenuComponents() {
        outputArea = new JTextArea(20, 30);
        outputArea.setEditable(false);

        inputArea = new JTextArea(10, 40);
        inputArea.setEditable(true);

        addProductButton = new JButton("Add product to list");
        removeProductButton = new JButton("Remove product");
        removeAllProductsButton = new JButton("Clear list");
        saveProductsToListFileButton = new JButton("Save list");
        displayAllProductsButton = new JButton("Display list");
        displayByCategoryButton = new JButton("Display list by category");
        updateProductsListButton = new JButton("Add product");

        addProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                //TODO
            }
        });
        removeProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                //TODO
            }
        });

        removeAllProductsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                //TODO
            }
        });

        saveProductsToListFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                //TODO
            }
        });

        displayAllProductsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                //TODO
            }
        });

        displayByCategoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                //TODO
            }
        });

        updateProductsListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                //TODO
            }
        });
    }


    private void InitDisplay() {
        MainMenuPanel = new JPanel(new BorderLayout());
        InitializeButtonsArea();

//        MainMenuPanel.setSize(500,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Shopping list");

        MainMenuPanel.add(buttonsPanel, BorderLayout.WEST);
        MainMenuPanel.add(outputArea, BorderLayout.EAST);

        pack();
        setVisible(true);
    }

    private void InitializeButtonsArea() {
        buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(addProductButton);
        buttonsPanel.add(removeProductButton);
        buttonsPanel.add(removeAllProductsButton);
        buttonsPanel.add(saveProductsToListFileButton);
        buttonsPanel.add(displayAllProductsButton);
        buttonsPanel.add(displayByCategoryButton);
        buttonsPanel.add(updateProductsListButton);
    }
}

