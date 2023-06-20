import javax.swing.*;

public class ShoppingListModificationDialogBox {

    private final JComboBox<String> cBox = new JComboBox<>(Product.allowedTypes);

    private final JTextField quantityField = new JTextField();

    private final JTextField productField = new JTextField();

    private final JTextField categoryField = new JTextField();

    private final Object[] fields = {
            "type", cBox,
            "quantity", quantityField,
            "product", productField,
            "category", categoryField
    };

    public ShoppingListModificationDialogBox() {
        JOptionPane.showConfirmDialog(null, fields, "Select parameters", JOptionPane.OK_CANCEL_OPTION);
    }

    public String getTypeLabelContents() {
        return (String)(cBox.getSelectedItem());
    }

    public String getQuantityLabelContents() {
        return quantityField.getText();
    }

    public String getProductLabelContents() {
        return productField.getText();
    }

    public String getCategoryLabelContents() {
        return categoryField.getText();
    }
}
