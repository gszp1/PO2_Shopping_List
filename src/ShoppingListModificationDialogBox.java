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
        String result = (String)(cBox.getSelectedItem());
        if (result != null) {
            result = result.trim();
        }
        return result;
    }

    public String getQuantityLabelContents() {
        String result = quantityField.getText();
        if (result != null) {
            result = result.trim();
        }
        return result;
    }

    public String getProductLabelContents() {
        String result = productField.getText();;
        if (result != null) {
            result = result.trim();
        }
        return result;
    }

    public String getCategoryLabelContents() {
        String result = categoryField.getText();
        if (result != null) {
            result = result.trim();
        }
        return result;
    }
}
